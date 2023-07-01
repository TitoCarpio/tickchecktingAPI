package com.ncapas.tickcheckting.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.dtos.SaveUserDTO;
import com.ncapas.tickcheckting.models.entities.Token;
import com.ncapas.tickcheckting.models.entities.User;
import com.ncapas.tickcheckting.repositories.TokenRepo;
import com.ncapas.tickcheckting.repositories.UserRepo;
import com.ncapas.tickcheckting.services.IUser;
import com.ncapas.tickcheckting.utils.JWTTools;

import jakarta.transaction.Transactional;

@Service
public class UserImpl implements IUser {
	@Autowired
	public PasswordEncoder passwordEncoder;

	@Autowired
	UserRepo userRepo;

	@Autowired
	private JWTTools jwtTools;

	@Autowired
	private TokenRepo tokenRepository;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(SaveUserDTO info) throws Exception {
		User user = new User(info.getUsername(), info.getEmail(), passwordEncoder.encode(info.getPassword()), false);
		userRepo.save(user);
		
		

	}

	@Override
	public User findOneByIdentifier(String identifier) {
		return userRepo.findOneByUsernameOrEmail(identifier, identifier);
	}

	@Override
	public User findOneByUsernameOrEmail(String username, String email) {
		return userRepo.findOneByUsernameOrEmail(username, email);
	}

	@Override
	public Boolean comparePassword(String toCompare, String current) {
		return passwordEncoder.matches(toCompare, current);
	}

	@Override
	public User findUserAuthenticated() {
		//String username = SecurityContextHolder.getContext().getAuthentication().getName();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepo.findOneByUsernameOrEmail(username, username);
	}

	@Override
	public Token registerToken(User user) throws Exception {
		cleanTokens(user);

		String tokenString = jwtTools.generateToken(user);
		Token token = new Token(tokenString, user);

		tokenRepository.save(token);

		return token;
	}

	@Override
	public Boolean isTokenValid(User user, String token) {
		try {
			cleanTokens(user);
			List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

			tokens.stream().filter(tk -> tk.getContent().equals(token)).findAny().orElseThrow(() -> new Exception());

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void cleanTokens(User user) throws Exception {
		List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

		tokens.forEach(token -> {
			if (!jwtTools.verifyToken(token.getContent())) {
				token.setActive(false);
				tokenRepository.save(token);
			}
		});

	}

	//actualiza un usuario existente
	@Override
	public void activeUser(User user) throws Exception{
//		User user = userRepo.findOneByUsernameOrEmail(username, username);
		boolean active = user.getActive();
		if (!active) {
			user.setActive(true);
			userRepo.save(user);
		}
		
	}

	@Override
	public Page<User> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("username"));
		
		Page<User> users = userRepo.findAll(pageable);
		return users;
	}
	
	
	
	

}

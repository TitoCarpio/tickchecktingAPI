package com.ncapas.tickcheckting.models.entities;

import java.util.Collection;
import java.util.List;
//import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.ToString;
import lombok.ToString;

@Data
@ToString(exclude = {"tokens", "purchase", "attend","sender", "reciver", "userPermision"}) //agrego mas campos al exclude
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	@JsonIgnore
	private String password;

	@Column(name = "active", insertable = true)
	private Boolean active;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UserXPermision> userPermision;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Token> tokens;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Purchase> purchase;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Attend> attend;
	
	@OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Transfer> sender;
	
	@OneToMany(mappedBy = "reciver", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Transfer> reciver;

	// modificaciones para el uso del token
	private static final long serialVersionUID = 1460435087476558985L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	// getUsername is already overridden

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return this.active;
	}

	public User(String username, String email, String password, Boolean active) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.active = active;
	}

}

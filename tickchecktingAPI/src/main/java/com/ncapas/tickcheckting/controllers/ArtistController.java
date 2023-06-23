package com.ncapas.tickcheckting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.DeleteArtistDTO;
import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.dtos.NewArtistDTO;
import com.ncapas.tickcheckting.models.dtos.UpdateArtistDTO;
import com.ncapas.tickcheckting.services.IArtist;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class ArtistController {
	@Autowired
	IArtist artistServices;
	
	@Autowired
	private RequestErrorHandler errorHandler;
	
	@DeleteMapping("deleteArtistE")
	public ResponseEntity<?> delete(@RequestBody @Valid DeleteArtistDTO info, BindingResult validations){
		
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		try {
			if (!info.getCodeArtist().equals(null) && !info.getCodeEvent().equals(null)) {
				artistServices.deleteByCode(info.getCodeArtist(), info.getCodeEvent());
				return new ResponseEntity<>(new MessageDTO("Artist deleted"), HttpStatus.OK);
			}else
				return new ResponseEntity<>("field not found",HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Artist or event not found",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("updArtist")
	public ResponseEntity<?> update(@RequestBody @Valid UpdateArtistDTO info, BindingResult validations){
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		try {
			artistServices.update(info);
			return new ResponseEntity<>(new MessageDTO("Artist updated"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping("saveArtist")
	public ResponseEntity<?> creatArtist(@RequestBody @Valid NewArtistDTO info, BindingResult validations){
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		try {
			artistServices.save(info);
			return new ResponseEntity<>(new MessageDTO("Artist created"), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Event not found or the artist already exists",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("deleteArtist/{code}")
	public ResponseEntity<?> deleteArtist(@PathVariable String code){
		if (!code.equals(null)) {
			try {
				artistServices.delete(code);
				return new ResponseEntity<>("Artist deleted", HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else
			return new ResponseEntity<>("Item not found",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}

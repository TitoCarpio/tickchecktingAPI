package com.ncapas.tickcheckting.models.dtos;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.Size;

@Data
public class SaveEventDTO {
	@NotEmpty
	private String name;

	@NotEmpty
	private String image;

	@NotEmpty
	private String category;

	@NotEmpty
	private String placeCode;

	@NotNull
	private Date eventDate;
	
	@NotNull
	private LocalTime eventHour;

	@NotEmpty
	@Size(min = 1, message = "El evento tiene que contar con almenos un artista")
	private List<SaveArtistDTO> artists;

	@NotEmpty
	@Size(min = 1, message = "El evento debe de contar con almenos un categoria de tickets")
	private List<SaveTicketCatDTO> ticket;

	@NotEmpty
	@Size(min = 1, message = "El evento debe de contar con almenos un patrocinador")
	private List<SaveSponsorDTO> sponsor;
}

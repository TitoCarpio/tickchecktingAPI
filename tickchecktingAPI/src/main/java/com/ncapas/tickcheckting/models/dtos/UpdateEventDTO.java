package com.ncapas.tickcheckting.models.dtos;

import java.time.LocalTime;
import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateEventDTO {
	
	@NotEmpty
	private String code;
	
	@NotEmpty
	private String name;
	
	@NotNull
	private Date eventDate;

	@NotNull
	private LocalTime eventHour;

	@NotEmpty
	private String image;
	
	@NotEmpty
	private String category;

	@NotEmpty
	private String placeCode;

}

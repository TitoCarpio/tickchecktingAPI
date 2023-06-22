package com.ncapas.tickcheckting.models.dtos;

import java.time.LocalTime;
import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import net.bytebuddy.utility.nullability.NeverNull;

@Data
public class UpdateEventDTO {
	
	@NotEmpty
	private String code;
	
	@NotEmpty
	private String name;
	
	@NeverNull
	private Date eventDate;

	private LocalTime eventHour;

	@NotEmpty
	private String image;
	
	@NotEmpty
	private String category;

	@NotEmpty
	private String placeCode;

}

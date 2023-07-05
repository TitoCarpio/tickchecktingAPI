package com.ncapas.tickcheckting.models.dtos;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestEventDTO {
	private UUID code;
	private String nameEvent;
	private Date eventDay;
	private LocalTime hourEvent;
	private String image;
	private String namePlace;
}

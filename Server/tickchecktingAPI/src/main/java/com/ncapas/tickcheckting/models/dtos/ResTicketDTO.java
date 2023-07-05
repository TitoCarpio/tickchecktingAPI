package com.ncapas.tickcheckting.models.dtos;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResTicketDTO {
	private UUID codeTicket;

	private boolean active;

	private UUID codeTicketCat;

	private String nameTicktCat;

	private String nameEvent;

	private List<String> nameArtist;

	private Date dateEvent;

	private LocalTime eventHour;

}

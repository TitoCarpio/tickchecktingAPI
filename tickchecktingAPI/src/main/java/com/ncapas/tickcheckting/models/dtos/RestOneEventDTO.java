package com.ncapas.tickcheckting.models.dtos;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ncapas.tickcheckting.models.entities.Artist;
import com.ncapas.tickcheckting.models.entities.EventCategory;
import com.ncapas.tickcheckting.models.entities.Place;
import com.ncapas.tickcheckting.models.entities.Sponsor;
import com.ncapas.tickcheckting.models.entities.TicketCategory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestOneEventDTO {
	private UUID code;
	private String nameEvent;
	private EventCategory category;
	private Date eventDay;
	private LocalTime hourEvent;
	private String image;
	private Place namePlace;
	private List<Artist> artistEvent;
	private List<Sponsor> sponsorEvent;
	private List<TicketCategory> tikets;
}

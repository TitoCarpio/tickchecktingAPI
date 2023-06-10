package com.ncapas.tickcheckting.entities;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

@Data
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	
	@Column(name = "event_date")
	private Date event_date;
	
	@Column(name = "event_hour")
    private LocalTime eventHour;
	
	@Column(name = "imagen")
	private String imagen;
	
	@Column(name = "created_date")
	private Date created_date;
	
	@Column(name = "upddate")
	private Date upddate;
	
	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<EventXArtist> eventArtist;

	public Event(String name, String description, Date event_date, LocalTime eventHour, String imagen,
			Date created_date, Date upddate) {
		super();
		this.name = name;
		this.description = description;
		this.event_date = event_date;
		this.eventHour = eventHour;
		this.imagen = imagen;
		this.created_date = created_date;
		this.upddate = upddate;
	}
	
	

}

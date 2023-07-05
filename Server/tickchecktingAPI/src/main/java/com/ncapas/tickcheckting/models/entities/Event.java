package com.ncapas.tickcheckting.models.entities;

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
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = {"eventArtist", "eventSponsor"})
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


	@Column(name = "event_date")
	private Date event_date;

	@Column(name = "event_hour")
	private LocalTime eventHour;

	@Column(name = "image")
	private String imagen;

	@Column(name = "created_date")
	@JsonIgnore
	private Date created_date;

	@Column(name = "upddat")
	@JsonIgnore
	private Date upddate;

	@OneToOne
	@JoinColumn(name = "category_id", nullable = false)
	private EventCategory eventCategory;

	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
	private List<EventXArtist> eventArtist;
	
	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<EventXSponsor> eventSponsor;
	

	@OneToOne
	@JoinColumn(name = "place_id", nullable = false)
	private Place place_id;
	
	


	public Event(String name, Date event_date, LocalTime eventHour, String imagen, Date created_date,
			Date upddate, EventCategory eventCategory, Place place_id) {
		super();
		this.name = name;
		this.event_date = event_date;
		this.eventHour = eventHour;
		this.imagen = imagen;
		this.created_date = created_date;
		this.upddate = upddate;
		this.eventCategory = eventCategory;
		this.place_id = place_id;
	}
}

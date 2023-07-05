package com.ncapas.tickcheckting.models.entities;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "eventxartist")
public class EventXArtist {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;

	@Column(name = "created_date")
	private Date created_date;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "artist_id", nullable = false)
	@JsonIgnore
	private Artist artist;
	
	@ManyToOne
	@JoinColumn(name = "event_id", nullable = false)
	@JsonIgnore
	private Event event;

	public EventXArtist(Date created_date, Artist artist, Event event) {
		super();
		this.created_date = created_date;
		this.artist = artist;
		this.event = event;
	}
	
	
}

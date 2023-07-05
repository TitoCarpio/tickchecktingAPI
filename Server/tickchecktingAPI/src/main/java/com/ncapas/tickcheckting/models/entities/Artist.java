package com.ncapas.tickcheckting.models.entities;

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
@Table(name = "artist")
public class Artist {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@Column(name = "name")
	private String name;
	
	
	@Column(name = "created_date")
	private Date created_date;
	
	@OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<EventXArtist> eventArtist;

	public Artist(String name, Date created_date) {
		super();
		this.name = name;
		this.created_date = created_date;
	}
	
	

}

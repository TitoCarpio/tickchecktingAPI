package com.ncapas.tickcheckting.models.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "place")
public class Place {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "created_date")
	private Date created_date;

	@Column(name = "upddate")
	private Date upddate;

	public Place(String name, String description, Date created_date, Date upddate) {
		super();
		this.name = name;
		this.description = description;
		this.created_date = created_date;
		this.upddate = upddate;
	}
	
	
}

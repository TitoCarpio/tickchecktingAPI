package com.ncapas.tickcheckting.models.entities;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	
	@Column(name = "created_date")
	@JsonIgnore
	private Date created_date;

	@Column(name = "upddat")
	@JsonIgnore
	private Date upddate;

	public Place(String name, Date created_date, Date upddate) {
		super();
		this.name = name;
		this.created_date = created_date;
		this.upddate = upddate;
	}
	
	
}

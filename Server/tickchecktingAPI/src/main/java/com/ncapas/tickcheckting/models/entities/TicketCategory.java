package com.ncapas.tickcheckting.models.entities;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "ticket_category")
public class TicketCategory {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private float price;

	@Column(name = "qty")
	private int qty;

	@Column(name = "created_date")
	@JsonIgnore
	private Date created_date;

	@Column(name = "upddat")
	@JsonIgnore
	private Date upddate;

	// CREANDO LAS FK
	@ManyToOne
	@JoinColumn(name = "event_id")
	@JsonIgnore
	private Event event_id;

	public TicketCategory(String name, float price, int qty, Date created_date, Date upddate, Event event_id) {
		super();
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.created_date = created_date;
		this.upddate = upddate;
		this.event_id = event_id;
	}

	

}

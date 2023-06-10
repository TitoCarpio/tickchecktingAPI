package com.ncapas.tickcheckting.entities;

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
@Table(name = "ticket")
public class Ticket {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	//coloco que si es modificable por que el ticket cuando se canjee se desactivara
	@Column(name = "active", insertable = true)
	private Boolean active;
	
	@Column(name = "created_date")
	private Date created_date;
	
	//creando FK
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	@JsonIgnore
	private Category category_id;
	
	@ManyToOne
	@JoinColumn(name = "purchase_id", nullable = false)
	@JsonIgnore
	private Purchase purchase_id;

	//constructor
	public Ticket(Boolean active, Date created_date) {
		super();
		this.active = active;
		this.created_date = created_date;
	}
	
	
	
	
	
	
	
}
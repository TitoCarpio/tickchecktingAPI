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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "attend")
public class Attend {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@Column(name = "upddat")
	private Date upddat;
	
	@Column(name = "actdat")
	private Date actdat;
	
	//agregando las FK
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user_id;
	
	@OneToOne
	@JoinColumn(name = "ticket_id", nullable = false)
	@JsonIgnore
	private Ticket ticket_id;
	
	@ManyToOne
	@JoinColumn(name = "event_id", nullable = false)
	@JsonIgnore
	private Event event_id;

	public Attend(Date upddat, Date actdat) {
		super();
		this.upddat = upddat;
		this.actdat = actdat;
	}
	
	
}

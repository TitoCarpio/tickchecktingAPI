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
//@AllArgsConstructor
@Entity
@Table(name = "transfer")
public class Transfer {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;

	@Column(name = "created_date")
	private Date created_date;

	@Column(name = "hastemp")
	private String hastemp;

	@Column(name = "actdat")
	private Date actdat;

	@Column(name = "status")
	private boolean status;

	// CREANDO LAS FK
	@OneToOne
	@JoinColumn(name = "ticket_id", nullable = false)
	@JsonIgnore
	private Ticket ticket;

	@ManyToOne
	@JoinColumn(name = "sender_user_id", nullable = false)
	@JsonIgnore
	private User sender;

	@ManyToOne
	@JoinColumn(name = "reciver_user_id", nullable = false)
	@JsonIgnore
	private User reciver;

	public Transfer(Date created_date, String hastemp, Date actdat, boolean status, Ticket ticket, User sender_user_id,
			User reciver_user_id) {
		super();
		this.created_date = created_date;
		this.hastemp = hastemp;
		this.actdat = actdat;
		this.status = status;
		this.ticket = ticket;
		this.sender = sender_user_id;
		this.reciver = reciver_user_id;
	}

}

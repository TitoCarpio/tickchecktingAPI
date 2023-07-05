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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = "tickets")
@NoArgsConstructor
@Entity
@Table(name = "purchase")
public class Purchase {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@Column(name = "upddat")
	private Date upddate;
	
	@Column(name = "actdat")
	private Date actdat;
	
	//CREANDO LAS FK
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY )
	@JsonIgnore
	private List<Ticket> tickets;
	

	public Purchase(Date upddate, Date actdat, User user_id) {
		super();
		this.upddate = upddate;
		this.actdat = actdat;
		this.user = user_id;
	}	
}

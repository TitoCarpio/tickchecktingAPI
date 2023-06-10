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
@Table(name = "userxpermision")
public class UserXPermision {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;

	@Column(name = "created_date")
	private Date created_date;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;

	@ManyToOne
	@JoinColumn(name = "permision_id", nullable = false)
	@JsonIgnore
	private Permision permision;

	public UserXPermision(Date created_date, User user, Permision permision) {
		super();
		this.created_date = created_date;
		this.user = user;
		this.permision = permision;
	}

}

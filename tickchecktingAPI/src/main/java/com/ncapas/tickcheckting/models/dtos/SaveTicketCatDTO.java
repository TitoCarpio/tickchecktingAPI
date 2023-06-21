package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveTicketCatDTO {
	@NotEmpty
	private String name;
	@NotEmpty
	@Min(1)
	private float price;
	@NotEmpty
	@Min(1)
	private int qty;
}

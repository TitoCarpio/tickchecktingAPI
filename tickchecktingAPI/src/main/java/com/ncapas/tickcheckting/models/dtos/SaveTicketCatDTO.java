package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaveTicketCatDTO {
	@NotEmpty
	private String name;
	@NotNull
	@Min(1)
	private float price;
	@NotNull
	@Min(1)
	private int qty;
}

package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PurchaseDTO {
	@NotNull
	private int cantidad;
	
	@NotEmpty
	private String ticketCatCode;

	
}

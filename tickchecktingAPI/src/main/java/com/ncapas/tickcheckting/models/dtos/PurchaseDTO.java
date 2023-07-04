package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PurchaseDTO {
	@NotEmpty
	private int cantidad;
	
	@NotEmpty
	private String ticketCatCode;

	
}

package com.ncapas.tickcheckting.models.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class UpdateTicketCatDTO {
//	@NotEmpty
	@NotNull
	private UUID code;

	@NotEmpty
	private String name;

	@NotNull
	@Min(1)
	private float price;

	@NotNull
	@Min(1)
	private int qty;

//	public UpdateTicketCatDTO(@NotEmpty String code, @NotEmpty String name, @NotNull @Min(1) float price,
//			@NotNull @Min(1) int qty) {
//		super();
//		this.code = code;
//		this.name = name;
//		this.price = price;
//		this.qty = qty;
//	}
	
	

}

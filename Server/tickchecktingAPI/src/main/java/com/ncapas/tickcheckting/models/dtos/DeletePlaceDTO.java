package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DeletePlaceDTO {
	@NotEmpty
	private String placeCode;
}

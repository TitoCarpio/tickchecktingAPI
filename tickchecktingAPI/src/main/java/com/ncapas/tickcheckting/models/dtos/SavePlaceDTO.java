package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SavePlaceDTO {
	@NotEmpty(message ="El nombre del lugar es necesario")
	private String name;
}

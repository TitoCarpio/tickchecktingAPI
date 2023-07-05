package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveECategoryDTO {
	@NotEmpty(message = "El nombre de la categoria no puede estar vacia")
	private String name;
}

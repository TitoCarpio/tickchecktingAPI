package com.ncapas.tickcheckting.models.dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SavePermisionDTO {
	@NotEmpty(message = "El nombre del permiso no puede estar vacio")
	@Size(min = 4, max = 32)
	private String name;

}

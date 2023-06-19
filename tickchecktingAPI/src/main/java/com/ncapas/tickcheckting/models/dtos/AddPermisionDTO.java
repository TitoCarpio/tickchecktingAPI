package com.ncapas.tickcheckting.models.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AddPermisionDTO {
	@NotEmpty
	private String permision;
}

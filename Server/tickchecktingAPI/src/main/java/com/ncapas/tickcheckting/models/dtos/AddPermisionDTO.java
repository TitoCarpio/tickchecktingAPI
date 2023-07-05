package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AddPermisionDTO {
	@NotEmpty
	private String permision;
	
	@NotEmpty
	private String user;
}

package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ActiveUserDTO {
	@NotEmpty
	private String username;
}

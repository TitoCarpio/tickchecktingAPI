package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLoginGDTO {
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String email;
}

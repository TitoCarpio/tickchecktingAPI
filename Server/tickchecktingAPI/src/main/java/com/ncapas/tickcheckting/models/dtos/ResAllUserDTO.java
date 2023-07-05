package com.ncapas.tickcheckting.models.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResAllUserDTO {
	private String username;
	private boolean active;
	private String email;
	private List<PermisionDTO> permisions;
}

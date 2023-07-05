package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateSponsorDTO {
	@NotEmpty
	private String code;
	@NotEmpty
	private String name;
}

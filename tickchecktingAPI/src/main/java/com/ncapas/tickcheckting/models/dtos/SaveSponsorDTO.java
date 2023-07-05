package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveSponsorDTO {
	@NotEmpty
	private String name;
}

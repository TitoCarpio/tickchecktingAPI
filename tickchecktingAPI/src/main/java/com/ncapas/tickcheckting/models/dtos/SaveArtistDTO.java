package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveArtistDTO {
	@NotEmpty
	private String name;
}

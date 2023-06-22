package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateArtistDTO {
	@NotEmpty
	private String codeArtist;
	@NotEmpty
	private String name;
}

package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DeleteArtistDTO {
	@NotEmpty
	private String codeEvent;
	
	@NotEmpty
	private String codeArtist;

}

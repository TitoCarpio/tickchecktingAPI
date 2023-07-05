package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DeleteSponsorDTO {
	@NotEmpty
	private String codeEvent;
	@NotEmpty
	private String codeSponsor;
}

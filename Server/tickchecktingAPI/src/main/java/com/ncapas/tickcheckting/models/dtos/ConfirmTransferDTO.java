package com.ncapas.tickcheckting.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ConfirmTransferDTO {
	@NotEmpty
	private String codeTransfer;
	@NotEmpty
	private String hash;
}

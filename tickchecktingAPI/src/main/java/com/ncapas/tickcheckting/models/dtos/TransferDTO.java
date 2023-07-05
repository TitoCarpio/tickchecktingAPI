package com.ncapas.tickcheckting.models.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferDTO {
	@NotEmpty
	private String emailReciver;
	
	@NotNull
	private UUID codeTicket;	
}

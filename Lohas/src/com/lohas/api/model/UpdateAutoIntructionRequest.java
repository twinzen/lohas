package com.lohas.api.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.lohas.data.BlobImageJdo;

public class UpdateAutoIntructionRequest extends CommonRequest {

	/*
	 * Auto Instruction ID
	 * Primary key
	 */
	@NotNull
	private Long autoInstructionId;
	
	/*
	 * Auto Instruction Frequency
	 */
	@NotNull
	private String autoInstructionFrequency;
	
	/*
	 * Auto Instruction Amount
	 */
	@NotNull
	private BigDecimal autoInstructionAmount;

	public Long getAutoInstructionId() {
		return autoInstructionId;
	}

	public void setAutoInstructionId(Long autoInstructionId) {
		this.autoInstructionId = autoInstructionId;
	}

	public String getAutoInstructionFrequency() {
		return autoInstructionFrequency;
	}

	public void setAutoInstructionFrequency(String autoInstructionFrequency) {
		this.autoInstructionFrequency = autoInstructionFrequency;
	}

	public BigDecimal getAutoInstructionAmount() {
		return autoInstructionAmount;
	}

	public void setAutoInstructionAmount(BigDecimal autoInstructionAmount) {
		this.autoInstructionAmount = autoInstructionAmount;
	}
	
	
}

package com.lohas.api.model.common;

import java.math.BigDecimal;

public class AutoInstruction {

	/*
	 * Auto Instruction ID
	 * Primary key
	 */
	private Long autoInstructionId;
	
	/*
	 * Frequency
	 * How frequency to execute this instruction? Weekly? Monthly?
	 */
	private String frequency;

	/*
	 * Amount
	 * How much to in/out to/from this account
	 */
	private BigDecimal amount;
	
	/*
	 * Currency
	 * Currency of amount
	 */
	private String currency;
	
	/*
	 * Narrative
	 * What is the purpose of this instruction
	 */
	private String narrative;
	
	/*
	 * Account ID
	 * This rule is belong to which account?
	 */
	private Long accountId;

	public Long getAutoInstructionId() {
		return autoInstructionId;
	}

	public void setAutoInstructionId(Long autoInstructionId) {
		this.autoInstructionId = autoInstructionId;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getNarrative() {
		return narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
}

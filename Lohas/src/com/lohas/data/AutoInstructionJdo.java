package com.lohas.data;

import java.math.BigDecimal;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class AutoInstructionJdo {

	/*
	 * Auto Instruction ID
	 * Primary key
	 */
	@Id
	@Index
	private Long autoInstructionId;
	
	/*
	 * Frequency
	 * How frequency to execute this instruction? Weekly? Monthly?
	 */
	@Index
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
	@Index
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

package com.lohas.api.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class NewCashTxnRequest extends CommonRequest {

	/*
	 * Account Code
	 */
	@NotNull
	@NotEmpty
	private String accountCode;
	
	/*
	 * Transaction Type
	 */
	@NotNull
	@NotEmpty
	private String transactionType;
	
	/*
	 * Transaction Date Time
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date transactionDateTime;
	
	/*
	 * Amount
	 */
	@NotNull
	private BigDecimal amount;
	
	/*
	 * Narrative
	 */
	private String narrative;

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(Date transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getNarrative() {
		return narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}
	
	
}

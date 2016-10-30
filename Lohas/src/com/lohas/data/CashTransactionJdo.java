package com.lohas.data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class CashTransactionJdo {

	/*
	 * Transaction ID
	 * Primary Key
	 */
	@Id
	@Index
	private Long transactionId;
	
	/*
	 * Transaction Code
	 * The external identifier of transaction
	 */
	@Index
	private String transactionCode;
	
	/*
	 * Transaction Type
	 * What kind of transaction is?
	 * Deposit? Withdraw? or Else?
	 */
	@Index
	private String transactionType;
	
	/*
	 * Transaction Date Time
	 * When did this transaction happen?
	 */
	@Index
	private Date transactionDateTime;
	
	/*
	 * Amount
	 * How much is it?
	 */
	private BigDecimal amount;
	
	/*
	 * Currency
	 * Suppose it must same as account currency
	 */
	private String currency;
	
	/*
	 * Narrative
	 * Tell me more information about this transaction
	 */
	private String narrative;
	
	/*
	 * Account ID
	 * This transaction is loaded to which account
	 */
	@Index
	private Long accountId;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
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

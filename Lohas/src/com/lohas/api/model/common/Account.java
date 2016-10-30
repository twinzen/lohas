package com.lohas.api.model.common;

import java.math.BigDecimal;
import java.util.Date;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class Account {

	/*
	 * Account ID
	 * Primary key
	 */
	private Long accountId;
	
	/*
	 * Account Code
	 * The external identifier of account
	 */
	private String accountCode;
	
	
	/*
	 * Account Name
	 * Give it a name for easier remember
	 */
	private String accountName;
	
	/*
	 * Account Currency
	 * Money in this account should be in this currency
	 */
	private String accountCurrency;
	
	
	/*
	 * Account Type
	 * What is the type of account?
	 * Cash, Investment, or else?
	 */
	private String accountType;
	
	/*
	 * Account Open Date
	 * When does this account open?
	 */
	private Date accountOpenDate;
	
	/*
	 * Account Terminate Date
	 * When does this account terminated?
	 */
	private Date accountTerminateDate;
	
	/*
	 * Account Status
	 * Status? Open, Close, Suspended?
	 */
	private String accountStatus;
	
	/*
	 * Interest Rate
	 */
	private BigDecimal interestRate;
	
	/*
	 * Account balance
	 */
	private BigDecimal accountBalanceAmount;
	
	/*
	 * Account gain/loss
	 */
	private BigDecimal accountGainLossAmount;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountCurrency() {
		return accountCurrency;
	}

	public void setAccountCurrency(String accountCurrency) {
		this.accountCurrency = accountCurrency;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Date getAccountOpenDate() {
		return accountOpenDate;
	}

	public void setAccountOpenDate(Date accountOpenDate) {
		this.accountOpenDate = accountOpenDate;
	}

	public Date getAccountTerminateDate() {
		return accountTerminateDate;
	}

	public void setAccountTerminateDate(Date accountTerminateDate) {
		this.accountTerminateDate = accountTerminateDate;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public BigDecimal getAccountBalanceAmount() {
		return accountBalanceAmount;
	}

	public void setAccountBalanceAmount(BigDecimal accountBalanceAmount) {
		this.accountBalanceAmount = accountBalanceAmount;
	}

	public BigDecimal getAccountGainLossAmount() {
		return accountGainLossAmount;
	}

	public void setAccountGainLossAmount(BigDecimal accountGainLossAmount) {
		this.accountGainLossAmount = accountGainLossAmount;
	}
	
	
}

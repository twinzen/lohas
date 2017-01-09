package com.lohas.api.model;

import java.util.List;

public class GetTransactionsRequest extends CommonRequest {

	/*
	 * Account Id
	 * Make it simple, this api only support one account code
	 */
	private String accountCode;

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
}

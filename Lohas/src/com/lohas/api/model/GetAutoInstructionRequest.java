package com.lohas.api.model;

import javax.validation.constraints.NotNull;

public class GetAutoInstructionRequest extends CommonRequest {

	/*
	 * Account Id
	 */
	@NotNull
	private Long accountId;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	
}

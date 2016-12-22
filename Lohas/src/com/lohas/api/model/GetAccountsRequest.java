package com.lohas.api.model;

public class GetAccountsRequest extends CommonRequest {

	/*
	 * Customer Id
	 */
	private Long customerId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
}

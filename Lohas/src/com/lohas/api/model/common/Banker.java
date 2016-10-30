package com.lohas.api.model.common;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class Banker {

	/*
	 * Banker ID
	 */
	private Long bankerId;
	
	/*
	 * Banker Name
	 */
	private String bankerName;
	
	/*
	 * Appellation
	 */
	private String appellation;
	
	/*
	 * Primary Email
	 */
	private String primaryEmail;

	public Long getBankerId() {
		return bankerId;
	}

	public void setBankerId(Long bankerId) {
		this.bankerId = bankerId;
	}

	public String getBankerName() {
		return bankerName;
	}

	public void setBankerName(String bankerName) {
		this.bankerName = bankerName;
	}

	public String getAppellation() {
		return appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	
}

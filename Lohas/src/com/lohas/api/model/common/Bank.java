package com.lohas.api.model.common;

import java.util.List;


public class Bank {
	
	/*
	 * Bank ID
	 */
	private Long bankId; //Bank ID
	
	/*
	 * Bank Name
	 */
	private String bankName; // Bank Name
	
	/*
	 * Base Currency
	 */
	private String baseCurrency; // Base Currency
	
	/*
	 * Banker Id Admin List
	 */
	private List<Banker> bankerAdminList;

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public List<Banker> getBankerAdminList() {
		return bankerAdminList;
	}

	public void setBankerAdminList(List<Banker> bankerAdminList) {
		this.bankerAdminList = bankerAdminList;
	}

	
	
}

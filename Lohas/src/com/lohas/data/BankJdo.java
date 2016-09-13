package com.lohas.data;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class BankJdo {

	/*
	 * Bank ID
	 * Primary key
	 */
	@Id
	@Index
	private Long bankId; //Bank ID
	
	/*
	 * Bank Name
	 * What is the name?
	 */
	private String bankName; // Bank Name
	
	/*
	 * Base Currency
	 */
	private String baseCurrency; // Base Currency
	
	/*
	 * Banker Admin List
	 * Who have admin right to manage this bank?
	 * List of banker ID
	 */
	private List<Long> bankerAdminList;

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

	public List<Long> getBankerAdminList() {
		return bankerAdminList;
	}

	public void setBankerAdminList(List<Long> bankerAdminList) {
		this.bankerAdminList = bankerAdminList;
	} 

	
	
}

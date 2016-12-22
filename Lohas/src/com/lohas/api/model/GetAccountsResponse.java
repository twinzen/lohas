package com.lohas.api.model;

import java.util.List;

import com.lohas.api.model.common.Account;
import com.lohas.api.model.common.Bank;
import com.lohas.api.model.common.Banker;
import com.lohas.api.model.common.Customer;

public class GetAccountsResponse extends CommonResponse {

	/*
	 * All accounts of given customer
	 */
	private List<Account> accounts;

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	
	
}

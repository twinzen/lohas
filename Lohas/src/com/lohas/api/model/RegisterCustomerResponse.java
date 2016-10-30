package com.lohas.api.model;

import com.lohas.api.model.common.Account;
import com.lohas.api.model.common.Bank;
import com.lohas.api.model.common.Banker;
import com.lohas.api.model.common.Customer;

public class RegisterCustomerResponse extends CommonResponse {

	/*
	 * The information of new registered customer
	 */
	private Customer customer;
	
	/*
	 * The information of first account
	 */
	private Account account;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
	
}

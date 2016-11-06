package com.lohas.api.model;

import java.util.List;

import com.lohas.api.model.common.Account;
import com.lohas.api.model.common.Bank;
import com.lohas.api.model.common.Banker;
import com.lohas.api.model.common.Customer;

public class GetCustomersResponse extends CommonResponse {

	/*
	 * All customer of my bank
	 */
	private List<Customer> customers;

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	
}

package com.lohas.api.model;

import com.lohas.api.model.common.Account;
import com.lohas.api.model.common.Bank;
import com.lohas.api.model.common.Banker;
import com.lohas.api.model.common.Customer;

public class UpdateCustomerProfileResponse extends CommonResponse {

	/*
	 * The information of new registered customer
	 */
	private Customer customer;
	
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}

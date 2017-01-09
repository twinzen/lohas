package com.lohas.api.model;

import java.util.List;

import com.lohas.api.model.common.Account;
import com.lohas.api.model.common.Bank;
import com.lohas.api.model.common.Banker;
import com.lohas.api.model.common.CashTransaction;
import com.lohas.api.model.common.Customer;

public class GetTransactionsResponse extends CommonResponse {

	/*
	 * All transactions of account
	 */
	private List<CashTransaction> cashTransactions;

	public List<CashTransaction> getCashTransactions() {
		return cashTransactions;
	}

	public void setCashTransactions(List<CashTransaction> cashTransactions) {
		this.cashTransactions = cashTransactions;
	}

	
}

package com.lohas.api.model;

import com.lohas.api.model.common.Bank;
import com.lohas.api.model.common.Banker;
import com.lohas.api.model.common.CashTransaction;

public class NewCashTxnResponse extends CommonResponse {

	/*
	 * The new cash transaction
	 */
	private CashTransaction cashTransaction;

	public CashTransaction getCashTransaction() {
		return cashTransaction;
	}

	public void setCashTransaction(CashTransaction cashTransaction) {
		this.cashTransaction = cashTransaction;
	}
	
	
	
	
}

package com.lohas.api.model;

import com.lohas.api.model.common.Bank;
import com.lohas.api.model.common.Banker;

public class OpenBankResponse extends CommonResponse {

	/*
	 * The information of new opened bank
	 */
	private Bank bank;
	
	/*
	 * The information of first banker of this new bank
	 */
	private Banker banker;

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Banker getBanker() {
		return banker;
	}

	public void setBanker(Banker banker) {
		this.banker = banker;
	}
	
	
}

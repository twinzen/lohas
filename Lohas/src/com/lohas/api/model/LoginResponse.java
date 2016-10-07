package com.lohas.api.model;

public class LoginResponse extends CommonResponse {


	/*
	 * The information of banker's bank
	 */
	private Bank bank;
	
	/*
	 * The information of the banker
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

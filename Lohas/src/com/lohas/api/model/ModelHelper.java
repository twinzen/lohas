package com.lohas.api.model;

import java.util.ArrayList;
import java.util.List;

import com.lohas.data.BankJdo;
import com.lohas.data.BankerJdo;

public class ModelHelper {

	/**
	 * Convert BankerJdo to Banker model
	 * Most of attributes should be converted
	 * 
	 * @param bankerJdo
	 * @return
	 */
	public static Banker convertBankerJdoToBanker (BankerJdo bankerJdo) {
		Banker banker = new Banker();
		banker.setBankerId(bankerJdo.getBankerId());
		banker.setPrimaryEmail(bankerJdo.getPrimaryEmail());
		banker.setBankerName(bankerJdo.getBankerName());
		banker.setAppellation(bankerJdo.getAppellation());
		return banker;
	}

	/**
	 * Convert BankJdo to Bank model
	 * Most of attributes should be converted
	 * Banker amdin list need to be provided, because helper won't touch DAO
	 * 
	 * @param bankJdo
	 * @param bankerJdoAdminList
	 * @return
	 */
	public static Bank convertBankJdoToBank (BankJdo bankJdo, List<BankerJdo> bankerJdoAdminList) {
		Bank bank = new Bank();
		bank.setBankId(bankJdo.getBankId());
		bank.setBankName(bankJdo.getBankName());
		bank.setBaseCurrency(bankJdo.getBaseCurrency());
		List<Banker> bankerAdminList = new ArrayList<Banker>();
		bank.setBankerAdminList(bankerAdminList);
		for (BankerJdo bankerJdoAdmin: bankerJdoAdminList) {
			bankerAdminList.add(convertBankerJdoToBanker(bankerJdoAdmin));
		}
		return bank;
	}
}

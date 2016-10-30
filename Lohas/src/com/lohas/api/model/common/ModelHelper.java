package com.lohas.api.model.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.lohas.data.AccountJdo;
import com.lohas.data.BankJdo;
import com.lohas.data.BankerJdo;
import com.lohas.data.BlobImageJdo;
import com.lohas.data.CashTransactionJdo;
import com.lohas.data.CustomerJdo;

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
	
	/**
	 * Convert AccountJdo to Account model
	 * Most of attributes should be converted
	 * Transactions of account is required for calculate account balance and gain/loss
	 * 
	 * @param accountJdo
	 * @param transactionJdos
	 * @return
	 */
	public static Account convertAccountJdoToAccount (AccountJdo accountJdo, List<CashTransactionJdo> transactionJdos) {
		Account account = new Account();
		account.setAccountId(accountJdo.getAccountId());
		account.setAccountCode(accountJdo.getAccountCode());
		account.setAccountCurrency(accountJdo.getAccountCurrency());
		account.setAccountName(accountJdo.getAccountName());
		account.setAccountType(accountJdo.getAccountType());
		account.setAccountOpenDate(accountJdo.getAccountOpenDate());
		account.setAccountTerminateDate(accountJdo.getAccountTerminateDate());
		account.setAccountStatus(accountJdo.getAccountStatus());
		account.setInterestRate(accountJdo.getInterestRate());
		BigDecimal accountBalanceAmount = new BigDecimal(0);
		BigDecimal accountGainLossAmount = new BigDecimal(0);
		for (CashTransactionJdo transactionJdo : transactionJdos) {
			// Add all txn amount = account balance
			accountBalanceAmount = accountBalanceAmount.add(transactionJdo.getAmount()); 
			if (transactionJdo.getTransactionType().equals("INTEREST")) {
				// Add all interest = gain/loss
				accountGainLossAmount = accountGainLossAmount.add(transactionJdo.getAmount()); 
			}
		}
		account.setAccountBalanceAmount(accountBalanceAmount);
		account.setAccountGainLossAmount(accountGainLossAmount);
		return account;
	}
	
	/**
	 * Convert AccountJdo to Account model
	 * Most of attributes should be converted
	 * Account list is required for calculating total asset amount AND total gain/loss
	 * 
	 * @param customerJdo
	 * @param accounts
	 * @return
	 */
	public static Customer convertCustomerJdoToCustomer (CustomerJdo customerJdo, List<Account> accounts) {
		Customer customer = new Customer();
		customer.setCustomerId(customerJdo.getCustomerId());
		customer.setCustomerName(customerJdo.getCustomerName());
		customer.setDateOfBirth(customerJdo.getDateOfBirth());
		customer.setGender(customerJdo.getGender());
		customer.setEmail(customerJdo.getEmail());
		customer.setProfilePicUrl(customerJdo.getProfilePic().getImageUrl());
		customer.setUsername(customerJdo.getUsername());
		BigDecimal totalBalanceAmount = new BigDecimal(0);
		BigDecimal totalGainLossAmount = new BigDecimal(0);
		for (Account account : accounts) {
			// Add all account amount = total assest value
			totalBalanceAmount = totalBalanceAmount.add(account.getAccountBalanceAmount()); 
			// Add all account gain/lost = total gain/loss
			totalGainLossAmount = totalGainLossAmount.add(account.getAccountGainLossAmount()); 
		}
		
		return customer;
	}

}

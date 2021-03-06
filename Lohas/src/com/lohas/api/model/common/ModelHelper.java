package com.lohas.api.model.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.lohas.data.AccountJdo;
import com.lohas.data.AutoInstructionJdo;
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
		if (bankerJdo == null) {
			return null;
		}
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
		if (bankJdo == null) {
			return null;
		}
		Bank bank = new Bank();
		bank.setBankId(bankJdo.getBankId());
		bank.setBankName(bankJdo.getBankName());
		bank.setBaseCurrency(bankJdo.getBaseCurrency());
		List<Banker> bankerAdminList = new ArrayList<Banker>();
		bank.setBankerAdminList(bankerAdminList);
		if (bankerJdoAdminList != null) {
			for (BankerJdo bankerJdoAdmin: bankerJdoAdminList) {
				bankerAdminList.add(convertBankerJdoToBanker(bankerJdoAdmin));
			}
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
		if (accountJdo == null) {
			return null;
		}
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
		if (transactionJdos != null) {
			for (CashTransactionJdo transactionJdo : transactionJdos) {
				// Sum up all txn amount = account balance
				if ("DEPOSIT".equals(transactionJdo.getTransactionType() )
						||"INITIAL".equals(transactionJdo.getTransactionType())) {
					accountBalanceAmount = accountBalanceAmount.add(transactionJdo.getAmount());
				} else {
					accountBalanceAmount = accountBalanceAmount.subtract(transactionJdo.getAmount());
				}
				if (transactionJdo.getTransactionType().equals("INTEREST")) {
					// Add all interest = gain/loss
					accountGainLossAmount = accountGainLossAmount.add(transactionJdo.getAmount()); 
				}
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
		if (customerJdo == null) {
			return null;
		}
		Customer customer = new Customer();
		customer.setCustomerId(customerJdo.getCustomerId());
		customer.setCustomerName(customerJdo.getCustomerName());
		customer.setDateOfBirth(customerJdo.getDateOfBirth());
		customer.setGender(customerJdo.getGender());
		customer.setEmail(customerJdo.getEmail());
		customer.setProfilePicUrl(customerJdo.getProfilePic().getImageUrl());
		customer.setUsername(customerJdo.getUsername());
		BigDecimal totalAssetValueAmount = new BigDecimal(0);
		BigDecimal totalGainLossAmount = new BigDecimal(0);
		if (accounts != null) {
			for (Account account : accounts) {
				// Add all account amount = total assest value
				totalAssetValueAmount = totalAssetValueAmount.add(account.getAccountBalanceAmount()); 
				// Add all account gain/lost = total gain/loss
				totalGainLossAmount = totalGainLossAmount.add(account.getAccountGainLossAmount()); 
			}
		}
		customer.setTotalAssetValueAmount(totalAssetValueAmount);
		customer.setTotalGainLossAmount(totalGainLossAmount);
		return customer;
	}
	
	/**
	 * Convert CashTransactionJdo to CashTransaction model
	 * Most of attributes should be converted
	 * 
	 * @param cashTransactionJdo
	 * @return
	 */
	public static CashTransaction convertCashTransactionJdoToCashTransaction (CashTransactionJdo cashTransactionJdo) {
		if (cashTransactionJdo == null) {
			return null;
		}
		CashTransaction cashTransaction = new CashTransaction();
		cashTransaction.setTransactionId(cashTransactionJdo.getTransactionId());
		cashTransaction.setTransactionCode(cashTransactionJdo.getTransactionCode());
		cashTransaction.setTransactionDateTime(cashTransactionJdo.getTransactionDateTime());
		cashTransaction.setTransactionType(cashTransactionJdo.getTransactionType());
		cashTransaction.setAmount(cashTransactionJdo.getAmount());
		cashTransaction.setCurrency(cashTransactionJdo.getCurrency());
		cashTransaction.setNarrative(cashTransactionJdo.getNarrative());
		cashTransaction.setAccountId(cashTransactionJdo.getAccountId());
		return cashTransaction;
	}
	
	/**
	 * Convert AutoInstructionJdo to AutoInstruction model
	 * Most of attributes should be converted
	 * 
	 * @param autoInstructionJdo
	 * @return
	 */
	public static AutoInstruction convertAutoInstructionJdoToAutoInstruction (AutoInstructionJdo autoInstructionJdo) {
		if (autoInstructionJdo == null) {
			return null;
		}
		AutoInstruction autoInstruction = new AutoInstruction();
		autoInstruction.setAccountId(autoInstructionJdo.getAccountId());
		autoInstruction.setAutoInstructionId(autoInstructionJdo.getAutoInstructionId());
		autoInstruction.setAmount(autoInstructionJdo.getAmount());
		autoInstruction.setCurrency(autoInstructionJdo.getCurrency());
		autoInstruction.setFrequency(autoInstructionJdo.getFrequency());
		autoInstruction.setNarrative(autoInstructionJdo.getNarrative());
		return autoInstruction;
	}

}

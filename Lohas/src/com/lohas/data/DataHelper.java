package com.lohas.data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class DataHelper {

	/**
	 * Create new CustomerJdo
	 * 
	 * @param customerName
	 * @param gender
	 * @param dateOfBirth
	 * @param profilePicBlobKey
	 * @param profilePicUrl
	 * @param username
	 * @param email
	 * @param password
	 * @param bankId
	 * @return
	 */
	public static CustomerJdo createCustomerJdo (
				String customerName,
				String gender,
				Date dateOfBirth,
				String profilePicBlobKey,
				String profilePicUrl,
				String username,
				String email,
				String password,
				Long bankId) {
		CustomerJdo customerJdo = new CustomerJdo();
		customerJdo.setBankId(bankId);
		customerJdo.setCustomerName(customerName);
		customerJdo.setDateOfBirth(dateOfBirth);
		customerJdo.setGender(gender);
		customerJdo.setUsername(username);
		customerJdo.setPassword(password);
		customerJdo.setEmail(email);
		BlobImageJdo profilePic = new BlobImageJdo();
		profilePic.setBlobKey(profilePicBlobKey);
		profilePic.setBlobKey(profilePicUrl);
		customerJdo.setProfilePic(profilePic);
		return customerJdo;
	}
	
	/**
	 * Create new AccountJdo
	 * 
	 * @param accountCode
	 * @param accountType
	 * @param accountCurrency
	 * @param accountName
	 * @param accountOpenDate
	 * @param accountStatus
	 * @param customerId
	 * @return
	 */
	public static AccountJdo createAccountJdo (
			String accountCode,
			String accountType,
			String accountCurrency,
			String accountName,
			Date accountOpenDate,
			String accountStatus,
			BigDecimal interestRate,
			Long customerId,
			Long bankId) {
		AccountJdo accountJdo = new AccountJdo();
		accountJdo.setCustomerId(customerId);
		accountJdo.setBankId(bankId);
		accountJdo.setAccountCode(accountCode);
		accountJdo.setAccountType(accountType);
		accountJdo.setAccountCurrency(accountCurrency);
		accountJdo.setAccountName(accountName);
		accountJdo.setAccountOpenDate((accountOpenDate == null) ? new Date():accountOpenDate);
		accountJdo.setAccountStatus((accountStatus == null) ? "OPEN":accountStatus);
		accountJdo.setInterestRate(interestRate);
		return accountJdo;
	}
	
	/**
	 * Create new CashTransactionJdo
	 * 
	 * @param transactionCode
	 * @param transactionType
	 * @param transactionDateTime
	 * @param amount
	 * @param currency
	 * @param narrative
	 * @param accountId
	 * @return
	 */
	public static CashTransactionJdo createCashTransactionJdo (
			String transactionCode,
			String transactionType,
			Date transactionDateTime,
			BigDecimal amount,
			String currency,
			String narrative,
			Long accountId
			) {
		CashTransactionJdo cashTransactionJdo = new CashTransactionJdo();
		cashTransactionJdo.setTransactionCode(transactionCode);
		cashTransactionJdo.setTransactionType(transactionType);
		cashTransactionJdo.setTransactionDateTime(transactionDateTime);
		cashTransactionJdo.setAmount(amount);
		cashTransactionJdo.setCurrency(currency);
		cashTransactionJdo.setNarrative(narrative);
		cashTransactionJdo.setAccountId(accountId);
		return cashTransactionJdo;
	}
	
	/**
	 * Create new AutoInstructionJdo
	 * 
	 * @param frequency
	 * @param executionDay
	 * @param amount
	 * @param currency
	 * @param narrative
	 * @param accountId
	 * @return
	 */
	public static AutoInstructionJdo createAutoInstructionJdo (
			String frequency,
			BigDecimal amount,
			String currency,
			String narrative,
			Long accountId
			) {
		AutoInstructionJdo autoInstructionJdo = new AutoInstructionJdo();
		autoInstructionJdo.setFrequency(frequency);
		autoInstructionJdo.setAmount(amount);
		autoInstructionJdo.setCurrency(currency);
		autoInstructionJdo.setNarrative(narrative);
		autoInstructionJdo.setAccountId(accountId);
		return autoInstructionJdo;
	}

	
}

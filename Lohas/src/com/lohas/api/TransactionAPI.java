package com.lohas.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.lohas.api.annotation.RequireLoggedIn;
import com.lohas.api.constant.ErrorCode;
import com.lohas.api.exception.ApplicationException;
import com.lohas.api.model.GetAccountsRequest;
import com.lohas.api.model.GetAccountsResponse;
import com.lohas.api.model.GetCustomersRequest;
import com.lohas.api.model.GetCustomersResponse;
import com.lohas.api.model.GetTransactionsRequest;
import com.lohas.api.model.GetTransactionsResponse;
import com.lohas.api.model.LoginRequest;
import com.lohas.api.model.LoginResponse;
import com.lohas.api.model.NewCashTxnRequest;
import com.lohas.api.model.NewCashTxnResponse;
import com.lohas.api.model.RegisterCustomerRequest;
import com.lohas.api.model.RegisterCustomerResponse;
import com.lohas.api.model.common.Account;
import com.lohas.api.model.common.Bank;
import com.lohas.api.model.common.Banker;
import com.lohas.api.model.common.CashTransaction;
import com.lohas.api.model.common.Customer;
import com.lohas.api.model.common.ModelHelper;
import com.lohas.dao.AccountDao;
import com.lohas.dao.AutoInstructionDao;
import com.lohas.dao.BankDao;
import com.lohas.dao.BankerDao;
import com.lohas.dao.CashTransactionDao;
import com.lohas.dao.CustomerDao;
import com.lohas.dao.SessionDao;
import com.lohas.data.AccountJdo;
import com.lohas.data.AutoInstructionJdo;
import com.lohas.data.BankJdo;
import com.lohas.data.BankerJdo;
import com.lohas.data.BlobImageJdo;
import com.lohas.data.CashTransactionJdo;
import com.lohas.data.CustomerJdo;
import com.lohas.data.DataHelper;
import com.lohas.data.SessionJdo;


@Controller
@RequestMapping("/api")
public class TransactionAPI extends CommonAPI {

	static Logger log = Logger.getLogger(TransactionAPI.class.getName());
	MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
	
	@Autowired
	Validator validator;
	
	@Autowired
	BankDao bankDao;
	
	@Autowired
	BankerDao bankerDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	CashTransactionDao cashTransactionDao;

	@Autowired
	AutoInstructionDao autoInstructionDao;
	
	/**
	 * This API is for cash deposit/withdraw
	 * Below information will be returned
	 * New transaction information 
	 * 
	 * @param reqt
	 * @return
	 * @throws ApplicationException
	 */
	@RequireLoggedIn
	@RequestMapping(value = "/newCashTxn", method = RequestMethod.GET)
	public @ResponseBody NewCashTxnResponse newCashTxn (@Valid NewCashTxnRequest reqt) throws ApplicationException {
		
		log.info("API newCashTxn start.");
		
		/*
		 * Retrieve banker itself
		 */
		BankerJdo bankerJdo = bankerDao.retrieveBankerJdo(reqt.getUserId());
		if (bankerJdo == null){ 
			// Check whether could get bankerJdo by given user id.
			//If couldn't, throw exception and say good bye
			log.warning("banker cannot be found with given user id.");
			throw new ApplicationException(ErrorCode.ERROR, "Internal Error");
		}
		log.info("Retrieve bankerJdo completed. bankerId:["+bankerJdo.getBankerId()+"]");
		
		/*
		 * Retrieve account by given input
		 */
		AccountJdo accountJdo = accountDao.retrieveAccountJdoByAccountCodeBankId(reqt.getAccountCode(), bankerJdo.getBankId());
		if (accountJdo == null){ 
			// Check whether could get accountJdo by given given input
			//If couldn't, throw exception and say good bye
			log.warning("account cannot be found with given account id and bank id.");
			throw new ApplicationException(ErrorCode.ACCOUNT_NOT_FOUND, "Account Not Found");
		}
		log.info("Retrieve accountJdo completed. accountId:["+accountJdo.getAccountId()+"]");

		/*
		 * Create one transaction
		 */
		String transactionCodePrefix = (reqt.getTransactionType().equals("deposit"))?"D":"W";
		CashTransactionJdo cashTransactionJdo = DataHelper.createCashTransactionJdo(
				transactionCodePrefix+System.currentTimeMillis(), // "I" for initial
				reqt.getTransactionType(), // txn type
				(reqt.getTransactionDateTime()!=null)?reqt.getTransactionDateTime():new Date(), // txn date time
				reqt.getAmount(), // txn amount
				accountJdo.getAccountCurrency(), // txn currency must same as account currency for cash account
				reqt.getNarrative(), // Narrative
				accountJdo.getAccountId() // account Id
				);
		cashTransactionDao.persistCashTransactionJdo(cashTransactionJdo);
		log.info("Create and persist cashTransactionJdo completed. transactionId: ["+cashTransactionJdo.getTransactionId()+"]");
		
		
		/*
		 * Prepare the response
		 */
		NewCashTxnResponse resp = new NewCashTxnResponse();
		CashTransaction cashTransaction = ModelHelper.convertCashTransactionJdoToCashTransaction(cashTransactionJdo);
		resp.setCashTransaction(cashTransaction);
		log.info("API newCashTxn end.");
		return resp;
		
	}
	
	@RequireLoggedIn
	@RequestMapping(value = "/getTransactions", method = RequestMethod.GET)
	public @ResponseBody GetTransactionsResponse getTransactions (@Valid GetTransactionsRequest reqt) throws ApplicationException {
		
		log.info("API getTransactions start.");
		
		/*
		 * Retrieve banker itself
		 */
		BankerJdo bankerJdo = bankerDao.retrieveBankerJdo(reqt.getUserId());
		if (bankerJdo == null){ 
			// Check whether could get bankerJdo by given user id.
			//If couldn't, throw exception and say good bye
			log.warning("banker cannot be found with given user id.");
			throw new ApplicationException(ErrorCode.ERROR, "Internal Error");
		}
		log.info("Retrieve bankerJdo completed. bankerId:["+bankerJdo.getBankerId()+"]");
		
		/*
		 * Retrieve account by given input
		 */
		AccountJdo accountJdo = accountDao.retrieveAccountJdoByAccountCodeBankId(reqt.getAccountCode(), bankerJdo.getBankId());
		if (accountJdo == null){ 
			// Check whether could get accountJdo by given given input
			//If couldn't, throw exception and say good bye
			log.warning("account cannot be found with given account id and bank id.");
			throw new ApplicationException(ErrorCode.ACCOUNT_NOT_FOUND, "Account Not Found");
		}
		log.info("Retrieve accountJdo completed. accountId:["+accountJdo.getAccountId()+"]");
		
		/*
		 * Retrieve transactions which belong to the account
		 */
		List<CashTransactionJdo> transactionJdos = cashTransactionDao.retrieveCashTransactionJdos(accountJdo.getAccountId());
		log.info("Retrieve cachTransactionJdo completed. No. of transaction:["+transactionJdos.size()+"]");
		
		
		/*
		 * Prepare for response
		 */
		List<CashTransaction> cashTransactions = new  ArrayList<CashTransaction>();
		for (CashTransactionJdo cashTransactionJdo:transactionJdos) {
			cashTransactions.add(ModelHelper.convertCashTransactionJdoToCashTransaction(cashTransactionJdo));
		}
		
		// Do a simple sorting, sort by date time
		Collections.sort(cashTransactions,
		        new Comparator<CashTransaction>() {
					public int compare(CashTransaction txn1, CashTransaction txn2) {
		                return txn1.getTransactionDateTime().compareTo(txn2.getTransactionDateTime())*-1; //Descending order
		            }
		        });
		
		GetTransactionsResponse resp = new GetTransactionsResponse();
		resp.setCashTransactions(cashTransactions);
		log.info("API getTransactions end.");
		return resp;
	}

}

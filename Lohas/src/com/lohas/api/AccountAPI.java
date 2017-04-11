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
import com.lohas.api.model.GetAutoInstructionRequest;
import com.lohas.api.model.GetAutoInstructionResponse;
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
import com.lohas.api.model.SetupAutoInstructionResponse;
import com.lohas.api.model.SetupAutoIntructionRequest;
import com.lohas.api.model.UpdateAutoInstructionResponse;
import com.lohas.api.model.UpdateAutoIntructionRequest;
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
public class AccountAPI extends CommonAPI {

	static Logger log = Logger.getLogger(AccountAPI.class.getName());
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

	@RequireLoggedIn
	@RequestMapping(value = "/getAccounts", method = RequestMethod.GET)
	public @ResponseBody GetAccountsResponse getAccounts (@Valid GetAccountsRequest reqt) throws ApplicationException {
		
		log.info("API getAccounts start.");
		
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
		 * Retrieve customer
		 */
		CustomerJdo customerJdo = customerDao.retrieveCustomerJdo(reqt.getCustomerId());
		if (customerJdo != null) {
			// Check if user had privilege to access this customer
			if (!bankerJdo.getBankId().equals(customerJdo.getBankId())) {
				// Bank ID is different
				log.warning("banker has no privilege to access this customer");
				throw new ApplicationException(ErrorCode.NO_PRIVILEGE, "No privilege.");
			}
		}
		
		
		/*
		 * Retrieve accounts which belong to the customer
		 */
		List<AccountJdo> accountJdos = accountDao.retrieveAccountJdos(reqt.getCustomerId(), bankerJdo.getBankId());
		log.info("Retrieve accountJdos completed. No. of account:["+accountJdos.size()+"]");
		
		
		/*
		 * Start calculating accounts' balance
		 */
		List<Account> accounts = new ArrayList<Account>();
		for (AccountJdo accountJdo: accountJdos) {
			List<CashTransactionJdo> transactionJdos = cashTransactionDao.retrieveCashTransactionJdos(accountJdo.getAccountId());
			log.info("Retrieve accountJdos completed. customerId/accountId: ["+accountJdo.getAccountId()+"]. No. of txn:["+transactionJdos.size()+"]"); 
			accounts.add(ModelHelper.convertAccountJdoToAccount(accountJdo, transactionJdos));
		}
		
		GetAccountsResponse resp = new GetAccountsResponse();
		resp.setAccounts(accounts);
		log.info("API getAccounts end.");
		return resp;
	}

	
	@RequireLoggedIn
	@RequestMapping(value = "/getAutoInstruction", method = RequestMethod.GET)
	public @ResponseBody GetAutoInstructionResponse getAutoInstruction (@Valid GetAutoInstructionRequest reqt) throws ApplicationException {
		
		log.info("API getAutoInstruction start.");
		
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
		 * Retrieve account
		 */
		AccountJdo accountJdo = accountDao.retrieveAccountJdo(reqt.getAccountId());
		if (accountJdo != null) {
			// Check if user had privilege to access this customer
			if (!bankerJdo.getBankId().equals(accountJdo.getBankId())) {
				// Bank ID is different
				log.warning("banker has no privilege to access this account");
				throw new ApplicationException(ErrorCode.NO_PRIVILEGE, "No privilege.");
			}
		} else {
			log.warning("account not found!");
			throw new ApplicationException(ErrorCode.ERROR, "Internal Error.");
		}
		
		
		/*
		 * Retrieve autoInstruction which belong to the account
		 */
		List<AutoInstructionJdo> autoInstructionJdos = autoInstructionDao.retrieveAutoInstructionJdos(reqt.getAccountId());
		if (autoInstructionJdos != null) {
			log.info("Retrieve autoInstructionJdo completed. No. of autoInstruction:["+autoInstructionJdos.size()+"]");
		}
		/**
		 * TODO assume one autoInstruction per account
		 */
		AutoInstructionJdo autoInstructionJdo = (autoInstructionJdos!=null)?autoInstructionJdos.get(0):null;
		
		GetAutoInstructionResponse resp = new GetAutoInstructionResponse();
		resp.setAutoInstrution(ModelHelper.convertAutoInstructionJdoToAutoInstruction(autoInstructionJdo));
		log.info("API getAutoInstruction end.");
		return resp;
	}
	
	@RequireLoggedIn
	@RequestMapping(value = "/updateAutoInstruction", method = RequestMethod.GET)
	public @ResponseBody UpdateAutoInstructionResponse updateAutoInstruction (@Valid UpdateAutoIntructionRequest reqt) throws ApplicationException {
		
		log.info("API updateAutoInstruction start.");
		
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
		 * Retrieve autoInstructionJdo
		 */
		AutoInstructionJdo autoInstructionJdo = autoInstructionDao.retrieveAutoInstructionJdo(reqt.getAutoInstructionId());
		if (autoInstructionJdo != null) {
			// Check if user had privilege to access this customer
			// Firstly, get the corresponding account first
			AccountJdo accountJdo = accountDao.retrieveAccountJdo(autoInstructionJdo.getAccountId());
			if (accountJdo != null) {
				// Check if user had privilege to access this customer
				if (!bankerJdo.getBankId().equals(accountJdo.getBankId())) {
					// Bank ID is different
					log.warning("banker has no privilege to access this account");
					throw new ApplicationException(ErrorCode.NO_PRIVILEGE, "No privilege.");
				}
			} else {
				log.warning("account not found!");
				throw new ApplicationException(ErrorCode.ERROR, "Internal Error.");
			}
		} else {
			log.warning("autoInstruction not found!");
			throw new ApplicationException(ErrorCode.ERROR, "Internal Error.");
		}
		
		
		/*
		 * Update the autoInstructionJdo and then persist
		 */
		autoInstructionJdo.setAmount(reqt.getAutoInstructionAmount());
		autoInstructionJdo.setFrequency(reqt.getAutoInstructionFrequency());
		autoInstructionDao.persistAutoInstructionJdo(autoInstructionJdo);
		
		UpdateAutoInstructionResponse resp = new UpdateAutoInstructionResponse();
		resp.setAutoInstrution(ModelHelper.convertAutoInstructionJdoToAutoInstruction(autoInstructionJdo));
		log.info("API updateAutoInstruction end.");
		return resp;
	}

	
	@RequireLoggedIn
	@RequestMapping(value = "/setupAutoInstruction", method = RequestMethod.GET)
	public @ResponseBody SetupAutoInstructionResponse setupAutoInstruction (@Valid SetupAutoIntructionRequest reqt) throws ApplicationException {
		
		log.info("API setupAutoInstruction start.");
		
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
		 * Retrieve account
		 */
		AccountJdo accountJdo = accountDao.retrieveAccountJdo(reqt.getAccountId());
		if (accountJdo != null) {
			// Check if user had privilege to access this customer
			if (!bankerJdo.getBankId().equals(accountJdo.getBankId())) {
				// Bank ID is different
				log.warning("banker has no privilege to access this account");
				throw new ApplicationException(ErrorCode.NO_PRIVILEGE, "No privilege.");
			}
		} else {
			log.warning("account not found!");
			throw new ApplicationException(ErrorCode.ERROR, "Internal Error.");
		}
		
		
		/*
		 * Create new autoInstructionJdo and then persist
		 */
		/*
		 * Create auto instruction
		 */
		AutoInstructionJdo autoInstructionJdo = DataHelper.createAutoInstructionJdo(
				reqt.getAutoInstructionFrequency(), // frequency
				reqt.getAutoInstructionAmount(), // amount
				accountJdo.getAccountCurrency(), // currency
				"Regular top-up", // narrative
				accountJdo.getAccountId() // account Id
				);
		autoInstructionDao.persistAutoInstructionJdo(autoInstructionJdo);
		log.info("Create and persist autoInstructionJdo completed. autoInstructionId: ["+autoInstructionJdo.getAutoInstructionId()+"]");
		
		SetupAutoInstructionResponse resp = new SetupAutoInstructionResponse();
		resp.setAutoInstrution(ModelHelper.convertAutoInstructionJdoToAutoInstruction(autoInstructionJdo));
		log.info("API setupAutoInstruction end.");
		return resp;
	}
	
	
}

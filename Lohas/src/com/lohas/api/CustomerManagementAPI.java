package com.lohas.api;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.lohas.api.model.GetCustomersRequest;
import com.lohas.api.model.GetCustomersResponse;
import com.lohas.api.model.LoginRequest;
import com.lohas.api.model.LoginResponse;
import com.lohas.api.model.RegisterCustomerRequest;
import com.lohas.api.model.RegisterCustomerResponse;
import com.lohas.api.model.common.Account;
import com.lohas.api.model.common.Bank;
import com.lohas.api.model.common.Banker;
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
public class CustomerManagementAPI extends CommonAPI {

	static Logger log = Logger.getLogger(BankAPI.class.getName());
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
	 * This API is for customer registration
	 * Below information will be returned
	 * Customer information 
	 * 
	 * @param reqt
	 * @return
	 * @throws ApplicationException
	 */
	@RequireLoggedIn
	@RequestMapping(value = "/registerCustomer", method = RequestMethod.GET)
	public @ResponseBody RegisterCustomerResponse registerCustomer (@Valid RegisterCustomerRequest reqt) throws ApplicationException {
		
		log.info("API registerCustomer start.");
		
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
		 * Retrieve banker's bank
		 */
		BankJdo bankJdo = bankDao.retrieveBankJdo(bankerJdo.getBankId());
		if (bankJdo == null){ 
			// Check whether could get bankJdo
			// If couldn't, throw exception and say good bye
			log.warning("bank cannot be found.");
			throw new ApplicationException(ErrorCode.ERROR, "Internal Error");
		}
		log.info("Retrieve bankJdo completed. bankId:["+bankJdo.getBankId()+"]");
		
		//TODO Check if the username has been taken in this bank
		
		
		/*
		 * Create customer
		 */
		CustomerJdo customerJdo = DataHelper.createCustomerJdo(
				reqt.getCustomerName(), 
				reqt.getGender(), 
				reqt.getDateOfBirth(), 
				reqt.getProfilePicBlobKey(), 
				reqt.getProfilePicUrl(), 
				reqt.getUsername().toLowerCase(), 
				null, // No Email provided 
				reqt.getPassword(), 
				bankJdo.getBankId());
		customerDao.persistCustomerJdo(customerJdo);
		log.info("Create and persist customerJdo completed. customerId: ["+customerJdo.getCustomerId()+"]");
		
		/*
		 * Create account
		 */
		AccountJdo accountJdo = DataHelper.createAccountJdo(
				UUID.randomUUID().toString(), // User a random string as account code
				"SAVING", // Account type
				bankJdo.getBaseCurrency(), // Base deposit account must follow bank's base currency 
				"Base currency saving", // Account name
				new Date(), // Open date
				"OPEN", // Status
				reqt.getInterestRate(), // Interest rate
				customerJdo.getCustomerId());
		accountDao.persistAccountJdo(accountJdo);
		log.info("Create and persist accountJdo completed. accountId: ["+accountJdo.getAccountId()+"]");
		
		/*
		 * Create first transaction
		 */
		CashTransactionJdo transactionJdo = DataHelper.createCashTransactionJdo(
				"I"+System.currentTimeMillis(), // "I" for initial
				"INITIAL", // txn type
				new Date(), // txn date time
				reqt.getInitialAmount(), // txn amount
				accountJdo.getAccountCurrency(), // txn currency must same as account currency for cash account
				"Initial amount", // Narrative
				accountJdo.getAccountId() // account Id
				);
		cashTransactionDao.persistCashTransactionJdo(transactionJdo);
		log.info("Create and persist cashTransactionJdo completed. transactionId: ["+transactionJdo.getTransactionId()+"]");
		
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
		
		
		/*
		 * Prepare the response
		 */
		final ArrayList transactionJdos = new ArrayList<CashTransactionJdo>(); 
		transactionJdos.add(transactionJdo); 
		final Account account = ModelHelper.convertAccountJdoToAccount(accountJdo, transactionJdos);
		final ArrayList accounts = new ArrayList<Account>(); 
		accounts.add(account); 
		final Customer customer = ModelHelper.convertCustomerJdoToCustomer(customerJdo, accounts);

		
		RegisterCustomerResponse resp = new RegisterCustomerResponse();
		resp.setCustomer(customer);
		resp.setAccount(account);
		log.info("API registerCustomer end.");
		return resp;
		
	}
	
	
	@RequireLoggedIn
	@RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
	public @ResponseBody GetCustomersResponse getCustomers (@Valid GetCustomersRequest reqt) throws ApplicationException {
		
		log.info("API getCustomers start.");
		
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
		 * Retrieve customers which belong to the bank
		 */
		List<CustomerJdo> customerJdos = customerDao.retrieveCustomerJdos(bankerJdo.getBankId());
		log.info("Retrieve customerJdos completed. No. of customer:["+customerJdos.size()+"]");
		
		
		/*
		 * Start calculating customer's portfolio
		 */
		List<Customer> customers = new ArrayList<Customer>(); // List of customer object to be returned in resp
		for (CustomerJdo customerJdo: customerJdos) {
			// Calculate each account balance first
			List<AccountJdo> accountJdos = accountDao.retrieveAccountJdos(customerJdo.getCustomerId());
			log.info("Retrieve accountJdos completed. customerId: ["+customerJdo.getCustomerId() +"]. No. of account:["+accountJdos.size()+"]"); 
			List<Account> accounts = new ArrayList<Account>();
			for (AccountJdo accountJdo: accountJdos) {
				List<CashTransactionJdo> transactionJdos = cashTransactionDao.retrieveCashTransactionJdos(accountJdo.getAccountId());
				log.info("Retrieve accountJdos completed. customerId/accountId: ["+customerJdo.getCustomerId()+"/"+accountJdo.getAccountId()+"]. No. of txn:["+transactionJdos.size()+"]"); 
				accounts.add(ModelHelper.convertAccountJdoToAccount(accountJdo, transactionJdos));
			}
			// Sum up all account balance= customer balance
			customers.add(ModelHelper.convertCustomerJdoToCustomer(customerJdo, accounts));
		}
		
		GetCustomersResponse resp = new GetCustomersResponse();
		resp.setCustomers(customers);
		log.info("API getCustomers end.");
		return resp;
	}

}

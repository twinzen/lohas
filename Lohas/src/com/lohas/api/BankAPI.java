package com.lohas.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.lohas.api.annotation.RequireLoggedIn;
import com.lohas.api.constant.ErrorCode;
import com.lohas.api.exception.ApplicationException;
import com.lohas.api.model.CheckSignUpEmailRequest;
import com.lohas.api.model.CheckSignUpEmailResponse;
import com.lohas.api.model.ErrorResponse;
import com.lohas.api.model.OpenBankRequest;
import com.lohas.api.model.OpenBankResponse;
import com.lohas.api.model.common.Bank;
import com.lohas.api.model.common.Banker;
import com.lohas.api.model.common.ModelHelper;
import com.lohas.dao.BankDao;
import com.lohas.dao.BankerDao;
import com.lohas.dao.SessionDao;
import com.lohas.data.BankJdo;
import com.lohas.data.BankerJdo;
import com.lohas.data.SessionJdo;


@Controller
@RequestMapping("/api")
public class BankAPI extends CommonAPI {

	static Logger log = Logger.getLogger(BankAPI.class.getName());
	MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
	
	@Autowired
	Validator validator;
	
	@Autowired
	SessionDao sessionDao;
	
	@Autowired
	BankDao bankDao;
	
	@Autowired
	BankerDao bankerDao;
	
	
	/**
	 * Check whether the Email was used by another banker already
	 * 
	 * @param reqt
	 * @return
	 */
	@RequestMapping(value = "/checkSignUpEamil", method = RequestMethod.GET)
	public @ResponseBody CheckSignUpEmailResponse checkSignUpEmail(@Valid CheckSignUpEmailRequest reqt) {
		
		System.out.println("/checkSignUpEamil");
		
		boolean isEmailAvailable = false;
		
		BankerJdo bankerJdo = bankerDao.retrieveBankerJdoByEmail(reqt.getEmail().toLowerCase()); // Email must be in lower case always!
		if (bankerJdo == null) {
			isEmailAvailable = true; // Someone used this email, not available!
		}
		
		CheckSignUpEmailResponse resp = new CheckSignUpEmailResponse();
		resp.setIsEmailAvailable(isEmailAvailable);
		return resp;
		
	}

	/** 
	 * Open a new bank
	 * Also register a banker for this bank at the same time
	 *  
	 * @param reqt
	 * @return
	 * @throws ApplicationException 
	 */
	@RequestMapping(value = "/openBank", method = RequestMethod.GET)
	public @ResponseBody OpenBankResponse openBank(@Valid OpenBankRequest reqt) throws ApplicationException {

		// Play safe, check the email was registered before or not.
		final BankerJdo bankerJdoCheker = bankerDao.retrieveBankerJdoByEmail(reqt.getEmail().toLowerCase()); // Email must be in lower case always!
		if (bankerJdoCheker != null) {
			throw new ApplicationException(ErrorCode.EMAIL_ALREADY_USED, "Email was already registered before."); // Good bye!
		}		
		
		/*
		 * Persist the incoming data to data store
		 */
		final BankJdo bankJdo = new BankJdo(); // Format bankJdo
		bankJdo.setBankName("Bank of "+reqt.getAdminName());
		bankJdo.setBaseCurrency(reqt.getBaseCurrency());
		bankDao.persistBankJdo(bankJdo); // Persist bankJdo first because bankerJdo need bankId, 
		// That's why bankJdo and bankerJdo are not in one transaction

		final BankerJdo bankerJdo = new BankerJdo(); // Format bankerJdo
		bankerJdo.setBankerName(reqt.getAdminName());
		bankerJdo.setAppellation(reqt.getAppellation());
		bankerJdo.setPassword(reqt.getPassword());
		bankerJdo.setPrimaryEmail(reqt.getEmail().toLowerCase()); // email must be in lower case always
		bankerJdo.setBankId(bankJdo.getBankId());
		bankerDao.persistBankerJdo(bankerJdo); // Persist bankerJdo
		
		bankJdo.setBankerAdminList(new ArrayList<Long>(){{add(bankerJdo.getBankerId());}}); // Put bankerId to bankerAdminList		
		bankDao.persistBankJdo(bankJdo); // Persist bankJdo again because bankerAdminList updated
		
		
		/*
		 * Start a session
		 */
		final SessionJdo sessionJdo = new SessionJdo();
		sessionJdo.setUserId(bankerJdo.getBankerId());
		sessionJdo.setUserType("BANKER");
		sessionJdo.setStartDateTime(new Date());
		sessionJdo.setToken(UUID.randomUUID().toString());
		sessionDao.persistSessionJdo(sessionJdo);
		
		
		/*
		 * Prepare the response
		 */
		OpenBankResponse resp = new OpenBankResponse();
		final Banker banker = ModelHelper.convertBankerJdoToBanker(bankerJdo);
		final Bank bank = ModelHelper.convertBankJdoToBank(bankJdo, new ArrayList<BankerJdo>(){{add(bankerJdo);}});
		resp.setBank(bank);
		resp.setBanker(banker);
		resp.setToken(sessionJdo.getToken());
		
		return resp;
		
	}
	
	
}

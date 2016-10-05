package com.lohas.api;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.lohas.api.annotation.RequireLoggedIn;
import com.lohas.api.constant.ErrorCode;
import com.lohas.api.exception.ApplicationException;
import com.lohas.api.model.Bank;
import com.lohas.api.model.Banker;
import com.lohas.api.model.BankerOneRequest;
import com.lohas.api.model.BankerOneResponse;
import com.lohas.api.model.CheckSignUpEmailRequest;
import com.lohas.api.model.CheckSignUpEmailResponse;
import com.lohas.api.model.ModelHelper;
import com.lohas.dao.BankDao;
import com.lohas.dao.BankerDao;
import com.lohas.data.BankJdo;
import com.lohas.data.BankerJdo;

@Controller
@RequestMapping("/api")
public class BankerAPI extends CommonAPI {

	@Autowired
	BankerDao bankerDao;
	
	@Autowired
	BankDao bankDao;
	
	static Logger log = Logger.getLogger(BankerAPI.class.getName());
	MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
	
	/**
	 * This API should be called when banker want to refresh all information about him
	 * E.g. when banker come back to the app
	 * Below information will be returned
	 * Banker itself, banker's bank, banker's customer 
	 * 
	 * @param reqt
	 * @return
	 * @throws ApplicationException
	 */
	@RequireLoggedIn
	@RequestMapping(value = "/bankerOne", method = RequestMethod.GET)
	public @ResponseBody BankerOneResponse bankerOne(@Valid BankerOneRequest reqt) throws ApplicationException {
		
		log.info("API bankerOne start.");
		
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
		log.info("Retrieve bankerJdo completed.");
		
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
		log.info("Retrieve bankJdo completed.");
		
		/*
		 * Retreive admin bankers
		 */
		final List<BankerJdo> bankerJdoAdminList = bankerDao.retrieveBankerJdos(bankJdo.getBankerIdAdminList());
		log.info("Retrieve bankerJdoAdminList completed.");
		
		/*
		 * Retrieve banker's customers
		 */
		
		/**
		 * TODO: Retrieve banker's customer
		 */
		
		/*
		 * Prepare the response
		 */
		final Banker banker = ModelHelper.convertBankerJdoToBanker(bankerJdo); // Convert bankerJdo to banker model 
		final Bank bank = ModelHelper.convertBankJdoToBank(bankJdo, bankerJdoAdminList); // Convert bankJdo to bank model
		log.info("Convert jdo to model completed.");
		
		BankerOneResponse resp = new BankerOneResponse();
		resp.setBank(bank);
		resp.setBanker(banker);
		log.info("API bankerOne end.");
		return resp;
		
	}
	
}


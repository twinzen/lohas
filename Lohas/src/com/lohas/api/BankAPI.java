package com.lohas.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.lohas.api.annotation.RequireLoggedIn;
import com.lohas.api.constant.ErrorCode;
import com.lohas.api.model.CheckSignUpEmailRequest;
import com.lohas.api.model.CheckSignUpEmailResponse;
import com.lohas.api.model.ErrorResponse;
import com.lohas.api.model.OpenBankRequest;
import com.lohas.api.model.OpenBankResponse;
import com.lohas.dao.BankerDao;
import com.lohas.data.BankerJdo;


@Controller
@RequestMapping("/api")
public class BankAPI {

	static Logger log = Logger.getLogger(BankAPI.class.getName());
	MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
	
	@Autowired
	Validator validator;
	
	@Autowired
	BankerDao bankerDao;
			
	/*
	 * Check whether the Email was used by another banker already
	 */
	@RequireLoggedIn
	@RequestMapping(value = "/checkSignUpEamil", method = RequestMethod.GET)
	public @ResponseBody CheckSignUpEmailResponse checkSignUpEmail(@Valid CheckSignUpEmailRequest reqt) {
		
		boolean isEmailAvailable = true;
		
		BankerJdo bankJdo = bankerDao.retrieveBankerJdoByEmail(reqt.getEmail()); // Try to retrieve bankerJdo by email
		if (bankJdo != null) {
			isEmailAvailable = false; // Someone used this email, not available!
		}
		
		CheckSignUpEmailResponse resp = new CheckSignUpEmailResponse();
		resp.setIsEmailAvailable(isEmailAvailable);
		return resp;
		
	}
	
	/*
	 * Open a new bank
	 * Also register a banker for this bank at the same time
	 */
	@RequestMapping(value = "/openBank", method = RequestMethod.GET)
	public @ResponseBody OpenBankResponse openBank(@Valid OpenBankRequest reqt) {
		
		/** TODO
		 * Create and persist new BankerJdo
		 * Create and persist new BankJdo
		 * Two actions should be in same transaction
		 */

		OpenBankResponse resp = new OpenBankResponse();

		return resp;
		
	}
	
	/*
	 * Handle invalid input parameter exception
	 */
	@ExceptionHandler(BindException.class)
	public @ResponseBody ErrorResponse handleInputParametersException(BindException e) {
		
		List<String> reasonDetails = new ArrayList<String>();
		
		for (ObjectError error : e.getBindingResult().getAllErrors()) {
			String reasonDetail = error.getCode()+"|"+error.getDefaultMessage();
			reasonDetails.add(reasonDetail);
		}

		ErrorResponse resp = new ErrorResponse();
		resp.setReasonCode(ErrorCode.INVALID_PARAMS);
		resp.setReasonDetails(reasonDetails);
		
		return resp;
	}
	
}

package com.lohas.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.lohas.api.model.CheckSignUpEmailRequest;
import com.lohas.api.model.CheckSignUpEmailResponse;
import com.lohas.api.model.ErrorResponse;
import com.lohas.api.model.SampleRequest;
import com.lohas.api.model.SampleResponse;
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

package com.lohas.api;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

@Controller
@RequestMapping("/api")
public class BankAPI {

	static Logger log = Logger.getLogger(BankAPI.class.getName());
	MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
	
	@RequestMapping(value = "/signUpBank", method = RequestMethod.GET)
	public ModelMap signUpBank(
			HttpServletRequest request,
			HttpServletResponse response) {
	
		
		ModelMap model = new ModelMap();
		return model;
	}
	
}

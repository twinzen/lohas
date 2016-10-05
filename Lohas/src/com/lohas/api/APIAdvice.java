package com.lohas.api;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lohas.api.constant.ErrorCode;
import com.lohas.api.exception.ApplicationException;
import com.lohas.api.model.CommonRequest;
import com.lohas.dao.SessionDao;
import com.lohas.data.SessionJdo;

@Component
@Aspect
public class APIAdvice {

	static Logger log = Logger.getLogger(BankerAPI.class.getName());
	
	@Autowired
	SessionDao sessionDao;
	
	
	@Before("com.lohas.SystemArchitecture.api()")
	public void beforeAPI (JoinPoint thisJoinPoint) {
		System.out.println("beforeAPI");
		System.out.println(thisJoinPoint);
	}
	
	@Before("com.lohas.SystemArchitecture.apiRequireLoggedIn()")
	public void beforeAPIRequireLoggedIn (JoinPoint thisJoinPoint) throws ApplicationException {
		log.finest("API require logged in, check session from request.");
		boolean isLoggedIn = false;
		for (Object arg : thisJoinPoint.getArgs()) {
			if (arg instanceof CommonRequest) {
				CommonRequest reqt = (CommonRequest) arg;
				String token = reqt.getToken();
				if (token != null) {
					SessionJdo sessionJdo = sessionDao.retrieveSessionJdoByToken(token);
					if (sessionJdo != null) {
						reqt.setUserId(sessionJdo.getUserId());
						isLoggedIn = true;
						log.finest("Session found, put user id to request.");
					} else {
						log.finest("Session not found.");
					}
				} else {
					log.finest("No token in request!");
				}
			}
		}
		if (!isLoggedIn) {
			throw new ApplicationException(ErrorCode.NOT_LOGGED_IN, "Not logged in.");
		}
	}
	
}

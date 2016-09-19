package com.lohas.api;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.lohas.api.model.CommonRequest;

@Component
@Aspect
public class APIAdvice {

	@Before("com.lohas.SystemArchitecture.api()")
	public void beforeAPI (JoinPoint thisJoinPoint) {
		System.out.println("beforeAPI");
		System.out.println(thisJoinPoint);
	}
	
	@Before("com.lohas.SystemArchitecture.apiRequireLoggedIn()")
	public void beforeAPIRequireLoggedIn (JoinPoint thisJoinPoint) {
		System.out.println("beforeAPIRequireLoggedIn");
		for (Object arg : thisJoinPoint.getArgs()) {
			if (arg instanceof CommonRequest) {
				System.out.println("Got the CommonRequest!");
				CommonRequest reqt = (CommonRequest) arg;
				System.out.println(reqt.getToken());
			}
		}
		System.out.println(thisJoinPoint);
	}
	
}

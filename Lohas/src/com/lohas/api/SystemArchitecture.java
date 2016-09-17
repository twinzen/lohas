package com.lohas.api;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.lohas.api.model.CommonRequest;

@Component
@Aspect
public class SystemArchitecture {

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) &&"
			+ "execution(* com.lohas.api.*.*(..))")
	public void api () {}
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) &&"
			+ "@annotation(com.lohas.api.annotation.RequireLoggedIn) &&"
			+ "execution(* com.lohas.api.*.*(..))")
	public void apiRequireLoggedIn () {}
	
	
	@Before("api()")
	public void beforeAPI (JoinPoint thisJoinPoint) {
		System.out.println("beforeAPI");
		System.out.println(thisJoinPoint);
	}
	
	@Before("apiRequireLoggedIn()")
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

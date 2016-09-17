package com.lohas;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


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
	
	
}

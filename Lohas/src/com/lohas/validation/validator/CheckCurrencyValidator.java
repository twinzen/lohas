package com.lohas.validation.validator;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lohas.validation.constraints.CheckCurrency;

public class CheckCurrencyValidator implements ConstraintValidator<CheckCurrency, String> {

	
	@Override
	public void initialize(CheckCurrency constraintAnnotation) {
		// Do nothing
	}

	@Override
	public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
		if (Currency.getAvailableCurrencies().contains(object)) {
			return true;
		} else {
			return false;
		}
		
	}

}

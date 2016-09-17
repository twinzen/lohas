package com.lohas.validation.constraints;


import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lohas.validation.validator.CheckCurrencyValidator;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckCurrencyValidator.class)
@Documented
public @interface CheckCurrency {

    String message() default "{com.lohas.validation.constraints}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}


package com.lohas.dao;

import static com.lohas.dao.OfyService.ofy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lohas.data.BankJdo;
import com.lohas.data.BankerJdo;

@Configuration
public class BankDao {
	
	@Bean
    public BankDao bankDao() {
        return new BankDao();
    }

	/*
	 * Get bank by primary key
	 */
	public BankJdo retrieveBankJdo (Long bankId) {
		return ofy().load().type(BankJdo.class).id(bankId).now(); 
	}
	
	/*
	 * Persist bankJdo 
	 */
	public void persistBankJdo (BankJdo bankJdo) {
		ofy().save().entities(bankJdo).now();
	}
	

	
}

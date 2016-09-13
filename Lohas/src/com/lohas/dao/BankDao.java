package com.lohas.dao;

import static com.lohas.dao.OfyService.ofy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lohas.data.BankerJdo;

@Configuration
public class BankDao {
	
	@Bean
    public BankDao bankDao() {
        return new BankDao();
    }

	

	
}

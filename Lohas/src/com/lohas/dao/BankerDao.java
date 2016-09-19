package com.lohas.dao;

import static com.lohas.dao.OfyService.ofy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lohas.data.BankerJdo;

@Configuration
public class BankerDao {
	
	@Bean
    public BankerDao bankerDao() {
        return new BankerDao();
    }

	/*
	 * Get banker by primary key
	 */
	public BankerJdo retrieveBankerJdo (Long bankId) {
		return ofy().load().type(BankerJdo.class).id(bankId).now(); 
	}
	
	/*
	 * Get banker by primary email
	 */
	public BankerJdo retrieveBankerJdoByEmail (String email) {
		return ofy().load().type(BankerJdo.class).filter("email", email).first().now();
	}
	
	/*
	 * Persist bankerJdo
	 */
	@SuppressWarnings("unchecked")
	public void persistBankerJdo (BankerJdo bankerJdo) {
		ofy().save().entities(bankerJdo).now();
	}

	
}

package com.lohas.dao;

import static com.lohas.dao.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lohas.api.annotation.RequireLoggedIn;
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
	public BankerJdo retrieveBankerJdo (Long bankerId) {
		return ofy().load().type(BankerJdo.class).id(bankerId).now(); 
	}
	
	/*
	 * Get banker by primary email
	 */
	public BankerJdo retrieveBankerJdoByEmail (String email) {
		return ofy().load().type(BankerJdo.class).filter("primaryEmail", email).first().now();
	}
	
	/*
	 * Get bankers by bankerIds
	 */
	public List<BankerJdo> retrieveBankerJdos (List<Long> bankerIds) {
		Map<Long, BankerJdo> bankerJdoMap = ofy().load().type(BankerJdo.class).ids(bankerIds);
		return new ArrayList<BankerJdo>(bankerJdoMap.values());
	}
	
	/*
	 * Persist bankerJdo
	 */
	@SuppressWarnings("unchecked")
	public void persistBankerJdo (BankerJdo bankerJdo) {
		ofy().save().entities(bankerJdo).now();
	}

	
}

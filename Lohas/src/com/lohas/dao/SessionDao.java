package com.lohas.dao;

import static com.lohas.dao.OfyService.ofy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lohas.data.BankJdo;
import com.lohas.data.BankerJdo;
import com.lohas.data.SessionJdo;

@Configuration
public class SessionDao {
	
	@Bean
    public SessionDao sessionDao() {
        return new SessionDao();
    }
	
	
	/*
	 * Get sessionJdo by token
	 */
	public SessionJdo retrieveSessionJdoByToken (String token) {
		return ofy().load().type(SessionJdo.class).filter("token", token).first().now(); 
	}
	
	/*
	 * Persist sessionJdo 
	 */
	public void persistSessionJdo (SessionJdo sessionJdo) {
		ofy().save().entities(sessionJdo).now();
	}

}

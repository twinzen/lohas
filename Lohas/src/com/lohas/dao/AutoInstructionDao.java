package com.lohas.dao;

import static com.lohas.dao.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lohas.data.AccountJdo;
import com.lohas.data.AutoInstructionJdo;
import com.lohas.data.BankerJdo;

@Configuration
public class AutoInstructionDao {
	
	@Bean
    public AutoInstructionDao autoInstructionDao () {
        return new AutoInstructionDao();
    }
	
	/*
	 * Get auto instruction by primary key
	 */
	public AutoInstructionJdo retrieveAutoInstructionJdo (Long autoInstructionId) {
		return ofy().load().type(AutoInstructionJdo.class).id(autoInstructionId).now(); 
	}
	
	/*
	 * Get auto instructions by accountId
	 */
	public List<AutoInstructionJdo> retrieveAutoInstructionJdos (Long accountId) {
		return ofy().load().type(AutoInstructionJdo.class).filter("accountId", accountId).list();
	}
	
	/*
	 * Persist autoInstructionJdo
	 */
	@SuppressWarnings("unchecked")
	public void persistAutoInstructionJdo (AutoInstructionJdo autoInstructionJdo) {
		ofy().save().entities(autoInstructionJdo).now();
	}


}

package com.lohas.dao;

import static com.lohas.dao.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lohas.data.AccountJdo;
import com.lohas.data.BankerJdo;

@Configuration
public class AccountDao {
	
	@Bean
    public AccountDao accountDao() {
        return new AccountDao();
    }
	
	/*
	 * Get account by primary key
	 */
	public AccountJdo retrieveAccountJdo (Long accountId) {
		return ofy().load().type(AccountJdo.class).id(accountId).now(); 
	}
	
	/*
	 * Get account by primary account code and bank Id
	 */
	public AccountJdo retrieveAccountJdoByAccountCodeBankId (String accountCode, Long bankId) {
		return ofy().load().type(AccountJdo.class).filter("bankId", bankId).filter("accountCode", accountCode).first().now();
	}
	
	/*
	 * Get accounts by customerId
	 */
	public List<AccountJdo> retrieveAccountJdos (Long customerId) {
		return ofy().load().type(AccountJdo.class).filter("customerId", customerId).list();
	}
	
	/*
	 * Persist accountJdo
	 */
	@SuppressWarnings("unchecked")
	public void persistAccountJdo (AccountJdo accountJdo) {
		ofy().save().entities(accountJdo).now();
	}


}

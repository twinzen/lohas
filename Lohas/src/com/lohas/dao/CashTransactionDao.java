package com.lohas.dao;

import static com.lohas.dao.OfyService.ofy;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lohas.data.CashTransactionJdo;

@Configuration
public class CashTransactionDao {
	
	@Bean
    public CashTransactionDao cashTransactionDao() {
        return new CashTransactionDao();
    }
	
	/*
	 * Get cash txn by primary key
	 */
	public CashTransactionJdo retrieveCashTransactionDao (Long cashTransactionId) {
		return ofy().load().type(CashTransactionJdo.class).id(cashTransactionId).now(); 
	}
	
	/*
	 * Get cash txns by account Id
	 */
	public List<CashTransactionJdo> retrieveCashTransactionJdos (Long accountId) {
		return ofy().load().type(CashTransactionJdo.class).filter("accountId", accountId).list();
	}
	
	/*
	 * Persist cashTransactionJdo
	 */
	@SuppressWarnings("unchecked")
	public void persistCashTransactionJdo (CashTransactionJdo cashTransactionJdo) {
		ofy().save().entities(cashTransactionJdo).now();
	}


}

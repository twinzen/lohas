package com.lohas.dao;

import static com.lohas.dao.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lohas.data.CustomerJdo;

@Configuration
public class CustomerDao {
	
	@Bean
    public CustomerDao customerDao() {
        return new CustomerDao();
    }
	
	/*
	 * Get customer by primary key
	 */
	public CustomerJdo retrieveCustomerJdo (Long customerId) {
		return ofy().load().type(CustomerJdo.class).id(customerId).now(); 
	}
	
	/*
	 * Get customer by username
	 */
	public CustomerJdo retrieveCustomerJdoByUsername (String username) {
		return ofy().load().type(CustomerJdo.class).filter("username", username).first().now();
	}
	
	/*
	 * Get customers by bankId
	 */
	public List<CustomerJdo> retrieveCustomerJdos (Long bankId) {
		return ofy().load().type(CustomerJdo.class).filter("bankId", bankId).list();
	}
	
	/*
	 * Persist customerJdo
	 */
	@SuppressWarnings("unchecked")
	public void persistCustomerJdo (CustomerJdo customerJdo) {
		ofy().save().entities(customerJdo).now();
	}

	
}

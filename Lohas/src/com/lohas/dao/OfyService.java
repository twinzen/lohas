package com.lohas.dao;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.lohas.data.AccountJdo;
import com.lohas.data.BankJdo;
import com.lohas.data.BankerJdo;
import com.lohas.data.BlobImageJdo;
import com.lohas.data.CashTransactionJdo;
import com.lohas.data.CustomerJdo;
import com.lohas.data.SessionJdo;
import com.lohas.data.TopUpRuleJdo;

public class OfyService {
    static {
//    	factory().register(AccountJdo.class);
    	factory().register(BankJdo.class);
    	factory().register(BankerJdo.class);
    	factory().register(SessionJdo.class);
//    	factory().register(BlobImageJdo.class);
//    	factory().register(CashTransactionJdo.class);
//    	factory().register(CustomerJdo.class);
//    	factory().register(TopUpRuleJdo.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
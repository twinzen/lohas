package com.lohas.dao;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.BigDecimalLongTranslatorFactory;
import com.lohas.data.AccountJdo;
import com.lohas.data.BankJdo;
import com.lohas.data.BankerJdo;
import com.lohas.data.BlobImageJdo;
import com.lohas.data.CashTransactionJdo;
import com.lohas.data.CustomerJdo;
import com.lohas.data.SessionJdo;
import com.lohas.data.AutoInstructionJdo;

public class OfyService {
    static {
    	factory().getTranslators().add(new BigDecimalLongTranslatorFactory());
    	factory().register(AccountJdo.class);
    	factory().register(BankJdo.class);
    	factory().register(BankerJdo.class);
    	factory().register(SessionJdo.class);
    	factory().register(BlobImageJdo.class);
    	factory().register(CashTransactionJdo.class);
    	factory().register(CustomerJdo.class);
    	factory().register(CashTransactionJdo.class);
    	factory().register(AutoInstructionJdo.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
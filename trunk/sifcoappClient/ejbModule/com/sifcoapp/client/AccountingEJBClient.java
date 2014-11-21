package com.sifcoapp.client;

import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.bussinessLogic.AccountingEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.security.ejb.SecurityEJBRemote;

public class AccountingEJBClient implements AccountingEJBRemote {
	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/AccountingEJB!com.sifcoapp.bussinessLogic.AccountingEJBRemote";
	private static AccountingEJBRemote bean;
	private static Context context = null;
	public AccountingEJBClient(){
	       
        //2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			bean = (AccountingEJBRemote)context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List getAccPeriods() {
		// TODO Auto-generated method stub
		List lstPeriods=new Vector();
		
		lstPeriods=bean.getAccPeriods();
		
		return lstPeriods;
	}

}

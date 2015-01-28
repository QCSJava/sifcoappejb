package com.sifcoapp.client;

import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.bussinessLogic.AccountingEJBRemote;
import com.sifcoapp.bussinessLogic.SalesEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;

public class SalesEJBClient {
	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/SalesEJB!com.sifcoapp.bussinessLogic.SalesEJBRemote";
	private static SalesEJBRemote bean;
	private static Context context = null;
	public SalesEJBClient() {
	       
        //2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			bean = (SalesEJBRemote)context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String doSales() throws Exception {
		// TODO Auto-generated method stub
		String retorno;
		retorno=bean.doSales();
		return retorno;
	}
	
		
	

}

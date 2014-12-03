package com.sifcoapp.client;

import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.admin.ejb.AdminEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;


public class AdminEJBClient {
	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/AdminEJB!com.sifcoapp.admin.ejb.AdminEJBRemote";
	private static AdminEJBRemote bean;
	private static Context context = null;
	public AdminEJBClient(){
		       
        //2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			bean = (AdminEJBRemote)context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public EnterpriseOutTO saveEnterprise(EnterpriseTO parameters){
		
		EnterpriseOutTO enterpriseOutTO=null;
		
		enterpriseOutTO=bean.saveEnterprise(parameters);
				
		return enterpriseOutTO;
	}
	public EnterpriseTO getEnterpriseInfo(){
		
		EnterpriseTO enterpriseTO;
		
		enterpriseTO=bean.getEnterpriseInfo();
		
		return enterpriseTO;
		
		
	}
	
	public List findCatalog(String nameCatalog) {
		List catlgLst=null;
		
		catlgLst=bean.findCatalog(nameCatalog);
		
		return catlgLst;
	}
}

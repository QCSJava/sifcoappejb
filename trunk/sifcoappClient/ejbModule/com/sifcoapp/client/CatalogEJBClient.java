package com.sifcoapp.client;

import java.util.List;
import java.util.Vector;

import javax.naming.Context;


import javax.naming.NamingException;

import com.sifcoapp.bussinessLogic.CatalogEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;

public class CatalogEJBClient implements CatalogEJBRemote {

	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/CatalogEJB!com.sifcoapp.bussinessLogic.CatalogEJBRemote";
	private static CatalogEJBRemote bean;
	private static Context context = null;
	
	public CatalogEJBClient() {

		// 2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			bean = (CatalogEJBRemote) context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int cat_bpa_businesspartner_mtto(BusinesspartnerTO parameters,int accion){
		
		int _return = 0;

		_return = bean.cat_bpa_businesspartner_mtto(parameters, accion);

		return _return;
	}
	public List get_businesspartner(BusinesspartnerInTO parameters){
		List lstPeriods = new Vector();
		lstPeriods = bean.get_businesspartner(parameters);
		return lstPeriods;
	}
	public List get_businesspartnerBykey(BusinesspartnerInTO parameters) {
		// TODO Auto-generated method stub
		List lstPeriods = new Vector();
		lstPeriods = bean.get_businesspartnerBykey(parameters);
		return lstPeriods;
	}
	
}

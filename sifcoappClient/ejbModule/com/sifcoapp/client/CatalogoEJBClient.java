package com.sifcoapp.client;

import java.util.List;
import java.util.Vector;

import javax.naming.Context;


import javax.naming.NamingException;

import com.sifcoapp.catalogo.ejb.CatalogoEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.catalogo.to.businesspartnerTO;

public class CatalogoEJBClient implements CatalogoEJBRemote {

	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/CatalogoEJB!com.sifcoapp.catalogo.ejb.CatalogoEJBRemote";
	private static CatalogoEJBRemote bean;
	private static Context context = null;
	public CatalogoEJBClient() {

		// 2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			bean = (CatalogoEJBRemote) context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int cat_bpa_businesspartner_mtto(businesspartnerTO parameters,int accion){
		
		int _return = 0;

		_return = bean.cat_bpa_businesspartner_mtto(parameters, accion);

		return _return;
	}
	public List get_cat_bpa_businesspartner(String codecard, String codename) {
		List lstPeriods = new Vector();
		lstPeriods = bean.get_cat_bpa_businesspartner(codecard, codename);
		return lstPeriods;
	}
	

}

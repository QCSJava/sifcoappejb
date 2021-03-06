package com.sifcoapp.client;

import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.bussinessLogic.CatalogEJBRemote;
import com.sifcoapp.bussinessLogic.facade.CatalogFacadeRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.catalog.to.BusinesspartnerAcountTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

public class CatalogEJBClient {

	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/CatalogEJB!com.sifcoapp.bussinessLogic.CatalogEJBRemote";
	private static final String LOOKUP_STRING_FACADE = "java:global/sifcoappEAR/sifcoapp/CatalogFacade!com.sifcoapp.bussinessLogic.facade.CatalogFacadeRemote";
	//private static CatalogEJBRemote bean;
	private static CatalogFacadeRemote bean;
	private static Context context = null;

	public CatalogEJBClient() {

		// 2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			//bean = (CatalogEJBRemote) context.lookup(LOOKUP_STRING);
			bean = (CatalogFacadeRemote) context.lookup(LOOKUP_STRING_FACADE);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultOutTO cat_bpa_businesspartner_mtto(
			BusinesspartnerTO parameters, int accion) throws Exception {

		ResultOutTO _return;

		_return = bean.cat_bpa_businesspartner_mtto(parameters, accion);

		return _return;
	}

	public List get_businesspartner(BusinesspartnerInTO parameters)
			throws Exception {
		List lstPeriods = new Vector();
		lstPeriods = bean.get_businesspartner(parameters);
		return lstPeriods;
	}

	public BusinesspartnerTO get_businesspartnerBykey(String parameters)
			throws Exception {
		// TODO Auto-generated method stub
		BusinesspartnerTO lstPeriods = new BusinesspartnerTO();
		lstPeriods = bean.get_businesspartnerBykey(parameters);
		return lstPeriods;
	}

	public ResultOutTO inv_cat_bpa_businesspartnerAcount_mtto(BusinesspartnerAcountTO parameters,int accion)throws Exception{
		

		ResultOutTO _return;

		_return = bean.inv_cat_bpa_businesspartnerAcount_mtto(parameters, accion);

		return _return;
		
	}

	public List get_businesspartnerAcount(String code)
			throws Exception {
		List lstPeriods = new Vector();
		lstPeriods = bean.get_businesspartnerAcount(code);
		return lstPeriods;
	}
	public BusinesspartnerAcountTO get_businesspartnerAcountBykey(BusinesspartnerAcountTO parameters)
			throws Exception {
		// TODO Auto-generated method stub
		BusinesspartnerAcountTO lstPeriods = new BusinesspartnerAcountTO();
		lstPeriods = bean.get_businesspartnerAcountBykey(parameters);
		return lstPeriods;
	}
}

package com.sifcoapp.client;

import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.admin.ejb.AdminEJBRemote;
import com.sifcoapp.admin.ejb.ParameterEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.admin.dao.ParameterDAO;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesPriceTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.admin.to.PricesListInTO;
import com.sifcoapp.objects.admin.to.PricesListTO;
import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.transaction.to.WarehouseJournalTO;

public class ParameterEJBClient {
	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/ParameterEJB!com.sifcoapp.admin.ejb.ParameterEJBRemote";
	private static ParameterEJBRemote bean;
	private static Context context = null;

	public ParameterEJBClient() {

		try {
			context = ClientUtility.getInitialContext();
			bean = (ParameterEJBRemote) context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultOutTO parameter_mtto(parameterTO parameter,int action)throws EJBException{
		ResultOutTO _return= new ResultOutTO();

		_return = bean.parameter_mtto(parameter, action);

		return _return;
	}

	 public List getParameter()throws EJBException{
		List parameter = new Vector();
	
		parameter = bean.getParameter();
		
		return parameter;
	
	}

	 public parameterTO getParameterbykey(int code)throws EJBException{
			// TODO Auto-generated method stub
		 parameterTO _return = new parameterTO();
			_return = bean.getParameterbykey(code);
			return _return;
		}
}

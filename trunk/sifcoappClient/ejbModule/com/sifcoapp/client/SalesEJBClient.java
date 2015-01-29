package com.sifcoapp.client;

import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.bussinessLogic.AccountingEJBRemote;
import com.sifcoapp.bussinessLogic.SalesEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.sales.to.SalesDetailTO;
import com.sifcoapp.objects.sales.to.SalesInTO;
import com.sifcoapp.objects.sales.to.SalesTO;

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
	
	public List getSales(SalesInTO param) throws Exception{
		List _return;
		_return= bean.getSales(param);
		return _return;
	}
	
	public SalesTO getSalesByKey(int docentry) throws Exception{
		SalesTO _return;
		_return=bean.getSalesByKey(docentry);
		return _return;
	}
	
	public ResultOutTO inv_Sales_mtto(SalesTO parameters, int accion) throws Exception{
		ResultOutTO _return;
		_return=bean.inv_Sales_mtto(parameters, accion);
		return _return;
	}
	
	public List getSalesDetail(int docentry) throws Exception{
		List _return;
		_return= bean.getSalesDetail(docentry);
		return _return;
	}
	
}

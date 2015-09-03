package com.sifcoapp.client;

import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.bussinessLogic.SalesEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.sales.to.*;
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
	
	public String last_INPUT(int series, String _objtype) throws Exception{
		String _return;
		_return= bean.last_INPUT(series, _objtype);
		return _return;
		
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
	
	public List getClientCredi(ClientCrediInTO param) throws Exception{
		List _return;
		_return= bean.getClientCredi(param);
		return _return;
	}
	
	public ClientCrediTO getClientCrediByKey(int docentry) throws Exception{
		ClientCrediTO _return;
		_return=bean.getClientCrediByKey(docentry);
		return _return;
	}
	
	public ResultOutTO inv_ClientCredi_mtto(ClientCrediTO parameters, int accion) throws Exception{
		ResultOutTO _return;
		_return=bean.inv_ClientCredi_mtto(parameters, accion);
		return _return;
	}
	
	public List getClientCrediDetail(int docentry) throws Exception{
		List _return;
		_return= bean.getClientCrediDetail(docentry);
		return _return;
	}
	
	public List getDelivery(DeliveryInTO param) throws Exception{
		List _return;
		_return= bean.getDelivery(param);
		return _return;
	}
	
	public DeliveryTO getDeliveryByKey(int docentry) throws Exception{
		DeliveryTO _return;
		_return=bean.getDeliveryByKey(docentry);
		return _return;
	}
	
	public ResultOutTO inv_Delivery_mtto(DeliveryTO parameters, int accion) throws Exception{
		ResultOutTO _return;
		_return=bean.inv_Delivery_mtto(parameters, accion);
		return _return;
	}
}

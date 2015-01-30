package com.sifcoapp.client;

import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import com.sifcoapp.bussinessLogic.PurchaseEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.purchase.to.*;

public class PurchaseEJBClient {
	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/PurchaseEJB!com.sifcoapp.bussinessLogic.PurchaseEJBRemote";
	private static PurchaseEJBRemote bean;
	private static Context context = null;
	public PurchaseEJBClient() {
	       
        //2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			bean = (PurchaseEJBRemote)context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List getPurchase(PurchaseInTO param) throws Exception{
		List _return;
		_return= bean.getPurchase(param);
		return _return;
	}
	
	public PurchaseTO getPurchaseByKey(int docentry) throws Exception{
		PurchaseTO _return;
		_return=bean.getPurchaseByKey(docentry);
		return _return;
	}
	
	public ResultOutTO inv_Purchase_mtto(PurchaseTO parameters, int accion) throws Exception{
		ResultOutTO _return;
		_return=bean.inv_Purchase_mtto(parameters, accion);
		return _return;
	}
	
	public List getPurchaseDetail(int docentry) throws Exception{
		List _return;
		_return= bean.getPurchaseDetail(docentry);
		return _return;
	}
	
	
	public List getSupplier(SupplierInTO param) throws Exception{
		List _return;
		_return= bean.getSupplier(param);
		return _return;
	}
	
	public SupplierTO getSupplierByKey(int docentry) throws Exception{
		SupplierTO _return;
		_return=bean.getSupplierByKey(docentry);
		return _return;
	}
	
	public ResultOutTO inv_Supplier_mtto(SupplierTO parameters, int accion) throws Exception{
		ResultOutTO _return;
		_return=bean.inv_Supplier_mtto(parameters, accion);
		return _return;
	}
	
	public List getSupplierDetail(int docentry) throws Exception{
		List _return;
		_return= bean.getSupplierDetail(docentry);
		return _return;
	}
}

package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.Remote;

import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.purchase.to.*;


@Remote
public interface PurchaseEJBRemote {
	
	public List getPurchase(PurchaseInTO param) throws Exception;
	
	public PurchaseTO getPurchaseByKey(int docentry) throws Exception;
	
	public ResultOutTO inv_Purchase_mtto(PurchaseTO parameters, int accion) throws Exception;
	
	public List getPurchaseQuotation(PurchaseQuotationInTO param) throws Exception;
	
	public PurchaseQuotationTO getPurchaseQuotationByKey(int docentry) throws Exception;
	
	public ResultOutTO inv_PurchaseQuotation_mtto(PurchaseQuotationTO parameters, int accion) throws Exception;
	
	public List getPurchaseDetail(int docentry) throws Exception;
	
	public List getSupplier(SupplierInTO param) throws Exception;
	
	public SupplierTO getSupplierByKey(int docentry) throws Exception;
	
	public ResultOutTO inv_Supplier_mtto(SupplierTO parameters, int accion) throws Exception;
	
	public List getSupplierDetail(int docentry) throws Exception;
	
	
}

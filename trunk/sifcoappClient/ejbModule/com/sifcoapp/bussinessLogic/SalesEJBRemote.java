package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.sales.to.SalesDetailTO;
import com.sifcoapp.objects.sales.to.SalesInTO;
import com.sifcoapp.objects.sales.to.SalesTO;

@Remote
public interface SalesEJBRemote {
	public String doSales() throws EJBException;
	
	public List getSales(SalesInTO param) throws Exception;
	
	public SalesTO getSalesByKey(int docentry) throws Exception;
	
	public ResultOutTO inv_Sales_mtto(SalesTO parameters, int accion) throws Exception;
	
	public List getSalesDetail(int docentry) throws Exception;
	

	
	

}

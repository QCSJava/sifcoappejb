package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.sales.to.*;

@Remote
public interface SalesEJBRemote {

	public List getSales(SalesInTO param) throws Exception;

	public SalesTO getSalesByKey(int docentry) throws Exception;

	public ResultOutTO inv_Sales_mtto(SalesTO parameters, int accion)
			throws Exception;

	public List getSalesDetail(int docentry) throws Exception;

	public List getClientCredi(ClientCrediInTO param) throws Exception;

	public ClientCrediTO getClientCrediByKey(int docentry) throws Exception;

	public ResultOutTO inv_ClientCredi_mtto(ClientCrediTO parameters, int accion)
			throws Exception;

	public List getClientCrediDetail(int docentry) throws Exception;

	public List getDelivery(DeliveryInTO param) throws Exception;

	public DeliveryTO getDeliveryByKey(int docentry) throws Exception;

	public ResultOutTO inv_Delivery_mtto(DeliveryTO parameters, int accion)
			throws Exception;

	public String last_Sales(int series, String _objtype) throws Exception;

	/* public ResultOutTO validateSale(SalesTO parameters) throws Exception; */
}

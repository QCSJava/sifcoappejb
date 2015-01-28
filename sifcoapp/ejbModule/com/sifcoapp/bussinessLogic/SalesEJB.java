package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.Stateless;

import com.sifcoapp.objects.sales.DAO.*;
import com.sifcoapp.objects.sales.to.*;

/**
 * Session Bean implementation class SalesEJB
 */
@Stateless
public class SalesEJB implements SalesEJBRemote {

    /**
     * Default constructor. 
     */
    public SalesEJB() {
        // TODO Auto-generated constructor stub
    }

	public String doSales() {
		String retorno;
		// TODO Auto-generated method stub
		retorno="Do Sales!";
		return retorno;
	}

	public List getSales(SalesInTO param) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		SalesDAO DAO= new SalesDAO();
		_return= DAO.getSales(param);
		return _return;
	}

	public SalesTO getSalesByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		SalesTO _return= new SalesTO();
		SalesDAO DAO = new SalesDAO();
		_return= DAO.getSalesByKey(docentry);
		return _return;
	}

	public int inv_Sales_mtto(SalesTO parameters, int accion) throws Exception {
		// TODO Auto-generated method stub
		int _return;
		SalesDAO DAO = new SalesDAO();
		_return= DAO.inv_Sales_mtto(parameters, accion);
		return _return;
	}

	public List getSalesDetail(int docentry) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		SalesDetailDAO DAO = new SalesDetailDAO();
		_return= DAO.getSalesDetail(docentry);
		return _return;
	}

	public int inv_SalesDetail_mtto(SalesDetailTO parameters, int action)
			throws Exception {
		// TODO Auto-generated method stub
		int _return;
		SalesDetailDAO DAO = new SalesDetailDAO();
		_return=DAO.inv_SalesDetail_mtto(parameters, action);
		return _return;
	}

}

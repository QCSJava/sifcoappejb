package com.sifcoapp.bussinessLogic;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.inventory.dao.GoodsIssuesDAO;
import com.sifcoapp.objects.inventory.dao.GoodsissuesDetailDAO;
import com.sifcoapp.objects.inventory.to.GoodsIssuesDetailTO;
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
		
		try {
			_return= DAO.getSales(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public SalesTO getSalesByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		SalesTO _return= new SalesTO();
		SalesDAO DAO = new SalesDAO();
		try {
			_return= DAO.getSalesByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		
		return _return;
	}

	public int inv_Sales_mtto(SalesTO parameters, int action) throws Exception {
		// TODO Auto-generated method stub
		int _return;
		Double total=0.0;
		SalesDAO DAO = new SalesDAO();
		SalesDetailDAO goodDAO1 = new SalesDetailDAO();
		try {
		Iterator<SalesDetailTO> iterator2 = parameters.getSalesDetails().iterator();
		while (iterator2.hasNext()) {
			SalesDetailTO articleDetalle = (SalesDetailTO) iterator2.next();
			articleDetalle.setLinetotal(articleDetalle.getQuantity()*articleDetalle.getPrice());
			total=total+articleDetalle.getLinetotal();
		}
		parameters.setDoctotal(total);
		_return = DAO.inv_Sales_mtto(parameters, action);
		
		
		Iterator<SalesDetailTO> iterator = parameters.getSalesDetails().iterator();
		while (iterator.hasNext()) {
			SalesDetailTO articleDetalle = (SalesDetailTO) iterator.next();
			// Para articulos nuevos
			articleDetalle.setDocentry(_return);
			if (action == Common.MTTOINSERT) {
				goodDAO1.inv_SalesDetail_mtto(articleDetalle,Common.MTTOINSERT);
			}
			if (action == Common.MTTODELETE) {
				goodDAO1.inv_SalesDetail_mtto(articleDetalle,Common.MTTODELETE);
			}
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}


	public List getSalesDetail(int docentry) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		SalesDetailDAO DAO = new SalesDetailDAO();
		
		try {
			_return= DAO.getSalesDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public int inv_SalesDetail_mtto(SalesDetailTO parameters, int action)
			throws Exception {
		// TODO Auto-generated method stub
		int _return;
		SalesDetailDAO DAO = new SalesDetailDAO();
		try {
			_return=DAO.inv_SalesDetail_mtto(parameters, action);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

}

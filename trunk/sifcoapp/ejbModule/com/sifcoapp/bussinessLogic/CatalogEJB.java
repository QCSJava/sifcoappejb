package com.sifcoapp.bussinessLogic;

import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.catalog.dao.BusinesspartnerDAO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

@Stateless
public class CatalogEJB implements CatalogEJBRemote {

	

	public CatalogEJB() {
		// TODO Auto-generated constructor stub
	}

	public ResultOutTO cat_bpa_businesspartner_mtto(BusinesspartnerTO parameters,
			int accion) throws EJBException{
		
		ResultOutTO _return = new ResultOutTO();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		DAO.setIstransaccional(true);
		parameters.setBalance(0.00);
		parameters.setChecksbal(0.00);
		parameters.setCreditline(0.00);
		parameters.setDebtline(0.00);
		parameters.setDiscount(0.00);
		parameters.setOrdersbal(0.00);
		try {
			DAO.inv_cat_bpa_businesspartner_mtto(parameters, accion);
			DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		}finally {

			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos ingresados correctamente");
		return _return;
	}

	public List get_businesspartner(BusinesspartnerInTO parameters) throws EJBException{
		// TODO Auto-generated method stub
		List _return = new Vector();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		try {
			_return = DAO.get_businesspartner(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public BusinesspartnerTO get_businesspartnerBykey(String parameters) throws EJBException{
		// TODO Auto-generated method stub
		BusinesspartnerTO _return = new BusinesspartnerTO();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		try {
			_return = DAO.get_businesspartnerByKey(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}
}

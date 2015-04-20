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

	private Double zero = 0.00;

	public CatalogEJB() {
		// TODO Auto-generated constructor stub
	}

	public ResultOutTO cat_bpa_businesspartner_mtto(
			BusinesspartnerTO parameters, int accion) throws EJBException {

		ResultOutTO _return = new ResultOutTO();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		DAO.setIstransaccional(true);
		if (parameters.getBalancesys() == null) {
			parameters.setBalancesys(zero);
		}
		if (parameters.getBalance() == null) {
			parameters.setBalance(zero);
		}
		if (parameters.getChecksbal() == null) {
			parameters.setChecksbal(zero);
		}
		if (parameters.getCreditline() == null) {
			parameters.setCreditline(zero);
		}
		if (parameters.getDebtline() == null) {
			parameters.setDebtline(zero);
		}
		if (parameters.getDiscount() == null) {
			parameters.setDiscount(0.00);
		}
		if (parameters.getDnotesbal() == null) {
			parameters.setDnotesbal(zero);
		}
		if (parameters.getOrderbalsy() == null) {
			parameters.setOrderbalsy(zero);
		}
		if (parameters.getOrdersbal() == null) {
			parameters.setOrdersbal(zero);
		}

		try {
			DAO.inv_cat_bpa_businesspartner_mtto(parameters, accion);
			DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos ingresados correctamente");
		return _return;
	}

	public List get_businesspartner(BusinesspartnerInTO parameters)
			throws EJBException {
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

	public BusinesspartnerTO get_businesspartnerBykey(String parameters)
			throws EJBException {
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

	public ResultOutTO validate_businesspartnerBykey(String parameters)
			throws EJBException {

		ResultOutTO _return = new ResultOutTO();
		BusinesspartnerTO partner = new BusinesspartnerTO();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		try {
			partner = DAO.get_businesspartnerByKey(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);

		}
		if (partner.getValidfor().toUpperCase().equals("Y")) {
			_return.setCodigoError(0);
			_return.setMensaje("Socio de Negocio Activo");

		} else {
			_return.setCodigoError(1);
			_return.setMensaje("Socio de Negocio No Activo");

		}

		return _return;
	}
}

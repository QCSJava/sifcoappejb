package com.sifcoapp.bussinessLogic;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;

import com.sifcoapp.objects.catalog.dao.BusinesspartnerDAO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;

@Stateless
public class CatalogEJB implements CatalogEJBRemote {

	public CatalogEJB() {
		// TODO Auto-generated constructor stub
	}

	public int cat_bpa_businesspartner_mtto(BusinesspartnerTO parameters,
			int accion) {
		// TODO Auto-generated method stub
		int _return = 0;
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		try {
			_return = DAO.inv_cat_bpa_businesspartner_mtto(parameters, accion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _return;
	}

	public List get_businesspartner(BusinesspartnerInTO parameters) {
		// TODO Auto-generated method stub
		List _return = new Vector();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		try {
			_return = DAO.get_businesspartner(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return _return;
	}

	public BusinesspartnerTO get_businesspartnerBykey(String parameters) {
		// TODO Auto-generated method stub
		BusinesspartnerTO _return = new BusinesspartnerTO();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		try {
			_return = DAO.get_businesspartnerByKey(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return _return;
	}
}

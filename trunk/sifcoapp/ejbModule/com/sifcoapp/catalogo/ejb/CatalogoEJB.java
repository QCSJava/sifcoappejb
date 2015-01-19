package com.sifcoapp.catalogo.ejb;


import java.util.List;
import java.util.Vector;

import com.sifcoapp.objects.catalogo.dao.businesspartnerDAO;
import com.sifcoapp.objects.catalogo.to.businesspartnerTO;
import com.sifcoapp.objects.inventory.dao.GoodsissuesDAO;

public class CatalogoEJB {

	public int cat_bpa_businesspartner_mtto(businesspartnerTO parameters,
			int accion) {
		// TODO Auto-generated method stub
		int _return;
		businesspartnerDAO DAO= new businesspartnerDAO();
		_return = DAO.inv_cat_bpa_businesspartner_mtto(parameters, accion);
		return _return;
	}
	public List get_cat_bpa_businesspartner(String codecard,String codename) {
		// TODO Auto-generated method stub
		List _return = new Vector();
		businesspartnerDAO DAO = new businesspartnerDAO();
		_return = DAO.get_cat_bpa_businesspartner(codecard, codename);

		return _return;
	}
}

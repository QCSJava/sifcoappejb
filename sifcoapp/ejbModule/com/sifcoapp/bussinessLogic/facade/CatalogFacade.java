package com.sifcoapp.bussinessLogic.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.bussinessLogic.CatalogEJBLocal;
import com.sifcoapp.bussinessLogic.InventoryEJBLocal;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

/**
 * Session Bean implementation class CatalogFacade
 */
@Stateless
public class CatalogFacade implements CatalogFacadeRemote {

    /**
     * Default constructor. 
     */
	@EJB private CatalogEJBLocal process;
    public CatalogFacade() {
        // TODO Auto-generated constructor stub
    }

	public ResultOutTO cat_bpa_businesspartner_mtto(
			BusinesspartnerTO parameters, int accion) throws Exception {
		// TODO Auto-generated method stub
		return process.cat_bpa_businesspartner_mtto(
				parameters,  accion);
	}

	public List get_businesspartner(BusinesspartnerInTO parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		return process.get_businesspartner(parameters);
	}

	public BusinesspartnerTO get_businesspartnerBykey(String parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		return process.get_businesspartnerBykey(parameters);
	}


}

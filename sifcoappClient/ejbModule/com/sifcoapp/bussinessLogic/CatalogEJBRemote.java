package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.catalog.to.BusinesspartnerAcountTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

@Remote
public interface CatalogEJBRemote {
	public ResultOutTO cat_bpa_businesspartner_mtto(
			BusinesspartnerTO parameters, int accion) throws Exception;

	public List get_businesspartner(BusinesspartnerInTO parameters)
			throws EJBException;

	public BusinesspartnerTO get_businesspartnerBykey(String parameters)
			throws EJBException;

	public ResultOutTO validate_businesspartnerBykey(String parameters)
			throws EJBException;

	public ResultOutTO inv_cat_bpa_businesspartnerAcount_mtto(
			BusinesspartnerAcountTO parameters, int accion)throws EJBException;

	public List get_businesspartnerAcount()
			throws EJBException ;
	public BusinesspartnerAcountTO get_businesspartnerAcountBykey(BusinesspartnerAcountTO parameters)
			throws EJBException;
}

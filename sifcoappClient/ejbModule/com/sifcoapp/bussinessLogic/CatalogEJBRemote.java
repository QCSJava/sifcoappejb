package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.Remote;

import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;

@Remote
public interface CatalogEJBRemote {
	public int cat_bpa_businesspartner_mtto(BusinesspartnerTO parameters,
			int accion);

	public List get_businesspartner(BusinesspartnerInTO parameters);

	public BusinesspartnerTO get_businesspartnerBykey(String parameters);

}

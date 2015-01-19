package com.sifcoapp.catalogo.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.sifcoapp.objects.catalogo.to.businesspartnerTO;
@Remote
public interface CatalogoEJBRemote {
	public int cat_bpa_businesspartner_mtto(businesspartnerTO parameters,int accion);
	public List get_cat_bpa_businesspartner(String codecard,String codename);
}

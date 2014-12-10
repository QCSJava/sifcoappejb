package com.sifcoapp.admin.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;

@Remote
public interface AdminEJBRemote {
	public EnterpriseOutTO saveEnterprise(EnterpriseTO parameters);
	public EnterpriseTO getEnterpriseInfo();
	public EnterpriseTO getEnterpriseInfo(int enterpriseCode);
	public List findCatalog(String nameCatalog);
	public List getTablesCatalog();
	/*
	 * Mantenimiento de Catalogos
	 */
	public int cat_tab1_catalogos_mtto(CatalogTO parameters, int action);
}

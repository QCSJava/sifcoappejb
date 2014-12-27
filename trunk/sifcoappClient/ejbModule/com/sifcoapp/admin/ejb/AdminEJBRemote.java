package com.sifcoapp.admin.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
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

	public int cat_articles_mtto(ArticlesTO parameters, int action);
	
	public int cat_branch_mtto(BranchTO parameters, int action);
	
	public List getArticles(String itemcode, String itemname );
	
	public ArticlesTO getArticlesByKey(String itemcode);
	
	public List getBranch(String whscode, String whsname);
	
	public BranchTO getBranchByKey(String whscode);
	
}

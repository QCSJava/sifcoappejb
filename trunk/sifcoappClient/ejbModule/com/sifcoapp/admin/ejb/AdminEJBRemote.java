package com.sifcoapp.admin.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

@Remote
public interface AdminEJBRemote {
	public EnterpriseOutTO saveEnterprise(EnterpriseTO parameters) throws EJBException;

	public EnterpriseTO getEnterpriseInfo() throws EJBException;

	public EnterpriseTO getEnterpriseInfo(int enterpriseCode) throws EJBException;

	public List findCatalog(String nameCatalog) throws EJBException;

	public List getTablesCatalog() throws EJBException;

	/*
	 * Mantenimiento de Catalogos
	 */
	public int cat_tab1_catalogos_mtto(CatalogTO parameters, int action) throws EJBException;

	public ResultOutTO cat_articles_mtto(ArticlesTO parameters, int action) throws EJBException;
	
	public int cat_branch_mtto(BranchTO parameters, int action) throws EJBException;
	
	public List getArticles(ArticlesInTO parameters ) throws EJBException;
	
	public ArticlesTO getArticlesByKey(String itemcode) throws EJBException;
	
	public List getBranch(String whscode, String whsname) throws EJBException;
	
	public BranchTO getBranchByKey(String whscode) throws EJBException;
	
}

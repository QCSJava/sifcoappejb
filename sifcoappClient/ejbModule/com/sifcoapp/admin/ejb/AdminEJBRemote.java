package com.sifcoapp.admin.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesPriceTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.admin.to.PricesListInTO;
import com.sifcoapp.objects.admin.to.PricesListTO;
import com.sifcoapp.objects.admin.to.WarehouseJournalTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

@Remote
public interface AdminEJBRemote {
	public EnterpriseOutTO saveEnterprise(EnterpriseTO parameters)
			throws EJBException;

	public EnterpriseTO getEnterpriseInfo() throws EJBException;

	public EnterpriseTO getEnterpriseInfo(int enterpriseCode)
			throws EJBException;

	public List findCatalog(String nameCatalog) throws EJBException;

	public CatalogTO findCatalogByKey(String catcode, int tablecode)
			throws EJBException;

	public List getTablesCatalog() throws EJBException;

	/*
	 * Mantenimiento de Catalogos
	 */
	public int cat_tab1_catalogos_mtto(CatalogTO parameters, int action)
			throws EJBException;

	public ResultOutTO cat_articles_mtto(ArticlesTO parameters, int action)
			throws EJBException;

	public int cat_branch_mtto(BranchTO parameters, int action)
			throws EJBException;

	public List getArticles(ArticlesInTO parameters) throws EJBException;

	public ArticlesTO getArticlesByKey(String itemcode) throws EJBException;

	public List getBranch(String whscode, String whsname) throws EJBException;

	public BranchTO getBranchByKey(String whscode) throws EJBException;

	/* Mantenimeinto de listas de precios */

	public ResultOutTO cat_prl0_priceslist_mtto(PricesListTO parameters, int action, Boolean UdpDetail)
			throws EJBException;

	public List getPricesList(PricesListInTO parameters) throws EJBException;

	public PricesListTO getPricesListByKey(int listnum) throws EJBException;
	
	public ResultOutTO cat_art1_articlesprice_mtto(ArticlesPriceTO parameters, int action) throws EJBException;
	
	public ResultOutTO adm_warehousejournal_mtto(WarehouseJournalTO param,int accion)throws EJBException;
	
	public WarehouseJournalTO getWarehouseJournalByKey(int transseq) throws EJBException;
	
	public List findCatQS(String nameCatalog) throws Exception;
}

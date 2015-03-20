package com.sifcoapp.client;

import java.util.List;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.admin.ejb.AdminEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesPriceTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.admin.to.PricesListInTO;
import com.sifcoapp.objects.admin.to.PricesListTO;
import com.sifcoapp.objects.admin.to.WarehouseJournalTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

public class AdminEJBClient {
	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/AdminEJB!com.sifcoapp.admin.ejb.AdminEJBRemote";
	private static AdminEJBRemote bean;
	private static Context context = null;

	public AdminEJBClient() {

		// 2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			bean = (AdminEJBRemote) context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public EnterpriseOutTO saveEnterprise(EnterpriseTO parameters)
			throws Exception {

		EnterpriseOutTO enterpriseOutTO = null;
		// try{
		enterpriseOutTO = bean.saveEnterprise(parameters);
		// }catch(EJBException ex){
		// throw new Exception(ex.getMessage());
		// }

		return enterpriseOutTO;
	}

	public EnterpriseTO getEnterpriseInfo() throws Exception {

		EnterpriseTO enterpriseTO;

		enterpriseTO = bean.getEnterpriseInfo();

		return enterpriseTO;

	}

	public List findCatalog(String nameCatalog) throws Exception {
		List catlgLst = null;

		catlgLst = bean.findCatalog(nameCatalog);

		return catlgLst;
	}

	public CatalogTO findCatalogByKey(String catcode, int tablecode)
			throws Exception {
		CatalogTO _return = new CatalogTO();

		_return = bean.findCatalogByKey(catcode, tablecode);

		return _return;
	}

	/**
	 * Obtiene los registros del catalogo de tablas del sistema
	 * 
	 * @author Rutilio
	 */
	public List getTablesCatalog() throws Exception {
		List _return = null;

		_return = bean.getTablesCatalog();

		return _return;
	}

	/*
	 * Mantenimiento de Catalogos
	 */
	public int cat_tab1_catalogos_mtto(CatalogTO parameters, int action)
			throws Exception {
		int _return = 0;

		_return = bean.cat_tab1_catalogos_mtto(parameters, action);

		return _return;

	}

	/*
	 * Mantenimiento de Articulos
	 */
	public ResultOutTO cat_articles_mtto(ArticlesTO parameters, int action)
			throws EJBException {
		ResultOutTO _return = new ResultOutTO();

		_return = bean.cat_articles_mtto(parameters, action);

		return _return;

	}

	/*
	 * Mantenimiento de almacenes
	 */
	public int cat_branch_mtto(BranchTO parameters, int action)
			throws Exception {
		int _return = 0;

		_return = bean.cat_branch_mtto(parameters, action);

		return _return;

	}

	public List getArticles(ArticlesInTO parameters) throws Exception {
		List _return;

		_return = bean.getArticles(parameters);

		return _return;
	}

	public ArticlesTO getArticlesByKey(String itemcode) throws Exception {
		ArticlesTO _return;

		_return = bean.getArticlesByKey(itemcode);

		return _return;
	}

	public List getBranch(String whscode, String whsname) throws Exception {
		List _return;

		_return = bean.getBranch(whscode, whsname);

		return _return;
	}

	public BranchTO getBranchByKey(String whscode) throws Exception {
		BranchTO _return;

		_return = bean.getBranchByKey(whscode);

		return _return;
	}

	/* Listas de precios */

	public ResultOutTO cat_prl0_priceslist_mtto(PricesListTO parameters, int action, Boolean UdpDetail) {
		ResultOutTO _return;

		_return = bean.cat_prl0_priceslist_mtto(parameters, action, UdpDetail);

		return _return;
	}

	public List getPricesList(PricesListInTO parameters) throws Exception {
		List _return;

		_return = bean.getPricesList(parameters);

		return _return;
	}

	public PricesListTO getPricesListByKey(int listnum) throws Exception {
		PricesListTO _return;

		_return = bean.getPricesListByKey(listnum);

		return _return;
	}
	public ResultOutTO adm_warehousejournal_mtto(WarehouseJournalTO param,int accion)throws EJBException{
		ResultOutTO _return = new ResultOutTO();
		_return= bean.adm_warehousejournal_mtto(param, accion);
		return _return;
	}
	
	public WarehouseJournalTO getWarehouseJournalByKey(int transseq) throws EJBException{
		WarehouseJournalTO _return = new WarehouseJournalTO();
		_return=bean.getWarehouseJournalByKey(transseq);
		return _return;
	}
	/*
	 * busqueda de catalogos por query string
	 * Rutilio Iraheta
	 * Marzo 2015
	 * */
	public List findCatQS(String nameCatalog) throws Exception {
		List catlgLst = null;

		catlgLst = bean.findCatQS(nameCatalog);

		return catlgLst;
	}
}

package com.sifcoapp.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.admin.ejb.AdminEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;

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

	public EnterpriseOutTO saveEnterprise(EnterpriseTO parameters) {

		EnterpriseOutTO enterpriseOutTO = null;

		enterpriseOutTO = bean.saveEnterprise(parameters);

		return enterpriseOutTO;
	}

	public EnterpriseTO getEnterpriseInfo() {

		EnterpriseTO enterpriseTO;

		enterpriseTO = bean.getEnterpriseInfo();

		return enterpriseTO;

	}

	public List findCatalog(String nameCatalog) {
		List catlgLst = null;

		catlgLst = bean.findCatalog(nameCatalog);

		return catlgLst;
	}

	/**
	 * Obtiene los registros del catalogo de tablas del sistema
	 * 
	 * @author Rutilio
	 */
	public List getTablesCatalog() {
		List _return = null;

		_return = bean.getTablesCatalog();

		return _return;
	}

	/*
	 * Mantenimiento de Catalogos
	 */
	public int cat_tab1_catalogos_mtto(CatalogTO parameters, int action) {
		int _return = 0;

		_return = bean.cat_tab1_catalogos_mtto(parameters, action);

		return _return;

	}

	/*
	 * Mantenimiento de Articulos
	 */
	public int cat_articles_mtto(ArticlesTO parameters, int action) {
		int _return = 0;

		_return = bean.cat_articles_mtto(parameters, action);

		return _return;

	}

	/*
	 * Mantenimiento de Articulos
	 */
	public int cat_brancharticles_mtto(BranchArticlesTO parameters, int action) {
		int _return = 0;

		_return = bean.cat_brancharticles_mtto(parameters, action);

		return _return;

	}
	
	public List getArticles(String itemcode, String itemname ) {
		List _return;

		_return = bean.getArticles(itemcode, itemname);

		return _return;
	}
	
	public ArticlesTO getArticlesByKey(String itemcode) {
		ArticlesTO _return;

		_return = bean.getArticlesByKey(itemcode);

		return _return;
	}
	
}

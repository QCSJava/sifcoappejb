package com.sifcoapp.admin.ejb;
//fyrtyrtyrtyrt
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;

import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.catalogos.Common;

/**
 * Session Bean implementation class AdminEJB
 */
@Stateless
public class AdminEJB implements AdminEJBRemote {

	/**
	 * Default constructor.
	 */
	public AdminEJB() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Guarda los datos de la empresa
	 * 
	 * @author Rutilio Iraheta
	 * 
	 * @date 02/12/2014
	 * 
	 * @see
	 * com.sifcoapp.admin.ejb.AdminEJBRemote#saveEnterprise(com.sifcoapp.objects
	 * .admin.to.EnterpriseTO)
	 */
	public EnterpriseOutTO saveEnterprise(EnterpriseTO parameters) {

		EnterpriseOutTO enterpriseOutTO = new EnterpriseOutTO();

		int _return = 0;
		AdminDAO adminDAO = new AdminDAO();

		_return = adminDAO.updEnterprise(parameters);

		enterpriseOutTO.setRespCode(_return);

		return enterpriseOutTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sifcoapp.admin.ejb.AdminEJBRemote#saveEnterprise(com.sifcoapp.objects
	 * .admin.to.EnterpriseTO)
	 */
	public EnterpriseTO getEnterpriseInfo(int enterpriseCode) {

		EnterpriseTO enterpriseOutTO = null;

		AdminDAO adminDAO = new AdminDAO();

		enterpriseOutTO = adminDAO.getEnterpriseInfo(enterpriseCode);

		return enterpriseOutTO;
	}

	public EnterpriseTO getEnterpriseInfo() {
		// TODO Auto-generated method stub
		EnterpriseTO enterpriseOutTO = new EnterpriseTO();
		enterpriseOutTO = this.getEnterpriseInfo(0);
		return enterpriseOutTO;
	}

	/*
	 * busca un catalogo especifico
	 * 
	 * @see com.sifcoapp.admin.ejb.AdminEJBRemote#findCatalog(java.lang.String)
	 */
	public List findCatalog(String nameCatalog) {
		// TODO Auto-generated method stub
		// List catlgLst=new Vector();
		List catlgLst = null;

		AdminDAO adminDAO = new AdminDAO();

		catlgLst = adminDAO.findCatalog(nameCatalog);

		return catlgLst;
	}

	/*
	 * Obtiene los registros del catalogo de tablas del sistema
	 * 
	 * @author Rutilio
	 */
	public List getTablesCatalog() {
		List _return = null;

		AdminDAO adminDAO = new AdminDAO();

		_return = adminDAO.getTablesCatalog();

		return _return;
	}

	/*
	 * Mantenimiento de Catalogos
	 */
	public int cat_tab1_catalogos_mtto(CatalogTO parameters, int action) {

		int _return;

		AdminDAO adminDAO = new AdminDAO();
		_return = adminDAO.cat_tab1_catalogos_mtto(parameters, action);

		return _return;
	}

	/*
	 * Mantenimiento de Articulos
	 */
	public int cat_articles_mtto(ArticlesTO parameters, int action) {

		int _return;

		Iterator<BranchArticlesTO> iterator = parameters.getBranchArticles().iterator();

		while (iterator.hasNext()) {
			BranchArticlesTO branch = (BranchArticlesTO) iterator.next();
			// Para articulos nuevos
			AdminDAO adminDAO1 = new AdminDAO();
			if (action == Common.MTTOINSERT && branch.isIsasociated()) {
				adminDAO1.cat_brancharticles_mtto(branch, action);
			}
			if (action == Common.MTTOUPDATE) {
				if (branch.isIsasociated()) {
					adminDAO1
							.cat_brancharticles_mtto(branch, Common.MTTOINSERT);
				} else {
					adminDAO1
							.cat_brancharticles_mtto(branch, Common.MTTODELETE);
				}
			}
			if (action == Common.MTTODELETE) {
				adminDAO1.cat_brancharticles_mtto(branch, Common.MTTODELETE);
			}
		}

		AdminDAO adminDAO = new AdminDAO();
		_return = adminDAO.cat_articles_mtto(parameters, action);

		return _return;
	}

	/*
	 * Mantenimiento de alamacenes de Articulos
	 */
	/*
	 * public int cat_brancharticles_mtto(BranchArticlesTO parameters, int
	 * action) {
	 * 
	 * int _return;
	 * 
	 * AdminDAO adminDAO = new AdminDAO(); _return =
	 * adminDAO.cat_brancharticles_mtto(parameters, action);
	 * 
	 * return _return; }
	 */

	public List getArticles(String itemcode, String itemname) {

		List _return;

		AdminDAO adminDAO = new AdminDAO();
		_return = adminDAO.getArticles(itemcode, itemname);

		return _return;
	}

	public ArticlesTO getArticlesByKey(String itemcode) {
		ArticlesTO _return;

		AdminDAO adminDAO = new AdminDAO();

		// para el manejo de transacciones
		adminDAO.setIstransaccional(true);
		_return = adminDAO.getArticlesByKey(itemcode);

		// adminDAO.forceCommit();
		adminDAO.forceCloseConnection();

		return _return;
	}

	/*
	 * Mantenimiento de almacenes
	 */
	public int cat_branch_mtto(BranchTO parameters, int action) {
		// TODO Auto-generated method stub
		int _return;

		AdminDAO adminDAO = new AdminDAO();
		_return = adminDAO.cat_branch_mtto(parameters, action);

		return _return;
	}

	public List getBranch(String whscode, String whsname) {
		// TODO Auto-generated method stub

		List _return;

		AdminDAO adminDAO = new AdminDAO();
		_return = adminDAO.getBranch(whscode, whsname);

		return _return;
	}

	public BranchTO getBranchByKey(String whscode) {
		// TODO Auto-generated method stub
		BranchTO _return;

		AdminDAO adminDAO = new AdminDAO();
		_return = adminDAO.getBranchByKey(whscode);

		return _return;
	}
}

package com.sifcoapp.admin.ejb;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;

import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.to.AccPeriodTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
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

		for (BranchArticlesTO branch : parameters.getBranchArticles()) {
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
	public int cat_brancharticles_mtto(BranchArticlesTO parameters, int action) {

		int _return;

		AdminDAO adminDAO = new AdminDAO();
		_return = adminDAO.cat_brancharticles_mtto(parameters, action);

		return _return;
	}

	public int cat_accPeriod_mtto(int parameters, int usersign, int action) {

		int _return = 0;

		// Dividir el año en 12 periodos y crear objeto

		/*
		 * Agregar validadiones - Se haran desde la base - Que no este creado el
		 * año - Que el año sea mayor al actual
		 */
		for (int i = 1; i <= 12; i++) {

			AccPeriodTO periodo = new AccPeriodTO();
			periodo.setAcccode(Integer.toString(i));
			periodo.setAccname(Integer.toString(parameters)	+ String.format("%02d", i));			
			periodo.setF_duedate(Common.getPrimerDiaDelMes(parameters, i));
			periodo.setF_refdate(Common.getPrimerDiaDelMes(parameters, i));
			periodo.setF_taxdate(Common.getPrimerDiaDelMes(parameters, i));
			periodo.setPeriodstat(1);
			periodo.setT_duedate(Common.getUltimoDiaDelMes(parameters, i));
			periodo.setT_refdate(Common.getUltimoDiaDelMes(parameters, i));
			periodo.setT_taxdate(Common.getUltimoDiaDelMes(parameters, i));
			periodo.setUsersign(usersign);

			AdminDAO adminDAO = new AdminDAO();
			_return = adminDAO.cat_accPeriod_mtto(periodo, action);

		}
		return _return;
	}
}

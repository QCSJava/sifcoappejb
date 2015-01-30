package com.sifcoapp.admin.ejb;
//fyrtyrtyrtyrt

import java.util.Iterator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
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
	public EnterpriseOutTO saveEnterprise(EnterpriseTO parameters) throws EJBException {

		EnterpriseOutTO enterpriseOutTO = new EnterpriseOutTO();

		int _return = 0;
		AdminDAO adminDAO = new AdminDAO();

		try {
			_return = adminDAO.updEnterprise(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw (EJBException) new EJBException(e);
		}

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
	public EnterpriseTO getEnterpriseInfo(int enterpriseCode) throws EJBException{

		EnterpriseTO enterpriseOutTO = null;

		AdminDAO adminDAO = new AdminDAO();

		try {
			enterpriseOutTO = adminDAO.getEnterpriseInfo(enterpriseCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return enterpriseOutTO;
	}

	public EnterpriseTO getEnterpriseInfo() throws EJBException{
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
	public List findCatalog(String nameCatalog) throws EJBException{
		// TODO Auto-generated method stub
		// List catlgLst=new Vector();
		List catlgLst = null;

		AdminDAO adminDAO = new AdminDAO();

		try {
			catlgLst = adminDAO.findCatalog(nameCatalog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return catlgLst;
	}

	/*
	 * Obtiene los registros del catalogo de tablas del sistema
	 * 
	 * @author Rutilio
	 */
	public List getTablesCatalog() throws EJBException{
		List _return = null;

		AdminDAO adminDAO = new AdminDAO();

		try {
			_return = adminDAO.getTablesCatalog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	/*
	 * Mantenimiento de Catalogos
	 */
	public int cat_tab1_catalogos_mtto(CatalogTO parameters, int action) throws EJBException{

		int _return=0;

		AdminDAO adminDAO = new AdminDAO();
		try {
			_return = adminDAO.cat_tab1_catalogos_mtto(parameters, action);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	/*
	 * Mantenimiento de Articulos
	 */
	public int cat_articles_mtto(ArticlesTO parameters, int action) throws EJBException{

		int _return = 0;

		Iterator<BranchArticlesTO> iterator = parameters.getBranchArticles().iterator();
		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
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

	public List getArticles(String itemcode, String itemname) throws EJBException{

		List _return = null;

		AdminDAO adminDAO = new AdminDAO();
		try {
			_return = adminDAO.getArticles(itemcode, itemname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ArticlesTO getArticlesByKey(String itemcode) throws EJBException{
		ArticlesTO _return = null;

		AdminDAO adminDAO = new AdminDAO();

		// para el manejo de transacciones
		adminDAO.setIstransaccional(true);
		try {
			_return = adminDAO.getArticlesByKey(itemcode);
			adminDAO.forceCloseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		// adminDAO.forceCommit();
		adminDAO.forceCloseConnection();

		return _return;
	}

	/*
	 * Mantenimiento de almacenes
	 */
	public int cat_branch_mtto(BranchTO parameters, int action) throws EJBException{
		// TODO Auto-generated method stub
		int _return = 0;

		AdminDAO adminDAO = new AdminDAO();
		try {
			_return = adminDAO.cat_branch_mtto(parameters, action);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public List getBranch(String whscode, String whsname) throws EJBException{
		// TODO Auto-generated method stub

		List _return = null;

		AdminDAO adminDAO = new AdminDAO();
		try {
			_return = adminDAO.getBranch(whscode, whsname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public BranchTO getBranchByKey(String whscode) throws EJBException{
		// TODO Auto-generated method stub
		BranchTO _return = null;

		AdminDAO adminDAO = new AdminDAO();
		try {
			_return = adminDAO.getBranchByKey(whscode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}
}

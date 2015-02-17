package com.sifcoapp.admin.ejb;

//fyrtyrtyrtyrt

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.admin.to.PricesListInTO;
import com.sifcoapp.objects.admin.to.PricesListTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.TransfersDAO;
import com.sifcoapp.objects.inventory.dao.TransfersDetailDAO;
import com.sifcoapp.objects.inventory.to.TransfersDetailTO;

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
	public EnterpriseOutTO saveEnterprise(EnterpriseTO parameters)
			throws EJBException {

		EnterpriseOutTO enterpriseOutTO = new EnterpriseOutTO();

		int _return = 0;
		AdminDAO adminDAO = new AdminDAO();

		try {
			_return = adminDAO.updEnterprise(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
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
	public EnterpriseTO getEnterpriseInfo(int enterpriseCode)
			throws EJBException {

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

	public EnterpriseTO getEnterpriseInfo() throws EJBException {
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
	public List findCatalog(String nameCatalog) throws EJBException {
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

	public CatalogTO findCatalogByKey(String catcode, int tablecode)
			throws EJBException {

		CatalogTO _return = new CatalogTO();
		AdminDAO adminDAO = new AdminDAO();

		try {
			_return = adminDAO.findCatalogByKey(catcode, tablecode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	/*
	 * Obtiene los registros del catalogo de tablas del sistema
	 * 
	 * @author Rutilio
	 */
	public List getTablesCatalog() throws EJBException {
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
	public int cat_tab1_catalogos_mtto(CatalogTO parameters, int action)
			throws EJBException {

		int _return = 0;

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
	public ResultOutTO cat_articles_mtto(ArticlesTO parameters, int action)
			throws EJBException {
		ResultOutTO _return = new ResultOutTO();

		AdminDAO adminDAO = new AdminDAO();
		adminDAO.setIstransaccional(true);
		Iterator<BranchArticlesTO> iterator = parameters.getBranchArticles()
				.iterator();

		try {

			List<BranchArticlesTO> consult = new Vector<BranchArticlesTO>();
			consult = adminDAO.getBranchArticles(parameters.getItemCode());
			Iterator<BranchArticlesTO> iterator2 = consult.iterator();
			while (iterator.hasNext()) {
				BranchArticlesTO branch = (BranchArticlesTO) iterator.next();
				// Para articulos nuevos
				// ############################ VALORES QUEMADOS
				// ###################################
				branch.setOnhand(0.0);
				branch.setOnhand1(0.0);
				branch.setIscommited(0.0);
				branch.setOnorder(0.0);
				branch.setMinorder(0.0);
				if (branch.getMinstock() == null) {
					branch.setMinstock(0.0);
				}
				if (branch.getMaxstock() == null) {
					branch.setMaxstock(0.0);
				}
				if (action == Common.MTTOINSERT && branch.isIsasociated()) {

					adminDAO.cat_brancharticles_mtto(branch, action);

				}
				if (action == Common.MTTOUPDATE) {
					if (branch.isIsasociated()) {
						int update = 0;
						iterator2 = consult.iterator();
						while (iterator2.hasNext()) {
							BranchArticlesTO branch2 = (BranchArticlesTO) iterator2
									.next();
							if (branch2.getWhscode()
									.equals(branch.getWhscode())) {
								adminDAO.cat_brancharticles_mtto(branch,
										Common.MTTOUPDATE);
								update = 1;
							}
						}
						if (update == 0) {
							adminDAO.cat_brancharticles_mtto(branch,
									Common.MTTOINSERT);
						}

					} else {
						adminDAO.cat_brancharticles_mtto(branch,
								Common.MTTODELETE);
					}
				}
				if (action == Common.MTTODELETE) {
					adminDAO.cat_brancharticles_mtto(branch, Common.MTTODELETE);
				}
			}
			// ############################ VALORES QUEMADOS
			// ###################################
			parameters.setNumInBuy(0.0);
			parameters.setSalPackUn(0.0);
			parameters.setPurPackUn(0.0);
			parameters.setAvgPrice(0.0);
			parameters.setOnHand(0.0);
			parameters.setNumInSale(0.0);

			adminDAO.cat_articles_mtto(parameters, action);
			adminDAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			adminDAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			adminDAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
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

	public List getArticles(ArticlesInTO parameters) throws EJBException {

		List _return = null;

		AdminDAO adminDAO = new AdminDAO();
		try {
			_return = adminDAO.getArticles(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ArticlesTO getArticlesByKey(String itemcode) throws EJBException {
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
	public int cat_branch_mtto(BranchTO parameters, int action)
			throws EJBException {
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

	public List getBranch(String whscode, String whsname) throws EJBException {
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

	public BranchTO getBranchByKey(String whscode) throws EJBException {
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

	/* Mantenimiento de listas de precios */
	public ResultOutTO cat_prl0_priceslist_mtto(PricesListTO parameters,
			int action) throws EJBException {

		// TODO Auto-generated method stub

		ResultOutTO _return = new ResultOutTO();
		AdminDAO adminDAO = new AdminDAO();

		adminDAO.setIstransaccional(true);

		try {
			// Primero guardar la lista
			// int id = adminDAO.cat_prl0_priceslist_mtto(parameters, action);

			// Consultar la lista de articulos registrados en la base de datos

			ArticlesInTO parametro = new ArticlesInTO();
			List articles = adminDAO.getArticles(parametro);

			Iterator<ArticlesTO> iterator2 = articles.iterator();
			while (iterator2.hasNext()) {
				ArticlesTO articleDetalle = (ArticlesTO) iterator2.next();
				// Calcular el precio para cada articulo

			}
			int id = -1;

			if (action == Common.MTTOINSERT) {
				id = adminDAO.cat_prl0_priceslist_mtto(parameters, action);
				// Agregar los detalles
			}
			if (action == Common.MTTOUPDATE) {
				id = adminDAO.cat_prl0_priceslist_mtto(parameters, action);
				// Borrar los detalles de la lista anterior e ingresarlos todos
				// nuevamente ya con los precios actualizados
			}
			if (action == Common.MTTODELETE) {
				id = adminDAO.cat_prl0_priceslist_mtto(parameters, action);
				// Borrar los detalles
			}

			adminDAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			adminDAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			adminDAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public List getPricesList(PricesListInTO getPricesList) throws EJBException {
		// TODO Auto-generated method stub

		List _return = null;

		AdminDAO adminDAO = new AdminDAO();
		try {
			_return = adminDAO.getPricesList(getPricesList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public PricesListTO getPricesListByKey(int listnum) throws EJBException {
		// TODO Auto-generated method stub
		PricesListTO _return = null;

		AdminDAO adminDAO = new AdminDAO();
		try {
			_return = adminDAO.getPricesListByKey(listnum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

}

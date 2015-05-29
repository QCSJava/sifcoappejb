package com.sifcoapp.admin.ejb;

//fyrtyrtyrtyrt

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.admin.dao.AdminDAO;
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
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.TransfersDAO;
import com.sifcoapp.objects.inventory.dao.TransfersDetailDAO;
import com.sifcoapp.objects.inventory.to.TransfersDetailTO;
import com.sifcoapp.objects.transaction.to.WarehouseJournalLayerTO;
import com.sifcoapp.objects.transaction.to.WarehouseJournalTO;

/**
 * Session Bean implementation class AdminEJB
 */
@Stateless
public class AdminEJB implements AdminEJBRemote {
	Double zero = 0.00;

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
		AdminDAO adminDAO2 = new AdminDAO();
		adminDAO2.setIstransaccional(true);
		Iterator<BranchArticlesTO> iterator = parameters.getBranchArticles()
				.iterator();
		List<BranchArticlesTO> consult = new Vector<BranchArticlesTO>();

		try {
			consult = adminDAO2.getBranchArticles(parameters.getItemCode());

			while (iterator.hasNext()) {

				BranchArticlesTO branch = (BranchArticlesTO) iterator.next();

				// Para articulos nuevos
				// ############################ VALORES QUEMADOS
				// ###################################
				if (branch.getOnhand() == null) {
					branch.setOnhand(zero);
				}
				if (branch.getOnhand1() == null) {
					branch.setOnhand1(zero);
				}
				if (branch.getIscommited() == null) {
					branch.setIscommited(zero);
				}
				if (branch.getOnorder() == null) {
					branch.setOnorder(zero);
				}
				if (branch.getMinorder() == null) {
					branch.setMinorder(zero);
				}
				if (branch.getMinstock() == null) {
					branch.setMinstock(zero);
				}
				if (branch.getMaxstock() == null) {
					branch.setMaxstock(zero);
				}
				if (action == Common.MTTOINSERT && branch.isIsasociated()) {

					adminDAO2.cat_brancharticles_mtto(branch, action);

				}
				if (action == Common.MTTOUPDATE) {
					if (branch.isIsasociated()) {
						int update = 0;
						Iterator<BranchArticlesTO> iterator2 = consult
								.iterator();

						while (iterator2.hasNext()) {
							BranchArticlesTO branch2 = (BranchArticlesTO) iterator2
									.next();
							if (branch2.getWhscode()
									.equals(branch.getWhscode())
									&& branch2.isIsasociated()) {
								adminDAO2.cat_brancharticles_mtto(branch,
										Common.MTTOUPDATE);
								update = 1;
							}
						}
						if (update == 0) {
							adminDAO2.cat_brancharticles_mtto(branch,
									Common.MTTOINSERT);
						}

					} else {
						adminDAO2.cat_brancharticles_mtto(branch,
								Common.MTTODELETE);
					}
				}
				if (action == Common.MTTODELETE) {
					adminDAO2
							.cat_brancharticles_mtto(branch, Common.MTTODELETE);
				}

			}

			// ############################ VALORES QUEMADOS
			// ###################################
			if (parameters.getNumInBuy() == null) {
				parameters.setNumInBuy(zero);
			}
			if (parameters.getSalPackUn() == null) {
				parameters.setSalPackUn(zero);
			}
			if (parameters.getAvgPrice() == null) {
				parameters.setAvgPrice(zero);
			}
			if (parameters.getNumInSale() == null) {
				parameters.setNumInSale(zero);
			}
			if (parameters.getPurPackUn() == null) {
				parameters.setPurPackUn(zero);
			}
			if (parameters.getOnHand() == null) {
				parameters.setOnHand(zero);
			}
			
			ArticlesPriceTO art = new ArticlesPriceTO();
			ArticlesPriceTO art2 = new ArticlesPriceTO();
			art.setItemcode(parameters.getItemCode());
			art.setPrice(0.0);
			art.setPricelist(1);
			art.setFactor(0.0);
			art.setOvrwritten(true);
			cat_art1_articlesprice_mtto(art, 1);
			art2.setItemcode(parameters.getItemCode());
			art2.setPrice(0.0);
			art2.setPricelist(1);
			art2.setFactor(0.0);
			art2.setOvrwritten(true);
			cat_art1_articlesprice_mtto(art2, 1);

			adminDAO.cat_articles_mtto(parameters, action);
			adminDAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			adminDAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {
			adminDAO2.forceCloseConnection();
			adminDAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	/*
	 * Mantenimiento de alamacenes de Articulos
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
		} finally {
			// adminDAO.forceCommit();
			adminDAO.forceCloseConnection();
		}
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
			int action, Boolean UdpDetail) throws EJBException {

		// TODO Auto-generated method stub
		int idlist = -1;
		ResultOutTO _return = new ResultOutTO();
		AdminDAO adminDAO = new AdminDAO();

		adminDAO.setIstransaccional(true);

		try {

			// Realizamos la acción indica por el usuario para el encabezado
			idlist = adminDAO.cat_prl0_priceslist_mtto(parameters, action);

			// Consultar la lista base para crear la nueva
			AdminDAO adminDAO2 = new AdminDAO(adminDAO.getConn());
			adminDAO2.setIstransaccional(true);

			// TODO:Se crear otra instacia de AdminDAO, porque en la primera ya
			// quedan seteados los parametros y por eso da error
			AdminDAO adminDAO3 = new AdminDAO(adminDAO.getConn());
			adminDAO3.setIstransaccional(true);

			if (action == Common.MTTOINSERT) {

				PricesListTO Baselist = adminDAO2.getPricesListByKey(parameters
						.getBase_num());

				// Insertamos detalles en base a lista base
				Iterator<ArticlesPriceTO> iterator = Baselist
						.getArticlesPrices().iterator();
				while (iterator.hasNext()) {
					ArticlesPriceTO newPrice = new ArticlesPriceTO();
					ArticlesPriceTO baseprice = (ArticlesPriceTO) iterator
							.next();

					// Calcular el precio para cada articulo
					newPrice.setItemcode(baseprice.getItemcode());
					newPrice.setPricelist(idlist);
					newPrice.setFactor(parameters.getFactor());
					newPrice.setPrice(baseprice.getPrice()
							* parameters.getFactor());
					newPrice.setOvrwritten(false);
					// Valores por derfecto
					newPrice.setAddprice1(zero);
					newPrice.setAddprice2(zero);

					adminDAO3.cat_art1_articlesprice_mtto(newPrice, action);

				}
			}

			if (action == Common.MTTOUPDATE) {

				PricesListTO Baselist = adminDAO2.getPricesListByKey(parameters
						.getBase_num());

				// Validar si se actualizaran todos los detalles
				if (UdpDetail) {
					// Actualizar todo el detalle de precios en base a la lista
					// base, no modificar los indicados como handWritten
					Iterator<ArticlesPriceTO> iterator = Baselist
							.getArticlesPrices().iterator();
					while (iterator.hasNext()) {
						Boolean actualizado = false;
						ArticlesPriceTO basePrice = (ArticlesPriceTO) iterator
								.next();

						// Comparara lista base con la lista recibida
						Iterator<ArticlesPriceTO> iterator2 = parameters
								.getArticlesPrices().iterator();
						while (iterator2.hasNext()) {
							ArticlesPriceTO NowPrice = (ArticlesPriceTO) iterator2
									.next();

							if (NowPrice.getItemcode().equals(
									basePrice.getItemcode())) {
								actualizado = true;

								NowPrice.setFactor(parameters.getFactor());
								if (!NowPrice.getOvrwritten()) {
									NowPrice.setPrice(basePrice.getPrice()
											* parameters.getFactor());
								}
								adminDAO3.cat_art1_articlesprice_mtto(NowPrice,
										Common.MTTOUPDATE);
								break;
							}
						}
						// Si no lo encontro se agregara a la colección
						if (!actualizado) {
							ArticlesPriceTO newPrice = new ArticlesPriceTO();

							// Calcular el precio para cada articulo
							newPrice.setItemcode(basePrice.getItemcode());
							newPrice.setPricelist(parameters.getListnum());
							newPrice.setFactor(parameters.getFactor());
							newPrice.setPrice(basePrice.getPrice()
									* parameters.getFactor());
							newPrice.setOvrwritten(false);
							// Valores por derfecto
							newPrice.setAddprice1(zero);
							newPrice.setAddprice2(zero);

							adminDAO3.cat_art1_articlesprice_mtto(newPrice,
									Common.MTTOINSERT);

						}
					}
				}
			}

			if (action == Common.MTTODELETE) {

				// Borrar los detalles
				adminDAO3
						.cat_art1_articlesprice_delete(parameters.getListnum());
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
		adminDAO.setIstransaccional(true);
		try {
			_return = adminDAO.getPricesListByKey(listnum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			adminDAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			adminDAO.forceCloseConnection();
		}
		return _return;
	}

	public ResultOutTO cat_art1_articlesprice_mtto(ArticlesPriceTO parameters,
			int action) throws EJBException {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();

		AdminDAO adminDAO = new AdminDAO();
		adminDAO.setIstransaccional(true);

		try {
			adminDAO.cat_art1_articlesprice_mtto(parameters, action);

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

	public List findCatQS(String nameCatalog) throws EJBException {
		// TODO Auto-generated method stub
		List catlgLst = null;

		AdminDAO adminDAO = new AdminDAO();

		try {
			catlgLst = adminDAO.findCatQS(nameCatalog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return catlgLst;
	}

	public ResultOutTO validate_branchActiv(String whscode) throws EJBException {

		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();
		BranchTO _branch = null;

		AdminDAO adminDAO = new AdminDAO();
		try {
			_branch = adminDAO.getBranchByKey(whscode);
			if (_branch.equals(null)) {
				_return.setCodigoError(1);
				_return.setMensaje("No se encontro Ningun Almacen con Este codigo ");
				return _return;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		if (_branch.isLocked()) {
			_return.setCodigoError(1);
			_return.setMensaje("Almacen se encuentra Bloqueado");
			return _return;
		}

		_return.setCodigoError(0);
		_return.setMensaje("Almacen Activo");
		return _return;

	}
}

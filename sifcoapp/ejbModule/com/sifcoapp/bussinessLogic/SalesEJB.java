package com.sifcoapp.bussinessLogic;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.sales.DAO.*;
import com.sifcoapp.objects.sales.to.*;
import com.sun.org.apache.regexp.internal.RESyntaxException;

/**
 * Session Bean implementation class SalesEJB
 */
@Stateless
public class SalesEJB implements SalesEJBRemote {

	/**
	 * Default constructor.
	 */
	public SalesEJB() {
		// TODO Auto-generated constructor stub
	}

	public List getSales(SalesInTO param) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		SalesDAO DAO = new SalesDAO();

		try {
			_return = DAO.getSales(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public SalesTO getSalesByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		SalesTO _return = new SalesTO();
		SalesDAO DAO = new SalesDAO();
		try {
			_return = DAO.getSalesByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO inv_Sales_mtto(SalesTO parameters, int action)
			throws Exception {

		// TODO Auto-generated method stub

		ResultOutTO _return = new ResultOutTO();
		// Double total = 0.0;
		SalesDAO DAO = new SalesDAO();
		DAO.setIstransaccional(true);
		SalesDetailDAO goodDAO1 = new SalesDetailDAO(DAO.getConn());
		goodDAO1.setIstransaccional(true);
		try {
			Iterator<SalesDetailTO> iterator2 = parameters.getSalesDetails()
					.iterator();
			while (iterator2.hasNext()) {
				SalesDetailTO articleDetalle = (SalesDetailTO) iterator2.next();
				// articleDetalle.setLinetotal(articleDetalle.getQuantity()*
				// articleDetalle.getPrice());
				articleDetalle.setDiscprcnt(articleDetalle.getQuantity()); // ############//
																			// DATOS//
																			// ESTATICOS//
																			// ##########
				articleDetalle.setOpenqty(articleDetalle.getQuantity());
				// articleDetalle.setPricebefdi(articleDetalle.getPrice());
				// articleDetalle.setPriceafvat(articleDetalle.getPrice());
				articleDetalle.setFactor1(articleDetalle.getQuantity());
				// articleDetalle.setVatsum(articleDetalle.getPrice());
				// articleDetalle.setGrssprofit(articleDetalle.getPrice());
				// articleDetalle.setVatappld(articleDetalle.getPrice());
				// articleDetalle.setStockpricestockprice(articleDetalle.getPrice());
				// articleDetalle.setGtotal(articleDetalle.getQuantity());
				// total = total + articleDetalle.getLinetotal();
			}
			// parameters.setDoctotal(total);
			parameters.setDiscsum(0.00); // /////////############ DATOS QUEMADOS
											// #######################
			parameters.setNret(0.00);
			parameters.setPaidsum(0.00);
			parameters.setRounddif(0.00);
			_return.setDocentry(DAO.inv_Sales_mtto(parameters, action));

			Iterator<SalesDetailTO> iterator = parameters.getSalesDetails()
					.iterator();
			while (iterator.hasNext()) {
				SalesDetailTO articleDetalle = (SalesDetailTO) iterator.next();
				// Para articulos nuevos
				articleDetalle.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					goodDAO1.inv_SalesDetail_mtto(articleDetalle,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					goodDAO1.inv_SalesDetail_mtto(articleDetalle,
							Common.MTTODELETE);
				}
			}
			DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}

		return _return;
	}

	public List getSalesDetail(int docentry) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		SalesDetailDAO DAO = new SalesDetailDAO();

		try {
			_return = DAO.getSalesDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public List getClientCredi(ClientCrediInTO param) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		ClientCrediDAO DAO = new ClientCrediDAO();

		try {
			_return = DAO.getClientCredi(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public ClientCrediTO getClientCrediByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		ClientCrediTO _return = new ClientCrediTO();
		ClientCrediDAO DAO = new ClientCrediDAO();
		try {
			_return = DAO.getClientCrediByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO inv_ClientCredi_mtto(ClientCrediTO parameters, int action)
			throws Exception {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();
		// Double total = 0.0;
		ClientCrediDAO DAO = new ClientCrediDAO();
		DAO.setIstransaccional(true);
		ClientCrediDetailDAO goodDAO1 = new ClientCrediDetailDAO(DAO.getConn());
		goodDAO1.setIstransaccional(true);
		try {
			Iterator<ClientCrediDetailTO> iterator2 = parameters
					.getclientDetails().iterator();
			while (iterator2.hasNext()) {
				ClientCrediDetailTO articleDetalle = (ClientCrediDetailTO) iterator2
						.next();
				// articleDetalle.setLinetotal(articleDetalle.getQuantity()*
				// articleDetalle.getPrice());
				articleDetalle.setDiscprcnt(articleDetalle.getQuantity()); // ############//
																			// DATOS//
																			// ESTATICOS//
																			// ##########
				articleDetalle.setOpenqty(articleDetalle.getQuantity());
				// articleDetalle.setPricebefdi(articleDetalle.getPrice());
				// articleDetalle.setPriceafvat(articleDetalle.getPrice());
				articleDetalle.setFactor1(articleDetalle.getQuantity());
				// articleDetalle.setVatsum(articleDetalle.getPrice());
				// articleDetalle.setGrssprofit(articleDetalle.getPrice());
				// articleDetalle.setVatappld(articleDetalle.getPrice());
				// articleDetalle.setStockpricestockprice(articleDetalle.getPrice());
				// articleDetalle.setGtotal(articleDetalle.getQuantity());
				// total = total + articleDetalle.getLinetotal();
			}
			// parameters.setDoctotal(total);
			parameters.setDiscsum(0.00); // /////////############ DATOS QUEMADOS
											// #######################
			parameters.setNret(0.00);
			parameters.setPaidsum(0.00);
			parameters.setRounddif(0.00);
			_return.setDocentry(DAO.inv_ClientCredi_mtto(parameters, action));

			Iterator<ClientCrediDetailTO> iterator = parameters
					.getclientDetails().iterator();
			while (iterator.hasNext()) {
				ClientCrediDetailTO articleDetalle = (ClientCrediDetailTO) iterator
						.next();
				// Para articulos nuevos
				articleDetalle.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					goodDAO1.inv_ClientCrediDetail_mtto(articleDetalle,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					goodDAO1.inv_ClientCrediDetail_mtto(articleDetalle,
							Common.MTTODELETE);
				}
			}
			DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados correctamente");
		return _return;
	}

	public List getClientCrediDetail(int docentry) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		ClientCrediDetailDAO DAO = new ClientCrediDetailDAO();

		try {
			_return = DAO.getClientCrediDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public List getDelivery(DeliveryInTO param) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		DeliveryDAO DAO = new DeliveryDAO();

		try {
			_return = DAO.getDelivery(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public DeliveryTO getDeliveryByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		DeliveryTO _return = new DeliveryTO();
		DeliveryDAO DAO = new DeliveryDAO();
		try {
			_return = DAO.getDeliveryByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO inv_Delivery_mtto(DeliveryTO parameters, int action)
			throws Exception {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();
		// Double total = 0.0;
		DeliveryDAO DAO = new DeliveryDAO();
		DAO.setIstransaccional(true);
		DeliveryDetailDAO goodDAO1 = new DeliveryDetailDAO(DAO.getConn());
		goodDAO1.setIstransaccional(true);
		try {
			Iterator<DeliveryDetailTO> iterator2 = parameters
					.getDeliveryDetails().iterator();
			while (iterator2.hasNext()) {
				DeliveryDetailTO articleDetalle = (DeliveryDetailTO) iterator2
						.next();
				// articleDetalle.setLinetotal(articleDetalle.getQuantity()*
				// articleDetalle.getPrice());
				articleDetalle.setDiscprcnt(articleDetalle.getQuantity()); // ############//
																			// DATOS//
																			// ESTATICOS//
																			// ##########
				articleDetalle.setOpenqty(articleDetalle.getQuantity());
				// articleDetalle.setPricebefdi(articleDetalle.getPrice());
				// articleDetalle.setPriceafvat(articleDetalle.getPrice());
				articleDetalle.setFactor1(articleDetalle.getQuantity());
				// articleDetalle.setVatsum(articleDetalle.getPrice());
				// articleDetalle.setGrssprofit(articleDetalle.getPrice());
				// articleDetalle.setVatappld(articleDetalle.getPrice());
				// articleDetalle.setStockpricestockprice(articleDetalle.getPrice());
				// articleDetalle.setGtotal(articleDetalle.getQuantity());
				// total = total + articleDetalle.getLinetotal();
			}
			// parameters.setDoctotal(total);
			parameters.setDiscsum(0.00); // /////////############ DATOS QUEMADOS
											// #######################
			parameters.setNret(0.00);
			parameters.setPaidsum(0.00);
			parameters.setRounddif(0.00);
			_return.setDocentry(DAO.inv_Delivery_mtto(parameters, action));

			Iterator<DeliveryDetailTO> iterator = parameters
					.getDeliveryDetails().iterator();
			while (iterator.hasNext()) {
				DeliveryDetailTO articleDetalle = (DeliveryDetailTO) iterator
						.next();
				// Para articulos nuevos
				articleDetalle.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					goodDAO1.inv_DeliveryDetail_mtto(articleDetalle,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					goodDAO1.inv_DeliveryDetail_mtto(articleDetalle,
							Common.MTTODELETE);
				}
			}
			DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados correctamente");
		return _return;
	}

	private ResultOutTO validateSale(SalesTO parameters) throws EJBException {

		boolean validquantity = false;
		ResultOutTO _return = new ResultOutTO();
		Double stocks;
		List branch = new Vector();
		ArticlesTO _result = new ArticlesTO();
		AdminDAO DAO1 = new AdminDAO();
		DAO1.setIstransaccional(true);
		String code;
		// validaciones antes de guardar la venta
		Iterator<SalesDetailTO> iterator1 = parameters.getSalesDetails()
				.iterator();
		// recorre el detalle de la venta por articulo
		while (iterator1.hasNext()) {

			validquantity = false;
			SalesDetailTO articleDetalle = (SalesDetailTO) iterator1.next();
			code = articleDetalle.getItemcode();
			stocks = articleDetalle.getQuantity();
			Iterator<SalesDetailTO> iterator2 = parameters.getSalesDetails()
					.iterator();
			// recorre de nuevo el detalle comparando el primer elemento con los
			// demas
			while (iterator2.hasNext()) {
				SalesDetailTO articleDetalle2 = (SalesDetailTO) iterator1
						.next();
				if (code.equals(articleDetalle2.getItemcode())) {
					// suma los elementos encontrados del mismo codigo
					stocks += articleDetalle2.getQuantity();
				}

			}

			try {
				_result = DAO1.getinventaryArticlesByKey(code);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			branch = _result.getBranchArticles();
			double Quantity = 0.0;
			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;
				if (branch1.getWhscode().equals(articleDetalle.getWhscode())) {
					if (articleDetalle.getQuantity() <= stocks) {
						validquantity = true;
					} else {
						_return.setLinenum(articleDetalle.getLinenum());
						_return.setCodigoError(1);
						_return.setMensaje("El articulo "
								+ articleDetalle.getItemcode()
								+ " "
								+ articleDetalle.getDscription()
								+ " Reace en un Inventario Negativo en la linea :"
								+ articleDetalle.getLinenum());
					}
				}
			}
		}
		if (validquantity) {
			_return.setCodigoError(0);
			_return.setMensaje("Datos guardados correctamente");
			return _return;
		}
		return _return;
	}

	private ResultOutTO branch_articles_Active(SalesTO parameters)
			throws EJBException {
		boolean validquantity = false;
		ResultOutTO _return = new ResultOutTO();
		List branch = new Vector();
		ArticlesTO _result = new ArticlesTO();
		AdminDAO DAO1 = new AdminDAO();
		DAO1.setIstransaccional(true);
		String code;
		// validaciones antes de guardar la venta
		Iterator<SalesDetailTO> iterator1 = parameters.getSalesDetails()
				.iterator();
		// recorre el detalle de la venta por articulo
		while (iterator1.hasNext()) {

			validquantity = false;
			SalesDetailTO articleDetalle = (SalesDetailTO) iterator1.next();
			code = articleDetalle.getItemcode();
			try {
				_result = DAO1.getinventaryArticlesByKey(code);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// se asigna lista de articulos por almacen
			branch = _result.getBranchArticles();
			// recorre la lista por almacen verificando si esta activo el
			// articulo
			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;
				if (branch1.getWhscode().equals(articleDetalle.getWhscode())) {
					if (branch1.getLocked().equals("f")) {
						validquantity = true;
					} else {
						_return.setLinenum(articleDetalle.getLinenum());
						_return.setCodigoError(1);
						_return.setMensaje("El articulo "
								+ articleDetalle.getItemcode() + " "
								+ articleDetalle.getDscription()
								+ " no esta activo para el almacen cod :"
								+ articleDetalle.getWhscode() + "en la linea :"
								+ articleDetalle.getLinenum());
					}
				}
			}
		}
		if (validquantity) {
			_return.setCodigoError(0);
			_return.setMensaje("Datos correctamente ingresados");
			return _return;
		}
		return _return;
	}

	private ResultOutTO branch_Active(SalesTO parameters) throws EJBException {
		boolean validquantity = false;
		ResultOutTO _return = new ResultOutTO();
		BranchTO _result = new BranchTO();
		AdminDAO DAO1 = new AdminDAO();
		DAO1.setIstransaccional(true);
		String code;
		// validaciones antes de guardar la venta
		Iterator<SalesDetailTO> iterator1 = parameters.getSalesDetails()
				.iterator();
		// recorre el detalle de la venta por articulo
		while (iterator1.hasNext()) {

			validquantity = false;
			SalesDetailTO articleDetalle = (SalesDetailTO) iterator1.next();
			code = articleDetalle.getWhscode();
			try {
				_result = DAO1.getBranchByKey(code);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (_result.isLocked()) {

				_return.setLinenum(articleDetalle.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("para articulo "
						+ articleDetalle.getItemcode() + " "
						+ articleDetalle.getDscription() + " el almacen cod :"
						+ articleDetalle.getWhscode()
						+ "se encuentra inactivo en la linea :"
						+ articleDetalle.getLinenum());
			} else {
				validquantity = true;
			}

		}
		if (validquantity) {
			_return.setCodigoError(0);
			_return.setMensaje("Datos correctamente ingresados");
			return _return;
		}
		return _return;

	}

	private ResultOutTO if_article_sale(SalesTO parameters) throws EJBException {

		boolean validquantity = false;
		ResultOutTO _return = new ResultOutTO();
		ArticlesTO _result = new ArticlesTO();
		AdminDAO DAO1 = new AdminDAO();
		DAO1.setIstransaccional(true);
		String code;
		// validaciones antes de guardar la venta
		Iterator<SalesDetailTO> iterator1 = parameters.getSalesDetails()
				.iterator();
		// recorre el detalle de la venta por articulo
		while (iterator1.hasNext()) {
			validquantity = false;
			SalesDetailTO articleDetalle = (SalesDetailTO) iterator1.next();
			code = articleDetalle.getItemcode();
			try {
				_result = DAO1.getArticlesByKey(code);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (_result.getSellItem().equals("N")) {

				_return.setLinenum(articleDetalle.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ articleDetalle.getItemcode() + " "
						+ articleDetalle.getDscription()
						+ " no es articulo de venta");

			} else {
				validquantity = true;
			}

		}
		if (validquantity) {
			_return.setCodigoError(0);
			_return.setMensaje("Datos correctamente ingresados");
			return _return;
		}
		return _return;
	}

}

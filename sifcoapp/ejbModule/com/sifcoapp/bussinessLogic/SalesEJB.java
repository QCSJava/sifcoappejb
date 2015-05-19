package com.sifcoapp.bussinessLogic;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;




import com.sifcoapp.admin.ejb.AdminEJB;
import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.purchase.to.SupplierDetailTO;
import com.sifcoapp.objects.sales.DAO.*;
import com.sifcoapp.objects.sales.to.*;


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

	public  String last_INPUT(int series,String _objtype) throws Exception{
		
		String ultimo = null;
		SalesDAO DAO = new SalesDAO();
		try {
			ultimo= DAO.last_input(series, _objtype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return ultimo;	
	
	}
	public List getSales(SalesInTO param) throws Exception {
		System.out.println("llego al sales ejb");
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
		System.out.println("llego al salesejb");
		// --------------------------------------------------------------------------------------------------------------------------------
		// Set el codigo de almacen del padre al detalle
		// --------------------------------------------------------------------------------------------------------------------------------
		Iterator<SalesDetailTO> iterator1 = parameters.getSalesDetails()
				.iterator();
		while (iterator1.hasNext()) {

			SalesDetailTO articleDetalle = (SalesDetailTO) iterator1
					.next();
			articleDetalle.setWhscode(parameters.getTowhscode());
		}
		ResultOutTO _return = new ResultOutTO();
		_return = validateSale(parameters);
		System.out.println(_return.getCodigoError());
		if (_return.getCodigoError() != 0) {
			return _return;
		}
		SalesDAO DAO = new SalesDAO();
		DAO.setIstransaccional(true);
		try {

			// --------------------------------------------------------------------------------------------------------
			// guardar venta
			// --------------------------------------------------------------------------------------------------------
			_return = saveSales(DAO, parameters, action);

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
		// --------------------------------------------------------------------------------------------------------------------------------
				// Set el codigo de almacen del padre al detalle
				// --------------------------------------------------------------------------------------------------------------------------------
				Iterator<ClientCrediDetailTO> iterator1 = parameters.getclientDetails()
						.iterator();
				while (iterator1.hasNext()) {

					ClientCrediDetailTO articleDetalle = (ClientCrediDetailTO) iterator1
							.next();
					articleDetalle.setWhscode(parameters.getTowhscode());
				}
				// validaciones
		ResultOutTO _return = new ResultOutTO();
		_return = validateClientCredi(parameters);
		System.out.println(_return.getCodigoError());
		if (_return.getCodigoError() != 0) {
			return _return;
		}

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
				articleDetalle.setDiscprcnt(articleDetalle.getQuantity());
				articleDetalle.setOpenqty(articleDetalle.getQuantity());

				articleDetalle.setFactor1(articleDetalle.getQuantity());

			}
			parameters.setDiscsum(0.00);
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
		
		// pasar el codigo de almacen a los hijos
				Iterator<DeliveryDetailTO> iterator3 = parameters.getDeliveryDetails()
						.iterator();
				while (iterator3.hasNext()) {

					DeliveryDetailTO articleDetalle = (DeliveryDetailTO) iterator3
							.next();
					articleDetalle.setWhscode(parameters.getTowhscode());
				}
				// -------------------------------------------------------------------------------------------------------------------------------
				// validaciones-------------------------------------------------------------------------------------------------------------------
		// validacion de los datos del delivery
		_return = Validateinv_Delivery(parameters);
		System.out.println(_return.getCodigoError());
		if (_return.getCodigoError() != 0) {
			return _return;
		}
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

				articleDetalle.setDiscprcnt(articleDetalle.getQuantity());
				articleDetalle.setOpenqty(articleDetalle.getQuantity());

				articleDetalle.setFactor1(articleDetalle.getQuantity());

			}

			parameters.setDiscsum(0.00);
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

	public ResultOutTO validateSale(SalesTO parameters) throws EJBException {
		// Variables
		System.out.println("llego al validateSAle ");
		boolean valid = false;
		ResultOutTO _return = new ResultOutTO();
		AccountingEJB acc = new AccountingEJB();
		CatalogEJB Businesspartner = new CatalogEJB();
		AdminEJB EJB1 = new AdminEJB();
		Double stocks;
		List branch = new Vector();
		ArticlesTO DBArticle = new ArticlesTO();

		String code;
		double Quantity;

		// validaciones antes de guardar la venta

		// validaciones del documento

		// ------------------------------------------------------------------------------------------------------------
		// Validación almacen bloqueado
		// ------------------------------------------------------------------------------------------------------------
		if (parameters.getTowhscode() == null) {
			_return.setCodigoError(1);
			_return.setMensaje("Codigo de almacen null");

			return _return;
		}
		_return = EJB1.validate_branchActiv(parameters.getTowhscode());

		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("El Almacen no esta activo");

			return _return;
		}

		// ------------------------------------------------------------------------------------------------------------
		// fecha de contabilizacion valida
		// ------------------------------------------------------------------------------------------------------------
		if (parameters.getDocdate() == null) {
			_return.setCodigoError(1);
			_return.setMensaje("no se encuentra fecha del documento ");

			return _return;
		}
		_return = acc.validate_exist_accperiod(parameters.getDocdate());
		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("El documento tiene una fecha Fuera del periodo contable activo");
			return _return;
		}
		// ------------------------------------------------------------------------------------------------------------
		// socio de negocio activo
		// ------------------------------------------------------------------------------------------------------------
		if (parameters.getCardcode() == null) {
			_return.setCodigoError(1);
			_return.setMensaje("Codigo de almacen null");

			return _return;
		}
		_return = Businesspartner.validate_businesspartnerBykey(parameters
				.getCardcode());
		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("el socio de negocio no esta activo para esta transaccion");
			return _return;
		}
		// recorre el detalle de la venta por articulo
		Iterator<SalesDetailTO> iterator1 = parameters.getSalesDetails()
				.iterator();
		while (iterator1.hasNext()) {
			AdminEJB EJB = new AdminEJB();
			// Consultar información actualizada desde la base
			SalesDetailTO salesDetail = (SalesDetailTO) iterator1.next();
			code = salesDetail.getItemcode();

			DBArticle = EJB.getArticlesByKey(code);

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo existe
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle != null) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(salesDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo " + salesDetail.getItemcode()
						+ " " + salesDetail.getDscription()

						+ " no existe,informar al administrador. linea :"
						+ salesDetail.getLinenum());
				System.out.println(valid);
				return _return;

			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo activo
			// ------------------------------------------------------------------------------------------------------------

			valid = false;
			if (DBArticle.getValidFor() != null
					&& DBArticle.getValidFor().toUpperCase().equals("Y")) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(salesDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo " + salesDetail.getItemcode()
						+ " " + salesDetail.getDscription()

						+ " No esta activo. linea :" + salesDetail.getLinenum());
				System.out.println(valid);
				return _return;

			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo venta
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle.getSellItem() != null
					&& DBArticle.getSellItem().toUpperCase().equals("Y")) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(salesDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo " + salesDetail.getItemcode()
						+ " " + salesDetail.getDscription()
						+ " No es un articulo de venta. linea :"
						+ salesDetail.getLinenum());
				return _return;
			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación almacen bloqueado para articulos
			// ------------------------------------------------------------------------------------------------------------
			valid = false;

			branch = DBArticle.getBranchArticles();

			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;
				System.out.println(branch1.getWhscode());
				System.out.println(salesDetail.getWhscode());
				if (branch1.getWhscode().equals(salesDetail.getWhscode())) {
					if (branch1.getWhscode() != null
							&& branch1.getLocked().toUpperCase().equals("F")) {
						valid = true;
					}
				}
			}

			if (!valid) {
				_return.setLinenum(salesDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ salesDetail.getItemcode()
						+ " "
						+ salesDetail.getDscription()
						+ " No esta asignado o esta bloquedo para el almacen indicado. linea :"
						+ salesDetail.getLinenum());
				return _return;
			}

			// ------------------------------------------------------------------------------------------------------------
			// Validaciones de Existencia
			// ------------------------------------------------------------------------------------------------------------
			valid = false;

			stocks = 0.000;
			Iterator<SalesDetailTO> iterator2 = parameters.getSalesDetails()
					.iterator();
			// recorre de nuevo el detalle comparando el primer elemento con los
			// demas
			while (iterator2.hasNext()) {
				SalesDetailTO articleDetalle2 = (SalesDetailTO) iterator2
						.next();
				if (code.equals(articleDetalle2.getItemcode())) {
					// suma los elementos encontrados del mismo codigo
					stocks = stocks + articleDetalle2.getQuantity();
				}
			}

			branch = DBArticle.getBranchArticles();
			Quantity = 0.0;
			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;
				if (branch1.getWhscode().equals(salesDetail.getWhscode())) {
					System.out.println("sumatoria" + stocks + "base"
							+ branch1.getOnhand());
					if (stocks <= branch1.getOnhand()) {
						valid = true;
					}
				}

			}
			if (!valid) {
				_return.setLinenum(salesDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo " + salesDetail.getItemcode()
						+ " " + salesDetail.getDscription()
						+ " Recae en un Inventario Negativo. linea :"
						+ salesDetail.getLinenum());
				return _return;
			}
		}
		_return.setCodigoError(0);

		return _return;
	}

	public ResultOutTO validateClientCredi(ClientCrediTO parameters)
			throws EJBException {
		// Variables
		System.out.println("llego al validateClientCredi ");
		boolean valid = false;
		ResultOutTO _return = new ResultOutTO();
		AccountingEJB acc = new AccountingEJB();
		CatalogEJB Businesspartner = new CatalogEJB();
		AdminEJB EJB1 = new AdminEJB();
		List branch = new Vector();
		ArticlesTO DBArticle = new ArticlesTO();
		String code;

		// validaciones

		// validaciones del documento
		// ------------------------------------------------------------------------------------------------------------
		// Validación almacen bloqueado
		// ------------------------------------------------------------------------------------------------------------

		_return = EJB1.validate_branchActiv(parameters.getTowhscode());

		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("El Almacen no esta activo");
			return _return;
		}

		// ------------------------------------------------------------------------------------------------------------
		// periodo contable activo
		// ------------------------------------------------------------------------------------------------------------
		_return = acc.validate_exist_accperiod(parameters.getDocdate());
		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("El documento tiene una fecha Fuera del periodo contable activo");
			return _return;
		}
		// ------------------------------------------------------------------------------------------------------------
		// validacion de socio de negocio
		// ------------------------------------------------------------------------------------------------------------

		_return = Businesspartner.validate_businesspartnerBykey(parameters
				.getCardcode());
		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("el socio de negocio no esta activo para esta transaccion");
			return _return;
		}
		// recorre el ClientCrediDetail
		Iterator<ClientCrediDetailTO> iterator1 = parameters.getclientDetails()
				.iterator();
		while (iterator1.hasNext()) {
			AdminEJB EJB = new AdminEJB();
			// Consultar información actualizada desde la base
			ClientCrediDetailTO ClientCrediDetail = (ClientCrediDetailTO) iterator1
					.next();
			code = ClientCrediDetail.getItemcode();

			DBArticle = EJB.getArticlesByKey(code);

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo existe
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle != null) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(ClientCrediDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ ClientCrediDetail.getItemcode() + " "
						+ ClientCrediDetail.getDscription()

						+ " no existe,informar al administrador. linea :"
						+ ClientCrediDetail.getLinenum());
				System.out.println(valid);
				return _return;

			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo activo
			// ------------------------------------------------------------------------------------------------------------

			valid = false;
			if (DBArticle.getValidFor() != null
					&& DBArticle.getValidFor().toUpperCase().equals("Y")) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(ClientCrediDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ ClientCrediDetail.getItemcode() + " "
						+ ClientCrediDetail.getDscription()

						+ " No esta activo. linea :"
						+ ClientCrediDetail.getLinenum());
				System.out.println(valid);
				return _return;

			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo venta
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle.getSellItem() != null
					&& DBArticle.getSellItem().toUpperCase().equals("Y")) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(ClientCrediDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ ClientCrediDetail.getItemcode() + " "
						+ ClientCrediDetail.getDscription()
						+ " No es un articulo de venta. linea :"
						+ ClientCrediDetail.getLinenum());
				return _return;
			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación almacen bloqueado
			// ------------------------------------------------------------------------------------------------------------
			valid = false;

			branch = DBArticle.getBranchArticles();

			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;
				System.out.println(branch1.getWhscode());
				System.out.println(ClientCrediDetail.getWhscode());
				if (branch1.getWhscode().equals(ClientCrediDetail.getWhscode())) {
					if (branch1.getWhscode() != null
							&& branch1.getLocked().toUpperCase().equals("F")) {
						valid = true;
					}
				}
			}

			if (!valid) {
				_return.setLinenum(ClientCrediDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ ClientCrediDetail.getItemcode()
						+ " "
						+ ClientCrediDetail.getDscription()
						+ " No esta asignado o esta bloquedo para el almacen indicado. linea :"
						+ ClientCrediDetail.getLinenum());
				return _return;
			}

		}
		_return.setCodigoError(0);

		return _return;

	}

	public ResultOutTO Validateinv_Delivery(DeliveryTO parameters)
			throws EJBException {
		System.out.println("llego al vav_Delivery ");
		boolean valid = false;
		ResultOutTO _return = new ResultOutTO();
		AccountingEJB acc = new AccountingEJB();
		CatalogEJB Businesspartner = new CatalogEJB();
		AdminEJB EJB1 = new AdminEJB();
		List branch = new Vector();
		ArticlesTO DBArticle = new ArticlesTO();
		String code;
		// validaciones
		// validaciones del documento
		// ------------------------------------------------------------------------------------------------------------
		// Validación almacen bloqueado
		// ------------------------------------------------------------------------------------------------------------

		_return = EJB1.validate_branchActiv(parameters.getTowhscode());

		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("El Almacen no esta activo");
			return _return;
		}

		// ------------------------------------------------------------------------------------------------------------
		// periodo contable activo
		// ------------------------------------------------------------------------------------------------------------
		_return = acc.validate_exist_accperiod(parameters.getDocdate());
		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("El documento tiene una fecha Fuera del periodo contable activo");
			return _return;
		}
		// ------------------------------------------------------------------------------------------------------------
		// validacion de socio de negocio
		// ------------------------------------------------------------------------------------------------------------

		_return = Businesspartner.validate_businesspartnerBykey(parameters
				.getCardcode());
		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("el socio de negocio no esta activo para esta transaccion");
			return _return;
		}

		// recorre el ClientCrediDetail
		Iterator<DeliveryDetailTO> iterator1 = parameters.getDeliveryDetails()
				.iterator();

		while (iterator1.hasNext()) {
			AdminEJB EJB = new AdminEJB();
			// Consultar información actualizada desde la base
			DeliveryDetailTO DeliveryDetail = (DeliveryDetailTO) iterator1
					.next();
			code = DeliveryDetail.getItemcode();

			DBArticle = EJB.getArticlesByKey(code);

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo existe
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle != null) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(DeliveryDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ DeliveryDetail.getItemcode() + " "
						+ DeliveryDetail.getDscription()

						+ " no existe,informar al administrador. linea :"
						+ DeliveryDetail.getLinenum());
				System.out.println(valid);
				return _return;

			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo activo
			// ------------------------------------------------------------------------------------------------------------

			valid = false;
			if (DBArticle.getValidFor() != null
					&& DBArticle.getValidFor().toUpperCase().equals("Y")) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(DeliveryDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ DeliveryDetail.getItemcode() + " "
						+ DeliveryDetail.getDscription()

						+ " No esta activo. linea :"
						+ DeliveryDetail.getLinenum());
				System.out.println(valid);
				return _return;

			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo venta
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle.getSellItem() != null
					&& DBArticle.getSellItem().toUpperCase().equals("Y")) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(DeliveryDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ DeliveryDetail.getItemcode() + " "
						+ DeliveryDetail.getDscription()
						+ " No es un articulo de venta. linea :"
						+ DeliveryDetail.getLinenum());
				return _return;
			}
			// ------------------------------------------------------------------------------------------------------------
			// Validación almacen bloqueado
			// ------------------------------------------------------------------------------------------------------------

			_return = EJB.validate_branchActiv(DeliveryDetail.getWhscode());

			if (_return.getCodigoError() != 0) {
				_return.setCodigoError(1);
				_return.setMensaje("El Almacen no esta activo");
				_return.setLinenum(DeliveryDetail.getLinenum());
				return _return;
			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación almacen bloqueado para el articulo
			// ------------------------------------------------------------------------------------------------------------
			valid = false;

			branch = DBArticle.getBranchArticles();

			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;
				System.out.println(branch1.getWhscode());
				System.out.println(DeliveryDetail.getWhscode());
				if (branch1.getWhscode().equals(DeliveryDetail.getWhscode())) {
					if (branch1.getWhscode() != null
							&& branch1.getLocked().toUpperCase().equals("F")) {
						valid = true;
					}
				}
			}

			if (!valid) {
				_return.setLinenum(DeliveryDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ DeliveryDetail.getItemcode()
						+ " "
						+ DeliveryDetail.getDscription()
						+ " No esta asignado o esta bloquedo para el almacen indicado. linea :"
						+ DeliveryDetail.getLinenum());
				return _return;
			}

		}
		_return.setCodigoError(0);

		return _return;

	}

	public ResultOutTO saveSales(SalesDAO DAO, SalesTO parameters, int action)
			throws EJBException {
		ResultOutTO _return = new ResultOutTO();

		SalesDetailDAO goodDAO1 = new SalesDetailDAO(DAO.getConn());
		goodDAO1.setIstransaccional(true);

		// TODO Auto-generated method stub

		Iterator<SalesDetailTO> iterator2 = parameters.getSalesDetails()
				.iterator();

		while (iterator2.hasNext()) {
			SalesDetailTO articleDetalle = (SalesDetailTO) iterator2.next();
			articleDetalle.setDiscprcnt(articleDetalle.getQuantity());
			articleDetalle.setOpenqty(articleDetalle.getQuantity());
			articleDetalle.setFactor1(articleDetalle.getQuantity());
		}
		parameters.setDiscsum(0.00);
		parameters.setNret(0.00);
		parameters.setPaidsum(0.00);
		parameters.setRounddif(0.00);
		try {
			_return.setDocentry(DAO.inv_Sales_mtto(parameters, action));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<SalesDetailTO> iterator = parameters.getSalesDetails()
				.iterator();
		while (iterator.hasNext()) {
			SalesDetailTO articleDetalle = (SalesDetailTO) iterator.next();
			// Para articulos nuevos
			articleDetalle.setDocentry(_return.getDocentry());
			if (action == Common.MTTOINSERT) {
				try {
					goodDAO1.inv_SalesDetail_mtto(articleDetalle,
							Common.MTTOINSERT);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if (action == Common.MTTODELETE) {
				try {
					goodDAO1.inv_SalesDetail_mtto(articleDetalle,
							Common.MTTODELETE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos almacenados con exito");
		return _return;

	}
}

package com.sifcoapp.bussinessLogic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.admin.ejb.AdminEJB;
import com.sifcoapp.admin.ejb.ParameterEJB;
import com.sifcoapp.objects.accounting.dao.AccountingDAO;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.dao.ParameterDAO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerAcountTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.GoodsReceiptDAO;
import com.sifcoapp.objects.inventory.to.GoodsIssuesDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.inventory.to.TransfersDetailTO;
import com.sifcoapp.objects.inventory.to.TransfersTO;
import com.sifcoapp.objects.purchase.dao.PurchaseDAO;
import com.sifcoapp.objects.purchase.dao.PurchaseDetailDAO;
import com.sifcoapp.objects.purchase.to.PurchaseDetailTO;
import com.sifcoapp.objects.purchase.to.PurchaseTO;
import com.sifcoapp.objects.purchase.to.SupplierDetailTO;
import com.sifcoapp.objects.sales.DAO.*;
import com.sifcoapp.objects.sales.to.*;
import com.sifcoapp.objects.transaction.dao.TransactionDAO;
import com.sifcoapp.objects.transaction.to.InventoryLogTO;
import com.sifcoapp.objects.transaction.to.TransactionTo;
import com.sifcoapp.objects.transaction.to.WarehouseJournalLayerTO;
import com.sifcoapp.objects.transaction.to.WarehouseJournalTO;
import com.sifcoapp.transaction.ejb.transactionEJB;

/**
 * Session Bean implementation class SalesEJB
 */
@Stateless
public class SalesEJB implements SalesEJBRemote {
	Double zero = 0.0;

	/**
	 * Default constructor.
	 */
	public SalesEJB() {
		// TODO Auto-generated constructor stub
	}

	public String last_INPUT(int series, String _objtype) throws Exception {

		String ultimo = null;
		SalesDAO DAO = new SalesDAO();
		try {
			ultimo = DAO.last_input(series, _objtype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ultimo;

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

	// -----------------------------------------------------------------------------------------------------------------
	// mantenimientos Ventas
	// -----------------------------------------------------------------------------------------------------------------
	public ResultOutTO inv_Sales_mtto(SalesTO parameters, int action)
			throws EJBException {

		// Declaración de variables

		ResultOutTO _valid = new ResultOutTO();

		ResultOutTO _return = new ResultOutTO();


		// --------------------------------------------------------------------------------------------------------------------------------
		// Validar acción a realizar
		// --------------------------------------------------------------------------------------------------------------------------------

		if (action != Common.MTTOINSERT) {
			_return = inv_Sales_update(parameters, action);
			return _return;
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Asignación de valores por defecto y llenado:
		// Estas se realizan solo para cuando es guardar, el actualizar y borrar
		// no aplican.
		// --------------------------------------------------------------------------------------------------------------------------------
		parameters = fillSales(parameters);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Hacer validaciones:
		// Estas se realizan solo para cuando es guardar, el actualizar y borrar
		// no aplican para validaciones
		// --------------------------------------------------------------------------------------------------------------------------------

		_valid = validateSale(parameters);

		if (_valid.getCodigoError() != 0) {
			return _valid;
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar en base:
		// Desde aqui se debe manajar como una transaccion global, cada metodo
		// debe tener una opción para ejecutarlo como parte de una transacción
		// global
		// --------------------------------------------------------------------------------------------------------------------------------
		SalesDAO DAO = new SalesDAO();
		try {
			
			DAO.setIstransaccional(true);
			_return = save_TransactionSales(parameters, action, DAO.getConn());
			DAO.forceCommit();

		} catch (Exception e) {
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public ResultOutTO inv_Sales_update(SalesTO parameters, int action)
			throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		SalesDAO DAO = new SalesDAO();
		try {
			_return = inv_Sales_update(parameters, action, DAO.getConn());
			DAO.forceCommit();
		} catch (Exception e) {
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {
			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos Almacenados con exito");
		return _return;

	}

	public ResultOutTO inv_Sales_update(SalesTO parameters, int action,
			Connection conn) throws Exception {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		SalesDAO DAO = new SalesDAO(conn);
		DAO.setIstransaccional(true);
		SalesDetailDAO DAO1 = new SalesDetailDAO(conn);
		DAO1.setIstransaccional(true);

		// Actualizar/borrar encabezados
		_return.setDocentry(DAO.inv_Sales_mtto(parameters, action));

		// Borrar detalles
		Iterator<SalesDetailTO> iterator = parameters.getSalesDetails()
				.iterator();
		if (action == Common.MTTODELETE) {
			while (iterator.hasNext()) {
				SalesDetailTO detalleReceipt = (SalesDetailTO) iterator.next();

				DAO1.inv_SalesDetail_mtto(detalleReceipt, Common.MTTODELETE);
			}
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos Actualizados con exito");
		return _return;
	}

	private SalesTO fillSales(SalesTO parameters) {

		// variables
		Double total = zero;
		Double vatsum = zero;
		ArticlesTO DBArticle = new ArticlesTO();
		AdminDAO admin = new AdminDAO();
		int Basetry = 0;

		BranchTO branch1 = new BranchTO();
		// buscando la cuenta asignada de cuenta de existencias al almacen

		try {
			branch1 = admin.getBranchByKey(parameters.getTowhscode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String branch_c = branch1.getBalinvntac();
		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto detalle
		// --------------------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings("unchecked")
		Iterator<SalesDetailTO> iterator = parameters.getSalesDetails()
				.iterator();
		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto encabezado
		// --------------------------------------------------------------------------------------------------------------------------------
		while (iterator.hasNext()) {
			SalesDetailTO articleDetalle = (SalesDetailTO) iterator.next();

			AdminEJB EJB = new AdminEJB();
			DBArticle = EJB.getArticlesByKey(articleDetalle.getItemcode());

			// Asignar a documento
			articleDetalle.setArticle(DBArticle);
			articleDetalle.setAcctcode(branch_c);
			// Valores por defecto
			articleDetalle.setDocentry(parameters.getDocentry());
			// articleDetalle.setTargettype(-1);
			// articleDetalle.setBaseref("");
			// articleDetalle.setBasetype(-1);
			articleDetalle.setLinestatus("O");
			articleDetalle.setDscription(DBArticle.getItemName());
			articleDetalle.setDiscprcnt(zero);
			articleDetalle.setWhscode(parameters.getTowhscode());
			// articleDetalle.setAcctcode(acctcode);
			// articleDetalle.setTaxstatus("Y");
			articleDetalle.setOcrcode(parameters.getTowhscode());
			articleDetalle.setFactor1(zero);
			articleDetalle.setObjtype("10");
			articleDetalle.setGrssprofit(articleDetalle.getGrssprofit());
			articleDetalle.setVatappld(zero);
			articleDetalle.setUnitmsr(DBArticle.getBuyUnitMsr());
			articleDetalle.setStockpricestockprice(articleDetalle.getStockpricestockprice());

			// Calculo de impuesto
			vatsum = vatsum + articleDetalle.getVatsum();

			// Calculo de totales
			articleDetalle.setLinetotal(articleDetalle.getQuantity()
					* articleDetalle.getPrice());
			articleDetalle.setOpenqty(articleDetalle.getQuantity());
			total = total + articleDetalle.getGtotal();

			if (articleDetalle.getBaseentry() != 0) {
				Basetry = articleDetalle.getBaseentry();
			}
		}

		
		
		
		// --------------------------------------------------------------------------------------------------------------------------------
		// consultando si es venta de diesel ; si lo es cambio de cuenta
		// asignada en el concepto de diesel por cliente
		// --------------------------------------------------------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------------------------------------------------------
		ParameterDAO parameter = new ParameterDAO();
		parameterTO param = new parameterTO();
		try {
			param = parameter.getParameterbykey(2);

			if (parameters.getSeries() == Integer.parseInt(param.getValue1())) {
				// --------------------------------------------------------------------------------------------------------------------------------
				// cosultando el concepto diesel
				// --------------------------------------------------------------------------------------------------------------------------------
				parameter = new ParameterDAO();
				param = parameter.getParameterbykey(9);
				// --------------------------------------------------------------------------------------------------------------------------------
				// --------------------------------------------------------------------------------------------------------------------------------
				BusinesspartnerAcountTO busines = new BusinesspartnerAcountTO();
				List list = new Vector();
				CatalogEJB catalogo = new CatalogEJB();
				// --------------------------------------------------------------------------------------------------------------------------------
				// --------------------------------------------------------------------------------------------------------------------------------
				list = catalogo.get_businesspartnerAcount(parameters
						.getCardcode());
				// --------------------------------------------------------------------------------------------------------------------------------
				// recorriendo la lista de businespartneraccount para encontrar
				// el concepto de diesel y su correspondiente cuenta
				// --------------------------------------------------------------------------------------------------------------------------------
				for (Object object : list) {
					BusinesspartnerAcountTO bus = (BusinesspartnerAcountTO) object;
					if (bus.getAcctype() == Integer.parseInt(param.getValue1())) {
						// --------------------------------------------------------------------------------------------------------------------------------
						// asignando la cuenta correspondiente a diesel segun el
						// socio de negocio
						// --------------------------------------------------------------------------------------------------------------------------------
						parameters.setCtlaccount(bus.getAcctcode());
						// --------------------------------------------------------------------------------------------------------------------------------
						// --------------------------------------------------------------------------------------------------------------------------------
					}

				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		// --------------------------------------------------------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto encabezado
		// --------------------------------------------------------------------------------------------------------------------------------
		java.util.Date utilDate = new java.util.Date(); // fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
		parameters.setDocduedate(sqlDate);
		parameters.setDocnum(parameters.getDocentry());
		parameters.setDoctype("I");
		parameters.setCanceled("N");
		parameters.setDocstatus("O");
		parameters.setObjtype("10");
		parameters.setVatsum(vatsum);
		parameters.setDiscsum(zero);
		parameters.setDoctotal(total);
		parameters.setRef1(Integer.toString(parameters.getDocnum()));
		parameters.setJrnlmemo("Facturas de venta a clientes - "
				+ parameters.getCardcode());
		parameters.setReceiptnum(parameters.getReceiptnum());
		parameters.setGroupnum(0);
		parameters.setConfirmed("Y");
		parameters.setCreatetran("Y");
		
		parameters.setRounddif(zero);
		parameters.setRounding("N");
	
		parameters.setPaidsum(zero);
		parameters.setNret(zero);

		return parameters;
	}

	public ResultOutTO save_TransactionSales(SalesTO Sales, int action,
			Connection conn) throws Exception {

		// Variables
		List transactions = new Vector();
		InventoryLogTO inventoryLog = new InventoryLogTO();
		WarehouseJournalTO warehouseJournal = new WarehouseJournalTO();
		WarehouseJournalLayerTO warehouseJournallayer = new WarehouseJournalLayerTO();
		ResultOutTO _return = new ResultOutTO();
		ResultOutTO res_invet = new ResultOutTO();
		ResultOutTO res_whj = new ResultOutTO();
		ResultOutTO res_whjl = new ResultOutTO();
		ResultOutTO res_jour = new ResultOutTO();
		ResultOutTO res_UpdateOnhand = new ResultOutTO();
		SalesDAO DAO = new SalesDAO(conn);
		transactionEJB trans = new transactionEJB();
		JournalEntryTO journal = new JournalEntryTO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar Encabezados y detalles de Entrada
		// --------------------------------------------------------------------------------------------------------------------------------

		_return = inv_sales_save(Sales, action, conn);
		Sales.setDocentry(_return.getDocentry());
		Sales.setDocnum(_return.getDocentry());
		Sales.setRef1(Integer.toString(Sales.getDocnum()));

		// --------------------------------------------------------------------------------------------------------------------------------
		// Llenar objeto tipo transacción
		// --------------------------------------------------------------------------------------------------------------------------------

		transactions = fill_transaction(Sales);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Calculo y actualizacion de existencias y costos
		// --------------------------------------------------------------------------------------------------------------------------------
		for (Object object : transactions) {
			TransactionTo ivt = (TransactionTo) object;
			ivt = trans.calculate(ivt);
			// ------------------------------------------------------------------------------------------------------------------------------------
			TransactionDAO transDAO = new TransactionDAO(conn);
			transDAO.setIstransaccional(true);
			res_UpdateOnhand = transDAO.Update_Onhand_articles(ivt);
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Registro de Inventory Log
		// --------------------------------------------------------------------------------------------------------------------------------

		for (Object object : transactions) {
			TransactionTo ivt = (TransactionTo) object;

			inventoryLog = trans.fill_Inventory_Log(ivt);
			res_invet = trans.save_Inventory_Log(inventoryLog, conn);
			// Asignación de codigo MessageId
			ivt.setMessageid(res_invet.getDocentry());
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Registro de Warehouse Journal
		// --------------------------------------------------------------------------------------------------------------------------------

		for (Object object : transactions) {
			TransactionTo ivt = (TransactionTo) object;
			warehouseJournal = trans.fill_WarehouseJournal(ivt);
			// Asiganción de TransSeq
			res_whj = trans.save_WarehouseJournal(warehouseJournal, conn);
			ivt.setTransseq(res_whj.getDocentry());
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Registro de Warehouse Journal layer
		// --------------------------------------------------------------------------------------------------------------------------------

		for (Object object : transactions) {

			TransactionTo ivt = (TransactionTo) object;
			warehouseJournallayer = trans.fill_WarehouseJournalLayer(ivt);
			// Asiganción de TransSeq
			res_whjl = trans.save_WarehouseJournalLayer(warehouseJournallayer,
					conn);
		}

		// -----------------------------------------------------------------------------------
		// registro del asiento contable y actualización de saldos
		// -----------------------------------------------------------------------------------

		journal = fill_JournalEntry(Sales);

		AccountingEJB account = new AccountingEJB();
		res_jour = account.journalEntry_mtto(journal, Common.MTTOINSERT, conn);

		// -----------------------------------------------------------------------------------
		// Actualización de documento con datos de Asiento contable
		// -----------------------------------------------------------------------------------
		// actualizacion del documento de nota de remision de donde se produjo
		// la venta
		if (Sales.getReceiptnum() != 0) {
			DeliveryTO delivery = new DeliveryTO();
			delivery = getDeliveryByKey(Sales.getReceiptnum());
			delivery.setCanceled("Y");
			delivery.setDocstatus("C");
			delivery.setCanceldate(Sales.getTaxdate());
			_return = inv_Delivery_update(delivery, Common.MTTOUPDATE, conn);
		}
		// -----------------------------------------------------------------------------------
		// -----------------------------------------------------------------------------------
		Sales.setTransid(res_jour.getDocentry());
		_return = inv_Sales_update(Sales, Common.MTTOUPDATE, conn);

		// -----------------------------------------------------------------------------------
		// Metodo de Pago de factura Contado
		// -----------------------------------------------------------------------------------
		if (Sales.getPeymethod().equals("1")) {
			journal = fill_JournalEntry_pago(Sales);

			AccountingEJB account1 = new AccountingEJB();
			res_jour = account1.journalEntry_mtto(journal, Common.MTTOINSERT,
					conn);
			Sales.setDocstatus("C");
			Sales.setPaidtodate(Sales.getDocdate());
			Sales.setPaidsum(journal.getSystotal());
			_return = inv_Sales_update(Sales, Common.MTTOUPDATE, conn);
		}
		// -----------------------------------------------------------------------------------
		// Actualización de documento con datos de Asiento contable
		// -----------------------------------------------------------------------------------
		_return.setCodigoError(0);
		_return.setDocentry(Sales.getDocentry());
		_return.setMensaje("Datos Alamacenados con Exito");
		return _return;
	}

	private List fill_transaction(SalesTO document) {
		List _return = new Vector();

		Iterator<SalesDetailTO> iterator = document.getSalesDetails()
				.iterator();
		while (iterator.hasNext()) {
			SalesDetailTO detail = (SalesDetailTO) iterator.next();

			TransactionTo transaction = new TransactionTo();
			transaction.setTransseq(0);
			transaction.setDocentry(document.getDocentry());
			transaction.setDocnum(Integer.toString(document.getDocnum()));
			transaction.setDocduedate(document.getDocduedate());
			transaction.setDocdate(document.getDocdate());
			transaction.setComment(document.getComments());
			transaction.setJrnlmemo(document.getJrnlmemo());
			transaction.setUsersign(document.getUsersign());
			transaction.setRef1(Integer.toString(document.getDocnum()));
			transaction.setRef2(document.getNumatcard());
			transaction.setLinenum(detail.getLinenum());
			transaction.setItemcode(detail.getItemcode());
			transaction.setDscription(detail.getDscription());
			transaction.setQuantity(detail.getQuantity());
			transaction.setPrice(detail.getPrice());
			transaction.setLinetotal(detail.getLinetotal());
			transaction.setWhscode(detail.getWhscode());
			transaction.setAcctcode(detail.getAcctcode());
			transaction.setOcrcode(document.getCardcode());
			transaction.setVatgroup(detail.getVatgroup());
			transaction.setPriceafvat(detail.getPriceafvat());
			transaction.setVatsum(detail.getVatsum());
			transaction.setObjtype(detail.getObjtype());
			transaction.setGrssprofit(detail.getGrssprofit());
			transaction.setTaxcode(detail.getTaxcode());
			transaction.setVatappld(detail.getVatappld());
			transaction.setStockprice(detail.getPrice());
			transaction.setGtotal(detail.getGtotal());
			transaction.setInqty(zero);
			transaction.setOutqty(zero);
			transaction.setMessageid(0);
			transaction.setBalance(zero);
			transaction.setNewOnhand(zero);
			transaction.setNewWhsOnhand(zero);
			transaction.setNewAvgprice(zero);
			transaction.setArticle(detail.getArticle());

			_return.add(transaction);

			/*
			 * transaction.setTransseq(detail.getTransseq());
			 * transaction.setDocentry(detail.getDocentry());
			 * transaction.setDocnum(detail.getDocnum());
			 * transaction.setDocduedate(detail.getDocduedate());
			 * transaction.setDocdate(detail.getDocdate());
			 * transaction.setComment(detail.getComment());
			 * transaction.setJrnlmemo(detail.getJrnlmemo());
			 * transaction.setUsersign(detail.getUsersign());
			 * transaction.setRef1(detail.getRef1());
			 * transaction.setRef2(detail.getRef2());
			 * transaction.setLinenum(detail.getLinenum());
			 * transaction.setItemcode(detail.getItemcode());
			 * transaction.setDscription(detail.getDscription());
			 * transaction.setQuantity(detail.getQuantity());
			 * transaction.setPrice(detail.getPrice());
			 * transaction.setLinetotal(detail.getLinetotal());
			 * transaction.setWhscode(detail.getWhscode());
			 * transaction.setAcctcode(detail.getAcctcode());
			 * transaction.setOcrcode(detail.getOcrcode());
			 * transaction.setVatgroup(detail.getVatgroup());
			 * transaction.setPriceafvat(detail.getPriceafvat());
			 * transaction.setVatsum(detail.getVatsum());
			 * transaction.setObjtype(detail.getObjtype());
			 * transaction.setGrssprofit(detail.getGrssprofit());
			 * transaction.setTaxcode(detail.getTaxcode());
			 * transaction.setVatappld(detail.getVatappld());
			 * transaction.setStockprice(detail.getStockprice());
			 * transaction.setGtotal(detail.getGtotal());
			 * transaction.setInqty(detail.getInqty());
			 * transaction.setOutqty(detail.getOutqty());
			 * transaction.setMessageid(detail.getMessageid());
			 * transaction.setBalance(detail.getBalance());
			 * transaction.setNewonhand(detail.getNewonhand());
			 * transaction.setNewwhsonhand(detail.getNewwhsonhand());
			 * transaction.setNewavgprice(detail.getNewavgprice());
			 */

		}
		return _return;
	}

	public ResultOutTO inv_sales_save(SalesTO parameters, int action,
			Connection conn) throws Exception {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		SalesDAO DAO = new SalesDAO(conn);
		DAO.setIstransaccional(true);
		SalesDetailDAO goodDAO1 = new SalesDetailDAO(conn);
		goodDAO1.setIstransaccional(true);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar encabezados
		// --------------------------------------------------------------------------------------------------------------------------------

		_return.setDocentry(DAO.inv_Sales_mtto(parameters, action));

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar detalles
		// --------------------------------------------------------------------------------------------------------------------------------

		Iterator<SalesDetailTO> iterator = parameters.getSalesDetails()
				.iterator();
		while (iterator.hasNext()) {
			SalesDetailTO salesdetalle = (SalesDetailTO) iterator.next();

			salesdetalle.setDocentry(_return.getDocentry());
			goodDAO1.inv_SalesDetail_mtto(salesdetalle, Common.MTTOINSERT);

		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public JournalEntryTO fill_JournalEntry(SalesTO parameters)
			throws Exception {

		String buss_c;
		String branch_c;
		String iva_c = null;
		String V_local;
		String costo_venta;
		String fovial = null;
		String cotrans_C = null;
		double bussines = 0.0;
		double branch = 0.0;
		double tax = 0.0;
		double fovc = 0.0;
		double cotrans = 0.0;
		double sale = 0.0;
		double costo = 0.0;
		double impuesto = 0.0;
		
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		ArticlesTO arti = new ArticlesTO();
		boolean ind = false;
		Double total = zero;
		List list = parameters.getSalesDetails();
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();
		// recorre la lista de detalles
		
		CatalogTO Catalog = new CatalogTO();
		CatalogTO Catalog1 = new CatalogTO();
		for (Object obj : list) {
			SalesDetailTO good = (SalesDetailTO) obj;
			String cod = good.getAcctcode();
			List lisHija = new Vector();
			// consultando la cuenta de iva y porcentajes de impuestos
			// --------------------------------------------------------------------------------
			AdminDAO admin = new AdminDAO();
			Catalog = admin.findCatalogByKey(good.getTaxcode(), 10);
			// calculando los impuestos y saldo de las cuentas
			// --------------------------------------------------------------------------------

			arti = good.getArticle();
			branch = branch + (arti.getAvgPrice() * good.getQuantity());
			sale = sale + good.getLinetotal();
			// calculando el iva validando si el producto esta exento o no de
			// iva
			if (good.getTaxstatus().equals("Y")) {
				// validar si es FOV
				if (good.getTaxcode().equals("FOV")) {

					admin = new AdminDAO();

					// cuentas contables por tipos de documentos

					Catalog1 = admin.findCatalogByKey("FOV1", 10);
					if (Catalog1.getCatvalue3() == null) {
						throw new Exception(
								"No tiene cuenta asignada para  impuestos");
					}
					if (Catalog1.getCatvalue2() == null) {
						throw new Exception(
								"No tiene cuenta asignada para  impuestos");
					}
					if (Catalog1.getCatvalue() == null) {
						throw new Exception(
								"No tiene cuenta asignada para  impuestos");
					}
					cotrans_C = Catalog1.getCatvalue3();
					fovial = Catalog1.getCatvalue2();
					iva_c = Catalog1.getCatvalue();

					// calculo de impuestos consultados anteriormente en Catalog
					impuesto = good.getLinetotal()
							* (Double.parseDouble(Catalog.getCatvalue()) / 100);
					fovc = fovc
							+ (Double.parseDouble(Catalog.getCatvalue2()) * good
									.getQuantity());
					cotrans = cotrans
							+ (Double.parseDouble(Catalog.getCatvalue3()) * good
									.getQuantity());
					tax = tax + impuesto;
					// -----------------------------------------------------------------
				} else {
					if (parameters.getSeries() == 1) {

						if (Catalog.getCatvalue2() == null) {
							throw new Exception(
									"No tiene cuenta asignada para impuestos");
						}

						iva_c = Catalog.getCatvalue2();
						impuesto = good.getLinetotal()
								* (Double.parseDouble(Catalog.getCatvalue()) / 100);
					} else {
						if (Catalog.getCatvalue3() == null) {
							throw new Exception(
									"No tiene cuenta asignada para impuestos");
						}

						iva_c = Catalog.getCatvalue3();
						impuesto = good.getLinetotal()
								* (Double.parseDouble(Catalog.getCatvalue()) / 100);
					}
					tax = tax + impuesto;
				}

			}

			// sacando el total de venta

			bussines = bussines
					+ ((good.getVatsum() - impuesto) + impuesto + good
							.getLinetotal());

		}

		// consultando en la base de datos los codigos de cuenta asignados
		// cuenta de bussines partner
		buss_c = parameters.getCtlaccount();
		// buscar la cuenta asignada al almacen

		BranchTO branch1 = new BranchTO();
		// buscando la cuenta asignada de cuenta de existencias al almacen
		AdminDAO admin = new AdminDAO();
		branch1 = admin.getBranchByKey(parameters.getTowhscode());
		branch_c = branch1.getBalinvntac();
		if (branch1.getBalinvntac() == null) {
			throw new Exception(
					"No hay una cuenta contable de Inventario asignada al almacen");
		}

		// cuenta asignada a ventas
		AccassignmentTO acc = new AccassignmentTO();
		AccountingDAO accDAO = new AccountingDAO();
		acc = accDAO.getAccAssignment();

		V_local = acc.getLinkact_1();
		if (V_local == null) {
			throw new Exception(
					"No hay una cuenta contable asignada a ingreso por Venta ");
		}
		costo_venta = acc.getCogm_act();

		if (costo_venta == null) {
			throw new Exception(
					"No hay una cuenta contable asignada a Costo de Ventas ");
		}

		// asiento contable
		JournalEntryLinesTO art1 = new JournalEntryLinesTO();
		JournalEntryLinesTO art2 = new JournalEntryLinesTO();
		JournalEntryLinesTO art3 = new JournalEntryLinesTO();
		JournalEntryLinesTO art4 = new JournalEntryLinesTO();
		JournalEntryLinesTO art5 = new JournalEntryLinesTO();
		JournalEntryLinesTO art6 = new JournalEntryLinesTO();
		JournalEntryLinesTO art7 = new JournalEntryLinesTO();
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// llenado del asiento contable
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// LLenado del padre
		List detail = new Vector();
		nuevo.setObjtype("5");
		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setLoctotal(bussines + branch);
		nuevo.setSystotal(bussines + branch);

		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setDuedate(parameters.getDocdate());
		nuevo.setTaxdate(parameters.getTaxdate());
		nuevo.setBtfstatus("O");
		nuevo.setTranstype(parameters.getObjtype());
		nuevo.setBaseref(parameters.getRef1());
		nuevo.setRefdate(parameters.getDocduedate());

		nuevo.setRef1(parameters.getRef1());
		nuevo.setRefndrprt("N");
		nuevo.setAdjtran("N");
		nuevo.setAutostorno("N");
		nuevo.setAutovat("N");
		nuevo.setPrinted("N");
		nuevo.setAutowt("N");
		nuevo.setDeferedtax("N");

		// llenado de los
		// hijos---------------------------------------------------------------------------------------------------
		// cuenta del socio de negocio
		art1.setLine_id(1);

		art1.setDebit(bussines);
		art1.setAccount(buss_c);
		art1.setDuedate(parameters.getDocdate());
		art1.setShortname(buss_c);
		art1.setContraact(V_local);
		art1.setLinememo("venta de mercancias");
		art1.setRefdate(parameters.getDocdate());
		art1.setRef1(parameters.getRef1());
		// ar1.setRef2();
		art1.setBaseref(parameters.getRef1());
		art1.setTaxdate(parameters.getTaxdate());
		// art1.setFinncpriod(finncpriod);
		art1.setReltransid(-1);
		art1.setRellineid(-1);
		art1.setReltype("N");
		art1.setObjtype("5");
		art1.setVatline("N");
		art1.setVatamount(0.0);
		art1.setClosed("N");
		art1.setGrossvalue(0.0);
		art1.setBalduedeb(bussines);
		art1.setBalduecred(0.0);
		art1.setIsnet("Y");
		art1.setTaxtype(0);
		art1.setTaxpostacc("N");
		art1.setTotalvat(0.0);
		art1.setWtliable("N");
		art1.setWtline("N");
		art1.setPayblock("N");
		art1.setOrdered("N");
		art1.setTranstype(parameters.getObjtype());
		detail.add(art1);

		// cuenta asignada al almacen
		art2.setLine_id(2);
		art2.setAccount(branch_c);

		art2.setCredit(branch);
		art2.setDuedate(parameters.getDocdate());
		art2.setShortname(branch_c);
		art2.setContraact(buss_c);
		art2.setLinememo("venta  de mercancias");
		art2.setRefdate(parameters.getDocdate());
		art2.setRef1(parameters.getRef1());
		// art2.setRef2();
		art2.setBaseref(parameters.getRef1());
		art2.setTaxdate(parameters.getTaxdate());
		// art1.setFinncpriod(finncpriod);
		art2.setReltransid(-1);
		art2.setRellineid(-1);
		art2.setReltype("N");
		art2.setObjtype("5");
		art2.setVatline("N");
		art2.setVatamount(0.0);
		art2.setClosed("N");
		art2.setGrossvalue(0.0);
		art2.setBalduedeb(0.0);
		art2.setBalduecred(branch);
		art2.setIsnet("Y");
		art2.setTaxtype(0);
		art2.setTaxpostacc("N");
		art2.setTotalvat(0.0);
		art2.setWtliable("N");
		art2.setWtline("N");
		art2.setPayblock("N");
		art2.setOrdered("N");
		art2.setTranstype(parameters.getObjtype());
		detail.add(art2);

		// ------------------------
		// para la cuenta de ventas
		// -----------------------------

		art4.setLine_id(3);

		art4.setCredit(sale);
		art4.setAccount(V_local);
		art4.setDuedate(parameters.getDocduedate());
		art4.setShortname(V_local);
		art4.setContraact(buss_c);
		art4.setLinememo("venta de mercancias");
		art4.setRefdate(parameters.getDocduedate());
		art4.setRef1(parameters.getRef1());
		// art2.setRef2();
		art4.setBaseref(parameters.getRef1());
		art4.setTaxdate(parameters.getDocduedate());
		// 4art1.setFinncpriod(finncpriod);
		art4.setReltransid(-1);
		art4.setRellineid(-1);
		art4.setReltype("N");
		art4.setObjtype("5");
		art4.setVatline("N");
		art4.setVatamount(0.0);
		art4.setClosed("N");
		art4.setGrossvalue(0.0);
		art4.setBalduedeb(0.0);
		art4.setBalduecred(sale);
		art4.setIsnet("Y");
		art4.setTaxtype(0);
		art4.setTaxpostacc("N");
		art4.setTotalvat(0.0);
		art4.setWtliable("N");
		art4.setWtline("N");
		art4.setPayblock("N");
		art4.setOrdered("N");
		art4.setTranstype(parameters.getObjtype());
		detail.add(art4);
		// ------------------------
		// para la cuenta de costo de ventas
		// -----------------------------

		art5.setLine_id(4);
		art5.setDebit(branch);

		art5.setAccount(costo_venta);
		art5.setDuedate(parameters.getDocduedate());
		art5.setShortname(costo_venta);
		art5.setContraact(branch_c);
		art5.setLinememo("venta de mercancias");
		art5.setRefdate(parameters.getDocduedate());
		art5.setRef1(parameters.getRef1());
		// art2.setRef2();
		art5.setBaseref(parameters.getRef1());
		art5.setTaxdate(parameters.getDocduedate());
		// 4art1.setFinncpriod(finncpriod);
		art5.setReltransid(-1);
		art5.setRellineid(-1);
		art5.setReltype("N");
		art5.setObjtype("5");
		art5.setVatline("N");
		art5.setVatamount(0.0);
		art5.setClosed("N");
		art5.setGrossvalue(0.0);
		art5.setBalduedeb(branch);
		art5.setBalduecred(0.0);
		art5.setIsnet("Y");
		art5.setTaxtype(0);
		art5.setTaxpostacc("N");
		art5.setTotalvat(0.0);
		art5.setWtliable("N");
		art5.setWtline("N");
		art5.setPayblock("N");
		art5.setOrdered("N");
		art5.setTranstype(parameters.getObjtype());
		detail.add(art5);
		// cuenta de cotrans y fovial si se aplica el impuesto
		if (tax != 0.00) {

			// cuenta de iva
			art3.setLine_id(5);

			art3.setCredit(tax);
			art3.setAccount(iva_c);
			art3.setDuedate(parameters.getDocduedate());
			art3.setShortname(iva_c);
			art3.setContraact(buss_c);
			art3.setLinememo("venta de mercancias");
			art3.setRefdate(parameters.getDocduedate());
			art3.setRef1(parameters.getRef1());
			// art2.setRef2();
			art3.setBaseref(parameters.getRef1());
			art3.setTaxdate(parameters.getDocduedate());
			// 3art1.setFinncpriod(finncpriod);
			art3.setReltransid(-1);
			art3.setRellineid(-1);
			art3.setReltype("N");
			art3.setObjtype("5");
			art3.setVatline("N");
			art3.setVatamount(0.0);
			art3.setClosed("N");
			art3.setGrossvalue(0.0);
			art3.setBalduedeb(0.0);
			art3.setBalduecred(tax);
			art3.setIsnet("Y");
			art3.setTaxtype(0);
			art3.setTaxpostacc("N");
			art3.setTotalvat(0.0);
			art3.setWtliable("N");
			art3.setWtline("N");
			art3.setPayblock("N");
			art3.setOrdered("N");
			art3.setTranstype(parameters.getObjtype());
			detail.add(art3);

		}

		if (fovc != 0.0) {
			art6.setLine_id(6);

			art6.setCredit(fovc);
			art6.setAccount(fovial);
			art6.setDuedate(parameters.getDocduedate());
			art6.setShortname(fovial);
			art6.setContraact(buss_c);
			art6.setLinememo("Venta  de mercancias");
			art6.setRefdate(parameters.getDocduedate());
			art6.setRef1(parameters.getRef1());
			// art2.setRef2();
			art6.setBaseref(parameters.getRef1());
			art6.setTaxdate(parameters.getDocduedate());
			// 4rt1.setFinncpriod(finncpriod);
			art6.setReltransid(-1);
			art6.setRellineid(-1);
			art6.setReltype("N");
			art6.setObjtype("5");
			art6.setVatline("N");
			art6.setVatamount(0.0);
			art6.setClosed("N");
			art6.setGrossvalue(0.0);
			art6.setBalduedeb(0.0);
			art6.setBalduecred(fovc);
			art6.setIsnet("Y");
			art6.setTaxtype(0);
			art6.setTaxpostacc("N");
			art6.setTotalvat(0.0);
			art6.setWtliable("N");
			art6.setWtline("N");
			art6.setPayblock("N");
			art6.setOrdered("N");
			art6.setTranstype(parameters.getObjtype());
			detail.add(art6);
		}
		if (cotrans != 0.0) {
			art7.setLine_id(7);

			art7.setCredit(cotrans);
			art7.setAccount(cotrans_C);
			art7.setDuedate(parameters.getDocduedate());
			art7.setShortname(cotrans_C);
			art7.setContraact(buss_c);
			art7.setLinememo("venta de mercancias");
			art7.setRefdate(parameters.getDocduedate());
			art7.setRef1(parameters.getRef1());
			// art2.setRef2();
			art7.setBaseref(parameters.getRef1());
			art7.setTaxdate(parameters.getDocduedate());
			// 4rt1.setFinncpriod(finncpriod);
			art7.setReltransid(-1);
			art7.setRellineid(-1);
			art7.setReltype("N");
			art7.setObjtype("5");
			art7.setVatline("N");
			art7.setVatamount(0.0);
			art7.setClosed("N");
			art7.setGrossvalue(0.0);
			art7.setBalduedeb(0.0);
			art7.setBalduecred(cotrans);
			art7.setIsnet("Y");
			art7.setTaxtype(0);
			art7.setTaxpostacc("N");
			art7.setTotalvat(0.0);
			art7.setWtliable("N");
			art7.setWtline("N");
			art7.setPayblock("N");
			art7.setOrdered("N");
			art7.setTranstype(parameters.getObjtype());
			detail.add(art7);
		}
		nuevo.setJournalentryList(detail);

		nuevo = fill_JournalEntry_Unir(nuevo);
		return nuevo;
	}

	public JournalEntryTO fill_JournalEntry_Unir(JournalEntryTO parameters)
			throws Exception {
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		boolean ind = false;
		Double total = zero;
		Double sum_debe = 0.0;
		Double sum_credit = 0.0;
		int n = 1;
		// copiando la lista de los detalles de el asiento contable
		List list = parameters.getJournalentryList();
		// --------------------------------------------------------
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();

		// recorre la lista de detalles
		for (Object obj : list) {
			ind = false;
			JournalEntryLinesTO good = (JournalEntryLinesTO) obj;
			String cod = good.getAccount();
			List lisHija = new Vector();

			// comparando lista aux de nodos visitados
			for (Object obj2 : aux) {
				JournalEntryLinesTO good2 = (JournalEntryLinesTO) obj2;
				if (cod.equals(good2.getAccount())) {
					ind = true;
				}
			}
			// compara el codigo de cuenta para hacer una sumatoria y guardarlo
			// en otra lista
			if (ind == false) {
				for (Object obj3 : list) {
					JournalEntryLinesTO good3 = (JournalEntryLinesTO) obj3;
					if (cod.equals(good3.getAccount())) {
						lisHija.add(good3);
					}
				}
				// guarda en la lista de listas
				listas.add(lisHija);
			}

			aux.add(good);

		}

		// recorre la lista de listas para encontrar los detalles de el asiento
		// contable
		List detail = new Vector();
		for (List obj1 : listas) {
			List listaDet = obj1;
			Double sum = zero;
			String acc = null;
			String c_acc = null;
			sum_debe = zero;
			sum_credit = zero;
			for (Object obj2 : listaDet) {
				JournalEntryLinesTO oldjournal = (JournalEntryLinesTO) obj2;
				if (oldjournal.getDebit() == null) {
					oldjournal.setDebit(0.0);
				}
				if (oldjournal.getCredit() == null) {
					oldjournal.setCredit(zero);
				}
				sum_debe = sum_debe + oldjournal.getDebit();
				sum_credit = sum_credit + oldjournal.getCredit();
				acc = oldjournal.getAccount();
				c_acc = oldjournal.getContraact();
			}

			// asiento contable

			JournalEntryLinesTO art1 = new JournalEntryLinesTO();
			// -----------------------------------------------------------------------------------
			// encontrando el saldo si es deudor o acreedor
			// -----------------------------------------------------------------------------------
			Double saldo = sum_debe - sum_credit;
			if (saldo != 0) {
				if (saldo > 0) {
					art1.setDebit(saldo);
					art1.setBalduedeb(saldo);
					art1.setBalduecred(zero);
				} else {
					saldo = saldo * -1;
					art1.setCredit(saldo);
					art1.setBalduecred(saldo);
					art1.setBalduedeb(zero);
				}
			} else {
				art1.setDebit(zero);
				art1.setBalduedeb(saldo);
				art1.setBalduecred(zero);
			}

			// --------------------------------------------------------------------------------------------------------------------------------------------------------
			// llenado del asiento contable
			// --------------------------------------------------------------------------------------------------------------------------------------------------------

			art1.setLine_id(n);
			art1.setAccount(acc);
			art1.setDuedate(parameters.getDuedate());
			art1.setShortname(acc);
			art1.setContraact(c_acc);
			art1.setLinememo("venta de mercaderia");
			art1.setRefdate(parameters.getDuedate());
			art1.setRef1(parameters.getRef1());
			// art1.setRef2();
			art1.setBaseref(parameters.getRef1());
			art1.setTaxdate(parameters.getTaxdate());
			// art1.setFinncpriod(finncpriod);
			art1.setReltransid(-1);
			art1.setRellineid(-1);
			art1.setReltype("N");
			art1.setObjtype("5");
			art1.setVatline("N");
			art1.setVatamount(zero);
			art1.setClosed("N");
			art1.setGrossvalue(zero);
			art1.setIsnet("Y");
			art1.setTaxtype(0);
			art1.setTaxpostacc("N");
			art1.setTotalvat(0.0);
			art1.setWtliable("N");
			art1.setWtline("N");
			art1.setPayblock("N");
			art1.setOrdered("N");
			art1.setTranstype(parameters.getTranstype());
			detail.add(art1);
			n++;

		}
		nuevo.setBtfstatus("O");
		nuevo.setTranstype(parameters.getTranstype());
		nuevo.setBaseref(parameters.getBaseref());
		nuevo.setRefdate(parameters.getRefdate());
		nuevo.setMemo(parameters.getMemo());
		nuevo.setRef1(parameters.getRef1());
		nuevo.setRef2(parameters.getRef2());
		nuevo.setLoctotal(parameters.getLoctotal());
		nuevo.setSystotal(parameters.getSystotal());
		nuevo.setTransrate(zero);
		nuevo.setDuedate(parameters.getDuedate());
		nuevo.setTaxdate(parameters.getTaxdate());
		nuevo.setFinncpriod(0);
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setRefndrprt("N");
		nuevo.setObjtype("5");
		nuevo.setAdjtran("N");
		nuevo.setAutostorno("N");
		nuevo.setSeries(0);
		nuevo.setAutovat("N");

		nuevo.setDocseries(0);
		nuevo.setPrinted("N");
		nuevo.setAutowt("N");
		nuevo.setDeferedtax("N");
		nuevo.setJournalentryList(detail);

		return nuevo;

	}

	public JournalEntryTO fill_JournalEntry_pago(SalesTO parameters)
			throws Exception {

		String buss_c;

		double bussines = 0.0;
		double branch = 0.0;
		double tax = 0.0;
		double fovc = 0.0;
		double sale = 0.0;

		double impuesto = 0.0;
		
		
		JournalEntryTO nuevo = new JournalEntryTO();

		ArticlesTO arti = new ArticlesTO();

		List list = parameters.getSalesDetails();

		// recorre la lista de detalles
		 AdminDAO admin = new AdminDAO();
		CatalogTO Catalog = new CatalogTO();
		Catalog = admin.findCatalogByKey("IVA", 10);

		 ParameterDAO admin1 = new ParameterDAO();
		parameterTO Catalog1 = new parameterTO();
		Catalog1 = admin1.getParameterbykey(7);

		for (Object obj : list) {
			SalesDetailTO good = (SalesDetailTO) obj;

			// calculando los impuestos y saldo de las cuentas
			// --------------------------------------------------------------------------------

			arti = good.getArticle();
			branch = branch + (arti.getAvgPrice() * good.getQuantity());
			sale = sale + good.getLinetotal();
			// calculando el iva validando si el producto esta exento o de iva
			if (good.getTaxstatus().equals("Y")) {

				impuesto = good.getLinetotal()
						* (Double.parseDouble(Catalog.getCatvalue()) / 100);
				fovc = fovc + (good.getVatsum() - impuesto);
				tax = tax + impuesto;

			}
			bussines = bussines
					+ ((good.getVatsum() - impuesto) + impuesto + good
							.getLinetotal());
		}
		// consultando en la base de datos los codigos de cuenta asignados
		// cuenta de bussines partner
		buss_c = parameters.getCtlaccount();

		// asiento contable
		JournalEntryLinesTO art1 = new JournalEntryLinesTO();
		JournalEntryLinesTO art2 = new JournalEntryLinesTO();

		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// llenado del asiento contable
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// LLenado del padre
		List detail = new Vector();
		nuevo.setObjtype("5");
		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setLoctotal(bussines);
		nuevo.setSystotal(bussines);
		nuevo.setMemo("Pago de factura");
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setDuedate(parameters.getDocdate());
		nuevo.setTaxdate(parameters.getTaxdate());
		nuevo.setBtfstatus("O");
		nuevo.setTranstype(parameters.getObjtype());
		nuevo.setBaseref(parameters.getRef1());
		nuevo.setRefdate(parameters.getDocduedate());
		nuevo.setRef1(parameters.getRef1());
		nuevo.setRefndrprt("N");
		nuevo.setAdjtran("N");
		nuevo.setAutostorno("N");
		nuevo.setAutovat("N");
		nuevo.setPrinted("N");
		nuevo.setAutowt("N");
		nuevo.setDeferedtax("N");

		// llenado de los
		// hijos---------------------------------------------------------------------------------------------------
		// cuenta del socio de negocio
		art1.setLine_id(1);
		art1.setCredit(bussines);
		art1.setAccount(buss_c);
		art1.setDuedate(parameters.getDocdate());
		art1.setShortname(buss_c);
		art1.setContraact(Catalog1.getValue1());
		art1.setLinememo("Pago de Factura");
		art1.setRefdate(parameters.getDocdate());
		art1.setRef1(parameters.getRef1());
		// ar1.setRef2();
		art1.setBaseref(parameters.getRef1());
		art1.setTaxdate(parameters.getTaxdate());
		// art1.setFinncpriod(finncpriod);
		art1.setReltransid(-1);
		art1.setRellineid(-1);
		art1.setReltype("N");
		art1.setObjtype("5");
		art1.setVatline("N");
		art1.setVatamount(0.0);
		art1.setClosed("N");
		art1.setGrossvalue(0.0);
		art1.setBalduedeb(0.0);
		art1.setBalduecred(bussines);
		art1.setIsnet("Y");
		art1.setTaxtype(0);
		art1.setTaxpostacc("N");
		art1.setTotalvat(0.0);
		art1.setWtliable("N");
		art1.setWtline("N");
		art1.setPayblock("N");
		art1.setOrdered("N");
		art1.setTranstype(parameters.getObjtype());
		detail.add(art1);
		// ---------------------------------------------------------------------------------------------------
		// cuenta del socio de negocio
		// ---------------------------------------------------------------------------------------------------
		art2.setLine_id(2);
		art2.setDebit(bussines);
		art2.setAccount(Catalog1.getValue1());
		art2.setDuedate(parameters.getDocdate());
		art2.setShortname(Catalog1.getValue1());
		art2.setContraact(buss_c);
		art2.setLinememo("Pago de Factura");
		art2.setRefdate(parameters.getDocdate());
		art2.setRef1(parameters.getRef1());
		// r1.setRef2();
		art2.setBaseref(parameters.getRef1());
		art2.setTaxdate(parameters.getTaxdate());
		// art1.setFinncpriod(finncpriod);
		art2.setReltransid(-1);
		art2.setRellineid(-1);
		art2.setReltype("N");
		art2.setObjtype("5");
		art2.setVatline("N");
		art2.setVatamount(0.0);
		art2.setClosed("N");
		art2.setGrossvalue(0.0);
		art2.setBalduedeb(bussines);
		art2.setBalduecred(0.0);
		art2.setIsnet("Y");
		art2.setTaxtype(0);
		art2.setTaxpostacc("N");
		art2.setTotalvat(0.0);
		art2.setWtliable("N");
		art2.setWtline("N");
		art2.setPayblock("N");
		art2.setOrdered("N");
		art2.setTranstype(parameters.getObjtype());
		detail.add(art2);

		nuevo.setJournalentryList(detail);
		return nuevo;
	}

	public ResultOutTO validateSale(SalesTO parameters) throws EJBException {
		// Variables

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
		// ------------------------------------------------------------------------------------------------------------
		// Validacion del limite de credito
		// ------------------------------------------------------------------------------------------------------------

		AdminDAO ad = new AdminDAO();
		CatalogTO cat = new CatalogTO();
		try {
			cat = ad.findCatalogByKey(parameters.getPeymethod(), 8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// si es necesaria una validacion de credito respecto a la foma de pago
		if (!cat.getCatvalue3().equals("N")) {

			_return = Businesspartner.validate_limit(parameters);
			if (_return.getCodigoError() != 0) {
				_return.setCodigoError(1);

				return _return;
			}
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

				if (branch1.getWhscode().equals(salesDetail.getWhscode())) {
					if (branch1.isIsasociated() && branch1.getLocked() != null
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
					stocks = stocks
							+ (articleDetalle2.getQuantity() * DBArticle
									.getNumInSale());
				}
			}

			branch = DBArticle.getBranchArticles();
			Quantity = 0.0;
			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;
				if (branch1.getWhscode().equals(salesDetail.getWhscode())) {

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

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------------------------
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

	// -----------------------------------------------------------------------------------------------------------------------------------------------
	// nota de credito
	// -----------------------------------------------------------------------------------------------------------------------------------------------

	public ResultOutTO inv_ClientCredi_mtto(ClientCrediTO parameters, int action)
			throws EJBException {

		// Declaración de variables

		ResultOutTO _valid = new ResultOutTO();

		ResultOutTO _return = new ResultOutTO();
		SalesDAO DAO = new SalesDAO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Validar acción a realizar
		// --------------------------------------------------------------------------------------------------------------------------------

		if (action != Common.MTTOINSERT) {
			_return = inv_ClientCredit_update(parameters, action);
			return _return;
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Asignación de valores por defecto y llenado:
		// Estas se realizan solo para cuando es guardar, el actualizar y borrar
		// no aplican.
		// --------------------------------------------------------------------------------------------------------------------------------
		parameters = fillClientCredi(parameters);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Hacer validaciones:
		// Estas se realizan solo para cuando es guardar, el actualizar y borrar
		// no aplican para validaciones
		// --------------------------------------------------------------------------------------------------------------------------------

		_valid = validateClientCredi(parameters);

		if (_valid.getCodigoError() != 0) {
			return _valid;
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar en base:
		// Desde aqui se debe manajar como una transaccion global, cada metodo
		// debe tener una opción para ejecutarlo como parte de una transacción
		// global
		// --------------------------------------------------------------------------------------------------------------------------------

		try {
			DAO.setIstransaccional(true);
			_return = save_TransactionClientCredi(parameters, action,
					DAO.getConn());
			DAO.forceCommit();

		} catch (Exception e) {
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public ResultOutTO inv_ClientCredit_update(ClientCrediTO parameters,
			int action) throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		ClientCrediDAO DAO = new ClientCrediDAO();
		try {
			_return = inv_ClientCredit_update(parameters, action, DAO.getConn());
			DAO.forceCommit();
		} catch (Exception e) {
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {
			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos Alamacenados Con Exito");
		return _return;

	}

	public ResultOutTO inv_ClientCredit_update(ClientCrediTO parameters,
			int action, Connection conn) throws Exception {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		ClientCrediDAO DAO = new ClientCrediDAO(conn);
		DAO.setIstransaccional(true);
		ClientCrediDetailDAO DAO1 = new ClientCrediDetailDAO(conn);
		DAO1.setIstransaccional(true);

		// Actualizar/borrar encabezados
		_return.setDocentry(DAO.inv_ClientCredi_mtto(parameters, action));

		// Borrar detalles
		Iterator<ClientCrediDetailTO> iterator = parameters.getclientDetails()
				.iterator();
		if (action == Common.MTTODELETE) {
			while (iterator.hasNext()) {
				ClientCrediDetailTO detalleReceipt = (ClientCrediDetailTO) iterator
						.next();

				DAO1.inv_ClientCrediDetail_mtto(detalleReceipt,
						Common.MTTODELETE);
			}
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos Actualizados con exito");
		return _return;
	}

	private ClientCrediTO fillClientCredi(ClientCrediTO parameters) {

		// variables
		Double total = zero;
		Double vatsum = zero;
		ArticlesTO DBArticle = new ArticlesTO();
		AdminDAO admin = new AdminDAO();
		int basentry = 0;

		BranchTO branch1 = new BranchTO();
		// buscando la cuenta asignada de cuenta de existencias al almacen

		try {
			branch1 = admin.getBranchByKey(parameters.getTowhscode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String branch_c = branch1.getBalinvntac();
		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto detalle
		// --------------------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings("unchecked")
		Iterator<ClientCrediDetailTO> iterator = parameters.getclientDetails()
				.iterator();
		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto encabezado
		// --------------------------------------------------------------------------------------------------------------------------------
		while (iterator.hasNext()) {
			ClientCrediDetailTO articleDetalle = (ClientCrediDetailTO) iterator
					.next();

			AdminEJB EJB = new AdminEJB();
			DBArticle = EJB.getArticlesByKey(articleDetalle.getItemcode());

			// Asignar a documento
			articleDetalle.setArticle(DBArticle);
			articleDetalle.setAcctcode(branch_c);
			// Valores por defecto
			articleDetalle.setDocentry(parameters.getDocentry());
			articleDetalle.setTargettype(-1);
			// articleDetalle.setBaseref("");
			articleDetalle.setBasetype(-1);
			articleDetalle.setLinestatus("O");
			articleDetalle.setDscription(DBArticle.getItemName());
			articleDetalle.setDiscprcnt(zero);
			articleDetalle.setWhscode(parameters.getTowhscode());
			// articleDetalle.setAcctcode(acctcode);
			articleDetalle.setTaxstatus("Y");
			articleDetalle.setOcrcode(parameters.getTowhscode());
			articleDetalle.setFactor1(zero);
			articleDetalle.setObjtype("11");
			articleDetalle.setGrssprofit(articleDetalle.getGrssprofit());
			articleDetalle.setVatappld(zero);
			articleDetalle.setUnitmsr(DBArticle.getBuyUnitMsr());
			articleDetalle.setStockpricestockprice(articleDetalle.getStockpricestockprice());

			// Calculo de impuesto
			vatsum = vatsum + articleDetalle.getVatsum();

			// Calculo de totales
			articleDetalle.setLinetotal(articleDetalle.getQuantity()
					* articleDetalle.getPrice());
			articleDetalle.setOpenqty(articleDetalle.getQuantity());
			total = total + articleDetalle.getGtotal();
			basentry = articleDetalle.getBaseentry();

		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto encabezado
		// --------------------------------------------------------------------------------------------------------------------------------
		java.util.Date utilDate = new java.util.Date(); // fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
		parameters.setDocduedate(sqlDate);
		parameters.setDocnum(parameters.getDocentry());
		parameters.setDoctype("I");
		parameters.setCanceled("N");
		parameters.setDocstatus("O");
		parameters.setObjtype("11");
		parameters.setVatsum(vatsum);
		parameters.setDiscsum(zero);
		parameters.setDoctotal(total);
		parameters.setRef1(Integer.toString(parameters.getDocnum()));
		parameters
				.setJrnlmemo("Nota de Credito  - " + parameters.getCardcode());
		// parameters.setReceiptnum(0);
		parameters.setGroupnum(0);
		parameters.setConfirmed("Y");
		parameters.setCreatetran("Y");
		// parameters.setReceiptnum(basentr);
		parameters.setRounddif(zero);
		parameters.setRounding("N");
		// parameters.setCtlaccount(ctlaccount); Aqui deberia de hacerse la
		// consulta he incluirse la cuenta contables
		parameters.setPaidsum(zero);
		parameters.setNret(zero);

		return parameters;
	}

	public ResultOutTO save_TransactionClientCredi(ClientCrediTO clientcredi,
			int action, Connection conn) throws Exception {

		// Variables
		List transactions = new Vector();
		InventoryLogTO inventoryLog = new InventoryLogTO();
		WarehouseJournalTO warehouseJournal = new WarehouseJournalTO();
		WarehouseJournalLayerTO warehouseJournallayer = new WarehouseJournalLayerTO();
		ResultOutTO _return = new ResultOutTO();
		ResultOutTO res_invet = new ResultOutTO();
		ResultOutTO res_whj = new ResultOutTO();
		ResultOutTO res_whjl = new ResultOutTO();
		ResultOutTO res_jour = new ResultOutTO();
		ResultOutTO res_UpdateOnhand = new ResultOutTO();
		ClientCrediDAO DAO = new ClientCrediDAO(conn);
		transactionEJB trans = new transactionEJB();
		JournalEntryTO journal = new JournalEntryTO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar Encabezados y detalles de Entrada
		// --------------------------------------------------------------------------------------------------------------------------------

		_return = inv_ClientCredi_save(clientcredi, action, conn);
		clientcredi.setDocentry(_return.getDocentry());
		clientcredi.setDocnum(_return.getDocentry());
		clientcredi.setRef1(Integer.toString(clientcredi.getDocnum()));

		// --------------------------------------------------------------------------------------------------------------------------------
		// Llenar objeto tipo transacción
		// --------------------------------------------------------------------------------------------------------------------------------

		transactions = fill_transaction(clientcredi);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Calculo y actualizacion de existencias y costos
		// --------------------------------------------------------------------------------------------------------------------------------
		for (Object object : transactions) {
			TransactionTo ivt = (TransactionTo) object;
			ivt = trans.calculate(ivt);
			TransactionDAO transDAO = new TransactionDAO(conn);
			transDAO.setIstransaccional(true);
			res_UpdateOnhand = transDAO.Update_Onhand_articles(ivt);
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Registro de Inventory Log
		// --------------------------------------------------------------------------------------------------------------------------------

		for (Object object : transactions) {
			TransactionTo ivt = (TransactionTo) object;

			inventoryLog = trans.fill_Inventory_Log(ivt);
			res_invet = trans.save_Inventory_Log(inventoryLog, conn);
			// Asignación de codigo MessageId
			ivt.setMessageid(res_invet.getDocentry());
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Registro de Warehouse Journal
		// --------------------------------------------------------------------------------------------------------------------------------

		for (Object object : transactions) {
			TransactionTo ivt = (TransactionTo) object;
			warehouseJournal = trans.fill_WarehouseJournal(ivt);
			// Asiganción de TransSeq
			res_whj = trans.save_WarehouseJournal(warehouseJournal, conn);
			ivt.setTransseq(res_whj.getDocentry());
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Registro de Warehouse Journal layer
		// --------------------------------------------------------------------------------------------------------------------------------

		for (Object object : transactions) {

			TransactionTo ivt = (TransactionTo) object;
			warehouseJournallayer = trans.fill_WarehouseJournalLayer(ivt);
			// Asiganción de TransSeq
			res_whjl = trans.save_WarehouseJournalLayer(warehouseJournallayer,
					conn);
		}

		SalesTO sales = new SalesTO();
		sales = getSalesByKey(clientcredi.getReceiptnum());

		// --------------------------------------------------------------------------------------------------------------------------------
		// Actualizacion de factura de ventas validando si proviene de factura
		// de ventas
		// --------------------------------------------------------------------------------------------------------------------------------

		if (!sales.equals(null)) {

			if (!sales.getDocstatus().equals("C")) {
				// -----------------------------------------------------------------------------------
				// registro del asiento contable y actualización de saldos
				// -----------------------------------------------------------------------------------

				journal = fill_JournalEntry(clientcredi);

				AccountingEJB account = new AccountingEJB();
				res_jour = account.journalEntry_mtto(journal,
						Common.MTTOINSERT, conn);

				// -----------------------------------------------------------------------------------
				// Actualización de documento con datos de Asiento contable
				// -----------------------------------------------------------------------------------

				clientcredi.setTransid(res_jour.getDocentry());
				_return = inv_ClientCredit_update(clientcredi,
						Common.MTTOUPDATE, conn);

			} else {

				// -----------------------------------------------------------------------------------
				// registro del asiento contable cuando ya a sido pagado y
				// actualización de saldos
				// -----------------------------------------------------------------------------------

				journal = fill_JournalEntry_pago(clientcredi);

				AccountingEJB account1 = new AccountingEJB();
				res_jour = account1.journalEntry_mtto(journal,
						Common.MTTOINSERT, conn);

				// -----------------------------------------------------------------------------------
				// Actualización de documento con datos de Asiento contable
				// -----------------------------------------------------------------------------------

				clientcredi.setPaidtodate(clientcredi.getDocdate());
				clientcredi.setPaidsum(journal.getSystotal());
				// -----------------------------------------------------------------------------------
				// registro del asiento contable y actualización de saldos
				// -----------------------------------------------------------------------------------

				journal = fill_JournalEntry(clientcredi);

				AccountingEJB account = new AccountingEJB();
				res_jour = account.journalEntry_mtto(journal,
						Common.MTTOINSERT, conn);

				// -----------------------------------------------------------------------------------
				// Actualización de documento con datos de Asiento contable
				// -----------------------------------------------------------------------------------

				clientcredi.setTransid(res_jour.getDocentry());
				_return = inv_ClientCredit_update(clientcredi,
						Common.MTTOUPDATE, conn);
			}
			// -----------------------------------------------------------------------------------
			// Actualización de documento de ventas
			// -----------------------------------------------------------------------------------

			ResultOutTO res_sales = new ResultOutTO();
			sales = update_lines(sales, clientcredi);
			res_sales = inv_Sales_update(sales, Common.MTTOUPDATE, conn);

		} else {
			// si la nota de credito se hiso sin una factura de venta como
			// referencia
			journal = fill_JournalEntry(clientcredi);

			AccountingEJB account = new AccountingEJB();
			res_jour = account.journalEntry_mtto(journal, Common.MTTOINSERT,
					conn);
			clientcredi.setTransid(res_jour.getDocentry());
			_return = inv_ClientCredit_update(clientcredi, Common.MTTOUPDATE,
					conn);
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos Guardados Con Exito");
		return _return;
	}

	private List fill_transaction(ClientCrediTO document) {
		List _return = new Vector();

		Iterator<ClientCrediDetailTO> iterator = document.getclientDetails()
				.iterator();
		while (iterator.hasNext()) {
			ClientCrediDetailTO detail = (ClientCrediDetailTO) iterator.next();

			TransactionTo transaction = new TransactionTo();
			transaction.setTransseq(0);
			transaction.setDocentry(document.getDocentry());
			transaction.setDocnum(Integer.toString(document.getDocnum()));
			transaction.setDocduedate(document.getDocduedate());
			transaction.setDocdate(document.getDocdate());
			transaction.setComment(document.getComments());
			transaction.setJrnlmemo(document.getJrnlmemo());
			transaction.setUsersign(document.getUsersign());
			transaction.setRef1(Integer.toString(document.getDocnum()));
			transaction.setRef2(document.getNumatcard());
			transaction.setLinenum(detail.getLinenum());
			transaction.setItemcode(detail.getItemcode());
			transaction.setDscription(detail.getDscription());
			transaction.setQuantity(detail.getQuantity());
			transaction.setPrice(detail.getPrice());
			transaction.setLinetotal(detail.getLinetotal());
			transaction.setWhscode(detail.getWhscode());
			transaction.setAcctcode(detail.getAcctcode());
			transaction.setOcrcode(document.getCardcode());
			transaction.setVatgroup(detail.getVatgroup());
			transaction.setPriceafvat(detail.getPriceafvat());
			transaction.setVatsum(detail.getVatsum());
			transaction.setObjtype(detail.getObjtype());
			transaction.setGrssprofit(detail.getGrssprofit());
			transaction.setTaxcode(detail.getTaxcode());
			transaction.setVatappld(detail.getVatappld());
			transaction.setStockprice(detail.getPrice());
			transaction.setGtotal(detail.getGtotal());
			transaction.setInqty(zero);
			transaction.setOutqty(zero);
			transaction.setMessageid(0);
			transaction.setBalance(zero);
			transaction.setNewOnhand(zero);
			transaction.setNewWhsOnhand(zero);
			transaction.setNewAvgprice(zero);
			transaction.setArticle(detail.getArticle());

			_return.add(transaction);

			/*
			 * transaction.setTransseq(detail.getTransseq());
			 * transaction.setDocentry(detail.getDocentry());
			 * transaction.setDocnum(detail.getDocnum());
			 * transaction.setDocduedate(detail.getDocduedate());
			 * transaction.setDocdate(detail.getDocdate());
			 * transaction.setComment(detail.getComment());
			 * transaction.setJrnlmemo(detail.getJrnlmemo());
			 * transaction.setUsersign(detail.getUsersign());
			 * transaction.setRef1(detail.getRef1());
			 * transaction.setRef2(detail.getRef2());
			 * transaction.setLinenum(detail.getLinenum());
			 * transaction.setItemcode(detail.getItemcode());
			 * transaction.setDscription(detail.getDscription());
			 * transaction.setQuantity(detail.getQuantity());
			 * transaction.setPrice(detail.getPrice());
			 * transaction.setLinetotal(detail.getLinetotal());
			 * transaction.setWhscode(detail.getWhscode());
			 * transaction.setAcctcode(detail.getAcctcode());
			 * transaction.setOcrcode(detail.getOcrcode());
			 * transaction.setVatgroup(detail.getVatgroup());
			 * transaction.setPriceafvat(detail.getPriceafvat());
			 * transaction.setVatsum(detail.getVatsum());
			 * transaction.setObjtype(detail.getObjtype());
			 * transaction.setGrssprofit(detail.getGrssprofit());
			 * transaction.setTaxcode(detail.getTaxcode());
			 * transaction.setVatappld(detail.getVatappld());
			 * transaction.setStockprice(detail.getStockprice());
			 * transaction.setGtotal(detail.getGtotal());
			 * transaction.setInqty(detail.getInqty());
			 * transaction.setOutqty(detail.getOutqty());
			 * transaction.setMessageid(detail.getMessageid());
			 * transaction.setBalance(detail.getBalance());
			 * transaction.setNewonhand(detail.getNewonhand());
			 * transaction.setNewwhsonhand(detail.getNewwhsonhand());
			 * transaction.setNewavgprice(detail.getNewavgprice());
			 */

		}
		return _return;
	}

	public ResultOutTO inv_ClientCredi_save(ClientCrediTO parameters,
			int action, Connection conn) throws Exception {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		ClientCrediDAO DAO = new ClientCrediDAO(conn);
		DAO.setIstransaccional(true);
		ClientCrediDetailDAO goodDAO1 = new ClientCrediDetailDAO(conn);
		goodDAO1.setIstransaccional(true);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar encabezados
		// --------------------------------------------------------------------------------------------------------------------------------

		_return.setDocentry(DAO.inv_ClientCredi_mtto(parameters, action));

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar detalles
		// --------------------------------------------------------------------------------------------------------------------------------

		Iterator<ClientCrediDetailTO> iterator = parameters.getclientDetails()
				.iterator();
		while (iterator.hasNext()) {
			ClientCrediDetailTO salesdetalle = (ClientCrediDetailTO) iterator
					.next();

			salesdetalle.setDocentry(_return.getDocentry());
			goodDAO1.inv_ClientCrediDetail_mtto(salesdetalle, Common.MTTOINSERT);

		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public ResultOutTO validateClientCredi(ClientCrediTO parameters)
			throws EJBException {
		// Variables

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

				if (branch1.getWhscode().equals(ClientCrediDetail.getWhscode())) {
					if (branch1.isIsasociated() && branch1.getLocked() != null
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

	public JournalEntryTO fill_JournalEntry(ClientCrediTO parameters)
			throws Exception {

		String buss_c;
		String branch_c;
		String iva_c = null;
		String V_local;
		String costo_venta;
		String fovial = null;
		String cotrans_C = null;
		double bussines = 0.0;
		double branch = 0.0;
		double tax = 0.0;
		double fovc = 0.0;
		double cotrans = 0.0;
		double sale = 0.0;
		double costo = 0.0;
		double impuesto = 0.0;
		
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		ArticlesTO arti = new ArticlesTO();
		boolean ind = false;
		Double total = zero;
		List list = parameters.getclientDetails();
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();
		// recorre la lista de detalles

		for (Object obj : list) {
			ClientCrediDetailTO good = (ClientCrediDetailTO) obj;
			String cod = good.getAcctcode();
			List lisHija = new Vector();
			CatalogTO Catalog = new CatalogTO();
			AdminDAO admin = new AdminDAO();

			Catalog = admin.findCatalogByKey(good.getTaxcode(), 10);
			// calculando los impuestos y saldo de las cuentas
			// --------------------------------------------------------------------------------

			arti = good.getArticle();
			branch = branch + (arti.getAvgPrice() * good.getQuantity());
			sale = sale + good.getLinetotal();
			// calculando el iva validando si el producto esta exento o de iva
			if (good.getTaxstatus().equals("Y")) {
				// validar si es FOV
				if (good.getTaxcode().equals("FOV")) {

					admin = new AdminDAO();
					CatalogTO Catalog1 = new CatalogTO();
					Catalog1 = admin.findCatalogByKey("FOV1", 10);
					if (Catalog1.getCatvalue3() == null) {
						throw new Exception(
								"No tiene cuenta asignada para  impuestos");
					}
					if (Catalog1.getCatvalue2() == null) {
						throw new Exception(
								"No tiene cuenta asignada para  impuestos");
					}
					if (Catalog1.getCatvalue() == null) {
						throw new Exception(
								"No tiene cuenta asignada para  impuestos");
					}
					cotrans_C = Catalog1.getCatvalue3();
					fovial = Catalog1.getCatvalue2();
					iva_c = Catalog1.getCatvalue();

					impuesto = good.getLinetotal()
							* (Double.parseDouble(Catalog.getCatvalue()) / 100);
					fovc = fovc
							+ (Double.parseDouble(Catalog.getCatvalue2()) * good
									.getQuantity());
					cotrans = cotrans
							+ (Double.parseDouble(Catalog.getCatvalue3()) * good
									.getQuantity());
					tax = tax + impuesto;

				} else {
					if (parameters.getSeries() == 1) {

						if (Catalog.getCatvalue2() == null) {
							throw new Exception(
									"No tiene cuenta asignada para impuestos");
						}

						iva_c = Catalog.getCatvalue2();
						impuesto = good.getLinetotal()
								* (Double.parseDouble(Catalog.getCatvalue()) / 100);
					} else {
						if (Catalog.getCatvalue3() == null) {
							throw new Exception(
									"No tiene cuenta asignada para impuestos");
						}

						iva_c = Catalog.getCatvalue3();
						impuesto = good.getLinetotal()
								* (Double.parseDouble(Catalog.getCatvalue()) / 100);
					}
					tax = tax + impuesto;
				}

			}

			// sacando el total de venta

			bussines = bussines
					+ ((good.getVatsum() - impuesto) + impuesto + good
							.getLinetotal());

		}

		// consultando en la base de datos los codigos de cuenta asignados
		// cuenta de bussines partner
		buss_c = parameters.getCtlaccount();
		// buscar la cuenta asignada al almacen

		BranchTO branch1 = new BranchTO();
		// buscando la cuenta asignada de cuenta de existencias al almacen
		AdminDAO admin = new AdminDAO();
		branch1 = admin.getBranchByKey(parameters.getTowhscode());
		branch_c = branch1.getBalinvntac();
		if (branch1.getBalinvntac() == null) {
			throw new Exception(
					"No hay una cuenta contable de Inventario asignada al almacen");
		}

		// buscando cuenta asignada para IVA y FOVIAL

		// cuenta asignada a ventas
		AccassignmentTO acc = new AccassignmentTO();
		AccountingDAO accDAO = new AccountingDAO();
		acc = accDAO.getAccAssignment();
		V_local = acc.getLinkact_1();
		costo_venta = acc.getCogm_act();

		// asiento contable
		JournalEntryLinesTO art1 = new JournalEntryLinesTO();
		JournalEntryLinesTO art2 = new JournalEntryLinesTO();
		JournalEntryLinesTO art3 = new JournalEntryLinesTO();
		JournalEntryLinesTO art4 = new JournalEntryLinesTO();
		JournalEntryLinesTO art5 = new JournalEntryLinesTO();
		JournalEntryLinesTO art6 = new JournalEntryLinesTO();
		JournalEntryLinesTO art7 = new JournalEntryLinesTO();
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// llenado del asiento contable
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// LLenado del padre
		List detail = new Vector();
		nuevo.setObjtype("5");
		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setLoctotal(bussines + branch);
		nuevo.setSystotal(bussines + branch);

		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setDuedate(parameters.getDocdate());
		nuevo.setTaxdate(parameters.getTaxdate());
		nuevo.setBtfstatus("O");
		nuevo.setTranstype(parameters.getObjtype());
		nuevo.setBaseref(parameters.getRef1());
		nuevo.setRefdate(parameters.getDocduedate());

		nuevo.setRef1(parameters.getRef1());
		nuevo.setRefndrprt("N");
		nuevo.setAdjtran("N");
		nuevo.setAutostorno("N");
		nuevo.setAutovat("N");
		nuevo.setPrinted("N");
		nuevo.setAutowt("N");
		nuevo.setDeferedtax("N");

		// llenado de los
		// hijos---------------------------------------------------------------------------------------------------
		// cuenta del socio de negocio
		art1.setLine_id(1);

		// art1.setDebit(bussines);
		art1.setCredit(bussines);
		art1.setAccount(buss_c);
		art1.setDuedate(parameters.getDocdate());
		art1.setShortname(buss_c);
		art1.setContraact(V_local);
		art1.setLinememo("anulacion de ventas ");
		art1.setRefdate(parameters.getDocdate());
		art1.setRef1(parameters.getRef1());
		// ar1.setRef2();
		art1.setBaseref(parameters.getRef1());
		art1.setTaxdate(parameters.getTaxdate());
		// art1.setFinncpriod(finncpriod);
		art1.setReltransid(-1);
		art1.setRellineid(-1);
		art1.setReltype("N");
		art1.setObjtype("5");
		art1.setVatline("N");
		art1.setVatamount(0.0);
		art1.setClosed("N");
		art1.setGrossvalue(0.0);
		art1.setBalduedeb(0.0);
		art1.setBalduecred(bussines);
		art1.setIsnet("Y");
		art1.setTaxtype(0);
		art1.setTaxpostacc("N");
		art1.setTotalvat(0.0);
		art1.setWtliable("N");
		art1.setWtline("N");
		art1.setPayblock("N");
		art1.setOrdered("N");
		art1.setTranstype(parameters.getObjtype());
		detail.add(art1);

		// cuenta asignada al almacen
		art2.setLine_id(2);
		art2.setAccount(branch_c);
		art2.setDebit(branch);
		// art2.setCredit(branch);
		art2.setDuedate(parameters.getDocdate());
		art2.setShortname(branch_c);
		art2.setContraact(buss_c);
		art2.setLinememo("anulacion de venta");
		art2.setRefdate(parameters.getDocdate());
		art2.setRef1(parameters.getRef1());
		// art2.setRef2();
		art2.setBaseref(parameters.getRef1());
		art2.setTaxdate(parameters.getTaxdate());
		// art1.setFinncpriod(finncpriod);
		art2.setReltransid(-1);
		art2.setRellineid(-1);
		art2.setReltype("N");
		art2.setObjtype("5");
		art2.setVatline("N");
		art2.setVatamount(0.0);
		art2.setClosed("N");
		art2.setGrossvalue(0.0);
		art2.setBalduedeb(branch);
		art2.setBalduecred(0.0);
		art2.setIsnet("Y");
		art2.setTaxtype(0);
		art2.setTaxpostacc("N");
		art2.setTotalvat(0.0);
		art2.setWtliable("N");
		art2.setWtline("N");
		art2.setPayblock("N");
		art2.setOrdered("N");
		art2.setTranstype(parameters.getObjtype());
		detail.add(art2);

		// ------------------------
		// para la cuenta de ventas
		// -----------------------------

		art4.setLine_id(3);
		art4.setDebit(sale);
		// art4.setCredit(sale);
		art4.setAccount(V_local);
		art4.setDuedate(parameters.getDocduedate());
		art4.setShortname(V_local);
		art4.setContraact(buss_c);
		art4.setLinememo("anulacion de venta");
		art4.setRefdate(parameters.getDocduedate());
		art4.setRef1(parameters.getRef1());
		// art2.setRef2();
		art4.setBaseref(parameters.getRef1());
		art4.setTaxdate(parameters.getDocduedate());
		// 4art1.setFinncpriod(finncpriod);
		art4.setReltransid(-1);
		art4.setRellineid(-1);
		art4.setReltype("N");
		art4.setObjtype("5");
		art4.setVatline("N");
		art4.setVatamount(0.0);
		art4.setClosed("N");
		art4.setGrossvalue(0.0);
		art4.setBalduedeb(sale);
		art4.setBalduecred(0.0);
		art4.setIsnet("Y");
		art4.setTaxtype(0);
		art4.setTaxpostacc("N");
		art4.setTotalvat(0.0);
		art4.setWtliable("N");
		art4.setWtline("N");
		art4.setPayblock("N");
		art4.setOrdered("N");
		art4.setTranstype(parameters.getObjtype());
		detail.add(art4);
		// ------------------------
		// para la cuenta de costo de ventas
		// -----------------------------

		art5.setLine_id(4);
		// art5.setDebit(branch);
		art5.setCredit(branch);
		art5.setAccount(costo_venta);
		art5.setDuedate(parameters.getDocduedate());
		art5.setShortname(costo_venta);
		art5.setContraact(branch_c);
		art5.setLinememo("anulacion de venta");
		art5.setRefdate(parameters.getDocduedate());
		art5.setRef1(parameters.getRef1());
		// art2.setRef2();
		art5.setBaseref(parameters.getRef1());
		art5.setTaxdate(parameters.getDocduedate());
		// 4art1.setFinncpriod(finncpriod);
		art5.setReltransid(-1);
		art5.setRellineid(-1);
		art5.setReltype("N");
		art5.setObjtype("5");
		art5.setVatline("N");
		art5.setVatamount(0.0);
		art5.setClosed("N");
		art5.setGrossvalue(0.0);
		art5.setBalduedeb(0.0);
		art5.setBalduecred(branch);
		art5.setIsnet("Y");
		art5.setTaxtype(0);
		art5.setTaxpostacc("N");
		art5.setTotalvat(0.0);
		art5.setWtliable("N");
		art5.setWtline("N");
		art5.setPayblock("N");
		art5.setOrdered("N");
		art5.setTranstype(parameters.getObjtype());
		detail.add(art5);

		if (tax != 0.00) {
			art3.setLine_id(5);
			art3.setDebit(tax);

			art3.setAccount(iva_c);
			art3.setDuedate(parameters.getDocduedate());
			art3.setShortname(iva_c);
			art3.setContraact(buss_c);
			art3.setLinememo("anulacion de venta");
			art3.setRefdate(parameters.getDocduedate());
			art3.setRef1(parameters.getRef1());
			// art2.setRef2();
			art3.setBaseref(parameters.getRef1());
			art3.setTaxdate(parameters.getDocduedate());
			// 3art1.setFinncpriod(finncpriod);
			art3.setReltransid(-1);
			art3.setRellineid(-1);
			art3.setReltype("N");
			art3.setObjtype("5");
			art3.setVatline("N");
			art3.setVatamount(0.0);
			art3.setClosed("N");
			art3.setGrossvalue(0.0);
			art3.setBalduedeb(0.0);
			art3.setBalduecred(tax);
			art3.setIsnet("Y");
			art3.setTaxtype(0);
			art3.setTaxpostacc("N");
			art3.setTotalvat(0.0);
			art3.setWtliable("N");
			art3.setWtline("N");
			art3.setPayblock("N");
			art3.setOrdered("N");
			art3.setTranstype(parameters.getObjtype());
			detail.add(art3);
		}

		// cuenta de cotrans y fovial si se aplica el impuesto
		if (fovc != 0.0) {
			art6.setLine_id(6);
			art6.setDebit(fovc);
			// art6.setCredit(fovc);
			art6.setAccount(fovial);
			art6.setDuedate(parameters.getDocduedate());
			art6.setShortname(fovial);
			art6.setContraact(buss_c);
			art6.setLinememo("anulacion de venta");
			art6.setRefdate(parameters.getDocduedate());
			art6.setRef1(parameters.getRef1());
			// art2.setRef2();
			art6.setBaseref(parameters.getRef1());
			art6.setTaxdate(parameters.getDocduedate());
			// 4rt1.setFinncpriod(finncpriod);
			art6.setReltransid(-1);
			art6.setRellineid(-1);
			art6.setReltype("N");
			art6.setObjtype("5");
			art6.setVatline("N");
			art6.setVatamount(0.0);
			art6.setClosed("N");
			art6.setGrossvalue(0.0);
			art6.setBalduedeb(fovc);
			art6.setBalduecred(0.0);
			art6.setIsnet("Y");
			art6.setTaxtype(0);
			art6.setTaxpostacc("N");
			art6.setTotalvat(0.0);
			art6.setWtliable("N");
			art6.setWtline("N");
			art6.setPayblock("N");
			art6.setOrdered("N");
			art6.setTranstype(parameters.getObjtype());
			detail.add(art6);
		}
		if (cotrans != 0.0) {
			art7.setLine_id(7);

			// art7.setCredit(cotrans);
			art7.setDebit(cotrans);
			art7.setAccount(cotrans_C);
			art7.setDuedate(parameters.getDocduedate());
			art7.setShortname(cotrans_C);
			art7.setContraact(buss_c);
			art7.setLinememo("anulacion de venta");
			art7.setRefdate(parameters.getDocduedate());
			art7.setRef1(parameters.getRef1());
			// art2.setRef2();
			art7.setBaseref(parameters.getRef1());
			art7.setTaxdate(parameters.getDocduedate());
			// 4rt1.setFinncpriod(finncpriod);
			art7.setReltransid(-1);
			art7.setRellineid(-1);
			art7.setReltype("N");
			art7.setObjtype("5");
			art7.setVatline("N");
			art7.setVatamount(0.0);
			art7.setClosed("N");
			art7.setGrossvalue(0.0);
			art7.setBalduedeb(cotrans);
			art7.setBalduecred(0.0);
			art7.setIsnet("Y");
			art7.setTaxtype(0);
			art7.setTaxpostacc("N");
			art7.setTotalvat(0.0);
			art7.setWtliable("N");
			art7.setWtline("N");
			art7.setPayblock("N");
			art7.setOrdered("N");
			art7.setTranstype(parameters.getObjtype());
			detail.add(art7);
		}
		nuevo.setJournalentryList(detail);

		nuevo = fill_JournalEntry_Unir2(nuevo);
		return nuevo;
	}

	public JournalEntryTO fill_JournalEntry_Unir2(JournalEntryTO parameters)
			throws Exception {
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		boolean ind = false;
		Double total = zero;
		Double sum_debe = 0.0;
		Double sum_credit = 0.0;
		int n = 1;
		// copiando la lista de los detalles de el asiento contable
		List list = parameters.getJournalentryList();
		// --------------------------------------------------------
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();

		// recorre la lista de detalles
		for (Object obj : list) {
			ind = false;
			JournalEntryLinesTO good = (JournalEntryLinesTO) obj;
			String cod = good.getAccount();
			List lisHija = new Vector();

			// comparando lista aux de nodos visitados
			for (Object obj2 : aux) {
				JournalEntryLinesTO good2 = (JournalEntryLinesTO) obj2;
				if (cod.equals(good2.getAccount())) {
					ind = true;
				}
			}
			// compara el codigo de cuenta para hacer una sumatoria y guardarlo
			// en otra lista
			if (ind == false) {
				for (Object obj3 : list) {
					JournalEntryLinesTO good3 = (JournalEntryLinesTO) obj3;
					if (cod.equals(good3.getAccount())) {
						lisHija.add(good3);
					}
				}
				// guarda en la lista de listas
				listas.add(lisHija);
			}

			aux.add(good);

		}

		// recorre la lista de listas para encontrar los detalles de el asiento
		// contable
		List detail = new Vector();
		for (List obj1 : listas) {
			List listaDet = obj1;
			Double sum = zero;
			String acc = null;
			String c_acc = null;
			sum_debe = zero;
			sum_credit = zero;
			for (Object obj2 : listaDet) {
				JournalEntryLinesTO oldjournal = (JournalEntryLinesTO) obj2;
				if (oldjournal.getDebit() == null) {
					oldjournal.setDebit(0.0);
				}
				if (oldjournal.getCredit() == null) {
					oldjournal.setCredit(zero);
				}
				sum_debe = sum_debe + oldjournal.getDebit();
				sum_credit = sum_credit + oldjournal.getCredit();
				acc = oldjournal.getAccount();
				c_acc = oldjournal.getContraact();
			}

			// asiento contable

			JournalEntryLinesTO art1 = new JournalEntryLinesTO();
			// -----------------------------------------------------------------------------------
			// encontrando el saldo si es deudor o acreedor
			// -----------------------------------------------------------------------------------
			Double saldo = sum_debe - sum_credit;
			if (saldo != 0) {
				if (saldo > 0) {
					art1.setDebit(saldo);
					art1.setBalduedeb(saldo);
					art1.setBalduecred(zero);
				} else {
					saldo = saldo * -1;
					art1.setCredit(saldo);
					art1.setBalduecred(saldo);
					art1.setBalduedeb(zero);
				}
			} else {
				art1.setDebit(zero);
				art1.setBalduedeb(saldo);
				art1.setBalduecred(zero);
			}

			// --------------------------------------------------------------------------------------------------------------------------------------------------------
			// llenado del asiento contable
			// --------------------------------------------------------------------------------------------------------------------------------------------------------

			art1.setLine_id(n);
			art1.setAccount(acc);
			art1.setDuedate(parameters.getDuedate());
			art1.setShortname(acc);
			art1.setContraact(c_acc);
			art1.setLinememo("anulacion de venta");
			art1.setRefdate(parameters.getDuedate());
			art1.setRef1(parameters.getRef1());
			// art1.setRef2();
			art1.setBaseref(parameters.getRef1());
			art1.setTaxdate(parameters.getTaxdate());
			// art1.setFinncpriod(finncpriod);
			art1.setReltransid(-1);
			art1.setRellineid(-1);
			art1.setReltype("N");
			art1.setObjtype("5");
			art1.setVatline("N");
			art1.setVatamount(zero);
			art1.setClosed("N");
			art1.setGrossvalue(zero);
			art1.setIsnet("Y");
			art1.setTaxtype(0);
			art1.setTaxpostacc("N");
			art1.setTotalvat(0.0);
			art1.setWtliable("N");
			art1.setWtline("N");
			art1.setPayblock("N");
			art1.setOrdered("N");
			art1.setTranstype(parameters.getTranstype());
			detail.add(art1);
			n++;

		}
		nuevo.setBtfstatus("O");
		nuevo.setTranstype(parameters.getTranstype());
		nuevo.setBaseref(parameters.getBaseref());
		nuevo.setRefdate(parameters.getRefdate());
		nuevo.setMemo(parameters.getMemo());
		nuevo.setRef1(parameters.getRef1());
		nuevo.setRef2(parameters.getRef2());
		nuevo.setLoctotal(parameters.getLoctotal());
		nuevo.setSystotal(parameters.getSystotal());
		nuevo.setTransrate(zero);
		nuevo.setDuedate(parameters.getDuedate());
		nuevo.setTaxdate(parameters.getTaxdate());
		nuevo.setFinncpriod(0);
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setRefndrprt("N");
		nuevo.setObjtype("5");
		nuevo.setAdjtran("N");
		nuevo.setAutostorno("N");
		nuevo.setSeries(0);
		nuevo.setAutovat("N");

		nuevo.setDocseries(0);
		nuevo.setPrinted("N");
		nuevo.setAutowt("N");
		nuevo.setDeferedtax("N");
		nuevo.setJournalentryList(detail);

		return nuevo;

	}

	public JournalEntryTO fill_JournalEntry_pago(ClientCrediTO parameters)
			throws Exception {

		String buss_c;
		String branch_c;
		String iva_c;
		String V_local;
		String costo_venta;
		String fovialCotrans_c = null;
		double bussines = 0.0;
		double branch = 0.0;
		double tax = 0.0;
		double fovc = 0.0;
		double sale = 0.0;
		double costo = 0.0;
		double impuesto = 0.0;
		
		
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		ArticlesTO arti = new ArticlesTO();
		boolean ind = false;
		Double total = zero;
		List list = parameters.getclientDetails();
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();
		// recorre la lista de detalles
		AdminDAO admin = new AdminDAO();
		CatalogTO Catalog = new CatalogTO();
		Catalog = admin.findCatalogByKey("IVA", 10);

		 ParameterDAO admin1 = new ParameterDAO();
		parameterTO Catalog1 = new parameterTO();
		Catalog1 = admin1.getParameterbykey(7);

		for (Object obj : list) {
			ClientCrediDetailTO good = (ClientCrediDetailTO) obj;
			String cod = good.getAcctcode();
			List lisHija = new Vector();
			// calculando los impuestos y saldo de las cuentas
			// --------------------------------------------------------------------------------

			arti = good.getArticle();
			branch = branch + (arti.getAvgPrice() * good.getQuantity());
			sale = sale + good.getLinetotal();
			// calculando el iva validando si el producto esta exento o de iva
			if (good.getTaxstatus().equals("Y")) {

				impuesto = good.getLinetotal()
						* (Double.parseDouble(Catalog.getCatvalue()) / 100);
				fovc = fovc + (good.getVatsum() - impuesto);
				tax = tax + impuesto;

			}
			bussines = bussines
					+ ((good.getVatsum() - impuesto) + impuesto + good
							.getLinetotal());
		}
		// consultando en la base de datos los codigos de cuenta asignados
		// cuenta de bussines partner
		buss_c = parameters.getCtlaccount();

		// asiento contable
		JournalEntryLinesTO art1 = new JournalEntryLinesTO();
		JournalEntryLinesTO art2 = new JournalEntryLinesTO();
		JournalEntryLinesTO art3 = new JournalEntryLinesTO();
		JournalEntryLinesTO art4 = new JournalEntryLinesTO();
		JournalEntryLinesTO art5 = new JournalEntryLinesTO();
		JournalEntryLinesTO art6 = new JournalEntryLinesTO();
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// llenado del asiento contable
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// LLenado del padre
		List detail = new Vector();
		nuevo.setObjtype("5");
		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setLoctotal(bussines);
		nuevo.setSystotal(bussines);
		nuevo.setMemo("anulacion pago de factura de venta");
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setDuedate(parameters.getDocdate());
		nuevo.setTaxdate(parameters.getTaxdate());
		nuevo.setBtfstatus("O");
		nuevo.setTranstype(parameters.getObjtype());
		nuevo.setBaseref(parameters.getRef1());
		nuevo.setRefdate(parameters.getDocduedate());
		nuevo.setRef1(parameters.getRef1());
		nuevo.setRefndrprt("N");
		nuevo.setAdjtran("N");
		nuevo.setAutostorno("N");
		nuevo.setAutovat("N");
		nuevo.setPrinted("N");
		nuevo.setAutowt("N");
		nuevo.setDeferedtax("N");

		// llenado de los
		// hijos---------------------------------------------------------------------------------------------------
		// cuenta del socio de negocio
		art1.setLine_id(1);
		art1.setDebit(bussines);
		// art1.setCredit(bussines);
		art1.setAccount(buss_c);
		art1.setDuedate(parameters.getDocdate());
		art1.setShortname(buss_c);
		art1.setContraact(Catalog1.getValue1());
		art1.setLinememo("Pago de Factura");
		art1.setRefdate(parameters.getDocdate());
		art1.setRef1(parameters.getRef1());
		// ar1.setRef2();
		art1.setBaseref(parameters.getRef1());
		art1.setTaxdate(parameters.getTaxdate());
		// art1.setFinncpriod(finncpriod);
		art1.setReltransid(-1);
		art1.setRellineid(-1);
		art1.setReltype("N");
		art1.setObjtype("5");
		art1.setVatline("N");
		art1.setVatamount(0.0);
		art1.setClosed("N");
		art1.setGrossvalue(0.0);
		art1.setBalduedeb(bussines);
		art1.setBalduecred(0.0);
		art1.setIsnet("Y");
		art1.setTaxtype(0);
		art1.setTaxpostacc("N");
		art1.setTotalvat(0.0);
		art1.setWtliable("N");
		art1.setWtline("N");
		art1.setPayblock("N");
		art1.setOrdered("N");
		art1.setTranstype(parameters.getObjtype());
		detail.add(art1);
		// ---------------------------------------------------------------------------------------------------
		// cuenta del socio de negocio
		// ---------------------------------------------------------------------------------------------------
		art2.setLine_id(2);
		art2.setCredit(bussines);
		// art2.setDebit(bussines);
		art2.setAccount(Catalog1.getValue1());
		art2.setDuedate(parameters.getDocdate());
		art2.setShortname(Catalog1.getValue1());
		art2.setContraact(buss_c);
		art2.setLinememo(" Anulacion Pago de Factura de ventas");
		art2.setRefdate(parameters.getDocdate());
		art2.setRef1(parameters.getRef1());
		// r1.setRef2();
		art2.setBaseref(parameters.getRef1());
		art2.setTaxdate(parameters.getTaxdate());
		// art1.setFinncpriod(finncpriod);
		art2.setReltransid(-1);
		art2.setRellineid(-1);
		art2.setReltype("N");
		art2.setObjtype("5");
		art2.setVatline("N");
		art2.setVatamount(0.0);
		art2.setClosed("N");
		art2.setGrossvalue(0.0);
		art2.setBalduedeb(0.0);
		art2.setBalduecred(bussines);
		art2.setIsnet("Y");
		art2.setTaxtype(0);
		art2.setTaxpostacc("N");
		art2.setTotalvat(0.0);
		art2.setWtliable("N");
		art2.setWtline("N");
		art2.setPayblock("N");
		art2.setOrdered("N");
		art2.setTranstype(parameters.getObjtype());
		detail.add(art2);

		nuevo.setJournalentryList(detail);
		return nuevo;
	}

	public SalesTO update_lines(SalesTO sales, ClientCrediTO credi) {
		// -----------------------------------------------------------------
		// inicializacion de DAtos
		// -----------------------------------------------------------------
		List credetail = new Vector();
		List saledetail = new Vector();
		credetail = credi.getclientDetails();
		saledetail = sales.getSalesDetails();
		int c_sale = saledetail.size();
		int c_credi = credetail.size();
		// -----------------------------------------------------------------
		// compara si ambas listas de destalles son iguales o menor
		// -----------------------------------------------------------------
		if (c_credi < c_sale) {
			// si es menor da comienzo a anulacion por linea
			// recorrido por detalles de nota de credito
			for (Object obj : credetail) {
				ClientCrediDetailTO good = (ClientCrediDetailTO) obj;
				// recorrido por lista de detalles de venta
				for (Object obj1 : saledetail) {
					SalesDetailTO good1 = (SalesDetailTO) obj;
					if (good.getBaseline() == good1.getLinenum()) {
						good1.setLinestatus("C");
					}

				}

			}
		} else {
			// si es mayor o igual se anula documento completo
			sales.setCanceled("Y");
			sales.setDocstatus("C");
		}
		return sales;

	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------
	// -------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------------------------------------
	// consultas de nota de credito
	// --------------------------------------------------------------------------------------------------------------------------------------------------------------------
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

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// nota de remision
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public ResultOutTO inv_Delivery_mtto(DeliveryTO parameters, int action)
			throws EJBException {

		// Declaración de variables

		ResultOutTO _valid = new ResultOutTO();

		ResultOutTO _return = new ResultOutTO();
		SalesDAO DAO = new SalesDAO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Validar acción a realizar
		// --------------------------------------------------------------------------------------------------------------------------------

		if (action != Common.MTTOINSERT) {
			_return = inv_Delivery_update(parameters, action);
			return _return;
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Asignación de valores por defecto y llenado:
		// Estas se realizan solo para cuando es guardar, el actualizar y borrar
		// no aplican.
		// --------------------------------------------------------------------------------------------------------------------------------
		parameters = fillDelivery(parameters);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Hacer validaciones:
		// Estas se realizan solo para cuando es guardar, el actualizar y borrar
		// no aplican para validaciones
		// --------------------------------------------------------------------------------------------------------------------------------

		_valid = Validateinv_Delivery(parameters);

		if (_valid.getCodigoError() != 0) {
			return _valid;
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar en base:
		// Desde aqui se debe manajar como una transaccion global, cada metodo
		// debe tener una opción para ejecutarlo como parte de una transacción
		// global
		// --------------------------------------------------------------------------------------------------------------------------------

		try {
			DAO.setIstransaccional(true);
			_return = save_TransactionDelivery(parameters, action,
					DAO.getConn());
			DAO.forceCommit();

		} catch (Exception e) {
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public ResultOutTO inv_Delivery_update(DeliveryTO parameters, int action)
			throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		DeliveryDAO DAO = new DeliveryDAO();
		try {
			_return = inv_Delivery_update(parameters, action, DAO.getConn());
			DAO.forceCommit();
		} catch (Exception e) {
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {
			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos Almacenados Con Exito");
		return _return;

	}

	public ResultOutTO inv_Delivery_update(DeliveryTO parameters, int action,
			Connection conn) throws Exception {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		DeliveryDAO DAO = new DeliveryDAO(conn);
		DAO.setIstransaccional(true);
		DeliveryDetailDAO DAO1 = new DeliveryDetailDAO(conn);
		DAO1.setIstransaccional(true);

		// Actualizar/borrar encabezados
		_return.setDocentry(DAO.inv_Delivery_mtto(parameters, action));

		// Borrar detalles
		Iterator<DeliveryDetailTO> iterator = parameters.getDeliveryDetails()
				.iterator();
		if (action == Common.MTTODELETE) {
			while (iterator.hasNext()) {
				DeliveryDetailTO detalleReceipt = (DeliveryDetailTO) iterator
						.next();

				DAO1.inv_DeliveryDetail_mtto(detalleReceipt, Common.MTTODELETE);
			}
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos Actualizados con exito");
		return _return;
	}

	private DeliveryTO fillDelivery(DeliveryTO parameters) {

		// variables
		Double total = zero;
		Double vatsum = zero;
		ArticlesTO DBArticle = new ArticlesTO();
		AdminDAO admin = new AdminDAO();
		ParameterDAO parameter = new ParameterDAO();
		String branch=null;

		BranchTO branch1 = new BranchTO();
		parameterTO param = new parameterTO();
		// buscando la cuenta asignada de cuenta de existencias al almacen

		try {
			if (parameters.getSeries() == 4) {
				param = parameter.getParameterbykey(6);
				branch1 = admin.getBranchByKey(param.getValue1());

			} else {
				// alamacen de notas de remision
				param = parameter.getParameterbykey(6);
				branch1 = admin.getBranchByKey(parameters.getTowhscode());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String branch_c = branch1.getBalinvntac();
		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto detalle
		// --------------------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings("unchecked")
		Iterator<DeliveryDetailTO> iterator = parameters.getDeliveryDetails()
				.iterator();
		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto encabezado
		// --------------------------------------------------------------------------------------------------------------------------------
		while (iterator.hasNext()) {
			DeliveryDetailTO articleDetalle = (DeliveryDetailTO) iterator
					.next();

			AdminEJB EJB = new AdminEJB();
			DBArticle = EJB.getArticlesByKey(articleDetalle.getItemcode());

			// Asignar a documento
			articleDetalle.setArticle(DBArticle);
			articleDetalle.setAcctcode(branch_c);
			// Valores por defecto
			articleDetalle.setDocentry(parameters.getDocentry());
			articleDetalle.setTargettype(-1);
			// articleDetalle.setBaseref("");
			articleDetalle.setBasetype(-1);
			articleDetalle.setLinestatus("O");
			articleDetalle.setDscription(DBArticle.getItemName());
			articleDetalle.setDiscprcnt(zero);
			// articleDetalle.setAcctcode(acctcode);
			articleDetalle.setTaxstatus("Y");
			if (parameters.getSeries() == 4) {
				branch=articleDetalle.getWhscode();
				articleDetalle.setWhscode(param.getValue1());
				articleDetalle.setOcrcode(param.getValue1());
			} else {
				articleDetalle.setWhscode(parameters.getTowhscode());
				articleDetalle.setOcrcode(parameters.getTowhscode());
			}

			articleDetalle.setFactor1(zero);
			articleDetalle.setObjtype("12");
			articleDetalle.setGrssprofit(articleDetalle.getGrssprofit());
			articleDetalle.setVatappld(zero);
			articleDetalle.setUnitmsr(DBArticle.getBuyUnitMsr());
			articleDetalle.setStockpricestockprice(articleDetalle.getStockpricestockprice());

			// Calculo de impuesto
			vatsum = vatsum + articleDetalle.getVatsum();

			// Calculo de totales
			articleDetalle.setLinetotal(articleDetalle.getQuantity()
					* articleDetalle.getPrice());
			articleDetalle.setOpenqty(articleDetalle.getQuantity());
			total = total + articleDetalle.getGtotal();

		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto encabezado
		// --------------------------------------------------------------------------------------------------------------------------------
		java.util.Date utilDate = new java.util.Date(); // fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
		parameters.setDocduedate(sqlDate);
		parameters.setDocnum(parameters.getDocentry());
		parameters.setDoctype("I");
		parameters.setCanceled("N");
		parameters.setDocstatus("O");
		parameters.setObjtype("12");
		parameters.setVatsum(vatsum);
		parameters.setDiscsum(zero);
		parameters.setDoctotal(total);
		parameters.setRef1(Integer.toString(parameters.getDocnum()));
		parameters.setJrnlmemo("Nota De Remision  - ");
		parameters.setReceiptnum(parameters.getReceiptnum());
		parameters.setGroupnum(parameters.getGroupnum());
		parameters.setConfirmed("Y");
		parameters.setCreatetran("Y");
		// parameters.setSeries(0);
		parameters.setRounddif(zero);
		parameters.setRounding("N");
		// parameters.setCtlaccount(ctlaccount); Aqui deberia de hacerse la
		// consulta he incluirse la cuenta contables
		parameters.setPaidsum(zero);
		parameters.setNret(zero);
		if (parameters.getSeries() == 4) {
			parameters.setFromwhscode(parameters.getTowhscode());
			parameters.setTowhscode(branch);
			parameters.setJrnlmemo("Anulacion Remision-"+parameters.getReceiptnum());

		} else {
			parameters.setFromwhscode(parameters.getTowhscode());
			parameters.setTowhscode(param.getValue1());
		}
		return parameters;
	}

	public ResultOutTO save_TransactionDelivery(DeliveryTO Delivery,
			int action, Connection conn) throws Exception {

		// Variables
		List transactions = new Vector();
		InventoryLogTO inventoryLog = new InventoryLogTO();
		WarehouseJournalTO warehouseJournal = new WarehouseJournalTO();
		WarehouseJournalLayerTO warehouseJournallayer = new WarehouseJournalLayerTO();
		ResultOutTO _return = new ResultOutTO();
		ResultOutTO res_invet = new ResultOutTO();
		ResultOutTO res_whj = new ResultOutTO();
		ResultOutTO res_whjl = new ResultOutTO();
		ResultOutTO res_jour = new ResultOutTO();
		ResultOutTO res_UpdateOnhand = new ResultOutTO();
		DeliveryDAO DAO = new DeliveryDAO(conn);
		transactionEJB trans = new transactionEJB();
		JournalEntryTO journal = new JournalEntryTO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar Encabezados y detalles de Entrada
		// --------------------------------------------------------------------------------------------------------------------------------

		_return = inv_Delivery_save(Delivery, action, conn);
		Delivery.setDocentry(_return.getDocentry());
		Delivery.setDocnum(_return.getDocentry());
		Delivery.setRef1(Integer.toString(Delivery.getDocnum()));

		// --------------------------------------------------------------------------------------------------------------------------------
		// Llenar objeto tipo transacción
		// --------------------------------------------------------------------------------------------------------------------------------

		transactions = fill_transaction(Delivery, conn);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Calculo actualizacion de existencias y costos
		// --------------------------------------------------------------------------------------------------------------------------------
		for (Object object : transactions) {
			TransactionTo ivt = (TransactionTo) object;
			ivt = trans.calculate(ivt);
			TransactionDAO transDAO = new TransactionDAO(conn);
			transDAO.setIstransaccional(true);
			res_UpdateOnhand = transDAO.Update_Onhand_articles(ivt);
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Registro de Inventory Log
		// --------------------------------------------------------------------------------------------------------------------------------

		for (Object object : transactions) {
			TransactionTo ivt = (TransactionTo) object;

			inventoryLog = trans.fill_Inventory_Log(ivt);
			res_invet = trans.save_Inventory_Log(inventoryLog, conn);
			// Asignación de codigo MessageId
			ivt.setMessageid(res_invet.getDocentry());
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Registro de Warehouse Journal
		// --------------------------------------------------------------------------------------------------------------------------------

		for (Object object : transactions) {
			TransactionTo ivt = (TransactionTo) object;
			warehouseJournal = trans.fill_WarehouseJournal(ivt);
			// Asiganción de TransSeq
			res_whj = trans.save_WarehouseJournal(warehouseJournal, conn);
			ivt.setTransseq(res_whj.getDocentry());
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Registro de Warehouse Journal layer
		// --------------------------------------------------------------------------------------------------------------------------------

		for (Object object : transactions) {

			TransactionTo ivt = (TransactionTo) object;
			warehouseJournallayer = trans.fill_WarehouseJournalLayer(ivt);
			// Asiganción de TransSeq
			res_whjl = trans.save_WarehouseJournalLayer(warehouseJournallayer,
					conn);
		}

		// -----------------------------------------------------------------------------------
		// actualizacion de la nota de remision original groupnum = 2 anulacion
		// de nota de remision
		if (Delivery.getSeries() == 4) {
			DeliveryTO remision = new DeliveryTO();
			remision = getDeliveryByKey(Delivery.getReceiptnum());
			if (remision.getDoctotal() > 0.0 && remision.getDoctotal() != null) {
				remision.setCanceled("Y");
				remision.setDocstatus("C");

				remision.setCanceldate(Delivery.getDocdate());
				inv_Delivery_update(remision, Common.MTTOUPDATE, conn);

				journal = fill_JournalEntry_cancellation(Delivery);

				AccountingEJB account = new AccountingEJB();
				res_jour = account.journalEntry_mtto(journal,
						Common.MTTOINSERT, conn);

			} else {
				throw new Exception("No se encuentra nota de remision "
						+ Delivery.getReceiptnum());
			}

		} else {
			// -----------------------------------------------------------------------------------

			// -----------------------------------------------------------------------------------
			// registro del asiento contable y actualización de saldos
			// -----------------------------------------------------------------------------------

			journal = fill_JournalEntry(Delivery);

			AccountingEJB account = new AccountingEJB();
			res_jour = account.journalEntry_mtto(journal, Common.MTTOINSERT,
					conn);
		}
		// -----------------------------------------------------------------------------------
		// Actualización de documento con datos de Asiento contable
		// -----------------------------------------------------------------------------------

		Delivery.setTransid(res_jour.getDocentry());
		_return = inv_Delivery_update(Delivery, Common.MTTOUPDATE, conn);

		_return.setCodigoError(0);
		_return.setMensaje("Datos Alamacenados Con exito");
		return _return;
	}

	private List fill_transaction(DeliveryTO document, Connection conn) {
		List _return = new Vector();

		Iterator<DeliveryDetailTO> iterator = document.getDeliveryDetails()
				.iterator();
		while (iterator.hasNext()) {
			DeliveryDetailTO detail = (DeliveryDetailTO) iterator.next();

			TransactionTo transaction = new TransactionTo();
			transaction.setTransseq(0);
			transaction.setDocentry(document.getDocentry());
			transaction.setDocnum(Integer.toString(document.getDocnum()));
			transaction.setDocduedate(document.getDocduedate());
			transaction.setDocdate(document.getDocdate());
			transaction.setComment(document.getComments());
			transaction.setJrnlmemo(document.getJrnlmemo());
			transaction.setUsersign(document.getUsersign());
			transaction.setRef1(Integer.toString(document.getDocnum()));
			transaction.setRef2(document.getNumatcard());
			transaction.setLinenum(detail.getLinenum());
			transaction.setItemcode(detail.getItemcode());
			transaction.setDscription(detail.getDscription());
			transaction.setQuantity(detail.getQuantity());
			transaction.setPrice(detail.getPrice());
			transaction.setLinetotal(detail.getLinetotal());
			transaction.setWhscode(document.getTowhscode());
			transaction.setAcctcode(detail.getAcctcode());
			transaction.setOcrcode(document.getCardcode());
			transaction.setVatgroup(detail.getVatgroup());
			transaction.setPriceafvat(detail.getPriceafvat());
			transaction.setVatsum(detail.getVatsum());
			transaction.setObjtype(detail.getObjtype());
			transaction.setGrssprofit(detail.getGrssprofit());
			transaction.setTaxcode(detail.getTaxcode());
			transaction.setVatappld(detail.getVatappld());
			transaction.setStockprice(detail.getPrice());
			transaction.setGtotal(detail.getGtotal());
			transaction.setInqty(detail.getQuantity());
			transaction.setOutqty(zero);
			transaction.setMessageid(0);
			transaction.setBalance(zero);
			transaction.setNewOnhand(zero);
			transaction.setNewWhsOnhand(zero);
			transaction.setNewAvgprice(zero);
			transaction.setArticle(detail.getArticle());

			_return.add(transaction);

			TransactionTo transaction2 = new TransactionTo();
			transaction2.setTransseq(0);
			transaction2.setDocentry(document.getDocentry());
			transaction2.setDocnum(Integer.toString(document.getDocnum()));
			transaction2.setDocduedate(document.getDocduedate());
			transaction2.setDocdate(document.getDocdate());
			transaction2.setComment(document.getComments());
			transaction2.setJrnlmemo(document.getJrnlmemo());
			transaction2.setUsersign(document.getUsersign());
			transaction2.setRef1(Integer.toString(document.getDocnum()));
			transaction2.setRef2(document.getNumatcard());
			transaction2.setLinenum(detail.getLinenum());
			transaction2.setItemcode(detail.getItemcode());
			transaction2.setDscription(detail.getDscription());
			transaction2.setQuantity(detail.getQuantity());
			transaction2.setPrice(detail.getPrice());
			transaction2.setLinetotal(detail.getLinetotal());
			transaction2.setWhscode(document.getFromwhscode());
			transaction2.setAcctcode(detail.getAcctcode());
			transaction2.setOcrcode(document.getCardcode());
			transaction2.setVatgroup(detail.getVatgroup());
			transaction2.setPriceafvat(detail.getPriceafvat());
			transaction2.setVatsum(detail.getVatsum());
			transaction2.setObjtype(detail.getObjtype());
			transaction2.setGrssprofit(detail.getGrssprofit());
			transaction2.setTaxcode(detail.getTaxcode());
			transaction2.setVatappld(detail.getVatappld());
			transaction2.setStockprice(detail.getPrice());
			transaction2.setGtotal(detail.getGtotal());
			transaction2.setInqty(zero);
			transaction2.setOutqty(detail.getQuantity());
			transaction2.setMessageid(0);
			transaction2.setBalance(zero);
			transaction2.setNewOnhand(zero);
			transaction2.setNewWhsOnhand(zero);
			transaction2.setNewAvgprice(zero);
			transaction2.setArticle(detail.getArticle());

			_return.add(transaction2);

		}
		return _return;
	}

	public ResultOutTO inv_Delivery_save(DeliveryTO parameters, int action,
			Connection conn) throws Exception {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		DeliveryDAO DAO = new DeliveryDAO(conn);
		DAO.setIstransaccional(true);
		DeliveryDetailDAO goodDAO1 = new DeliveryDetailDAO(conn);
		goodDAO1.setIstransaccional(true);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar encabezados
		// --------------------------------------------------------------------------------------------------------------------------------

		_return.setDocentry(DAO.inv_Delivery_mtto(parameters, action));

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar detalles
		// --------------------------------------------------------------------------------------------------------------------------------

		Iterator<DeliveryDetailTO> iterator = parameters.getDeliveryDetails()
				.iterator();
		while (iterator.hasNext()) {
			DeliveryDetailTO salesdetalle = (DeliveryDetailTO) iterator.next();

			salesdetalle.setDocentry(_return.getDocentry());
			goodDAO1.inv_DeliveryDetail_mtto(salesdetalle, Common.MTTOINSERT);

		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public ResultOutTO Validateinv_Delivery(DeliveryTO parameters)
			throws EJBException {

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
		// validacion de almacen origen y destino
		// ------------------------------------------------------------------------------------------------------------

		if (parameters.getTowhscode().equals(parameters.getFromwhscode())) {
			_return.setCodigoError(1);
			_return.setMensaje("Codigo de almacen origen coincide con almacen destino");

			return _return;
		}

		// ------------------------------------------------------------------------------------------------------------
		// Validación almacen origen bloqueado
		// ------------------------------------------------------------------------------------------------------------

		_return = EJB1.validate_branchActiv(parameters.getFromwhscode());

		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("El Almacen no esta activo");
			return _return;
		}
		// ------------------------------------------------------------------------------------------------------------
		// Validación almacen destino bloqueado
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

				if (branch1.getWhscode().equals(DeliveryDetail.getWhscode())) {
					if (branch1.isIsasociated() && branch1.getLocked() != null
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

			// ------------------------------------------------------------------------------------------------------------
			// Validación almacen destino bloqueado
			// ------------------------------------------------------------------------------------------------------------
			EJB = new AdminEJB();

			_return = EJB.validate_branchActiv(parameters.getTowhscode());

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
				if (branch1.getWhscode().equals(parameters.getTowhscode())) {
					if (branch1.isIsasociated() && branch1.getLocked() != null
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
						+ " No esta asignado o esta bloquedo para el almacen de destino indicado. linea :"
						+ DeliveryDetail.getLinenum());
				return _return;
			}
			// ------------------------------------------------------------------------------------------------------------
			// Validaciones de Existencia
			// ------------------------------------------------------------------------------------------------------------
			valid = false;

			Double stocks = 0.000;
			Iterator<DeliveryDetailTO> iterator2 = parameters
					.getDeliveryDetails().iterator();
			// recorre de nuevo el detalle comparando el primer elemento con los
			// demas
			while (iterator2.hasNext()) {
				DeliveryDetailTO articleDetalle2 = (DeliveryDetailTO) iterator2
						.next();
				if (code.equals(articleDetalle2.getItemcode())) {
					// suma los elementos encontrados del mismo codigo
					stocks = stocks
							+ (articleDetalle2.getQuantity() * DBArticle
									.getNumInSale());
				}
			}

			branch = DBArticle.getBranchArticles();
			Double Quantity = 0.0;
			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;
				if (branch1.getWhscode().equals(parameters.getFromwhscode())) {

					if (stocks <= branch1.getOnhand()) {
						valid = true;
					}
				}

			}
			if (!valid) {
				_return.setLinenum(DeliveryDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ DeliveryDetail.getItemcode() + " "
						+ DeliveryDetail.getDscription()
						+ " Recae en un Inventario Negativo. linea :"
						+ DeliveryDetail.getLinenum());
				return _return;
			}

		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos Almacenados Con Exito");
		return _return;

	}

	public JournalEntryTO fill_JournalEntry(DeliveryTO parameters)
			throws Exception {
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		boolean ind = false;
		Double total = zero;
		Double sum = zero;
		String acc = null;
		List list = parameters.getDeliveryDetails();
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();
		// recorre la lista de detalles
		for (Object obj : list) {
			DeliveryDetailTO good = (DeliveryDetailTO) obj;
			String cod = good.getAcctcode();
			List lisHija = new Vector();

			// comparando lista aux de nodos visitados
			for (Object obj2 : aux) {
				DeliveryDetailTO good2 = (DeliveryDetailTO) obj2;
				if (cod.equals(good2.getAcctcode())) {
					ind = true;
				}
			}
			// compara el codigo de cuenta para hacer una sumatoria y guardarlo
			// en otra lista
			if (ind == false) {
				for (Object obj3 : list) {
					DeliveryDetailTO good3 = (DeliveryDetailTO) obj3;
					if (cod.equals(good3.getAcctcode())) {
						lisHija.add(good3);
					}
				}
				// guarda en la lista de listas
				listas.add(lisHija);
			}

			aux.add(good);

		}
		// recorre la lista de listas para encontrar los detalles de el asiento
		// contable
		List detail = new Vector();
		for (List obj1 : listas) {
			List listaDet = obj1;
			sum = zero;
			acc = null;
			for (Object obj2 : listaDet) {
				DeliveryDetailTO newGood = (DeliveryDetailTO) obj2;
				sum = sum + (newGood.getQuantity() * newGood.getPrice());
				acc = newGood.getAcctcode();
			}
		}
		// asiento contable

		JournalEntryLinesTO art1 = new JournalEntryLinesTO();
		JournalEntryLinesTO art2 = new JournalEntryLinesTO();
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// llenado del asiento contable
		// --------------------------------------------------------------------------------------------------------------------------------------------------------

		// llenado de los hijos
		art1.setLine_id(1);
		// buscar la cuenta asignada al almacen
		AdminDAO admin = new AdminDAO();
		BranchTO branch = new BranchTO();
		// buscando la cuenta asignada de cuenta de existencias al almacen

		branch = admin.getBranchByKey(parameters.getFromwhscode());
		art1.setAccount(branch.getBalinvntac());

		if (branch.getBalinvntac() == null) {
			throw new Exception(
					"No hay una cuenta de Inventario asignada al almacen");
		}
		// -----------------------------------------------------------------------------------------------------------------------------------------------
		art2.setLine_id(2);
		 admin = new AdminDAO();
		BranchTO branch1 = new BranchTO();
		branch1 = admin.getBranchByKey(parameters.getTowhscode());
		art2.setAccount(branch1.getBalinvntac());

		art1.setCredit(sum);
		art1.setDuedate(parameters.getDocduedate());
		art1.setShortname(branch.getBalinvntac());
		art1.setContraact(branch1.getBalinvntac());
		art1.setLinememo("nota  de remision ");
		art1.setRefdate(parameters.getDocduedate());
		art1.setRef1(parameters.getRef1());
		// art1.setRef2();
		art1.setBaseref(parameters.getRef1());
		art1.setTaxdate(parameters.getDocduedate());
		// art1.setFinncpriod(finncpriod);
		art1.setReltransid(-1);
		art1.setRellineid(-1);
		art1.setReltype("N");
		art1.setObjtype("5");
		art1.setVatline("N");
		art1.setVatamount(0.0);
		art1.setClosed("N");
		art1.setGrossvalue(0.0);
		art1.setBalduedeb(0.0);
		art1.setBalduecred(sum);
		art1.setIsnet("Y");
		art1.setTaxtype(0);
		art1.setTaxpostacc("N");
		art1.setTotalvat(0.0);
		art1.setWtliable("N");
		art1.setWtline("N");
		art1.setPayblock("N");
		art1.setOrdered("N");
		art1.setTranstype(parameters.getObjtype());

		detail.add(art1);

		art2.setDebit(sum);
		art2.setDuedate(parameters.getDocduedate());
		art2.setShortname(branch1.getBalinvntac());
		art2.setContraact(branch.getBalinvntac());
		art2.setLinememo("nota de remision ");
		art2.setRefdate(parameters.getDocduedate());
		art2.setRef1(parameters.getRef1());
		// art2.setRef2();
		art2.setBaseref(parameters.getRef1());
		art2.setTaxdate(parameters.getDocduedate());
		// art1.setFinncpriod(finncpriod);
		art2.setReltransid(-1);
		art2.setRellineid(-1);
		art2.setReltype("N");
		art2.setObjtype("5");
		art2.setVatline("N");
		art2.setVatamount(0.0);
		art2.setClosed("N");
		art2.setGrossvalue(0.0);
		art2.setBalduedeb(sum);
		art2.setBalduecred(0.0);
		art2.setIsnet("Y");
		art2.setTaxtype(0);
		art2.setTaxpostacc("N");
		art2.setTotalvat(0.0);
		art2.setWtliable("N");
		art2.setWtline("N");
		art2.setPayblock("N");
		art2.setOrdered("N");
		art2.setTranstype(parameters.getObjtype());

		detail.add(art2);

		nuevo.setObjtype("5");
		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setLoctotal(sum);
		nuevo.setSystotal(sum);
		nuevo.setBtfstatus("O");
		nuevo.setTranstype(parameters.getObjtype());
		nuevo.setBaseref(parameters.getRef1());
		nuevo.setRefdate(parameters.getDocdate());
		nuevo.setRef1(parameters.getRef1());
		nuevo.setDuedate(parameters.getDocduedate());
		nuevo.setTaxdate(parameters.getTaxdate());
		nuevo.setRefndrprt("N");
		nuevo.setAdjtran("N");
		nuevo.setAutostorno("N");
		nuevo.setAutovat("N");
		nuevo.setPrinted("N");
		nuevo.setAutowt("N");
		nuevo.setDeferedtax("N");
		nuevo.setJournalentryList(detail);
		return nuevo;

		// #endregion
	}

	public JournalEntryTO fill_JournalEntry_cancellation(DeliveryTO parameters)
			throws Exception {
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		boolean ind = false;
		Double total = zero;
		Double sum = zero;
		String acc = null;
		List list = parameters.getDeliveryDetails();
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();
		// recorre la lista de detalles
		for (Object obj : list) {
			DeliveryDetailTO good = (DeliveryDetailTO) obj;
			String cod = good.getAcctcode();
			List lisHija = new Vector();

			// comparando lista aux de nodos visitados
			for (Object obj2 : aux) {
				DeliveryDetailTO good2 = (DeliveryDetailTO) obj2;
				if (cod.equals(good2.getAcctcode())) {
					ind = true;
				}
			}
			// compara el codigo de cuenta para hacer una sumatoria y guardarlo
			// en otra lista
			if (ind == false) {
				for (Object obj3 : list) {
					DeliveryDetailTO good3 = (DeliveryDetailTO) obj3;
					if (cod.equals(good3.getAcctcode())) {
						lisHija.add(good3);
					}
				}
				// guarda en la lista de listas
				listas.add(lisHija);
			}

			aux.add(good);

		}
		// recorre la lista de listas para encontrar los detalles de el asiento
		// contable
		List detail = new Vector();
		for (List obj1 : listas) {
			List listaDet = obj1;
			sum = zero;
			acc = null;
			for (Object obj2 : listaDet) {
				DeliveryDetailTO newGood = (DeliveryDetailTO) obj2;
				sum = sum + (newGood.getQuantity() * newGood.getPrice());
				acc = newGood.getAcctcode();
			}
		}
		// asiento contable

		JournalEntryLinesTO art1 = new JournalEntryLinesTO();
		JournalEntryLinesTO art2 = new JournalEntryLinesTO();
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// llenado del asiento contable
		// --------------------------------------------------------------------------------------------------------------------------------------------------------

		// llenado de los hijos
		art1.setLine_id(1);
		// buscar la cuenta asignada al almacen
		AdminDAO admin = new AdminDAO();
		BranchTO branch = new BranchTO();
		// buscando la cuenta asignada de cuenta de existencias al almacen

		branch = admin.getBranchByKey(parameters.getFromwhscode());
		if (branch.getBalinvntac() == null) {
			throw new Exception(
					"No hay una cuenta de Inventario asignada al almacen");
		}
		art1.setAccount(branch.getBalinvntac());
		// -----------------------------------------------------------------------------------------------------------------------------------------------
		art2.setLine_id(2);
		admin = new AdminDAO();
		BranchTO branch1 = new BranchTO();

		branch1 = admin.getBranchByKey(parameters.getTowhscode());
		art2.setAccount(branch1.getBalinvntac());

		art1.setCredit(sum);
		art1.setDuedate(parameters.getDocduedate());
		art1.setShortname(branch.getBalinvntac());
		art1.setContraact(branch1.getBalinvntac());
		art1.setLinememo("nota  de remision ");
		art1.setRefdate(parameters.getDocduedate());
		art1.setRef1(parameters.getRef1());
		// art1.setRef2();
		art1.setBaseref(parameters.getRef1());
		art1.setTaxdate(parameters.getDocduedate());
		// art1.setFinncpriod(finncpriod);
		art1.setReltransid(-1);
		art1.setRellineid(-1);
		art1.setReltype("N");
		art1.setObjtype("5");
		art1.setVatline("N");
		art1.setVatamount(0.0);
		art1.setClosed("N");
		art1.setGrossvalue(0.0);
		art1.setBalduedeb(0.0);
		art1.setBalduecred(sum);
		art1.setIsnet("Y");
		art1.setTaxtype(0);
		art1.setTaxpostacc("N");
		art1.setTotalvat(0.0);
		art1.setWtliable("N");
		art1.setWtline("N");
		art1.setPayblock("N");
		art1.setOrdered("N");
		art1.setTranstype(parameters.getObjtype());

		detail.add(art1);

		art2.setDebit(sum);
		art2.setDuedate(parameters.getDocduedate());
		art2.setShortname(branch1.getBalinvntac());
		art2.setContraact(branch.getBalinvntac());
		art2.setLinememo("nota de remision ");
		art2.setRefdate(parameters.getDocduedate());
		art2.setRef1(parameters.getRef1());
		// art2.setRef2();
		art2.setBaseref(parameters.getRef1());
		art2.setTaxdate(parameters.getDocduedate());
		// art1.setFinncpriod(finncpriod);
		art2.setReltransid(-1);
		art2.setRellineid(-1);
		art2.setReltype("N");
		art2.setObjtype("5");
		art2.setVatline("N");
		art2.setVatamount(0.0);
		art2.setClosed("N");
		art2.setGrossvalue(0.0);
		art2.setBalduedeb(sum);
		art2.setBalduecred(0.0);
		art2.setIsnet("Y");
		art2.setTaxtype(0);
		art2.setTaxpostacc("N");
		art2.setTotalvat(0.0);
		art2.setWtliable("N");
		art2.setWtline("N");
		art2.setPayblock("N");
		art2.setOrdered("N");
		art2.setTranstype(parameters.getObjtype());

		detail.add(art2);

		nuevo.setObjtype("5");
		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setLoctotal(sum);
		nuevo.setSystotal(sum);
		nuevo.setBtfstatus("O");
		nuevo.setTranstype(parameters.getObjtype());
		nuevo.setBaseref(parameters.getRef1());
		nuevo.setRefdate(parameters.getDocdate());
		nuevo.setRef1(parameters.getRef1());
		nuevo.setDuedate(parameters.getDocduedate());
		nuevo.setTaxdate(parameters.getTaxdate());
		nuevo.setRefndrprt("N");
		nuevo.setAdjtran("N");
		nuevo.setAutostorno("N");
		nuevo.setAutovat("N");
		nuevo.setPrinted("N");
		nuevo.setAutowt("N");
		nuevo.setDeferedtax("N");
		nuevo.setJournalentryList(detail);
		return nuevo;

		// #endregion
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// consultas nota de remision
	// --------------------------------------------------------------------------------------------------------------------------------------------

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

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------------------------
}

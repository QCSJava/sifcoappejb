package com.sifcoapp.bussinessLogic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.admin.ejb.AdminEJB;
import com.sifcoapp.objects.accounting.dao.AccountingDAO;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.GoodsReceiptDAO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
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

	// -----------------------------------------------------------------------------------------------------------------
	// mantenimientos Ventas
	// -----------------------------------------------------------------------------------------------------------------
	public ResultOutTO inv_Sales_mtto(SalesTO parameters, int action)
			throws EJBException {

		// Declaración de variables

		ResultOutTO _valid = new ResultOutTO();

		ResultOutTO _return = new ResultOutTO();
		SalesDAO DAO = new SalesDAO();

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
			articleDetalle.setObjtype("10");
			articleDetalle.setGrssprofit(zero);
			articleDetalle.setVatappld(zero);
			articleDetalle.setUnitmsr(DBArticle.getBuyUnitMsr());
			articleDetalle.setStockpricestockprice(zero);

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
		parameters.setObjtype("10");
		parameters.setVatsum(vatsum);
		parameters.setDiscsum(zero);
		parameters.setDoctotal(total);
		parameters.setRef1(Integer.toString(parameters.getDocnum()));
		parameters.setJrnlmemo("Facturas de venta a clientes - "
				+ parameters.getCardcode());
		parameters.setReceiptnum(0);
		parameters.setGroupnum(0);
		parameters.setConfirmed("Y");
		parameters.setCreatetran("Y");
		// parameters.setSeries(0);
		parameters.setRounddif(zero);
		parameters.setRounding("N");
		// parameters.setCtlaccount(ctlaccount); Aqui deberia de hacerse la
		// consulta he incluirse la cuenta contables
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
		// Calculo de existencias y costos
		// --------------------------------------------------------------------------------------------------------------------------------
		for (Object object : transactions) {
			TransactionTo ivt = (TransactionTo) object;
			ivt = trans.calculate(ivt);
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

		// --------------------------------------------------------------------------------------------------------------------------------
		// Actualizacion de existencia articulos y almacenes
		// --------------------------------------------------------------------------------------------------------------------------------

		for (Object object : transactions) {
			TransactionDAO transDAO = new TransactionDAO(conn);
			transDAO.setIstransaccional(true);
			TransactionTo ivt = (TransactionTo) object;
			res_UpdateOnhand = transDAO.Update_Onhand_articles(ivt);
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

		Sales.setTransid(res_jour.getDocentry());
		_return = inv_Sales_update(Sales, Common.MTTOUPDATE, conn);

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
			transaction.setRef2(document.getRef1());
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
		String iva_c;
		String V_local;
		String costo_venta;
		String fovialCotrans_c = null;
		double bussines = 0;
		double branch = 0;
		double tax = 0;
		double fovc = 0;
		double sale = 0;
		double costo = 0;
		AdminDAO admin = new AdminDAO();
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
		for (Object obj : list) {
			SalesDetailTO good = (SalesDetailTO) obj;
			String cod = good.getAcctcode();
			List lisHija = new Vector();
			// calculando los impuestos y saldo de las cuentas
			// --------------------------------------------------------------------------------

			arti = good.getArticle();
			branch = branch + (arti.getAvgPrice() * good.getQuantity());
			sale = sale + good.getLinetotal();
			double impuesto = good.getLinetotal() * 0.13;
			fovc = fovc + (good.getVatsum() - impuesto);
			tax = tax + impuesto;
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

		branch1 = admin.getBranchByKey(parameters.getTowhscode());
		branch_c = branch1.getBalinvntac();
		if (branch1.getBalinvntac() == null) {
			throw new Exception(
					"No hay una cuenta contable de Inventario asignada al almacen");
		}

		// buscando cuenta asignada para IVA y FOVIAL
		if (fovc != 0) {
			admin = new AdminDAO();
			CatalogTO Catalog = new CatalogTO();
			Catalog = admin.findCatalogByKey("FOV1", 10);

			if (Catalog.getCatvalue2() == null) {
				throw new Exception("No tiene cuenta asignada para  impuestos");
			}
			if (Catalog.getCatvalue() == null) {
				iva_c = Catalog.getCatvalue();
			}
			fovialCotrans_c = Catalog.getCatvalue2();
			iva_c = Catalog.getCatvalue();

		} else {
			admin = new AdminDAO();
			CatalogTO Catalog = new CatalogTO();
			Catalog = admin.findCatalogByKey("IVA", 10);
			if (Catalog.getCatvalue2() == null) {
				throw new Exception("No tiene cuenta asignada para impuestos");
			}
			iva_c = Catalog.getCatvalue2();
		}

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
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// llenado del asiento contable
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// LLenado del padre
		List detail = new Vector();
		nuevo.setObjtype("5");
		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setLoctotal(bussines+branch);
		nuevo.setSystotal(bussines+branch);
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
		detail.add(art2);

		// cuenta de iva
		art3.setLine_id(3);
		
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
		detail.add(art3);
		// ------------------------
		// para la cuenta de ventas
		// -----------------------------

		art4.setLine_id(4);
		
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
		detail.add(art4);
		// ------------------------
		// para la cuenta de costo de ventas
		// -----------------------------

		art5.setLine_id(5);
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
		detail.add(art5);
		// cuenta de cotrans y fovial si se aplica el impuesto
		if (fovc != 0) {
			art6.setLine_id(6);
			
			art6.setCredit(fovc);
			art6.setAccount(fovialCotrans_c);
			art6.setDuedate(parameters.getDocduedate());
			art6.setShortname(fovialCotrans_c);
			art6.setContraact(buss_c);
			art6.setLinememo("Compra de mercancias");
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
			detail.add(art6);
		}
		nuevo.setJournalentryList(detail);
		return nuevo;
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

}

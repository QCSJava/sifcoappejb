package com.sifcoapp.bussinessLogic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.admin.ejb.AdminEJB;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.catalog.dao.BusinesspartnerDAO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.GoodReceiptDetailDAO;
import com.sifcoapp.objects.inventory.dao.GoodsReceiptDAO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
import com.sifcoapp.objects.purchase.dao.*;
import com.sifcoapp.objects.purchase.to.*;
import com.sifcoapp.objects.sales.to.DeliveryDetailTO;
import com.sifcoapp.objects.sales.to.SalesDetailTO;
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
public class PurchaseEJB implements PurchaseEJBRemote {
	Double zero = 0.0;

	/**
	 * Default constructor.
	 */
	public PurchaseEJB() {
		// TODO Auto-generated constructor stub
	}

	// -----------------------------------------------------------------------------------------------------------------
	// mantenimientos compras
	// -----------------------------------------------------------------------------------------------------------------
	public ResultOutTO inv_Purchase_mtto(PurchaseTO parameters, int action)
			throws EJBException {

		// Declaración de variables

		ResultOutTO _valid = new ResultOutTO();
		ResultOutTO _return = new ResultOutTO();
		GoodsReceiptDAO DAO = new GoodsReceiptDAO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Validar acción a realizar
		// --------------------------------------------------------------------------------------------------------------------------------

		if (action != Common.MTTOINSERT) {
			_return = inv_GoodsReceipt_update(parameters, action);
			return _return;
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Asignación de valores por defecto y llenado:
		// Estas se realizan solo para cuando es guardar, el actualizar y borrar
		// no aplican.
		// --------------------------------------------------------------------------------------------------------------------------------
		parameters = fillGoodReceipt(parameters);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Hacer validaciones:
		// Estas se realizan solo para cuando es guardar, el actualizar y borrar
		// no aplican para validaciones
		// --------------------------------------------------------------------------------------------------------------------------------

		
			_valid = validate_inv_Purchase_mtto(parameters);
		
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
			_return = save_TransactionGoodsReceipt(parameters, action,
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

	public ResultOutTO inv_GoodsReceipt_update(PurchaseTO parameters, int action)
			throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		GoodsReceiptDAO DAO = new GoodsReceiptDAO();
		try {
			_return = inv_GoodsReceipt_update(parameters, action, DAO.getConn());
			DAO.forceCommit();
		} catch (Exception e) {
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {
			DAO.forceCloseConnection();
		}
		return _return;

	}

	public ResultOutTO inv_GoodsReceipt_update(PurchaseTO parameters,
			int action, Connection conn) throws EJBException {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		PurchaseDAO DAO = new PurchaseDAO();
		DAO.setIstransaccional(true);
		PurchaseDetailDAO goodDAO1 = new PurchaseDetailDAO(DAO.getConn());
		goodDAO1.setIstransaccional(true);

		try {
			// Actualizar/borrar encabezados
			_return.setDocentry(DAO.inv_Purchase_mtto(parameters, action));

			// Borrar detalles
			Iterator<PurchaseDetailTO> iterator = parameters
					.getpurchaseDetails().iterator();
			if (action == Common.MTTODELETE) {
				while (iterator.hasNext()) {
					PurchaseDetailTO detalleReceipt = (PurchaseDetailTO) iterator
							.next();

					goodDAO1.inv_PurchaseDetail_mtto(detalleReceipt,
							Common.MTTODELETE);
				}
			}
			DAO.forceCommit();
		} catch (Exception e) {
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos Actualizados con exito");
		return _return;
	}

	public ResultOutTO inv_purchase_save(PurchaseTO parameters, int action,
			Connection conn) throws Exception {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		PurchaseDAO DAO = new PurchaseDAO(conn);
		DAO.setIstransaccional(true);
		PurchaseDetailDAO goodDAO1 = new PurchaseDetailDAO(conn);
		goodDAO1.setIstransaccional(true);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar encabezados
		// --------------------------------------------------------------------------------------------------------------------------------

		_return.setDocentry(DAO.inv_Purchase_mtto(parameters, action));

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar detalles
		// --------------------------------------------------------------------------------------------------------------------------------

		Iterator<PurchaseDetailTO> iterator = parameters.getpurchaseDetails()
				.iterator();
		while (iterator.hasNext()) {
			PurchaseDetailTO detalleReceipt = (PurchaseDetailTO) iterator
					.next();

			detalleReceipt.setDocentry(_return.getDocentry());
			goodDAO1.inv_PurchaseDetail_mtto(detalleReceipt, Common.MTTOINSERT);

		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	private PurchaseTO fillGoodReceipt(PurchaseTO parameters) {

		// variables
		Double total = zero;
		ArticlesTO DBArticle = new ArticlesTO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto detalle
		// --------------------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings("unchecked")
		Iterator<PurchaseDetailTO> iterator = parameters.getpurchaseDetails()
				.iterator();
		while (iterator.hasNext()) {
			PurchaseDetailTO articleDetalle = (PurchaseDetailTO) iterator
					.next();

			AdminEJB EJB = new AdminEJB();

			DBArticle = EJB.getArticlesByKey(articleDetalle.getItemcode());

			// Asignar a documento
			articleDetalle.setArticle(DBArticle);

			// Asignación de almacen en cada detalle
			articleDetalle.setWhscode(parameters.getTowhscode());

			// Asignaciones varias
			articleDetalle.setDscription(DBArticle.getItemName());
			articleDetalle.setUnitmsr(DBArticle.getInvntryUom());

			// Calculo de totales
			articleDetalle.setLinetotal(articleDetalle.getQuantity()
					* articleDetalle.getPrice());
			articleDetalle.setOpenqty(articleDetalle.getQuantity());
			total = total + articleDetalle.getLinetotal();

		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto encabezado
		// --------------------------------------------------------------------------------------------------------------------------------
		parameters.setDoctotal(total);
		parameters.setCanceled("N");
		parameters.setDocstatus("O");
		parameters.setDoctype("I");
		parameters.setJrnlmemo("Entrada de Mercancia");
		parameters.setConfirmed("Y");
		parameters.setDocduedate(parameters.getDocdate());

		return parameters;
	}

	private List fill_transaction(PurchaseTO document) {
		List _return = new Vector();

		Iterator<PurchaseDetailTO> iterator = document
				.getpurchaseDetails().iterator();
		while (iterator.hasNext()) {
			PurchaseDetailTO detail = (PurchaseDetailTO) iterator
					.next();

			TransactionTo transaction = new TransactionTo();
			transaction.setTransseq(0);
			transaction.setDocentry(document.getDocentry());
			transaction.setDocnum(Integer.toString(document.getDocnum()));
			transaction.setDocduedate(document.getDocduedate());
			transaction.setDocdate(document.getDocdate());
			transaction.setComment(document.getComments());
			transaction.setJrnlmemo(document.getJrnlmemo());
			transaction.setUsersign(document.getUsersign());
			transaction.setRef1("");
			transaction.setRef2(document.getRef1());
			transaction.setLinenum(detail.getLinenum());
			transaction.setItemcode(detail.getItemcode());
			transaction.setDscription(detail.getDscription());
			transaction.setQuantity(detail.getQuantity());
			transaction.setPrice(detail.getPrice());
			transaction.setLinetotal(detail.getLinetotal());
			transaction.setWhscode(detail.getWhscode());
			transaction.setAcctcode(detail.getAcctcode());
			transaction.setOcrcode("");
			transaction.setVatgroup("");
			transaction.setPriceafvat(zero);
			transaction.setVatsum(zero);
			transaction.setObjtype(detail.getObjtype());
			transaction.setGrssprofit(zero);
			transaction.setTaxcode("");
			transaction.setVatappld(zero);
			transaction.setStockprice(detail.getPrice());
			transaction.setGtotal(zero);
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


	public ResultOutTO save_TransactionGoodsReceipt(PurchaseTO purchase,
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
		PurchaseDAO DAO = new PurchaseDAO(conn);
		transactionEJB trans = new transactionEJB();
		JournalEntryTO journal = new JournalEntryTO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar Encabezados y detalles de Entrada
		// --------------------------------------------------------------------------------------------------------------------------------

		_return = inv_purchase_save(purchase, action, conn);
		purchase.setDocentry(_return.getDocentry());
		purchase.setDocnum(_return.getDocentry());

		// --------------------------------------------------------------------------------------------------------------------------------
		// Llenar objeto tipo transacción
		// --------------------------------------------------------------------------------------------------------------------------------

		transactions = fill_transaction(purchase);

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

		journal = fill_JournalEntry(purchase);

		AccountingEJB account = new AccountingEJB();
		res_jour = account.journalEntry_mtto(journal, Common.MTTOINSERT, conn);

		// -----------------------------------------------------------------------------------
		// Actualización de documento con datos de Asiento contable
		// -----------------------------------------------------------------------------------
		purchase.setTransid(res_jour.getDocentry());
		_return = inv_GoodsReceipt_update(purchase, Common.MTTOUPDATE, conn);

		return _return;
	}
	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------

	public List getPurchase(PurchaseInTO param) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		PurchaseDAO DAO = new PurchaseDAO();

		try {
			_return = DAO.getPurchase(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public PurchaseTO getPurchaseByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		PurchaseTO _return = new PurchaseTO();
		PurchaseDAO DAO = new PurchaseDAO();
		try {
			_return = DAO.getPurchaseByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	
	// ####purchasequantition
	public List getPurchaseQuotation(PurchaseQuotationInTO param)
			throws Exception {
		// TODO Auto-generated method stub
		List _return;
		PurchaseQuotationDAO DAO = new PurchaseQuotationDAO();

		try {
			_return = DAO.getPurchaseQuotation(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public PurchaseQuotationTO getPurchaseQuotationByKey(int docentry)
			throws Exception {
		// TODO Auto-generated method stub
		PurchaseQuotationTO _return = new PurchaseQuotationTO();
		PurchaseQuotationDAO DAO = new PurchaseQuotationDAO();
		try {
			_return = DAO.getPurchaseQuotationByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO inv_PurchaseQuotation_mtto(
			PurchaseQuotationTO parameters, int action) throws Exception {
		// TODO Auto-generated method stub
		// pasar el codigo de almacen a los hijos
		Iterator<PurchaseQuotationDetailTO> iterator3 = parameters
				.getPurchaseQuotationDetails().iterator();
		while (iterator3.hasNext()) {

			PurchaseQuotationDetailTO articleDetalle = (PurchaseQuotationDetailTO) iterator3
					.next();
			articleDetalle.setWhscode(parameters.getTowhscode());
		}
		// -------------------------------------------------------------------------------------------------------------------------------------
		// validaciones-------------------------------------------------------------------------------------------------------------------------
		System.out.println("llego al salesejb");
		ResultOutTO _return = new ResultOutTO();
		_return = validate_inv_PurchaseQuotation_mtto(parameters);
		System.out.println(_return.getCodigoError());
		if (_return.getCodigoError() != 0) {
			return _return;
		}
		Double total = 0.0;
		PurchaseQuotationDAO DAO = new PurchaseQuotationDAO();
		DAO.setIstransaccional(true);
		PurchaseQuotationDetailDAO goodDAO1 = new PurchaseQuotationDetailDAO(
				DAO.getConn());
		goodDAO1.setIstransaccional(true);
		try {
			Iterator<PurchaseQuotationDetailTO> iterator2 = parameters
					.getPurchaseQuotationDetails().iterator();
			while (iterator2.hasNext()) {
				PurchaseQuotationDetailTO articleDetalle = (PurchaseQuotationDetailTO) iterator2
						.next();
				articleDetalle.setDiscprcnt(articleDetalle.getQuantity());
				articleDetalle.setOpenqty(articleDetalle.getQuantity());
				articleDetalle.setFactor1(articleDetalle.getQuantity());

			}

			parameters.setDiscsum(0.00);
			parameters.setNret(0.00);
			parameters.setPaidsum(0.00);
			parameters.setRounddif(0.00);
			_return.setDocentry(DAO.inv_PurchaseQuotation_mtto(parameters,
					action));

			Iterator<PurchaseQuotationDetailTO> iterator = parameters
					.getPurchaseQuotationDetails().iterator();
			while (iterator.hasNext()) {
				PurchaseQuotationDetailTO articleDetalle = (PurchaseQuotationDetailTO) iterator
						.next();
				// Para articulos nuevos
				articleDetalle.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					goodDAO1.inv_PurchaseQuotationDetail_mtto(articleDetalle,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					goodDAO1.inv_PurchaseQuotationDetail_mtto(articleDetalle,
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

	public List getPurchaseDetail(int docentry) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		PurchaseDetailDAO DAO = new PurchaseDetailDAO();

		try {
			_return = DAO.getpurchaseDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public List getSupplier(SupplierInTO param) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		SupplierDAO DAO = new SupplierDAO();

		try {
			_return = DAO.getSupplier(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public SupplierTO getSupplierByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		SupplierTO _return = new SupplierTO();
		SupplierDAO DAO = new SupplierDAO();
		try {
			_return = DAO.getSupplierByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO inv_Supplier_mtto(SupplierTO parameters, int action)
			throws Exception {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();

		// pasar el codigo de almacen a los hijos
		Iterator<SupplierDetailTO> iterator3 = parameters.getsupplierDetails()
				.iterator();
		while (iterator3.hasNext()) {

			SupplierDetailTO articleDetalle = (SupplierDetailTO) iterator3
					.next();
			articleDetalle.setWhscode(parameters.getTowhscode());
		}
		// -------------------------------------------------------------------------------------------------------------------------------
		// validaciones-------------------------------------------------------------------------------------------------------------------
		_return = validate_inv_supplier_mtto(parameters);
		System.out.println(_return.getCodigoError());
		if (_return.getCodigoError() != 0) {
			return _return;
		}

		SupplierDAO DAO = new SupplierDAO();
		DAO.setIstransaccional(true);
		SupplierDetailDAO goodDAO1 = new SupplierDetailDAO(DAO.getConn());
		goodDAO1.setIstransaccional(true);
		try {
			Iterator<SupplierDetailTO> iterator2 = parameters
					.getsupplierDetails().iterator();
			while (iterator2.hasNext()) {
				SupplierDetailTO articleDetalle = (SupplierDetailTO) iterator2
						.next();

				articleDetalle.setDiscprcnt(articleDetalle.getQuantity());
				articleDetalle.setOpenqty(articleDetalle.getQuantity());

				articleDetalle.setFactor1(articleDetalle.getQuantity());

			}
			;
			parameters.setDiscsum(0.00);
			parameters.setNret(0.00);
			parameters.setPaidsum(0.00);
			parameters.setRounddif(0.00);
			_return.setDocentry(DAO.inv_Supplier_mtto(parameters, action));

			Iterator<SupplierDetailTO> iterator = parameters
					.getsupplierDetails().iterator();
			while (iterator.hasNext()) {
				SupplierDetailTO articleDetalle = (SupplierDetailTO) iterator
						.next();
				// Para articulos nuevos
				articleDetalle.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					goodDAO1.inv_SupplierDetail_mtto(articleDetalle,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					goodDAO1.inv_SupplierDetail_mtto(articleDetalle,
							Common.MTTODELETE);
				}
			}
			DAO.forceCommit();
			_return.setCodigoError(0);
			_return.setMensaje("Datos guardados correctamente");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}

		return _return;
	}

	public List getSupplierDetail(int docentry) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		SupplierDetailDAO DAO = new SupplierDetailDAO();

		try {
			_return = DAO.getsupplierDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	// validando inv_purchase_mtto
	public ResultOutTO validate_inv_Purchase_mtto(PurchaseTO parameters)
			throws EJBException {
		System.out.println("llego al validate purchase_mtto ");
		boolean valid = false;
		ResultOutTO _return = new ResultOutTO();
		AccountingEJB acc = new AccountingEJB();
		CatalogEJB Businesspartner = new CatalogEJB();
		AdminEJB EJB1 = new AdminEJB();
		List branch = new Vector();
		ArticlesTO DBArticle = new ArticlesTO();
		String code;
		// ------------------------------------------------------------------------------------------------------------
		// validaciones
		// ------------------------------------------------------------------------------------------------------------

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
		// Validación de fecha de periodo contable
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
		// Validación del socio de negocio
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

		Iterator<PurchaseDetailTO> iterator1 = parameters.getpurchaseDetails()
				.iterator();

		// recorre el ClientCrediDetail
		while (iterator1.hasNext()) {
			AdminEJB EJB = new AdminEJB();
			// Consultar información actualizada desde la base
			PurchaseDetailTO PurchaseDetail = (PurchaseDetailTO) iterator1
					.next();
			code = PurchaseDetail.getItemcode();

			DBArticle = EJB.getArticlesByKey(code);

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo existe
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle != null) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(PurchaseDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ PurchaseDetail.getItemcode() + " "
						+ PurchaseDetail.getDscription()

						+ " no existe,informar al administrador. linea :"
						+ PurchaseDetail.getLinenum());
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
				_return.setLinenum(PurchaseDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ PurchaseDetail.getItemcode() + " "
						+ PurchaseDetail.getDscription()

						+ " No esta activo. linea :"
						+ PurchaseDetail.getLinenum());
				System.out.println(valid);
				return _return;

			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo de compra
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle.getPrchseItem() != null
					&& DBArticle.getPrchseItem().toUpperCase().equals("Y")) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(PurchaseDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ PurchaseDetail.getItemcode() + " "
						+ PurchaseDetail.getDscription()
						+ " No es un articulo de venta. linea :"
						+ PurchaseDetail.getLinenum());
				return _return;
			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación almacen bloqueado para articulo
			// ------------------------------------------------------------------------------------------------------------
			valid = false;

			branch = DBArticle.getBranchArticles();

			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;
				System.out.println(branch1.getWhscode());
				System.out.println(PurchaseDetail.getWhscode());
				if (branch1.getWhscode().equals(PurchaseDetail.getWhscode())) {
					if (branch1.getWhscode() != null
							&& branch1.getLocked().toUpperCase().equals("F")) {
						valid = true;
					}
				}
			}

			if (!valid) {
				_return.setLinenum(PurchaseDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ PurchaseDetail.getItemcode()
						+ " "
						+ PurchaseDetail.getDscription()
						+ " No esta asignado o esta bloquedo para el almacen indicado. linea :"
						+ PurchaseDetail.getLinenum());
				return _return;
			}

		}
		_return.setCodigoError(0);

		return _return;

	}

	public ResultOutTO validate_inv_PurchaseQuotation_mtto(
			PurchaseQuotationTO parameters) throws Exception {
		System.out.println("llego al validate purchase_quotation_mtto ");
		boolean valid = false;
		ResultOutTO _return = new ResultOutTO();
		AccountingEJB acc = new AccountingEJB();
		CatalogEJB Businesspartner = new CatalogEJB();
		AdminEJB EJB1 = new AdminEJB();
		List branch = new Vector();
		ArticlesTO DBArticle = new ArticlesTO();
		String code;
		// ------------------------------------------------------------------------------------------------------------
		// validaciones
		// ------------------------------------------------------------------------------------------------------------

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
		// Validación de fecha de periodo contable
		// ------------------------------------------------------------------------------------------------------------
		if (parameters.getDocdate() == null) {
			_return.setCodigoError(1);
			_return.setMensaje("no se encuentra fecha");

			return _return;
		}
		_return = acc.validate_exist_accperiod(parameters.getDocdate());
		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("El documento tiene una fecha Fuera del periodo contable activo");
			return _return;
		}
		// ------------------------------------------------------------------------------------------------------------
		// Validación del socio de negocio
		// ------------------------------------------------------------------------------------------------------------
		if (parameters.getCardcode() == null) {
			_return.setCodigoError(1);
			_return.setMensaje("Codigo de socio vacio");

			return _return;
		}
		_return = Businesspartner.validate_businesspartnerBykey(parameters
				.getCardcode());
		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("el socio de negocio no esta activo para esta transaccion");
			return _return;
		}

		Iterator<PurchaseQuotationDetailTO> iterator1 = parameters
				.getPurchaseQuotationDetails().iterator();

		// recorre el ClientCrediDetail
		while (iterator1.hasNext()) {
			AdminEJB EJB = new AdminEJB();
			// Consultar información actualizada desde la base
			PurchaseQuotationDetailTO PurchaseQuotationDetail = (PurchaseQuotationDetailTO) iterator1
					.next();
			code = PurchaseQuotationDetail.getItemcode();

			DBArticle = EJB.getArticlesByKey(code);

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo existe
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle != null) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(PurchaseQuotationDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ PurchaseQuotationDetail.getItemcode() + " "
						+ PurchaseQuotationDetail.getDscription()

						+ " no existe,informar al administrador. linea :"
						+ PurchaseQuotationDetail.getLinenum());
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
				_return.setLinenum(PurchaseQuotationDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ PurchaseQuotationDetail.getItemcode() + " "
						+ PurchaseQuotationDetail.getDscription()

						+ " No esta activo. linea :"
						+ PurchaseQuotationDetail.getLinenum());
				System.out.println(valid);
				return _return;

			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo de compra
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle.getPrchseItem() != null
					&& DBArticle.getPrchseItem().toUpperCase().equals("Y")) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(PurchaseQuotationDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ PurchaseQuotationDetail.getItemcode() + " "
						+ PurchaseQuotationDetail.getDscription()
						+ " No es un articulo de venta. linea :"
						+ PurchaseQuotationDetail.getLinenum());
				return _return;
			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación almacen bloqueado para articulo
			// ------------------------------------------------------------------------------------------------------------
			valid = false;

			branch = DBArticle.getBranchArticles();

			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;
				System.out.println(branch1.getWhscode());
				System.out.println(PurchaseQuotationDetail.getWhscode());
				if (branch1.getWhscode().equals(
						PurchaseQuotationDetail.getWhscode())) {
					if (branch1.getWhscode() != null
							&& branch1.getLocked().toUpperCase().equals("F")) {
						valid = true;
					}
				}
			}

			if (!valid) {
				_return.setLinenum(PurchaseQuotationDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ PurchaseQuotationDetail.getItemcode()
						+ " "
						+ PurchaseQuotationDetail.getDscription()
						+ " No esta asignado o esta bloquedo para el almacen indicado. linea :"
						+ PurchaseQuotationDetail.getLinenum());
				return _return;
			}

		}
		_return.setCodigoError(0);

		return _return;

	}

	public ResultOutTO validate_inv_supplier_mtto(SupplierTO parameters)
			throws Exception {
		System.out.println("llego al validate inv_supplier_mtto ");
		boolean valid = false;
		ResultOutTO _return = new ResultOutTO();
		AccountingEJB acc = new AccountingEJB();
		CatalogEJB Businesspartner = new CatalogEJB();
		AdminEJB EJB1 = new AdminEJB();
		List branch = new Vector();
		ArticlesTO DBArticle = new ArticlesTO();
		String code;
		// ------------------------------------------------------------------------------------------------------------
		// validaciones
		// ------------------------------------------------------------------------------------------------------------

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
		// Validación de fecha de periodo contable
		// ------------------------------------------------------------------------------------------------------------
		if (parameters.getDocdate() == null) {
			_return.setCodigoError(1);
			_return.setMensaje("no se encuentra fecha");

			return _return;
		}
		_return = acc.validate_exist_accperiod(parameters.getDocdate());
		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("El documento tiene una fecha Fuera del periodo contable activo");
			return _return;
		}
		// ------------------------------------------------------------------------------------------------------------
		// Validación del socio de negocio
		// ------------------------------------------------------------------------------------------------------------
		if (parameters.getCardcode() == null) {
			_return.setCodigoError(1);
			_return.setMensaje("Codigo de socio de negocio null");

			return _return;
		}
		_return = Businesspartner.validate_businesspartnerBykey(parameters
				.getCardcode());
		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("el socio de negocio no esta activo para esta transaccion");
			return _return;
		}

		// recorre el ClientCrediDetail
		Iterator<SupplierDetailTO> iterator1 = parameters.getsupplierDetails()
				.iterator();
		while (iterator1.hasNext()) {
			AdminEJB EJB = new AdminEJB();
			// Consultar información actualizada desde la base
			SupplierDetailTO SupplierDetail = (SupplierDetailTO) iterator1
					.next();
			code = SupplierDetail.getItemcode();

			DBArticle = EJB.getArticlesByKey(code);

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo existe
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle != null) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(SupplierDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ SupplierDetail.getItemcode() + " "
						+ SupplierDetail.getDscription()

						+ " no existe,informar al administrador. linea :"
						+ SupplierDetail.getLinenum());
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
				_return.setLinenum(SupplierDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ SupplierDetail.getItemcode() + " "
						+ SupplierDetail.getDscription()

						+ " No esta activo. linea :"
						+ SupplierDetail.getLinenum());
				System.out.println(valid);
				return _return;

			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo de compra
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle.getPrchseItem() != null
					&& DBArticle.getPrchseItem().toUpperCase().equals("Y")) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(SupplierDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ SupplierDetail.getItemcode() + " "
						+ SupplierDetail.getDscription()
						+ " No es un articulo de venta. linea :"
						+ SupplierDetail.getLinenum());
				return _return;
			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación almacen bloqueado para articulo
			// ------------------------------------------------------------------------------------------------------------
			valid = false;

			branch = DBArticle.getBranchArticles();

			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;
				System.out.println(branch1.getWhscode());
				System.out.println(SupplierDetail.getWhscode());
				if (branch1.getWhscode().equals(SupplierDetail.getWhscode())) {
					if (branch1.getWhscode() != null
							&& branch1.getLocked().toUpperCase().equals("F")) {
						valid = true;
					}
				}
			}

			if (!valid) {
				_return.setLinenum(SupplierDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ SupplierDetail.getItemcode()
						+ " "
						+ SupplierDetail.getDscription()
						+ " No esta asignado o esta bloquedo para el almacen indicado. linea :"
						+ SupplierDetail.getLinenum());
				return _return;
			}

		}
		_return.setCodigoError(0);

		return _return;

	}

	public JournalEntryTO fill_JournalEntry(PurchaseTO parameters)
			throws Exception {

		String buss_c;
		String branch_c;
		String iva_c;
		String fovialCotrans_c = null;
		double bussines = 0;
		double branch = 0;
		double tax = 0;
		double fovc = 0;

		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		boolean ind = false;
		Double total = zero;
		List list = parameters.getpurchaseDetails();
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();
		// recorre la lista de detalles
		for (Object obj : list) {
			PurchaseDetailTO good = (PurchaseDetailTO) obj;
			String cod = good.getAcctcode();
			List lisHija = new Vector();
			// calculando los impuestos y saldo de las cuentas
			// --------------------------------------------------------------------------------
			branch = branch + good.getLinetotal();
			double impuesto = good.getLinetotal() * 0.13;
			fovc = fovc + (good.getVatsum() - impuesto);
			tax = tax + impuesto;
			bussines = bussines + good.getGtotal();

		}
		// consultando en la base de datos los codigos de cuenta asignados
		// cuenta de bussines partner
		buss_c = parameters.getCtlaccount();
		// buscar la cuenta asignada al almacen
		AdminDAO admin = new AdminDAO();

		BranchTO branch1 = new BranchTO();
		// buscando la cuenta asignada de cueta de existencias al almacen

		branch1 = admin.getBranchByKey(parameters.getTowhscode());
		branch_c = branch1.getBalinvntac();
		if (branch1.getBalinvntac() == null) {
			throw new Exception("No hay una cuenta contable asignada");
		}

		// buscando cuenta asignada para iva y fovial
		if (fovc != 0) {
			CatalogTO Catalog = new CatalogTO();
			Catalog = admin.findCatalogByKey("FOV1", 10);
			fovialCotrans_c = Catalog.getCatvalue2();
			iva_c = Catalog.getCatvalue();
		} else {
			CatalogTO Catalog = new CatalogTO();
			Catalog = admin.findCatalogByKey("IVA", 10);
			iva_c = Catalog.getCatvalue2();
		}
		// asiento contable

		JournalEntryLinesTO art1 = new JournalEntryLinesTO();
		JournalEntryLinesTO art2 = new JournalEntryLinesTO();
		JournalEntryLinesTO art3 = new JournalEntryLinesTO();
		JournalEntryLinesTO art4 = new JournalEntryLinesTO();
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// llenado del asiento contable
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// LLenado del padre
		List detail = new Vector();
		nuevo.setObjtype("5");
		nuevo.setMemo("por Compra de Repuestos");
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setLoctotal(bussines);
		nuevo.setSystotal(bussines);
		// llenado de los
		// hijos---------------------------------------------------------------------------------------------------
		// cuenta del socio de negocio
		art1.setLine_id(1);
		art1.setCredit(bussines);
		;
		art1.setAccount(buss_c);
		detail.add(art1);
		// cuenta asignada al almacen
		art2.setLine_id(2);
		art2.setAccount(branch_c);
		art2.setDebit(branch);
		detail.add(art2);
		// cuenta de iva
		art3.setLine_id(3);
		art3.setDebit(tax);
		art3.setAccount(iva_c);
		detail.add(art3);
		// cuenta de cotrans y fovial si se aplica el impuesto
		if (fovc != 0) {
			art4.setLine_id(4);
			art4.setDebit(fovc);
			art4.setAccount(fovialCotrans_c);
			detail.add(art4);
		}
		nuevo.setJournalentryList(detail);
		return nuevo;
	}
}

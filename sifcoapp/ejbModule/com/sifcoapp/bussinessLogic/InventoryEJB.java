package com.sifcoapp.bussinessLogic;

import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.admin.ejb.AdminEJB;
import com.sifcoapp.bussinessLogic.InventoryEJBLocal;
import com.sifcoapp.objects.accounting.dao.JournalEntryDAO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.*;
import com.sifcoapp.objects.inventory.to.*;
import com.sifcoapp.objects.sales.to.DeliveryDetailTO;
import com.sifcoapp.objects.transaction.dao.TransactionDAO;
import com.sifcoapp.objects.transaction.to.InventoryLogTO;
import com.sifcoapp.objects.transaction.to.TransactionTo;
import com.sifcoapp.objects.transaction.to.WarehouseJournalLayerTO;
import com.sifcoapp.objects.transaction.to.WarehouseJournalTO;
import com.sifcoapp.transaction.ejb.transactionEJB;

/**
 * Session Bean implementation class InventoryEJB
 */
@Stateless
public class InventoryEJB implements InventoryEJBRemote, InventoryEJBLocal {
	Double zero = 0.0;

	/**
	 * Default constructor.
	 */
	public InventoryEJB() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * public List getListGoodsreceipt(int docnum, Date docdate, int series) {
	 * // TODO Auto-generated method stub GoodsreceiptDAO List _return = new
	 * Vector(); GoodsreceiptDAO DAO = new GoodsreceiptDAO(); _return =
	 * DAO.getListGoodsreceipt(docnum, docdate, series);
	 * 
	 * return _return; }
	 */

	// #region principal Entrada de inventario

	public ResultOutTO inv_GoodsReceipt_mtto(GoodsreceiptTO parameters,
			int action) throws EJBException {

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

		_valid = validate_goodsReceipt(parameters, action);

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

	private GoodsreceiptTO fillGoodReceipt(GoodsreceiptTO parameters) {

		// variables
		Double total = zero;
		ArticlesTO DBArticle = new ArticlesTO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto detalle
		// --------------------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings("unchecked")
		Iterator<GoodsReceiptDetailTO> iterator = parameters
				.getGoodReceiptDetail().iterator();
		while (iterator.hasNext()) {
			GoodsReceiptDetailTO articleDetalle = (GoodsReceiptDetailTO) iterator
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
		parameters.setRef1(Integer.toString(parameters.getDocnum()));
		parameters.setJrnlmemo("entrada de inventario");

		return parameters;
	}

	public ResultOutTO validate_goodsReceipt(GoodsreceiptTO parameters,
			int action) throws EJBException {

		// Variables
		boolean valid = false;
		ResultOutTO _return = new ResultOutTO();
		AccountingEJB acc = new AccountingEJB();
		AdminEJB EJB1 = new AdminEJB();
		List branch = new Vector();
		ArticlesTO DBArticle = new ArticlesTO();
		String code;

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
			_return.setMensaje("No se encuentra fecha del documento");

			return _return;
		}
		_return = acc.validate_exist_accperiod(parameters.getDocdate());
		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("El documento tiene una fecha Fuera del periodo contable activo");
			return _return;
		}

		// ------------------------------------------------------------------------------------------------------------
		// Validación de Socios de Negocio Activo
		// ------------------------------------------------------------------------------------------------------------

		Iterator<GoodsReceiptDetailTO> iterator1 = parameters
				.getGoodReceiptDetail().iterator();

		// recorre el Detalle
		while (iterator1.hasNext()) {

			// Consultar información actualizada desde la base
			GoodsReceiptDetailTO GoodsReceiptDetail = (GoodsReceiptDetailTO) iterator1
					.next();

			DBArticle = GoodsReceiptDetail.getArticle();

			// Asignar articulo al detalle
			GoodsReceiptDetail.setArticle(DBArticle);

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo existe
			// ------------------------------------------------------------------------------------------------------------

			valid = false;
			if (DBArticle != null) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(GoodsReceiptDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ GoodsReceiptDetail.getItemcode() + " "
						+ GoodsReceiptDetail.getDscription()

						+ " no existe,informar al administrador. linea :"
						+ GoodsReceiptDetail.getLinenum());
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
				_return.setLinenum(GoodsReceiptDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ GoodsReceiptDetail.getItemcode() + " "
						+ GoodsReceiptDetail.getDscription()

						+ " No esta activo. linea :"
						+ GoodsReceiptDetail.getLinenum());
				System.out.println(valid);
				return _return;

			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo de inventario
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle.getInvntItem() != null
					&& DBArticle.getInvntItem().toUpperCase().equals("Y")) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(GoodsReceiptDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ GoodsReceiptDetail.getItemcode() + " "
						+ GoodsReceiptDetail.getDscription()
						+ " No es un articulo de venta. linea :"
						+ GoodsReceiptDetail.getLinenum());
				return _return;
			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación almacen bloqueado para articulo
			// ------------------------------------------------------------------------------------------------------------
			valid = false;

			branch = DBArticle.getBranchArticles();

			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;
				if (branch1.getWhscode()
						.equals(GoodsReceiptDetail.getWhscode())) {
					if (branch1.isIsasociated() && branch1.getLocked() != null
							&& branch1.getLocked().toUpperCase().equals("F")) {
						valid = true;
					}
				}
			}

			if (!valid) {
				_return.setLinenum(GoodsReceiptDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ GoodsReceiptDetail.getItemcode()
						+ " "
						+ GoodsReceiptDetail.getDscription()
						+ " No esta asignado o esta bloquedo para el almacen indicado. linea :"
						+ GoodsReceiptDetail.getLinenum());
				return _return;
			}

		}
		_return.setCodigoError(0);

		return _return;

	}

	public ResultOutTO save_TransactionGoodsReceipt(GoodsreceiptTO goodReceipt,
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
		GoodsReceiptDAO DAO = new GoodsReceiptDAO(conn);
		transactionEJB trans = new transactionEJB();
		JournalEntryTO journal = new JournalEntryTO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar Encabezados y detalles de Entrada
		// --------------------------------------------------------------------------------------------------------------------------------

		_return = inv_GoodsReceipt_save(goodReceipt, action, conn);
		goodReceipt.setDocentry(_return.getDocentry());
		goodReceipt.setDocnum(_return.getDocentry());

		// --------------------------------------------------------------------------------------------------------------------------------
		// Llenar objeto tipo transacción
		// --------------------------------------------------------------------------------------------------------------------------------

		transactions = fill_transaction(goodReceipt);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Calculo de existencias y costos
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

		journal = fill_JournalEntry(goodReceipt);

		AccountingEJB account = new AccountingEJB();
		res_jour = account.journalEntry_mtto(journal, Common.MTTOINSERT, conn);

		// -----------------------------------------------------------------------------------
		// Actualización de documento con datos de Asiento contable
		// -----------------------------------------------------------------------------------
		goodReceipt.setTransid(res_jour.getDocentry());
		_return = inv_GoodsReceipt_update(goodReceipt, Common.MTTOUPDATE, conn);

		return _return;
	}

	private List fill_transaction(GoodsreceiptTO document) {
		List _return = new Vector();

		Iterator<GoodsReceiptDetailTO> iterator = document
				.getGoodReceiptDetail().iterator();
		while (iterator.hasNext()) {
			GoodsReceiptDetailTO detail = (GoodsReceiptDetailTO) iterator
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
			transaction.setRef1(document.getRef1());
			transaction.setRef2(document.getRef1());
			transaction.setLinenum(detail.getLinenum());
			transaction.setItemcode(detail.getItemcode());
			transaction.setDscription(detail.getDscription());
			transaction.setQuantity(detail.getQuantity());
			transaction.setPrice(detail.getPrice());
			transaction.setLinetotal(detail.getLinetotal());
			transaction.setWhscode(detail.getWhscode());
			transaction.setAcctcode(detail.getAcctcode());
			transaction.setOcrcode(String.valueOf(document.getUsersign()));
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

	public ResultOutTO inv_GoodsReceipt_update(GoodsreceiptTO parameters,
			int action) throws EJBException {
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

	public ResultOutTO inv_GoodsReceipt_update(GoodsreceiptTO parameters,
			int action, Connection conn) throws Exception {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		GoodsReceiptDAO DAO = new GoodsReceiptDAO(conn);
		DAO.setIstransaccional(true);
		GoodReceiptDetailDAO goodDAO1 = new GoodReceiptDetailDAO(conn);
		goodDAO1.setIstransaccional(true);

		// Actualizar/borrar encabezados
		_return.setDocentry(DAO.inv_GoodsReceipt_mtto(parameters, action));

		// Borrar detalles
		Iterator<GoodsReceiptDetailTO> iterator = parameters
				.getGoodReceiptDetail().iterator();
		if (action == Common.MTTODELETE) {
			while (iterator.hasNext()) {
				GoodsReceiptDetailTO detalleReceipt = (GoodsReceiptDetailTO) iterator
						.next();

				goodDAO1.inv_goodReceiptDetail_mtto(detalleReceipt,
						Common.MTTODELETE);
			}
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos Actualizados con exito");
		return _return;
	}

	public ResultOutTO inv_GoodsReceipt_save(GoodsreceiptTO parameters,
			int action, Connection conn) throws Exception {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		GoodsReceiptDAO DAO = new GoodsReceiptDAO(conn);
		DAO.setIstransaccional(true);
		GoodReceiptDetailDAO goodDAO1 = new GoodReceiptDetailDAO(conn);
		goodDAO1.setIstransaccional(true);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar encabezados
		// --------------------------------------------------------------------------------------------------------------------------------

		_return.setDocentry(DAO.inv_GoodsReceipt_mtto(parameters, action));

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar detalles
		// --------------------------------------------------------------------------------------------------------------------------------

		Iterator<GoodsReceiptDetailTO> iterator = parameters
				.getGoodReceiptDetail().iterator();
		while (iterator.hasNext()) {
			GoodsReceiptDetailTO detalleReceipt = (GoodsReceiptDetailTO) iterator
					.next();

			detalleReceipt.setDocentry(_return.getDocentry());
			goodDAO1.inv_goodReceiptDetail_mtto(detalleReceipt,
					Common.MTTOINSERT);
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	// #region Consultas Entradas inventario
	public List getGoodsReceiptDetail(int docentry) throws EJBException {
		// TODO Auto-generated method stub GoodsissuesDAO
		List _return = new Vector();
		GoodReceiptDetailDAO DAO = new GoodReceiptDetailDAO();
		try {
			_return = DAO.getGoodReceiptDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public List getGoodsreceipt(GoodsReceiptInTO param) throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		GoodsReceiptDAO DAO = new GoodsReceiptDAO();
		try {
			_return = DAO.getGoodsreceipt(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public GoodsreceiptTO getGoodsReceiptByKey(int docentry)
			throws EJBException {
		// TODO Auto-generated method stub
		GoodsreceiptTO _return = null;
		GoodsReceiptDAO GoodDAO = new GoodsReceiptDAO();
		try {
			_return = GoodDAO.getGoodsReceiptByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	// #endregion consultas entradas

	// #endregion principal entradas
	// ---------------------------------------------------------------------------------------------------------------------------------------------------
	// #region Salidas de Inventario
	// ---------------------------------------------------------------------------------------------------------------------------------------------------

	public ResultOutTO inv_goodsissues_mtto(GoodsissuesTO parameters, int action)
			throws EJBException {

		// Declaración de variables

		ResultOutTO _valid = new ResultOutTO();
		ResultOutTO _return = new ResultOutTO();
		GoodsReceiptDAO DAO = new GoodsReceiptDAO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Validar acción a realizar
		// --------------------------------------------------------------------------------------------------------------------------------

		if (action != Common.MTTOINSERT) {
			_return = inv_goodsissues_update(parameters, action);
			return _return;
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Asignación de valores por defecto y llenado:
		// Estas se realizan solo para cuando es guardar, el actualizar y borrar
		// no aplican.
		// --------------------------------------------------------------------------------------------------------------------------------
		parameters = fillgoodsissues(parameters);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Hacer validaciones:
		// Estas se realizan solo para cuando es guardar, el actualizar y borrar
		// no aplican para validaciones
		// --------------------------------------------------------------------------------------------------------------------------------

		_valid = valid_goodsissues_mtto(parameters);

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
			_return = save_Transactiongoodsissues(parameters, action,
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

	private GoodsissuesTO fillgoodsissues(GoodsissuesTO parameters) {

		// variables
		Double total = zero;
		ArticlesTO DBArticle = new ArticlesTO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto detalle
		// --------------------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings("unchecked")
		Iterator<GoodsIssuesDetailTO> iterator = parameters
				.getGoodIssuesDetail().iterator();
		while (iterator.hasNext()) {
			GoodsIssuesDetailTO articleDetalle = (GoodsIssuesDetailTO) iterator
					.next();

			AdminEJB EJB = new AdminEJB();

			DBArticle = EJB.getArticlesByKey(articleDetalle.getItemcode());

			// Asignar a documento
			articleDetalle.setArticle(DBArticle);

			// Asignación de almacen en cada detalle
			articleDetalle.setWhscode(parameters.getFromwhscode());

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
		parameters.setJrnlmemo("Salida de de Mercancia");
		parameters.setConfirmed("Y");
		parameters.setDocduedate(parameters.getDocdate());
		parameters.setObjtype(parameters.getObjtype());

		return parameters;
	}

	public ResultOutTO save_Transactiongoodsissues(GoodsissuesTO goodissues,
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
		GoodsIssuesDAO DAO = new GoodsIssuesDAO();
		transactionEJB trans = new transactionEJB();
		JournalEntryTO journal = new JournalEntryTO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar Encabezados y detalles de Entrada
		// --------------------------------------------------------------------------------------------------------------------------------

		_return = inv_goodsissues_save(goodissues, action, conn);
		goodissues.setDocentry(_return.getDocentry());
		goodissues.setDocnum(_return.getDocentry());

		// --------------------------------------------------------------------------------------------------------------------------------
		// Llenar objeto tipo transacción
		// --------------------------------------------------------------------------------------------------------------------------------

		transactions = fill_transaction(goodissues);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Calculo de existencias y costos
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

		journal = fill_JournalEntry(goodissues);

		AccountingEJB account = new AccountingEJB();
		res_jour = account.journalEntry_mtto(journal, Common.MTTOINSERT, conn);

		// -----------------------------------------------------------------------------------
		// Actualización de documento con datos de Asiento contable
		// -----------------------------------------------------------------------------------
		goodissues.setTransid(res_jour.getDocentry());
		_return = inv_goodsissues_update(goodissues, Common.MTTOUPDATE, conn);

		return _return;
	}

	private List fill_transaction(GoodsissuesTO document) {
		List _return = new Vector();

		Iterator<GoodsIssuesDetailTO> iterator = document.getGoodIssuesDetail()
				.iterator();
		while (iterator.hasNext()) {
			GoodsIssuesDetailTO detail = (GoodsIssuesDetailTO) iterator.next();

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

	public ResultOutTO inv_goodsissues_update(GoodsissuesTO parameters,
			int action) throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		GoodsIssuesDAO DAO = new GoodsIssuesDAO();
		try {
			_return = inv_goodsissues_update(parameters, action, DAO.getConn());
			DAO.forceCommit();
		} catch (Exception e) {
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {
			DAO.forceCloseConnection();
		}
		return _return;

	}

	public ResultOutTO inv_goodsissues_update(GoodsissuesTO parameters,
			int action, Connection conn) throws Exception {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		GoodsIssuesDAO DAO = new GoodsIssuesDAO();
		DAO.setIstransaccional(true);
		GoodsissuesDetailDAO goodDAO1 = new GoodsissuesDetailDAO(conn);
		goodDAO1.setIstransaccional(true);

		// Actualizar/borrar encabezados
		_return.setDocentry(DAO.inv_goodsissues_mtto(parameters, action));

		// Borrar detalles
		Iterator<GoodsIssuesDetailTO> iterator = parameters
				.getGoodIssuesDetail().iterator();
		if (action == Common.MTTODELETE) {
			while (iterator.hasNext()) {
				GoodsIssuesDetailTO detalleReceipt = (GoodsIssuesDetailTO) iterator
						.next();

				goodDAO1.inv_goodsIssuesDetail_mtto(detalleReceipt,
						Common.MTTODELETE);
			}
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos Actualizados con exito");
		return _return;
	}

	public ResultOutTO inv_goodsissues_save(GoodsissuesTO parameters,
			int action, Connection conn) throws Exception {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		GoodsIssuesDAO DAO = new GoodsIssuesDAO();
		DAO.setIstransaccional(true);
		GoodsissuesDetailDAO goodDAO1 = new GoodsissuesDetailDAO(conn);
		goodDAO1.setIstransaccional(true);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar encabezados
		// --------------------------------------------------------------------------------------------------------------------------------

		_return.setDocentry(DAO.inv_goodsissues_mtto(parameters, action));

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar detalles
		// --------------------------------------------------------------------------------------------------------------------------------

		Iterator<GoodsIssuesDetailTO> iterator = parameters
				.getGoodIssuesDetail().iterator();
		while (iterator.hasNext()) {
			GoodsIssuesDetailTO detalleIssues = (GoodsIssuesDetailTO) iterator
					.next();

			detalleIssues.setDocentry(_return.getDocentry());
			goodDAO1.inv_goodsIssuesDetail_mtto(detalleIssues,
					Common.MTTOINSERT);
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public ResultOutTO valid_goodsissues_mtto(GoodsissuesTO parameters)
			throws EJBException {
		System.out.println("llego al valid_goodsissues_mtto ");
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
		if (parameters.getFromwhscode() == null) {
			_return.setCodigoError(1);
			_return.setMensaje("Codigo de almacen null");

			return _return;
		}
		_return = EJB1.validate_branchActiv(parameters.getFromwhscode());

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

		Iterator<GoodsIssuesDetailTO> iterator1 = parameters
				.getGoodIssuesDetail().iterator();

		// recorre el ClientCrediDetail
		while (iterator1.hasNext()) {
			AdminEJB EJB = new AdminEJB();
			// Consultar información actualizada desde la base
			GoodsIssuesDetailTO GoodsIssuesDetail = (GoodsIssuesDetailTO) iterator1
					.next();
			code = GoodsIssuesDetail.getItemcode();

			DBArticle = EJB.getArticlesByKey(code);

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo existe
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle != null) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(GoodsIssuesDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ GoodsIssuesDetail.getItemcode() + " "
						+ GoodsIssuesDetail.getDscription()

						+ " no existe,informar al administrador. linea :"
						+ GoodsIssuesDetail.getLinenum());
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
				_return.setLinenum(GoodsIssuesDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ GoodsIssuesDetail.getItemcode() + " "
						+ GoodsIssuesDetail.getDscription()

						+ " No esta activo. linea :"
						+ GoodsIssuesDetail.getLinenum());
				System.out.println(valid);
				return _return;

			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo de compra
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle.getInvntItem() != null
					&& DBArticle.getInvntItem().toUpperCase().equals("Y")) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(GoodsIssuesDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ GoodsIssuesDetail.getItemcode() + " "
						+ GoodsIssuesDetail.getDscription()
						+ " No es un articulo de venta. linea :"
						+ GoodsIssuesDetail.getLinenum());
				return _return;
			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación almacen bloqueado para articulo
			// ------------------------------------------------------------------------------------------------------------
			valid = false;

			branch = DBArticle.getBranchArticles();

			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;

				if (branch1.getWhscode().equals(GoodsIssuesDetail.getWhscode())) {
					if (branch1.isIsasociated() && branch1.getLocked() != null
							&& branch1.getLocked().toUpperCase().equals("F")) {
						valid = true;
					}
				}
			}

			if (!valid) {
				_return.setLinenum(GoodsIssuesDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ GoodsIssuesDetail.getItemcode()
						+ " "
						+ GoodsIssuesDetail.getDscription()
						+ " No esta asignado o esta bloquedo para el almacen indicado. linea :"
						+ GoodsIssuesDetail.getLinenum());
				return _return;
			}
			// ------------------------------------------------------------------------------------------------------------
			// Validaciones de Existencia
			// ------------------------------------------------------------------------------------------------------------
			valid = false;

			Double stocks = 0.000;
			Iterator<GoodsIssuesDetailTO> iterator2 = parameters
					.getGoodIssuesDetail().iterator();
			// recorre de nuevo el detalle comparando el primer elemento con los
			// demas
			while (iterator2.hasNext()) {
				GoodsIssuesDetailTO articleDetalle2 = (GoodsIssuesDetailTO) iterator2
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
					System.out.println("sumatoria" + stocks + "base"
							+ branch1.getOnhand());
					if (stocks <= branch1.getOnhand()) {
						valid = true;
					}
				}

			}
			if (!valid) {
				_return.setLinenum(GoodsIssuesDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ GoodsIssuesDetail.getItemcode() + " "
						+ GoodsIssuesDetail.getDscription()
						+ " Recae en un Inventario Negativo. linea :"
						+ GoodsIssuesDetail.getLinenum());
				return _return;
			}

		}
		_return.setCodigoError(0);

		return _return;

	}

	public JournalEntryTO fill_JournalEntry(GoodsissuesTO parameters)
			throws Exception {
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		boolean ind = false;
		Double total = zero;
		List list = parameters.getGoodIssuesDetail();
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();
		int n = 1;
		Double sum = zero;
		Double sum1 = zero;
		String acc = null;
		AdminDAO admin = new AdminDAO();
		BranchTO branch = new BranchTO();
		// buscando la cuenta asignada de cuenta de existencias al almacen

		branch = admin.getBranchByKey(parameters.getFromwhscode());
		// recorre la lista de detalles
		for (Object obj : list) {
			GoodsIssuesDetailTO good = (GoodsIssuesDetailTO) obj;
			String cod = good.getAcctcode();
			List lisHija = new Vector();

			// comparando lista aux de nodos visitados
			for (Object obj2 : aux) {
				GoodsIssuesDetailTO good2 = (GoodsIssuesDetailTO) obj2;
				if (cod.equals(good2.getAcctcode())) {
					ind = true;
				}
			}
			// compara el codigo de cuenta para hacer una sumatoria y guardarlo
			// en otra lista
			if (ind == false) {
				for (Object obj3 : list) {
					GoodsIssuesDetailTO good3 = (GoodsIssuesDetailTO) obj3;
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

			for (Object obj2 : listaDet) {
				GoodsIssuesDetailTO newGood = (GoodsIssuesDetailTO) obj2;
				sum = sum + (newGood.getQuantity() * newGood.getPrice());
				acc = newGood.getAcctcode();
			}
			sum1 = sum1 + sum;
			// asiento contable

			JournalEntryLinesTO art1 = new JournalEntryLinesTO();
			JournalEntryLinesTO art2 = new JournalEntryLinesTO();
			// --------------------------------------------------------------------------------------------------------------------------------------------------------
			// llenado del asiento contable
			// --------------------------------------------------------------------------------------------------------------------------------------------------------
			// // nuevo.setBatchnum(1);
			// LLenado del padre

			// llenado de los hijos

			art2.setLine_id(n);
			art2.setAccount(acc);

			art2.setDebit(sum);
			art2.setDuedate(parameters.getDocduedate());
			art2.setShortname(acc);
			art2.setContraact(branch.getBalinvntac());
			art2.setLinememo("salida de inventario ");
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
			n++;

		}

		JournalEntryLinesTO art1 = new JournalEntryLinesTO();

		art1.setLine_id(n);
		// buscar la cuenta asignada al almacen

		art1.setAccount(branch.getBalinvntac());

		if (branch.getBalinvntac() == null) {
			throw new Exception(
					"No hay una cuenta de Inventario asignada al almacen");
		}

		art1.setCredit(sum1);
		art1.setDuedate(parameters.getDocduedate());
		art1.setShortname(branch.getBalinvntac());
		art1.setContraact(acc);
		art1.setLinememo("salida de inventario");
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
		art1.setBalduecred(sum1);
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

		// LLenado del padre

		nuevo.setBtfstatus("O");
		nuevo.setTranstype(parameters.getObjtype());
		nuevo.setBaseref(Integer.toString(parameters.getDocnum()));
		nuevo.setRefdate(parameters.getDocdate());
		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setRef1(Integer.toString(parameters.getDocnum()));
		nuevo.setRef2(parameters.getRef1());
		nuevo.setLoctotal(sum1);
		nuevo.setSystotal(sum1);
		nuevo.setTransrate(0.0);
		nuevo.setDuedate(parameters.getDocduedate());
		nuevo.setTaxdate(parameters.getDocdate());
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

		// #endregion
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------
	// #region Consultas Entradas inventario
	// --------------------------------------------------------------------------------------------------------------------------------------------------
	public List getGoodsissues(GoodsissuesInTO param) throws EJBException {
		// TODO Auto-generated method stub GoodsissuesDAO
		List _return = new Vector();
		GoodsIssuesDAO DAO = new GoodsIssuesDAO();
		try {
			_return = DAO.getGoodsissues(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public List getGoodsIssuesDetail(int docentry) throws EJBException {
		// TODO Auto-generated method stub GoodsissuesDAO
		List _return = new Vector();
		GoodsissuesDetailDAO DAO = new GoodsissuesDetailDAO();
		try {
			_return = DAO.getGoodsIssuesDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public GoodsissuesTO getGoodsissuesByKey(int docentry) throws EJBException {
		// TODO Auto-generated method stub
		GoodsissuesTO _return = null;
		GoodsIssuesDAO GoodDAO = new GoodsIssuesDAO();
		try {
			_return = GoodDAO.getGoodsissuesByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------
	// #endregion
	// ---------------------------------------------------------------------------------------------------------------------------------------------------
	// #region Transferencias

	public ResultOutTO inv_transfers_mtto(TransfersTO parameters, int action)
			throws EJBException {

		// Declaración de variables

		ResultOutTO _valid = new ResultOutTO();
		ResultOutTO _return = new ResultOutTO();
		GoodsReceiptDAO DAO = new GoodsReceiptDAO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Validar acción a realizar
		// --------------------------------------------------------------------------------------------------------------------------------

		if (action != Common.MTTOINSERT) {
			_return = inv_transfers_update(parameters, action);
			return _return;
		}

		// --------------------------------------------------------------------------------------------------------------------------------
		// Asignación de valores por defecto y llenado:
		// Estas se realizan solo para cuando es guardar, el actualizar y borrar
		// no aplican.
		// --------------------------------------------------------------------------------------------------------------------------------
		parameters = filltransfers(parameters);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Hacer validaciones:
		// Estas se realizan solo para cuando es guardar, el actualizar y borrar
		// no aplican para validaciones
		// --------------------------------------------------------------------------------------------------------------------------------

		_valid = valida_inv_transfers_mtto(parameters);

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
			_return = save_Transactiontransfers(parameters, action,
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

	private TransfersTO filltransfers(TransfersTO parameters) {

		// variables
		Double total = zero;
		ArticlesTO DBArticle = new ArticlesTO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Valores por defecto detalle
		// --------------------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings("unchecked")
		Iterator<TransfersDetailTO> iterator = parameters.getTransfersDetail()
				.iterator();
		while (iterator.hasNext()) {
			TransfersDetailTO articleDetalle = (TransfersDetailTO) iterator
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
		parameters.setJrnlmemo("Salida de de Mercancia");
		parameters.setConfirmed("Y");
		parameters.setDocduedate(parameters.getDocdate());

		return parameters;
	}

	public ResultOutTO save_Transactiontransfers(TransfersTO tranfers,
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
		TransfersDAO DAO = new TransfersDAO(conn);
		transactionEJB trans = new transactionEJB();
		JournalEntryTO journal = new JournalEntryTO();

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar Encabezados y detalles de Entrada
		// --------------------------------------------------------------------------------------------------------------------------------

		_return = inv_transfers_save(tranfers, action, conn);
		tranfers.setDocentry(_return.getDocentry());
		tranfers.setDocnum(_return.getDocentry());

		// --------------------------------------------------------------------------------------------------------------------------------
		// Llenar objeto tipo transacción
		// --------------------------------------------------------------------------------------------------------------------------------

		transactions = fill_transaction(tranfers);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Calculo de existencias y costos, actualizacion de inventarios y listas de precios 
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
		// registro del asiento contable y actualización de saldos
		// -----------------------------------------------------------------------------------

		journal = fill_JournalEntry(tranfers);

		AccountingEJB account = new AccountingEJB();
		res_jour = account.journalEntry_mtto(journal, Common.MTTOINSERT, conn);

		// -----------------------------------------------------------------------------------
		// Actualización de documento con datos de Asiento contable
		// -----------------------------------------------------------------------------------
		tranfers.setTransid(res_jour.getDocentry());
		_return = inv_transfers_update(tranfers, Common.MTTOUPDATE, conn);

		return _return;
	}

	private List fill_transaction(TransfersTO document) {
		List _return = new Vector();

		Iterator<TransfersDetailTO> iterator = document.getTransfersDetail()
				.iterator();
		while (iterator.hasNext()) {
			TransfersDetailTO detail = (TransfersDetailTO) iterator.next();

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
			transaction.setWhscode(document.getTowhscode());
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
			transaction2.setRef1("");
			transaction2.setRef2(document.getRef1());
			transaction2.setLinenum(detail.getLinenum());
			transaction2.setItemcode(detail.getItemcode());
			transaction2.setDscription(detail.getDscription());
			transaction2.setQuantity(detail.getQuantity());
			transaction2.setPrice(detail.getPrice());
			transaction2.setLinetotal(detail.getLinetotal());
			transaction2.setWhscode(detail.getWhscode());
			transaction2.setAcctcode(detail.getAcctcode());
			transaction2.setOcrcode("");
			transaction2.setVatgroup("");
			transaction2.setPriceafvat(zero);
			transaction2.setVatsum(zero);
			transaction2.setObjtype(detail.getObjtype());
			transaction2.setGrssprofit(zero);
			transaction2.setTaxcode("");
			transaction2.setVatappld(zero);
			transaction2.setStockprice(detail.getPrice());
			transaction2.setGtotal(zero);
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

	public ResultOutTO inv_transfers_update(TransfersTO parameters, int action)
			throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		GoodsIssuesDAO DAO = new GoodsIssuesDAO();
		try {
			_return = inv_transfers_update(parameters, action, DAO.getConn());
			DAO.forceCommit();
		} catch (Exception e) {
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {
			DAO.forceCloseConnection();
		}
		return _return;

	}

	public ResultOutTO inv_transfers_update(TransfersTO parameters, int action,
			Connection conn) throws Exception {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		TransfersDAO DAO = new TransfersDAO(conn);
		DAO.setIstransaccional(true);
		TransfersDetailDAO goodDAO1 = new TransfersDetailDAO(conn);
		goodDAO1.setIstransaccional(true);

		// Actualizar/borrar encabezados
		_return.setDocentry(DAO.inv_transfers_mtto(parameters, action));

		// Borrar detalles
		Iterator<TransfersDetailTO> iterator = parameters.getTransfersDetail()
				.iterator();
		if (action == Common.MTTODELETE) {
			while (iterator.hasNext()) {
				TransfersDetailTO detalleReceipt = (TransfersDetailTO) iterator
						.next();

				goodDAO1.inv_transfersDetail_mtto(detalleReceipt,
						Common.MTTODELETE);
			}
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos Actualizados con exito");
		return _return;
	}

	public ResultOutTO inv_transfers_save(TransfersTO parameters, int action,
			Connection conn) throws Exception {
		// Variables
		ResultOutTO _return = new ResultOutTO();
		TransfersDAO DAO = new TransfersDAO(conn);
		DAO.setIstransaccional(true);
		TransfersDetailDAO goodDAO1 = new TransfersDetailDAO(conn);
		goodDAO1.setIstransaccional(true);

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar encabezados
		// --------------------------------------------------------------------------------------------------------------------------------

		_return.setDocentry(DAO.inv_transfers_mtto(parameters, action));

		// --------------------------------------------------------------------------------------------------------------------------------
		// Guardar detalles
		// --------------------------------------------------------------------------------------------------------------------------------

		Iterator<TransfersDetailTO> iterator = parameters.getTransfersDetail()
				.iterator();
		while (iterator.hasNext()) {
			TransfersDetailTO detalletransfers = (TransfersDetailTO) iterator
					.next();

			detalletransfers.setDocentry(_return.getDocentry());
			goodDAO1.inv_transfersDetail_mtto(detalletransfers,
					Common.MTTOINSERT);
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public JournalEntryTO fill_JournalEntry(TransfersTO parameters)
			throws Exception {
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		boolean ind = false;
		Double total = zero;
		List list = parameters.getTransfersDetail();
		List detail= new Vector();
	
		// asiento contable

			JournalEntryLinesTO art1 = new JournalEntryLinesTO();
			JournalEntryLinesTO art2 = new JournalEntryLinesTO();
			// --------------------------------------------------------------------------------------------------------------------------------------------------------
			// llenado del asiento contable
			// --------------------------------------------------------------------------------------------------------------------------------------------------------
			// // nuevo.setBatchnum(1);
			// LLenado del padre
			nuevo.setBtfstatus("O");
			nuevo.setTranstype(parameters.getObjtype());
			nuevo.setBaseref(Integer.toString(parameters.getDocnum()));
			nuevo.setRefdate(parameters.getDocdate());
			nuevo.setMemo(parameters.getJrnlmemo());
			nuevo.setRef1(Integer.toString(parameters.getDocnum()));
			nuevo.setRef2(parameters.getRef1());
			nuevo.setLoctotal(parameters.getDoctotal());
			nuevo.setSystotal(parameters.getDoctotal());
			nuevo.setTransrate(0.0);
			nuevo.setDuedate(parameters.getDocduedate());
			nuevo.setTaxdate(parameters.getDocdate());
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
			// nuevo.setRef2(ref2);

			// llenado de los hijos
			art1.setLine_id(1);
			// buscar la cuenta asignada al almacen origen 
			AdminDAO admin = new AdminDAO();
			BranchTO branch = new BranchTO();
			// buscando la cuenta asignada de cuenta de existencias al almacen
			branch = admin.getBranchByKey(parameters.getFromwhscode());
			art1.setAccount(branch.getBalinvntac());
          if (branch.getBalinvntac() == null) {
				throw new Exception(
						"No hay una cuenta de Inventario asignada al almacen");
			}
		
			
			
			// buscando la cuenta asignada de cuenta de existencias al almacen destino
		    admin = new AdminDAO();
			BranchTO branch1 = new BranchTO();
			branch1 = admin.getBranchByKey(parameters.getTowhscode());
			if (branch1.getBalinvntac() == null) {
				throw new Exception(
						"No hay una cuenta de Inventario asignada al almacen");
			}
			
			

			art1.setCredit(parameters.getDoctotal());
			art1.setDuedate(parameters.getDocduedate());
			art1.setShortname(branch.getBalinvntac());
			art1.setContraact(branch1.getBalinvntac());
			art1.setLinememo("entrada de mercancias");
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
			art1.setBalduecred(parameters.getDoctotal());
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

			
			art2.setLine_id(2);
			
			art2.setAccount(branch1.getBalinvntac());

			art2.setDebit(parameters.getDoctotal());
			art2.setDuedate(parameters.getDocduedate());
			art2.setShortname(branch1.getBalinvntac());
			art2.setContraact(branch.getBalinvntac());
			art2.setLinememo("entrada de mercancias");
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
			art2.setBalduedeb(parameters.getDoctotal());
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

		// #endregion
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------
	public List getTransfers(TransfersInTO param) throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		TransfersDAO DAO = new TransfersDAO();
		try {
			_return = DAO.getTransfers(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public TransfersTO getTransfersByKey(int docentry) throws EJBException {
		// TODO Auto-generated method stub
		TransfersTO _return = null;
		TransfersDAO TraDAO = new TransfersDAO();
		try {
			_return = TraDAO.getTransfersByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public List getTransfersDetail(int docentry) throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		TransfersDetailDAO DAO = new TransfersDetailDAO();
		try {
			_return = DAO.getTransfersDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public ResultOutTO valida_inv_transfers_mtto(TransfersTO parameters)
			throws EJBException {
		System.out.println("llego al valid_goodsissues_mtto ");
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
		if (parameters.getTowhscode() == null) {
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

		Iterator<TransfersDetailTO> iterator1 = parameters.getTransfersDetail()
				.iterator();

		// recorre el ClientCrediDetail
		while (iterator1.hasNext()) {
			AdminEJB EJB = new AdminEJB();
			// Consultar información actualizada desde la base
			TransfersDetailTO TransfersDetail = (TransfersDetailTO) iterator1
					.next();
			code = TransfersDetail.getItemcode();

			DBArticle = EJB.getArticlesByKey(code);

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo existe
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle != null) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(TransfersDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ TransfersDetail.getItemcode() + " "
						+ TransfersDetail.getDscription()

						+ " no existe,informar al administrador. linea :"
						+ TransfersDetail.getLinenum());
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
				_return.setLinenum(TransfersDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ TransfersDetail.getItemcode() + " "
						+ TransfersDetail.getDscription()

						+ " No esta activo. linea :"
						+ TransfersDetail.getLinenum());
				System.out.println(valid);
				return _return;

			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación articulo de compra
			// ------------------------------------------------------------------------------------------------------------
			valid = false;
			if (DBArticle.getInvntItem() != null
					&& DBArticle.getInvntItem().toUpperCase().equals("Y")) {
				valid = true;
			}

			if (!valid) {
				_return.setLinenum(TransfersDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ TransfersDetail.getItemcode() + " "
						+ TransfersDetail.getDscription()
						+ " No es un articulo de venta. linea :"
						+ TransfersDetail.getLinenum());
				return _return;
			}

			// ------------------------------------------------------------------------------------------------------------
			// Validación almacen bloqueado para articulo
			// ------------------------------------------------------------------------------------------------------------
			valid = false;

			branch = DBArticle.getBranchArticles();

			for (Object object : branch) {
				BranchArticlesTO branch1 = (BranchArticlesTO) object;

				if (branch1.getWhscode().equals(TransfersDetail.getWhscode())) {
					if (branch1.isIsasociated() && branch1.getLocked() != null
							&& branch1.getLocked().toUpperCase().equals("F")) {
						valid = true;
					}
				}
			}

			if (!valid) {
				_return.setLinenum(TransfersDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ TransfersDetail.getItemcode()
						+ " "
						+ TransfersDetail.getDscription()
						+ " No esta asignado o esta bloquedo para el almacen indicado. linea :"
						+ TransfersDetail.getLinenum());
				return _return;
			}
			// ------------------------------------------------------------------------------------------------------------
			// Validaciones de Existencia
			// ------------------------------------------------------------------------------------------------------------
			valid = false;

			Double stocks = 0.000;
			Iterator<TransfersDetailTO> iterator2 = parameters
					.getTransfersDetail().iterator();
			// recorre de nuevo el detalle comparando el primer elemento con los
			// demas
			while (iterator2.hasNext()) {
				TransfersDetailTO articleDetalle2 = (TransfersDetailTO) iterator2
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
					System.out.println("sumatoria" + stocks + "base"
							+ branch1.getOnhand());
					if (stocks <= branch1.getOnhand()) {
						valid = true;
					}
				}

			}
			if (!valid) {
				_return.setLinenum(TransfersDetail.getLinenum());
				_return.setCodigoError(1);
				_return.setMensaje("El articulo "
						+ TransfersDetail.getItemcode() + " "
						+ TransfersDetail.getDscription()
						+ " Recae en un Inventario Negativo. linea :"
						+ TransfersDetail.getLinenum());
				return _return;
			}

		}
		_return.setCodigoError(0);

		return _return;
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------
	//
	// ---------------------------------------------------------------------------------------------------------------------------------------------------
	// #endregion

	// #region Transacciones

	public JournalEntryTO fill_JournalEntry(GoodsreceiptTO parameters)
			throws Exception {
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		boolean ind = false;
		Double total = zero;
		String acc = null;
		int n=1;
		List list = parameters.getGoodReceiptDetail();
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();
		AdminDAO admin = new AdminDAO();
		BranchTO branch = new BranchTO();
		// buscando la cuenta asignada de cuenta de existencias al almacen

		branch = admin.getBranchByKey(parameters.getTowhscode());

		// recorre la lista de detalles
		for (Object obj : list) {
			GoodsReceiptDetailTO good = (GoodsReceiptDetailTO) obj;
			String cod = good.getAcctcode();
			List lisHija = new Vector();

			// comparando lista aux de nodos visitados
			for (Object obj2 : aux) {
				GoodsReceiptDetailTO good2 = (GoodsReceiptDetailTO) obj2;
				if (cod.equals(good2.getAcctcode())) {
					ind = true;
				}
			}
			// compara el codigo de cuenta para hacer una sumatoria y guardarlo
			// en otra lista
			if (ind == false) {
				for (Object obj3 : list) {
					GoodsReceiptDetailTO good3 = (GoodsReceiptDetailTO) obj3;
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
			Double sum = zero;

			for (Object obj2 : listaDet) {
				GoodsReceiptDetailTO newGood = (GoodsReceiptDetailTO) obj2;
				sum = sum + (newGood.getQuantity() * newGood.getPrice());
				acc = newGood.getAcctcode();
			}
			// suma total de todas las cuentas
			total = total + sum;
			// asiento contable

			JournalEntryLinesTO art2 = new JournalEntryLinesTO();
			// --------------------------------------------------------------------------------------------------------------------------------------------------------
			// llenado del asiento contable

			// llenado de los hijos
			

			art2.setLine_id(n);
			art2.setAccount(acc);
			art2.setCredit(sum);
			art2.setDuedate(parameters.getDocduedate());
			art2.setShortname(acc);
			art2.setContraact(branch.getBalinvntac());
			art2.setLinememo("entrada de mercancias");
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
			art2.setBalduedeb(0.0);
			art2.setBalduecred(sum);
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
			n++;

		}
		
		JournalEntryLinesTO art1 = new JournalEntryLinesTO();

		art1.setLine_id(n);
		// buscar la cuenta asignada al almacen
		
		art1.setAccount(branch.getBalinvntac());

		if (branch.getBalinvntac() == null) {
			throw new Exception(
					"No hay una cuenta de Inventario asignada al almacen");
		}

		art1.setDebit(total);
		art1.setDuedate(parameters.getDocduedate());
		art1.setShortname(branch.getBalinvntac());
		art1.setContraact(acc);
		art1.setLinememo("entrada de mercancias");
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
		art1.setBalduedeb(total);
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
		
		
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// LLenado del padre

		nuevo.setBtfstatus("O");
		nuevo.setTranstype(parameters.getObjtype());
		nuevo.setBaseref(Integer.toString(parameters.getDocnum()));
		nuevo.setRefdate(parameters.getDocdate());
		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setRef1(Integer.toString(parameters.getDocnum()));
		nuevo.setRef2(parameters.getRef1());
		nuevo.setLoctotal(total);
		nuevo.setSystotal(total);
		nuevo.setTransrate(0.0);
		nuevo.setDuedate(parameters.getDocduedate());
		nuevo.setTaxdate(parameters.getDocdate());
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

		// #endregion
	}

}
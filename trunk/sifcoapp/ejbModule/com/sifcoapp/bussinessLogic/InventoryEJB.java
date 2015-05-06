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
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.WarehouseJournalDetailTO;
import com.sifcoapp.objects.admin.to.WarehouseJournalTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.*;
import com.sifcoapp.objects.inventory.to.*;
import com.sifcoapp.objects.purchase.to.PurchaseDetailTO;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.exceptionMappingType;

/**
 * Session Bean implementation class InventoryEJB
 */
@Stateless
public class InventoryEJB implements InventoryEJBRemote {
	Double zero = 0.00;

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

	public ResultOutTO inv_goodsissues_mtto(GoodsissuesTO parameters, int action)
			throws EJBException {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();
		// --------------------------------------------------------------------------------------------------------------------------------
		// Set el codigo de almacen del padre al detalle
		// --------------------------------------------------------------------------------------------------------------------------------
		Iterator<GoodsIssuesDetailTO> iterator3 = parameters
				.getGoodIssuesDetail().iterator();
		while (iterator3.hasNext()) {

			GoodsIssuesDetailTO articleDetalle = (GoodsIssuesDetailTO) iterator3
					.next();
			articleDetalle.setWhscode(parameters.getFromwhscode());
		}
		_return = valid_goodsissues_mtto(parameters);
		System.out.println(_return.getCodigoError());
		if (_return.getCodigoError() != 0) {
			return _return;
		}
		Double total = 0.0;
		GoodsIssuesDAO DAO = new GoodsIssuesDAO();
		DAO.setIstransaccional(true);
		GoodsissuesDetailDAO goodDAO1 = new GoodsissuesDetailDAO(DAO.getConn());
		goodDAO1.setIstransaccional(true);
		try {
			Iterator<GoodsIssuesDetailTO> iterator2 = parameters
					.getGoodIssuesDetail().iterator();
			while (iterator2.hasNext()) {
				GoodsIssuesDetailTO articleDetalle = (GoodsIssuesDetailTO) iterator2
						.next();
				articleDetalle.setLinetotal(articleDetalle.getQuantity()
						* articleDetalle.getPrice());
				articleDetalle.setOpenqty(articleDetalle.getQuantity());
				total = total + articleDetalle.getLinetotal();
			}
			parameters.setDoctotal(total);
			_return.setDocentry(DAO.inv_goodsissues_mtto(parameters, action));
			Iterator<GoodsIssuesDetailTO> iterator = parameters
					.getGoodIssuesDetail().iterator();
			while (iterator.hasNext()) {
				GoodsIssuesDetailTO articleDetalle = (GoodsIssuesDetailTO) iterator
						.next();
				// Para articulos nuevos
				System.out.println("" + _return + "");
				articleDetalle.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					goodDAO1.inv_goodsIssuesDetail_mtto(articleDetalle,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					goodDAO1.inv_goodsIssuesDetail_mtto(articleDetalle,
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
			goodDAO1.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;

	}

	public ResultOutTO inv_GoodsReceipt_mtto(GoodsreceiptTO parameters,
			int action) throws EJBException {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();
		// --------------------------------------------------------------------------------------------------------------------------------
		// Set el codigo de almacen del padre al detalle
		// --------------------------------------------------------------------------------------------------------------------------------
		Iterator<GoodsReceiptDetailTO> iterator2 = parameters
				.getGoodReceiptDetail().iterator();
		while (iterator2.hasNext()) {

			GoodsReceiptDetailTO articleDetalle = (GoodsReceiptDetailTO) iterator2
					.next();
			articleDetalle.setWhscode(parameters.getTowhscode());
		}
		_return = valid_goodsReceipt_mtto(parameters, action);
		System.out.println(_return.getCodigoError());
		if (_return.getCodigoError() != 0) {
			return _return;
		}

		Double total = zero;
		GoodsReceiptDAO DAO = new GoodsReceiptDAO();
		DAO.setIstransaccional(true);
		
		try {
			_return = save_GoodsReceipt(parameters, action, DAO);
			DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

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

	public ResultOutTO inv_transfers_mtto(TransfersTO parameters, int action)
			throws EJBException {
		// TODO Auto-generated method stub
		double total = 0.0;
		ResultOutTO _return = new ResultOutTO();
		_return = valida_inv_transfers_mtto(parameters);
		System.out.println(_return.getCodigoError());
		if (_return.getCodigoError() != 0) {
			return _return;
		}
		TransfersDAO Trans = new TransfersDAO();
		Trans.setIstransaccional(true);
		TransfersDetailDAO TransDAO = new TransfersDetailDAO(Trans.getConn());
		TransDAO.setIstransaccional(true);
		try {
			Iterator<TransfersDetailTO> iterator2 = parameters
					.getTransfersDetail().iterator();
			while (iterator2.hasNext()) {
				TransfersDetailTO articleDetalle = (TransfersDetailTO) iterator2
						.next();
				articleDetalle.setLinetotal(articleDetalle.getQuantity()
						* articleDetalle.getPrice());
				articleDetalle.setOpenqty(articleDetalle.getQuantity());
				total = total + articleDetalle.getLinetotal();
			}
			parameters.setDoctotal(total);
			_return.setDocentry(Trans.inv_transfers_mtto(parameters, action));

			Iterator<TransfersDetailTO> iterator = parameters
					.getTransfersDetail().iterator();

			while (iterator.hasNext()) {
				TransfersDetailTO articleDetalle = (TransfersDetailTO) iterator
						.next();
				// Para articulos nuevos

				articleDetalle.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					TransDAO.inv_transfersDetail_mtto(articleDetalle,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					TransDAO.inv_transfersDetail_mtto(articleDetalle,
							Common.MTTODELETE);
				}
			}
			Trans.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Trans.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			Trans.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
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

	public ResultOutTO adm_inventorylog_mtto(InventoryLogTO parameters,
			int accion) throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		InventoryLogDAO DAO = new InventoryLogDAO();
		DAO.setIstransaccional(true);
		if (parameters.getDoctotal() == null) {
			parameters.setDoctotal(zero);
		}
		if (parameters.getEffectqty() == null) {
			parameters.setEffectqty(zero);
		}
		if (parameters.getExpenseslc() == null) {
			parameters.setExpenseslc(zero);
		}
		if (parameters.getPrice() == null) {
			parameters.setPrice(zero);
		}
		if (parameters.getPricerate() == null) {
			parameters.setPricerate(zero);
		}
		if (parameters.getQuantity() == null) {
			parameters.setQuantity(zero);
		}
		if (parameters.getTotallc() == null) {
			parameters.setTotallc(zero);
		}

		try {
			if (accion == Common.MTTOINSERT) {
				_return.setDocentry(DAO.adm_inventorylog_mtto(parameters,
						accion));
				DAO.forceCommit();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public InventoryLogTO getInventoryLogByKey(int messageid)
			throws EJBException {
		InventoryLogTO _return = new InventoryLogTO();
		InventoryLogDAO DAO = new InventoryLogDAO();
		try {
			_return = DAO.getInventoryLogByKey(messageid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}
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
				System.out.println(branch1.getWhscode());
				System.out.println(GoodsIssuesDetail.getWhscode());
				if (branch1.getWhscode().equals(GoodsIssuesDetail.getWhscode())) {
					if (branch1.getWhscode() != null
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

		}
		_return.setCodigoError(0);

		return _return;

	}

	public ResultOutTO valid_goodsReceipt_mtto(GoodsreceiptTO parameters,
			int action) throws EJBException {
		System.out.println("llego al valid_goodsReceipt_mtto ");
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
			_return.setMensaje("No se encuentra fecha del documento");

			return _return;
		}
		_return = acc.validate_exist_accperiod(parameters.getDocdate());
		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("El documento tiene una fecha Fuera del periodo contable activo");
			return _return;
		}

		Iterator<GoodsReceiptDetailTO> iterator1 = parameters
				.getGoodReceiptDetail().iterator();

		// recorre el ClientCrediDetail
		while (iterator1.hasNext()) {
			AdminEJB EJB = new AdminEJB();
			// Consultar información actualizada desde la base
			GoodsReceiptDetailTO GoodsReceiptDetail = (GoodsReceiptDetailTO) iterator1
					.next();
			code = GoodsReceiptDetail.getItemcode();

			DBArticle = EJB.getArticlesByKey(code);

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
			// Validación articulo de compra
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
				System.out.println(branch1.getWhscode());
				System.out.println(GoodsReceiptDetail.getWhscode());

				if (branch1.getWhscode()
						.equals(GoodsReceiptDetail.getWhscode())) {
					if (branch1.getWhscode() != null
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
				System.out.println(branch1.getWhscode());
				System.out.println(TransfersDetail.getWhscode());
				if (branch1.getWhscode().equals(TransfersDetail.getWhscode())) {
					if (branch1.getWhscode() != null
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

		}
		_return.setCodigoError(0);

		return _return;
	}

	public ResultOutTO save_GoodsReceipt(GoodsreceiptTO parameters, int action,
			GoodsReceiptDAO DAO) throws EJBException {

		ResultOutTO _return = new ResultOutTO();
		ResultOutTO _return1 = new ResultOutTO();
		Double total = zero;
		GoodReceiptDetailDAO goodDAO1 = new GoodReceiptDetailDAO(DAO.getConn());
		goodDAO1.setIstransaccional(true);

		@SuppressWarnings("unchecked")
		Iterator<GoodsReceiptDetailTO> iterator2 = parameters
				.getGoodReceiptDetail().iterator();
		while (iterator2.hasNext()) {

			GoodsReceiptDetailTO articleDetalle = (GoodsReceiptDetailTO) iterator2
					.next();
			articleDetalle.setLinetotal(articleDetalle.getQuantity()
					* articleDetalle.getPrice());
			articleDetalle.setOpenqty(articleDetalle.getQuantity());
			total = total + articleDetalle.getLinetotal();

		}
		parameters.setDoctotal(total);
		parameters.setCanceled("N");
		parameters.setDocstatus("O");
		parameters.setDoctype("I");
		parameters.setJrnlmemo("Entrada de Mercancia");
		parameters.setConfirmed("Y");

		try {
			_return.setDocentry(DAO.inv_GoodsReceipt_mtto(parameters, action));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<GoodsReceiptDetailTO> iterator = parameters
				.getGoodReceiptDetail().iterator();
		while (iterator.hasNext()) {
			GoodsReceiptDetailTO detalleReceipt = (GoodsReceiptDetailTO) iterator
					.next();
			// Para articulos nuevos

			detalleReceipt.setDocentry(_return.getDocentry());

			ArticlesInTO Article = new ArticlesInTO();
			Article.setOnHand(detalleReceipt.getQuantity());
			Article.setItemCode(detalleReceipt.getItemcode());
			Article.setSww(detalleReceipt.getWhscode());
			Article.setObjtype(detalleReceipt.getObjtype());

			// -----------------------------------------------------------------------------------
			//
			// -----------------------------------------------------------------------------------

			if (action == Common.MTTOINSERT) {
				try {
					goodDAO1.inv_goodReceiptDetail_mtto(detalleReceipt,
							Common.MTTOINSERT);
					// -----------------------------------------------------------------------------------
					//
					// -----------------------------------------------------------------------------------

					_return1 = save_Inventory_Log(parameters, detalleReceipt);
					_return1 = save_WarehouseJournal(parameters,
							detalleReceipt, DAO);

					// -----------------------------------------------------------------------------------
					// actualizacion de articulos
					// -----------------------------------------------------------------------------------
					AdminDAO DAO1 = new AdminDAO(DAO.getConn());
					DAO1.setIstransaccional(true);

					InventorylogInTo Inventorylog = new InventorylogInTo();
					
					Inventorylog = DAO1.Calcul_arti(Article);
					
					Inventorylog.setDoclinenum(_return.getDocentry());
					Inventorylog.setLayerId(detalleReceipt.getLinenum());
					
					_return1 = save_WarehouseJournallayer(Inventorylog, DAO);
					_return1 = DAO1.Update_inventory_articles(Article,
							Inventorylog);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (action == Common.MTTODELETE) {
				try {
					goodDAO1.inv_goodReceiptDetail_mtto(detalleReceipt,
							Common.MTTODELETE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		// -----------------------------------------------------------------------------------
		// registro del asiento contable y actualizacion de la entrada
		// -----------------------------------------------------------------------------------
		_return1 = complete_accounting_entry(parameters, DAO.getConn());
		GoodsreceiptTO good = new GoodsreceiptTO();

		good = getGoodsReceiptByKey(_return.getDocentry());
		good.setTransid(_return1.getDocentry());

		_return = inv_GoodsReceipt_mtto(good, 2);

		// -----------------------------------------------------------------------------------
		//
		// -----------------------------------------------------------------------------------

		return _return;
	}

	public ResultOutTO save_Inventory_Log(GoodsreceiptTO parameters,
			GoodsReceiptDetailTO articleDetalle) throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		InventoryLogTO inventory = new InventoryLogTO();
		// -----------------------------------------------------------------------------------------------------------------------------------------
		// llenado de inventory por cada linea de detalle por documento
		// -----------------------------------------------------------------------------------------------------------------------------------------

		inventory.setDocentry(articleDetalle.getDocentry());
		inventory.setDoclinenum(articleDetalle.getLinenum());
		inventory.setQuantity(articleDetalle.getQuantity());
		// inventory.setEffectqty(parameters);
		inventory.setLoctype(Integer.parseInt(articleDetalle.getObjtype()));
		inventory.setLoccode(articleDetalle.getWhscode());
		inventory.setTotallc(articleDetalle.getLinetotal());
		inventory.setBase_ref(articleDetalle.getBaseref());
		inventory.setBasetype(articleDetalle.getBasetype());
		inventory.setAccumtype(1);
		inventory.setActiontype(1);
		inventory.setExpenseslc(0.0);
		inventory.setDocduedate(parameters.getDocduedate());
		inventory.setItemcode(articleDetalle.getItemcode());
		// inventory.setBpcardcode(parameters.get);
		inventory.setDocdate(parameters.getDocdate());
		inventory.setComment(parameters.getComments());
		inventory.setJrnlmemo(parameters.getJrnlmemo());
		inventory.setRef1(parameters.getRef1());
		// inventory.setRef2(parameters.getR);
		inventory.setBaseline(articleDetalle.getLinenum());
		inventory.setSnbtype(4);
		// inventory.setOcrcode();
		// inventory.setOcrcode2();
		// inventory.setOcrcode3();
		// inventory.setCardname();
		inventory.setDscription(articleDetalle.getDscription());
		inventory.setPricerate(0.0);
		inventory.setDoctotal(parameters.getDoctotal());
		inventory.setPrice(articleDetalle.getPrice());
		inventory.setTaxdate(parameters.getDocdate());
		inventory.setUsersign(parameters.getUsersign());

		_return = adm_inventorylog_mtto(inventory, 1);

		if (_return.getCodigoError() != 0) {
			_return.setCodigoError(1);
			_return.setMensaje("No se puede almacenar Linea de Documento "
					+ articleDetalle.getLinenum());
			_return.setLinenum(articleDetalle.getLinenum());
			return _return;
		}

		return _return;
	}

	public ResultOutTO save_WarehouseJournal(GoodsreceiptTO parameters,
			GoodsReceiptDetailTO articleDetalle, GoodsReceiptDAO DAO)
			throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		WarehouseJournalTO WarehouseJournal = new WarehouseJournalTO();

		// ------------------------------------------------------------------------------------------------------------------------------
		// llenando WarehouseJournalTO
		// ------------------------------------------------------------------------------------------------------------------------------
		WarehouseJournal
				.setTranstype(Integer.parseInt(parameters.getObjtype()));
		WarehouseJournal.setCreatedby(articleDetalle.getDocentry());
		WarehouseJournal.setBase_ref(Integer.toString(parameters.getDocnum()));
		WarehouseJournal.setDoclinenum(articleDetalle.getLinenum());
		WarehouseJournal.setItemcode(articleDetalle.getItemcode());
		WarehouseJournal.setInqty(articleDetalle.getQuantity());
		// WarehouseJournal.setOutqty();
		WarehouseJournal.setPrice(articleDetalle.getPrice());
		WarehouseJournal.setSublinenum(-1);
		WarehouseJournal.setAppobjline(-1);
		WarehouseJournal.setExpenses(0.0);
		WarehouseJournal.setOpenexp(0.0);
		WarehouseJournal.setAllocation(0.0);
		WarehouseJournal.setOpenalloc(0.0);
		WarehouseJournal.setExpalloc(0.0);
		WarehouseJournal.setOexpalloc(0.0);
		WarehouseJournal.setOpenpdiff(0.0);
		WarehouseJournal.setNeginvadjs(0.0);
		WarehouseJournal.setOpenneginv(0.0);
		WarehouseJournal.setNegstckact(" ");
		WarehouseJournal.setBtransval(0.0);
		WarehouseJournal.setVarval(0.0);
		WarehouseJournal.setBexpval(0.0);
		WarehouseJournal.setCogsval(0.0);
		WarehouseJournal.setBnegaval(0.0);
		WarehouseJournal.setMessageid(articleDetalle.getDocentry());
		WarehouseJournal.setLoctype(-1);
		WarehouseJournal.setLoccode(articleDetalle.getWhscode());
		WarehouseJournal.setOutqty(0.0);
		WarehouseJournal.setOpenstock(0.0);
		WarehouseJournal.setPricediff(0.0);
		WarehouseJournal.setIoffincval(0.0);
		WarehouseJournal.setDoffdecval(0.0);
		WarehouseJournal.setDecval(0.0);
		WarehouseJournal.setWipval(0.0);
		WarehouseJournal.setWipvarval(0.0);
		WarehouseJournal.setIncval(0.0);
		WarehouseJournal.setSumstock(0.0);
		WarehouseJournal.setOpenqty(0.0);

		WarehouseJournal.setPaoffval(0.0);
		WarehouseJournal.setOpenpaoff(0.0);

		WarehouseJournal.setPaval(0.0);
		WarehouseJournal.setOpenpa(0.0);

		AdminDAO DAO1 = new AdminDAO(DAO.getConn());

		try {
			_return.setDocentry(DAO1.adm_warehousejournal_mtto(
					WarehouseJournal, 1));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (_return.getDocentry() == 0) {
			_return.setCodigoError(1);
			_return.setMensaje("No se puede almacenar Linea de Documento "
					+ articleDetalle.getLinenum());
			_return.setLinenum(articleDetalle.getLinenum());
			return _return;
		} else {
			_return.setCodigoError(0);
			_return.setMensaje("datos almacenados con exito");
		}

		return _return;
	}

	public ResultOutTO save_WarehouseJournallayer(InventorylogInTo parameters,
			GoodsReceiptDAO DAO) throws EJBException {

		ResultOutTO _return = new ResultOutTO();
		WarehouseJournalDetailTO WarehouseJournal = new WarehouseJournalDetailTO();
		WarehouseJournal.setTransseq(parameters.getDoclinenum());
		WarehouseJournal.setLayerid(0);
		WarehouseJournal.setCalcprice(parameters.getAvgPrice());
		WarehouseJournal.setBalance(parameters.getBalance());
		WarehouseJournal.setTransvalue(parameters.getQuantity());
		WarehouseJournal.setLayerinqty(parameters.getTotallc());
		WarehouseJournal.setLayeroutq(0.0);
		WarehouseJournal.setRevaltotal(0.0);
		AdminDAO DAO1 = new AdminDAO(DAO.getConn());

		try {
			_return.setDocentry(DAO1.adm_warehousejournalDetail_mtto(
					WarehouseJournal, 1));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (_return.getDocentry() == 0) {
			_return.setCodigoError(1);
			_return.setMensaje("No se puede almacenar Linea de Documento "
					+ parameters.getLayerId());
			_return.setLinenum(parameters.getLayerId());
			return _return;
		} else {
			_return.setCodigoError(0);
			_return.setMensaje("datos almacenados con exito");
		}

		return _return;
	}

	public ResultOutTO complete_accounting_entry(GoodsreceiptTO parameters,
			Connection DAO) throws EJBException {

		ResultOutTO _result = new ResultOutTO();
		Double total = zero;
		List list = parameters.getGoodReceiptDetail();
		List aux = new Vector();
		List<List> listas = new Vector();
		// recorre la lista de detalles
		for (Object obj : list) {
			GoodsReceiptDetailTO good = (GoodsReceiptDetailTO) obj;
			String cod = good.getAcctcode();
			List lisHija = new Vector();
			// compara el codigo de cuenta para hacer una saumatoria y guardarlo
			// en otra lista
			for (Object obj2 : list) {
				GoodsReceiptDetailTO good2 = (GoodsReceiptDetailTO) obj2;
				if (cod.equals(good2.getAcctcode())) {
					lisHija.add(obj2);
				}
			}
			// guarda en la lista de listas
			listas.add(lisHija);
			// recorre la lista de listas para remover de la lista original los
			// nodos visitados
			for (Object node : list) {
				GoodsReceiptDetailTO no = (GoodsReceiptDetailTO) node;
				if (no.getAcctcode().equals(cod)) {
					list.remove(no);
				}
			}
			lisHija.clear();
		}

		for (List obj : listas) {
			List listaDet = obj;
			Double sum = 0.0;
			String acc = null;
			for (Object obj2 : listaDet) {
				GoodsReceiptDetailTO newGood = (GoodsReceiptDetailTO) obj2;
				sum = sum + (newGood.getQuantity() * newGood.getPrice());
				acc = newGood.getAcctcode();
			}
			// asiento contable
			List detail = new Vector();
			JournalEntryTO nuevo = new JournalEntryTO();
			JournalEntryLinesTO art1 = new JournalEntryLinesTO();
			JournalEntryLinesTO art2 = new JournalEntryLinesTO();
			// --------------------------------------------------------------------------------------------------------------------------------------------------------
			// llenado del asiento contable
			// --------------------------------------------------------------------------------------------------------------------------------------------------------
			// // nuevo.setBatchnum(1);
			// LLenado del padre

			nuevo.setObjtype("5");
			nuevo.setMemo("entrada de mercancia");
			nuevo.setUsersign(parameters.getUsersign());
			nuevo.setLoctotal(sum);
			nuevo.setSystotal(sum);
			// llenado de los hijos
			art1.setLine_id(1);
			// buscar la cuenta asignada al almacen
			AdminDAO admin = new AdminDAO(DAO);
			BranchTO branch;
			// buscando la cuenta asignada de cuenta de existencias al almacen
			try {
				branch = admin.getBranchByKey(parameters.getTowhscode());
				art1.setAccount(branch.getBalinvntac());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			art1.setDebit(sum);
			detail.add(art1);
			art2.setLine_id(2);
			art2.setAccount(acc);
			art2.setCredit(sum);
			detail.add(art2);
			nuevo.setJournalentryList(detail);
			// llama al metodo dentro del ejeb para insertar el nuevo journal y
			// actualizar las cuentas
			AccountingEJB account = new AccountingEJB();
			_result = account.journalEntry_mtto(nuevo, 1, DAO);

		}
		return _result;
	}

}

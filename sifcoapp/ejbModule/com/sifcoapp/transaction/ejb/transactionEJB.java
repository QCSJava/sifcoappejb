package com.sifcoapp.transaction.ejb;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.admin.ejb.AdminEJB;
import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.InventoryLogDAO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
import com.sifcoapp.objects.transaction.dao.TransactionDAO;
import com.sifcoapp.objects.transaction.to.InventoryLogTO;
import com.sifcoapp.objects.transaction.to.InventorylogInTo;
import com.sifcoapp.objects.transaction.to.TransactionTo;
import com.sifcoapp.objects.transaction.to.WarehouseJournalLayerTO;
import com.sifcoapp.objects.transaction.to.WarehouseJournalTO;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

public class transactionEJB {
	Double zero = 0.0;

	public ResultOutTO save_Inventory_Log(InventoryLogTO inventory,
			Connection conn) throws Exception {

		ResultOutTO _return = new ResultOutTO();

		InventoryLogDAO inventDAO = new InventoryLogDAO(conn);
		inventDAO.setIstransaccional(true);

		_return.setDocentry(inventDAO.adm_inventorylog_mtto(inventory,
				Common.MTTOINSERT));

		if (_return.getDocentry() == 0) {
			_return.setCodigoError(1);
			_return.setMensaje("No se puede almacenar Linea de Documento ");
			throw new Exception("No se puede almacenar Linea de Documento ");
		} else {
			_return.setCodigoError(0);
			_return.setMensaje("datos almacenados");

		}
		return _return;
	}

	public ResultOutTO save_WarehouseJournal(
			WarehouseJournalTO warehousejournal, Connection conn)
			throws Exception {

		ResultOutTO _return = new ResultOutTO();

		TransactionDAO warehouseDAO = new TransactionDAO(conn);
		warehouseDAO.setIstransaccional(true);

		_return.setDocentry(warehouseDAO.adm_warehousejournal_mtto(
				warehousejournal, 1));

		if (_return.getDocentry() == 0) {
			_return.setCodigoError(1);
			_return.setMensaje("No se puede almacenar Linea de Documento ");

			return _return;
		} else {
			_return.setCodigoError(0);
			_return.setMensaje("datos almacenados");

		}
		return _return;
	}

	public ResultOutTO save_WarehouseJournalLayer(
			WarehouseJournalLayerTO warehousejournal, Connection conn)
			throws Exception {

		ResultOutTO _return = new ResultOutTO();

		TransactionDAO warehouseDAO = new TransactionDAO(conn);
		warehouseDAO.setIstransaccional(true);

		_return.setDocentry(warehouseDAO.adm_warehousejournalLayer_mtto(
				warehousejournal, Common.MTTOINSERT));

		if (_return.getDocentry() == 0) {
			_return.setCodigoError(1);
			_return.setMensaje("No se puede almacenar Linea de Documento ");

			return _return;
		} else {
			_return.setCodigoError(0);
			_return.setMensaje("datos almacenados");

		}
		return _return;
	}

	public InventoryLogTO fill_Inventory_Log(TransactionTo transaction)
			throws Exception {

		InventoryLogTO inventory = new InventoryLogTO();

		inventory.setDocentry(transaction.getDocentry());
		inventory.setTranstype(Integer.parseInt(transaction.getObjtype()));
		inventory.setDoclinenum(transaction.getLinenum());
		inventory.setQuantity(transaction.getQuantity());
		inventory.setEffectqty(transaction.getQuantity());
		inventory.setLoctype(0); // valor defecto
		inventory.setLoccode(transaction.getWhscode());
		inventory.setTotallc(transaction.getLinetotal());
		// inventory.setBaseabsent(null);
		inventory.setBasetype(-1);
		inventory.setAccumtype(1);
		inventory.setActiontype(1);
		inventory.setExpenseslc(zero);
		inventory.setDocduedate(transaction.getDocduedate());
		inventory.setItemcode(transaction.getItemcode());
		inventory.setBpcardcode(transaction.getAcctcode());
		inventory.setDocdate(transaction.getDocdate());
		inventory.setComment(transaction.getComment());
		inventory.setJrnlmemo(transaction.getJrnlmemo());
		inventory.setRef1(transaction.getRef1());
		inventory.setRef2(transaction.getRef2());
		inventory.setBaseline(-1);
		inventory.setSnbtype(-1);
		// inventory.setCreatetime();
		// inventory.setCreatetime();
		inventory.setOcrcode(transaction.getOcrcode());
		// inventory.setOcrcode2();
		// inventory.setOcrcode3();
		inventory.setCardname(transaction.getCardname());
		inventory.setDscription(transaction.getDscription());
		inventory.setBase_ref(transaction.getDocnum());
		inventory.setPricerate(zero);
		inventory.setDoctotal(transaction.getLinetotal());
		inventory.setPrice(transaction.getPrice());
		inventory.setTaxdate(transaction.getDocdate());
		inventory.setUsersign(transaction.getUsersign());

		return inventory;
	}

	public WarehouseJournalTO fill_WarehouseJournal(TransactionTo transaction)
			throws Exception {

		WarehouseJournalTO WarehouseJournal = new WarehouseJournalTO();

		// ------------------------------------------------------------------------------------------------------------------------------
		// llenando WarehouseJournalTO
		// ------------------------------------------------------------------------------------------------------------------------------

		WarehouseJournal
				.setTranstype(Integer.parseInt(transaction.getObjtype()));
		WarehouseJournal.setCreatedby(transaction.getDocentry());
		WarehouseJournal.setBase_ref(transaction.getDocnum());
		WarehouseJournal.setDoclinenum(transaction.getLinenum());
		WarehouseJournal.setItemcode(transaction.getItemcode());
		WarehouseJournal.setInqty(transaction.getInqty());
		WarehouseJournal.setOutqty(transaction.getOutqty());
		WarehouseJournal.setPrice(transaction.getPrice());
		// WarehouseJournal.setTrnsfract(trnsfract);
		// WarehouseJournal.setPricedifac(pricedifac);
		// WarehouseJournal.setVarianceac(varianceac);
		// WarehouseJournal.setReturnact(returnact);
		// WarehouseJournal.setClearact();
		// WarehouseJournal.setCostact(costact);
		// WarehouseJournal.setWipact(wipact);
		WarehouseJournal.setOpenstock(transaction.getLinetotal());
		WarehouseJournal.setPricediff(zero);
		WarehouseJournal.setTransseq(-1);
		WarehouseJournal.setInvntact(transaction.getInvntact());
		WarehouseJournal.setSublinenum(-1);
		WarehouseJournal.setAppobjline(-1);
		WarehouseJournal.setExpenses(zero);
		WarehouseJournal.setOpenexp(zero);
		WarehouseJournal.setAllocation(zero);
		WarehouseJournal.setOpenalloc(zero);
		WarehouseJournal.setExpalloc(zero);
		WarehouseJournal.setOexpalloc(zero);
		WarehouseJournal.setOpenpdiff(zero);
		WarehouseJournal.setNeginvadjs(zero);
		WarehouseJournal.setOpenneginv(zero);
		// WarehouseJournal.setNegstckact(negstckact);
		WarehouseJournal.setBtransval(zero);
		WarehouseJournal.setVarval(zero);
		WarehouseJournal.setBexpval(zero);
		WarehouseJournal.setCogsval(zero);
		WarehouseJournal.setBnegaval(zero);
		WarehouseJournal.setIoffincacc(transaction.getAcctcode());
		WarehouseJournal.setIoffincval(zero);
		// WarehouseJournal.setDoffdecacc(doffdecacc);
		WarehouseJournal.setDoffdecval(zero);
		// WarehouseJournal.setDecacc(decacc);
		WarehouseJournal.setDecval(zero);
		WarehouseJournal.setWipval(zero);
		// WarehouseJournal.setWipvaracc(wipvaracc);
		WarehouseJournal.setWipvarval(zero);
		// WarehouseJournal.setIncact(incact);
		WarehouseJournal.setIncval(zero);
		// WarehouseJournal.setExpcacc(expcacc);
		WarehouseJournal.setMessageid(transaction.getMessageid());
		WarehouseJournal.setLoctype(-1);
		WarehouseJournal.setLoccode(transaction.getWhscode());
		WarehouseJournal.setOutqty(transaction.getOutqty());
		WarehouseJournal.setPoststatus("N");
		WarehouseJournal.setSumstock(transaction.getLinetotal());
		WarehouseJournal.setOpenqty(transaction.getQuantity());
		// WarehouseJournal.setPaoffacc(paoffacc);
		WarehouseJournal.setPaoffval(zero);
		WarehouseJournal.setOpenpaoff(zero);
		// WarehouseJournal.setPaacc(paacc);
		WarehouseJournal.setPaval(zero);
		WarehouseJournal.setOpenpa(zero);

		return WarehouseJournal;
	}

	public WarehouseJournalLayerTO fill_WarehouseJournalLayer(
			TransactionTo transaction) {

		WarehouseJournalLayerTO WarehouseJournalLayer = new WarehouseJournalLayerTO();
		WarehouseJournalLayer.setTransseq(transaction.getTransseq());
		WarehouseJournalLayer.setLayerid(0);
		WarehouseJournalLayer.setCalcprice(transaction.getPrice());
		WarehouseJournalLayer.setBalance(transaction.getBalance());
		WarehouseJournalLayer.setTransvalue(transaction.getLinetotal());
		WarehouseJournalLayer.setLayerinqty(transaction.getInqty());
		WarehouseJournalLayer.setLayeroutq(transaction.getOutqty());
		WarehouseJournalLayer.setRevaltotal(zero);

		return WarehouseJournalLayer;
	}

	public TransactionTo calculate(TransactionTo transaction) throws Exception {
		// Variables
		List brachArticles = new Vector();
		ResultOutTO _return = new ResultOutTO();
		ArticlesTO DBArticle = new ArticlesTO();

		// Variables para los calculos
		double avgPrice = 0.0;
		double newAvgPrice = 0.0;
		double onhand = 0.0;
		String whsCode = "";
		double whsOnhand = 0.0;
		double newOnhand = 0.0;
		double newWhsOnhand = 0.0;
		double oldTotalArticle = 0.0;
		double newTotalArticle = 0.0;
		double totalWhsArticle = 0.0;
		double transQuantity = 0.0;
		double transValue = 0.0;

		// TODO: ver como quito esta parte ya tienen que venir los articulos
		// dentro del transaction
		DBArticle = transaction.getArticle();

		avgPrice = DBArticle.getAvgPrice();
		onhand = DBArticle.getOnHand();
		whsCode = transaction.getWhscode();
		transQuantity = transaction.getQuantity();
		transValue = transaction.getLinetotal();

		for (Object object : DBArticle.getBranchArticles()) {
			BranchArticlesTO branch = (BranchArticlesTO) object;

			if (branch.getWhscode().equals(whsCode)) {
				whsOnhand = branch.getOnhand();

			}
		}

		// ------------------------------------------------------------------------------------------------------------
		// Documentos Entrada de mercancias GoodReceipt ObjectType = 30
		// Documentos Compra de mercancias Purchase ObjectType = 20
		// ------------------------------------------------------------------------------------------------------------

		if (transaction.getObjtype().equals("30")||transaction.getObjtype().equals("20")) {
			// Existencias
			// --------------------------------------------------------------------------------------------------------
			newOnhand = onhand + transQuantity;
			newWhsOnhand = whsOnhand + transQuantity;
			// Actualizar objeto princicpal
			transaction.setNewOnhand(newOnhand);
			transaction.setNewWhsOnhand(newWhsOnhand);
			transaction.setInqty(transQuantity);
			transaction.setOutqty(zero);

			// Costos promedios
			// --------------------------------------------------------------------------------------------------------

			oldTotalArticle = onhand * avgPrice;
			newTotalArticle = oldTotalArticle + transValue;
			newAvgPrice = newTotalArticle / newOnhand;
			// Actualizar objeto princicpal
			transaction.setNewAvgprice(newAvgPrice);
			transaction.setBalance(newTotalArticle);

		}
		
		if (transaction.getObjtype().equals("10")) {
			// Existencias
			// --------------------------------------------------------------------------------------------------------
			newOnhand = onhand - transQuantity;
			newWhsOnhand = whsOnhand - transQuantity;
			// Actualizar objeto princicpal
			transaction.setNewOnhand(newOnhand);
			transaction.setNewWhsOnhand(newWhsOnhand);
			transaction.setInqty(zero);
			transaction.setOutqty(transQuantity);

			// Costos promedios
			// --------------------------------------------------------------------------------------------------------
/*
			oldTotalArticle = onhand * avgPrice;
			newTotalArticle = oldTotalArticle + transValue;
			newAvgPrice = newTotalArticle / newOnhand;*/
			// Actualizar objeto princicpal
			transaction.setNewAvgprice(avgPrice);
			transaction.setBalance(newOnhand);

		}
		
		return transaction;

	}

}

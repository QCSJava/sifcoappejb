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
	
	public InventoryLogTO fill_Inventory_Log(TransactionTo transaction)
			throws Exception {

		InventoryLogTO inventory = new InventoryLogTO();

		inventory.setDocentry(transaction.getCreatedby());
		inventory.setDoclinenum(transaction.getDoclinenum());
		inventory.setQuantity(transaction.getQuantity());
		inventory.setEffectqty(transaction.getQuantity());
		inventory.setLoctype(0); // valor defecto
		inventory.setLoccode(transaction.getLoccode());
		inventory.setTotallc(transaction.getOpenstock());
		inventory.setBase_ref(transaction.getBase_ref());
		inventory.setBasetype(-1);
		inventory.setAccumtype(1);
		inventory.setActiontype(1);
		inventory.setExpenseslc(zero);
		inventory.setDocduedate(transaction.getDocduedate());
		inventory.setItemcode(transaction.getItemcode());
		inventory.setBpcardcode(transaction.getIoffincacc());
		inventory.setDocdate(transaction.getDocdate());
		inventory.setComment(transaction.getComment());
		inventory.setJrnlmemo(transaction.getJrnlmemo());
		inventory.setRef1(transaction.getBase_ref());
		inventory.setRef2(transaction.getRef2());
		inventory.setBaseline(transaction.getDoclinenum());
		inventory.setSnbtype(-1);
		// inventory.setOcrcode();
		// inventory.setOcrcode2();
		// inventory.setOcrcode3();
		// inventory.setCardname(detalleReceipt);
		inventory.setDscription(transaction.getDscription());
		inventory.setBase_ref(transaction.getBase_ref());
		inventory.setPricerate(zero);
		inventory.setDoctotal(transaction.getOpenstock());
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

		WarehouseJournal.setTranstype(transaction.getTranstype());
		WarehouseJournal.setCreatedby(transaction.getCreatedby());
		WarehouseJournal.setBase_ref(transaction.getBase_ref());
		WarehouseJournal.setDoclinenum(transaction.getDoclinenum());
		WarehouseJournal.setItemcode(transaction.getItemcode());
		WarehouseJournal.setInqty(transaction.getInqty());
		WarehouseJournal.setOutqty(transaction.getOutqty());
		WarehouseJournal.setPrice(transaction.getPrice());
		WarehouseJournal.setSublinenum(transaction.getSublinenum());
		WarehouseJournal.setAppobjline(transaction.getAppobjline());
		WarehouseJournal.setExpenses(transaction.getExpenses());
		WarehouseJournal.setOpenexp(transaction.getOpenexp());
		WarehouseJournal.setAllocation(transaction.getAllocation());
		WarehouseJournal.setOpenalloc(transaction.getOpenalloc());
		WarehouseJournal.setExpalloc(transaction.getExpalloc());
		WarehouseJournal.setOexpalloc(transaction.getOexpalloc());
		WarehouseJournal.setOpenpdiff(transaction.getOpenpdiff());
		WarehouseJournal.setNeginvadjs(transaction.getNeginvadjs());
		WarehouseJournal.setOpenneginv(transaction.getOpenneginv());
		WarehouseJournal.setNegstckact(transaction.getNegstckact());
		WarehouseJournal.setBtransval(transaction.getBtransval());
		WarehouseJournal.setVarval(transaction.getVarval());
		WarehouseJournal.setBexpval(transaction.getBexpval());
		WarehouseJournal.setCogsval(transaction.getCogsval());
		WarehouseJournal.setBnegaval(transaction.getBnegaval());
		WarehouseJournal.setMessageid(transaction.getMessageid());
		WarehouseJournal.setLoctype(transaction.getLoctype());
		WarehouseJournal.setLoccode(transaction.getLoccode());
		WarehouseJournal.setOutqty(transaction.getOutqty());
		WarehouseJournal.setOpenstock(transaction.getOpenstock());
		WarehouseJournal.setPricediff(transaction.getPricediff());
		WarehouseJournal.setIoffincval(transaction.getIoffincval());
		WarehouseJournal.setDoffdecval(transaction.getDoffdecval());
		WarehouseJournal.setDecval(transaction.getDecval());
		WarehouseJournal.setWipval(transaction.getWipval());
		WarehouseJournal.setWipvarval(transaction.getWipvarval());
		WarehouseJournal.setIncval(transaction.getIncval());
		WarehouseJournal.setSumstock(transaction.getSumstock());
		WarehouseJournal.setOpenqty(transaction.getOpenqty());
		WarehouseJournal.setPaoffval(transaction.getPaoffval());
		WarehouseJournal.setOpenpaoff(transaction.getOpenpaoff());
		WarehouseJournal.setPaval(transaction.getPaval());
		WarehouseJournal.setOpenpa(transaction.getOpenpa());

		return WarehouseJournal;
	}

	public WarehouseJournalLayerTO fill_WarehouseJournalLayer(
			TransactionTo transaction) {

		WarehouseJournalLayerTO WarehouseJournalLayer = new WarehouseJournalLayerTO();
		WarehouseJournalLayer.setTransseq(transaction.getTransseq());
		WarehouseJournalLayer.setLayerid(0);
		WarehouseJournalLayer.setCalcprice(transaction.getPrice());
		WarehouseJournalLayer.setBalance(transaction.getBalance());
		WarehouseJournalLayer.setTransvalue(transaction.getSumstock());
		WarehouseJournalLayer.setLayerinqty(transaction.getInqty());
		WarehouseJournalLayer.setLayeroutq(transaction.getOutqty());
		WarehouseJournalLayer.setRevaltotal(zero);

		return null;
	}

	public TransactionTo calculate(TransactionTo transaction)
			throws Exception {
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
		AdminEJB EJB = new AdminEJB();
		DBArticle = EJB.getArticlesByKey(transaction.getItemcode());
		
		avgPrice = DBArticle.getAvgPrice();
		onhand = DBArticle.getOnHand();
		whsCode = transaction.getLoccode();
		transQuantity = transaction.getQuantity();
		transValue = transaction.getSumstock();

		for (Object object : DBArticle.getBranchArticles()) {
			BranchArticlesTO branch = (BranchArticlesTO) object;

			if (branch.getWhscode()
					.equals(whsCode)) {
				whsOnhand = branch.getOnhand();
				
			}
		}
		

		// ------------------------------------------------------------------------------------------------------------
		// Documentos Entrada de mercancias GoodReceipt ObjectType = 30
		// ------------------------------------------------------------------------------------------------------------

		if (transaction.getTranstype() == 30) {
			// Existencias
			// --------------------------------------------------------------------------------------------------------
			newOnhand = onhand + transQuantity;
			newWhsOnhand = whsOnhand + transQuantity;
			//Actualizar objeto princicpal
			transaction.setNewOnhand(newOnhand);
			transaction.setNewWhsOnhand(newWhsOnhand);		
			transaction.setInqty(transQuantity);
			transaction.setOutqty(zero);

			// Costos promedios
			// --------------------------------------------------------------------------------------------------------
			
			oldTotalArticle = onhand * avgPrice; 
			newTotalArticle = oldTotalArticle + transValue;
			newAvgPrice = newTotalArticle / newOnhand;
			//Actualizar objeto princicpal
			transaction.setNewAvgprice(newAvgPrice);
			transaction.setBalance(newTotalArticle);

		}

		return null;

	}


}

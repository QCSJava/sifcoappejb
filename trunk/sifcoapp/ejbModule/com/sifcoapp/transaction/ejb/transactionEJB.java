package com.sifcoapp.transaction.ejb;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import org.glassfish.jersey.gf.ejb.internal.EjbExceptionMapper;

import com.sifcoapp.admin.ejb.AdminEJB;
import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.WarehouseJournalDetailTO;
import com.sifcoapp.objects.admin.to.WarehouseJournalTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.InventoryLogDAO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
import com.sifcoapp.objects.inventory.to.InventoryLogTO;
import com.sifcoapp.objects.inventory.to.InventorylogInTo;
import com.sifcoapp.objects.sales.DAO.*;
import com.sifcoapp.objects.sales.to.*;
import com.sun.org.apache.regexp.internal.RESyntaxException;
import com.sun.xml.ws.server.sei.EndpointResponseMessageBuilder.Bare;

public class transactionEJB {
	Double zero = 0.0;
	public ResultOutTO save_Inventory_Log(InventoryLogTO inventory,
			Connection conn) throws Exception {
	
		ResultOutTO _return = new ResultOutTO();

		InventoryLogDAO inventDAO = new InventoryLogDAO(conn);
		inventDAO.setIstransaccional(true);

		_return.setDocentry(inventDAO.adm_inventorylog_mtto(inventory, 1));

		if (_return.getDocentry()== 0) {
			_return.setCodigoError(1);
			_return.setMensaje("No se puede almacenar Linea de Documento ");
			
			return _return;
		}else{
			_return.setCodigoError(0);
			_return.setMensaje("datos almacenados");
			
		}
		return _return;
	}
	public ResultOutTO save_WarehouseJournal(WarehouseJournalTO warehousejournal,
			Connection conn) throws Exception {
	
		ResultOutTO _return = new ResultOutTO();

		AdminDAO warehouseDAO = new AdminDAO(conn);
		warehouseDAO.setIstransaccional(true);
		
		_return.setDocentry(warehouseDAO.adm_warehousejournal_mtto(warehousejournal,1));

		if (_return.getDocentry()== 0) {
			_return.setCodigoError(1);
			_return.setMensaje("No se puede almacenar Linea de Documento ");
			
			return _return;
		}else{
			_return.setCodigoError(0);
			_return.setMensaje("datos almacenados");
			
		}
		return _return;
	}
	public ResultOutTO save_WarehouseJournallayer(InventorylogInTo parameters,
			Connection conn) throws Exception {

		ResultOutTO _return = new ResultOutTO();
		WarehouseJournalDetailTO WarehouseJournal = new WarehouseJournalDetailTO();
		WarehouseJournal.setTransseq(parameters.getDoclinenum());
		WarehouseJournal.setLayerid(0);
		WarehouseJournal.setCalcprice(parameters.getAvgPrice());
		WarehouseJournal.setBalance(parameters.getBalance());
		WarehouseJournal.setTransvalue(parameters.getQuantity());
		WarehouseJournal.setLayerinqty(parameters.getTotallc());
		WarehouseJournal.setLayeroutq(zero);
		WarehouseJournal.setRevaltotal(zero);
		AdminDAO DAO1 = new AdminDAO(conn);
		DAO1.setIstransaccional(true);
		_return.setDocentry(DAO1.adm_warehousejournalDetail_mtto(
				WarehouseJournal, 1));

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
	//llenado de warehousejournallayer y llamado a guardado y actualizacion de precios y existencias
	public ResultOutTO fill_save_WarehouseJournallayer(GoodsreceiptTO parameters,Connection conn,int _return)throws Exception{
		ResultOutTO res_whjl = new ResultOutTO();
		Iterator<GoodsReceiptDetailTO> iterator2 = parameters
				.getGoodReceiptDetail().iterator();
		while (iterator2.hasNext()) {
			GoodsReceiptDetailTO detalle = (GoodsReceiptDetailTO) iterator2
					.next();
			transactionEJB trans = new transactionEJB();
			ArticlesInTO Article = new ArticlesInTO();
			// llenando nuevo objetos
			Article.setAvgPrice(detalle.getPrice());
			Article.setOnHand(detalle.getQuantity());
			Article.setObjtype(detalle.getObjtype());
			Article.setItemCode(detalle.getItemcode());
			Article.setSww(detalle.getWhscode());
			// nueva instancia de admin Dao
			AdminDAO DAO1 = new AdminDAO(conn);
			DAO1.setIstransaccional(true);
			InventorylogInTo Inventorylog = new InventorylogInTo();
			Inventorylog = DAO1.Calcul_arti(Article);

			Inventorylog.setDoclinenum(_return);
			Inventorylog.setLayerId(detalle.getLinenum());
			
			res_whjl = save_WarehouseJournallayer(Inventorylog,conn);
			// --------------------------------------------------------------------------------------------------------------------------------
			// Actualización de existencias en almacenes y articulos
			// --------------------------------------------------------------------------------------------------------------------------------
			res_whjl = DAO1.Update_inventory_articles(Article, Inventorylog);

		}

	return res_whjl;
	}
}

package com.sifcoapp.objects.transaction.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.transaction.Transaction;

import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesPriceTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.admin.to.PricesListInTO;
import com.sifcoapp.objects.admin.to.PricesListTO;
import com.sifcoapp.objects.admin.to.TablesCatalogTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.GoodsissuesDetailDAO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;
import com.sifcoapp.objects.transaction.to.InventoryLogTO;
import com.sifcoapp.objects.transaction.to.InventorylogInTo;
import com.sifcoapp.objects.transaction.to.TransactionTo;
import com.sifcoapp.objects.transaction.to.WarehouseJournalLayerTO;
import com.sifcoapp.objects.transaction.to.WarehouseJournalTO;
import com.sun.rowset.CachedRowSetImpl;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.deploymentExtensionType;

public class TransactionDAO extends CommonDAO {

	public TransactionDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionDAO(Connection _conn) {
		super(_conn);
		// TODO Auto-generated constructor stub
	}

	/* Mantenimiento de la tabla warehouse y su detalle */
	public int adm_warehousejournal_mtto(WarehouseJournalTO parameters,
			int accion) throws Exception {
		// ResultOutTO _return = new ResultOutTO();
		List v_resp;
		// t.setDbObject("{call sp_inv_gis0_goodsissues_mtto    (1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{? = call sp_adm_warehousejournal_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(2, "_transseq", new Integer(parameters.getTransseq()));
		this.setInt(3, "_transtype ", new Integer(parameters.getTranstype()));
		this.setInt(4, "_createdby ", new Integer(parameters.getCreatedby()));
		this.setString(5, "_base_ref ", parameters.getBase_ref());
		this.setInt(6, "_doclinenum ", new Integer(parameters.getDoclinenum()));
		this.setString(7, "_itemcode ", parameters.getItemcode());
		this.setDouble(8, "_inqty ", new Double(parameters.getInqty()));
		this.setDouble(9, "_outqty ", new Double(parameters.getOutqty()));
		this.setDouble(10, "_price ", new Double(parameters.getPrice()));
		this.setString(11, "_trnsfract ", parameters.getTrnsfract());
		this.setString(12, "_pricedifac ", parameters.getPricedifac());
		this.setString(13, "_varianceac ", parameters.getVarianceac());
		this.setString(14, "_returnact ", parameters.getReturnact());
		this.setString(15, "_clearact ", parameters.getClearact());
		this.setString(16, "_costact ", parameters.getCostact());
		this.setString(17, "_wipact ", parameters.getWipact());
		this.setDouble(18, "_openstock ", new Double(parameters.getOpenstock()));
		this.setDouble(19, "_pricediff ", new Double(parameters.getPricediff()));
		this.setString(20, "_invntact ", parameters.getInvntact());
		this.setInt(21, "_sublinenum ", new Integer(parameters.getSublinenum()));
		this.setInt(22, "_appobjline ", new Integer(parameters.getAppobjline()));
		this.setDouble(23, "_expenses ", new Double(parameters.getExpenses()));
		this.setDouble(24, "_openexp ", new Double(parameters.getOpenexp()));
		this.setDouble(25, "_allocation ",
				new Double(parameters.getAllocation()));
		this.setDouble(26, "_openalloc ", new Double(parameters.getOpenalloc()));
		this.setDouble(27, "_expalloc ", new Double(parameters.getExpalloc()));
		this.setDouble(28, "_oexpalloc ", new Double(parameters.getOexpalloc()));
		this.setDouble(29, "_openpdiff ", new Double(parameters.getOpenpdiff()));
		this.setDouble(30, "_neginvadjs ",
				new Double(parameters.getNeginvadjs()));
		this.setDouble(31, "_openneginv ",
				new Double(parameters.getOpenneginv()));
		this.setString(32, "_negstckact ", parameters.getNegstckact());
		this.setDouble(33, "_btransval ", new Double(parameters.getBtransval()));
		this.setDouble(34, "_varval ", new Double(parameters.getVarval()));
		this.setDouble(35, "_bexpval ", new Double(parameters.getBexpval()));
		this.setDouble(36, "_cogsval ", new Double(parameters.getCogsval()));
		this.setDouble(37, "_bnegaval ", new Double(parameters.getBnegaval()));
		this.setString(38, "_ioffincacc ", parameters.getIoffincacc());
		this.setDouble(39, "_ioffincval ",
				new Double(parameters.getIoffincval()));
		this.setString(40, "_doffdecacc ", parameters.getDoffdecacc());
		this.setDouble(41, "_doffdecval ",
				new Double(parameters.getDoffdecval()));
		this.setString(42, "_decacc ", parameters.getDecacc());
		this.setDouble(43, "_decval ", new Double(parameters.getDecval()));
		this.setDouble(44, "_wipval ", new Double(parameters.getWipval()));
		this.setString(45, "_wipvaracc ", parameters.getWipvaracc());
		this.setDouble(46, "_wipvarval ", new Double(parameters.getWipvarval()));
		this.setString(47, "_incact ", parameters.getIncact());
		this.setDouble(48, "_incval ", new Double(parameters.getIncval()));
		this.setString(49, "_expcacc ", parameters.getExpcacc());
		this.setInt(50, "_messageid ", new Integer(parameters.getMessageid()));
		this.setInt(51, "_loctype ", new Integer(parameters.getLoctype()));
		this.setString(52, "_loccode ", parameters.getLoccode());
		this.setString(53, "_poststatus ", parameters.getPoststatus());
		this.setDouble(54, "_sumstock ", new Double(parameters.getSumstock()));
		this.setDouble(55, "_openqty ", new Double(parameters.getOpenqty()));
		this.setString(56, "_paoffacc ", parameters.getPaoffacc());
		this.setDouble(57, "_paoffval ", new Double(parameters.getPaoffval()));
		this.setDouble(58, "_openpaoff ", new Double(parameters.getOpenpaoff()));
		this.setString(59, "_paacc ", parameters.getPaacc());
		this.setDouble(60, "_paval ", new Double(parameters.getPaval()));
		this.setDouble(61, "_openpa ", new Double(parameters.getOpenpa()));

		this.setInt(62, "_action", new Integer(accion));
		v_resp = this.runQuery();
		// System.out.println(this.getInt());

		// _return.setDocentry(this.getInt());
		return this.getInt();

	}

	public int adm_warehousejournalLayer_mtto(
			WarehouseJournalLayerTO parameters, int accion) throws Exception {
		List v_resp;
		// t.setDbObject("{call sp_inv_gis0_goodsissues_mtto     (1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_adm_warehousejournallayer_mtto(?,?,?,?,?,?,?,?,?)}");
		this.setInt(1, "_transseq", new Integer(parameters.getTransseq()));
		this.setInt(2, "_layerid", new Integer(parameters.getLayerid()));
		this.setDouble(3, "_calcprice", new Double(parameters.getCalcprice()));
		this.setDouble(4, "_balance", new Double(parameters.getBalance()));
		this.setDouble(5, "_transvalue", new Double(parameters.getTransvalue()));
		this.setDouble(6, "_layerinqty", new Double(parameters.getLayerinqty()));
		this.setDouble(7, "_layeroutq", new Double(parameters.getLayeroutq()));
		this.setDouble(8, "_revaltotal", new Double(parameters.getRevaltotal()));
		this.setInt(9, "_action", new Integer(accion));

		v_resp = this.runQuery();

		return this.getInt();

	}

	public ResultOutTO Update_Onhand_articles(TransactionTo transaction)
			throws Exception {

		ResultOutTO _return = new ResultOutTO();
		int lstResultSet = 0;

		this.setDbObject("UPDATE cat_art0_articles SET avgprice=?, onhand=? WHERE itemcode=?");

		this.setDouble(1, "avgprice", transaction.getNewAvgprice());
		this.setDouble(2, "onhand", transaction.getNewOnhand());
		this.setString(3, "itemcode", transaction.getItemcode());

		lstResultSet = this.runUpdate();

		_return.setCodigoError(lstResultSet);
		_return.setMensaje("Datos actualizados correctamente");
		return _return;
	}

}

package com.sifcoapp.objects.inventory.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;
import com.sifcoapp.objects.inventory.to.InventoryLogTO;
import com.sun.rowset.CachedRowSetImpl;

public class InventoryLogDAO extends CommonDAO{
	
	public InventoryLogDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InventoryLogDAO(Connection _conn) {
		super(_conn);
		// TODO Auto-generated constructor stub
	}
	public int adm_inventorylog_mtto(InventoryLogTO parameters,int accion)throws Exception {
		int v_resp;
		// t.setDbObject("{call sp_inv_gis0_goodsissue  (1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7)}");
		this.setDbObject("{call sp_adm_inventorylog_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(1,"_messageid", new Integer( parameters.getMessageid()));
		this.setInt(2,"_docentry", new Integer( parameters.getDocentry()));
		this.setInt(3,"_transtype", new Integer( parameters.getTranstype()));
		this.setInt(4,"_doclinenum", new Integer( parameters.getDoclinenum()));
		this.setDouble(5,"_quantity",new Double( parameters.getQuantity()));
		this.setDouble(6,"_effectqty",new Double( parameters.getEffectqty()));
		this.setInt(7,"_loctype", new Integer( parameters.getLoctype()));
		this.setString(8,"_loccode", parameters.getLoccode());
		this.setDouble(9,"_totallc",new Double( parameters.getTotallc()));
		this.setInt(10,"_baseabsent", new Integer( parameters.getBaseabsent()));
		this.setInt(11,"_basetype", new Integer( parameters.getBasetype()));
		this.setInt(12,"_accumtype", new Integer( parameters.getAccumtype()));
		this.setInt(13,"_actiontype", new Integer( parameters.getActiontype()));
		this.setDouble(14,"_expenseslc",new Double( parameters.getExpenseslc()));
		if (parameters.getDocduedate() == null){
			this.setDate(15,"_docduedate", parameters.getDocduedate() );
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameters.getDocduedate() .getTime());
			this.setDate(15,"_docduedate", fecha);
		}
		this.setString(16,"_itemcode,", parameters.getItemcode());
		this.setString(17,"_bpcardcode", parameters.getBpcardcode());
		if (parameters.getDocdate() == null){
			this.setDate(18,"_docdate", parameters.getDocdate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameters.getDocdate().getTime());
			this.setDate(18,"_docdate", fecha);
		}
		this.setString(19,"_comment", parameters.getComment());
		this.setString(20,"_jrnlmemo", parameters.getJrnlmemo());
		this.setString(21,"_ref1", parameters.getRef1());
		this.setString(22,"_ref2", parameters.getRef2());
		this.setInt(23,"_baseline", new Integer( parameters.getBaseline()));
		this.setInt(24,"_snbtype", new Integer( parameters.getSnbtype()));
		this.setString(25,"_ocrcode", parameters.getOcrcode());
		this.setString(26,"_ocrcode2", parameters.getOcrcode2());
		this.setString(27,"_ocrcode3", parameters.getOcrcode3());
		this.setString(28,"_cardname", parameters.getCardname());
		this.setString(29,"_dscription", parameters.getDscription());
		this.setString(30,"_base_ref", parameters.getBase_ref());
		this.setDouble(31,"_pricerate",new Double( parameters.getPricerate()));
		this.setDouble(32,"_doctotal",new Double( parameters.getDoctotal()));
		this.setDouble(33,"_price",new Double( parameters.getPrice()));
		if (parameters.getTaxdate() == null){
			this.setDate(34,"_taxdate", parameters.getTaxdate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameters.getTaxdate().getTime());
			this.setDate(34,"_taxdate", fecha);
		}
		this.setInt(35,"_usersign", new Integer( parameters.getUsersign()));
		this.setInt(36,"_action", new Integer(accion));
		
		
		v_resp =this.runUpdate();
		//System.out.println(this.getInt());
		
		return v_resp;
		
	}

	public InventoryLogTO getInventoryLogByKey(int messageid) throws Exception {
		InventoryLogTO _return = new InventoryLogTO();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_inventorylog_by_key(?)}");
		this.setInt(1, "_messageid", new Integer(messageid));
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					InventoryLogTO Inventory = new InventoryLogTO();
					Inventory.setMessageid(rowsetActual.getInt(1));
					Inventory.setDocentry(rowsetActual.getInt(2));
					Inventory.setTranstype(rowsetActual.getInt(3));
					Inventory.setDoclinenum(rowsetActual.getInt(4));
					Inventory.setQuantity(rowsetActual.getDouble(5));
					Inventory.setEffectqty(rowsetActual.getDouble(6));
					Inventory.setLoctype(rowsetActual.getInt(7));
					Inventory.setLoccode(rowsetActual.getString(8));
					Inventory.setTotallc(rowsetActual.getDouble(9));
					Inventory.setBaseabsent(rowsetActual.getInt(10));
					Inventory.setBasetype(rowsetActual.getInt(11));
					Inventory.setAccumtype(rowsetActual.getInt(12));
					Inventory.setActiontype(rowsetActual.getInt(13));
					Inventory.setExpenseslc(rowsetActual.getDouble(14));
					Inventory.setDocduedate(rowsetActual.getDate(15));
					Inventory.setItemcode(rowsetActual.getString(16));
					Inventory.setBpcardcode(rowsetActual.getString(17));
					Inventory.setDocdate(rowsetActual.getDate(18));
					Inventory.setComment(rowsetActual.getString(19));
					Inventory.setJrnlmemo(rowsetActual.getString(20));
					Inventory.setRef1(rowsetActual.getString(21));
					Inventory.setRef2(rowsetActual.getString(22));
					Inventory.setBaseline(rowsetActual.getInt(23));
					Inventory.setSnbtype(rowsetActual.getInt(24));
					Inventory.setOcrcode(rowsetActual.getString(25));
					Inventory.setOcrcode2(rowsetActual.getString(26));
					Inventory.setOcrcode3(rowsetActual.getString(27));
					Inventory.setCardname(rowsetActual.getString(28));
					Inventory.setDscription(rowsetActual.getString(29));
					Inventory.setBase_ref(rowsetActual.getString(30));
					Inventory.setPricerate(rowsetActual.getDouble(31));
					Inventory.setDoctotal(rowsetActual.getDouble(32));
					Inventory.setPrice(rowsetActual.getDouble(33));
					Inventory.setTaxdate(rowsetActual.getDate(34));
					Inventory.setUsersign(rowsetActual.getInt(35));
					Inventory.setCreatedate(rowsetActual.getDate(36));
					Inventory.setCreatetime(rowsetActual.getInt(37));
					_return=Inventory;
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
}

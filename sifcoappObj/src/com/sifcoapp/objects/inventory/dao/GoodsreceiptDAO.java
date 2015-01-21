package com.sifcoapp.objects.inventory.dao;


import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptInTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;


public class GoodsreceiptDAO extends CommonDAO{
	
	public List getGoodsreceipt(GoodsReceiptInTO param){
		List _return = new Vector();
		List lstResultSet = null;
		
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_goodsreceipt(?,?,?)}");
		
		if (param.getDocdate().getDay()==0){
			this.setDate(2, "_docdate", param.getDocdate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(param.getDocdate().getTime());
			this.setDate(2, "_docdate", fecha);
		}
		this.setInt(1, "_docnum", new Integer(param.getDocnum()));
		
		this.setInt(3, "_series", new Integer(param.getSeries()));
		
		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					GoodsreceiptTO docu = new GoodsreceiptTO();
					docu.setDocentry(rowsetActual.getInt(1));
					docu.setDocnum(rowsetActual.getInt(2));
					docu.setDoctype(rowsetActual.getString(3));
					docu.setCanceled(rowsetActual.getString(4));
					docu.setDocstatus(rowsetActual.getString(5));
					docu.setObjtype(rowsetActual.getString(6));
					docu.setDocdate(rowsetActual.getDate(7));
					docu.setDocduedate(rowsetActual.getDate(8));
					docu.setDoctotal(rowsetActual.getDouble(9));
					docu.setRef1(rowsetActual.getString(10));
					docu.setComments(rowsetActual.getString(11));
					docu.setJrnlmemo(rowsetActual.getString(12));
					docu.setTransid(rowsetActual.getInt(13));
					docu.setSeries(rowsetActual.getInt(14));
					docu.setTowhscode(rowsetActual.getString(15));
					docu.setFromwhscode(rowsetActual.getString(16));
					docu.setConfirmed(rowsetActual.getString(17));
					docu.setUsersign(rowsetActual.getInt(18));
					docu.setCreatedate(rowsetActual.getDate(19));
					docu.setCreatetime(rowsetActual.getInt(20));
					_return.add(docu);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
	
	public int Goodsreceipt_mtto(GoodsreceiptTO parameters, int action) {

		int v_resp = 0;
		// this.setDbObject("{call sp_cat_acc_period_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_inv_gre0_goodsreceipt_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(1,"_docentry", parameters.getDocentry());
		this.setInt(2,"_docnum", parameters.getDocnum());
		this.setString(3,"_doctype", parameters.getDoctype());
		this.setString(4,"_canceled", parameters.getCanceled());
		this.setString(5,"_docstatus", parameters.getDocstatus());
		this.setString(6,"_objtype", parameters.getObjtype());
		this.setDate(7,"_docdate", parameters.getDocdate());
		this.setDate(8,"_docduedate", parameters.getDocduedate());
		this.setDouble(9,"_doctotal", parameters.getDoctotal());
		this.setString(10,"_ref1", parameters.getRef1());
		this.setString(11,"_comments", parameters.getComments());
		this.setString(12,"_jrnlmemo", parameters.getJrnlmemo());
		this.setInt(13,"_transid", parameters.getTransid());
		this.setInt(14,"_series", parameters.getSeries());
		this.setString(15,"_towhscode", parameters.getTowhscode());
		this.setString(16,"_fromwhscode", parameters.getFromwhscode());
		this.setString(17,"_confirmed", parameters.getConfirmed());
		this.setInt(18,"_usersign", parameters.getUsersign());
		this.setDate(19,"_createdate", parameters.getCreatedate());
		this.setInt(20,"_createtime", parameters.getCreatetime());
		this.setInt(21, "_action", new Integer(action));

		v_resp = this.runUpdate();

		return v_resp;
	}

}

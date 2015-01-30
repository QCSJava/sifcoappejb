package com.sifcoapp.objects.inventory.dao;



import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptInTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;


public class GoodsReceiptDAO extends CommonDAO{
	
	public List getGoodsreceipt(GoodsReceiptInTO param)throws Exception {
		List _return = new Vector();
		List lstResultSet = null;
		
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_goodsreceipt(?,?,?,?,?,?,?,?)}");
		
		if (param.getDocdate() == null){
			this.setDate(2, "_docdate", param.getDocdate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(param.getDocdate().getTime());
			this.setDate(2, "_docdate", fecha);
		}
		if (param.getDocduedate() == null){
			this.setDate(8, "_docduedate", param.getDocduedate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(param.getDocduedate().getTime());
			this.setDate(8, "_docdate", fecha);
		}
		
		this.setInt(1, "_docnum", new Integer(param.getDocnum()));
		this.setInt(3, "_series", new Integer(param.getSeries()));
		this.setString(4, "_towhscode", param.getTowhscode());
		this.setString(5, "_fromwhscode", param.getFromwhscode());
		this.setString(6, "_ref1", param.getRef1());
		this.setString(7, "_comments", param.getComments());
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
	//Retorna elemento goodsreceipt con detalle por clave
	public GoodsreceiptTO getGoodsReceiptByKey(int docentry) throws Exception {
		GoodsreceiptTO _return = new GoodsreceiptTO();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_goodsReceipt_by_key(?)}");
		this.setInt(1, "_docentry", new Integer(docentry));
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		GoodReceiptDetailDAO Detail = new GoodReceiptDetailDAO();
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					GoodsreceiptTO documento = new GoodsreceiptTO();
					documento.setDocentry(rowsetActual.getInt(1));
					documento.setDocnum(rowsetActual.getInt(2));
					documento.setDoctype(rowsetActual.getString(3));
					documento.setCanceled(rowsetActual.getString(4));
					documento.setDocstatus(rowsetActual.getString(5));
					documento.setObjtype(rowsetActual.getString(6));
					documento.setDocdate(rowsetActual.getDate(7));
					documento.setDocduedate(rowsetActual.getDate(8));
					documento.setDoctotal(rowsetActual.getDouble(9));
					documento.setRef1(rowsetActual.getString(10));
					documento.setComments(rowsetActual.getString(11));
					documento.setJrnlmemo(rowsetActual.getString(12));
					documento.setTransid(rowsetActual.getInt(13));
					documento.setSeries(rowsetActual.getInt(14));
					documento.setTowhscode(rowsetActual.getString(15));
					documento.setFromwhscode(rowsetActual.getString(16));
					documento.setConfirmed(rowsetActual.getString(17));
					documento.setUsersign(rowsetActual.getInt(18));
					documento.setCreatedate(rowsetActual.getDate(19));
					documento.setCreatetime(rowsetActual.getInt(20));
					documento.setGoodReceiptDetail(Detail.getGoodReceiptDetail(rowsetActual.getInt(1)));
					_return=documento;
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
	
	public int inv_GoodsReceipt_mtto(GoodsreceiptTO parameters, int accion) throws Exception {

		List v_resp;
		// this.seObject("{call sp_inv_gre0_goodsreceipt_mtto    (1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{? = call sp_inv_gre0_goodsreceipt_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		if (parameters.getDocdate() == null){
			this.setDate(8,"_docdate", parameters.getDocdate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameters.getDocdate().getTime());
			this.setDate(8,"_docdate", fecha);
		}
		if (parameters.getDocduedate() == null){
			this.setDate(9,"_docduedate", parameters.getDocduedate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameters.getDocduedate().getTime());
			this.setDate(9,"_docduedate", fecha);
		}
		this.setInt(2,"_docentry", new Integer(parameters.getDocentry()));
		this.setInt(3,"_docnum", new Integer(parameters.getDocnum()));
		this.setString(4,"_doctype", parameters.getDoctype());
		this.setString(5,"_canceled", parameters.getCanceled());
		this.setString(6,"_docstatus", parameters.getDocstatus());
		this.setString(7,"_objtype", parameters.getObjtype());		
		this.setDouble(10,"_doctotal", new Double(parameters.getDoctotal()));
		this.setString(11,"_ref1", parameters.getRef1());
		this.setString(12,"_comments", parameters.getComments());
		this.setString(13,"_jrnlmemo", parameters.getJrnlmemo());
		this.setInt(14,"_transid", new Integer(parameters.getTransid()));
		this.setInt(15,"_series", new Integer(parameters.getSeries()));
		this.setString(16,"_towhscode", parameters.getTowhscode());
		this.setString(17,"_fromwhscode", parameters.getFromwhscode());
		this.setString(18,"_confirmed", parameters.getConfirmed());
		this.setInt(19,"_usersign", new Integer(parameters.getUsersign()));
		this.setInt(20, "_action", new Integer(accion));

		v_resp = this.runQuery();

		return this.getInt();
	}

}

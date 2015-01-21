package com.sifcoapp.objects.inventory.dao;
import com.sifcoapp.objects.inventory.to.*;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.inventory.dao.GoodReceiptDetailDAO;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class GoodsIssuesDAO extends CommonDAO{
	
	//Retorna de goodsisuues registros por filtro
	public List getGoodsissues(GoodsissuesInTO param) {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_goodsissues(?,?,?)}");
		
		if (param.getDocdate() == null){
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
					GoodsissuesTO documento = new GoodsissuesTO();
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
					_return.add(documento);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
	
	//busca elemento en tabla goosissues por clave
	public List getGoodsissuesByKey(int docentry) {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_goodsissues_by_key(?)}");
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
					GoodsissuesTO documento = new GoodsissuesTO();
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
					documento.setGoodIssuesDetail(Detail.getGoodReceiptDetail(rowsetActual.getInt(1)));
					_return.add(documento);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
//mantenimiento de la tabla goodsissues
	public int inv_goodsissues_mtto(GoodsissuesTO parameters,int accion){
		int v_resp = 0;
		this.setDbObject("{call sp_inv_gis0_goodsissues_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(1,"_docentry", new Integer(parameters.getDocentry()));
		this.setInt(2,"_docnum", new Integer(parameters.getDocnum()));
		this.setString(3,"_doctype", parameters.getDoctype());
		this.setString(4,"_canceled", parameters.getCanceled());
		this.setString(5,"_docstatus", parameters.getDocstatus());
		this.setString(6,"_objtype", parameters.getObjtype());
		this.setDate(7,"_docdate", parameters.getDocdate());
		this.setDate(8,"_docduedate", parameters.getDocduedate());
		this.setDouble(9,"_doctotal", new Double(parameters.getDoctotal()));
		this.setString(10,"_ref1", parameters.getRef1());
		this.setString(11,"_comments", parameters.getComments());
		this.setString(12,"_jrnlmemo", parameters.getJrnlmemo());
		this.setInt(13,"_transid", new Integer(parameters.getTransid()));
		this.setInt(14,"_series", new Integer(parameters.getSeries()));
		this.setString(15,"_towhscode", parameters.getTowhscode());
		this.setString(16,"_fromwhscode", parameters.getFromwhscode());
		this.setString(17,"_confirmed", parameters.getConfirmed());
		this.setInt(18,"_usersign", new Integer(parameters.getUsersign()));
		this.setInt(19, "_action", new Integer(accion));

		v_resp = this.runUpdate();

		return v_resp;
		
	}
}

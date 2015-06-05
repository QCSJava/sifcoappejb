package com.sifcoapp.objects.inventory.dao;
import com.sifcoapp.objects.inventory.to.*;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class TransfersDAO extends CommonDAO{
	public TransfersDAO() {
		super();
	}

	public TransfersDAO(Connection _conn) {
		super(_conn);
	}
	//RETORNA REGISTROS DE LA TABLA TRANSFERS POR TRES TIPOS DE FILTO: DOCDATE, DOCNUM Y SERIES
	public List getTransfers(TransfersInTO param) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_transfers(?,?,?,?,?,?,?,?)}");
		
		if (param.getDocdate() == null){
			this.setDate(2, "_docdate", param.getDocdate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(param.getDocdate().getTime());
			this.setDate(2, "_docdate", fecha);
		}
		if (param.getDocduedate() == null){
			this.setDate(4, "_docduedate", param.getDocduedate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(param.getDocduedate().getTime());
			this.setDate(4, "_docdate", fecha);
		}
		this.setInt(1, "_docnum", new Integer(param.getDocnum()));
		this.setInt(3, "_series", new Integer(param.getSeries()));
		this.setString(5, "_towshcode",param.getTowhscode());
		this.setString(6, "_fromwshcode",param.getFromwhscode());
		this.setString(7, "_ref1",param.getRef1());
		this.setString(8, "_comments",param.getComments());
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					TransfersTO transfers = new TransfersTO();
					transfers.setDocentry(rowsetActual.getInt(1));
					transfers.setDocnum(rowsetActual.getInt(2));
					transfers.setDoctype(rowsetActual.getString(3));
					transfers.setCanceled(rowsetActual.getString(4));
					transfers.setDocstatus(rowsetActual.getString(5));
					transfers.setObjtype(rowsetActual.getString(6));
					transfers.setDocdate(rowsetActual.getDate(7));
					transfers.setDocduedate(rowsetActual.getDate(8));
					transfers.setDoctotal(rowsetActual.getDouble(9));
					transfers.setRef1(rowsetActual.getString(10));
					transfers.setComments(rowsetActual.getString(11));
					transfers.setJrnlmemo(rowsetActual.getString(12));
					transfers.setTransid(rowsetActual.getInt(13));
					transfers.setSeries(rowsetActual.getInt(14));
					transfers.setTowhscode(rowsetActual.getString(15));
					transfers.setFromwhscode(rowsetActual.getString(16));
					transfers.setConfirmed(rowsetActual.getString(17));
					transfers.setUsersign(rowsetActual.getInt(18));
					transfers.setCreatedate(rowsetActual.getDate(19));
					transfers.setCreatetime(rowsetActual.getInt(20));
					_return.add(transfers);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
	
	//RETORNA UN REGISTRO DE LA TABLA TRANSFERS CON SUS HIJAS DE LA TABLA TRANSFERSDETAIL
	public TransfersTO getTransfersByKey(int docentry) throws Exception {
		TransfersTO _return = new TransfersTO();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_transfers_by_key(?)}");
		this.setInt(1, "_docentry", new Integer(docentry));
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		TransfersDetailDAO Detail = new TransfersDetailDAO();
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					TransfersTO transfers = new TransfersTO();
					transfers.setDocentry(rowsetActual.getInt(1));
					transfers.setDocnum(rowsetActual.getInt(2));
					transfers.setDoctype(rowsetActual.getString(3));
					transfers.setCanceled(rowsetActual.getString(4));
					transfers.setDocstatus(rowsetActual.getString(5));
					transfers.setObjtype(rowsetActual.getString(6));
					transfers.setDocdate(rowsetActual.getDate(7));
					transfers.setDocduedate(rowsetActual.getDate(8));
					transfers.setDoctotal(rowsetActual.getDouble(9));
					transfers.setRef1(rowsetActual.getString(10));
					transfers.setComments(rowsetActual.getString(11));
					transfers.setJrnlmemo(rowsetActual.getString(12));
					transfers.setTransid(rowsetActual.getInt(13));
					transfers.setSeries(rowsetActual.getInt(14));
					transfers.setTowhscode(rowsetActual.getString(15));
					transfers.setFromwhscode(rowsetActual.getString(16));
					transfers.setConfirmed(rowsetActual.getString(17));
					transfers.setUsersign(rowsetActual.getInt(18));
					transfers.setCreatedate(rowsetActual.getDate(19));
					transfers.setCreatetime(rowsetActual.getInt(20));
					transfers.setTransfersDetail(Detail.getTransfersDetail(rowsetActual.getInt(1)));
					_return=transfers;
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
//#############################CRUD DE LA TABLA TRANSFERS###############################
	public int inv_transfers_mtto(TransfersTO parameters,int accion)throws Exception {
		List v_resp;
		// t.setDbObject("{call sp_inv_gis0_goodsissues_mtto  (1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{? = call sp_inv_tra0_transfers_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(2,"_docentry", new Integer(parameters.getDocentry()));
		this.setInt(3,"_docnum", new Integer(parameters.getDocnum()));
		this.setString(4,"_doctype", parameters.getDoctype());
		this.setString(5,"_canceled", parameters.getCanceled());
		this.setString(6,"_docstatus", parameters.getDocstatus());
		this.setString(7,"_objtype", parameters.getObjtype());
		if (parameters.getDocdate() == null){
			this.setDate(8,"_docdate", parameters.getDocdate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameters.getDocdate().getTime());
			this.setDate(8, "_docdate", fecha);
		}
		if (parameters.getDocduedate() == null){
			this.setDate(9,"_docduedate", parameters.getDocduedate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameters.getDocduedate().getTime());
			this.setDate(9, "_docduedate", fecha);
		}
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
		//System.out.println(this.getInt());
		
		return this.getInt();
		
	}
}

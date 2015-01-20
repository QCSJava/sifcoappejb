package com.sifcoapp.objects.inventory.dao;
import com.sifcoapp.objects.inventory.to.*;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class GoodsissuesDAO extends CommonDAO{
	
	//Retorna de goodsisuues registros por filtro
	public List getGoodsissues(GoodsissuesInTO param) {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_goodsissues(?,?,?)}");
		
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

	public boolean inv_goodsissues_mtto(GoodsissuesTO parameters,int accion){
		int v_resp = 0;
		this.setDbObject("{call sp_cat_acc_assignment_mtto(?,?)}");
		return false;
		
	}
}

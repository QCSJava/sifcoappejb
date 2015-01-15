package com.sifcoapp.objects.inventary.dao;

import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.inventary.to.GoodsreceiptTO;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;


public class GoodsreceiptDAO extends CommonDAO{
	
	public List getListGoodsreceipt(int docnum, Date docdate, int series){
		List _return = new Vector();
		List lstResultSet = null;
		
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_goodsreceipt(?,?,?)}");
		this.setArrayString(1, "_docnum", docnum);
		this.setArrayString(2, "_docdate", docdate);
		this.setArrayString(3, "_series", series);
		
		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					GoodsreceiptTO article = new GoodsreceiptTO();
					article.setDocentry(rowsetActual.getInt(1));
					article.setDocnum(rowsetActual.getInt(2));
					article.setCanceled(rowsetActual.getString(3));
					article.setDocstatus(rowsetActual.getString(4));
					article.setObjtype(rowsetActual.getString(5));
					article.setDocdate(rowsetActual.getDate(6));
					article.setDocduedate(rowsetActual.getDate(7));
					article.setDoctotal(rowsetActual.getDouble(8));
					article.setComments(rowsetActual.getString(9));
					article.setRef2(rowsetActual.getString(10));
					article.setSeries(rowsetActual.getInt(11));
					article.setWhscode(rowsetActual.getString(12));
					article.setIdmovement(rowsetActual.getString(13));
					article.setItmsgrpcod(rowsetActual.getInt(14));
					article.setCreateddatel(rowsetActual.getDate(15));
					article.setModifiedbyl(rowsetActual.getString(16));
					article.setCreatedbyl(rowsetActual.getString(17));
					article.setModifieddatel(rowsetActual.getDate(18));

					_return.add(article);
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

package com.sifcoapp.objects.inventary.dao;
import com.sifcoapp.objects.inventary.to.*;
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
	public List getGoodsissues(Integer docnum, Date docdate,Integer series) {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_goodsissues(?,?,?)}");
		this.setString(1, "_docnum", docnum);
		this.setString(2, "_docdate", docdate);
		this.setString(2, "_series", series);

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
					documento.setCanceled(rowsetActual.getString(3));
					documento.setDocstatus(rowsetActual.getString(4));
					documento.setObjtype(rowsetActual.getString(5));
					documento.setDocdate(rowsetActual.getDate(6));
					documento.setDocduedate(rowsetActual.getDate(7));
					documento.setDoctotal(rowsetActual.getDouble(8));
					documento.setComments(rowsetActual.getString(9));
					documento.setRef2(rowsetActual.getString(10));
					documento.setSeries(rowsetActual.getInt(11));
					documento.setWhscode(rowsetActual.getString(12));
					documento.setItmsgrpcod(rowsetActual.getInt(13));
					documento.setCreateddatel(rowsetActual.getDate(14));
					documento.setModifiedbyl(rowsetActual.getString(15));
					documento.setCreatedbyl(rowsetActual.getString(16));
					documento.setModifieddatel(rowsetActual.getDate(17));
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

}

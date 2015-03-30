package com.sifcoapp.objects.bank.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.bank.to.ColecturiaDetailTO;
import com.sifcoapp.objects.bank.to.ColecturiaInTO;
import com.sifcoapp.objects.bank.to.ColecturiaTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sun.rowset.CachedRowSetImpl;

public class ColecturiaDetailDAO extends CommonDAO {

	public ColecturiaDetailDAO() {
		super();
	}
	
	public ColecturiaDetailDAO(Connection _conn) {
		super(_conn);
	}

	public int ges_ges_col0_colecturiadetail_mtto(
			ColecturiaDetailTO parameters, int action) throws Exception {
		List v_resp;
		// s.setDbObject("{call sp_ges_col0_colecturiadetail_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_ges_col0_colecturiadetail_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(1, "_docentry,", parameters.getDocentry());
		this.setInt(2, "_linenum,", parameters.getLinenum());
		this.setString(3, "_linestatus,", parameters.getLinestatus());
		this.setString(4, "_objtype,", parameters.getObjtype());
		this.setString(5, "_dscription,", parameters.getDscription());
		this.setString(6, "_acctcode,", parameters.getAcctcode());
		this.setString(7, "_acctcode2,", parameters.getAcctcode2());
		this.setString(8, "_acctcode3,", parameters.getAcctcode3());
		this.setString(9, "_ctlaccount,", parameters.getCtlaccount());
		this.setString(10, "_ocrcode,", parameters.getOcrcode());
		this.setInt(11, "_transid,", parameters.getTransid());
		this.setString(12, "_confirmed,", parameters.getConfirmed());
		this.setString(13, "_peymethod,", parameters.getPeymethod());
		this.setDouble(14, "_paidsum,", parameters.getPaidsum());
		this.setDouble(15, "_vatsum,", parameters.getVatsum());
		this.setString(16, "_docsubtype,", parameters.getDocsubtype());
		this.setString(17, "_value1,", parameters.getValue1());
		this.setString(18, "_value2,", parameters.getValue2());
		this.setString(19, "_value3,", parameters.getValue3());
		v_resp = this.runQuery();
		return this.getInt();
	}

	public List get_ges_colecturiaDetailByKey(int parameters) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_ges_colecturiadetail_by_key(?)}");
		this.setInt(1, "_docentry", parameters);

		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					ColecturiaDetailTO colecturia = new ColecturiaDetailTO();
					colecturia.setDocentry(rowsetActual.getInt(1));
					colecturia.setLinenum(rowsetActual.getInt(2));
					colecturia.setLinestatus(rowsetActual.getString(3));
					colecturia.setObjtype(rowsetActual.getString(4));
					colecturia.setDscription(rowsetActual.getString(5));
					colecturia.setAcctcode(rowsetActual.getString(6));
					colecturia.setAcctcode2(rowsetActual.getString(7));
					colecturia.setAcctcode3(rowsetActual.getString(8));
					colecturia.setCtlaccount(rowsetActual.getString(9));
					colecturia.setOcrcode(rowsetActual.getString(10));
					colecturia.setTransid(rowsetActual.getInt(11));
					colecturia.setConfirmed(rowsetActual.getString(12));
					colecturia.setPeymethod(rowsetActual.getString(13));
					colecturia.setPaidsum(rowsetActual.getDouble(14));
					colecturia.setVatsum(rowsetActual.getDouble(15));
					colecturia.setDocsubtype(rowsetActual.getString(16));
					colecturia.setValue1(rowsetActual.getString(17));
					colecturia.setValue2(rowsetActual.getString(18));
					colecturia.setValue3(rowsetActual.getString(19));

					_return.add(colecturia);
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

package com.sifcoapp.objects.bank.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.bank.to.ColecturiaConceptTO;
import com.sifcoapp.objects.bank.to.ColecturiaDetailTO;
import com.sifcoapp.objects.bank.to.ColecturiaInTO;
import com.sifcoapp.objects.bank.to.ColecturiaTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sun.rowset.CachedRowSetImpl;

public class ColecturiaConceptDAO extends CommonDAO {

	public ColecturiaConceptDAO() {
		super();
	}
	
	public ColecturiaConceptDAO(Connection _conn) {
		super(_conn);
	}

	public int ges_ges_col2_colecturiaConcepts_mtto(
			ColecturiaConceptTO parameters, int action) throws Exception {
		List v_resp;
		// s.setDbObject("{call sp_ges_col0_colecturiaconcept_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_ges_col2_colecturiaconcept_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(1, "_linenum,", parameters.getLinenum());
		this.setString(2, "_linestatus,", parameters.getLinestatus());
		this.setString(3, "_objtype,", parameters.getObjtype());
		this.setString(4, "_dscription,", parameters.getDscription());
		this.setString(5, "_acctcode,", parameters.getAcctcode());
		this.setString(6, "_acctcode2,", parameters.getAcctcode2());
		this.setString(7, "_acctcode3,", parameters.getAcctcode3());
		this.setString(8, "_ctlaccount,", parameters.getCtlaccount());
		this.setString(9, "_ocrcode,", parameters.getOcrcode());
		this.setInt(10, "_transid,", parameters.getTransid());
		this.setString(11, "_confirmed,", parameters.getConfirmed());
		this.setString(12, "_peymethod,", parameters.getPeymethod());
		this.setDouble(13, "_paidsum,", parameters.getPaidsum());
		this.setDouble(14, "_vatsum,", parameters.getVatsum());
		this.setString(15, "_docsubtype,", parameters.getDocsubtype());
		this.setString(16, "_value1,", parameters.getValue1());
		this.setString(17, "_value2,", parameters.getValue2());
		this.setString(18, "_value3,", parameters.getValue3());
		this.setString(19, "_taxstatus,", parameters.getTaxstatus());
		this.setString(20, "_aditional_account,", parameters.getAditional_account());
		this.setInt(21, "_action", action);
		v_resp = this.runQuery();
		return this.getInt();
	}

	public List get_ges_colecturiaConcept() throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{?=call sp_get_ges_colecturiaconcept()}");

		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					ColecturiaConceptTO colecturia = new ColecturiaConceptTO();
					colecturia.setLinenum(rowsetActual.getInt(1));
					colecturia.setLinestatus(rowsetActual.getString(2));
					colecturia.setObjtype(rowsetActual.getString(3));
					colecturia.setDscription(rowsetActual.getString(4));
					colecturia.setAcctcode(rowsetActual.getString(5));
					colecturia.setAcctcode2(rowsetActual.getString(6));
					colecturia.setAcctcode3(rowsetActual.getString(7));
					colecturia.setCtlaccount(rowsetActual.getString(8));
					colecturia.setOcrcode(rowsetActual.getString(9));
					colecturia.setTransid(rowsetActual.getInt(10));
					colecturia.setConfirmed(rowsetActual.getString(11));
					colecturia.setPeymethod(rowsetActual.getString(12));
					colecturia.setPaidsum(rowsetActual.getDouble(13));
					colecturia.setVatsum(rowsetActual.getDouble(14));
					colecturia.setDocsubtype(rowsetActual.getString(15));
					colecturia.setValue1(rowsetActual.getString(16));
					colecturia.setValue2(rowsetActual.getString(17));
					colecturia.setValue3(rowsetActual.getString(18));
					colecturia.setTaxstatus(rowsetActual.getString(19));
					colecturia.setAditional_account(rowsetActual.getString(20));

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
	
	
	public List get_ges_colecturiaConcept1(String code) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_businesspartnerconcept(?)}");
		this.setString(1, "_cardcode", code);
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					ColecturiaConceptTO colecturia = new ColecturiaConceptTO();
					colecturia.setLinenum(rowsetActual.getInt(1));
					colecturia.setLinestatus(rowsetActual.getString(2));
					colecturia.setObjtype(rowsetActual.getString(3));
					colecturia.setDscription(rowsetActual.getString(4));
					colecturia.setAcctcode(rowsetActual.getString(5));
					colecturia.setAcctcode2(rowsetActual.getString(6));
					colecturia.setAcctcode3(rowsetActual.getString(7));
					colecturia.setCtlaccount(rowsetActual.getString(8));
					colecturia.setOcrcode(rowsetActual.getString(9));
					colecturia.setTransid(rowsetActual.getInt(10));
					colecturia.setConfirmed(rowsetActual.getString(11));
					colecturia.setPeymethod(rowsetActual.getString(12));
					colecturia.setPaidsum(rowsetActual.getDouble(13));
					colecturia.setVatsum(rowsetActual.getDouble(14));
					colecturia.setDocsubtype(rowsetActual.getString(15));
					colecturia.setValue1(rowsetActual.getDouble(16)+"");
					colecturia.setValue2(rowsetActual.getString(17));
					colecturia.setValue3(rowsetActual.getString(18));
					colecturia.setTaxstatus(rowsetActual.getString(19));
					colecturia.setAditional_account(rowsetActual.getString(20));

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

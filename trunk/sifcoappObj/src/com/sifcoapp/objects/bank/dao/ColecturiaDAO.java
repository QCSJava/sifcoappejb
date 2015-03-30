package com.sifcoapp.objects.bank.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.bank.to.ColecturiaInTO;
import com.sifcoapp.objects.bank.to.ColecturiaTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.purchase.dao.PurchaseDetailDAO;
import com.sun.rowset.CachedRowSetImpl;

public class ColecturiaDAO extends CommonDAO {

	public ColecturiaDAO() {
		// TODO Auto-generated constructor stub
	}

	public int ges_ges_col0_colecturia_mtto(ColecturiaTO parameters, int action)
			throws Exception {
		List v_resp;
		// s.setDbObject("{call sp_ges_col0_colecturia_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_ges_col0_colecturia_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(1, "_docentry", new Integer(parameters.getDocentry()));
		this.setInt(2, "_transtype", new Integer(parameters.getTranstype()));
		this.setInt(3, "_docnum", new Integer(parameters.getDocnum()));
		this.setInt(4, "_receiptnum", new Integer(parameters.getReceiptnum()));
		this.setString(5, "_printed", parameters.getPrinted());
		// this.setDate(6,"_docdate", parameters.getDocdate());
		if (parameters.getDocdate() == null) {
			this.setDate(6, "_docdate", parameters.getDocdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getDocdate()
					.getTime());
			this.setDate(6, "_docdate", fecha);
		}
		// this.setDate(7,"_docduedate", parameters.getDocduedate());
		if (parameters.getDocduedate() == null) {
			this.setDate(7, "_docduedate", parameters.getDocduedate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getDocduedate()
					.getTime());
			this.setDate(7, "_docduedate", fecha);
		}
		this.setString(8, "_cardcode", parameters.getCardcode());
		this.setString(9, "_cardname", parameters.getCardname());
		this.setString(10, "_comments", parameters.getComments());
		this.setString(11, "_jrnlmemo", parameters.getJrnlmemo());
		this.setInt(12, "_series", new Integer(parameters.getSeries()));
		this.setDouble(13, "_doctotal", new Double(parameters.getDoctotal()));
		// this.setDate(14,"_taxdate", parameters.getTaxdate());
		if (parameters.getTaxdate() == null) {
			this.setDate(14, "_taxdate", parameters.getTaxdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getTaxdate()
					.getTime());
			this.setDate(14, "_taxdate", fecha);
		}
		this.setString(15, "_ref1", parameters.getRef1());
		this.setString(16, "_ref2", parameters.getRef2());
		this.setInt(17, "_usersign ", new Integer(parameters.getUsersign()));
		this.setInt(18, "_action", new Integer(action));
		v_resp = this.runQuery();
		return this.getInt();
	}

	public List get_ges_colecturia(ColecturiaInTO parameters) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// s.setDbObject("{call sp_get_ges_colecturia(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_get_ges_colecturia(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(1, "_docentry", new Integer(parameters.getDocentry()));
		this.setInt(2, "_transtype", new Integer(parameters.getTranstype()));
		this.setInt(3, "_docnum", new Integer(parameters.getDocnum()));
		this.setInt(4, "_receiptnum", new Integer(parameters.getReceiptnum()));
		this.setString(5, "_printed", parameters.getPrinted());
		// this.setDate(6,"_docdate", parameters.getDocdate());
		if (parameters.getDocdate() == null) {
			this.setDate(6, "_docdate", parameters.getDocdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getDocdate()
					.getTime());
			this.setDate(6, "_docdate", fecha);
		}
		// this.setDate(7,"_docduedate", parameters.getDocduedate());
		if (parameters.getDocduedate() == null) {
			this.setDate(7, "_docduedate", parameters.getDocduedate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getDocduedate()
					.getTime());
			this.setDate(7, "_docduedate", fecha);
		}
		this.setString(8, "_cardcode", parameters.getCardcode());
		this.setString(9, "_cardname", parameters.getCardname());
		this.setString(10, "_comments", parameters.getComments());
		this.setString(11, "_jrnlmemo", parameters.getJrnlmemo());
		this.setInt(12, "_series", new Integer(parameters.getSeries()));
		this.setDouble(13, "_doctotal", new Double(parameters.getDoctotal()));
		// this.setDate(14,"_taxdate", parameters.getTaxdate());
		if (parameters.getTaxdate() == null) {
			this.setDate(14, "_taxdate", parameters.getTaxdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getTaxdate()
					.getTime());
			this.setDate(14, "_taxdate", fecha);
		}
		this.setString(15, "_ref1", parameters.getRef1());
		this.setString(16, "_ref2", parameters.getRef2());

		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					ColecturiaTO colecturia = new ColecturiaTO();
					colecturia.setDocentry(rowsetActual.getInt(1));
					colecturia.setTranstype(rowsetActual.getInt(2));
					colecturia.setDocnum(rowsetActual.getInt(3));
					colecturia.setReceiptnum(rowsetActual.getInt(4));
					colecturia.setPrinted(rowsetActual.getString(5));
					colecturia.setDocdate(rowsetActual.getDate(6));
					colecturia.setDocduedate(rowsetActual.getDate(7));
					colecturia.setCardcode(rowsetActual.getString(8));
					colecturia.setCardname(rowsetActual.getString(9));
					colecturia.setComments(rowsetActual.getString(10));
					colecturia.setJrnlmemo(rowsetActual.getString(11));
					colecturia.setSeries(rowsetActual.getInt(12));
					colecturia.setDoctotal(rowsetActual.getDouble(13));
					colecturia.setTaxdate(rowsetActual.getDate(14));
					colecturia.setRef1(rowsetActual.getString(15));
					colecturia.setRef2(rowsetActual.getString(16));
					colecturia.setUsersign(rowsetActual.getInt(17));
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

	public ColecturiaTO get_ges_colecturiaByKey(int parameters)
			throws Exception {
		ColecturiaTO _return = new ColecturiaTO();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_ges_colecturia_by_key(?)}");
		this.setInt(1, "_docentry", parameters);

		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		ColecturiaDetailDAO Detail = new ColecturiaDetailDAO();
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					ColecturiaTO colecturia = new ColecturiaTO();
					colecturia.setDocentry(rowsetActual.getInt(1));
					colecturia.setTranstype(rowsetActual.getInt(2));
					colecturia.setDocnum(rowsetActual.getInt(3));
					colecturia.setReceiptnum(rowsetActual.getInt(4));
					colecturia.setPrinted(rowsetActual.getString(5));
					colecturia.setDocdate(rowsetActual.getDate(6));
					colecturia.setDocduedate(rowsetActual.getDate(7));
					colecturia.setCardcode(rowsetActual.getString(8));
					colecturia.setCardname(rowsetActual.getString(9));
					colecturia.setComments(rowsetActual.getString(10));
					colecturia.setJrnlmemo(rowsetActual.getString(11));
					colecturia.setSeries(rowsetActual.getInt(12));
					colecturia.setDoctotal(rowsetActual.getDouble(13));
					colecturia.setTaxdate(rowsetActual.getDate(14));
					colecturia.setRef1(rowsetActual.getString(15));
					colecturia.setRef2(rowsetActual.getString(16));
					colecturia.setUsersign(rowsetActual.getInt(17));
					colecturia.setColecturiaDetail(Detail.get_ges_colecturiaDetailByKey(rowsetActual.getInt(1)));
					_return = colecturia;
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

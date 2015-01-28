package com.sifcoapp.objects.sales.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.inventory.dao.GoodReceiptDetailDAO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptInTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
import com.sifcoapp.objects.sales.to.SalesInTO;
import com.sifcoapp.objects.sales.to.SalesTO;
import com.sun.rowset.CachedRowSetImpl;

public class SalesDAO extends CommonDAO{
	
	public List getSales(SalesInTO param) throws Exception{
		List _return = new Vector();
		List lstResultSet = null;
		
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_sales(?,?,?,?,?,?,?)}");
		
		if (param.getDocdate() == null){
			this.setDate(2, "_docdate", param.getDocdate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(param.getDocdate().getTime());
			this.setDate(2, "_docdate", fecha);
		}
		
		this.setInt(1, "_docnum", new Integer(param.getDocnum()));
		
		this.setInt(3, "_series", new Integer(param.getSeries()));
		this.setString(4, "_towhscode", param.getTowhscode());
		this.setString(5, "_ref1", param.getRef1());
		this.setString(6, "_ref2", param.getRef2());
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
	public SalesTO getSalesByKey(int docentry) throws Exception {
		SalesTO _return = new SalesTO();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_sales_by_key(?)}");
		this.setInt(1, "_docentry", new Integer(docentry));
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		SalesDetailDAO Detail = new SalesDetailDAO();
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					SalesTO sales= new SalesTO();
					sales.setDocentry(rowsetActual.getInt(1));
					sales.setDocnum(rowsetActual.getInt(2));
					sales.setDoctype(rowsetActual.getString(3));
					sales.setCanceled(rowsetActual.getString(4));
					sales.setDocstatus(rowsetActual.getString(5));
					sales.setObjtype(rowsetActual.getString(6));
					sales.setDocdate(rowsetActual.getDate(7));
					sales.setDocduedate(rowsetActual.getDate(8));
					sales.setCardcode(rowsetActual.getString(9));
					sales.setNumatcard(rowsetActual.getString(10));
					sales.setCardname(rowsetActual.getString(11));
					sales.setVatsum(rowsetActual.getString(12));
					sales.setDiscsum(rowsetActual.getDouble(13));
					sales.setDoctotal(rowsetActual.getDouble(14));
					sales.setRef1(rowsetActual.getString(15));
					sales.setRef2(rowsetActual.getString(16));
					sales.setComments(rowsetActual.getString(17));
					sales.setJrnlmemo(rowsetActual.getString(18));
					sales.setPaidtodate(rowsetActual.getDate(19));
					sales.setTransid(rowsetActual.getInt(20));
					sales.setReceiptnum(rowsetActual.getInt(21));
					sales.setGroupnum(rowsetActual.getInt(22));
					sales.setConfirmed(rowsetActual.getString(23));
					sales.setCreatetran(rowsetActual.getString(24));
					sales.setSeries(rowsetActual.getInt(25));
					sales.setTaxdate(rowsetActual.getDate(26));
					sales.setFiller(rowsetActual.getString(27));
					sales.setRounddif(rowsetActual.getDouble(28));
					sales.setRounding(rowsetActual.getString(29));
					sales.setCanceldate(rowsetActual.getDate(30));
					sales.setPeymethod(rowsetActual.getString(31));
					sales.setCtlaccount(rowsetActual.getString(32));
					sales.setBplname(rowsetActual.getString(33));
					sales.setVatregnum(rowsetActual.getString(34));
					sales.setPaidsum(rowsetActual.getDouble(35));
					sales.setTowhscode(rowsetActual.getString(36));
					sales.setNret(rowsetActual.getDouble(37));
					sales.setNamenp(rowsetActual.getString(38));
					sales.setQuedan(rowsetActual.getInt(39));
					sales.setFechreciva(rowsetActual.getDate(40));
					sales.setFquedan(rowsetActual.getDate(41));
					sales.setUsersign(rowsetActual.getInt(42));
					sales.setSalesDetails(Detail.getSalesDetail(rowsetActual.getInt(1)));
					_return=sales;
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
	
	public int inv_Sales_mtto(SalesTO parameters, int accion) throws Exception {

		List v_resp;
		// this.seObject("{call sp_inv_gre0_goodsrecei(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{? = call sp_sal0_sales_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
		if (parameters.getPaidtodate() == null){
			this.setDate(20,"_paidtodate", parameters.getPaidtodate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameters.getPaidtodate().getTime());
			this.setDate(20,"_paidtodate", fecha);
		}
		if (parameters.getTaxdate() == null){
			this.setDate(27,"_taxdate", parameters.getTaxdate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameters.getTaxdate().getTime());
			this.setDate(27,"_taxdate", fecha);
		}
		if (parameters.getCanceldate() == null){
			this.setDate(31,"_canceldate", parameters.getCanceldate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameters.getCanceldate().getTime());
			this.setDate(31,"_canceldate", fecha);
		}
		if (parameters.getFechreciva() == null){
			this.setDate(41,"_fechreciva", parameters.getFechreciva());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameters.getFechreciva().getTime());
			this.setDate(41,"_fechreciva", fecha);
		}
		if (parameters.getFquedan() == null){
			this.setDate(42,"_fquedan", parameters.getFquedan());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameters.getFquedan().getTime());
			this.setDate(42,"_fquedan", fecha);
		}
		this.setInt(2,"_docentry", new Integer(parameters.getDocentry()));
		this.setInt(3,"_docnum", new Integer(parameters.getDocnum()));
		this.setString(4,"_doctype", parameters.getDoctype());
		this.setString(5,"_canceled", parameters.getCanceled());
		this.setString(6,"_docstatus", parameters.getDocstatus());
		this.setString(7,"_objtype", parameters.getObjtype());		
		this.setString(10,"_cardcode", parameters.getCardcode());
		this.setString(11,"_numatcard", parameters.getNumatcard());
		this.setString(12,"_cardname", parameters.getCardname());
		this.setString(13,"_vatsum", parameters.getVatsum());
		this.setDouble(14,"_discsum", new Double(parameters.getDiscsum()));
		this.setDouble(15,"_doctotal", new Double(parameters.getDoctotal()));
		this.setString(16,"_ref1", parameters.getRef1());
		this.setString(17,"_ref2", parameters.getRef2());
		this.setString(18,"_comments", parameters.getComments());
		this.setString(19,"_jrnlmemo", parameters.getJrnlmemo());
		this.setInt(21,"_transid", new Integer(parameters.getTransid()));
		this.setInt(22,"_receiptnum", new Integer(parameters.getReceiptnum()));
		this.setInt(23,"_groupnum", new Integer(parameters.getGroupnum()));
		this.setString(24,"_confirmed", parameters.getConfirmed());
		this.setString(25,"_createtran", parameters.getCreatetran());
		this.setInt(26,"_series", new Integer(parameters.getSeries()));	
		this.setString(28,"_filler", parameters.getFiller());
		this.setDouble(29,"_rounddif", new Double(parameters.getRounddif()));
		this.setString(30,"_rounding", parameters.getRounding());	
		this.setString(32,"_peymethod", parameters.getPeymethod());
		this.setString(33,"_ctlaccount", parameters.getCtlaccount());
		this.setString(34,"_bplname", parameters.getBplname());
		this.setString(35,"_vatregnum", parameters.getVatregnum());
		this.setDouble(36,"_paidsum", new Double(parameters.getPaidsum()));
		this.setString(37,"_towhscode", parameters.getTowhscode());
		this.setDouble(38,"_nret", new Double(parameters.getNret()));
		this.setString(39,"_namenp", parameters.getNamenp());
		this.setInt(40,"_quedan", new Integer(parameters.getQuedan()));
		this.setInt(43,"_usersign", new Integer(parameters.getUsersign()));
		this.setInt(44, "_action", new Integer(accion));

		v_resp = this.runQuery();

		return this.getInt();
	}

}

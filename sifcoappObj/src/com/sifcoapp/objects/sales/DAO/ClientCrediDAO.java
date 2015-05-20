package com.sifcoapp.objects.sales.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.sales.to.*;
import com.sun.rowset.CachedRowSetImpl;

public class ClientCrediDAO extends CommonDAO{
	
	public List getClientCredi(ClientCrediInTO param) throws Exception{
		List _return = new Vector();
		List lstResultSet = null;
		
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_clientcreditnotes(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		
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
			this.setDate(8, "_docduedate", fecha);
		}
		this.setInt(1, "_docnum", new Integer(param.getDocnum()));
		
		this.setInt(3, "_series", new Integer(param.getSeries()));
		this.setString(4, "_towhscode", param.getTowhscode());
		this.setString(5, "_ref1", param.getRef1());
		this.setString(6, "_ref2", param.getRef2());
		this.setString(7, "_comments", param.getComments());
		this.setString(9,"_doctype", param.getDoctype());
		this.setString(10,"_docstatus", param.getDocstatus());
		this.setString(11,"_objtype", param.getObjtype());
		this.setString(12, "_cardcode", param.getCardcode());
		this.setString(13, "_cardname", param.getCardname());
		if (param.getTaxdate() == null){
			this.setDate(14, "_taxdate", param.getTaxdate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(param.getTaxdate().getTime());
			this.setDate(14, "_taxdate", fecha);
		}
		
		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					ClientCrediTO ClientCredi = new ClientCrediTO();
					ClientCredi.setDocentry(rowsetActual.getInt(1));
					ClientCredi.setDocnum(rowsetActual.getInt(2));
					ClientCredi.setDoctype(rowsetActual.getString(3));
					ClientCredi.setCanceled(rowsetActual.getString(4));
					ClientCredi.setDocstatus(rowsetActual.getString(5));
					ClientCredi.setObjtype(rowsetActual.getString(6));
					ClientCredi.setDocdate(rowsetActual.getDate(7));
					ClientCredi.setDocduedate(rowsetActual.getDate(8));
					ClientCredi.setCardcode(rowsetActual.getString(9));
					ClientCredi.setNumatcard(rowsetActual.getString(10));
					ClientCredi.setCardname(rowsetActual.getString(11));
					ClientCredi.setVatsum(rowsetActual.getDouble(12));
					ClientCredi.setDiscsum(rowsetActual.getDouble(13));
					ClientCredi.setDoctotal(rowsetActual.getDouble(14));
					ClientCredi.setRef1(rowsetActual.getString(15));
					ClientCredi.setRef2(rowsetActual.getString(16));
					ClientCredi.setComments(rowsetActual.getString(17));
					ClientCredi.setJrnlmemo(rowsetActual.getString(18));
					ClientCredi.setPaidtodate(rowsetActual.getDate(19));
					ClientCredi.setTransid(rowsetActual.getInt(20));
					ClientCredi.setReceiptnum(rowsetActual.getInt(21));
					ClientCredi.setGroupnum(rowsetActual.getInt(22));
					ClientCredi.setConfirmed(rowsetActual.getString(23));
					ClientCredi.setCreatetran(rowsetActual.getString(24));
					ClientCredi.setSeries(rowsetActual.getInt(25));
					ClientCredi.setTaxdate(rowsetActual.getDate(26));
					ClientCredi.setFiller(rowsetActual.getString(27));
					ClientCredi.setRounddif(rowsetActual.getDouble(28));
					ClientCredi.setRounding(rowsetActual.getString(29));
					ClientCredi.setCanceldate(rowsetActual.getDate(30));
					ClientCredi.setPeymethod(rowsetActual.getString(31));
					ClientCredi.setCtlaccount(rowsetActual.getString(32));
					ClientCredi.setBplname(rowsetActual.getString(33));
					ClientCredi.setVatregnum(rowsetActual.getString(34));
					ClientCredi.setPaidsum(rowsetActual.getDouble(35));
					ClientCredi.setTowhscode(rowsetActual.getString(36));
					ClientCredi.setNret(rowsetActual.getDouble(37));
					ClientCredi.setNamenp(rowsetActual.getString(38));
					ClientCredi.setQuedan(rowsetActual.getInt(39));
					ClientCredi.setFechreciva(rowsetActual.getDate(40));
					ClientCredi.setFquedan(rowsetActual.getDate(41));
					ClientCredi.setUsersign(rowsetActual.getInt(42));
					ClientCredi.setCreatedate(rowsetActual.getDate(43));
					ClientCredi.setCreatetime(rowsetActual.getInt(44));

					_return.add(ClientCredi);
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
	public ClientCrediTO getClientCrediByKey(int docentry) throws Exception {
		ClientCrediTO _return = new ClientCrediTO();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_clientcreditnotes_by_key(?)}");
		this.setInt(1, "_docentry", new Integer(docentry));
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		ClientCrediDetailDAO Detail = new ClientCrediDetailDAO();
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					ClientCrediTO ClientCredi= new ClientCrediTO();
					ClientCredi.setDocentry(rowsetActual.getInt(1));
					ClientCredi.setDocnum(rowsetActual.getInt(2));
					ClientCredi.setDoctype(rowsetActual.getString(3));
					ClientCredi.setCanceled(rowsetActual.getString(4));
					ClientCredi.setDocstatus(rowsetActual.getString(5));
					ClientCredi.setObjtype(rowsetActual.getString(6));
					ClientCredi.setDocdate(rowsetActual.getDate(7));
					ClientCredi.setDocduedate(rowsetActual.getDate(8));
					ClientCredi.setCardcode(rowsetActual.getString(9));
					ClientCredi.setNumatcard(rowsetActual.getString(10));
					ClientCredi.setCardname(rowsetActual.getString(11));
					ClientCredi.setVatsum(rowsetActual.getDouble(12));
					ClientCredi.setDiscsum(rowsetActual.getDouble(13));
					ClientCredi.setDoctotal(rowsetActual.getDouble(14));
					ClientCredi.setRef1(rowsetActual.getString(15));
					ClientCredi.setRef2(rowsetActual.getString(16));
					ClientCredi.setComments(rowsetActual.getString(17));
					ClientCredi.setJrnlmemo(rowsetActual.getString(18));
					ClientCredi.setPaidtodate(rowsetActual.getDate(19));
					ClientCredi.setTransid(rowsetActual.getInt(20));
					ClientCredi.setReceiptnum(rowsetActual.getInt(21));
					ClientCredi.setGroupnum(rowsetActual.getInt(22));
					ClientCredi.setConfirmed(rowsetActual.getString(23));
					ClientCredi.setCreatetran(rowsetActual.getString(24));
					ClientCredi.setSeries(rowsetActual.getInt(25));
					ClientCredi.setTaxdate(rowsetActual.getDate(26));
					ClientCredi.setFiller(rowsetActual.getString(27));
					ClientCredi.setRounddif(rowsetActual.getDouble(28));
					ClientCredi.setRounding(rowsetActual.getString(29));
					ClientCredi.setCanceldate(rowsetActual.getDate(30));
					ClientCredi.setPeymethod(rowsetActual.getString(31));
					ClientCredi.setCtlaccount(rowsetActual.getString(32));
					ClientCredi.setBplname(rowsetActual.getString(33));
					ClientCredi.setVatregnum(rowsetActual.getString(34));
					ClientCredi.setPaidsum(rowsetActual.getDouble(35));
					ClientCredi.setTowhscode(rowsetActual.getString(36));
					ClientCredi.setNret(rowsetActual.getDouble(37));
					ClientCredi.setNamenp(rowsetActual.getString(38));
					ClientCredi.setQuedan(rowsetActual.getInt(39));
					ClientCredi.setFechreciva(rowsetActual.getDate(40));
					ClientCredi.setFquedan(rowsetActual.getDate(41));
					ClientCredi.setUsersign(rowsetActual.getInt(42));
					ClientCredi.setclientDetails(Detail.getClientCrediDetail(rowsetActual.getInt(1)));
					_return=ClientCredi;
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
	
	public int inv_ClientCredi_mtto(ClientCrediTO parameters, int accion) throws Exception {
		Double DATO=0.00; //////////######## DATO QUEMADO###############
		List v_resp;
		// this.seObject("{call sp_inv_gre0_goodsrecei(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{? = call sp_clientcreditnotes_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
		this.setDouble(14,"_discsum",new Double(parameters.getDiscsum()));
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

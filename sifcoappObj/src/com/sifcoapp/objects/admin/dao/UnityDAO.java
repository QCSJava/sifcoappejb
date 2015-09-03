package com.sifcoapp.objects.admin.dao;

import com.sifcoapp.objects.admin.to.UnityTO;
import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.common.dao.CommonDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.GoodReceiptDetailDAO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptInTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
import com.sifcoapp.objects.sales.to.SalesInTO;
import com.sifcoapp.objects.sales.to.SalesTO;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;
import com.sun.rowset.CachedRowSetImpl;
public class UnityDAO extends CommonDAO {
	public int Unity_mtto(UnityTO parameter,int action)throws Exception{
	int v_resp;
		ResultOutTO _return = new ResultOutTO();
		//t.setDbObject("{call sp_inv_gis0_goodsissues_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_cat_uny0_unity(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setString(1, "_code", parameter.getCode());
		this.setString(2,"_record",parameter.getRecord());
		this.setString(3, "_license", parameter.getLicense());
		this.setString(4,"_card",parameter.getCard());
		this.setString(5, "_driver", parameter.getDriver());
		this.setString(6, "_cardcode",parameter.getCardcode());
		this.setString(7,"_cardname",parameter.getCardname());
		this.setString(8,"_notes",parameter.getNotes());
		this.setString(9,"_type",parameter.getType());
		this.setString(10,"_status",parameter.getStatus());
		this.setInt(11,"_year",parameter.getYear());
		this.setString(12,"_brand",parameter.getBrand());
		if (parameter.getDuedate() == null){
			this.setDate(13,"_duedate", parameter.getDuedate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameter.getDuedate().getTime());
			this.setDate(13,"_duedate", fecha);
		}
		
	    this.setString(14,"_dflaccount",parameter.getDflaccount());
		this.setString(15,"_relatedacc1",parameter.getRelatedacc1());
        this.setString(16, "_relatedacc2",parameter.getRelatedacc2());	
        this.setString(17,"_relatedacc3",parameter.getRelatedacc3());
        this.setString(18, "_relatedacc4",parameter.getRelatedacc4());
        if (parameter.getPurchasedate() == null){
			this.setDate(19,"_purchasedate", parameter.getPurchasedate());
		}else
		{
			java.sql.Date fecha= new java.sql.Date(parameter.getPurchasedate().getTime());
			this.setDate(19,"_purchasedate", fecha);
		}
        this.setString(20,"_usersign",parameter.getUsersign());
        this.setInt(21,"_action",action);
            
		v_resp = this.runUpdate();
		
		return v_resp;
		
	}
     
	public List getUnity() throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_unity()}");

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					UnityTO parameter = new UnityTO();
					parameter.setCode(rowsetActual.getString(1));
					parameter.setRecord(rowsetActual.getString(2));
					parameter.setLicense(rowsetActual.getString(3));
					parameter.setCard(rowsetActual.getString(4));
					parameter.setDriver(rowsetActual.getString(5));
					parameter.setCardcode(rowsetActual.getString(6));
					parameter.setCardname(rowsetActual.getString(7));
					parameter.setNotes(rowsetActual.getString(8));
					parameter.setType(rowsetActual.getString(9));
					parameter.setStatus(rowsetActual.getString(10));
					parameter.setYear(rowsetActual.getInt(11));
					parameter.setBrand(rowsetActual.getString(12));
					parameter.setDuedate(rowsetActual.getDate(13));
					parameter.setDflaccount(rowsetActual.getString(14));
					parameter.setRelatedacc1(rowsetActual.getString(15));
					parameter.setRelatedacc2(rowsetActual.getString(16));
					parameter.setRelatedacc3(rowsetActual.getString(17));
					parameter.setRelatedacc4(rowsetActual.getString(18));
					parameter.setPurchasedate(rowsetActual.getDate(19));
					parameter.setUsersign(rowsetActual.getInt(20));
					_return.add(parameter);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
	public UnityTO getUnity_bykey(int code) throws Exception {
		UnityTO _return = new UnityTO();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_unity_bykey(?)}");
		
		this.setInt(1, "_code", code);

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					UnityTO parameter = new UnityTO();
					parameter.setCode(rowsetActual.getString(1));
					parameter.setRecord(rowsetActual.getString(2));
					parameter.setLicense(rowsetActual.getString(3));
					parameter.setCard(rowsetActual.getString(4));
					parameter.setDriver(rowsetActual.getString(5));
					parameter.setCardcode(rowsetActual.getString(6));
					parameter.setCardname(rowsetActual.getString(7));
					parameter.setNotes(rowsetActual.getString(8));
					parameter.setType(rowsetActual.getString(9));
					parameter.setStatus(rowsetActual.getString(10));
					parameter.setYear(rowsetActual.getInt(11));
					parameter.setBrand(rowsetActual.getString(12));
					parameter.setDuedate(rowsetActual.getDate(13));
					parameter.setDflaccount(rowsetActual.getString(14));
					parameter.setRelatedacc1(rowsetActual.getString(15));
					parameter.setRelatedacc2(rowsetActual.getString(16));
					parameter.setRelatedacc3(rowsetActual.getString(17));
					parameter.setRelatedacc4(rowsetActual.getString(18));
					parameter.setPurchasedate(rowsetActual.getDate(19));
					parameter.setUsersign(rowsetActual.getInt(20));
					//_return.add(parameter);
					_return=parameter;
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

package com.sifcoapp.objects.catalog.dao;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;
import com.sun.rowset.CachedRowSetImpl;
public class BusinesspartnerDAO extends CommonDAO{
	
	//##################################  CRUD DE TABLA BUSINESSPARTNER ####################################
	public int inv_cat_bpa_businesspartner_mtto(BusinesspartnerTO parameters, int action) throws Exception {
		int v_resp = 0;
		// s.setDbObject("{call sp_gis1_goodsissuedetail_mtto   (1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_cat_bpa0_businesspartner_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setString(1,"_cardcode", parameters.getCardcode());
		this.setString(2,"_cardname", parameters.getCardname());
		this.setInt(3,"_groupcode",new Integer (parameters.getGroupcode()));
		this.setString(4,"_addid", parameters.getAddid());
		this.setString(5,"_notes", parameters.getNotes());
		this.setString(6,"_address", parameters.getAddress());
		this.setString(7,"_cardtype", parameters.getCardtype());
		this.setString(8,"_phone1", parameters.getPhone1());
		this.setString(9,"_phone2", parameters.getPhone2());
		this.setString(10,"_cellular", parameters.getCellular());
		this.setString(11,"_email", parameters.getEmail());
		this.setString(12,"_validfor", parameters.getValidfor());
		this.setDate(13,"_validfrom", parameters.getValidfrom());
		this.setDate(14,"_validto", parameters.getValidto());
		this.setString(15,"_nit", parameters.getNit());
		this.setString(16,"_vatgroup", parameters.getVatgroup());
		this.setInt(17, "_action", new Integer(action));
		v_resp = this.runUpdate();
		return v_resp;
	}
//RETORNA DE REGISTROS DE LA TABLA BUSINESSPARTNER POR FILTRO (5 FILTROS)
	public List get_businesspartner(BusinesspartnerInTO parameters)throws Exception {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_businesspartner(?,?,?,?,?)}");
		this.setString(1, "_cardcode", parameters.getCardcode());
		this.setString(2, "_cardname", parameters.getCardname());
		this.setInt(3, "_groupcode",new Integer(parameters.getGroupcode()));
		this.setString(4, "_cardtype", parameters.getCardtype());
		this.setString(5, "_nit", parameters.getNit());
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					BusinesspartnerTO account = new BusinesspartnerTO();
					account.setCardcode(rowsetActual.getString(1));
					account.setCardname(rowsetActual.getString(2));
					account.setGroupcode(rowsetActual.getInt(3));
					account.setAddid(rowsetActual.getString(4));
					account.setNotes(rowsetActual.getString(5));
					account.setAddress(rowsetActual.getString(6));
					account.setCardtype(rowsetActual.getString(7));
					account.setPhone1(rowsetActual.getString(8));
					account.setPhone2(rowsetActual.getString(9));
					account.setCellular(rowsetActual.getString(10));
					account.setEmail(rowsetActual.getString(11));
					account.setValidfor(rowsetActual.getString(12));
					account.setValidfrom(rowsetActual.getDate(13));
					account.setValidto(rowsetActual.getDate(14));
					account.setNit(rowsetActual.getString(15));
					account.setVatgroup(rowsetActual.getString(16));

					_return.add(account);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
	
	//RETORNA DE LA TABLA BUSINEESPARTNER UN REGISTRO POR CLAVE
	public BusinesspartnerTO get_businesspartnerByKey(String parameters)throws Exception {
		BusinesspartnerTO _return = new BusinesspartnerTO();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_businesspartner_by_key(?)}");
		this.setString(1, "_cardcode", parameters);
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					BusinesspartnerTO account = new BusinesspartnerTO();
					account.setCardcode(rowsetActual.getString(1));
					account.setCardname(rowsetActual.getString(2));
					account.setGroupcode(rowsetActual.getInt(3));
					account.setAddid(rowsetActual.getString(4));
					account.setNotes(rowsetActual.getString(5));
					account.setAddress(rowsetActual.getString(6));
					account.setCardtype(rowsetActual.getString(7));
					account.setPhone1(rowsetActual.getString(8));
					account.setPhone2(rowsetActual.getString(9));
					account.setCellular(rowsetActual.getString(10));
					account.setEmail(rowsetActual.getString(11));
					account.setValidfor(rowsetActual.getString(12));
					account.setValidfrom(rowsetActual.getDate(13));
					account.setValidto(rowsetActual.getDate(14));
					account.setNit(rowsetActual.getString(15));
					account.setVatgroup(rowsetActual.getString(16));
					_return=account;
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

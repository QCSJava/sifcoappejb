package com.sifcoapp.objects.admin.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.ejb.EJBException;

import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesPriceTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.admin.to.PricesListInTO;
import com.sifcoapp.objects.admin.to.PricesListTO;
import com.sifcoapp.objects.admin.to.TablesCatalogTO;
import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.GoodsissuesDetailDAO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;
import com.sifcoapp.objects.transaction.to.InventoryLogTO;
import com.sifcoapp.objects.transaction.to.InventorylogInTo;
import com.sifcoapp.objects.transaction.to.WarehouseJournalLayerTO;
import com.sifcoapp.objects.transaction.to.WarehouseJournalTO;
import com.sun.rowset.CachedRowSetImpl;

public class ParameterDAO extends CommonDAO{
	public ParameterDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParameterDAO(Connection _conn) {
		super(_conn);
		// TODO Auto-generated constructor stub
	}

	
	public int parameter_mtto(parameterTO parameter,int action)throws Exception{
	int v_resp;
		ResultOutTO _return = new ResultOutTO();
		// t.setDbObject("{call sp_inv_gis0_goodsissues_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_amd_par0_parameter_mtto(?,?,?,?,?,?,?)}");
		this.setInt(1,"_parametercode", parameter.getParametercode());
		this.setString(2,"_parametername ", parameter.getParametername());
		this.setString(3,"_value1 ", parameter.getValue1());
		this.setString(4,"_value2 ", parameter.getValue2());
		this.setDate(5,"_value3 ", parameter.getValue3());
		this.setInt(6,"_usersign ", parameter.getUsersign());
		this.setInt(7,"_action ", action);
    
		v_resp = this.runUpdate();
		
		return v_resp;
		
	}
	
	public List getParameter() throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{?= call sp_get_parameter()}");

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					parameterTO parameter = new parameterTO();
					
					parameter.setParametercode(rowsetActual.getInt(1));
					parameter.setParametername(rowsetActual.getString(2));
					parameter.setValue1(rowsetActual.getString(3));
					parameter.setValue2(rowsetActual.getString(4));
					parameter.setValue3(rowsetActual.getString(5));
					parameter.setUsersign(rowsetActual.getInt(6));
					
					
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
	
	public parameterTO getParameterbykey(int code)throws Exception{
		parameterTO _return = new parameterTO();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_parameter_by_key(?)}");
		this.setInt(1, "_code", code);

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					parameterTO parameter = new parameterTO();
					parameter.setParametercode(rowsetActual.getInt(1));
					parameter.setParametername(rowsetActual.getString(2));
					parameter.setValue1(rowsetActual.getString(3));
					parameter.setValue2(rowsetActual.getString(4));
					parameter.setValue3(rowsetActual.getString(5));
					parameter.setUsersign(rowsetActual.getInt(6));
					
					_return = parameter;
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

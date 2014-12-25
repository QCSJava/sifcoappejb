package com.sifcoapp.objects.accounting.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sun.rowset.CachedRowSetImpl;

public class AccountingDAO extends CommonDAO {
	/*
	 * Mantenimiento de periodos contables
	 */
	public int cat_accPeriod_mtto(AccPeriodTO parameters, int action) {

		int v_resp = 0;
		// this.setDbObject("{call sp_cat_acc_period_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_cat_acc_period_mtto(?,?,?,?,?,?,?,?,?,?,?)}");
		this.setString(1, "_acccode", parameters.getAcccode());
		this.setString(2, "_accname", parameters.getAccname());
		this.setDate(3, "_f_refdate", parameters.getF_refdate());
		this.setDate(4, "_t_refdate", parameters.getT_refdate());
		this.setDate(5, "_f_duedate", parameters.getF_duedate());
		this.setDate(6, "_t_duedate", parameters.getT_duedate());
		this.setDate(7, "_f_taxdate", parameters.getF_taxdate());
		this.setDate(8, "_t_taxdate", parameters.getT_taxdate());
		this.setInt(9, "_periodstat", new Integer(parameters.getPeriodstat()));
		this.setInt(10, "_usersign", new Integer(parameters.getUsersign()));
		this.setInt(11, "_action", new Integer(action));

		v_resp = this.runUpdate();

		return v_resp;
	}

	public List getAccPeriods() {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{? = call sp_get_acc_period()}");		

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					AccPeriodTO period = new AccPeriodTO();
					period.setAbsentry(rowsetActual.getInt(1));
					period.setAcccode(rowsetActual.getString(2));
					period.setAccname(rowsetActual.getString(3));
					period.setF_refdate(rowsetActual.getDate(4));
					period.setT_refdate(rowsetActual.getDate(5));
					period.setF_duedate(rowsetActual.getDate(6));
					period.setT_duedate(rowsetActual.getDate(7));
					period.setF_taxdate(rowsetActual.getDate(8));
					period.setT_taxdate(rowsetActual.getDate(9));
					period.setPeriodstat(rowsetActual.getInt(10));
					period.setUsersign(rowsetActual.getInt(11));			
					_return.add(period);
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

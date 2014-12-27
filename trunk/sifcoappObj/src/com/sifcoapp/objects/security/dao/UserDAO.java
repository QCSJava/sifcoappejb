package com.sifcoapp.objects.security.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.common.to.DBManager;
import com.sifcoapp.objects.security.to.ProfileInTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;
import com.sifcoapp.objects.security.to.UserTO;
import com.sun.rowset.CachedRowSetImpl;

public class UserDAO extends CommonDAO {
	private DBManager dbManager;

	public UserDAO() {
		super();
	}

	public UserAppOutTO getUserValid(UserAppInTO parameters) {

		UserAppOutTO v_return = new UserAppOutTO();
		List lstResultSets = new Vector();
		System.out.println("Desde DAO");

		this.setDbObject("{? = call sp_valid_usr(?,?)}");
		// this.setString(1, "return");
		this.setString(2, "USRNAME", parameters.getIdUserApp());
		this.setString(3, "USRPSW", parameters.getPasswordUserApp());

		lstResultSets = this.runQuery();
		System.out.println("return psg");
		System.out.println(this.getInt());
		v_return.setValidUser(this.getInt());
		// return this.getIntReturn();

		return v_return;

	}

	public ProfileOutTO getUserProfiles(ProfileInTO parameters) {

		ProfileOutTO v_return = new ProfileOutTO();
		List lstResultSets = new Vector();
		System.out.println("Desde DAO");
		this.setTypeReturn(Common.TYPERETURN_RESULTSET);
		this.setDbObject("{call sp_get_usr_profiles(?)}");
		// this.setString(1, "return");
		this.setInt(1, "USRNAME", new Integer(1));

		lstResultSets = this.runQuery();
		System.out.println("return psg");
		// System.out.println(this.getInt());

		// return this.getIntReturn();

		return v_return;

	}

	public int cat_users_mtto(UserTO parameters, int action) {
		int v_resp = 0;
		// t.setDbObject("{call sp_cat_users_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_cat_users_mtto(?,?,?,?,?,?,?,?,?)}");
		this.setInt(1, "_usersign", parameters.getUsersign());
		this.setString(2, "_nickname", parameters.getNickname());
		this.setString(3, "_username", parameters.getUsername());
		this.setString(4, "_lastname", parameters.getLastname());
		this.setString(5, "_password", parameters.getPassword());
		this.setInt(6, "_profilecode", parameters.getProfilecode());
		this.setString(7, "_locked", parameters.getLocked());
		this.setInt(8, "_cusersign", parameters.getCusersign());
		this.setInt(9, "_action", new Integer(action));
		v_resp = this.runUpdate();
		return v_resp;
	}
	
	public List getUser() {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// thsetDbObject("{call sp_get_user(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{?=call sp_get_user()}");

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					UserTO user = new UserTO();
					user.setUsersign(rowsetActual.getInt(1));
					user.setNickname(rowsetActual.getString(2));
					user.setUsername(rowsetActual.getString(3));
					user.setLastname(rowsetActual.getString(4));
					user.setPassword(rowsetActual.getString(5));
					user.setProfilecode(rowsetActual.getInt(6));
					user.setLocked(rowsetActual.getString(7));
					user.setUserdate(rowsetActual.getDate(8));
					user.setCusersign(rowsetActual.getInt(9));
					_return.add(user);
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

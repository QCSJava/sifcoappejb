package com.sifcoapp.objects.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.security.ejb.SecurityEJBRemote;

public class commonDAO {
	private Connection conn;
	private static final String DB_JDBC = "jdbc/sifcoDBJDBC";
	
	private static Context context = null;
	private int istransaccional;
	public commonDAO() {

		if (conn == null) {
			try {

				//Class.forName(DB_DRIVER);
				context=ClientUtility.getInitialContext();
				DataSource datasource=(DataSource)context.lookup(DB_JDBC);
				this.conn=datasource.getConnection();

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		
	}
	public void closeConnection(){
		try {
			if (this.getIstransaccional()==Common.C_FALSE){
				this.conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static java.sql.Timestamp getCurrentTimeStamp() {
		 
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
 
	}
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public int getIstransaccional() {
		return istransaccional;
	}
	public void setIstransaccional(int istransaccional) {
		this.istransaccional = istransaccional;
	}
}

package com.sifcoapp.objects.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.security.ejb.SecurityEJBRemote;

public class commonDAO {
	private Connection conn;
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://127.0.0.1:5432/sifcoDB";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "admin";
	private static Context context = null;
	public commonDAO() {

		if (conn == null) {
			try {

				//Class.forName(DB_DRIVER);
				context=ClientUtility.getInitialContext();
				DataSource datasource=(DataSource)context.lookup("jdbc/sifcoDBJDBC");
				conn=datasource.getConnection();

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*try {

				this.conn = DriverManager.getConnection(
						DB_CONNECTION, "postgres",
						"admin");
				
			} catch (SQLException e) {

				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return;

			}*/
		}

		
	}
	public void closeConnection(){
		try {
			this.conn.close();
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
}

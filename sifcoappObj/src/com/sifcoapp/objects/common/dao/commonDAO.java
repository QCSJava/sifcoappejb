package com.sifcoapp.objects.common.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.DetailParameter;
import com.sifcoapp.security.ejb.SecurityEJBRemote;

public class commonDAO {
	private Connection conn;
	private static final String DB_JDBC = "jdbc/sifcoDBJDBC";
	private static final String CALLABLETYPE="CALLABLE";
	private static final String PREPAREDTYPE="PREPARED";
	private static Context context = null;
	private boolean istransaccional=false;
	private String typeStatement=null;
	private Hashtable inParameters;
	private Hashtable outParameters;
	private String dbObject;
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
			if (this.getIstransaccional()==false){
				this.conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 
	 */
	private static java.sql.Timestamp getCurrentTimeStamp() {
		 
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
 
	}
	
	private void insertParam(int position, String colName, Object colValue, String colType){
					
		this.inParameters.put(new Integer(position), (new DetailParameter(position,colName,colValue)));
		
	}
	
	
	/*
	 * 
	 */
	public void setString(int position, String colName, Object colValue){
		this.insertParam(position, colName, colValue,Common.TYPESTRING);
	}
	
	/*
	 * 
	 */
	public void setInt(int position, String colName, Object colValue){
		this.insertParam(position, colName, colValue, Common.TYPEINT);
	}
	
	/*
	 * 
	 */
	public void setBigDecimal(int position, String colName, Object colValue){
		this.insertParam(position, colName, colValue, Common.TYPEBIGDECIMAL);
	}
	public Connection getConn() {
		return conn;
	}

	/*
	 * 
	 */
	public void runUpdate(){
		
	}
	
	public void runQuery(){
		
	}
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public boolean getIstransaccional() {
		return istransaccional;
	}
	public void setIstransaccional(boolean istransaccional) {
		this.istransaccional = istransaccional;
	}
	public String getDbObject() {
		return dbObject;
	}
	public void setDbObject(String dbObject) {
		this.dbObject = dbObject;
	}
}

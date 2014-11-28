package com.sifcoapp.objects.common.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.DetailParameter;
import com.sifcoapp.security.ejb.SecurityEJBRemote;
import com.sun.rowset.CachedRowSetImpl;

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

	private String typeReturn;
	
	public commonDAO() {

		this.getConnectionDB();
		this.inParameters=new Hashtable();
		this.outParameters=new Hashtable();
		
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
					
		DetailParameter detailParameter=new DetailParameter(position,colName,colValue,colType);
		
		this.inParameters.put(new Integer(position), detailParameter);
		
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
		
		//Primero creamos el prepareStatement
		
		CallableStatement statementToExecute = null; 
		boolean v_haveResultsets=false;
		ResultSet rsData = null;
		CachedRowSet crsData = null;
		List rsDataList=new Vector();
		
		if (this.getTypeReturn()==null){
			this.setTypeReturn(Common.TYPERETURN_INT);
		}
		
		try {
			statementToExecute	= this.getConn().prepareCall(this.getDbObject());
			
			//asignamos parametros
			
			Enumeration enParameters = this.inParameters.keys();
			DetailParameter dtParameterTmp = null;
			Integer v_position=null;
			//Recorremos hash con parametros previamente seteados
			while(enParameters.hasMoreElements()){
				
				v_position=(Integer)enParameters.nextElement();
				
				dtParameterTmp=(DetailParameter)this.inParameters.get(v_position);			
				
				System.out.println("detail: position "+v_position);
				System.out.println("detail: getColName "+dtParameterTmp.getColName());
				System.out.println("detail: getColValue "+dtParameterTmp.getColValue());
				System.out.println("detail: getColType " );
				System.out.println( dtParameterTmp.getColType());
				
				
				if (dtParameterTmp.getColType().equalsIgnoreCase(Common.TYPESTRING)){
					statementToExecute.setString(v_position.intValue(), (String)dtParameterTmp.getColValue());
				}
				
				if (dtParameterTmp.getColType().equalsIgnoreCase(Common.TYPEINT)){
					statementToExecute.setInt(v_position.intValue(), ((Integer)dtParameterTmp.getColValue()).intValue());
				}
				
			}
			if (this.getTypeReturn().equals(Common.TYPERETURN_INT)){
				statementToExecute.registerOutParameter(1,Types.INTEGER);	
			}
			
			v_haveResultsets=statementToExecute.execute();
			//System.out.println("resultado");
			//System.out.println(statementToExecute.getInt(1));
			if (this.getTypeReturn().equals(Common.TYPERETURN_INT)){
				this.outParameters.put(new Integer(1), new Integer(statementToExecute.getInt(1)));
			}
			
			//analizando resultsets recibidos
			System.out.println("v_haveResultsets");
			System.out.println(v_haveResultsets);
			if (v_haveResultsets){
				System.out.println("tiene resultsets");
				rsData=statementToExecute.getResultSet();
				if (rsData != null) {
					rsData.clearWarnings();
					crsData = new CachedRowSetImpl ();
					crsData.populate (rsData);						
					rsDataList.add (crsData);
					//closeToDB (rsData);
					rsData = null;
					crsData = null;
				}
				
				
			}
			
			//Cerramos la conexion
			statementToExecute.close();
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
		
	}
	
	private void getConnectionDB(){
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
	
	public int getInt(){
		int returnCode = 0;
		
		returnCode=((Integer)this.outParameters.get(new Integer(1))).intValue();
		
		
		return returnCode;
	}
	public String getTypeReturn() {
		return typeReturn;
	}
	public void setTypeReturn(String typeReturn) {
		this.typeReturn = typeReturn;
	}
	
}

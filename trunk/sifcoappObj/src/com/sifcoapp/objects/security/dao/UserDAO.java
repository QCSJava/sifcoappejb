package com.sifcoapp.objects.security.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import com.sifcoapp.objects.common.dao.commonDAO;
import com.sifcoapp.objects.common.to.DBManager;

public class UserDAO extends commonDAO {
	private DBManager dbManager;
	
	public UserDAO(){
		super();
	}
	
	public int getUserValid(){
		
		System.out.println("Desde DAO");
		
		this.setDbObject("{? = call sp_valid_usr(?,?)}");
		//this.setString(1, "return");
		this.setString(2, "USRNAME", "ALONSO");
		this.setString(3, "USRPSW", "ALONSOPSW");
		
		this.runQuery();
		System.out.println(this.getInt());
		return this.getIntReturn();
	}

}

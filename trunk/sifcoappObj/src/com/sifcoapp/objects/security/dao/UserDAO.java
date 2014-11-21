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
		
		this.setDbObject("sp_valid_usr");
		this.setString(1, "USRNAME", "ALONSO");
		this.setString(2, "USRPSW", "ALONSOPSW");
		
		this.runQuery();
		
		return 0;
	}

}

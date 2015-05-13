package com.sifcoapp.objects.admin.dao;

import com.sifcoapp.objects.admin.to.UnityTO;
import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.common.dao.CommonDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.GoodReceiptDetailDAO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptInTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
import com.sifcoapp.objects.sales.to.SalesInTO;
import com.sifcoapp.objects.sales.to.SalesTO;
import com.sun.rowset.CachedRowSetImpl;
public class UnityDAO extends CommonDAO {
	public int Unity_mtto(UnityTO parameter,int action)throws Exception{
	int v_resp;
		ResultOutTO _return = new ResultOutTO();
		//t.setDbObject("{call sp_inv_gis0_goodsissues_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_amd_par0_parameter_mtto(?,?,?,?,?,?,?)}");
		this.setString(1, "_code", parameter.getCode());
		this.setString(2,"_record",parameter.getRecord());
		this.setString(3, "_license", parameter.getLicense());
		this.setString(4,"_card",parameter.getCard());
		this.setString(5, "_driver", parameter.getDriver());
		/*private String driver;
		private String cardcode;
		private String cardname;
		private String notes;
		private String type;
		private String status;
		private int year;
		private String brand;
		private Date duedate;
		private String dflaccount;
		private String relatedacc1;
		private String relatedacc2;
		private String relatedacc3;
		private String relatedacc4;
		private Date purchasedate;
		private int usersign;*/
    
		v_resp = this.runUpdate();
		
		return v_resp;
		
	}
}

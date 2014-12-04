package com.sifcoapp.objects.admin.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sun.rowset.CachedRowSetImpl;

public class AdminDAO extends CommonDAO{
	/*
	 * Retorna un catalogo specifico de la base de datos
	 */
	public List findCatalog(String nameCatalog){
		
		List lstResult=new Vector();
		List lstResultSet=null;
		
		System.out.println("Desde DAO");
		this.setTypeReturn(Common.TYPERETURN_RESULTSET);
		this.setDbObject("{call sp_get_catalog(?)}");
		//this.setString(1, "return");
		this.setString(1, "IN_CAT_NAME",nameCatalog);
		 		
		lstResultSet=this.runQuery();
		System.out.println("return psg");
		
		CachedRowSetImpl	rowsetActual;
		
		ListIterator 		liRowset 		= null;
		liRowset=lstResultSet.listIterator();
		//Iterator<CachedRowSetImpl> iterator = lstResult.iterator();
		while (liRowset.hasNext()) {
			//System.out.println(iterator.next());
			//CatalogTO catalogTO=(CatalogTO)iterator.next();
			//System.out.println("->"+catalogTO.getValueCatlg());
			rowsetActual=(CachedRowSetImpl) liRowset.next();
			
			try {
				while(rowsetActual.next()){
					System.out.println(rowsetActual.getString(1));
					System.out.println(rowsetActual.getString(2));
					System.out.println(rowsetActual.getString(3));
					
					lstResult.add(new CatalogTO(rowsetActual.getInt(1),rowsetActual.getString(2),rowsetActual.getString(3)));
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return lstResult;
				
	}
	
	/*
	 * Actualiza los datos de la empresa
	 */
	
	public int updEnterprise(EnterpriseTO parameters){
		
		int v_resp=0;
		
		this.setDbObject("{call sp_upd_enterprise(?,?,?,?,?,?,?,?,?,?)}");
		
		this.setString(1, "_name",parameters.getCompnyName());
		this.setString(2, "_addr",parameters.getCompnyAddr());
		this.setString(3, "_country",parameters.getCountry_catalog());
		this.setString(4, "_printheadr",parameters.getCrintHeadr());
		this.setString(5, "_phone1",parameters.getPhone1());
		this.setString(6, "_phone2",parameters.getPhone2());
		this.setString(7, "_fax",parameters.getFax());
		this.setString(8, "_e_mail",parameters.getE_Mail());
		this.setString(9, "_manager",parameters.getManager());
		this.setString(10, "_taxidnum",parameters.getTaxIdNum());
		v_resp=this.runUpdate();
		
		return v_resp;
		
		
	}
}

package com.sifcoapp.objects.admin.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.commonDAO;
import com.sun.rowset.CachedRowSetImpl;

public class AdminDAO extends commonDAO{
	public List findCatalog(String nameCatalog){
		
		List lstResult=null;
		
		System.out.println("Desde DAO");
		this.setTypeReturn(Common.TYPERETURN_RESULTSET);
		this.setDbObject("{call sp_get_catalog(?)}");
		//this.setString(1, "return");
		this.setString(1, "IN_CAT_NAME",nameCatalog);
		 		
		lstResult=this.runQuery();
		System.out.println("return psg");
		
		CachedRowSetImpl	rowsetActual;
		
		ListIterator 		liRowset 		= null;
		liRowset=lstResult.listIterator();
		//Iterator<CachedRowSetImpl> iterator = lstResult.iterator();
		while (liRowset.hasNext()) {
			//System.out.println(iterator.next());
			//CatalogTO catalogTO=(CatalogTO)iterator.next();
			//System.out.println("->"+catalogTO.getValueCatlg());
			rowsetActual=(CachedRowSetImpl) liRowset.next();
			
			try {
				System.out.println(rowsetActual.getString(1));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return lstResult;
				
	}
}
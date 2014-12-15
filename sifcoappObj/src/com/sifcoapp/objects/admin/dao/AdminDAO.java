package com.sifcoapp.objects.admin.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.admin.to.TablesCatalogTO;
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
			
			rowsetActual=(CachedRowSetImpl) liRowset.next();
			
			try {
				while(rowsetActual.next()){
					/*System.out.println(rowsetActual.getString(1));
					System.out.println(rowsetActual.getString(2));
					System.out.println(rowsetActual.getString(3));*/
					
					lstResult.add(new CatalogTO(rowsetActual.getString(1),rowsetActual.getInt(2),rowsetActual.getString(3),rowsetActual.getString(4),rowsetActual.getInt(5)));
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
	
	/*
	 * Obtiene la informacion de la empresa
	 */
	public EnterpriseTO getEnterpriseInfo(int enterpriseCode) {
		
		List lstResult=new Vector();
		List lstResultSet=null;
		EnterpriseTO _return=new EnterpriseTO();
		
		System.out.println("Desde DAO");
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{? = call sp_get_enterprise_info()}");
				 		
		lstResultSet=this.runQuery();
		System.out.println("return psg");
		
		CachedRowSetImpl	rowsetActual;
		
		ListIterator 		liRowset 		= null;
		liRowset=lstResultSet.listIterator();
		//Iterator<CachedRowSetImpl> iterator = lstResult.iterator();
		while (liRowset.hasNext()) {
			
			rowsetActual=(CachedRowSetImpl) liRowset.next();
			
			try {
				while(rowsetActual.next()){
					
					_return.setCode(rowsetActual.getInt(1));
					_return.setCompnyName(rowsetActual.getString(2));
					_return.setCompnyAddr(rowsetActual.getString(3));
					_return.setCountry_catalog(rowsetActual.getString(4));
					_return.setCrintHeadr(rowsetActual.getString(5));
					_return.setPhone1(rowsetActual.getString(6));
					_return.setPhone2(rowsetActual.getString(7));
					_return.setFax(rowsetActual.getString(8));
					_return.setE_Mail(rowsetActual.getString(9));
					_return.setManager(rowsetActual.getString(10));
					_return.setTaxIdNum(rowsetActual.getString(11));					
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		return _return;
	}
	
	/*
	 * Obtiene los registros del catalogo de tablas del sistema
	 * @author Rutilio
	 */	
	public List getTablesCatalog(){
		List _return=new Vector();
		List lstResultSet=null;
		TablesCatalogTO _returnTO=new TablesCatalogTO();
		
		System.out.println("Desde DAO");
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{? = call sp_get_tables_catalog()}");
				 		
		lstResultSet=this.runQuery();
			
		CachedRowSetImpl	rowsetActual;
		
		ListIterator 		liRowset 		= null;
		liRowset=lstResultSet.listIterator();
		//Iterator<CachedRowSetImpl> iterator = lstResult.iterator();
		while (liRowset.hasNext()) {
			rowsetActual=(CachedRowSetImpl) liRowset.next();
			
			try {
				while(rowsetActual.next()){
					_return.add(new TablesCatalogTO(rowsetActual.getInt(1),rowsetActual.getString(2),rowsetActual.getString(3)));
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
	
	/*
	 * Mantenimiento tabla de catalogos de sistema
	 */
	public int cat_tab1_catalogos_mtto(CatalogTO parameters, int action){
		int _return;
			
		this.setDbObject("{call sp_cat_tab1_catalogos_mtto(?,?,?,?,?,?)}");
		
		this.setString(1, "_catcode",parameters.getCatcode());
		this.setInt(2, "_tablecode",new Integer(parameters.getTablecode()));
		this.setString(3, "_catvalue",parameters.getCatvalue());
		this.setString(4, "_catstatus",parameters.getCatstatus());
		this.setInt(5, "_usersign",new Integer(parameters.getUsersign()));
		this.setInt(6, "_action",new Integer(action));
		
		_return=this.runUpdate();
							
		return _return;
	}
	
	/*
	 * Guarda los cambios en los articulos
	 */
	public int saveArticle(ArticlesTO parameters) {

		int v_resp = 0;

		this.setDbObject("{call sp_upd_enterprise(?,?,?,?,?,?,?,?,?,?)}");

		this.setString(1,"_itemcode", parameters.getItemCode());
		this.setString(2,"_itemname", parameters.getItemName());
		this.setString(3,"_itemtype", parameters.getItemType());
		this.setInt(4,"_itmsgrpcod", new Integer(parameters.getItmsGrpCod ()));
		this.setString(5,"_vatliable", parameters.getVatLiable());
		this.setString(6,"_codebars", parameters.getCodeBars());
		this.setString(7,"_prchseitem", parameters.getPrchseItem ());
		this.setString(8,"_sellitem", parameters.getSellItem());
		this.setString(9,"_invntitem", parameters.getInvntItem());
		this.setString(10,"_assetitem", parameters.getAssetItem());
		this.setString(11,"_cardcode", parameters.getCardCode());
		this.setString(12,"_buyunitmsr", parameters.getBuyUnitMsr());
		this.setDouble(13,"_numinbuy", parameters.getNumInBuy());
		this.setString(14,"_salunitmsr", parameters.getSalUnitMsr());
		this.setDouble(15,"_salpackun", parameters.getSalPackUn());
		this.setString(16,"_suppcatnum", parameters.getSuppCatNum());
		this.setDouble(17,"_purpackun", parameters.getSalPackUn());
		this.setDouble(18,"_avgprice", parameters.getAvgPrice());
		this.setDouble(19,"_onhand", parameters.getOnHand());
		this.setString(20,"_validfor", parameters.getValidFor());
		this.setString(21,"_validfrom", parameters.getValidFrom());
		this.setString(22,"_validto", parameters.getValidTo());
		this.setString(23,"_invntryuom", parameters.getInvntryUom());
		this.setDouble(24,"_numinsale", parameters.getNumInSale());
		this.setString(25,"_dfltwh", parameters.getDfltWH());
		this.setString(26,"_wtliable", parameters.getWtliable());
		this.setString(27,"_sww", parameters.getSww());
		this.setString(28,"_validcomm", parameters.getValidComm());
		this.setInt(29,"_usersign", parameters.getUserSign());
		this.setInt(29, "_action", new Integer("1"));

		v_resp = this.runUpdate();

		return v_resp;

	}
	
}

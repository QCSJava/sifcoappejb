package com.sifcoapp.objects.security.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;
import java.util.Vector;

import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.TablesCatalogTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.common.to.DBManager;
import com.sifcoapp.objects.common.to.DetailParameter;
import com.sifcoapp.objects.security.to.ProfileDetOutTO;
import com.sifcoapp.objects.security.to.ProfileInTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;
import com.sun.rowset.CachedRowSetImpl;

public class UserDAO extends CommonDAO {
	private DBManager dbManager;
	
	public UserDAO(){
		super();
	}
	
	public UserAppOutTO getUserValid(UserAppInTO parameters){
		
		UserAppOutTO v_return=new UserAppOutTO();  
		List lstResultSets=new Vector();
		System.out.println("Desde DAO");
		
		this.setDbObject("{? = call sp_valid_usr(?,?)}");
		//this.setString(1, "return");
		this.setString(2, "USRNAME",parameters.getIdUserApp());
		this.setString(3, "USRPSW", parameters.getPasswordUserApp());
		
		lstResultSets=this.runQuery();
		System.out.println("return psg");
		System.out.println(this.getInt());
		v_return.setValidUser(this.getInt());
		//return this.getIntReturn();
		
		return v_return;
		
	}
	
	public ProfileOutTO getUserProfiles(ProfileInTO parameters){
		
		ProfileOutTO v_return=new ProfileOutTO();  
		List lstResultSets=new Vector();
		System.out.println("Desde DAO");
		this.setTypeReturn(Common.TYPERETURN_RESULTSET);
		this.setDbObject("{call sp_get_usr_profile(?)}");
		//this.setString(1, "return");
		this.setInt(1, "USRNAME",new Integer(1));
		 		
		lstResultSets=this.runQuery();
		System.out.println("return psg");
		//System.out.println(this.getInt());
		 
		//return this.getIntReturn();
		
		return v_return;
		
	}
	
	public ProfileOutTO getUsrProfileHeader(String _nickname){
		ProfileOutTO _return = null;

		List lstResultSet = null;
		TablesCatalogTO _returnTO = new TablesCatalogTO();

		System.out.println("Desde DAO " + "{call sp_get_usr_profile_header(?)}");
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_usr_profile_header(?)}");
		this.setString(1, "nickname", _nickname);
				
		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;
		
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();		
			try {
				while (rowsetActual.next()) {
					_return =  new ProfileOutTO();
					_return.setId_perfil(rowsetActual.getInt(1));
					_return.setDesc_perfil(rowsetActual.getString(2));
					
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
	 * Obtiene todas las opciones permitidas  para el perfil de usuario
	 * 
	 * @author Rutilio
	 */
	public List getUsrProfileDetail(Integer _profileCode) {
		List _return = new Vector();
		List lstResultSet = null;
		TablesCatalogTO _returnTO = new TablesCatalogTO();

		System.out.println("Desde DAO");
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_usr_profile(?)}");
		this.setInt(1, "_profilecode", _profileCode);
		
		
		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;
		ArrayList arr;
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		Hashtable _values=new Hashtable();
		
		 //Iterator<CachedRowSetImpl> iterator = lstResultSet.iterator();
		while (liRowset.hasNext()) {
			
			//if (liRowset.nextIndex()==1){
			System.out.println("liRowset.nextIndex()");
			System.out.println(liRowset.nextIndex());
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			
			/*System.out.println("tamanyo");
		    System.out.println(lstResultSet.size());
			rowsetActual = (CachedRowSetImpl) lstResultSet.get(1);
*/
			try {
				while (rowsetActual.next()) {
					/*_return.add(new TablesCatalogTO(rowsetActual.getInt(1),
							rowsetActual.getString(2), rowsetActual
									.getString(3)));*/
				/*	System.out.println("profilecode");
					System.out.println(rowsetActual.getInt(1));
					System.out.println("profiledetcode");
					System.out.println(rowsetActual.getInt(2));
					System.out.println("profiledetparent");
					System.out.println(rowsetActual.getInt(3));
					System.out.println("profiledetdesc");
					System.out.println(rowsetActual.getString(4));
					System.out.println("profiledeturl");
					System.out.println(rowsetActual.getString(5));
					System.out.println("profiledeticon");
					System.out.println(rowsetActual.getString(6));
					System.out.println("profilename");
					System.out.println(rowsetActual.getString(7));*/
					
					ProfileDetOutTO profileDet=new ProfileDetOutTO();
					profileDet.setId_perfil_det(rowsetActual.getInt(2));
					profileDet.setDesc_perfil_det(rowsetActual.getString(4));
					profileDet.setParent_id(rowsetActual.getInt(3));
					profileDet.setUrl_perfil_det(rowsetActual.getString(5));
										
					_values.put(profileDet.getId_perfil_det(), profileDet);
					
			
				}
				
				
				Enumeration enParameters = _values.keys();
				ProfileDetOutTO profileDetTmp = null;
				Integer _position = null;
				List lstDetProfile=new Vector();
				
				//partimos de los nodos sin hijos
				while (enParameters.hasMoreElements()) {
					_position = (Integer) enParameters.nextElement();
					
					profileDetTmp = (ProfileDetOutTO) _values
							.get(_position);
															
					
					if (profileDetTmp.getParent_id()==0){
						
						System.out.println("Id_perfil_det: " + profileDetTmp.getId_perfil_det());
						System.out.println("Desc_perfil_det: " + profileDetTmp.getDesc_perfil_det());
						System.out.println("Parent_id(): " + profileDetTmp.getParent_id());
											
						this.filterParent(profileDetTmp, _values, profileDetTmp.getId_perfil_det());
						
						_return.add(profileDetTmp);
					
					}
					
					
				}
				
				//partimos de los nodos sin hijos
				
				
				
				
				/*
				TreeMap tm=(TreeMap) rowsetActual.toCollection();
				//arr=(ArrayList) rowsetActual.toCollection();
				arr=new ArrayList();
				int count = arr.size();
				System.out.println("Count: " + count);

				// Loop through elements.
				for (int i = 0; i < arr.size(); i++) {
				    Object value = (Object)arr.get(i);
				    System.out.println("Element: " + value.toString());
				}*/
				
				
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//}
		}
		return _return;
	}
	
	/*
	 * de acuerdo a un TO parent,todos los valores del menu y elfiltro setea los nodos hijos
	 */
	
	private void filterParent(ProfileDetOutTO parent, Hashtable _allvalues, int parentFilter){
		
		Enumeration enParameters = _allvalues.keys();
		ProfileDetOutTO profileDetTmp = null;
		Integer _position = null;
		List lstDetProfile=new Vector();
		
		//partimos de los nodos sin hijos
		while (enParameters.hasMoreElements()) {
			_position = (Integer) enParameters.nextElement();
			
			profileDetTmp = (ProfileDetOutTO) _allvalues
					.get(_position);
							
					
			//
			
			if (profileDetTmp.getParent_id()==parentFilter){	
				
				System.out.println("Id_perfil_det: " + profileDetTmp.getId_perfil_det());
				System.out.println("Desc_perfil_det: " + profileDetTmp.getDesc_perfil_det());
				System.out.println("Parent_id(): " + profileDetTmp.getParent_id());
				
				this.filterParent(profileDetTmp, _allvalues, profileDetTmp.getId_perfil_det());
				
				lstDetProfile.add(profileDetTmp);	
				
				
				
			}
			
		}
		
		parent.setNodeDetail(lstDetProfile);
		
		
	}

}

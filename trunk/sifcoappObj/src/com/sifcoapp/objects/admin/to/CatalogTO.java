package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class CatalogTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3400353028558278833L;
	
	private String catcode;
	public String getCatcode() {
		return catcode;
	}

	public void setCatcode(String catcode) {
		this.catcode = catcode;
	}

	private int tablecode;
	public int getTablecode() {
		return tablecode;
	}

	public void setTablecode(int tablecode) {
		this.tablecode = tablecode;
	}

	private String catvalue;
	public String getCatvalue() {
		return catvalue;
	}

	public void setCatvalue(String catvalue) {
		this.catvalue = catvalue;
	}

	private String catstatus;
	public String getCatstatus() {
		return catstatus;
	}

	public void setCatstatus(String catstatus) {
		this.catstatus = catstatus;
	}

	private int usersign;
	
	public int getUsersign() {
		return usersign;
	}

	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}

	public CatalogTO(){
		this.setCatcode(null);
		this.setTablecode(0);
		this.setCatvalue(null);
		this.setCatstatus(null);
		this.setUsersign(0);
	}
	
	public CatalogTO(String Catcode,int Tablecode,String Catvalue, String Catstatus,int Usersign){
		this.setCatcode(Catcode);
		this.setTablecode(Tablecode);
		this.setCatvalue(Catvalue);
		this.setCatstatus(Catstatus);
		this.setUsersign(Usersign);
	}
}

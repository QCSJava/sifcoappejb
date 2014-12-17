package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class CatalogTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3400353028558278833L;

	private String catcode;
	private int tablecode;
	private String catvalue;
	private String catvalue2;
	private String catvalue3;
	private String catstatus;
	private int usersign;

	public String getCatcode() {
		return catcode;
	}

	public void setCatcode(String catcode) {
		this.catcode = catcode;
	}

	public int getTablecode() {
		return tablecode;
	}

	public void setTablecode(int tablecode) {
		this.tablecode = tablecode;
	}

	public String getCatvalue() {
		return catvalue;
	}

	public void setCatvalue(String catvalue) {
		this.catvalue = catvalue;
	}

	public String getCatvalue2() {
		return catvalue2;
	}

	public void setCatvalue2(String catvalue2) {
		this.catvalue2 = catvalue2;
	}

	public String getCatvalue3() {
		return catvalue3;
	}

	public void setCatvalue3(String catvalue3) {
		this.catvalue3 = catvalue3;
	}

	public String getCatstatus() {
		return catstatus;
	}

	public void setCatstatus(String catstatus) {
		this.catstatus = catstatus;
	}

	public int getUsersign() {
		return usersign;
	}

	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}

	public CatalogTO(String catcode, int tablecode, String catvalue, String catvalue2,
			String catvalue3, String catstatus,  int usersign) {
		super();
		this.catcode = catcode;
		this.tablecode = tablecode;
		this.catvalue = catvalue;
		this.catvalue2 = catvalue2;
		this.catvalue3 = catvalue3;
		this.catstatus = catstatus;
		this.usersign = usersign;
	}

	public CatalogTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * public CatalogTO() { this.setCatcode(null); this.setTablecode(0);
	 * this.setCatvalue(null); this.setCatstatus(null); this.setUsersign(0); }
	 * 
	 * public CatalogTO(String Catcode, int Tablecode, String Catvalue, String
	 * Catstatus, int Usersign) { this.setCatcode(Catcode);
	 * this.setTablecode(Tablecode); this.setCatvalue(Catvalue);
	 * this.setCatstatus(Catstatus); this.setUsersign(Usersign); }
	 */
}

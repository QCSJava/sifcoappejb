package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class EnterpriseTO extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3699994115891620587L;
	private int code;
	private String compnyName;
	private String compnyAddr;
	private String   country_catalog;
	private String   crintHeadr;
	private String   phone1;
	private String   phone2;
	private String   fax;
	private String   e_Mail;
	private String   manager;
	private String   taxIdNum;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getCompnyName() {
		return compnyName;
	}
	public void setCompnyName(String compnyName) {
		this.compnyName = compnyName;
	}
	public String getCompnyAddr() {
		return compnyAddr;
	}
	public void setCompnyAddr(String compnyAddr) {
		this.compnyAddr = compnyAddr;
	}
	public String getCountry_catalog() {
		return country_catalog;
	}
	public void setCountry_catalog(String country_catalog) {
		this.country_catalog = country_catalog;
	}
	public String getCrintHeadr() {
		return crintHeadr;
	}
	public void setCrintHeadr(String crintHeadr) {
		this.crintHeadr = crintHeadr;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getE_Mail() {
		return e_Mail;
	}
	public void setE_Mail(String e_Mail) {
		this.e_Mail = e_Mail;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getTaxIdNum() {
		return taxIdNum;
	}
	public void setTaxIdNum(String taxIdNum) {
		this.taxIdNum = taxIdNum;
	}
	
	
	
}

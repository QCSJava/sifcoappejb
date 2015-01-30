package com.sifcoapp.objects.catalog.to;
import com.sifcoapp.objects.common.to.CommonTO;

import java.util.Date;
public class BusinesspartnerTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6266904645558998389L;
	
	private String cardcode;
	private String cardname;
	private int groupcode;
	private String addid;
	private String notes;
	private String address;
	private String cardtype;
	private String phone1;
	private String phone2;
	private String cellular;
	private String email;
	private String validfor;
	private Date validfrom;
	private Date validto;
	private String nit;
	private String vatgroup;
	public String getCardcode() {
		return cardcode;
	}
	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public int getGroupcode() {
		return groupcode;
	}
	public void setGroupcode(int groupcode) {
		this.groupcode = groupcode;
	}
	public String getAddid() {
		return addid;
	}
	public void setAddid(String addid) {
		this.addid = addid;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
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
	public String getCellular() {
		return cellular;
	}
	public void setCellular(String cellular) {
		this.cellular = cellular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getValidfor() {
		return validfor;
	}
	public void setValidfor(String validfor) {
		this.validfor = validfor;
	}
	public Date getValidfrom() {
		return validfrom;
	}
	public void setValidfrom(Date validfrom) {
		this.validfrom = validfrom;
	}
	public Date getValidto() {
		return validto;
	}
	public void setValidto(Date validto) {
		this.validto = validto;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getVatgroup() {
		return vatgroup;
	}
	public void setVatgroup(String vatgroup) {
		this.vatgroup = vatgroup;
	}
	public BusinesspartnerTO(String cardcode, String cardname, int groupcode,
			String addid, String notes, String address, String cardtype,
			String phone1, String phone2, String cellular, String email,
			String validfor, Date validfrom, Date validto, String nit,
			String vatgroup) {
		super();
		this.cardcode = cardcode;
		this.cardname = cardname;
		this.groupcode = groupcode;
		this.addid = addid;
		this.notes = notes;
		this.address = address;
		this.cardtype = cardtype;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.cellular = cellular;
		this.email = email;
		this.validfor = validfor;
		this.validfrom = validfrom;
		this.validto = validto;
		this.nit = nit;
		this.vatgroup = vatgroup;
	}
	public BusinesspartnerTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}

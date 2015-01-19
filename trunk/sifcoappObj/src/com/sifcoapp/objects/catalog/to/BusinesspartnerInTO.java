package com.sifcoapp.objects.catalog.to;
import com.sifcoapp.objects.common.to.CommonTO;

import java.sql.Date;
import java.util.List;
public class BusinesspartnerInTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6266904645558998389L;
	
	private String cardcode;
	private String cardname;
	private int groupcode;
	private String cardtype;
	private String nit;

	
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


	public String getCardtype() {
		return cardtype;
	}


	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}


	public String getNit() {
		return nit;
	}


	public void setNit(String nit) {
		this.nit = nit;
	}


	public BusinesspartnerInTO(String cardcode, String cardname, int groupcode,
			String cardtype, String nit) {
		super();
		this.cardcode = cardcode;
		this.cardname = cardname;
		this.groupcode = groupcode;
		this.cardtype = cardtype;
		this.nit = nit;
	}


	public BusinesspartnerInTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}

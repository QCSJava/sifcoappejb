package com.sifcoapp.objects.accounting.to;
import java.util.Date;

import com.sifcoapp.objects.common.to.CommonTO;


public class EntryTO extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -127403424423656504L;

	private Date refdate;
	private String acctcode;
	
	public Date getRefdate() {
		return refdate;
	}
	public void setRefdate(Date refdate) {
		this.refdate = refdate;
	}
	public String getAcctcode() {
		return acctcode;
	}
	public void setAcctcode(String acctcode) {
		this.acctcode = acctcode;
	}
	public EntryTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EntryTO(Date refdate, String acctcode) {
		super();
		this.refdate = refdate;
		this.acctcode = acctcode;
	}
	
	
}

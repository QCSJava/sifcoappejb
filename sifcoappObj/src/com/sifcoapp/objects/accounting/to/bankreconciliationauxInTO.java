package com.sifcoapp.objects.accounting.to;

import java.util.Date;

import com.sifcoapp.objects.common.to.CommonTO;

public class bankreconciliationauxInTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7139126590439564090L;
	private Date refdate;
	private int extrmatch;
	public bankreconciliationauxInTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public bankreconciliationauxInTO(Date refdate, int extrmatch) {
		super();
		this.refdate = refdate;
		this.extrmatch = extrmatch;
	}
	public Date getRefdate() {
		return refdate;
	}
	public void setRefdate(Date refdate) {
		this.refdate = refdate;
	}
	public int getExtrmatch() {
		return extrmatch;
	}
	public void setExtrmatch(int extrmatch) {
		this.extrmatch = extrmatch;
	}
}

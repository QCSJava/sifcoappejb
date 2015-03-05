package com.sifcoapp.objects.accounting.to;

import java.util.Date;
import java.util.List;

import com.sifcoapp.objects.common.to.CommonTO;

public class RecurringPostingsInTO extends CommonTO  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5283383351391170486L;
	/**
	 * 
	 */

	private String rcurcode;
	private String rcurdesc;
	private String ref1;
	private String ref2;
	private String memo;
	private String ref3;


	public RecurringPostingsInTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RecurringPostingsInTO(String rcurcode, String rcurdesc, String ref1,
			String ref2, String memo, String ref3) {
		super();
		this.rcurcode = rcurcode;
		this.rcurdesc = rcurdesc;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.memo = memo;
		this.ref3 = ref3;
	}


	public String getRcurcode() {
		return rcurcode;
	}


	public void setRcurcode(String rcurcode) {
		this.rcurcode = rcurcode;
	}


	public String getRcurdesc() {
		return rcurdesc;
	}


	public void setRcurdesc(String rcurdesc) {
		this.rcurdesc = rcurdesc;
	}


	public String getRef1() {
		return ref1;
	}


	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}


	public String getRef2() {
		return ref2;
	}


	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getRef3() {
		return ref3;
	}


	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}

	
}

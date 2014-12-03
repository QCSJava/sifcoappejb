package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class EnterpriseOutTO extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1164714134293192513L;

	private int respCode;
	private String respDesc;
	public int getRespCode() {
		return respCode;
	}
	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	
}

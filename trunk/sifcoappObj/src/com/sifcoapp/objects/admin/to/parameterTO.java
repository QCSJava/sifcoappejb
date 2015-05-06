package com.sifcoapp.objects.admin.to;

import java.util.Date;

import com.sifcoapp.objects.common.to.CommonTO;

public class parameterTO extends CommonTO {

	private int parametercode;
	public int getParametercode() {
		return parametercode;
	}
	public void setParametercode(int parametercode) {
		this.parametercode = parametercode;
	}
	public String getParametername() {
		return parametername;
	}
	public void setParametername(String parametername) {
		this.parametername = parametername;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String string) {
		this.value3 = string;
	}
	public int getUsersign() {
		return usersign;
	}
	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}
	public parameterTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public parameterTO(int parametercode, String parametername, String value1,
			String value2, String value3, int usersign) {
		super();
		this.parametercode = parametercode;
		this.parametername = parametername;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.usersign = usersign;
	}
	private String parametername;
	private String value1;
	private String value2;
	private String value3;
	private int usersign;

	

}

package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class TablesCatalogTO extends CommonTO {

	private String name;
	private String description;
	private int code;
	
	public TablesCatalogTO(){
		this.setCode(0);
		this.setDescription(null);
		this.setName(null);
	}
	public TablesCatalogTO(int _code, String _name, String _description){
		this.setDescription(_description);
		this.setName(_name);
		this.setCode(_code);
	}
	
	private static final long serialVersionUID = 5481160656182582187L;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	

}

package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class BranchTO extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6840211846980087050L;
	private String whscode;
	private String whsname;
	private String grp_code;
	private String locked;

	public BranchTO(){
		this.setGrp_code(grp_code);
		this.setLocked(locked);
		this.setWhscode(whscode);
		this.setWhsname(whsname);
	}
	
	
	public BranchTO(String whscode, String whsname, String grp_code, String locked){
		this.setGrp_code(grp_code);
		this.setLocked(locked);
		this.setWhscode(whscode);
		this.setWhsname(whsname);
	}
	
	
	public String getWhscode() {
		return whscode;
	}
	public void setWhscode(String whscode) {
		this.whscode = whscode;
	}
	public String getWhsname() {
		return whsname;
	}
	public void setWhsname(String whsname) {
		this.whsname = whsname;
	}
	public String getGrp_code() {
		return grp_code;
	}
	public void setGrp_code(String grp_code) {
		this.grp_code = grp_code;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	

}

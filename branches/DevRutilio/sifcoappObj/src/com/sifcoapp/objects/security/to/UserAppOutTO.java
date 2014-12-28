package com.sifcoapp.objects.security.to;

public class UserAppOutTO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7499103127629005767L;
	private int validUser;
	private String desc_usr;	
	private ProfileOutTO usrprofile;
	
	public int getValidUser() {
		return validUser;
	}

	public void setValidUser(int validUser) {
		this.validUser = validUser;
	}

	public void setDesc_usr(String desc_usr) {
		this.desc_usr = desc_usr;
	}

	public String getDesc_usr() {
		return desc_usr;
	}

	public ProfileOutTO getUsrprofile() {
		return usrprofile;
	}

	public void setUsrprofile(ProfileOutTO usrprofile) {
		this.usrprofile = usrprofile;
	}

}

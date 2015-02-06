package com.sifcoapp.objects.security.to;

public class ProfileTO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2914236886324120348L;
	/**
	 * 
	 */
	private int profilecode;
	private String profilename;
	private String active;
	public int getProfilecode() {
		return profilecode;
	}
	public void setProfilecode(int profilecode) {
		this.profilecode = profilecode;
	}
	public String getProfilename() {
		return profilename;
	}
	public void setProfilename(String profilename) {
		this.profilename = profilename;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}

	
}

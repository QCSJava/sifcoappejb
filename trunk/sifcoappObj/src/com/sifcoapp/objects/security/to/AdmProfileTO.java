package com.sifcoapp.objects.security.to;

public class AdmProfileTO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5827261308945878306L;
	/**
	 * 
	 */
	
	/**
	 * 
	 */
	private String doccode;
	private int profilecode;
	public AdmProfileTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdmProfileTO(String doccode, int profilecode) {
		super();
		this.doccode = doccode;
		this.profilecode = profilecode;
	}
	public String getDoccode() {
		return doccode;
	}
	public void setDoccode(String doccode) {
		this.doccode = doccode;
	}
	public int getProfilecode() {
		return profilecode;
	}
	public void setProfilecode(int profilecode) {
		this.profilecode = profilecode;
	}

	
	
}

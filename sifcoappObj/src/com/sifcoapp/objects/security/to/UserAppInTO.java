package com.sifcoapp.objects.security.to;

public class UserAppInTO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6859218926312996355L;
	private String idUserApp;
	private String passwordUserApp;

	public String getIdUserApp() {
		return idUserApp;
	}

	public void setIdUserApp(String idUserApp) {
		this.idUserApp = idUserApp;
	}

	public String getPasswordUserApp() {
		return passwordUserApp;
	}

	public void setPasswordUserApp(String passwordUserApp) {
		this.passwordUserApp = passwordUserApp;
	}

}

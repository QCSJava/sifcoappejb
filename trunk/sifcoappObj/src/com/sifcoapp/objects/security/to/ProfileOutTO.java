package com.sifcoapp.objects.security.to;

import java.util.List;

public class ProfileOutTO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3529727057744111387L;
	private int id_perfil;
	private String desc_perfil;
	public int getId_perfil() {
		return id_perfil;
	}
	public void setId_perfil(int idPerfil) {
		id_perfil = idPerfil;
	}
	public String getDesc_perfil() {
		return desc_perfil;
	}
	public void setDesc_perfil(String descPerfil) {
		desc_perfil = descPerfil;
	}
	public List getProfile_det() {
		return profile_det;
	}
	public void setProfile_det(List profileDet) {
		profile_det = profileDet;
	}
	private List profile_det;
}

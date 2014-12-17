package com.sifcoapp.objects.security.to;

public class ProfileDetInTO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 943936217434082784L;
	private int id_perfil_det;
	private int id_perfil;

	public int getId_perfil_det() {
		return id_perfil_det;
	}

	public void setId_perfil_det(int idPerfilDet) {
		id_perfil_det = idPerfilDet;
	}

	public int getId_perfil() {
		return id_perfil;
	}

	public void setId_perfil(int idPerfil) {
		id_perfil = idPerfil;
	}
}

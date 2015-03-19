package com.sifcoapp.objects.security.to;

import java.util.List;

public class ProfileDetOutTO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4551172303290259655L;
	private int id_perfil_det;
	private String desc_perfil_det;
	private String url_perfil_det;
	private int parent_id;
	private String perfilOrder;
	private String status;
	private List nodeDetail;

	public int getId_perfil_det() {
		return id_perfil_det;
	}

	public void setId_perfil_det(int idPerfilDet) {
		id_perfil_det = idPerfilDet;
	}

	public String getPerfilOrder() {
		return perfilOrder;
	}

	public void setPerfilOrder(String perfilOrder) {
		this.perfilOrder = perfilOrder;
	}

	public String getDesc_perfil_det() {
		return desc_perfil_det;
	}

	public void setDesc_perfil_det(String descPerfilDet) {
		desc_perfil_det = descPerfilDet;
	}

	public String getUrl_perfil_det() {
		return url_perfil_det;
	}

	public void setUrl_perfil_det(String urlPerfilDet) {
		url_perfil_det = urlPerfilDet;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parentId) {
		parent_id = parentId;
	}

	public List getNodeDetail() {
		return nodeDetail;
	}

	public void setNodeDetail(List nodeDetail) {
		this.nodeDetail = nodeDetail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}

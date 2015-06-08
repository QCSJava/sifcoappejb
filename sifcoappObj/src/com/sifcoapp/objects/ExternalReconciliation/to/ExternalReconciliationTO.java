package com.sifcoapp.objects.ExternalReconciliation.to;

import java.util.Date;
import java.util.List;

import com.sifcoapp.objects.common.to.CommonTO;

public class ExternalReconciliationTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7685622119685323000L;
	private List AccToConciliate;
	private List AuxiliaryDoc;
	private Date RefDate;
	private String account;

	public List getAccToConciliate() {
		return AccToConciliate;
	}

	public void setAccToConciliate(List accToConciliate) {
		AccToConciliate = accToConciliate;
	}

	public List getAuxiliaryDoc() {
		return AuxiliaryDoc;
	}

	public void setAuxiliaryDoc(List auxiliaryDoc) {
		AuxiliaryDoc = auxiliaryDoc;
	}

	public Date getRefDate() {
		return RefDate;
	}

	public void setRefDate(Date refDate) {
		RefDate = refDate;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}

package com.sifcoapp.objects.ExternalReconciliation.to;

import java.util.Date;

import com.sifcoapp.objects.common.to.CommonTO;

public class PendingExternalReconciliationInTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3787341974221731283L;

	private Date RefDate;
	private String account;

	public PendingExternalReconciliationInTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PendingExternalReconciliationInTO(Date refDate, String account) {
		super();
		RefDate = refDate;
		this.account = account;
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

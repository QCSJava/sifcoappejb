package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AccountingEJBRemote {
	 public List getAccPeriods();
}

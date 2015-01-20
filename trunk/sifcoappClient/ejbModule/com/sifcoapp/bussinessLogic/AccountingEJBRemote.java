package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.Remote;

import com.sifcoapp.objects.accounting.to.AccassignmentTO;

@Remote
public interface AccountingEJBRemote {

	public List getAccPeriods();

	public int cat_accPeriod_mtto(int parameters, int usersign, int action);
	
	public int cat_accAssignment_mtto(AccassignmentTO parameters, int action);
	
	public AccassignmentTO getAccAssignment();
	
	public List getAccount(int type);
	
	
}

package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.Remote;

import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.AccountTO;

@Remote
public interface AccountingEJBRemote {

	public List getAccPeriods();

	public int cat_accPeriod_mtto(int parameters, int usersign, int action);
	
	public int cat_accAssignment_mtto(AccassignmentTO parameters, int action);
	
	public AccassignmentTO getAccAssignment();
	
	public List getAccount(int type);
	
	public List getAccountByFilter(String acctcode,String acctname);
	
	public AccountTO getAccountByKey(String acctcode);
	
	public int cat_acc0_ACCOUNT_mtto(AccountTO parameters, int action);
	
	public List getTreeAccount();
	
}

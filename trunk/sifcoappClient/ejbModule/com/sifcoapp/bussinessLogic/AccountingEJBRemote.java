package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.AccountTO;

@Remote
public interface AccountingEJBRemote {

	public List getAccPeriods() throws EJBException;

	public int cat_accPeriod_mtto(int parameters, int usersign, int action)throws EJBException;
	
	public int cat_accAssignment_mtto(AccassignmentTO parameters, int action)throws EJBException;
	
	public AccassignmentTO getAccAssignment()throws EJBException;
	
	public List getAccount(int type)throws EJBException;
	
	public List getAccountByFilter(String acctcode,String acctname)throws EJBException;
	
	public AccountTO getAccountByKey(String acctcode)throws EJBException;
	
	public int cat_acc0_ACCOUNT_mtto(AccountTO parameters, int action)throws EJBException;
	
	public List getTreeAccount()throws EJBException;
	
}

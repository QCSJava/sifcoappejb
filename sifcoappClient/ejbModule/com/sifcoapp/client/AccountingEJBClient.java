package com.sifcoapp.client;

import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.bussinessLogic.AccountingEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;

public class AccountingEJBClient implements AccountingEJBRemote {
	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/AccountingEJB!com.sifcoapp.bussinessLogic.AccountingEJBRemote";
	private static AccountingEJBRemote bean;
	private static Context context = null;

	public AccountingEJBClient() {

		// 2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			bean = (AccountingEJBRemote) context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List getAccPeriods() {
		// TODO Auto-generated method stub
		List lstPeriods = new Vector();

		lstPeriods = bean.getAccPeriods();

		return lstPeriods;
	}

	/*
	 * Mantenimiento de periodos contables
	 */
	public int cat_accPeriod_mtto(int parameters, int usersign, int action) {

		int _return = 0;

		_return = bean.cat_accPeriod_mtto(parameters, usersign, action);

		return _return;
	}

	public int cat_accAssignment_mtto(AccassignmentTO parameters, int action) {
		// TODO Auto-generated method stub
		int _return = 0;

		_return = bean.cat_accAssignment_mtto(parameters, action);

		return _return;
	}

	public AccassignmentTO getAccAssignment() {
		// TODO Auto-generated method stub
		AccassignmentTO acc = new AccassignmentTO();

		acc = bean.getAccAssignment();

		return acc;
	}

}

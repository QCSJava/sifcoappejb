package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.AccountingEJBClient;
import com.sifcoapp.client.SecurityEJBClient;
import com.sifcoapp.objects.accounting.to.AccPeriodInTO;
import com.sifcoapp.objects.accounting.to.AccPeriodOutTO;
import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.security.to.ProfileInTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;
import com.sifcoapp.objects.security.to.UserTO;
import com.sifcoapp.objects.utilities.PasswordService;

public class AccountingTest {
	private static AccountingEJBClient AccountingEJBService = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (AccountingEJBService == null)
			AccountingEJBService = new AccountingEJBClient();

		String v_method = args[0];

		/*
		 * List lstPeriods=new Vector();
		 * 
		 * lstPeriods=AccountingEJBService.getAccPeriods();
		 * 
		 * System.out.println(lstPeriods);
		 */

		try {
			AccountingTest.class.getMethod(args[0], null).invoke(null, null);
			// testPeriods();

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getPeriods() {

		List lstPeriods = new Vector();

		try {
			lstPeriods = AccountingEJBService.getAccPeriods();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<AccPeriodTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			AccPeriodTO periodo = (AccPeriodTO) iterator.next();
			System.out.println(periodo.getAcccode() + " - "
					+ periodo.getAccname() + " - "
					+ periodo.getF_duedate().toString());
		}
	}

	public static void getAccount() {

		List lstPeriods = new Vector();

		try {
			lstPeriods = AccountingEJBService.getAccount(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<AccountTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			AccountTO acc = (AccountTO) iterator.next();
			System.out.println(acc.getAcctcode() + " - " + acc.getCurrtotal());
		}
	}
	public static void getAccountree() {

		List lstPeriods = new Vector();

		try {
			lstPeriods = AccountingEJBService.getTreeAccount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<AccountTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			AccountTO acc = (AccountTO) iterator.next();
			System.out.println(acc.getAcctcode() + " - " + acc.getCurrtotal());
		}
	}

	public static void accPeridod_mtto() throws Exception {

		int _result;
		int parameters = 2015;
		int usersing = 1;

		// Agregar

		_result = AccountingEJBService.cat_accPeriod_mtto(parameters, usersing,
				Common.MTTOINSERT);

		// Actualizar

		// _result=AdminEJBService.cat_accPeriod_mtto(parameters,
		// Common.MTTOUPDATE);

		// Borrar

		// _result=AdminEJBService.cat_accPeriod_mtto(parameters,
		// Common.MTTODELETE);

		System.out.println("luego de servicio");
		System.out.println(_result);

	}

	public static void accAssignmetn_mtto() throws Exception {

		int _result;
		AccassignmentTO parameters = new AccassignmentTO();

		// parameters.setUsersign();
		parameters.setLinkact_1("101010");
		parameters.setLinkact_2("111111");
		parameters.setLinkact_3("1212121");
		parameters.setLinkact_4("131313");
		parameters.setLinkact_5("141414");
		parameters.setLinkact_6("141414");
		parameters.setLinkact_10("jc");
		parameters.setLinkact_11("jc");
		parameters.setSdfltwt("Sdfltwt");
		parameters.setPdfltwt("Pdfltwt");
		parameters.setShandlewt(true);
		parameters.setPhandlewt(true);

		// Agregar

		// _result = AccountingEJBService.cat_accAssignment_mtto(parameters,
		// Common.MTTOINSERT);

		// Actualizar

		parameters.setUsersign(1);
		_result = AccountingEJBService.cat_accAssignment_mtto(parameters,
				Common.MTTOUPDATE);

		// Borrar
		/*
		 * parameters.setUsersign(13);
		 * _result=SecurityEJBService.cat_users_mtto(parameters,
		 * Common.MTTODELETE);
		 */

		System.out.println("luego de servicio");
		System.out.println(_result);

	}

	public static void getAccAssignment() throws Exception {
		AccassignmentTO acc = null;

		acc = AccountingEJBService.getAccAssignment();

		System.out.println(acc.getLinkact_1());
		System.out.println(acc.getLinkact_2());
		System.out.println(acc.getLinkact_3());
		System.out.println(acc.getLinkact_4());
		System.out.println(acc.getLinkact_5());
		System.out.println(acc.getLinkact_6());
		System.out.println(acc.getLinkact_8());
		System.out.println(acc.getLinkact_9());
		System.out.println(acc.getLinkact_10());
		System.out.println(acc.getLinkact_11());
		System.out.println(acc.isPhandlewt());
		System.out.println(acc.isShandlewt());
		System.out.println(acc.getSdfltwt());
		System.out.println(acc.getPdfltwt());
	}


//################## PRUEBAS DE TABLA ACCOUNT  ############################
public static void getAccountByFilter() {

	List lstPeriods = new Vector();

	try {
		lstPeriods = AccountingEJBService.getAccountByFilter(null,"de");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	Iterator<AccountTO> iterator = lstPeriods.iterator();
	while (iterator.hasNext()) {
		System.out.println("entrooo");
		AccountTO acc = (AccountTO) iterator.next();
		System.out.println(acc.getAcctcode() + " - " + acc.getAcctname());
	}
}

public static void getAccountByKey() throws Exception {

	AccountTO acc = new AccountTO();

	acc = AccountingEJBService.getAccountByKey("101010");
	
		System.out.println(acc.getAcctcode() + " - " + acc.getAcctname());
	
}

public static void cat_Acc0_account_mtto() throws Exception {
	int _return=8;
	AccountTO acc = new AccountTO();
	acc.setAcctcode("101013");
	acc.setAcctname("Pasivos Corrientes");
	acc.setCurrtotal(42.5);
	acc.setEndtotal(56.2);

	_return = AccountingEJBService.cat_acc0_ACCOUNT_mtto(acc, 3);
	
		System.out.println(_return);
	
}


}

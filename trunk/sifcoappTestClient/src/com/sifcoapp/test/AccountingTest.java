package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.AccountingEJBClient;
import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.accounting.to.JournalEntryInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;

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
			Iterator<AccPeriodTO> iterator = lstPeriods.iterator();

			while (iterator.hasNext()) {
				AccPeriodTO periodo = (AccPeriodTO) iterator.next();
				System.out.println(periodo.getAcccode() + " - "
						+ periodo.getAccname() + " - "
						+ periodo.getF_duedate().toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error EJB " + e.getMessage());
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
		// parameters.setAbsentry(2);
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
		Date fecha = new Date();
		parameters.setT_taxdate(fecha);
		parameters.setF_duedate(fecha);
		// Agregar

		 //_result = AccountingEJBService.cat_accAssignment_mtto(parameters,
		 //Common.MTTOINSERT);

		// Actualizar

		//parameters.setUsersign(1);
		_result = AccountingEJBService.cat_accAssignment_mtto(parameters,
				Common.MTTOINSERT);

		// Borrar
		/*
		 * parameters.setUsersign(13);
		 * _result=SecurityEJBService.cat_users_mtto(parameters,
		 * Common.MTTODELETE);
		 */

		System.out.println("luego de servicio");
		System.out.println(fecha);

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

	// ################## PRUEBAS DE TABLA ACCOUNT ############################
	public static void getAccountByFilter() {

		List lstPeriods = new Vector();
		// 558,7954
		try {
			lstPeriods = AccountingEJBService
					.getAccountByFilter(null, "ingresos", "Y");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<AccountTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			// System.out.println("entrooo");
			AccountTO acc = (AccountTO) iterator.next();
			System.out.println(acc.getAcctcode() + " - " + acc.getAcctname()
					+ " - " + acc.getPostable());
		}
	}

	public static void getAccountByKey() throws Exception {

		AccountTO acc = new AccountTO();

		acc = AccountingEJBService.getAccountByKey("101010");

		System.out.println(acc.getAcctcode() + " - " + acc.getAcctname());

	}

	public static void cat_Acc0_account_mtto() throws Exception {
		int _return = 8;
		AccountTO acc = new AccountTO();
		acc.setAcctcode("101013");
		acc.setAcctname("Pasivos Corrientes");
		acc.setCurrtotal(42.5);
		acc.setEndtotal(56.2);

		_return = AccountingEJBService.cat_acc0_ACCOUNT_mtto(acc, 3);

		System.out.println(_return);

	}

	
	//###############  PRUEBAS DE JOURNALENTRYS  ##################################
	
	public static void journal_matto() throws Exception{
		ResultOutTO _result= new ResultOutTO();
		List detail = new Vector();
		JournalEntryTO nuevo = new JournalEntryTO();
		JournalEntryLinesTO art1 = new JournalEntryLinesTO();
		JournalEntryLinesTO art2 = new JournalEntryLinesTO();
		
		nuevo.setBatchnum(1);
		art1.setLine_id(1);
		detail.add(art1);
		art2.setLine_id(2);
		detail.add(art2);
		nuevo.setJournalentryList(detail);
		
		_result= AccountingEJBService.journalEntry_mtto(nuevo,Common.MTTOINSERT);
		
		System.out.println(_result.getMensaje());
	}
	
	public static void getjournalEntry(){
		JournalEntryInTO nuevo = new JournalEntryInTO();
		List consul= new Vector();
		//nuevo.setTransid(1);
		//nuevo.setBtfstatus("Y");
		
		consul= AccountingEJBService.getJournalEntry(nuevo);
		Iterator<JournalEntryTO> iterator = consul.iterator();
		while (iterator.hasNext()) {
			JournalEntryTO acc = (JournalEntryTO) iterator.next();
			System.out.println(acc.getTransid() + " - " + acc.getBtfstatus());
		}
		
	}
	public static void getjournalentry_by_key(){
		List consul= new Vector();
		JournalEntryTO result= new JournalEntryTO();
		int trans=14;
		result= AccountingEJBService.getJournalEntryByKey(trans);
		System.out.println(result.getBtfstatus()+result.getTransid());
		consul= result.getJournalentryList();
		Iterator<JournalEntryLinesTO> iterator = consul.iterator();
		while (iterator.hasNext()) {
			JournalEntryLinesTO acc = (JournalEntryLinesTO) iterator.next();
			System.out.println(acc.getTransid() + " - " + acc.getLine_id());
		}
	}
}

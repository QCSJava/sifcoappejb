package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;



//import sun.awt.shell.Win32ShellFolder2.SystemIcon;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.sifcoapp.client.AccountingEJBClient;
import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.accounting.to.BudgetTO;
import com.sifcoapp.objects.accounting.to.JournalEntryInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsDetailTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsInTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

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
ResultOutTO _result = new ResultOutTO();
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

		_result = AccountingEJBService.cat_accAssignment_mtto(parameters,
		 Common.MTTOINSERT);

		// Actualizar

		// parameters.setUsersign(1);
		//_result = AccountingEJBService.cat_accAssignment_mtto(parameters,
			//	Common.MTTOINSERT);

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
			lstPeriods = AccountingEJBService.getAccountByFilter(null,
					"ingresos", "Y");
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
		ResultOutTO _return = new ResultOutTO();
		AccountTO acc = new AccountTO();
		acc.setAcctcode("1102700106");
		acc.setAcctname("cuenta por cobrar Equipo 01");
	
		acc.setCurrtotal(42.5);
		acc.setEndtotal(56.2);

		_return = AccountingEJBService.cat_acc0_ACCOUNT_mtto(acc, 1);

		System.out.println(_return.getDocentry());

	}

	public static void saveTreeAccount() throws EJBException {

		AccountTO node = new AccountTO();
		AccountTO node1 = new AccountTO();
		AccountTO account = new AccountTO();

		account.setAcctcode("1");
		account.setAcctname("FINANCIACIÓN BÁSICA");
		account.setCurrtotal(0.000000);
		account.setEndtotal(0.000000);
		account.setBudget("Y");
		account.setFrozen("n");
		account.setPostable("N");
		account.setLevels(1);

		node.setAcctcode("1.1");
		node.setAcctname("gffgfgfgfgfgfgfgfgfgfggffh");
		node.setCurrtotal(0.000000);
		node.setEndtotal(0.000000);
		node.setBudget("Y");
		node.setFrozen("n");
		node.setPostable("N");
		node.setLevels(2);
		node.setFathernum("1");
		
		node1.setAcctcode("1.1.1");
		node1.setAcctname("jjsgjgggggggggggggfsjdf");
		node1.setCurrtotal(0.000000);
		node1.setEndtotal(0.000000);
		node1.setBudget("Y");
		node1.setFrozen("n");
		node1.setPostable("N");
		node1.setLevels(3);
		node1.setFathernum("1.1");
		
		 System.out.println(account.getAcctname());
		 System.out.println(node.getAcctname());
		List parameters = new Vector();
		parameters.add(account);
		parameters.add(node);
		parameters.add(node1);
		
		
		ResultOutTO _result= new ResultOutTO();
		_result= AccountingEJBService.saveTreeAccount(parameters);
		
	}

	// ############### PRUEBAS DE JOURNALENTRYS
	// ##################################
	
	
	

	public static void journal_matto() throws Exception {
		ResultOutTO _result = new ResultOutTO();
		List detail = new Vector();
		JournalEntryTO nuevo = new JournalEntryTO();
		JournalEntryLinesTO art1 = new JournalEntryLinesTO();
		JournalEntryLinesTO art2 = new JournalEntryLinesTO();

		nuevo.setBatchnum(1);
		art1.setLine_id(1);
		art1.setAccount("1.1");
		art1.setDebit(20.30);
		detail.add(art1);
		art2.setAccount("2.2");
		art2.setCredit(20.30);
		detail.add(art2);
		nuevo.setJournalentryList(detail);

		_result = AccountingEJBService.journalEntry_mtto(nuevo,
				Common.MTTOINSERT);

		System.out.println(_result.getMensaje());
	}

	public static void getjournalEntry() {
		JournalEntryInTO nuevo = new JournalEntryInTO();
		List consul = new Vector();
		 nuevo.setTransid(283);
		// nuevo.setBtfstatus("Y");

		consul = AccountingEJBService.getJournalEntry(nuevo);
		Iterator<JournalEntryTO> iterator = consul.iterator();
		while (iterator.hasNext()) {
			JournalEntryTO acc = (JournalEntryTO) iterator.next();
			System.out.println(acc.getTransid() + " - " + acc.getBtfstatus());
		}

	}
	public static void getEntry() {
		JournalEntryLinesInTO nuevo = new JournalEntryLinesInTO();
		List consul = new Vector();
		nuevo.setTransid(10);
		// nuevo.setBtfstatus("Y");
		nuevo.setAccount("210201010121");
		java.util.Date utilDate = new java.util.Date(); //fecha actual
		  long lnMilisegundos = utilDate.getTime();
		  java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
		nuevo.setTaxdate(sqlDate);
		Calendar ca=Calendar.getInstance();
		ca.set(2015, 05, 01);
			
		  nuevo.setRefdate(ca.getTime());
		  nuevo.setTotalvat(203.210649);
				
		

		consul = AccountingEJBService.getEntryDetail(nuevo);
		Iterator<JournalEntryLinesTO> iterator = consul.iterator();
		while (iterator.hasNext()) {
			JournalEntryLinesTO acc = (JournalEntryLinesTO) iterator.next();
			System.out.println(acc.getTransid() + "-" + acc.getAccount()+"-"+acc.getTotalvat() +"-"+acc.getDebit()+"-"+acc.getCredit());
		}

	}

	public static void getjournalentry_by_key() {
		List consul = new Vector();
		JournalEntryTO result = new JournalEntryTO();
		int trans =367 ;
		result = AccountingEJBService.getJournalEntryByKey(trans);
		System.out.println(result.getBtfstatus() + result.getTransid());
		consul = result.getJournalentryList();
		
		if(consul!=null){
		Iterator<JournalEntryLinesTO> iterator = consul.iterator();
		while (iterator.hasNext()) {
			JournalEntryLinesTO acc = (JournalEntryLinesTO) iterator.next();
			System.out.println(acc.getTransid() + " - " + acc.getLine_id());
		}
		}
	}

	// ################pruebas budget###############################
	public static void budget_mtto() throws Exception {
		ResultOutTO _result = new ResultOutTO();
		List detail = new Vector();
		BudgetTO nuevo = new BudgetTO();
		Date fecha = new Date();
		nuevo.setFinancyear(fecha);
		nuevo.setAcctcode("1255");
		nuevo.setBgdcode(1);

		_result = AccountingEJBService
				.cat_budget_mtto(nuevo, Common.MTTOINSERT);

		System.out.println(_result.getMensaje());
	}

	public static void getBudget() {

		List consul = new Vector();
		// Date fecha = new Date();
		// nuevo.setTransid(1);
		// nuevo.setBtfstatus("Y");

		consul = AccountingEJBService.getBudget(2015);
		Iterator<BudgetTO> iterator = consul.iterator();
		while (iterator.hasNext()) {
			BudgetTO acc = (BudgetTO) iterator.next();
			System.out.println(acc.getAbsid() + " - " + acc.getAcctcode());
		}

	}

	// #################### pruebas de recurringposting #####################
	public static void recurring_mtto() throws Exception {
		ResultOutTO _result = new ResultOutTO();
		List detail = new Vector();
		RecurringPostingsTO nuevo = new RecurringPostingsTO();
		RecurringPostingsDetailTO detalle1 = new RecurringPostingsDetailTO();
		RecurringPostingsDetailTO detalle2 = new RecurringPostingsDetailTO();

		nuevo.setRcurcode("00000002");
		nuevo.setInstance(1);
		detalle1.setAcctdesc("probando");
		detalle1.setLineid(1);
		detalle1.setCredit(45.50);
		detalle2.setAcctdesc("probando");
		detalle2.setLineid(2);
		detalle2.setCredit(45.50);
		detail.add(detalle1);
		detail.add(detalle2);
		// nuevo.setRecurringPostingsDetail(detail);

		_result = AccountingEJBService.fin_recurringPosting_mtto(nuevo,
				Common.MTTODELETE);
		System.out.println(_result.getMensaje() + " - "
				+ _result.getCodigoError());

	}

	public static void getRecurring() {
		RecurringPostingsInTO nuevo = new RecurringPostingsInTO();
		List consul = new Vector();
		RecurringPostingsTO consul2 = new RecurringPostingsTO();
		// nuevo.setTransid(1);
		// nuevo.setBtfstatus("Y");
		nuevo.setRcurdesc("n");
		consul = AccountingEJBService.getrecurringPosting(nuevo);
		Iterator<RecurringPostingsTO> iterator = consul.iterator();
		while (iterator.hasNext()) {
			RecurringPostingsTO acc = (RecurringPostingsTO) iterator.next();
			System.out.println(acc.getRcurcode() + " - " + acc.getRcurdesc());

		}

	}

	public static void getReccuring_by_key() {
		RecurringPostingsTO consul2 = new RecurringPostingsTO();
		List consul = new Vector();
		consul2 = AccountingEJBService
				.getrecurringPosting_by_key("00000001", 0);
		// Iterator<RecurringPostingsTO> iterator = consul2.iterator();
		// while (iterator.hasNext()) {
		// RecurringPostingsTO acc = (RecurringPostingsTO) iterator.next();
		System.out.println(consul2.getRcurcode() + " - "
				+ consul2.getFrequency());

		// }
	}
	
	
	// pruebas de insercion de cuentas
	public static void getprueba_by_key() {
		ResultOutTO nuevo=new ResultOutTO();
		List consul = new Vector();
		
		for (int i = 1; i < 689; i++) {
			JournalEntryTO result = new JournalEntryTO();
			
			result = AccountingEJBService.getpruebaByKey(i);
							
			//nuevo = AccountingEJBService.journalEntry_mtto(result,
			//		Common.MTTOINSERT);			
			
			if (nuevo.getCodigoError() != 0) {
				System.out.println(nuevo.getMensaje());
				break;
			}
			
			System.out.println(nuevo.getDocentry() + " - " + result.getTransid() + " - " + nuevo.getMensaje() );
		}		
	
	}
	
	public static void update_endtotal() throws Exception {
		ResultOutTO _result = new ResultOutTO();
		

		_result = AccountingEJBService.Update_endTotal();
		System.out.println(_result.getMensaje());
	}
	
}

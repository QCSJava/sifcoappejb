package com.sifcoapp.client;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.bussinessLogic.AccountingEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.accounting.dao.JournalEntryDAO;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.accounting.to.BudgetTO;
import com.sifcoapp.objects.accounting.to.EntryTO;
import com.sifcoapp.objects.accounting.to.JournalEntryInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsInTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

public class AccountingEJBClient {

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

	public List getAccPeriods() throws Exception {
		// TODO Auto-generated method stub
		List lstPeriods = new Vector();

		lstPeriods = bean.getAccPeriods();

		return lstPeriods;
	}

	/*
	 * Mantenimiento de periodos contables
	 */
	public int cat_accPeriod_mtto(int parameters, int usersign, int action)
			throws Exception {

		int _return = 0;

		_return = bean.cat_accPeriod_mtto(parameters, usersign, action);

		return _return;
	}

	public ResultOutTO validate_exist_accperiod(Date parameters)
			throws Exception {

		ResultOutTO _return = new ResultOutTO();

		_return = bean.validate_exist_accperiod(parameters);

		return _return;
	}

	public ResultOutTO cat_accAssignment_mtto(AccassignmentTO parameters,
			int action) throws Exception {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();

		_return = bean.cat_accAssignment_mtto(parameters, action);

		return _return;
	}

	public AccassignmentTO getAccAssignment() throws Exception {
		// TODO Auto-generated method stub
		AccassignmentTO acc = new AccassignmentTO();

		acc = bean.getAccAssignment();

		return acc;
	}

	public List getAccount(int type) throws Exception {
		// TODO Auto-generated method stub
		List acc = new Vector();

		acc = bean.getAccount(type);

		return acc;
	}

	public List getAccountByFilter(String acctcode, String acctname)
			throws Exception {
		return getAccountByFilter(acctcode, acctname, null);
	}

	public List getAccountByFilter(String acctcode, String acctname,
			String postable) throws Exception {
		// TODO Auto-generated method stub
		List account = new Vector();
		account = bean.getAccountByFilter(acctcode, acctname, postable);
		return account;
	}

	public AccountTO getAccountByKey(String acctcode) throws Exception {
		// TODO Auto-generated method stub
		AccountTO _return = new AccountTO();
		_return = bean.getAccountByKey(acctcode);
		return _return;
	}

	public int cat_acc0_ACCOUNT_mtto(AccountTO parameters, int action)
			throws Exception {
		// TODO Auto-generated method stub
		int _return = 0;
		_return = bean.cat_acc0_ACCOUNT_mtto(parameters, action);
		return _return;
	}

	public List getTreeAccount() throws Exception {
		// TODO Auto-generated method stub
		List account = new Vector();
		account = bean.getTreeAccount();
		return account;
	}

	public ResultOutTO saveTreeAccount(List parameters) throws EJBException {
		ResultOutTO _result = new ResultOutTO();

		_result = bean.saveTreeAccount(parameters);

		return _result;

	}

	// ######## JOURNALENTRYS ########

	public List getJournalEntry(JournalEntryInTO parameters)
			throws EJBException {
		List _return = new Vector();
		_return = bean.getJournalEntry(parameters);
		return _return;
	}

	public JournalEntryTO getJournalEntryByKey(int transid) throws EJBException {
		JournalEntryTO _return = new JournalEntryTO();

		_return = bean.getJournalEntryByKey(transid);

		return _return;
	}

	public ResultOutTO journalEntry_mtto(JournalEntryTO parameters, int action)
			throws EJBException {
		ResultOutTO _return;
		_return = bean.journalEntry_mtto(parameters, action);
		return _return;
	}

	/* MANTENIMIENTO DE TABLA BUDGET */
	public ResultOutTO cat_budget_mtto(BudgetTO parameters, int action)
			throws EJBException {
		ResultOutTO _return;
		_return = bean.cat_budget_mtto(parameters, action);
		return _return;

	}

	public List getBudget(int _bgdcode) {
		List _return = new Vector();
		_return = bean.getBudget(_bgdcode);
		return _return;
	}

	/* MANTENIMIENTO DE TABLA RECURRINGPOSTING */
	public ResultOutTO fin_recurringPosting_mtto(
			RecurringPostingsTO parameters, int action) throws EJBException {
		ResultOutTO _return;
		_return = bean.fin_recurringPosting_mtto(parameters, action);
		return _return;
	}

	public List getrecurringPosting(RecurringPostingsInTO parameters)
			throws EJBException {
		List _return = new Vector();
		_return = bean.getrecurringPosting(parameters);
		return _return;
	}

	public List getrecurringPosting_user(RecurringPostingsInTO parameters)
			throws EJBException {
		List _return = new Vector();
		_return = bean.getrecurringPosting_user(parameters);
		return _return;
	}

	public RecurringPostingsTO getrecurringPosting_by_key(String _rcurcode,
			int _instance) throws EJBException {
		RecurringPostingsTO _return = new RecurringPostingsTO();

		_return = bean.getrecurringPosting_by_key(_rcurcode, _instance);

		return _return;
	}

	public List getrecurringPostingExecute(int usersign) throws EJBException {
		List _return = new Vector();
		_return = bean.getrecurringPostingExecute(usersign);
		return _return;
	}

	public List getEntryDetail(JournalEntryLinesInTO parameters)
			throws EJBException {
		List _return = new Vector();
		try {
			_return = bean.getEntryDetail(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _return;
	}

	public JournalEntryLinesTO getsaldo(EntryTO parameters) throws EJBException {
		JournalEntryLinesTO _return = new JournalEntryLinesTO();
		try {
			_return = bean.getsaldo(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _return;
	}

	// --------------------------------------------------------------
	// --elementos de prueba-----------------------------------------
	public JournalEntryTO getpruebaByKey(int transid) throws EJBException {
		JournalEntryTO _return = new JournalEntryTO();

		_return = bean.getpruebaByKey(transid);

		return _return;
	}
	
	public ResultOutTO journal_entry_new(JournalEntryTO parameters, int action)
			throws EJBException {
		ResultOutTO _return = null;
		try {
			_return = bean.journal_entry_new(parameters, action);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _return;
	}

	public ResultOutTO Update_endTotal(int indicador) throws Exception{
		
		ResultOutTO _return = null;
		try {
			_return = bean.Update_endTotal(indicador);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _return;
	}
	
	public ResultOutTO fill_Journal_close(JournalEntryTO parameters)
			throws Exception{
		ResultOutTO _return=new ResultOutTO();
		
		_return=bean.fill_Journal_close(parameters);
		
		return _return;
		
	}

}

package com.sifcoapp.bussinessLogic;

import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.accounting.to.BudgetTO;
import com.sifcoapp.objects.accounting.to.EntryTO;
import com.sifcoapp.objects.accounting.to.JournalEntryInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsDetailTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsInTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

@Remote
public interface AccountingEJBRemote {

	public List getAccPeriods() throws EJBException;

	public int cat_accPeriod_mtto(int parameters, int usersign, int action)
			throws EJBException;

	public ResultOutTO cat_accAssignment_mtto(AccassignmentTO parameters,
			int action) throws EJBException;

	public AccassignmentTO getAccAssignment() throws EJBException;

	public List getAccount(int type) throws EJBException;

	public List getAccountByFilter(String acctcode, String acctname)
			throws EJBException;

	public List getAccountByFilter(String acctcode, String acctname,
			String postable) throws EJBException;

	public AccountTO getAccountByKey(String acctcode) throws EJBException;

	public ResultOutTO cat_acc0_ACCOUNT_mtto(AccountTO parameters, int action)
			throws EJBException;

	public List getTreeAccount() throws EJBException;

	public ResultOutTO saveTreeAccount(List parameters) throws EJBException;

	// ////###### journal entry####/////////////////////////////
	public List getJournalEntry(JournalEntryInTO parameters)
			throws EJBException;

	public JournalEntryTO getJournalEntryByKey(int transid) throws EJBException;

	public ResultOutTO journalEntry_mtto(JournalEntryTO parameters, int action)
			throws EJBException;

	// ############### BUDGET ####################################
	public ResultOutTO cat_budget_mtto(BudgetTO parameters, int action)
			throws EJBException;

	public List getBudget(int _bgdcode) throws EJBException;

	// ################# RecurringPosting #################################
	public ResultOutTO fin_recurringPosting_mtto(
			RecurringPostingsTO parameters, int action) throws EJBException;

	public List getrecurringPosting(RecurringPostingsInTO parameters)
			throws EJBException;

	public List getrecurringPosting_user(RecurringPostingsInTO parameters)
			throws EJBException;

	public RecurringPostingsTO getrecurringPosting_by_key(String _rcurcode,
			int _instance) throws EJBException;

	public List getrecurringPostingExecute(int usersign) throws EJBException;

	public ResultOutTO validate_exist_accperiod(Date parameters)
			throws EJBException;

	public List getEntryDetail(JournalEntryLinesInTO parameters)
			throws Exception;

	public JournalEntryLinesTO getsaldo(EntryTO parameters) throws EJBException;

	// -------------------------------------------------------------------
	// elementos de prueba
	// ---------------------------------------------------------------------

	public ResultOutTO fill_Journal_close(JournalEntryTO parameters)
			throws Exception;

	public ResultOutTO journal_entry_new(JournalEntryTO parameters, int action)
			throws Exception;

	public ResultOutTO Update_endTotal() throws Exception;

	public ResultOutTO traslado_caja(AccountTO account) throws Exception;

	public Double devolver_saldo(AccountTO account) throws Exception;

	public void update_tree(Date refdate, String transtype) throws EJBException;
}
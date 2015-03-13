package com.sifcoapp.bussinessLogic;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.accounting.dao.AccountingDAO;
import com.sifcoapp.objects.accounting.dao.JournalEntryDAO;
import com.sifcoapp.objects.accounting.dao.JournalEntryLinesDAO;
import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.accounting.to.BudgetTO;
import com.sifcoapp.objects.accounting.to.JournalEntryInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsDetailTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsInTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.to.GoodsIssuesDetailTO;

/**
 * Session Bean implementation class AccountingEJB
 */
@Stateless
public class AccountingEJB implements AccountingEJBRemote {
	Double zero=0.00;
	/**
	 * Default constructor.
	 */
	public AccountingEJB() {
		// TODO Auto-generated constructor stub
	}

	public List getAccPeriods() throws EJBException {
		List _return = new Vector();
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return = DAO.getAccPeriods();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}
	
	public List getAccount(int type) throws EJBException {
		List _return = new Vector();
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return = DAO.getAccount(type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public int cat_accPeriod_mtto(int parameters, int usersign, int action) throws EJBException {

		int _return = 0;

		// Dividir el año en 12 periodos y crear objeto

		/*
		 * Agregar validadiones - Se haran desde la base - Que no este creado el
		 * año - Que el año sea mayor al actual
		 */
		AccountingDAO DAO = new AccountingDAO();
		//para el manejo de transacciones
		DAO.setIstransaccional(true);
		
		for (int i = 1; i <= 12; i++) {

			AccPeriodTO periodo = new AccPeriodTO();
			periodo.setAcccode(Integer.toString(i));
			periodo.setAccname(Integer.toString(parameters)
					+ String.format("%02d", i));
			periodo.setF_duedate(Common.getPrimerDiaDelMes(parameters, i));
			periodo.setF_refdate(Common.getPrimerDiaDelMes(parameters, i));
			periodo.setF_taxdate(Common.getPrimerDiaDelMes(parameters, i));
			periodo.setPeriodstat(1);
			periodo.setT_duedate(Common.getUltimoDiaDelMes(parameters, i));
			periodo.setT_refdate(Common.getUltimoDiaDelMes(parameters, i));
			periodo.setT_taxdate(Common.getUltimoDiaDelMes(parameters, i));
			periodo.setUsersign(usersign);
			try {
				_return = DAO.cat_accPeriod_mtto(periodo, action);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw (EJBException) new EJBException(e);
			}
		}
		
		DAO.forceCommit();
		DAO.forceCloseConnection();
		
		return _return;
	}

	public int cat_accAssignment_mtto(AccassignmentTO parameters, int action)throws EJBException {
		int _return = 0;

		AccountingDAO DAO = new AccountingDAO();
		try {
			_return = DAO.cat_accAssignment_mtto(parameters, action);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public AccassignmentTO getAccAssignment() throws EJBException {
		AccassignmentTO _return = null;

		AccountingDAO DAO = new AccountingDAO();
		try {
			_return = DAO.getAccAssignment();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public List getAccountByFilter(String acctcode, String acctname) throws EJBException {
		return  getAccountByFilter(acctcode, acctname, null);
	}

	public List getAccountByFilter(String acctcode, String acctname, String postable) throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return= DAO.getAccountByFilter(acctcode, acctname,postable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public AccountTO getAccountByKey(String acctcode)throws EJBException {
		// TODO Auto-generated method stub
		AccountTO acc= new AccountTO();
		AccountingDAO DAO= new AccountingDAO();
		try {
			acc = DAO.getAccountByKey(acctcode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return acc;
	}

	public int cat_acc0_ACCOUNT_mtto(AccountTO parameters, int action) throws EJBException {
		// TODO Auto-generated method stub
		int _return=0;
		AccountingDAO DAO= new AccountingDAO();
		try {
			_return= DAO.cat_acc0_ACCOUNT_mtto(parameters, action);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public List getTreeAccount() throws EJBException {
		// TODO Auto-generated method stub
		List _return= new Vector();
		AccountingDAO DAO= new AccountingDAO();
		try {
			_return=DAO.getTreeAccount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}
	
//////###### journal entry####/////////////////////////////
	public List getJournalEntry(JournalEntryInTO parameters)
			throws EJBException {
		List _return= new Vector();
		JournalEntryDAO DAO= new JournalEntryDAO();
		try {
			_return= DAO.getJournalEntry(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public JournalEntryTO getJournalEntryByKey(int transid) throws EJBException {
		JournalEntryTO _return = new JournalEntryTO();
		JournalEntryDAO DAO = new JournalEntryDAO();
		try {
			_return= DAO.getJournalEntryByKey(transid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public ResultOutTO journalEntry_mtto(JournalEntryTO parameters, int action) throws EJBException {
		//  VALOR POR DEFECTO PARA LOS DOUBLES##############
		double zero=0.00;
		ResultOutTO _return = new ResultOutTO();
		JournalEntryDAO DAO = new JournalEntryDAO();
		DAO.setIstransaccional(true);
		JournalEntryLinesDAO JournalLinesDAO = new JournalEntryLinesDAO(DAO.getConn());
		JournalLinesDAO.setIstransaccional(true);
		try {
			if(parameters.getLoctotal()==null){
				parameters.setLoctotal(zero);
			}
			if(parameters.getSystotal()==null){
				parameters.setSystotal(zero);
			}
			if(parameters.getTransrate()==null){
				parameters.setTransrate(zero);
			}
			if(parameters.getWtapplied()==null){
				parameters.setWtapplied(zero);
			}
			if(parameters.getBaseamnt()==null){
				parameters.setBaseamnt(zero);
			}
			if(parameters.getBasevtat()==null){
				parameters.setBasevtat(zero);
			}
			_return.setDocentry(DAO.journalEntry_mtto(parameters, action));
			_return.getDocentry();
		Iterator<JournalEntryLinesTO> iterator = parameters.getJournalentryList().iterator();
		while (iterator.hasNext()) {
			JournalEntryLinesTO Detalle = (JournalEntryLinesTO) iterator.next();
			if(Detalle.getDebit()==null){
				Detalle.setDebit(zero);
			}if(Detalle.getCredit()==null){
				Detalle.setCredit(zero);
			}if(Detalle.getTomthsum()==null){
				Detalle.setTomthsum(zero);
			}if(Detalle.getBasesum()==null){
				Detalle.setBasesum(zero);
			}if(Detalle.getVatrate()==null){
				Detalle.setVatrate(zero);
			}if(Detalle.getSysbasesum()==null){
				Detalle.setSysbasesum(zero);
			}if(Detalle.getVatamount()==null){
				Detalle.setVatamount(zero);
			}if(Detalle.getGrossvalue()==null){
				Detalle.setGrossvalue(zero);
			}if(Detalle.getBalduedeb()==null){
				Detalle.setBalduedeb(zero);
			}if(Detalle.getBalduecred()==null){
				Detalle.setBalduecred(zero);
			}if(Detalle.getTotalvat()==null){
				Detalle.setTotalvat(zero);
			}
			// Para articulos nuevos
			System.out.println("" + _return + "");
			Detalle.setTransid(_return.getDocentry());
			if (action == Common.MTTOINSERT) {
				JournalLinesDAO.journalEntryLines_mtto(Detalle,Common.MTTOINSERT);
			}
			if (action == Common.MTTODELETE) {
				JournalLinesDAO.journalEntryLines_mtto(Detalle,Common.MTTODELETE);
			}
		}
		DAO.forceCommit();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		DAO.rollBackConnection();
		throw (EJBException) new EJBException(e);
	} finally {

		DAO.forceCloseConnection();
		JournalLinesDAO.forceCloseConnection();
	}
	_return.setCodigoError(0);
	_return.setMensaje("Datos guardados con exito");
	return _return;
	}
	
	//################### BUDGET ######################
	public ResultOutTO cat_budget_mtto(BudgetTO parameters, int action) throws EJBException{
		
		ResultOutTO _return = new ResultOutTO();
		AccountingDAO DAO = new AccountingDAO();
		DAO.setIstransaccional(true);
		if(parameters.getCrdrltotal()==null){
			parameters.setCrdrltotal(zero);
		}
		if(parameters.getCredltotal()==null){
			parameters.setCredltotal(zero);
		}
		if(parameters.getDebltotal()==null){
			parameters.setDebltotal(zero);
		}
		if(parameters.getDebrltotal()==null){
			parameters.setDebrltotal(zero);
		}
		if(parameters.getFthrprcnt()==null){
			parameters.setFthrprcnt(zero);
		}
		if(parameters.getFtridrlsum()==null){
			parameters.setFtridrlsum(zero);
		}
		if(parameters.getFtridrssum()==null){
			parameters.setFtridrssum(zero);
		}
		if(parameters.getFtrocrlsum()==null){
			parameters.setFtrocrlsum(zero);
		}
		if(parameters.getFtrodrlsum()==null){
			parameters.setFtrodrlsum(zero);
		}
		try {
		_return.setDocentry(DAO.cat_budget_mtto(parameters, action));
		DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos Ingresados con Éxito");
		return _return;
	}
	
	public List getBudget(int _bgdcode) throws EJBException{
		List _return = new Vector();
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return= DAO.getBudget(_bgdcode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	//######################## RecurringPosting ####################
	public ResultOutTO fin_recurringPosting_mtto(RecurringPostingsTO parameters,int action) throws EJBException {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();
		List Detalles= new Vector();
		RecurringPostingsTO padre= new RecurringPostingsTO();
		AccountingDAO DAO = new AccountingDAO();
		DAO.setIstransaccional(true);
		if(parameters.getFinancvol()==null){
			parameters.setFinancvol(zero);
		}
		if(parameters.getVolume()==null){
			parameters.setVolume(zero);
		}
		try {
		//eliminar los detalles registrados antes de un update o en caso de ser delete la accion
		if(action==Common.MTTOUPDATE || action==Common.MTTODELETE){
			padre=DAO.getrecurringPosting_by_key(parameters.getRcurcode(),parameters.getInstance());
			
			Detalles=padre.getRecurringPostingsDetail();
			
				Iterator<RecurringPostingsDetailTO> iterator2 =Detalles.iterator();
				while (iterator2.hasNext()) {
					RecurringPostingsDetailTO Detallex = (RecurringPostingsDetailTO) iterator2.next();
					DAO.fin_recurringPostingDetail_mtto(Detallex,Common.MTTODELETE);
				}
			}
		//verificar la accion para obtener los hijos e insertarlos en ambos casos (ya que se eliminan al principio si es un update)
		if(action==Common.MTTOINSERT || action==Common.MTTOUPDATE){
		Iterator<RecurringPostingsDetailTO> iterator = parameters.getRecurringPostingsDetail().iterator();
		while (iterator.hasNext()) {
			RecurringPostingsDetailTO Detalle = (RecurringPostingsDetailTO) iterator.next();
			// Para articulos nuevos
			//System.out.println("" + _return + "");
			Detalle.setRcurcode(parameters.getRcurcode());
			if(Detalle.getCredit()==null){
				Detalle.setCredit(zero);
			}
			if(Detalle.getDebit()==null){
				Detalle.setDebit(zero);
			}
			if(Detalle.getGrossvalue()==null){
				Detalle.setGrossvalue(zero);
			}
			if (action == Common.MTTOINSERT) {
				DAO.fin_recurringPostingDetail_mtto(Detalle,Common.MTTOINSERT);
			}
			if (action == Common.MTTOUPDATE) {
				DAO.fin_recurringPostingDetail_mtto(Detalle,Common.MTTOINSERT);
			}
		}
		}
		DAO.fin_recurringPosting_mtto(parameters, action);
		DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos Ingresados con Éxito");
		return _return;
	}

	public List getrecurringPosting(RecurringPostingsInTO parameters)throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return= DAO.getrecurringPosting(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public RecurringPostingsTO getrecurringPosting_by_key(String _rcurcode,int _instance) throws EJBException {
		// TODO Auto-generated method stub
		RecurringPostingsTO _return= new RecurringPostingsTO();
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return= DAO.getrecurringPosting_by_key(_rcurcode,_instance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public List getrecurringPostingExecute()throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return= DAO.getrecurringPostingExecute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}
	
}

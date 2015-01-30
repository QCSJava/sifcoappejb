package com.sifcoapp.bussinessLogic;

import java.util.List;
import java.util.Vector;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import com.sifcoapp.objects.accounting.dao.AccountingDAO;
import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.catalogos.Common;

/**
 * Session Bean implementation class AccountingEJB
 */
@Stateless
public class AccountingEJB implements AccountingEJBRemote {

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
		// TODO Auto-generated method stub
		List _return = new Vector();
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return= DAO.getAccountByFilter(acctcode, acctname);
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
	
}

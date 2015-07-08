package com.sifcoapp.bussinessLogic;

import java.sql.Connection;
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
import com.sifcoapp.objects.accounting.to.EntryTO;
import com.sifcoapp.objects.accounting.to.JournalEntryInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsDetailTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsInTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsTO;
import com.sifcoapp.objects.admin.dao.ParameterDAO;
import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;

/**
 * Session Bean implementation class AccountingEJB
 */
@Stateless
public class AccountingEJB implements AccountingEJBRemote {
	Double zero = 0.00;

	/**
	 * Default constructor.
	 * 
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

	public ResultOutTO validate_exist_accperiod(Date parameters)
			throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		AccountingDAO acc = new AccountingDAO();
		AccPeriodTO period = new AccPeriodTO();
		try {
			period = acc.validate_exist_accperiod(parameters);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			_return.setCodigoError(1);
			_return.setMensaje("no se encontro periodo contable");
			e.printStackTrace();
		}
		if (period.getPeriodstat() == 1) {
			_return.setCodigoError(0);
			_return.setMensaje("periodo contable activo ");
		} else {
			_return.setCodigoError(1);
			_return.setMensaje("periodo contable inactivo");
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

	public int cat_accPeriod_mtto(int parameters, int usersign, int action)
			throws EJBException {

		int _return = 0;

		// Dividir el año en 12 periodos y crear objeto

		/*
		 * Agregar validadiones - Se haran desde la base - Que no este creado el
		 * año - Que el año sea mayor al actual
		 */
		AccountingDAO DAO = new AccountingDAO();
		// para el manejo de transacciones
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

	public ResultOutTO cat_accAssignment_mtto(AccassignmentTO parameters,
			int action) throws EJBException {
		ResultOutTO _return = new ResultOutTO();

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

	public List getAccountByFilter(String acctcode, String acctname)
			throws EJBException {
		return getAccountByFilter(acctcode, acctname, null);
	}

	public List getAccountByFilter(String acctcode, String acctname,
			String postable) throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return = DAO.getAccountByFilter(acctcode, acctname, postable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public AccountTO getAccountByKey(String acctcode) throws EJBException {
		// TODO Auto-generated method stub
		AccountTO acc = new AccountTO();
		AccountingDAO DAO = new AccountingDAO();
		try {
			acc = DAO.getAccountByKey(acctcode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return acc;
	}

	public int cat_acc0_ACCOUNT_mtto(AccountTO parameters, int action)
			throws EJBException {
		// TODO Auto-generated method stub
		int _return = 0;
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return = DAO.cat_acc0_ACCOUNT_mtto(parameters, action);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public int update_currtotal(AccountTO parameters, Connection conn)
			throws Exception {
		// TODO Auto-generated method stub
		int _return = 0;
		AccountingDAO DAO = new AccountingDAO(conn);
		DAO.setIstransaccional(true);

		_return = DAO.update_currtotal(parameters);

		return _return;
	}

	public List getTreeAccount() throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return = DAO.getTreeAccount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	// ////###### journal entry####/////////////////////////////
	public List getJournalEntry(JournalEntryInTO parameters)
			throws EJBException {
		List _return = new Vector();
		JournalEntryDAO DAO = new JournalEntryDAO();
		try {
			_return = DAO.getJournalEntry(parameters);
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
			_return = DAO.getJournalEntryByKey(transid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public ResultOutTO journalEntry_mtto(JournalEntryTO parameters, int action) {
		ResultOutTO _return = new ResultOutTO();
		JournalEntryDAO DAO = new JournalEntryDAO();
		try {
			_return = journalEntry_mtto(parameters, action, DAO.getConn());
			DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {
			DAO.forceCloseConnection();
		}
		return _return;
	}

	public ResultOutTO journalEntry_mtto(JournalEntryTO parameters, int action,
			Connection _conn) throws Exception {

		JournalEntryDAO DAO = new JournalEntryDAO(_conn);
		double debe = 0.0;
		double haber = 0.0;
		ResultOutTO _return = new ResultOutTO();

		DAO.setIstransaccional(true);
		JournalEntryLinesDAO JournalLinesDAO = new JournalEntryLinesDAO(_conn);
		JournalLinesDAO.setIstransaccional(true);

		Iterator<JournalEntryLinesTO> iterator1 = parameters
				.getJournalentryList().iterator();
		while (iterator1.hasNext()) {
			JournalEntryLinesTO Detalle = (JournalEntryLinesTO) iterator1
					.next();

			if (Detalle.getDebit() != null) {
				debe = debe + Detalle.getDebit();
			}
			if (Detalle.getCredit() != null) {
				haber = haber + Detalle.getCredit();
			}

		}
		
	//validando que debe y haber sean iguales.....
		
		if(debe==haber){

		// Valores por defecto
		double zero = 0.00;
		parameters.setObjtype("5");
	    parameters.setBtfstatus("O");
		parameters.setRefndrprt("N");
		parameters.setAdjtran("N");
		parameters.setAutostorno("N");
		parameters.setAutovat("N");
		parameters.setPrinted("N");
		parameters.setAutowt("N");
		parameters.setDeferedtax("N");

		if (parameters.getLoctotal() == null) {
			parameters.setLoctotal(zero);
		}
		if (parameters.getSystotal() == null) {
			parameters.setSystotal(zero);
		}
		if (parameters.getTransrate() == null) {
			parameters.setTransrate(zero);
		}
		if (parameters.getWtapplied() == null) {
			parameters.setWtapplied(zero);
		}
		if (parameters.getBaseamnt() == null) {
			parameters.setBaseamnt(zero);
		}
		if (parameters.getBasevtat() == null) {
			parameters.setBasevtat(zero);
		}

		// Guardar encabezado

		_return.setDocentry(DAO.journalEntry_mtto(parameters, action));
		_return.getDocentry();

		// Guardar detalle
		Iterator<JournalEntryLinesTO> iterator = parameters
				.getJournalentryList().iterator();
		while (iterator.hasNext()) {
			JournalEntryLinesTO Detalle = (JournalEntryLinesTO) iterator.next();

			// ------------------------------------------------------------------------------------------------------------
			// Valores por defecto del detalle
			// ------------------------------------------------------------------------------------------------------------

			AccountTO account = new AccountTO();

			
		
			
			
		
			Detalle.setReltransid(-1);
			Detalle.setRellineid(-1);
			Detalle.setReltype("N");
			Detalle.setObjtype("5");
			Detalle.setVatline("N");
			Detalle.setVatamount(0.0);
			Detalle.setClosed("N");
			Detalle.setGrossvalue(0.0);
	        Detalle.setIsnet("Y");
			Detalle.setTaxtype(0);
			Detalle.setTaxpostacc("N");
			Detalle.setTotalvat(0.0);
			Detalle.setWtliable("N");
			Detalle.setWtline("N");
			Detalle.setPayblock("N");
			Detalle.setOrdered("N");
		
			if (Detalle.getDebit() == null || Detalle.getDebit() == zero) {
				Detalle.setDebit(zero);
				Detalle.setDebcred("C");
				account.setCurrtotal(Detalle.getCredit());

			} else {
				Detalle.setCredit(zero);
				Detalle.setDebcred("D");
				account.setCurrtotal(Detalle.getDebit());
			}
			/*
			 * if (Detalle.getCredit() == null) { Detalle.setCredit(zero); }
			 * else { Detalle.setDebcred("C");
			 * account.setCurrtotal(Detalle.getCredit()); }
			 */

			if (Detalle.getTomthsum() == null) {
				Detalle.setTomthsum(zero);
			}
			if (Detalle.getBasesum() == null) {
				Detalle.setBasesum(zero);
			}
			if (Detalle.getVatrate() == null) {
				Detalle.setVatrate(zero);
			}
			if (Detalle.getSysbasesum() == null) {
				Detalle.setSysbasesum(zero);
			}
			if (Detalle.getVatamount() == null) {
				Detalle.setVatamount(zero);
			}
			if (Detalle.getGrossvalue() == null) {
				Detalle.setGrossvalue(zero);
			}
			if (Detalle.getBalduedeb() == null) {
				Detalle.setBalduedeb(zero);
			}
			if (Detalle.getBalduecred() == null) {
				Detalle.setBalduecred(zero);
			}
			if (Detalle.getTotalvat() == null) {
				Detalle.setTotalvat(zero);
			}

			// ---------------------------------------------------------------------------------------------------------------
			Detalle.setTransid(_return.getDocentry());
			Detalle.setTranstype(Integer.toString(_return.getDocentry()));
			if (action == Common.MTTOINSERT) {
				JournalLinesDAO.journalEntryLines_mtto(Detalle,
						Common.MTTOINSERT);
			}
			if (action == Common.MTTODELETE) {
				JournalLinesDAO.journalEntryLines_mtto(Detalle,
						Common.MTTODELETE);
			}

			// ---------------------------------------------------------------------------------------------------------------
			// actualizacion de saldo de cuenta
			// ---------------------------------------------------------------------------------------------------------------

			account.setAcctcode(Detalle.getAccount());
			update_currtotal(account, Detalle.getDebcred(), _conn);

		}
		}else{
			_return.setCodigoError(1);
			_return.setMensaje("error en el almacenamiento de datos");
			return _return;
		}
		
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public ResultOutTO update_currtotal(AccountTO Account, String action,
			Connection conn) throws Exception {
		ResultOutTO _return = new ResultOutTO();
		AccountTO account1 = new AccountTO();
		double saldo;
		account1 = getAccountByKey(Account.getAcctcode());
		// ------------------------------------------------------------------------------------------------------------------
		// 1 si su saldo esta en el debe Y 2 si su saldo estan el haber
		if (account1 == null || account1.getPostable().equals("N")) {
			throw new Exception(
					"La cuenta contable no existe o no es posteable");
		}

		switch (account1.getGroupmask()) {
		// ------------------------------------------------------------------------------------------------------------------
		// 1- Activo
		// ------------------------------------------------------------------------------------------------------------------

		case 1:
			if (action.equals("D")) {
				saldo = Account.getCurrtotal() + account1.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else if (action.equals("C")) {
				saldo = account1.getCurrtotal() - Account.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else {
				throw new Exception(
						"Error en cuenta contable, informar al administrador");

			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// 2- Pasivo
		// ------------------------------------------------------------------------------------------------------------------
		case 2:
			if (action.equals("C")) {
				saldo = Account.getCurrtotal() + account1.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else if (action.equals("D")) {
				saldo = account1.getCurrtotal() - Account.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else {
				throw new Exception(
						"Error en cuenta contable, informar al administrador");

			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// 3-Capital
		// ------------------------------------------------------------------------------------------------------------------
		case 3:
			if (action.equals("C")) {
				saldo = Account.getCurrtotal() + account1.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else if (action.equals("D")) {
				saldo = account1.getCurrtotal() - Account.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else {
				throw new Exception(
						"Error en cuenta contable, informar al administrador");

			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// 4- Cuentas de costos y gastos
		// ------------------------------------------------------------------------------------------------------------------
		case 4:
			if (action.equals("D")) {
				saldo = Account.getCurrtotal() + account1.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else if (action.equals("C")) {
				saldo = account1.getCurrtotal() - Account.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else {
				throw new Exception(
						"Error en cuenta contable, informar al administrador");

			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// 5- Cuentas de Ingresos
		// ------------------------------------------------------------------------------------------------------------------
		case 5:
			if (action.equals("C")) {
				saldo = Account.getCurrtotal() + account1.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else if (action.equals("D")) {
				saldo = account1.getCurrtotal() - Account.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else {
				throw new Exception(
						"Error en cuenta contable, informar al administrador");

			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// 6- Cuentas de Gastos
		// ------------------------------------------------------------------------------------------------------------------
		case 6:
			if (action.equals("D")) {
				saldo = Account.getCurrtotal() + account1.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else if (action.equals("C")) {
				saldo = account1.getCurrtotal() - Account.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else {
				throw new Exception(
						"Error en cuenta contable, informar al administrador");

			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// Otros Ingresos
		// ------------------------------------------------------------------------------------------------------------------
		case 7:
			if (action.equals("C")) {
				saldo = Account.getCurrtotal() + account1.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else if (action.equals("D")) {
				saldo = account1.getCurrtotal() - Account.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else {
				throw new Exception(
						"Error en cuenta contable, informar al administrador");

			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// 8 - Otros Gastos
		// ------------------------------------------------------------------------------------------------------------------
		case 8:
			if (action.equals("D")) {
				saldo = Account.getCurrtotal() + account1.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else if (action.equals("C")) {
				saldo = account1.getCurrtotal() - Account.getCurrtotal();
				account1.setCurrtotal(saldo);
				int i = update_currtotal(account1, conn);
			} else {
				throw new Exception(
						"Error en cuenta contable, informar al administrador");

			}
			break;

		default:
			throw new Exception(
					"Error en cuenta contable, informar al administrador");
		}

		_return.setCodigoError(0);
		_return.setMensaje("cuenta actualizada correctamente");
		return _return;

	}

	// ################### BUDGET ######################
	public ResultOutTO cat_budget_mtto(BudgetTO parameters, int action)
			throws EJBException {

		ResultOutTO _return = new ResultOutTO();
		AccountingDAO DAO = new AccountingDAO();
		DAO.setIstransaccional(true);
		if (parameters.getCrdrltotal() == null) {
			parameters.setCrdrltotal(zero);
		}
		if (parameters.getCredltotal() == null) {
			parameters.setCredltotal(zero);
		}
		if (parameters.getDebltotal() == null) {
			parameters.setDebltotal(zero);
		}
		if (parameters.getDebrltotal() == null) {
			parameters.setDebrltotal(zero);
		}
		if (parameters.getFthrprcnt() == null) {
			parameters.setFthrprcnt(zero);
		}
		if (parameters.getFtridrlsum() == null) {
			parameters.setFtridrlsum(zero);
		}
		if (parameters.getFtridrssum() == null) {
			parameters.setFtridrssum(zero);
		}
		if (parameters.getFtrocrlsum() == null) {
			parameters.setFtrocrlsum(zero);
		}
		if (parameters.getFtrodrlsum() == null) {
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

	public List getBudget(int _bgdcode) throws EJBException {
		List _return = new Vector();
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return = DAO.getBudget(_bgdcode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	// ######################## RecurringPosting ####################
	public ResultOutTO fin_recurringPosting_mtto(
			RecurringPostingsTO parameters, int action) throws EJBException {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();
		List Detalles = new Vector();
		RecurringPostingsTO padre = new RecurringPostingsTO();
		AccountingDAO DAO = new AccountingDAO();
		DAO.setIstransaccional(true);
		if (parameters.getFinancvol() == null) {
			parameters.setFinancvol(zero);
		}
		if (parameters.getVolume() == null) {
			parameters.setVolume(zero);
		}
		try {
			// eliminar los detalles registrados antes de un update o en caso de
			// ser delete la accion
			if (action == Common.MTTOUPDATE || action == Common.MTTODELETE) {
				padre = DAO.getrecurringPosting_by_key(
						parameters.getRcurcode(), parameters.getInstance());

				Detalles = padre.getRecurringPostingsDetail();

				Iterator<RecurringPostingsDetailTO> iterator2 = Detalles
						.iterator();
				while (iterator2.hasNext()) {
					RecurringPostingsDetailTO Detallex = (RecurringPostingsDetailTO) iterator2
							.next();
					DAO.fin_recurringPostingDetail_mtto(Detallex,
							Common.MTTODELETE);
				}
			}
			// verificar la accion para obtener los hijos e insertarlos en ambos
			// casos (ya que se eliminan al principio si es un update)
			if (action == Common.MTTOINSERT || action == Common.MTTOUPDATE) {
				Iterator<RecurringPostingsDetailTO> iterator = parameters
						.getRecurringPostingsDetail().iterator();
				while (iterator.hasNext()) {
					RecurringPostingsDetailTO Detalle = (RecurringPostingsDetailTO) iterator
							.next();
					// Para articulos nuevos
					// System.out.println("" + _return + "");
					Detalle.setRcurcode(parameters.getRcurcode());
					if (Detalle.getCredit() == null) {
						Detalle.setCredit(zero);
					}
					if (Detalle.getDebit() == null) {
						Detalle.setDebit(zero);
					}
					if (Detalle.getGrossvalue() == null) {
						Detalle.setGrossvalue(zero);
					}
					if (action == Common.MTTOINSERT) {
						DAO.fin_recurringPostingDetail_mtto(Detalle,
								Common.MTTOINSERT);
					}
					if (action == Common.MTTOUPDATE) {
						DAO.fin_recurringPostingDetail_mtto(Detalle,
								Common.MTTOINSERT);
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

	public List getrecurringPosting(RecurringPostingsInTO parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return = DAO.getrecurringPosting(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public List getrecurringPosting_user(RecurringPostingsInTO parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		ParameterDAO parameter = new ParameterDAO();
		parameterTO param = new parameterTO();
		// cosulta en la tabla parametros para comparar si el usuario es
		// administrador
		try {
			param = parameter.getParameterbykey(8);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// si es administrador llama al metodo getrecurringPosting() para
		// mostrar todos los recurringPosting
		if (parameters.getUsersign() == param.getUsersign()) {

			_return = getrecurringPosting(parameters);
		} else {

			AccountingDAO DAO = new AccountingDAO();
			try {
				_return = DAO.getrecurringPosting_user(parameters);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw (EJBException) new EJBException(e);
			}
		}
		return _return;

	}

	public RecurringPostingsTO getrecurringPosting_by_key(String _rcurcode,
			int _instance) throws EJBException {
		// TODO Auto-generated method stub
		RecurringPostingsTO _return = new RecurringPostingsTO();
		AccountingDAO DAO = new AccountingDAO();
		try {
			_return = DAO.getrecurringPosting_by_key(_rcurcode, _instance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public List getrecurringPostingExecute() throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		AccountingDAO DAO = new AccountingDAO();
		try {

			_return = DAO.getrecurringPostingExecute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public ResultOutTO saveTreeAccount(List parameters) throws EJBException {

		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();

		AccountingDAO DAO = new AccountingDAO();
		DAO.setIstransaccional(true);
		AccountTO node = new AccountTO();
		AccountTO account = new AccountTO();
		try {

			for (Object object : parameters) {
				AccountingDAO DAO1 = new AccountingDAO(DAO.getConn());
				DAO1.setIstransaccional(true);
				node = (AccountTO) object;
				if (!(node.getLevels() == 1)) {
					account = DAO1.getAccountByKey(node.getAcctcode());
					if (account.getAcctcode() != null) {

						int i = DAO1.account_mtto_new(node);
					} else {
						node.setCurrtotal(zero);
						node.setEndtotal(zero);
						int i = DAO1.cat_acc0_ACCOUNT_mtto(node,
								Common.MTTOINSERT);
					}

				}

			}
			DAO.forceCommit();
		} catch (Exception e) {
			// TODO: handle exception
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados correctamente");
		return _return;
	}

	public List getEntryDetail(JournalEntryLinesInTO parameters)
			throws Exception {
		List journal = new Vector();
		JournalEntryLinesTO nuevo = new JournalEntryLinesTO();
		String action = " ";
		JournalEntryLinesDAO DAO = new JournalEntryLinesDAO();
		JournalEntryLinesDAO DAO1 = new JournalEntryLinesDAO();
		AccountTO account1 = new AccountTO();
		// consultamos la cuenta para saber el groupmask para realizar los
		// calculos
		account1 = getAccountByKey(parameters.getAccount());

		double saldo1 = 0;
		// salñdo inicial antes de la fecha indicada
		journal = DAO.getEntryDetail(parameters);
		EntryTO entrada = new EntryTO();
		entrada.setAcctcode(parameters.getAccount());
		entrada.setRefdate(parameters.getRefdate());
		nuevo = DAO1.getsaldo(entrada);
		double saldo = nuevo.getTotalvat();
		nuevo.setLinememo("saldo inicial a la fecha");

		// -----------------------------------------------------------------------------------------------------------
		switch (account1.getGroupmask()) {
		// ------------------------------------------------------------------------------------------------------------------
		// 1- Activo
		// ------------------------------------------------------------------------------------------------------------------

		case 1:

			Iterator<JournalEntryLinesTO> iterator = journal.iterator();
			while (iterator.hasNext()) {
				JournalEntryLinesTO Detalle = (JournalEntryLinesTO) iterator
						.next();
				// se asigna a la inversa para encontrar el saldo inicial antes
				// de cada movimiento
				if (Detalle.getDebit() > 0.0) {
					action = "D";

				}
				if (Detalle.getCredit() > 0.0) {
					action = "C";
				}

				if (action.equals("C")) {
					saldo = saldo - Detalle.getCredit();
					Detalle.setTotalvat(saldo);

				} else if (action.equals("D")) {
					saldo = saldo + Detalle.getDebit();
					Detalle.setTotalvat(saldo);
				} else {
					throw new Exception(
							"Error en cuenta contable, informar al administrador");

				}
			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// 2- Pasivo
		// ------------------------------------------------------------------------------------------------------------------
		case 2:
			Iterator<JournalEntryLinesTO> iterator5 = journal.iterator();
			while (iterator5.hasNext()) {
				JournalEntryLinesTO Detalle = (JournalEntryLinesTO) iterator5
						.next();
				// se asigna a la inversa para encontrar el saldo inicial antes
				// de cada movimiento
				if (Detalle.getDebit() > 0.0) {
					action = "D";

				}
				if (Detalle.getCredit() > 0.0) {
					action = "C";
				}

				if (action.equals("C")) {
					saldo = saldo + Detalle.getCredit();
					Detalle.setTotalvat(saldo);

				} else if (action.equals("D")) {
					saldo = saldo - Detalle.getDebit();
					Detalle.setTotalvat(saldo);
				} else {
					throw new Exception(
							"Error en cuenta contable, informar al administrador");

				}
			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// 3-Capital
		// ------------------------------------------------------------------------------------------------------------------
		case 3:
			Iterator<JournalEntryLinesTO> iterator6 = journal.iterator();
			while (iterator6.hasNext()) {
				JournalEntryLinesTO Detalle = (JournalEntryLinesTO) iterator6
						.next();
				// se asigna a la inversa para encontrar el saldo inicial antes
				// de cada movimiento
				if (Detalle.getDebit() > 0.0) {
					action = "D";

				}
				if (Detalle.getCredit() > 0.0) {
					action = "C";
				}

				if (action.equals("C")) {
					saldo = saldo + Detalle.getCredit();
					Detalle.setTotalvat(saldo);

				} else if (action.equals("D")) {
					saldo = saldo - Detalle.getDebit();
					Detalle.setTotalvat(saldo);
				} else {
					throw new Exception(
							"Error en cuenta contable, informar al administrador");

				}
			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// 4- Cuentas de costos y gastos
		// ------------------------------------------------------------------------------------------------------------------
		case 4:
			Iterator<JournalEntryLinesTO> iterator2 = journal.iterator();
			while (iterator2.hasNext()) {
				JournalEntryLinesTO Detalle = (JournalEntryLinesTO) iterator2
						.next();
				// se asigna a la inversa para encontrar el saldo inicial antes
				// de cada movimiento
				if (Detalle.getDebit() > 0.0) {
					action = "D";

				}
				if (Detalle.getCredit() > 0.0) {
					action = "C";
				}

				if (action.equals("C")) {
					saldo = saldo - Detalle.getCredit();
					Detalle.setTotalvat(saldo);

				} else if (action.equals("D")) {
					saldo = saldo + Detalle.getDebit();
					Detalle.setTotalvat(saldo);
				} else {
					throw new Exception(
							"Error en cuenta contable, informar al administrador");

				}
			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// 5- Cuentas de Ingresos
		// ------------------------------------------------------------------------------------------------------------------
		case 5:
			Iterator<JournalEntryLinesTO> iterator7 = journal.iterator();
			while (iterator7.hasNext()) {
				JournalEntryLinesTO Detalle = (JournalEntryLinesTO) iterator7
						.next();
				// se asigna a la inversa para encontrar el saldo inicial antes
				// de cada movimiento
				if (Detalle.getDebit() > 0.0) {
					action = "D";

				}
				if (Detalle.getCredit() > 0.0) {
					action = "C";
				}

				if (action.equals("C")) {
					saldo = saldo + Detalle.getCredit();
					Detalle.setTotalvat(saldo);

				} else if (action.equals("D")) {
					saldo = saldo - Detalle.getDebit();
					Detalle.setTotalvat(saldo);
				} else {
					throw new Exception(
							"Error en cuenta contable, informar al administrador");

				}
			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// 6- Cuentas de Gastos
		// ------------------------------------------------------------------------------------------------------------------
		case 6:
			Iterator<JournalEntryLinesTO> iterator3 = journal.iterator();
			while (iterator3.hasNext()) {
				JournalEntryLinesTO Detalle = (JournalEntryLinesTO) iterator3
						.next();
				// se asigna a la inversa para encontrar el saldo inicial antes
				// de cada movimiento
				if (Detalle.getDebit() > 0.0) {
					action = "D";

				}
				if (Detalle.getCredit() > 0.0) {
					action = "C";
				}

				if (action.equals("C")) {
					saldo = saldo - Detalle.getCredit();
					Detalle.setTotalvat(saldo);

				} else if (action.equals("D")) {
					saldo = saldo + Detalle.getDebit();
					Detalle.setTotalvat(saldo);
				} else {
					throw new Exception(
							"Error en cuenta contable, informar al administrador");

				}
			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// Otros Ingresos
		// ------------------------------------------------------------------------------------------------------------------
		case 7:
			Iterator<JournalEntryLinesTO> iterator8 = journal.iterator();
			while (iterator8.hasNext()) {
				JournalEntryLinesTO Detalle = (JournalEntryLinesTO) iterator8
						.next();
				// se asigna a la inversa para encontrar el saldo inicial antes
				// de cada movimiento
				if (Detalle.getDebit() > 0.0) {
					action = "D";

				}
				if (Detalle.getCredit() > 0.0) {
					action = "C";
				}

				if (action.equals("C")) {
					saldo = saldo + Detalle.getCredit();
					Detalle.setTotalvat(saldo);

				} else if (action.equals("D")) {
					saldo = saldo - Detalle.getDebit();
					Detalle.setTotalvat(saldo);
				} else {
					throw new Exception(
							"Error en cuenta contable, informar al administrador");

				}
			}
			break;
		// ------------------------------------------------------------------------------------------------------------------
		// 8 - Otros Gastos
		// ------------------------------------------------------------------------------------------------------------------
		case 8:
			Iterator<JournalEntryLinesTO> iterator4 = journal.iterator();
			while (iterator4.hasNext()) {
				JournalEntryLinesTO Detalle = (JournalEntryLinesTO) iterator4
						.next();
				// se asigna a la inversa para encontrar el saldo inicial antes
				// de cada movimiento
				if (Detalle.getDebit() > 0.0) {
					action = "D";

				}
				if (Detalle.getCredit() > 0.0) {
					action = "C";
				}

				if (action.equals("C")) {
					saldo = saldo - Detalle.getCredit();
					Detalle.setTotalvat(saldo);

				} else if (action.equals("D")) {
					saldo = saldo + Detalle.getDebit();
					Detalle.setTotalvat(saldo);
				} else {
					throw new Exception(
							"Error en cuenta contable, informar al administrador");

				}
			}
			break;

		default:
			throw new Exception(
					"Error en cuenta contable, informar al administrador");
		}
		journal.add(nuevo);

		return journal;
	}

	public JournalEntryLinesTO getsaldo(EntryTO parameters) throws EJBException {
		JournalEntryLinesTO _return = new JournalEntryLinesTO();

		JournalEntryLinesDAO DAO = new JournalEntryLinesDAO();
		try {
			_return = DAO.getsaldo(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	// -----------------------------------------------------------
	// elementos de prueba
	// -----------------------------------------------------------

	public JournalEntryTO getpruebaByKey(int transid) throws EJBException {
		JournalEntryTO _return = new JournalEntryTO();
		JournalEntryDAO DAO = new JournalEntryDAO();
		try {
			_return = DAO.getpruebaByKey(transid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public ResultOutTO journal_entry_new(JournalEntryTO parameters, int action)
			throws EJBException {

		JournalEntryDAO DAO = new JournalEntryDAO();

		DAO.setIstransaccional(true);
		JournalEntryLinesDAO JournalLinesDAO = new JournalEntryLinesDAO(
				DAO.getConn());
		JournalLinesDAO.setIstransaccional(true);

		// Valores por defecto
		double zero = 0.00;
		ResultOutTO _return = new ResultOutTO();
		try {

			if (parameters.getLoctotal() == null) {
				parameters.setLoctotal(zero);
			}
			if (parameters.getSystotal() == null) {
				parameters.setSystotal(zero);
			}
			if (parameters.getTransrate() == null) {
				parameters.setTransrate(zero);
			}
			if (parameters.getWtapplied() == null) {
				parameters.setWtapplied(zero);
			}
			if (parameters.getBaseamnt() == null) {
				parameters.setBaseamnt(zero);
			}
			if (parameters.getBasevtat() == null) {
				parameters.setBasevtat(zero);
			}

			// Guardar encabezado

			_return.setDocentry(DAO.journalEntry_mtto(parameters, action));

			_return.getDocentry();

			// Guardar detalle
			Iterator<JournalEntryLinesTO> iterator = parameters
					.getJournalentryList().iterator();
			while (iterator.hasNext()) {
				JournalEntryLinesTO Detalle = (JournalEntryLinesTO) iterator
						.next();

				// ------------------------------------------------------------------------------------------------------------
				// Valores por defecto del detalle
				// ------------------------------------------------------------------------------------------------------------

				AccountTO account = new AccountTO();

				if (Detalle.getDebit() == null) {
					Detalle.setDebit(zero);
				} else {
					Detalle.setDebcred("D");
					account.setCurrtotal(Detalle.getDebit());
				}
				if (Detalle.getCredit() == null) {
					Detalle.setCredit(zero);
				} else {
					Detalle.setDebcred("C");
					account.setCurrtotal(Detalle.getCredit());
				}
				if (Detalle.getTomthsum() == null) {
					Detalle.setTomthsum(zero);
				}
				if (Detalle.getBasesum() == null) {
					Detalle.setBasesum(zero);
				}
				if (Detalle.getVatrate() == null) {
					Detalle.setVatrate(zero);
				}
				if (Detalle.getSysbasesum() == null) {
					Detalle.setSysbasesum(zero);
				}
				if (Detalle.getVatamount() == null) {
					Detalle.setVatamount(zero);
				}
				if (Detalle.getGrossvalue() == null) {
					Detalle.setGrossvalue(zero);
				}
				if (Detalle.getBalduedeb() == null) {
					Detalle.setBalduedeb(zero);
				}
				if (Detalle.getBalduecred() == null) {
					Detalle.setBalduecred(zero);
				}
				if (Detalle.getTotalvat() == null) {
					Detalle.setTotalvat(zero);
				}

				// ---------------------------------------------------------------------------------------------------------------
				Detalle.setTransid(_return.getDocentry());
				Detalle.setTranstype(Integer.toString(_return.getDocentry()));
				if (action == Common.MTTOINSERT) {

					JournalLinesDAO.journalEntryLines_mtto(Detalle,
							Common.MTTOINSERT);

				}
				if (action == Common.MTTODELETE) {
					JournalLinesDAO.journalEntryLines_mtto(Detalle,
							Common.MTTODELETE);
				}

				// ---------------------------------------------------------------------------------------------------------------
				// actualizacion de saldo de cuenta
				// ---------------------------------------------------------------------------------------------------------------

				account.setAcctcode(Detalle.getAccount());
				update_currtotal(account, Detalle.getDebcred(), DAO.getConn());
			}
			DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			_return.setCodigoError(1);
			_return.setMensaje("error en almacenamiento de datos");
			return _return;

		} finally {

			DAO.forceCloseConnection();
		}

		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

}

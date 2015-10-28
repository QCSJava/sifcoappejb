package com.sifcoapp.bussinessLogic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.ExternalReconciliation.dao.BankreconciliationauxDAO;
import com.sifcoapp.objects.ExternalReconciliation.to.ExternalReconciliationTO;
import com.sifcoapp.objects.ExternalReconciliation.to.PendingExternalReconciliationInTO;
import com.sifcoapp.objects.ExternalReconciliation.to.bankreconciliationauxInTO;
import com.sifcoapp.objects.ExternalReconciliation.to.bankreconciliationauxTO;
import com.sifcoapp.objects.accounting.dao.JournalEntryDAO;
import com.sifcoapp.objects.accounting.dao.JournalEntryLinesDAO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.transaction.to.TransactionTo;

@Stateless
public class ExternalReconciliationEJB implements
		ExternalReconciliationEJBRemote {

	public ExternalReconciliationEJB() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * public ResultOutTO bankreconciliationaux_mtto(bankreconciliationauxTO
	 * parameter, int action) throws EJBException { ResultOutTO _return = new
	 * ResultOutTO(); int i = 0; BankreconciliationauxDAO DAO = new
	 * BankreconciliationauxDAO(); try { i =
	 * DAO.bankreconciliationaux_mtto(parameter, action); } catch (Exception e)
	 * { // TODO Auto-generated catch block throw (EJBException) new
	 * EJBException(e); } if (i == 0) { _return.setCodigoError(1);
	 * _return.setMensaje("No es posible ingresar los datos al sistema");
	 * 
	 * } else { _return.setCodigoError(0);
	 * _return.setMensaje("datos  ingresados los datos al sistema");
	 * _return.setDocentry(i); } return _return; }
	 */

	public ResultOutTO bankreconciliationaux_mtto(
			bankreconciliationauxTO parameter, int action) {
		ResultOutTO _return = new ResultOutTO();
		BankreconciliationauxDAO DAO = new BankreconciliationauxDAO();
		try {
			_return = bankreconciliationaux_mtto(parameter, action,
					DAO.getConn());
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

	public ResultOutTO bankreconciliationaux_mtto(
			bankreconciliationauxTO parameters, int action, Connection _conn)
			throws Exception {

		BankreconciliationauxDAO DAO = new BankreconciliationauxDAO(_conn);

		DAO.setIstransaccional(true);

		// Valores por defecto
		double zero = 0.00;
		ResultOutTO _return = new ResultOutTO();

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

		_return.setDocentry(DAO.bankreconciliationaux_mtto(parameters, action));
		_return.getDocentry();

		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public List getBankreconciliationaux(bankreconciliationauxInTO parameters)
			throws EJBException {
		List _return = new Vector();
		BankreconciliationauxDAO DAO = new BankreconciliationauxDAO();
		try {
			_return = DAO.getBankreconciliationaux(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public List getJournalEntryDetail_nc(ExternalReconciliationTO parameters)
			throws EJBException {
		List _return = new Vector();
		BankreconciliationauxDAO DAO = new BankreconciliationauxDAO();

		try {

			_return = DAO.getJournalEntryDetail_nc(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
		}
	public ExternalReconciliationTO get_newExternalReconciliation(
			ExternalReconciliationTO parameters) throws EJBException {

		ExternalReconciliationTO _return = new ExternalReconciliationTO();
		BankreconciliationauxDAO DAO = new BankreconciliationauxDAO();
		BankreconciliationauxDAO DAO1 = new BankreconciliationauxDAO();

		try {
			_return.setAccToConciliate(DAO.getJournalEntryDetail_nc(parameters));
			_return.setAuxiliaryDoc(DAO1
					.getBankreconciliationaux_nc(parameters));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO UpdateExternalReconciliation(ExternalReconciliationTO parameters) throws EJBException {

		ResultOutTO _return = new ResultOutTO();
		
		JournalEntryLinesDAO DAO = new JournalEntryLinesDAO();
		DAO.setIstransaccional(true);
		BankreconciliationauxDAO DAO1 = new BankreconciliationauxDAO(
				DAO.getConn());
		DAO1.setIstransaccional(true);
		try {

			// --------------------------------------------------------------------------------------------------------------------------------
			// Generar numero aleatorio para conciliación
			// --------------------------------------------------------------------------------------------------------------------------------
			int idC = new java.util.Date().getMonth()
					+ new java.util.Date().getHours();
			// --------------------------------------------------------------------------------------------------------------------------------
			// Actualizar detalles asientos contrables
			// --------------------------------------------------------------------------------------------------------------------------------

			for (Object object : parameters.getAccToConciliate()) {
				JournalEntryLinesTO ivt = (JournalEntryLinesTO) object;
				if (ivt.getExtrmatch() == 1) {
					if (ivt.getMthdate() == null) {
						java.util.Date utilDate = new java.util.Date();
						ivt.setDuedate(utilDate);
					}
					ivt.setExtrmatch(idC);				
					DAO.update_conciliate(ivt);
				}				
			}

			// --------------------------------------------------------------------------------------------------------------------------------
			// Actualizar Documentos auxiliares
			// --------------------------------------------------------------------------------------------------------------------------------

			for (Object object : parameters.getAuxiliaryDoc()) {
				bankreconciliationauxTO ivt = (bankreconciliationauxTO) object;
				if (ivt.getExtrmatch() == 1) {
					if (ivt.getDuedate() == null) {
						java.util.Date utilDate = new java.util.Date();
						ivt.setDuedate(utilDate);
					}
					ivt.setExtrmatch(idC);
					DAO1.bankreconciliationaux_mtto(ivt, Common.MTTOUPDATE);
				}
			}

			_return.setCodigoError(0);
			_return.setMensaje("Datos guardados con exito");
			return _return;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {
			DAO.forceCloseConnection();
		}
	}

}

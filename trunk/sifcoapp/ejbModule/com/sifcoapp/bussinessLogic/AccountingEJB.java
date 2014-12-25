package com.sifcoapp.bussinessLogic;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;

import com.sifcoapp.objects.accounting.dao.AccountingDAO;
import com.sifcoapp.objects.accounting.to.AccPeriodInTO;
import com.sifcoapp.objects.accounting.to.AccPeriodOutTO;
import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.admin.dao.AdminDAO;
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

	public List getAccPeriods() {
		List _return = new Vector();
		AccountingDAO DAO = new AccountingDAO();
		_return = DAO.getAccPeriods();

		return _return;

	}

	public int cat_accPeriod_mtto(int parameters, int usersign, int action) {

		int _return = 0;

		// Dividir el a�o en 12 periodos y crear objeto

		/*
		 * Agregar validadiones - Se haran desde la base - Que no este creado el
		 * a�o - Que el a�o sea mayor al actual
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
			_return = DAO.cat_accPeriod_mtto(periodo, action);
		}
		
		DAO.forceCommit();
		DAO.forceCloseConnection();
		
		return _return;
	}

}

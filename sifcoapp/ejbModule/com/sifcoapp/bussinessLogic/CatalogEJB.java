package com.sifcoapp.bussinessLogic;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.accounting.dao.AccountingDAO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.catalog.dao.BusinesspartnerDAO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerAcountTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.purchase.to.PurchaseDetailTO;
import com.sifcoapp.objects.sales.to.SalesTO;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

@Stateless
public class CatalogEJB implements CatalogEJBRemote, CatalogEJBLocal {

	private Double zero = 0.00;

	public CatalogEJB() {
		// TODO Auto-generated constructor stub
	}

	public ResultOutTO cat_bpa_businesspartner_mtto(
			BusinesspartnerTO parameters, int accion) throws EJBException {

		ResultOutTO _return = new ResultOutTO();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		DAO.setIstransaccional(true);
		
		
		if (parameters.getBalancesys() == null) {
			parameters.setBalancesys(zero);
		}
		if (parameters.getBalance() == null) {
			parameters.setBalance(zero);
		}
		if (parameters.getChecksbal() == null) {
			parameters.setChecksbal(zero);
		}
		if (parameters.getCreditline() == null) {
			parameters.setCreditline(zero);
		}
		if (parameters.getDebtline() == null) {
			parameters.setDebtline(zero);
		}
		if (parameters.getDiscount() == null) {
			parameters.setDiscount(0.00);
		}
		if (parameters.getDnotesbal() == null) {
			parameters.setDnotesbal(zero);
		}
		if (parameters.getOrderbalsy() == null) {
			parameters.setOrderbalsy(zero);
		}
		if (parameters.getOrdersbal() == null) {
			parameters.setOrdersbal(zero);
		}

		try {
				if (accion == Common.MTTOINSERT) {
			DAO.inv_cat_bpa_businesspartner_mtto(parameters, accion);
			
			//validacion si el businespartner tiene conceptos asignados 
    if(parameters.getBusinesspartnerAcount()!=null&&parameters.getBusinesspartnerAcount().size()>0){
			
    	Iterator<BusinesspartnerAcountTO> iterator = parameters
					.getBusinesspartnerAcount().iterator();
			while (iterator.hasNext()) {
				BusinesspartnerAcountTO detalleReceipt = (BusinesspartnerAcountTO) iterator
						.next();
				BusinesspartnerDAO DAO1 = new BusinesspartnerDAO(DAO.conn);
				DAO1.setIstransaccional(true);

				DAO1.inv_cat_bpa_businesspartnerAcount_mtto(detalleReceipt,
						Common.MTTOINSERT);
			}
        }//fin del if de validacion 

				}
			// ----------------------------------------------------------------------------------------------------------------------------------------------------
			// para actualizar campos del business partner
			// ----------------------------------------------------------------------------------------------------------------------------------------------------
			
				if (accion == Common.MTTOUPDATE) {
				
//-----------------------------------------------------------------------------------------------------------------------------
				DAO.inv_cat_bpa_businesspartner_mtto(parameters, accion);
				
				BusinesspartnerDAO DAO1 = new BusinesspartnerDAO(DAO.conn);
				DAO1.setIstransaccional(true);
				boolean encontrado = false;
				List bAcc = new Vector();
				List aux = new Vector();

				// -------------------------------------------------------------------------------------------------------
				// llenado de la lista auxiliar con la lista resultado de la
				// consulta de la base
				// -------------------------------------------------------------------------------------------------------
				bAcc = get_businesspartnerAcount(parameters.getCardcode());
				Iterator<BusinesspartnerAcountTO> iter2 = bAcc.iterator();
				while (iter2.hasNext()) {
					
					BusinesspartnerAcountTO bus = (BusinesspartnerAcountTO) iter2
							.next();
					aux.add(bus);
				}

				// -------------------------------------------------------------------------------------------------------
				// recorre las listas para comparar si ya existe el codigo de
				// cuenta dentro de la tabla businesspartnerAcount
				// -------------------------------------------------------------------------------------------------------
				Iterator<BusinesspartnerAcountTO> iterator2 = parameters
						.getBusinesspartnerAcount().iterator();

				while (iterator2.hasNext()) {
					BusinesspartnerAcountTO business = (BusinesspartnerAcountTO) iterator2
							.next();
					encontrado = false;
					Iterator<BusinesspartnerAcountTO> iterator3 = bAcc
							.iterator();
					while (iterator3.hasNext()) {
						BusinesspartnerAcountTO business2 = (BusinesspartnerAcountTO) iterator3
								.next();
						// compara si ya existe el codigo de la cuenta si existe
						// encontrado = true;y elimina el elemento de la lista
						// auxiliar
						if (business.getAcctcode().equals(
								business2.getAcctcode()) &&business.getAcctype()==business2.getAcctype()) {
							encontrado = true;
							aux.remove(business2);
						}

					}

					if (encontrado) {
						System.out.println("encontrado");

					} else {
						DAO1.inv_cat_bpa_businesspartnerAcount_mtto(business,
								Common.MTTOINSERT);
					}
				}// fin del while (iterator2.hasNext())
				
				//Eliminar todos los datos que no fueron tomados en la actualizacion de la tabla
				Iterator<BusinesspartnerAcountTO> iter3 = aux.iterator();
				while (iter3.hasNext()) {
					BusinesspartnerAcountTO bus = (BusinesspartnerAcountTO) iter3
							.next();
					DAO1.inv_cat_bpa_businesspartnerAcount_mtto(bus,
							Common.MTTODELETE);
				}

			//if si no tiene listas en businessparnert acount
				// finalizacion del if superior if (accion == Common.MTTOUPDATE)
			}
				DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos ingresados correctamente");
		return _return;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------------------------------------------
	public List get_businesspartner(BusinesspartnerInTO parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		try {
			_return = DAO.get_businesspartner(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public BusinesspartnerTO get_businesspartnerBykey(String parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		BusinesspartnerTO _return = new BusinesspartnerTO();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		try {
			_return = DAO.get_businesspartnerByKey(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO validate_businesspartnerBykey(String parameters)
			throws EJBException {

		ResultOutTO _return = new ResultOutTO();
		BusinesspartnerTO partner = new BusinesspartnerTO();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		try {
			partner = DAO.get_businesspartnerByKey(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);

		}
		if (partner.getValidfor() != null
				&& partner.getValidfor().toUpperCase().equals("Y")) {
			_return.setCodigoError(0);
			_return.setMensaje("Socio de Negocio Activo");

		} else {
			_return.setCodigoError(1);
			_return.setMensaje("Socio de Negocio No Activo");

		}

		return _return;
	}

	public ResultOutTO validate_limit(SalesTO parameters) throws EJBException {

		ResultOutTO _return = new ResultOutTO();
		AccountTO acc = new AccountTO();
		BusinesspartnerTO partner = new BusinesspartnerTO();
		BusinesspartnerDAO DAO1 = new BusinesspartnerDAO();
		AccountingDAO DAO = new AccountingDAO();

		try {
			acc = DAO.getAccountByKey(parameters.getCtlaccount());
			partner = DAO1.get_businesspartnerByKey(parameters.getCardcode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);

		}

		double credit = acc.getCurrtotal() + parameters.getDoctotal();
		if (partner.getCreditline() == null) {
			_return.setCodigoError(1);
			_return.setMensaje("No posee limite de credito Asignado");
			return _return;

		}
		if (partner.getCreditline() == -1) {
			_return.setCodigoError(0);
			_return.setMensaje("No posee limite de credito");
			return _return;

		}

		if (credit <= partner.getCreditline()) {
			_return.setCodigoError(0);
			_return.setMensaje("No sobre pasa el credito permitido");

		} else {
			_return.setCodigoError(1);
			_return.setMensaje("sobrepasa el credito perrmitido");

		}

		return _return;
	}

	public ResultOutTO inv_cat_bpa_businesspartnerAcount_mtto(
			BusinesspartnerAcountTO parameters, int accion) throws EJBException {

		ResultOutTO _return = new ResultOutTO();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		DAO.setIstransaccional(true);
		if (parameters.getBalance() == null) {
			parameters.setBalance(zero);

		}
		try {
			_return.setDocentry(DAO.inv_cat_bpa_businesspartnerAcount_mtto(
					parameters, accion));
			DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos ingresados correctamente");
		return _return;

	}

	public List get_businesspartnerAcount(String code) throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		try {
			_return = DAO.get_businesspartnerAcount(code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public BusinesspartnerAcountTO get_businesspartnerAcountBykey(
			BusinesspartnerAcountTO parameters) throws EJBException {
		// TODO Auto-generated method stub
		BusinesspartnerAcountTO _return = new BusinesspartnerAcountTO();
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		try {
			_return = DAO.get_businesspartnerAcountByKey(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}
}
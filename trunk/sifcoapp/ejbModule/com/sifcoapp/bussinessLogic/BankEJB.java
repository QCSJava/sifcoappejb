package com.sifcoapp.bussinessLogic;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.bank.dao.CheckForPaymentDAO;
import com.sifcoapp.objects.bank.dao.ColecturiaConceptDAO;
import com.sifcoapp.objects.bank.dao.ColecturiaDAO;
import com.sifcoapp.objects.bank.dao.ColecturiaDetailDAO;
import com.sifcoapp.objects.bank.to.CheckForPaymentInTO;
import com.sifcoapp.objects.bank.to.CheckForPaymentTO;
import com.sifcoapp.objects.bank.to.ColecturiaConceptTO;
import com.sifcoapp.objects.bank.to.ColecturiaDetailTO;
import com.sifcoapp.objects.bank.to.ColecturiaInTO;
import com.sifcoapp.objects.bank.to.ColecturiaTO;
import com.sifcoapp.objects.catalog.dao.BusinesspartnerDAO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.purchase.dao.PurchaseDetailDAO;
import com.sifcoapp.objects.purchase.to.PurchaseDetailTO;

@Stateless
public class BankEJB implements BankEJBRemote {

	private Double zero = 0.00;

	public BankEJB() {
		// TODO Auto-generated constructor stub
	}

	public ResultOutTO ges_cfp0_checkforpayment_mtto(
			CheckForPaymentTO parameters, int action) throws EJBException {

		ResultOutTO _return = new ResultOutTO();
		CheckForPaymentDAO DAO = new CheckForPaymentDAO();
		DAO.setIstransaccional(true);

		if (parameters.getChecksum() == null) {
			parameters.setChecksum(zero);
		}
		if (parameters.getLinessum() == null) {
			parameters.setLinessum(zero);
		}
		if (parameters.getVattotal() == null) {
			parameters.setVattotal(zero);
		}

		try {
			_return.setDocentry(DAO.ges_cfp0_checkforpayment_mtto(parameters,
					action));
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

	public List get_cfp0_checkforpayment(CheckForPaymentInTO parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		CheckForPaymentDAO DAO = new CheckForPaymentDAO();

		if (parameters.getChecksum() == null) {
			parameters.setChecksum(zero);
		}
		if (parameters.getLinessum() == null) {
			parameters.setLinessum(zero);
		}
		if (parameters.getVattotal() == null) {
			parameters.setVattotal(zero);
		}

		try {
			_return = DAO.get_cfp0_checkforpayment(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public CheckForPaymentTO get_cfp0_checkforpaymentByKey(int parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		CheckForPaymentTO _return = new CheckForPaymentTO();
		CheckForPaymentDAO DAO = new CheckForPaymentDAO();
		try {
			_return = DAO.get_cfp0_checkforpaymentByKey(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO ges_ges_col0_colecturia_mtto(ColecturiaTO parameters,
			int action) throws Exception {
		ResultOutTO _return = new ResultOutTO();
		ColecturiaDAO DAO = new ColecturiaDAO();
		ColecturiaDetailDAO DAO1 = new ColecturiaDetailDAO(DAO.getConn());
		DAO.setIstransaccional(true);

		if (parameters.getDoctotal() == null) {
			parameters.setDoctotal(zero);
		}

		try {
			_return.setDocentry(DAO.ges_ges_col0_colecturia_mtto(parameters,
					action));
			// Acciones con los hijos
			Iterator<ColecturiaDetailTO> iterator = parameters
					.getColecturiaDetail().iterator();
			while (iterator.hasNext()) {
				ColecturiaDetailTO detail = (ColecturiaDetailTO) iterator
						.next();
				// Para articulos nuevos
				detail.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					DAO1.ges_ges_col0_colecturiadetail_mtto(detail,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					DAO1.ges_ges_col0_colecturiadetail_mtto(detail,
							Common.MTTODELETE);
				}
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

	public List get_ges_colecturia(ColecturiaInTO parameters)
			throws EJBException {
		List _return = new Vector();
		ColecturiaDAO DAO = new ColecturiaDAO();

		if (parameters.getDoctotal() == null) {
			parameters.setDoctotal(zero);
		}

		try {
			_return = DAO.get_ges_colecturia(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ColecturiaTO get_ges_colecturiaByKey(int parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		ColecturiaTO _return = new ColecturiaTO();
		ColecturiaDAO DAO = new ColecturiaDAO();
		try {
			_return = DAO.get_ges_colecturiaByKey(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public List get_ges_colecturiaConcept() throws EJBException {
		// TODO Auto-generated method stub
				List _return = new Vector();
				ColecturiaConceptDAO DAO = new ColecturiaConceptDAO();
				try {
					_return = DAO.get_ges_colecturiaConcept();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw (EJBException) new EJBException(e);
				}

				return _return;
	}
}

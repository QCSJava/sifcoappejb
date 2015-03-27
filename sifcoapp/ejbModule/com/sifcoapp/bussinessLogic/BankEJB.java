package com.sifcoapp.bussinessLogic;

import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.bank.dao.CheckForPaymentDAO;
import com.sifcoapp.objects.bank.to.CheckForPaymentInTO;
import com.sifcoapp.objects.bank.to.CheckForPaymentTO;
import com.sifcoapp.objects.catalog.dao.BusinesspartnerDAO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

@Stateless
public class BankEJB implements BankEJBRemote {

	private Double zero=0.00;
	
	public BankEJB() {
		// TODO Auto-generated constructor stub
	}

	public ResultOutTO ges_cfp0_checkforpayment_mtto(
			CheckForPaymentTO parameters, int action) throws EJBException {

		ResultOutTO _return = new ResultOutTO();
		CheckForPaymentDAO DAO = new CheckForPaymentDAO();
		DAO.setIstransaccional(true);
		
		if(parameters.getChecksum()==null){
			parameters.setChecksum(zero);
		}
		if(parameters.getLinessum()==null){
			parameters.setLinessum(zero);
		}
		if(parameters.getVattotal()==null){
			parameters.setVattotal(zero);
		}		
		

		try {
			_return.setDocentry(DAO.ges_cfp0_checkforpayment_mtto(parameters, action));
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
		
		if(parameters.getChecksum()==null){
			parameters.setChecksum(zero);
		}
		if(parameters.getLinessum()==null){
			parameters.setLinessum(zero);
		}
		if(parameters.getVattotal()==null){
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
}

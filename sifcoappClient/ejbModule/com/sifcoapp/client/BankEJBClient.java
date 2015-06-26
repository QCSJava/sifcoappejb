package com.sifcoapp.client;

import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingException;


import com.sifcoapp.bussinessLogic.BankEJBRemote;
import com.sifcoapp.bussinessLogic.CatalogEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.bank.to.CheckForPaymentInTO;
import com.sifcoapp.objects.bank.to.CheckForPaymentTO;
import com.sifcoapp.objects.bank.to.ColecturiaConceptTO;
import com.sifcoapp.objects.bank.to.ColecturiaDetailTO;
import com.sifcoapp.objects.bank.to.ColecturiaInTO;
import com.sifcoapp.objects.bank.to.ColecturiaTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

public class BankEJBClient {

	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/BankEJB!com.sifcoapp.bussinessLogic.BankEJBRemote";
	private static BankEJBRemote bean;
	private static Context context = null;

	public BankEJBClient() {

		// 2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			bean = (BankEJBRemote) context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultOutTO ges_cfp0_checkforpayment_mtto(
			CheckForPaymentTO parameters, int action) throws Exception {

		ResultOutTO _return;

		_return = bean.ges_cfp0_checkforpayment_mtto(parameters, action);

		return _return;
	}

	public List get_cfp0_checkforpayment(CheckForPaymentInTO parameters)
			throws Exception {
		List lstPeriods = new Vector();
		lstPeriods = bean.get_cfp0_checkforpayment(parameters);
		return lstPeriods;
	}

	public CheckForPaymentTO get_cfp0_checkforpaymentByKey(int parameters)
			throws Exception {
		// TODO Auto-generated method stub
		CheckForPaymentTO lstPeriods = new CheckForPaymentTO();
		lstPeriods = bean.get_cfp0_checkforpaymentByKey(parameters);
		return lstPeriods;
	}

	public ResultOutTO ges_ges_col0_colecturia_mtto(ColecturiaTO parameters,
			int action) throws Exception {

		ResultOutTO _return;

		_return = bean.ges_ges_col0_colecturia_mtto(parameters, action);

		return _return;
	}

	public List get_ges_colecturia(ColecturiaInTO parameters) throws Exception {
		List lstPeriods = new Vector();
		lstPeriods = bean.get_ges_colecturia(parameters);
		return lstPeriods;
	}
	

	public ColecturiaTO get_ges_colecturiaByKey(int parameters)
			throws Exception {
		// TODO Auto-generated method stub
		ColecturiaTO lstPeriods = new ColecturiaTO();
		lstPeriods = bean.get_ges_colecturiaByKey(parameters);
		return lstPeriods;
	}
	
	public List get_ges_colecturiaConcept()
			throws Exception {
		// TODO Auto-generated method stub
		List lstPeriods = new Vector();
		lstPeriods = bean.get_ges_colecturiaConcept();
		return lstPeriods;
	}
	public List get_ges_colecturiaConcept1(String Code)
			throws Exception {
		// TODO Auto-generated method stub
		List lstPeriods = new Vector();
		lstPeriods = bean.get_ges_colecturiaConcept1( Code);
		return lstPeriods;
	}
	public ResultOutTO ges_ges_col2_colecturiaConcepts_mtto(ColecturiaConceptTO parameters, int action)throws Exception{
		// TODO Auto-generated method stub
				ResultOutTO _return= new ResultOutTO();
				
				_return=bean.ges_ges_col2_colecturiaConcepts_mtto(parameters, action);
				
				return _return;
	}

}

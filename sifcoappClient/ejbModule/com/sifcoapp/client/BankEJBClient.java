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

}

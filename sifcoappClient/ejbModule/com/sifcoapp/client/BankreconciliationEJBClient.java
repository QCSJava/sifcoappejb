package com.sifcoapp.client;

import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.admin.ejb.AdminEJBRemote;
import com.sifcoapp.admin.ejb.ParameterEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.ExternalReconciliation.to.ExternalReconciliationTO;
import com.sifcoapp.objects.ExternalReconciliation.to.bankreconciliationauxInTO;
import com.sifcoapp.objects.ExternalReconciliation.to.bankreconciliationauxTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.admin.dao.ParameterDAO;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesPriceTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.admin.to.PricesListInTO;
import com.sifcoapp.objects.admin.to.PricesListTO;
import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.transaction.to.WarehouseJournalTO;
import com.sifcoapp.admin.ejb.ParameterEJBRemote;
import com.sifcoapp.bussinessLogic.ExternalReconciliationEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;

public class BankreconciliationEJBClient {
	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/ExternalReconciliationEJB!com.sifcoapp.bussinessLogic.ExternalReconciliationEJBRemote";
	private static ExternalReconciliationEJBRemote bean;
	private static Context context = null;

	public BankreconciliationEJBClient() {

		try {
			context = ClientUtility.getInitialContext();
			bean = (ExternalReconciliationEJBRemote) context
					.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultOutTO bankreconciliationaux_mtto(
			bankreconciliationauxTO parameter, int action) throws EJBException {
		ResultOutTO _return = new ResultOutTO();

		_return = bean.bankreconciliationaux_mtto(parameter, action);

		return _return;
	}

	public ExternalReconciliationTO get_newExternalReconciliation(
			ExternalReconciliationTO parameters) throws EJBException {
		ExternalReconciliationTO parameter = new ExternalReconciliationTO();

		parameter = bean.get_newExternalReconciliation(parameters);

		return parameter;

	}

	public ResultOutTO UpdateExternalReconciliation(
			ExternalReconciliationTO parameters) throws EJBException {
		ResultOutTO parameter = new ResultOutTO();

		parameter = bean.UpdateExternalReconciliation(parameters);

		return parameter;

	}
}

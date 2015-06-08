package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.EJBException;

import com.sifcoapp.objects.ExternalReconciliation.to.ExternalReconciliationTO;
import com.sifcoapp.objects.ExternalReconciliation.to.bankreconciliationauxInTO;
import com.sifcoapp.objects.ExternalReconciliation.to.bankreconciliationauxTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.common.to.ResultOutTO;
@Remote
public interface ExternalReconciliationEJBRemote {

	
	public ResultOutTO bankreconciliationaux_mtto(bankreconciliationauxTO parameter,int action)throws EJBException;
	public List getBankreconciliationaux(bankreconciliationauxInTO parameters)throws EJBException;
	public ExternalReconciliationTO get_newExternalReconciliation(ExternalReconciliationTO parameters)throws EJBException;
	
}

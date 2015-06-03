package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.EJBException;

import com.sifcoapp.objects.accounting.to.bankreconciliationauxInTO;
import com.sifcoapp.objects.accounting.to.bankreconciliationauxTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.common.to.ResultOutTO;
@Remote
public interface bankreconciliationauxEJBRemote {

	
	public ResultOutTO bankreconciliationaux_mtto(bankreconciliationauxTO parameter,int action)throws EJBException;
	public List getBankreconciliationaux(bankreconciliationauxInTO parameters)throws EJBException;
	
}

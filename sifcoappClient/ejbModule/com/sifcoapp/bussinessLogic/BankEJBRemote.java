package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.bank.to.CheckForPaymentInTO;
import com.sifcoapp.objects.bank.to.CheckForPaymentTO;
import com.sifcoapp.objects.bank.to.ColecturiaConceptTO;
import com.sifcoapp.objects.bank.to.ColecturiaDetailTO;
import com.sifcoapp.objects.bank.to.ColecturiaInTO;
import com.sifcoapp.objects.bank.to.ColecturiaTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

@Remote
public interface BankEJBRemote {

	public ResultOutTO ges_cfp0_checkforpayment_mtto(
			CheckForPaymentTO parameters, int action) throws Exception;

	public ResultOutTO ges_cfp0_checkforpayment_annular(int parameters)
			throws EJBException;
	
	public List get_cfp0_checkforpayment(CheckForPaymentInTO parameters)
			throws EJBException;

	public CheckForPaymentTO get_cfp0_checkforpaymentByKey(int parameters)
			throws EJBException;

	public ResultOutTO ges_ges_col0_colecturia_mtto(ColecturiaTO parameters,
			int action) throws Exception;

	public List get_ges_colecturia(ColecturiaInTO parameters)
			throws EJBException;

	public ColecturiaTO get_ges_colecturiaByKey(int parameters)
			throws EJBException;
	public ColecturiaTO get_ges_colecturiaByKey_print(int parameters)
			throws EJBException;

	public List get_ges_colecturiaConcept() throws EJBException;
	public List get_ges_colecturiaConcept1(String Code) throws EJBException;

	public ResultOutTO ges_ges_col2_colecturiaConcepts_mtto(
			ColecturiaConceptTO parameters, int action) throws EJBException;

}

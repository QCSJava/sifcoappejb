package com.sifcoapp.bussinessLogic;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.accounting.dao.AccountingDAO;
import com.sifcoapp.objects.accounting.dao.BankreconciliationauxDAO;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.accounting.to.bankreconciliationauxInTO;
import com.sifcoapp.objects.accounting.to.bankreconciliationauxTO;
import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.dao.ParameterDAO;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesPriceTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.admin.to.PricesListInTO;
import com.sifcoapp.objects.admin.to.PricesListTO;
import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.TransfersDAO;
import com.sifcoapp.objects.inventory.dao.TransfersDetailDAO;
import com.sifcoapp.objects.inventory.to.TransfersDetailTO;
import com.sifcoapp.objects.transaction.to.WarehouseJournalLayerTO;
import com.sifcoapp.objects.transaction.to.WarehouseJournalTO;
@Stateless
public class bankreconciliationauxEJB implements bankreconciliationauxEJBRemote{

	public bankreconciliationauxEJB() {
		// TODO Auto-generated constructor stub
	}
	public ResultOutTO bankreconciliationaux_mtto(bankreconciliationauxTO parameter,int action)throws EJBException{
		ResultOutTO _return = new ResultOutTO();
		int i=0;
		BankreconciliationauxDAO DAO= new BankreconciliationauxDAO();
		try {
			i = DAO.bankreconciliationaux_mtto(parameter, action);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		if(i==0){
			_return.setCodigoError(1);
			_return.setMensaje("No es posible ingresar los datos al sistema");
			
		}else{
			_return.setCodigoError(0);
			_return.setMensaje("datos  ingresados los datos al sistema");
		    _return.setDocentry(i);
		}
		return _return;
	}
	public List getBankreconciliationaux(bankreconciliationauxInTO parameters)
			throws EJBException {
		List _return = new Vector();
		BankreconciliationauxDAO DAO = new BankreconciliationauxDAO();
		try {
			_return = DAO.getBankreconciliationaux(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}


}

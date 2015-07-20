package com.sifcoapp.admin.ejb;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.accounting.dao.AccountingDAO;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
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
public class ParameterEJB implements ParameterEJBRemote {
	Double zero = 0.00;

	/**
	 * Default constructor.
	 */
	public ParameterEJB() {
		
		// TODO Auto-generated constructor stub
	}
	
	
	public ResultOutTO parameter_mtto(parameterTO parameter,int action)throws EJBException{
			ResultOutTO _return = new ResultOutTO();
			int i=0;
			ParameterDAO DAO= new ParameterDAO();
			try {
				i = DAO.parameter_mtto(parameter, action);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw (EJBException) new EJBException(e);
			}
			if(i!=0){
				_return.setCodigoError(1);
				_return.setMensaje("No es posible ingresar los datos al sistema");
				
			}else{
				_return.setCodigoError(0);
				_return.setMensaje("datos  ingresados los datos al sistema");
			    _return.setDocentry(i);
			}
			return _return;
		}

	public List getParameter()throws EJBException{
		List _return = new Vector();
		ParameterDAO DAO = new ParameterDAO();
		try {
			_return = DAO.getParameter();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	
	}
     
	public parameterTO getParameterbykey(int code)throws EJBException{
		// TODO Auto-generated method stub
				parameterTO parameter = new parameterTO();
				ParameterDAO DAO = new ParameterDAO();
				try {
					parameter = DAO.getParameterbykey(code);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw (EJBException) new EJBException(e);
				}
				return parameter;
	}

}

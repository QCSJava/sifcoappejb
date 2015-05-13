package com.sifcoapp.admin.ejb;

import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;

import com.sifcoapp.bussinessLogic.UnityEJBRemote;
import com.sifcoapp.objects.admin.dao.ParameterDAO;
import com.sifcoapp.objects.admin.dao.UnityDAO;
import com.sifcoapp.objects.admin.to.UnityTO;
import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

public class UnityEJB implements UnityEJBRemote {

	public ResultOutTO Unity_mtto(UnityTO parameter, int action) throws Exception {
		ResultOutTO _return = new ResultOutTO();
		int i=0;
		UnityDAO DAO= new UnityDAO();
		try {
			i = DAO.Unity_mtto(parameter, action);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		if(i==0){
			_return.setCodigoError(1);
			_return.setMensaje("No es posible ingresar los datos al sistema");
			
		}else{
			_return.setCodigoError(0);
			_return.setMensaje("No es posible ingresar los datos al sistema");
		    _return.setDocentry(i);
		}
		return _return;
		
	}

	public List getUnity() throws Exception {
		List _return = new Vector();
		UnityDAO DAO = new UnityDAO();
		try {
			_return = DAO.getUnity();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public UnityTO getUnity_bykey(int code) throws Exception {
		// TODO Auto-generated method stub
		UnityTO parameter = new UnityTO();
		UnityDAO DAO = new UnityDAO();
		try {
			parameter = DAO.getUnity_bykey(code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return parameter;
		
	}

}

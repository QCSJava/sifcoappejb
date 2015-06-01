package com.sifcoapp.admin.ejb;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.common.to.ResultOutTO;
@Remote
public interface ParameterEJBRemote {
	public ResultOutTO parameter_mtto(parameterTO parameter,int action)throws EJBException;
	public List getParameter()throws EJBException;
	public parameterTO getParameterbykey(int code)throws EJBException;
}

package com.sifcoapp.client;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.admin.ejb.ParameterEJBRemote;
import com.sifcoapp.bussinessLogic.SalesEJBRemote;
import com.sifcoapp.bussinessLogic.UnityEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.admin.to.UnityTO;
import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.sales.to.*;
public class UnityEJBClient {
	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/UnityEJB!com.sifcoapp.bussinessLogic.UnityEJBRemote";
	private static UnityEJBRemote bean;
	private static Context context = null;
	
	public UnityEJBClient() {

		try {
			context = ClientUtility.getInitialContext();
			bean = (UnityEJBRemote) context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultOutTO Unity_mtto(UnityTO parameter,int action)throws Exception{
		ResultOutTO _return= new ResultOutTO();

		_return = bean.Unity_mtto(parameter, action);

		return _return;
	}
	public List getUnity() throws Exception{
		List parameter = new Vector();
		
		parameter = bean.getUnity();
		
		return parameter;
	
	}
	public UnityTO getUnity_bykey(int code) throws Exception {
		UnityTO _return = new UnityTO();
			_return = bean.getUnity_bykey(code);
			return _return;
		}
	}
	
	
	
	


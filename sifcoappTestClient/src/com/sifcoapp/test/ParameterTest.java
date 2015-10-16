package com.sifcoapp.test;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.AccountingEJBClient;
import com.sifcoapp.client.AdminEJBClient;
import com.sifcoapp.client.ParameterEJBClient;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.DocStatusTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.admin.to.PricesListInTO;
import com.sifcoapp.objects.admin.to.PricesListTO;
import com.sifcoapp.objects.admin.to.TablesCatalogTO;
import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.security.to.ProfileDetOutTO;

import java.sql.Date;

public class ParameterTest {
	private static ParameterEJBClient AdminEJBService;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (AdminEJBService == null)
			AdminEJBService = new ParameterEJBClient();

		String v_method = args[0];

		try {
		if(args.length>0){
			ParameterTest.class.getMethod(args[0], null).invoke(null, null);
		}else
		{
			System.out
			.println("ParameterTest.getparameter();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			ParameterTest.getparameter();
			
			
		}
			// testPeriods();

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void getparameter() {
		parameterTO branch = new parameterTO();

		String name = null;
		String code = null;

		try {
			branch = AdminEJBService.getParameterbykey(9);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Iterator<parameterTO> iterator = resp.iterator();
		//while (iterator.hasNext()) {
			//parameterTO branch = (parameterTO) iterator.next();
			System.out.println(branch.getParametercode()+ " - "
					+ branch.getParametername()+ branch.getValue1());
		//}
	}

}

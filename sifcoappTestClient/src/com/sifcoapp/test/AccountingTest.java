package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.AccountingEJBClient;
import com.sifcoapp.client.SecurityEJBClient;
import com.sifcoapp.objects.accounting.to.AccPeriodInTO;
import com.sifcoapp.objects.accounting.to.AccPeriodOutTO;
import com.sifcoapp.objects.security.to.ProfileInTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;

public class AccountingTest {
	private static AccountingEJBClient AccountingEJBService=null;
	public AccountingTest(){
		super();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if 		(AccountingEJBService==null)
			AccountingEJBService=new AccountingEJBClient();
		
		String v_method=args[0];
		
		/*List lstPeriods=new Vector();
		
		lstPeriods=AccountingEJBService.getAccPeriods();
		
		System.out.println(lstPeriods);*/
		
		try {
			AccountingTest.class.getMethod(args[0], null).invoke(null, null);
			//testPeriods();
			
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
	
	public static void testPeriods(){
		
		List lstPeriods=new Vector();
		
		lstPeriods=AccountingEJBService.getAccPeriods();
		
		System.out.println(lstPeriods);
		
	}
	
	public static void addPeriod(){
		AccPeriodInTO parameters = new AccPeriodInTO();
		AccPeriodOutTO outputs = new AccPeriodOutTO();
    	 
    	
		parameters.setCantidadPeriodo("cant1");
		parameters.setCodigoPeriodo("CodigoPeriodo1");
		
		parameters.setEjercicio("Ejercicio1");
    	parameters.setFechaConta("20/11/2014");
    	parameters.setFechaDocumento("20/11/2014");
    	parameters.setFechaVencimiento("20/11/2014");
    	parameters.setIndicadorPeriodo("indicadorPeriodo1");
    	parameters.setInicioEjercicio("inicioEjercicio");
    	parameters.setNombrePeriodo("nombrePeriodo");
    	parameters.setStatusPeriodo("statusPeriodo");
    	parameters.setSubPeriodo("subPeriodo");
    	outputs=AccountingEJBService.AccAddPeriod(parameters);
    	System.out.println(outputs.getCodResp());
    	//CodResp = 0 ===> OK
    	//CodResp <>0 ===> KO
		
	}
}

package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.SalesEJBClient;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.sales.to.*;

public class SalesTest {
	private static SalesEJBClient sales;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if 		(sales==null)
			sales=new SalesEJBClient();
		
		String v_method = args[0];

		/*
		 * List lstPeriods=new Vector();
		 * 
		 * lstPeriods=AccountingEJBService.getAccPeriods();
		 * 
		 * System.out.println(lstPeriods);
		 */

		try {
			SalesTest.class.getMethod(args[0], null).invoke(null, null);
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
	
	public static void getSales() {

		List lstPeriods = new Vector();
		SalesInTO nuevo = new SalesInTO();
		nuevo.setSeries(8);
		Date fecha= new Date();
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			lstPeriods = sales.getSales(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<SalesTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			SalesTO periodo = (SalesTO) iterator.next();
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
		}
	}
	
	public static void getSalesbykey() {

		SalesTO periodo = new SalesTO();
		List lstPeriods = new Vector();
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			periodo = sales.getSalesByKey(15);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
			
			lstPeriods = periodo.getSalesDetails();
			Iterator<SalesDetailTO> iterator = lstPeriods.iterator();
			while (iterator.hasNext()) {
				SalesDetailTO periodo2 = (SalesDetailTO) iterator.next();
				System.out.println(periodo2.getItemcode() + " - "
						+ periodo2.getDscription() + " - "
						+ periodo2.getAcctcode());
			}	
	}
	public static void Sales_mtto() {

		ResultOutTO _result=new ResultOutTO();
		SalesTO parameters = new SalesTO();
	
		List prueba = new Vector();
		SalesDetailTO document = new SalesDetailTO();
		SalesDetailTO document1 = new SalesDetailTO();
		
		//document.setDocentry(1);
		document.setLinenum(1);
		document.setItemcode("ART-001");
		document.setDscription("A");
		document.setQuantity(10.25);
		document.setPrice(11.25);
		//document.setLinetotal(100.00);
		prueba.add(document);
		//document1.setDocentry(1);
		document1.setLinenum(2);
		document1.setItemcode("ART-001");
		document1.setDscription("A");
		document1.setQuantity(10.25);
		document1.setPrice(11.25);
		//document1.setLinetotal(100.00);
		prueba.add(document1);
		parameters.setDocnum(485);
		parameters.setUsersign(1);
		//parameters.setDocentry(26);
		parameters.setCardcode("P");
		//parameters.setDoctotal(15.5);
		parameters.setSalesDetails(prueba);
		try {
			
			_result = sales.inv_Sales_mtto(parameters,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error EJB " + e.getMessage());
		}

		System.out.println("luego de servicio");
		System.out.println(_result.getCodigoError()+"---"+_result.getDocentry());

	}
	
	
	public static void getClientCredi() {

		List lstPeriods = new Vector();
		ClientCrediInTO nuevo = new ClientCrediInTO();
		//nuevo.setSeries(8);
		nuevo.setDocnum(1);
		Date fecha= new Date();
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			lstPeriods = sales.getClientCredi(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<ClientCrediTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			ClientCrediTO periodo = (ClientCrediTO) iterator.next();
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
		}
	}
	
	public static void getClientCredibykey() {

		ClientCrediTO periodo = new ClientCrediTO();
		List lstPeriods = new Vector();
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			periodo = sales.getClientCrediByKey(5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
			
			lstPeriods = periodo.getclientDetails();
			Iterator<ClientCrediDetailTO> iterator = lstPeriods.iterator();
			while (iterator.hasNext()) {
				ClientCrediDetailTO periodo2 = (ClientCrediDetailTO) iterator.next();
				System.out.println(periodo2.getItemcode() + " - "
						+ periodo2.getDscription() + " - "
						+ periodo2.getAcctcode());
			}	
	}
	public static void ClientCredi_mtto() {

		ResultOutTO _result=new ResultOutTO();
		ClientCrediTO parameters = new ClientCrediTO();
	
		List prueba = new Vector();
		ClientCrediDetailTO document = new ClientCrediDetailTO();
		ClientCrediDetailTO document1 = new ClientCrediDetailTO();
		
		//document.setDocentry(1);
		document.setLinenum(1);
		document.setItemcode("ART-001");
		document.setDscription("A");
		document.setQuantity(10.25);
		document.setPrice(11.25);
		//document.setLinetotal(100.00);
		prueba.add(document);
		//document1.setDocentry(1);
		document1.setLinenum(2);
		document1.setItemcode("ART-001");
		document1.setDscription("A");
		document1.setQuantity(10.25);
		document1.setPrice(11.25);
		//document1.setLinetotal(100.00);
		prueba.add(document1);
		parameters.setDocnum(485);
		parameters.setUsersign(1);
		//parameters.setDocentry(26);
		parameters.setCardcode("P");
		//parameters.setDoctotal(15.5);
		parameters.setclientDetails(prueba);
		try {
			_result = sales.inv_ClientCredi_mtto(parameters,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error EJB " + e.getMessage());
		}

		System.out.println("luego de servicio");
		System.out.println(_result.getCodigoError()+"---"+_result.getDocentry());

	}
}

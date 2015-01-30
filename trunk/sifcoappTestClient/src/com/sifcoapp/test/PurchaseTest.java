package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.PurchaseEJBClient;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.purchase.to.*;

public class PurchaseTest {
	private static PurchaseEJBClient Purchase;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if 		(Purchase==null)
			Purchase=new PurchaseEJBClient();
		
		String v_method = args[0];

		/*
		 * List lstPeriods=new Vector();
		 * 
		 * lstPeriods=AccountingEJBService.getAccPeriods();
		 * 
		 * System.out.println(lstPeriods);
		 */

		try {
			PurchaseTest.class.getMethod(args[0], null).invoke(null, null);
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
	
	public static void getPurchase() {

		List lstPeriods = new Vector();
		PurchaseInTO nuevo = new PurchaseInTO();
		//nuevo.setSeries(8);
		nuevo.setDocnum(485);
		Date fecha= new Date();
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			lstPeriods = Purchase.getPurchase(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<PurchaseTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			PurchaseTO periodo = (PurchaseTO) iterator.next();
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
		}
	}
	
	public static void getPurchasebykey() {

		PurchaseTO periodo = new PurchaseTO();
		List lstPeriods = new Vector();
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			periodo = Purchase.getPurchaseByKey(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
			
			lstPeriods = periodo.getpurchaseDetails();
			Iterator<PurchaseDetailTO> iterator = lstPeriods.iterator();
			while (iterator.hasNext()) {
				PurchaseDetailTO periodo2 = (PurchaseDetailTO) iterator.next();
				System.out.println(periodo2.getItemcode() + " - "
						+ periodo2.getDscription() + " - "
						+ periodo2.getAcctcode());
			}	
	}
	public static void Purchase_mtto() {

		ResultOutTO _result=new ResultOutTO();
		PurchaseTO parameters = new PurchaseTO();
	
		List prueba = new Vector();
		PurchaseDetailTO document = new PurchaseDetailTO();
		PurchaseDetailTO document1 = new PurchaseDetailTO();
		
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
		parameters.setpurchaseDetails(prueba);
		try {
			_result = Purchase.inv_Purchase_mtto(parameters,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error EJB " + e.getMessage());
		}

		System.out.println("luego de servicio");
		System.out.println(_result.getCodigoError()+"---"+_result.getDocentry());

	}
	
	
	
	
	
	public static void getSupplier() {

		List lstPeriods = new Vector();
		SupplierInTO nuevo = new SupplierInTO();
		//nuevo.setSeries(8);
		nuevo.setDocnum(485);
		Date fecha= new Date();
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			lstPeriods = Purchase.getSupplier(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<SupplierTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			SupplierTO periodo = (SupplierTO) iterator.next();
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
		}
	}
	
	public static void getSupplierbykey() {

		SupplierTO periodo = new SupplierTO();
		List lstPeriods = new Vector();
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			periodo = Purchase.getSupplierByKey(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
			
			lstPeriods = periodo.getsupplierDetails();
			Iterator<SupplierDetailTO> iterator = lstPeriods.iterator();
			while (iterator.hasNext()) {
				SupplierDetailTO periodo2 = (SupplierDetailTO) iterator.next();
				System.out.println(periodo2.getItemcode() + " - "
						+ periodo2.getDscription() + " - "
						+ periodo2.getAcctcode());
			}	
	}
	public static void Supplier_mtto() {

		ResultOutTO _result=new ResultOutTO();
		SupplierTO parameters = new SupplierTO();
	
		List prueba = new Vector();
		SupplierDetailTO document = new SupplierDetailTO();
		SupplierDetailTO document1 = new SupplierDetailTO();
		
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
		parameters.setsupplierDetails(prueba);
		try {
			_result = Purchase.inv_Supplier_mtto(parameters,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error EJB " + e.getMessage());
		}

		System.out.println("luego de servicio");
		System.out.println(_result.getCodigoError()+"---"+_result.getDocentry());

	}
}

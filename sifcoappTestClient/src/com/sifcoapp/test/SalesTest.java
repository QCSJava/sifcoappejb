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
		if (sales == null)
			sales = new SalesEJBClient();

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
		
		try {
			
			SalesEJBClient nuevo1= new SalesEJBClient();
			lstPeriods =  nuevo1.getSales(nuevo);
					//sales.getDelivery(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<SalesTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			SalesTO periodo = (SalesTO) iterator.next();
			System.out.println(periodo.getDocnum() + " - "
					+ periodo.getSeries() + " - " + periodo.getDocentry());
		}
	}

	public static void getSalesbykey() {

		SalesTO periodo = new SalesTO();
		List lstPeriods = new Vector();
		// nuevo.setDocdate(fecha);
		// nuevo.setSeries(42);
		try {
			periodo = sales.getSalesByKey(15);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(periodo.getDocnum() + " - " + periodo.getSeries()
				+ " - " + periodo.getDocentry());

		lstPeriods = periodo.getSalesDetails();
		Iterator<SalesDetailTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			SalesDetailTO periodo2 = (SalesDetailTO) iterator.next();
			System.out
					.println(periodo2.getItemcode() + " - "
							+ periodo2.getDscription() + " - "
							+ periodo2.getAcctcode());
		}
	}

	public static void Sales_mtto() {
		ResultOutTO _result = new ResultOutTO();
		SalesTO parameters = new SalesTO();

		List prueba = new Vector();
		SalesDetailTO document = new SalesDetailTO();
		SalesDetailTO document1 = new SalesDetailTO();

		document.setLinenum(1);
		document.setItemcode("001-004-545-6114");
		document.setDscription("A");
		document.setQuantity(2.0);
		document.setPrice(3.56);
		document.setWhscode("ALM-001");
		document.setDiscprcnt(0.0000);
		document.setLinetotal(0.0000);
		document.setPricebefdi(0.00000);
		document.setPriceafvat(0.0000);
		document.setStockpricestockprice(0.00000);
		document.setVatappld(0.000000);
		document.setVatsum(0.0000);
		document.setGrssprofit(0.000000);
		document.setGtotal(0.000000);
		document.setObjtype("10");
		prueba.add(document);

		document1.setLinenum(2);
		document1.setItemcode("001-004-545-6114");
		document1.setDscription("A");
		document1.setQuantity(6.0);
		document1.setPrice(11.25);
		document1.setWhscode("ALM-001");
		
		document1.setLinetotal(0.0000);
		document1.setPricebefdi(0.00000);
		document1.setPriceafvat(0.0000);
		document1.setStockpricestockprice(0.00000);
		document1.setVatappld(0.000000);
		document1.setVatsum(0.0000);
		document1.setGrssprofit(0.000000);
		document1.setGtotal(6.275);
		document1.setObjtype("10");
		prueba.add(document1);
		java.util.Date utilDate = new java.util.Date(); //fecha actual
		  long lnMilisegundos = utilDate.getTime();
		  java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
		  
		parameters.setTowhscode("ALM-001");
		parameters.setDocdate(sqlDate);
		parameters.setDocduedate(sqlDate);
		parameters.setTaxdate(sqlDate);
		parameters.setSeries(2);
		parameters.setUsersign(1);
		parameters.setCardcode("00001");
		parameters.setDoctotal(10000.00);
		parameters.setDiscsum(0.00000);
		parameters.setNret(0.00000);
		parameters.setPaidsum(0.0000);
		parameters.setRounddif(0.00000);
		parameters.setObjtype("10");
		parameters.setPeymethod("2");
		parameters.setVatsum(0.000000);
		parameters.setCtlaccount("110602");

		parameters.setSalesDetails(prueba);

		System.out.println(document1.getItemcode());


		try {
			_result = sales.inv_Sales_mtto(parameters,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		System.out.println("luego de servicio");
		System.out.println(_result.getCodigoError() + "---"
				+ _result.getDocentry()+ "---------------"+_result.getMensaje());

	}

	public static void getClientCredi() {

		List lstPeriods = new Vector();
		ClientCrediInTO nuevo = new ClientCrediInTO();
		
		
		try {
			
			//SalesEJB nuevo1= new SalesEJB();
			lstPeriods =  sales.getClientCredi(nuevo);
					//sales.getDelivery(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<ClientCrediTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			ClientCrediTO periodo = (ClientCrediTO) iterator.next();
			System.out.println(periodo.getDocnum() + " - "
					+ periodo.getSeries() + " - " + periodo.getDocentry());
		}
	}

	public static void getClientCredibykey() {

		ClientCrediTO periodo = new ClientCrediTO();
		List lstPeriods = new Vector();
		// nuevo.setDocdate(fecha);
		// nuevo.setSeries(42);
		try {
			periodo = sales.getClientCrediByKey(5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(periodo.getDocnum() + " - " + periodo.getSeries()
				+ " - " + periodo.getDocentry());

		lstPeriods = periodo.getclientDetails();
		Iterator<ClientCrediDetailTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			ClientCrediDetailTO periodo2 = (ClientCrediDetailTO) iterator
					.next();
			System.out
					.println(periodo2.getItemcode() + " - "
							+ periodo2.getDscription() + " - "
							+ periodo2.getAcctcode());
		}
	}

	public static void ClientCredi_mtto() {

		ResultOutTO _result = new ResultOutTO();
		ClientCrediTO parameters = new ClientCrediTO();

		List prueba = new Vector();
		ClientCrediDetailTO document = new ClientCrediDetailTO();
		ClientCrediDetailTO document1 = new ClientCrediDetailTO();


		

		document.setLinenum(1);
		document.setItemcode("001-004-545-6114");
		document.setDscription("A");
		document.setQuantity(2.0);
		document.setPrice(3.56);
		document.setWhscode("ALM-001");
		document.setDiscprcnt(0.0000);
		document.setLinetotal(0.0000);
		document.setPricebefdi(0.00000);
		document.setPriceafvat(0.0000);
		document.setStockpricestockprice(0.00000);
		document.setVatappld(0.000000);
		document.setVatsum(0.0000);
		document.setGrssprofit(0.000000);
		document.setGtotal(0.000000);
		document.setObjtype("10");
		prueba.add(document);

		document1.setLinenum(2);
		document1.setItemcode("001-004-545-6114");
		document1.setDscription("A");
		document1.setQuantity(6.0);
		document1.setPrice(11.25);
		document1.setWhscode("ALM-001");
		
		document1.setLinetotal(0.0000);
		document1.setPricebefdi(0.00000);
		document1.setPriceafvat(0.0000);
		document1.setStockpricestockprice(0.00000);
		document1.setVatappld(0.000000);
		document1.setVatsum(0.0000);
		document1.setGrssprofit(0.000000);
		document1.setGtotal(6.275);
		document1.setObjtype("10");
		prueba.add(document1);
		java.util.Date utilDate = new java.util.Date(); //fecha actual
		  long lnMilisegundos = utilDate.getTime();
		  java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
		  
		parameters.setTowhscode("ALM-001");
		parameters.setDocdate(sqlDate);
		parameters.setDocduedate(sqlDate);
		parameters.setTaxdate(sqlDate);
		parameters.setSeries(2);
		parameters.setUsersign(1);
		parameters.setCardcode("00001");
		parameters.setDoctotal(10000.00);
		parameters.setDiscsum(0.00000);
		parameters.setNret(0.00000);
		parameters.setPaidsum(0.0000);
		parameters.setRounddif(0.00000);
		parameters.setObjtype("10");
		parameters.setPeymethod("2");
		parameters.setVatsum(0.000000);
		parameters.setCtlaccount("110602");

		parameters.setclientDetails(prueba);

		try {
			_result = sales.inv_ClientCredi_mtto(parameters, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error EJB " + e.getMessage());
		}

		System.out.println("luego de servicio");
		System.out.println(_result.getCodigoError() + "---"
				+ _result.getDocentry());

	}

	public static void getDelivery() {

		List lstPeriods = new Vector();
		DeliveryInTO nuevo = new DeliveryInTO();
		
		try {
			
			SalesEJBClient nuevo1= new SalesEJBClient();
			lstPeriods =  nuevo1.getDelivery(nuevo);
					//sales.getDelivery(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<DeliveryTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			DeliveryTO periodo = (DeliveryTO) iterator.next();
			System.out.println(periodo.getDocnum() + " - "
					+ periodo.getSeries() + " - " + periodo.getDocentry());
		}
	}

	public static void getDeliverybykey() {

		DeliveryTO periodo = new DeliveryTO();
		List lstPeriods = new Vector();
		// nuevo.setDocdate(fecha);
		// nuevo.setSeries(42);
		try {
			periodo = sales.getDeliveryByKey(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(periodo.getDocnum() + " - " + periodo.getSeries()
				+ " - " + periodo.getDocentry());

		lstPeriods = periodo.getDeliveryDetails();
		Iterator<DeliveryDetailTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			DeliveryDetailTO periodo2 = (DeliveryDetailTO) iterator.next();
			System.out
					.println(periodo2.getItemcode() + " - "
							+ periodo2.getDscription() + " - "
							+ periodo2.getAcctcode());
		}
	}

	public static void Delivery_mtto() {

		ResultOutTO _result = new ResultOutTO();
		DeliveryTO parameters = new DeliveryTO();

		List prueba = new Vector();
		DeliveryDetailTO document = new DeliveryDetailTO();
		DeliveryDetailTO document1 = new DeliveryDetailTO();

		
		document.setLinenum(1);
		document.setItemcode("001-004-545-6114");
		document.setDscription("A");
		document.setQuantity(2.0);
		document.setPrice(3.56);
		document.setWhscode("ALM-001");
		document.setDiscprcnt(0.0000);
		document.setLinetotal(0.0000);
		document.setPricebefdi(0.00000);
		document.setPriceafvat(0.0000);
		document.setStockpricestockprice(0.00000);
		document.setVatappld(0.000000);
		document.setVatsum(0.0000);
		document.setGrssprofit(0.000000);
		document.setGtotal(0.000000);
		document.setObjtype("10");
		prueba.add(document);

		document1.setLinenum(2);
		document1.setItemcode("001-004-545-6114");
		document1.setDscription("A");
		document1.setQuantity(6.0);
		document1.setPrice(11.25);
		document1.setWhscode("ALM-001");
		
		document1.setLinetotal(0.0000);
		document1.setPricebefdi(0.00000);
		document1.setPriceafvat(0.0000);
		document1.setStockpricestockprice(0.00000);
		document1.setVatappld(0.000000);
		document1.setVatsum(0.0000);
		document1.setGrssprofit(0.000000);
		document1.setGtotal(6.275);
		document1.setObjtype("10");
		prueba.add(document1);
		java.util.Date utilDate = new java.util.Date(); //fecha actual
		  long lnMilisegundos = utilDate.getTime();
		  java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
		  
		parameters.setTowhscode("ALM-001");
		parameters.setDocdate(sqlDate);
		parameters.setDocduedate(sqlDate);
		parameters.setTaxdate(sqlDate);
		parameters.setSeries(2);
		parameters.setUsersign(1);
		parameters.setCardcode("00001");
		parameters.setDoctotal(10000.00);
		parameters.setDiscsum(0.00000);
		parameters.setNret(0.00000);
		parameters.setPaidsum(0.0000);
		parameters.setRounddif(0.00000);
		parameters.setObjtype("10");
		parameters.setPeymethod("2");
		parameters.setVatsum(0.000000);
		parameters.setCtlaccount("110602");

		parameters.setDeliveryDetails(prueba);
		try {
			SalesEJBClient nuevo= new SalesEJBClient();
			_result = nuevo.inv_Delivery_mtto(parameters, 1);
					//sales.inv_Delivery_mtto(parameters, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error EJB " + e.getMessage());
		}

		System.out.println("luego de servicio");
		System.out.println(_result.getCodigoError() + "---"
				+ _result.getDocentry());

	}
}

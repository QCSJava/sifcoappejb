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

		try {

			
			if (args.length > 0) {
				SalesTest.class.getMethod(args[0], null).invoke(null, null);
			} else {
				System.out
						.println("SalesTest.ClientCredi_mtto();;-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				SalesTest.ClientCredi_mtto();

				System.out
						.println("SalesTest.Delivery_mtto();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				SalesTest.Delivery_mtto();

				System.out
						.println("SalesTest.getClientCredi();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				SalesTest.getClientCredi();

				System.out
						.println("SalesTest.getClientCredibykey();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				SalesTest.getClientCredibykey();

				System.out
						.println("SalesTest.getDelivery();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				SalesTest.getDelivery();

				System.out
						.println("SalesTest.getDeliverybykey();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				SalesTest.getDeliverybykey();

				System.out
						.println("SalesTest.getSales();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				SalesTest.getSales();

				System.out
						.println("SalesTest.getSalesbykey();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				SalesTest.getSalesbykey();

				System.out
						.println("SalesTest.Sales_mtto();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				SalesTest.Sales_mtto();

			}

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
		// nuevo.setCardcode("00001");
		int n = 1;
		try {

			SalesEJBClient nuevo1 = new SalesEJBClient();
			lstPeriods = nuevo1.getSales(nuevo);
			// sales.getDelivery(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<SalesTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			SalesTO periodo = (SalesTO) iterator.next();
			System.out.println(periodo.getDocnum() + " - "
					+ periodo.getJrnlmemo() + " - " + periodo.getDocentry()
					+ "-" + n);
			n++;
		}
	}

	public static void getSalesbykey() {

		SalesTO periodo = new SalesTO();
		List lstPeriods = new Vector();
		// nuevo.setDocdate(fecha);
		// nuevo.setSeries(42);
		try {
			periodo = sales.getSalesByKey(254);
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

		java.util.Date utilDate = new java.util.Date();
		long lnMilisegundos = utilDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);

	

		 SalesTO sale=new SalesTO();
		 try {
			sale=sales.getSalesByKey(6);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		    parameters.setDoctype(sale.getDoctype());
			parameters.setCanceled(sale.getCanceled());
			parameters.setDocstatus(sale.getDocstatus());
			parameters.setObjtype(sale.getObjtype());
			parameters.setDocdate(sale.getDocdate());
			parameters.setDocduedate(sale.getDocduedate());
			parameters.setCardcode(sale.getCardcode());
			parameters.setNumatcard(sale.getNumatcard());
			parameters.setCardname(sale.getCardname());
			parameters.setVatsum(sale.getVatsum());
			parameters.setDiscsum(sale.getDiscsum());
			parameters.setDoctotal(sale.getDoctotal());
			parameters.setRef1(sale.getRef1());
			parameters.setRef2(sale.getRef2());
			parameters.setComments(sale.getComments());
			parameters.setJrnlmemo(sale.getJrnlmemo());
			parameters.setPaidtodate(sale.getPaidtodate());
			parameters.setTransid(sale.getTransid());
			parameters.setReceiptnum(sale.getReceiptnum());
			parameters.setGroupnum(sale.getGroupnum());
			parameters.setConfirmed(sale.getConfirmed());
			parameters.setCreatetran(sale.getCreatetran());
			parameters.setSeries(sale.getSeries());
			parameters.setTaxdate(sale.getTaxdate());
			parameters.setFiller(sale.getFiller());
			parameters.setRounddif(sale.getRounddif());
			parameters.setRounding(sale.getRounding());
			parameters.setCanceldate(sale.getCanceldate());
			parameters.setPeymethod(sale.getPeymethod());
			parameters.setCtlaccount(sale.getCtlaccount());
			parameters.setBplname(sale.getBplname());
			parameters.setVatregnum(sale.getVatregnum());
			parameters.setPaidsum(sale.getPaidsum());
			parameters.setTowhscode(sale.getTowhscode());
			parameters.setNret(sale.getNret());
			parameters.setNamenp(sale.getNamenp());
			parameters.setQuedan(sale.getQuedan());
			parameters.setFechreciva(sale.getFechreciva());
			parameters.setFquedan(sale.getFquedan());
			parameters.setUsersign(sale.getUsersign());
		
			List lstPeriods = new Vector();
			lstPeriods = sale.getSalesDetails();
			
			
			Iterator<SalesDetailTO> iterator = lstPeriods.iterator();
			while (iterator.hasNext()) {
				SalesDetailTO periodo2 = (SalesDetailTO) iterator
						.next();
				SalesDetailTO ClientCredi = new SalesDetailTO();
				
				ClientCredi.setDocentry(periodo2.getDocentry());
				ClientCredi.setLinenum(periodo2.getLinenum());
				ClientCredi.setTargettype(periodo2.getTargettype());
				ClientCredi.setBaseref(periodo2.getBaseref());
				ClientCredi.setBasetype(periodo2.getBasetype());
				ClientCredi.setBaseentry(periodo2.getBaseentry());
				ClientCredi.setBaseline(periodo2.getBaseline());
				ClientCredi.setLinestatus(periodo2.getLinestatus());
				ClientCredi.setItemcode("INV0000001");
				ClientCredi.setDscription(periodo2.getDscription());
				ClientCredi.setQuantity(periodo2.getQuantity());
				ClientCredi.setOpenqty(periodo2.getOpenqty());
				ClientCredi.setPrice(periodo2.getPrice());
				ClientCredi.setDiscprcnt(periodo2.getDiscprcnt());
				ClientCredi.setLinetotal(periodo2.getLinetotal());
				ClientCredi.setWhscode(periodo2.getWhscode());
				ClientCredi.setAcctcode(periodo2.getAcctcode());
				ClientCredi.setTaxstatus(periodo2.getTaxstatus());
				ClientCredi.setPricebefdi(periodo2.getPricebefdi());
				ClientCredi.setOcrcode(periodo2.getOcrcode());
				ClientCredi.setVatgroup(periodo2.getVatgroup());
				ClientCredi.setPriceafvat(periodo2.getPriceafvat());
				ClientCredi.setFactor1(periodo2.getFactor1());
				ClientCredi.setVatsum(periodo2.getVatsum());
				ClientCredi.setObjtype(periodo2.getObjtype());
				ClientCredi.setGrssprofit(periodo2.getGrssprofit());
				ClientCredi.setTaxcode(periodo2.getTaxcode());
				ClientCredi.setVatappld(periodo2.getVatappld());
				ClientCredi.setUnitmsr(periodo2.getUnitmsr());
				ClientCredi.setStockpricestockprice(periodo2.getStockpricestockprice());
				ClientCredi.setGtotal(periodo2.getGtotal());
				
				prueba.add(ClientCredi);
			}
			//haciendo la nota de credito 
			
			parameters.setSalesDetails(prueba);
		
		try {
			_result = sales.inv_Sales_mtto(parameters, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("luego de servicio");
		System.out.println(_result.getCodigoError() + "---"
				+ _result.getDocentry() + "---------------"
				+ _result.getMensaje());

	}

	public static void getClientCredi() {

		List lstPeriods = new Vector();
		ClientCrediInTO nuevo = new ClientCrediInTO();

		try {

			// SalesEJB nuevo1= new SalesEJB();
			lstPeriods = sales.getClientCredi(nuevo);
			// sales.getDelivery(nuevo);
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
        SalesTO sale=new SalesTO();
		List prueba = new Vector();
		ClientCrediDetailTO document = new ClientCrediDetailTO();
		ClientCrediDetailTO document1 = new ClientCrediDetailTO();
		

		try {
			//cosultando una venta para hacer la nota de credito 
			sale=sales.getSalesByKey(6);
			
			//parameters.setDocentry(sale.getDocentry());
			parameters.setDocnum(sale.getDocnum());
			parameters.setDoctype(sale.getDoctype());
			parameters.setCanceled(sale.getCanceled());
			parameters.setDocstatus(sale.getDocstatus());
			parameters.setObjtype(sale.getObjtype());
			parameters.setDocdate(sale.getDocdate());
			parameters.setDocduedate(sale.getDocduedate());
			parameters.setCardcode(sale.getCardcode());
			parameters.setNumatcard(sale.getNumatcard());
			parameters.setCardname(sale.getCardname());
			parameters.setVatsum(sale.getVatsum());
			parameters.setDiscsum(sale.getDiscsum());
			parameters.setDoctotal(sale.getDoctotal());
			parameters.setRef1(sale.getRef1());
			parameters.setRef2(sale.getRef2());
			parameters.setComments(sale.getComments());
			parameters.setJrnlmemo(sale.getJrnlmemo());
			parameters.setPaidtodate(sale.getPaidtodate());
			parameters.setTransid(sale.getTransid());
			parameters.setReceiptnum(6);
			parameters.setGroupnum(sale.getGroupnum());
			parameters.setConfirmed(sale.getConfirmed());
			parameters.setCreatetran(sale.getCreatetran());
			parameters.setSeries(sale.getSeries());
			parameters.setTaxdate(sale.getTaxdate());
			parameters.setFiller(sale.getFiller());
			parameters.setRounddif(sale.getRounddif());
			parameters.setRounding(sale.getRounding());
			parameters.setCanceldate(sale.getCanceldate());
			parameters.setPeymethod(sale.getPeymethod());
			parameters.setCtlaccount(sale.getCtlaccount());
			parameters.setBplname(sale.getBplname());
			parameters.setVatregnum(sale.getVatregnum());
			parameters.setPaidsum(sale.getPaidsum());
			parameters.setTowhscode(sale.getTowhscode());
			parameters.setNret(sale.getNret());
			parameters.setNamenp(sale.getNamenp());
			parameters.setQuedan(sale.getQuedan());
			parameters.setFechreciva(sale.getFechreciva());
			parameters.setFquedan(sale.getFquedan());
			parameters.setUsersign(sale.getUsersign());
			
		
			List lstPeriods = new Vector();
			lstPeriods = sale.getSalesDetails();
			
			//parameters.setclientDetails(lstPeriods);
			
			Iterator<SalesDetailTO> iterator = lstPeriods.iterator();
			while (iterator.hasNext()) {
				SalesDetailTO periodo2 = (SalesDetailTO) iterator
						.next();
				ClientCrediDetailTO ClientCredi = new ClientCrediDetailTO();
				
				ClientCredi.setDocentry(periodo2.getDocentry());
				ClientCredi.setLinenum(periodo2.getLinenum());
				ClientCredi.setTargettype(periodo2.getTargettype());
				ClientCredi.setBaseref(periodo2.getBaseref());
				ClientCredi.setBasetype(periodo2.getBasetype());
				ClientCredi.setBaseentry(periodo2.getBaseentry());
				ClientCredi.setBaseline(periodo2.getBaseline());
				ClientCredi.setLinestatus(periodo2.getLinestatus());
				ClientCredi.setItemcode(periodo2.getItemcode());
				ClientCredi.setDscription(periodo2.getDscription());
				ClientCredi.setQuantity(periodo2.getQuantity());
				ClientCredi.setOpenqty(periodo2.getOpenqty());
				ClientCredi.setPrice(periodo2.getPrice());
				ClientCredi.setDiscprcnt(periodo2.getDiscprcnt());
				ClientCredi.setLinetotal(periodo2.getLinetotal());
				ClientCredi.setWhscode(periodo2.getWhscode());
				ClientCredi.setAcctcode(periodo2.getAcctcode());
				ClientCredi.setTaxstatus(periodo2.getTaxstatus());
				ClientCredi.setPricebefdi(periodo2.getPricebefdi());
				ClientCredi.setOcrcode(periodo2.getOcrcode());
				ClientCredi.setVatgroup(periodo2.getVatgroup());
				ClientCredi.setPriceafvat(periodo2.getPriceafvat());
				ClientCredi.setFactor1(periodo2.getFactor1());
				ClientCredi.setVatsum(periodo2.getVatsum());
				ClientCredi.setObjtype(periodo2.getObjtype());
				ClientCredi.setGrssprofit(periodo2.getGrssprofit());
				ClientCredi.setTaxcode(periodo2.getTaxcode());
				ClientCredi.setVatappld(periodo2.getVatappld());
				ClientCredi.setUnitmsr(periodo2.getUnitmsr());
				ClientCredi.setStockpricestockprice(periodo2.getStockpricestockprice());
				ClientCredi.setGtotal(periodo2.getGtotal());
				
				prueba.add(ClientCredi);
			}
			//haciendo la nota de credito 
			
			parameters.setclientDetails(prueba);
			
			//parameters=sales.getClientCrediByKey(19);
			
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

			SalesEJBClient nuevo1 = new SalesEJBClient();
			lstPeriods = nuevo1.getDelivery(nuevo);
			// sales.getDelivery(nuevo);
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
		List lstPeriods = new Vector();

		DeliveryTO sale=new DeliveryTO();
		 try {
			sale=sales.getDeliveryByKey(4);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		    parameters.setDocnum(sale.getDocnum());
			parameters.setDoctype(sale.getDoctype());
			parameters.setCanceled(sale.getCanceled());
			parameters.setDocstatus(sale.getDocstatus());
			parameters.setObjtype(sale.getObjtype());
			parameters.setDocdate(sale.getDocdate());
			parameters.setDocduedate(sale.getDocduedate());
			parameters.setCardcode(sale.getCardcode());
			parameters.setNumatcard(sale.getNumatcard());
			parameters.setCardname(sale.getCardname());
			parameters.setVatsum(sale.getVatsum());
			parameters.setDiscsum(sale.getDiscsum());
			parameters.setDoctotal(sale.getDoctotal());
			parameters.setRef1(sale.getRef1());
			parameters.setRef2(sale.getRef2());
			parameters.setComments(sale.getComments());
			parameters.setJrnlmemo(sale.getJrnlmemo());
			parameters.setPaidtodate(sale.getPaidtodate());
			parameters.setTransid(sale.getTransid());
			parameters.setReceiptnum(4);
			parameters.setGroupnum(sale.getGroupnum());
			parameters.setConfirmed(sale.getConfirmed());
			parameters.setCreatetran(sale.getCreatetran());
			parameters.setSeries(4);
			parameters.setTaxdate(sale.getTaxdate());
			parameters.setFiller(sale.getFiller());
			parameters.setRounddif(sale.getRounddif());
			parameters.setRounding(sale.getRounding());
			parameters.setCanceldate(sale.getCanceldate());
			parameters.setPeymethod(sale.getPeymethod());
			parameters.setCtlaccount(sale.getCtlaccount());
			parameters.setBplname(sale.getBplname());
			parameters.setVatregnum(sale.getVatregnum());
			parameters.setPaidsum(sale.getPaidsum());
			parameters.setTowhscode(sale.getTowhscode());
			parameters.setNret(sale.getNret());
			parameters.setNamenp(sale.getNamenp());
			parameters.setQuedan(sale.getQuedan());
			parameters.setFechreciva(sale.getFechreciva());
			parameters.setFquedan(sale.getFquedan());
			parameters.setUsersign(sale.getUsersign());
		
			
			
			lstPeriods = sale.getDeliveryDetails();
			
		
			Iterator<DeliveryDetailTO> iterator = lstPeriods.iterator();
			
			while (iterator.hasNext()) {
				DeliveryDetailTO periodo2 = (DeliveryDetailTO) iterator
						.next();
				DeliveryDetailTO ClientCredi = new DeliveryDetailTO();
				
				ClientCredi.setDocentry(periodo2.getDocentry());
				ClientCredi.setLinenum(periodo2.getLinenum());
				ClientCredi.setTargettype(periodo2.getTargettype());
				ClientCredi.setBaseref(periodo2.getBaseref());
				ClientCredi.setBasetype(periodo2.getBasetype());
				ClientCredi.setBaseentry(periodo2.getBaseentry());
				ClientCredi.setBaseline(periodo2.getBaseline());
				ClientCredi.setLinestatus(periodo2.getLinestatus());
				ClientCredi.setItemcode(periodo2.getItemcode());
				ClientCredi.setDscription(periodo2.getDscription());
				ClientCredi.setQuantity(periodo2.getQuantity());
				ClientCredi.setOpenqty(periodo2.getOpenqty());
				ClientCredi.setPrice(periodo2.getPrice());
				ClientCredi.setDiscprcnt(periodo2.getDiscprcnt());
				ClientCredi.setLinetotal(periodo2.getLinetotal());
				ClientCredi.setWhscode(periodo2.getWhscode());
				ClientCredi.setAcctcode(periodo2.getAcctcode());
				ClientCredi.setTaxstatus(periodo2.getTaxstatus());
				ClientCredi.setPricebefdi(periodo2.getPricebefdi());
				ClientCredi.setOcrcode(periodo2.getOcrcode());
				ClientCredi.setVatgroup(periodo2.getVatgroup());
				ClientCredi.setPriceafvat(periodo2.getPriceafvat());
				ClientCredi.setFactor1(periodo2.getFactor1());
				ClientCredi.setVatsum(periodo2.getVatsum());
				ClientCredi.setObjtype(periodo2.getObjtype());
				ClientCredi.setGrssprofit(periodo2.getGrssprofit());
				ClientCredi.setTaxcode(periodo2.getTaxcode());
				ClientCredi.setVatappld(periodo2.getVatappld());
				ClientCredi.setUnitmsr(periodo2.getUnitmsr());
				ClientCredi.setStockpricestockprice(periodo2.getStockpricestockprice());
				ClientCredi.setGtotal(periodo2.getGtotal());
				
				prueba.add(ClientCredi);
			}
			//haciendo la nota de credito 
			
			
		

		parameters.setDeliveryDetails(prueba);
		try {
			_result=sales.inv_Delivery_mtto(parameters, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error EJB " + e.getMessage());
		}

		System.out.println("luego de servicio");
		System.out.println(_result.getCodigoError() + "---"
				+ _result.getDocentry() + _result.getMensaje());

	}
}

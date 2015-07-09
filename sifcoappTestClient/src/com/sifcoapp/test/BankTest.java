package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.BankEJBClient;
import com.sifcoapp.client.CatalogEJBClient;
import com.sifcoapp.objects.bank.to.CheckForPaymentInTO;
import com.sifcoapp.objects.bank.to.CheckForPaymentTO;
import com.sifcoapp.objects.bank.to.ColecturiaConceptTO;
import com.sifcoapp.objects.bank.to.ColecturiaDetailTO;
import com.sifcoapp.objects.bank.to.ColecturiaInTO;
import com.sifcoapp.objects.bank.to.ColecturiaTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.sales.to.SalesTO;

public class BankTest {

	private static BankEJBClient catalog;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (catalog == null)
			catalog = new BankEJBClient();
		String v_method = args[0];

		try {
			BankTest.class.getMethod(args[0], null).invoke(null, null);
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

	public static void getCheck() {

		CheckForPaymentTO lstPeriods = new CheckForPaymentTO();
		List lstPeriods3 = null;
		CheckForPaymentInTO nuevo = new CheckForPaymentInTO();
		nuevo.setCheckkey(20);

		try {
			lstPeriods3 = catalog.get_cfp0_checkforpayment(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<CheckForPaymentTO> iterator = lstPeriods3.iterator();
		while (iterator.hasNext()) {
			CheckForPaymentTO periodo = (CheckForPaymentTO) iterator.next();
			System.out.println(periodo.getCheckkey() + "  "
					+ periodo.getDetails() + " - " + periodo.getAcctnum()
					+ " - " + periodo.getAddress());
		}
	}

	public static void getCheckByKey() {
		CheckForPaymentTO lstPeriods3 = new CheckForPaymentTO();
		CheckForPaymentInTO nuevo = new CheckForPaymentInTO();
		nuevo.setCheckkey(1);

		try {
			lstPeriods3 = catalog.get_cfp0_checkforpaymentByKey(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(lstPeriods3.getCheckkey() + "  "
				+ lstPeriods3.getDetails() + " - " + lstPeriods3.getAcctnum()
				+ " - " + lstPeriods3.getAddress());

	}

	public static void check_mtto() {
		ResultOutTO resp = null;
		CheckForPaymentTO bus = new CheckForPaymentTO();
		bus.setCheckkey(1);
		bus.setAcctnum("000001");
		bus.setBankname("Banck JC");
		bus.setChecknum(5454);

		try {
			resp = catalog.ges_cfp0_checkforpayment_mtto(bus, 2);
			System.out.println(resp.getDocentry());
			System.out.println(resp.getCodigoError());
			System.out.println(resp.getMensaje());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void colecturia_mtto() {
		ResultOutTO resp = null;
		ColecturiaTO bus = new ColecturiaTO();
		
		bus.setCardcode("00001");
		bus.setCardname("Prueba");
		bus.setDoctotal(10.1);
		
		ColecturiaConceptTO lstPeriods = new ColecturiaConceptTO();
		List lstPeriods4= null;
		// nuevo.setDocentry(1);

		try {
			lstPeriods4 = catalog.get_ges_colecturiaConcept1("00001");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List prueba = new Vector();
		Iterator<ColecturiaConceptTO> iterator = lstPeriods4.iterator();
		while (iterator.hasNext()) {
			
			
			ColecturiaConceptTO concept = (ColecturiaConceptTO) iterator.next();
			
			ColecturiaDetailTO colecturia = new ColecturiaDetailTO();
			
			//colecturia.setDocentry(concept.getTransid());
			colecturia.setLinenum(concept.getLinenum());
			colecturia.setLinestatus(concept.getLinestatus());
			colecturia.setObjtype(concept.getObjtype());
			colecturia.setDscription(concept.getDscription());
			colecturia.setAcctcode(concept.getAcctcode());
			colecturia.setAcctcode2(concept.getAcctcode2());
			colecturia.setAcctcode3(concept.getAcctcode3());
			colecturia.setCtlaccount(concept.getCtlaccount());
			colecturia.setOcrcode(concept.getOcrcode());
			colecturia.setTransid(concept.getTransid());
			colecturia.setConfirmed(concept.getConfirmed());
			colecturia.setPeymethod(concept.getPeymethod());
			colecturia.setPaidsum(10.00);
			colecturia.setVatsum(concept.getVatsum());
			colecturia.setDocsubtype(concept.getDocsubtype());
			colecturia.setValue1(concept.getValue1());
			colecturia.setValue2(concept.getValue2());
			colecturia.setValue3(concept.getValue3());
			colecturia.setTaxstatus("Y");
            colecturia.setAditional_account("Y");
            colecturia.setFacturas(concept.getFacturas());
			
			
			prueba.add(colecturia);
		}
		
		bus.setColecturiaDetail(prueba);

		try {
			resp = catalog.ges_ges_col0_colecturia_mtto(bus, 1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(resp.getDocentry());
		System.out.println(resp.getCodigoError());
		System.out.println(resp.getMensaje());
	}

	public static void colecturiaConcept_mtto() {

		ResultOutTO resp = null;
		ColecturiaConceptTO detalle = new ColecturiaConceptTO();
		detalle.setLinenum(1);
		detalle.setAcctcode("23434");
		detalle.setDscription("jjjjjjjj");
		detalle.setPaidsum(0.00000);
		detalle.setVatsum(0.00000);
		
		try {
			resp = catalog.ges_ges_col2_colecturiaConcepts_mtto(detalle, 2);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(resp.getDocentry());
		System.out.println(resp.getCodigoError());
		System.out.println(resp.getMensaje());

	}

	public static void getColecturia() {

		ColecturiaTO lstPeriods = new ColecturiaTO();
		ColecturiaInTO nuevo = new ColecturiaInTO();
		List lstPeriods3 = null;
		// nuevo.setDocentry(1);

		try {
			lstPeriods3 = catalog.get_ges_colecturia(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<ColecturiaTO> iterator = lstPeriods3.iterator();
		while (iterator.hasNext()) {
			ColecturiaTO periodo = (ColecturiaTO) iterator.next();
			System.out.println(periodo.getCardcode() + "  "
					+ periodo.getDocnum() + " - " + periodo.getDoctotal()
					+ " - " + periodo.getDocentry());
		}
	}

	public static void getColecturiaByKey() {
		ColecturiaTO lstPeriods3 = new ColecturiaTO();

		try {
			lstPeriods3 = catalog.get_ges_colecturiaByKey(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(lstPeriods3.getCardcode() + "  "
				+ lstPeriods3.getDocnum() + " - " + lstPeriods3.getDoctotal()
				+ " - " + lstPeriods3.getDocentry());

		Iterator<ColecturiaDetailTO> iterator = lstPeriods3
				.getColecturiaDetail().iterator();
		while (iterator.hasNext()) {
			ColecturiaDetailTO periodo = (ColecturiaDetailTO) iterator.next();
			System.out.println(periodo.getAcctcode() + "  "
					+ periodo.getLinenum() + " - " + periodo.getObjtype()
					+ " - " + periodo.getDscription());
		}

	}

	public static void getColecturiaConcept() {
		ColecturiaConceptTO lstPeriods = new ColecturiaConceptTO();
		List lstPeriods3 = null;
		// nuevo.setDocentry(1);

		try {
			lstPeriods3 = catalog.get_ges_colecturiaConcept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<ColecturiaConceptTO> iterator = lstPeriods3.iterator();
		while (iterator.hasNext()) {
			ColecturiaConceptTO periodo = (ColecturiaConceptTO) iterator.next();
			System.out.println(periodo.getAcctcode() + "  "
					+ periodo.getLinenum() + " - " + periodo.getObjtype()
					+ " - " + periodo.getDscription());
		}
	}
	
	public static void getColecturiaConcept1() {
		ColecturiaConceptTO lstPeriods = new ColecturiaConceptTO();
		List lstPeriods3 = null;
		// nuevo.setDocentry(1);

		try {
			lstPeriods3 = catalog.get_ges_colecturiaConcept1("00001");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double fac=0.0;
		Iterator<ColecturiaConceptTO> iterator = lstPeriods3.iterator();
		while (iterator.hasNext()) {
			ColecturiaConceptTO periodo = (ColecturiaConceptTO) iterator.next();
			if(periodo.getFacturas()!=null){
				
				Iterator<SalesTO> iterator1 = periodo.getFacturas().iterator();
				while (iterator1.hasNext()) {
					SalesTO sale = (SalesTO) iterator1.next();
					System.out.println("Lista de facturas de venta"+"-"+sale.getDoctotal());
					fac=fac+sale.getDoctotal();
			
			}
				
			}
			System.out.println("lista de conceptos" +"-"+periodo.getAcctcode() + "  "+periodo.getAcctcode2() + "  "+periodo.getAcctcode3() + "  "	+
					+
					+ periodo.getLinenum() + " - " + periodo.getObjtype()
					+ " - " + periodo.getDscription());
			
			System.out.println("total facturas"+"----"+fac);
			
		}
	}

}

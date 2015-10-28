package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.BankEJBClient;
import com.sifcoapp.client.BankreconciliationEJBClient;
import com.sifcoapp.client.CatalogEJBClient;
import com.sifcoapp.objects.ExternalReconciliation.to.ExternalReconciliationTO;
import com.sifcoapp.objects.ExternalReconciliation.to.bankreconciliationauxTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.bank.to.CheckForPaymentInTO;
import com.sifcoapp.objects.bank.to.CheckForPaymentTO;
import com.sifcoapp.objects.bank.to.ColecturiaConceptTO;
import com.sifcoapp.objects.bank.to.ColecturiaDetailTO;
import com.sifcoapp.objects.bank.to.ColecturiaInTO;
import com.sifcoapp.objects.bank.to.ColecturiaTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;

public class BankReconciliationTest {

	private static BankreconciliationEJBClient catalog;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (catalog == null)
			catalog = new BankreconciliationEJBClient();
		String v_method = args[0];

		try {
			if(args.length>0){
			BankReconciliationTest.class.getMethod(args[0], null).invoke(null,
					null);
			}else{
				
				// testPeriods();
				System.out
				.println("BankReconciliationTest.aux_mtto();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				BankReconciliationTest.aux_mtto();
				
				System.out
				.println("BankReconciliationTest.conliciacion_update();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				BankReconciliationTest.conliciacion_update();
				
				System.out
				.println("BankReconciliationTest.get_new();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				BankReconciliationTest.get_new();
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

	public static void get_new() {

		ExternalReconciliationTO conciliate = new ExternalReconciliationTO();
		conciliate.setAccount("1101010104");
		java.util.Date utilDate = new java.util.Date(); // fecha actual
		
		
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		String strFecha = "2015-10-27";
		Date fecha = null;
		

		

		try {
			fecha = formatoDelTexto.parse(strFecha);
			conciliate.setRefDate(fecha);

			conciliate = catalog.get_newExternalReconciliation(conciliate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<JournalEntryLinesTO> iterator = conciliate
				.getAccToConciliate().iterator();
		while (iterator.hasNext()) {
			JournalEntryLinesTO cuenta = (JournalEntryLinesTO) iterator.next();
			System.out.println(cuenta.getAccount() + "  "
					+ cuenta.getLinememo() + " - " + cuenta.getTransid()
					+ " - " + cuenta.getDebit());
		}

		Iterator<bankreconciliationauxTO> iterator1 = conciliate
				.getAuxiliaryDoc().iterator();
		while (iterator1.hasNext()) {
			bankreconciliationauxTO cuenta = (bankreconciliationauxTO) iterator1
					.next();
			System.out.println(cuenta.getRef1() + "  " + cuenta.getMemo()
					+ " - " + cuenta.getTransid() + " - "
					+ cuenta.getLoctotal());
		}
	}

	public static void conliciacion_update () throws Exception {
		ResultOutTO _result = new ResultOutTO();

		ExternalReconciliationTO conciliate = new ExternalReconciliationTO();
		conciliate.setAccount("110605");
		java.util.Date utilDate = new java.util.Date(); // fecha actual
		conciliate.setRefDate(utilDate);

		try {
			conciliate = catalog.get_newExternalReconciliation(conciliate);
			
			JournalEntryLinesTO cuenta= (JournalEntryLinesTO) conciliate.getAccToConciliate().get(0);
			cuenta.setExtrmatch(1);
			
			bankreconciliationauxTO cuenta2= (bankreconciliationauxTO) conciliate.getAuxiliaryDoc().get(0);
			cuenta2.setExtrmatch(1);

			_result = catalog.UpdateExternalReconciliation(conciliate);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(_result.getMensaje());
	}

	public static void aux_mtto() throws Exception {
		ResultOutTO _result = new ResultOutTO();

		bankreconciliationauxTO nuevo = new bankreconciliationauxTO();

		nuevo.setBatchnum(1);
		nuevo.setTranscode("10");
		java.util.Date utilDate = new java.util.Date(); // fecha actual
		nuevo.setRefdate(utilDate);
		nuevo.setRef1("110605");
		nuevo.setLoctotal(10.5);
		nuevo.setMemo("Prueba entrada");
		nuevo.setIndicator("D");

		_result = catalog.bankreconciliationaux_mtto(nuevo, Common.MTTOINSERT);

		System.out.println(_result.getMensaje());
	}

	/*
	 * public static void getColecturiaConcept() { ColecturiaConceptTO
	 * lstPeriods = new ColecturiaConceptTO(); List lstPeriods3 = null; //
	 * nuevo.setDocentry(1);
	 * 
	 * try { lstPeriods3 = catalog.get_ges_colecturiaConcept(); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * Iterator<ColecturiaConceptTO> iterator = lstPeriods3.iterator(); while
	 * (iterator.hasNext()) { ColecturiaConceptTO periodo =
	 * (ColecturiaConceptTO) iterator.next();
	 * System.out.println(periodo.getAcctcode() + "  " + periodo.getLinenum() +
	 * " - " + periodo.getObjtype() + " - " + periodo.getDscription()); } }
	 */

}

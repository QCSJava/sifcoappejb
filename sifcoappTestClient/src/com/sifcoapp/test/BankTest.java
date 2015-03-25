package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.BankEJBClient;
import com.sifcoapp.client.CatalogEJBClient;
import com.sifcoapp.objects.bank.to.CheckForPaymentInTO;
import com.sifcoapp.objects.bank.to.CheckForPaymentTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

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
		CheckForPaymentInTO lstPeriods2 = new CheckForPaymentInTO();
		List lstPeriods3 = null;
		CheckForPaymentInTO nuevo = new CheckForPaymentInTO();
		//nuevo.setCheckkey(1);

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
		CheckForPaymentTO lstPeriods3 =  new CheckForPaymentTO();
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
		bus.setAcctnum("000001");
		bus.setBankname("Banck JC");
		bus.setChecknum(5454);

		try {
			resp = catalog.ges_cfp0_checkforpayment_mtto(bus, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(resp.getMensaje());

	}

}

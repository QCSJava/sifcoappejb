package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.CatalogEJBClient;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;


public class BusinesspartnerTest {
	
	private static CatalogEJBClient catalog;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (catalog == null)
			catalog = new CatalogEJBClient();
		String v_method = args[0];

		/*
		 * List lstPeriods=new Vector();
		 * 
		 * lstPeriods=AccountingEJBService.getAccPeriods();
		 * 
		 * System.out.println(lstPeriods);
		 */

		try {
			BusinesspartnerTest.class.getMethod(args[0], null).invoke(null, null);
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
	
	
	public static void getBuss() {

		List lstPeriods = new Vector();
		BusinesspartnerInTO nuevo = new BusinesspartnerInTO();
		nuevo.setCardcode("T");
		//nuevo.setCardname("P");
		//nuevo.setSeries(42);
		lstPeriods = catalog.get_businesspartnerBykey(nuevo);
		Iterator<BusinesspartnerTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			BusinesspartnerTO periodo = (BusinesspartnerTO) iterator.next();
			System.out.println(periodo.getGroupcode()+ " - "
					+ periodo.getNit() + " - "
					+ periodo.getAddid());
		}
	}
	
	public static void businesspartner_mtto(){
		int resp;
		BusinesspartnerTO bus= new BusinesspartnerTO();
		bus.setCardcode("prueba3");
		bus.setCardname("testeo");
		bus.setGroupcode(26);
		
		resp= catalog.cat_bpa_businesspartner_mtto(bus,3);
		System.out.println(resp);
		
		
	}

}

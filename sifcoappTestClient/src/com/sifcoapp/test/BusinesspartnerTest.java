package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.CatalogEJBClient;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.common.to.ResultOutTO;


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
	
	//################################ PRUEBAS DE TABLA BUSINESSPARTNER########################################
	
	//##### PARA PROBAR LA BUSQUEDA POR KEY Y POR FILTRO########### (CAMBIAR EL METODO)#####
	public static void getBuss() {

		BusinesspartnerTO lstPeriods= new BusinesspartnerTO();
		BusinesspartnerInTO lstPeriods2= new BusinesspartnerInTO();
	 List lstPeriods3 = null;
		BusinesspartnerInTO nuevo = new BusinesspartnerInTO();
		nuevo.setCardcode("cod");
		
		//nuevo.setCardname("P");
		//nuevo.setSeries(42);
		/*
		String nuevo="T";
		lstPeriods = catalog.get_businesspartnerBykey(nuevo);
		System.out.println(lstPeriods.getGroupcode()+ " - "
				+ lstPeriods.getNit() + " - "
				+ lstPeriods.getAddid());
		/**/
		try {
			lstPeriods3 = catalog.get_businesspartner(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<BusinesspartnerTO> iterator = lstPeriods3.iterator();
		while (iterator.hasNext()) {
			BusinesspartnerTO periodo = (BusinesspartnerTO) iterator.next();
			System.out.println(periodo.getCardcode()+"  "+periodo.getGroupcode()+ " - "
					+ periodo.getNit() + " - "
					+ periodo.getAddid());
		}
	}
	
	public static void businesspartner_mtto(){
		ResultOutTO resp=null;
		BusinesspartnerTO bus= new BusinesspartnerTO();
		bus.setCardcode("prueba6");
		bus.setCardname("cardaneeeeee");
		bus.setGroupcode("none");
		
		try {
			resp= catalog.cat_bpa_businesspartner_mtto(bus,3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(resp.getMensaje());
		
		
	}

}

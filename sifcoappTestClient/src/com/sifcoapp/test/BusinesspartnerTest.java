package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.CatalogEJBClient;
import com.sifcoapp.objects.catalog.to.BusinesspartnerAcountTO;
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

		
		try {
		if(args.length>0){
			BusinesspartnerTest.class.getMethod(args[0], null).invoke(null, null);
		}else{
				// testPeriods();
			System.out
			.println("BusinesspartnerTest.businesspartner_mtto();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			BusinesspartnerTest.businesspartner_mtto();
			
			System.out
			.println("BusinesspartnerTest.getBuss();-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			BusinesspartnerTest.getBuss();
		
			
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
	
	//################################ PRUEBAS DE TABLA BUSINESSPARTNER########################################
	
	//##### PARA PROBAR LA BUSQUEDA POR KEY Y POR FILTRO########### (CAMBIAR EL METODO)#####
	public static void getBuss() {

		BusinesspartnerTO lstPeriods= new BusinesspartnerTO();
		BusinesspartnerInTO lstPeriods2= new BusinesspartnerInTO();
	 BusinesspartnerTO lstPeriods3 = null;
		BusinesspartnerTO nuevo = new BusinesspartnerTO();
		
		
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
			lstPeriods3 = catalog.get_businesspartnerBykey("00001");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(lstPeriods3.getCardcode()+"  "+lstPeriods3.getCardname()+ " - "
				);
		Iterator<BusinesspartnerAcountTO> iterator = lstPeriods3.getBusinesspartnerAcount().iterator();
		while (iterator.hasNext()) {
			BusinesspartnerAcountTO periodo = (BusinesspartnerAcountTO) iterator.next();
			System.out.println(periodo.getCardcode()+"  "+periodo.getAcctcode()+ " - "
					);
		}
	}
	
	public static void businesspartner_mtto(){
		ResultOutTO resp=null;
		List ne=new Vector();
	
		BusinesspartnerInTO lstPeriods2= new BusinesspartnerInTO();
		 BusinesspartnerTO lstPeriods3 = null;
			BusinesspartnerTO nuevo = new BusinesspartnerTO();
			
			
			try {
				lstPeriods3 = catalog.get_businesspartnerBykey("004");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(lstPeriods3.getCardcode()+"  "+lstPeriods3.getCardname()+ " - "
					);
			Iterator<BusinesspartnerAcountTO> iterator = lstPeriods3.getBusinesspartnerAcount().iterator();
			while (iterator.hasNext()) {
				BusinesspartnerAcountTO periodo = (BusinesspartnerAcountTO) iterator.next();
				System.out.println(periodo.getCardcode()+"  "+periodo.getAcctcode()+ " - "
						);
			}
		
		
		//bus.setBusinesspartnerAcount(ne);
			
			List aux=lstPeriods3.getBusinesspartnerAcount();
			BusinesspartnerAcountTO nuevo1= new BusinesspartnerAcountTO();
			nuevo1.setAcctcode("110102010104");
			nuevo1.setCardcode("004");
			nuevo1.setBalance(0.0);
			nuevo1.setAcctype(17);
			aux.add(nuevo1);
			lstPeriods3.setBusinesspartnerAcount(aux);
			
		try {
			resp= catalog.cat_bpa_businesspartner_mtto(lstPeriods3,2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(resp.getMensaje());
		
		
	}

}

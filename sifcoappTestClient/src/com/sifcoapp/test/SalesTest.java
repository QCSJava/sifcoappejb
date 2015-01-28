package com.sifcoapp.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.SalesEJBClient;
import com.sifcoapp.objects.sales.to.*;

public class SalesTest {
	private static SalesEJBClient sales=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if 		(sales==null)
			sales=new SalesEJBClient();
		
		String retorno=null;
		
		try {
			retorno=sales.doSales();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(retorno);
		
	}
	
	public static void getSales() {

		List lstPeriods = new Vector();
		SalesInTO nuevo = new SalesInTO();
		nuevo.setDocnum(21);
		Date fecha= new Date();
		nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			lstPeriods = sales.getSales(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<GoodsissuesTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			GoodsissuesTO periodo = (GoodsissuesTO) iterator.next();
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
		}
	}
}

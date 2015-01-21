package com.sifcoapp.test;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.InventoryEJBClient;
import com.sifcoapp.objects.inventory.to.GoodsReceiptInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
public class InventoryTEST {
	
	private static InventoryEJBClient Inventory;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (Inventory == null)
			Inventory = new InventoryEJBClient();

		String v_method = args[0];

		/*
		 * List lstPeriods=new Vector();
		 * 
		 * lstPeriods=AccountingEJBService.getAccPeriods();
		 * 
		 * System.out.println(lstPeriods);
		 */

		try {
			InventoryTEST.class.getMethod(args[0], null).invoke(null, null);
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
	
	public static void getGood() {

		List lstPeriods = new Vector();
		GoodsissuesInTO nuevo = new GoodsissuesInTO();
		nuevo.setDocnum(21);
		Date fecha= new Date();
		nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		lstPeriods = Inventory.getGoodsissues(nuevo);
		Iterator<GoodsissuesTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			GoodsissuesTO periodo = (GoodsissuesTO) iterator.next();
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
		}
	}
	public static void getGoodreceipt() {

		List lstPeriods = new Vector();
		GoodsReceiptInTO nuevo = new GoodsReceiptInTO();
		nuevo.setDocnum(25);
		Date fecha= new Date();
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		lstPeriods = Inventory.getGoodsreceipt(nuevo);
		Iterator<GoodsreceiptTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			GoodsreceiptTO periodo = (GoodsreceiptTO) iterator.next();
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
		}
	}
}

package com.sifcoapp.test;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.InventoryEJBClient;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.inventory.to.GoodsIssuesDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
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
	
	public static void getGoodreceiptDetail() {

		List lstPeriods = new Vector();
		int	docentry = 5;

		lstPeriods = Inventory.getGoodsReceiptDetail(docentry);
		Iterator<GoodsReceiptDetailTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			GoodsReceiptDetailTO periodo = (GoodsReceiptDetailTO) iterator.next();
			System.out.println(periodo.getItemcode() + " - "
					+ periodo.getDscription() + " - "
					+ periodo.getAcctcode());
		}
	}
	
	public static void getGoodIssuesDetail() {

		List lstPeriods = new Vector();
		int	docentry = 5;

		lstPeriods = Inventory.getGoodsIssuesDetail(docentry);
		Iterator<GoodsIssuesDetailTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			GoodsIssuesDetailTO periodo = (GoodsIssuesDetailTO) iterator.next();
			System.out.println(periodo.getItemcode() + " - "
					+ periodo.getDscription() + " - "
					+ periodo.getAcctcode());
		}
	}
	
	public static void GoodIssuesDetail_mtto() {

		GoodsIssuesDetailTO document = new GoodsIssuesDetailTO();

		document.setDocentry(1);
		document.setLinenum(1);
		document.setItemcode("ART-001");
		document.setDscription("Articulo de prueba");
		document.setQuantity(10.25);
		document.setOpenqty(10.25);
		document.setPrice(11.25);
		document.setLinetotal(100.00);



		int resp = Inventory.inv_goodsIssuesDetail_mtto(document, 1);

		System.out.println(resp);

	}
	
	public static void GoodReceiptDetail_mtto() {

		GoodsReceiptDetailTO document = new GoodsReceiptDetailTO();

		document.setDocentry(1);
		document.setLinenum(1);
		document.setItemcode("ART-001");
		document.setDscription("Articulo de prueba");
		document.setQuantity(10.25);
		document.setOpenqty(10.25);
		document.setPrice(11.25);
		document.setLinetotal(100.00);


		int resp = Inventory.inv_goodsReceiptDetail_mtto(document, 1);

		System.out.println(resp);

	}
}

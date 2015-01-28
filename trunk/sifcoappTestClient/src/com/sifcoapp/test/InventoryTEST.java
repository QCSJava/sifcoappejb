package com.sifcoapp.test;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.InventoryEJBClient;
import com.sifcoapp.objects.inventory.to.*;
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
		try {
			lstPeriods = Inventory.getGoodsissues(nuevo);
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
	
	//#########################PRUEBAS DE TABLAS GOODSISSUES AND 
	public static void getGoodreceipt() {

		List lstPeriods = new Vector();
		GoodsReceiptInTO nuevo = new GoodsReceiptInTO();
		nuevo.setDocnum(0);
		nuevo.setComments("N");
		nuevo.setFromwhscode("R");
		nuevo.setTowhscode("i");
		//nuevo.setRef1("E");
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			lstPeriods = Inventory.getGoodsreceipt(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<GoodsreceiptTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			GoodsreceiptTO periodo = (GoodsreceiptTO) iterator.next();
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
		}
	}
	public static void getGoodsissues() {

		List lstPeriods = new Vector();
		GoodsissuesInTO nuevo = new GoodsissuesInTO();
		nuevo.setDocnum(0);
		nuevo.setComments("ERt");
		nuevo.setRef1("s");
		nuevo.setFromwhscode("o");
		nuevo.setTowhscode("sd");
		//nuevo.setFromwhscode("R");
		//nuevo.setTowhscode("E");
		//nuevo.setRef1("E");
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			lstPeriods = Inventory.getGoodsissues(nuevo);
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

	
	public static void GoodReceipt_mtto() {

		int _result=0;
		GoodsreceiptTO parameters = new GoodsreceiptTO();
		parameters.setDocnum(26);
		parameters.setUsersign(1);
		parameters.setDoctotal(111.2);
		try {
			_result = Inventory.inv_GoodsReceipt_mtto(parameters, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("luego de servicio");
		System.out.println(_result);

	}

	
	public static void getGoodreceiptDetail() {

		List lstPeriods = new Vector();
		int	docentry = 5;

		try {
			lstPeriods = Inventory.getGoodsReceiptDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		try {
			lstPeriods = Inventory.getGoodsIssuesDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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



		int resp=0;
		try {
			resp = Inventory.inv_goodsIssuesDetail_mtto(document, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(resp);

	}
	
	public static void GoodReceipt_mtto_condetalle() {
		int _result=0;
		GoodsReceiptDetailTO document = new GoodsReceiptDetailTO();

		GoodsreceiptTO parameters = new GoodsreceiptTO();
		
		List prueba = new Vector();
		GoodsReceiptDetailTO document1 = new GoodsReceiptDetailTO();
		
		//document.setDocentry(1);
		document.setLinenum(5);
		document.setItemcode("ART-001");
		document.setDscription("Articulo de prueba");
		document.setQuantity(10.25);
		document.setOpenqty(1.56);
		document.setPrice(11.25);
		document.setLinetotal(5.6);
		prueba.add(document);
		//document1.setDocentry(1);
		document1.setLinenum(6);
		document1.setItemcode("ART-001");
		document1.setDscription("Articulo de prueba");
		document1.setQuantity(10.25);
		document1.setOpenqty(15.56);
		document1.setPrice(11.25);
		document1.setLinetotal(5.6);
		prueba.add(document1);
		parameters.setDocnum(485);
		parameters.setUsersign(1);
		//parameters.setDocentry(26);
		parameters.setDoctotal(3.6);
		Date fecha= new Date();
		parameters.setDocdate(fecha);
		parameters.setGoodReceiptDetail(prueba);
		try {
			_result = Inventory.inv_GoodsReceipt_mtto(parameters,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("luego de servicio");
		System.out.println(_result);

	}
	
	public static void GoodsIssues_mtto() {

		int _result=0;
		GoodsissuesTO parameters = new GoodsissuesTO();
	
		List prueba = new Vector();
		GoodsIssuesDetailTO document = new GoodsIssuesDetailTO();
		GoodsIssuesDetailTO document1 = new GoodsIssuesDetailTO();
		
		//document.setDocentry(1);
		document.setLinenum(1);
		document.setItemcode("ART-001");
		document.setDscription("Articulo de prueba");
		document.setQuantity(10.25);
		document.setOpenqty(10.25);
		document.setPrice(11.25);
		document.setLinetotal(100.00);
		prueba.add(document);
		document1.setDocentry(1);
		document1.setLinenum(2);
		document1.setItemcode("ART-001");
		document1.setDscription("Articulo de prueba");
		document1.setQuantity(10.25);
		document1.setOpenqty(10.25);
		document1.setPrice(11.25);
		document1.setLinetotal(100.00);
		prueba.add(document1);
		parameters.setDocnum(485);
		parameters.setUsersign(1);
		parameters.setDocentry(26);
		//parameters.setDoctotal(15.5);
		parameters.setGoodIssuesDetail(prueba);
		try {
			_result = Inventory.inv_goodsissues_mtto(parameters,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("luego de servicio");
		System.out.println(_result);

	}
	public static void getGoodreceiptbykey() {

		GoodsreceiptTO periodo = new GoodsreceiptTO();
		List lstPeriods = new Vector();
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			periodo = Inventory.getGoodsReceiptByKey(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
			
			lstPeriods = periodo.getGoodReceiptDetail();
			Iterator<GoodsReceiptDetailTO> iterator = lstPeriods.iterator();
			while (iterator.hasNext()) {
				GoodsReceiptDetailTO periodo2 = (GoodsReceiptDetailTO) iterator.next();
				System.out.println(periodo2.getItemcode() + " - "
						+ periodo2.getDscription() + " - "
						+ periodo2.getAcctcode());
			}	
	}
	public static void getGoodsissuesbykey() {

		GoodsissuesTO periodo = new GoodsissuesTO();
		List lstPeriods = new Vector();
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			periodo = Inventory.getGoodsissuesByKey(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
			
			lstPeriods = periodo.getGoodIssuesDetail();
			Iterator<GoodsIssuesDetailTO> iterator = lstPeriods.iterator();
			while (iterator.hasNext()) {
				GoodsIssuesDetailTO periodo2 = (GoodsIssuesDetailTO) iterator.next();
				System.out.println(periodo2.getItemcode() + " - "
						+ periodo2.getDscription() + " - "
						+ periodo2.getAcctcode());
			}	
	}
	
	//###########################pruebas de tablas transfers y trasnfersdetail###################################################
	
	public static void transfers_mtto() {

		int _result=0;
		TransfersTO parameters = new TransfersTO();
	
		List prueba = new Vector();
		TransfersDetailTO document = new TransfersDetailTO();
		TransfersDetailTO document1 = new TransfersDetailTO();
		
		//document.setDocentry(1);
		document.setLinenum(1);
		document.setItemcode("ART-001");
		document.setDscription("Articulo de prueba");
		document.setQuantity(10.25);
		document.setOpenqty(10.25);
		document.setPrice(11.25);
		document.setLinetotal(100.00);
		prueba.add(document);
		//document1.setDocentry(1);
		document1.setLinenum(2);
		document1.setItemcode("ART-001");
		document1.setDscription("Articulo de prueba");
		document1.setQuantity(10.25);
		document1.setOpenqty(10.25);
		document1.setPrice(11.25);
		document1.setLinetotal(100.00);
		prueba.add(document1);
		parameters.setDocnum(485);
		//parameters.setUsersign(1);
		parameters.setDocentry(26);
		parameters.setDoctotal(15.5);
		parameters.setTransfersDetail(prueba);
		try {
			_result = Inventory.inv_transfers_mtto(parameters,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("luego de servicio");
		System.out.println(_result);

	}
	
	
	public static void getTransfers_by_key() {


		List lstPeriods = new Vector();
		TransfersTO nuevo = new TransfersTO();
	
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			nuevo = Inventory.getTransfersByKey(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TransfersTO periodo = (TransfersTO) lstPeriods.get(0);
		System.out.println(nuevo.getDocnum()+ " - "
				+ nuevo.getSeries() + " - "
				+ nuevo.getDocentry()+"-");
		/*
		while (iterator.hasNext()) {
			TransfersTO periodo = (TransfersTO) iterator.next();
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
		}*/
	}
	public static void getTransfer() {

		List lstPeriods = new Vector();
		TransfersInTO nuevo = new TransfersInTO();
		nuevo.setDocnum(12);
		//Date fecha= new Date();
		//nuevo.setDocdate(fecha);
		//nuevo.setSeries(42);
		try {
			lstPeriods = Inventory.getTransfers(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<TransfersTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			TransfersTO periodo = (TransfersTO) iterator.next();
			System.out.println(periodo.getDocnum()+ " - "
					+ periodo.getSeries() + " - "
					+ periodo.getDocentry());
		}
	}

}



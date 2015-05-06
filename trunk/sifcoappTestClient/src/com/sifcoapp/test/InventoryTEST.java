package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.InventoryEJBClient;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
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
		nuevo.setDocnum(485);
		Date fecha = new Date();
		// nuevo.setDocdate(fecha);
		// nuevo.setSeries(42);
		try {
			lstPeriods = Inventory.getGoodsissues(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error EJB " + e.getMessage());
		}
		Iterator<GoodsissuesTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			GoodsissuesTO periodo = (GoodsissuesTO) iterator.next();
			System.out.println(periodo.getDocnum() + " - "
					+ periodo.getSeries() + " - " + periodo.getDocentry()
					+ periodo.getCreatedate());
		}
	}

	// #########################PRUEBAS DE TABLAS GOODSISSUES AND
	public static void getGoodreceipt() {

		List lstPeriods = new Vector();
		GoodsReceiptInTO nuevo = new GoodsReceiptInTO();
		nuevo.setDocnum(85);
		// nuevo.setComments(null);
		// nuevo.setTowhscode("COD-01");
		// nuevo.setRef1("");
		// nuevo.setRef1("E");
		// nuevo.setDocdate(fecha);
		// nuevo.setSeries(42);
		try {
			lstPeriods = Inventory.getGoodsreceipt(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<GoodsreceiptTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			GoodsreceiptTO periodo = (GoodsreceiptTO) iterator.next();
			System.out.println(periodo.getDocnum() + " - "
					+ periodo.getSeries() + " - " + periodo.getDocentry());
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
		// nuevo.setFromwhscode("R");
		// nuevo.setTowhscode("E");
		// nuevo.setRef1("E");
		// nuevo.setDocdate(fecha);
		// nuevo.setSeries(42);
		try {
			lstPeriods = Inventory.getGoodsissues(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<GoodsissuesTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			GoodsissuesTO periodo = (GoodsissuesTO) iterator.next();
			System.out.println(periodo.getDocnum() + " - "
					+ periodo.getSeries() + " - " + periodo.getDocentry());
		}
	}

	public static void GoodReceipt_mtto() {

		ResultOutTO _result = new ResultOutTO();
		GoodsReceiptDetailTO art= new GoodsReceiptDetailTO();
		GoodsreceiptTO parameters = new GoodsreceiptTO();
		parameters.setDocnum(26);
		parameters.setObjtype("30");
		parameters.setUsersign(1);
		List detalle = new Vector();
		art.setAcctcode("1.1");
		art.setObjtype("30");
		art.setWhscode("ALM-001");
		art.setItemcode("001-004-545-6114");
		art.setPrice(5.30);
		art.setQuantity(10.00);
        art.setLinetotal(53.00);
        art.setObjtype("30");
        detalle.add(art);
        java.util.Date utilDate = new java.util.Date(); //fecha actual
		  long lnMilisegundos = utilDate.getTime();
		  java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
       
		  parameters.setGoodReceiptDetail(detalle);
        parameters.setDocdate(sqlDate);
        parameters.setTowhscode("ALM-001");
		
		// parameters.setDoctotal(111.2);
		try {
			_result = Inventory.inv_GoodsReceipt_mtto(parameters, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error EJB " + e.getMessage());
		}

		System.out.println("luego de servicio");
		System.out.println(_result.getCodigoError() + "----"
				+ _result.getDocentry());

	}

	public static void getGoodreceiptDetail() {

		List lstPeriods = new Vector();
		int docentry = 5;

		try {
			lstPeriods = Inventory.getGoodsReceiptDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<GoodsReceiptDetailTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			GoodsReceiptDetailTO periodo = (GoodsReceiptDetailTO) iterator
					.next();
			System.out.println(periodo.getItemcode() + " - "
					+ periodo.getDscription() + " - " + periodo.getAcctcode());
		}
	}

	public static void getGoodIssuesDetail() {

		List lstPeriods = new Vector();
		int docentry = 10;

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
					+ periodo.getDscription() + " - " + periodo.getAcctcode());
		}
	}

	public static void GoodReceipt_mtto_condetalle() {
		ResultOutTO _result = new ResultOutTO();
		GoodsReceiptDetailTO document = new GoodsReceiptDetailTO();

		GoodsreceiptTO parameters = new GoodsreceiptTO();

		List prueba = new Vector();
		GoodsReceiptDetailTO document1 = new GoodsReceiptDetailTO();

		// document.setDocentry(1);
		document.setLinenum(5);
		document.setItemcode("ART-001");
		document.setDscription("Articulo de prueba");
		document.setQuantity(10.2);
		// document.setOpenqty(1.56);
		document.setPrice(11.35);
		// document.setLinetotal(5.6);
		prueba.add(document);
		// document1.setDocentry(1);
		document1.setLinenum(6);
		document1.setItemcode("ART-001");
		document1.setDscription("Articulo de prueba");
		document1.setQuantity(10.25);
		// document1.setOpenqty(15.56);
		document1.setPrice(11.5);
		// document1.setLinetotal(5.6);
		prueba.add(document1);
		parameters.setDocnum(485);
		parameters.setUsersign(1);
		// parameters.setDocentry(26);
		// parameters.setDoctotal(3.6);
		Date fecha = new Date();
		parameters.setDocdate(fecha);
		parameters.setGoodReceiptDetail(prueba);
		try {
			_result = Inventory.inv_GoodsReceipt_mtto(parameters, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error EJB " + e.getMessage());
		}

		System.out.println("luego de servicio");
		System.out.println(_result.getCodigoError() + "----"
				+ _result.getCodigoError());

	}

	public static void GoodsIssues_mtto() {

		ResultOutTO _result = new ResultOutTO();
		GoodsissuesTO parameters = new GoodsissuesTO();

		List prueba = new Vector();
		GoodsIssuesDetailTO document = new GoodsIssuesDetailTO();
		GoodsIssuesDetailTO document1 = new GoodsIssuesDetailTO();

		// document.setDocentry(1);
		document.setLinenum(1);
		document.setItemcode("ART-001");
		document.setDscription("Articulo de prueba");
		document.setQuantity(10.25);
		// document.setOpenqty(10.25);
		document.setPrice(11.25);
		document.setLinetotal(100.00);
		prueba.add(document);
		// document1.setDocentry(1);
		document1.setLinenum(2);
		document1.setItemcode("ART-001");
		document1.setDscription("Articulo de prueba");
		document1.setQuantity(10.25);
		// document1.setOpenqty(10.25);
		document1.setPrice(11.25);
		// document1.setLinetotal(100.00);
		prueba.add(document1);
		parameters.setDocnum(485);
		// parameters.setUsersign(1);
		// parameters.setDocentry(26);
		// parameters.setDoctotal(15.5);
		parameters.setGoodIssuesDetail(prueba);
		try {
			_result = Inventory.inv_goodsissues_mtto(parameters, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error EJB " + e.getMessage());
		}

		System.out.println("luego de servicio");
		System.out.println(_result.getDocentry());

	}

	public static void getGoodreceiptbykey() {

		GoodsreceiptTO periodo = new GoodsreceiptTO();
		List lstPeriods = new Vector();
		// nuevo.setDocdate(fecha);
		// nuevo.setSeries(42);
		try {
			periodo = Inventory.getGoodsReceiptByKey(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(periodo.getDocnum() + " - " + periodo.getSeries()
				+ " - " + periodo.getDocentry());

		lstPeriods = periodo.getGoodReceiptDetail();
		Iterator<GoodsReceiptDetailTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			GoodsReceiptDetailTO periodo2 = (GoodsReceiptDetailTO) iterator
					.next();
			System.out
					.println(periodo2.getItemcode() + " - "
							+ periodo2.getDscription() + " - "
							+ periodo2.getAcctcode());
		}
	}

	public static void getGoodsissuesbykey() {

		GoodsissuesTO periodo = new GoodsissuesTO();
		List lstPeriods = new Vector();
		// nuevo.setDocdate(fecha);
		// nuevo.setSeries(42);
		try {
			periodo = Inventory.getGoodsissuesByKey(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(periodo.getDocnum() + " - " + periodo.getSeries()
				+ " - " + periodo.getDocentry());

		lstPeriods = periodo.getGoodIssuesDetail();
		Iterator<GoodsIssuesDetailTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			GoodsIssuesDetailTO periodo2 = (GoodsIssuesDetailTO) iterator
					.next();
			System.out
					.println(periodo2.getItemcode() + " - "
							+ periodo2.getDscription() + " - "
							+ periodo2.getAcctcode());
		}
	}

	// ###########################pruebas de tablas transfers y
	// trasnfersdetail###################################################

	public static void transfers_mtto() {

		ResultOutTO _result = new ResultOutTO();
		TransfersTO parameters = new TransfersTO();

		List prueba = new Vector();
		TransfersDetailTO document = new TransfersDetailTO();
		TransfersDetailTO document1 = new TransfersDetailTO();

		// document.setDocentry(1);
		document.setLinenum(1);
		document.setItemcode("ART-001");
		document.setDscription("Articulo de prueba");
		document.setQuantity(10.25);
		document.setOpenqty(10.25);
		document.setPrice(11.25);
		document.setLinetotal(100.00);
		prueba.add(document);
		// document1.setDocentry(1);
		document1.setLinenum(2);
		document1.setItemcode("ART-001");
		document1.setDscription("Articulo de prueba");
		document1.setQuantity(10.25);
		document1.setOpenqty(10.25);
		document1.setPrice(11.25);
		document1.setLinetotal(100.00);
		prueba.add(document1);
		parameters.setDocnum(485);
		// parameters.setUsersign(1);
		parameters.setDocentry(26);
		parameters.setDoctotal(15.5);
		parameters.setTransfersDetail(prueba);
		try {
			_result = Inventory.inv_transfers_mtto(parameters, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("luego de servicio");
		System.out.println(_result.getDocentry());

	}

	public static void getTransfers_by_key() {

		List lstPeriods = new Vector();
		TransfersTO nuevo = new TransfersTO();

		// nuevo.setDocdate(fecha);
		// nuevo.setSeries(42);
		try {
			nuevo = Inventory.getTransfersByKey(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TransfersTO periodo = (TransfersTO) lstPeriods.get(0);
		System.out.println(nuevo.getDocnum() + " - " + nuevo.getSeries()
				+ " - " + nuevo.getDocentry() + "-");
		/*
		 * while (iterator.hasNext()) { TransfersTO periodo = (TransfersTO)
		 * iterator.next(); System.out.println(periodo.getDocnum()+ " - " +
		 * periodo.getSeries() + " - " + periodo.getDocentry()); }
		 */
	}

	public static void getTransfer() {

		List lstPeriods = new Vector();
		TransfersInTO nuevo = new TransfersInTO();
		// nuevo.setDocnum(12);
		// Date fecha= new Date();
		// nuevo.setDocdate(fecha);
		// nuevo.setSeries(42);
		try {
			lstPeriods = Inventory.getTransfers(nuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<TransfersTO> iterator = lstPeriods.iterator();
		while (iterator.hasNext()) {
			TransfersTO periodo = (TransfersTO) iterator.next();
			System.out.println(periodo.getDocnum() + " - "
					+ periodo.getSeries() + " - " + periodo.getDocentry());
		}
	}

	public static void adm_inventoryLog_mtto(){
		InventoryLogTO nuevo = new InventoryLogTO();
		ResultOutTO retorno;
		nuevo.setCardname("00001");
		nuevo.setBase_ref("baseref");
		Date fecha= new Date();
		//nuevo.setDocdate(fecha);
		nuevo.setDoctotal(55.25);
		retorno=Inventory.adm_inventorylog_mtto(nuevo,Common.MTTOINSERT);
		System.out.println(retorno.getMensaje());

	}
	
	public static void getInventory_by_key() {

		List lstPeriods = new Vector();
		InventoryLogTO nuevo = new InventoryLogTO();

		// nuevo.setDocdate(fecha);
		// nuevo.setSeries(42);
		try {
			nuevo = Inventory.getInventoryLogByKey(6);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TransfersTO periodo = (TransfersTO) lstPeriods.get(0);
		System.out.println(nuevo.getBase_ref() + " - " 
				+ " - " + nuevo.getDocentry() + "-");
		/*
		 * while (iterator.hasNext()) { TransfersTO periodo = (TransfersTO)
		 * iterator.next(); System.out.println(periodo.getDocnum()+ " - " +
		 * periodo.getSeries() + " - " + periodo.getDocentry()); }
		 */
	}
}

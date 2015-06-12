package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.AccountingEJBClient;
import com.sifcoapp.client.AdminEJBClient;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.DocStatusTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.admin.to.PricesListInTO;
import com.sifcoapp.objects.admin.to.PricesListTO;
import com.sifcoapp.objects.admin.to.TablesCatalogTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.security.to.ProfileDetOutTO;

import java.sql.Date;

public class AdminTest {
	private static AdminEJBClient AdminEJBService;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (AdminEJBService == null)
			AdminEJBService = new AdminEJBClient();

		String v_method = args[0];

		/*
		 * List lstPeriods=new Vector();
		 * 
		 * lstPeriods=AccountingEJBService.getAccPeriods();
		 * 
		 * System.out.println(lstPeriods);
		 */

		try {
			AdminTest.class.getMethod(args[0], null).invoke(null, null);
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

	public static void saveEnterprise() {

		EnterpriseTO parameters = new EnterpriseTO();
		EnterpriseOutTO resp = null;

		parameters.setCode(1);
		parameters.setCompnyAddr("San Bartolo1");
		parameters.setCompnyName("ACOETMISAB");
		parameters.setCountry_catalog("01");
		parameters.setCrintHeadr("ACOETMISAB");
		parameters.setE_Mail("contactenos@acoetmisab.com.sv2");
		parameters.setFax("2222-1111");
		parameters.setManager("Pepe Perez");
		parameters.setPhone1("2222-2222");
		parameters.setPhone2("2222-3333");
		parameters.setTaxIdNum("1234-5");

		try {
			resp = AdminEJBService.saveEnterprise(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Error EJB " + e.getMessage());
		}

		// System.out.println(resp.getRespCode());

	}

	public static void getEnterpriseInfo() {
		EnterpriseTO resp = null;

		try {
			resp = AdminEJBService.getEnterpriseInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error EJB " + e.getMessage());
		}

		// System.out.println(resp.getCompnyName());
		// System.out.println(resp.getCompnyAddr());

	}

	public static void findCatalog() {

		List catlgLst = null;

		try {
			catlgLst = AdminEJBService.findCatalog("Impuestos");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("luego de servicio mod   ");

		Iterator<CatalogTO> iterator = catlgLst.iterator();
		while (iterator.hasNext()) {
			// System.out.println(iterator.next());
			CatalogTO catalogTO = (CatalogTO) iterator.next();
			System.out.println("--->" + catalogTO.getCatcode() + "-"
					+ catalogTO.getCatvalue() + "-" + catalogTO.getCatvalue2());
		}
	}

	public static void findCatalogByKey() {

		CatalogTO catlgLst = null;

		try {
			catlgLst = AdminEJBService.findCatalogByKey("1", 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("luego de servicio mod   ");

		System.out.println("--->" + catlgLst.getCatcode() + "-"
				+ catlgLst.getCatvalue() + "-" + catlgLst.getCatvalue2());

	}

	/*
	 * Obtiene el catalogo de tablas del sistema
	 */
	public static void getTablesCatalogTest() {

		List catlgLst = null;

		try {
			catlgLst = AdminEJBService.getTablesCatalog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("luego de servicio");
		Iterator<TablesCatalogTO> iterator = catlgLst.iterator();
		while (iterator.hasNext()) {
			// System.out.println(iterator.next());
			TablesCatalogTO _returnTO = (TablesCatalogTO) iterator.next();
			System.out.println("Code: ->" + _returnTO.getCode());
			System.out.println("Name: ->" + _returnTO.getName());

		}
	}

	public static void cat_tab1_catalogos_mtto() {

		int _result = 0;
		CatalogTO parameters = new CatalogTO();

		parameters.setTablecode(1);
		parameters.setCatcode("1");
		parameters.setCatvalue("Panama 2");
		parameters.setCatvalue3("Panama 2");
		parameters.setCatstatus("P");
		parameters.setUsersign(1);

		// Agregar

		// _result=AdminEJBService.cat_tab1_catalogos_mtto(parameters,
		// Common.MTTOINSERT);

		// Actualizar

		parameters.setCatvalue("Honduras UPD -1");

		try {
			_result = AdminEJBService.cat_tab1_catalogos_mtto(parameters,
					Common.MTTOUPDATE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Borrar

		// _result=AdminEJBService.cat_tab1_catalogos_mtto(parameters,
		// Common.MTTODELETE);

		System.out.println("luego de servicio");
		System.out.println(_result);

	}

	public static void articles_mtto() {

		ArticlesTO article = null;

		String code = "001-004-545-6114";

		try {
			article = AdminEJBService.getArticlesByKey(code);
			//System.out.println(article.getPrice(42));
			
			System.out.println(article.getItemCode() + " - "
					+ article.getItemName());
			System.out.println("Almacenes asociados");

			Iterator<BranchArticlesTO> iterator = article.getBranchArticles()
					.iterator();
			while (iterator.hasNext()) {
				BranchArticlesTO branch = (BranchArticlesTO) iterator.next();
				System.out.println(branch.isIsasociated() + " - "
						+ branch.getWhscode() + " - " + branch.getWhsname());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		article.setValidFor("Y");
		ResultOutTO _result = new ResultOutTO();
		//ArticlesTO parameters = new ArticlesTO();
		//parameters.setItemCode("00-00-01");
		//parameters.setValidFor("N");
		//// parameters.setUserSign(2);
		
		// parameters.setNumInBuy(54.2);
		// parameters.setNumInSale(12.23);
		// parameters.setOnHand(2.5);
		// parameters.setPurPackUn(21.2);
		// parameters.setSalPackUn(12.2);
		// parameters.setAvgPrice(4.0);

		List branch = new Vector();

		//BranchArticlesTO branch1 = new BranchArticlesTO();
		//branch1.setIsasociated(true);
		// branch1.setIscommited(100.2);
		//branch1.setItemcode("0011-sfs");
		// branch1.setLocked("Y");

		//.setWhscode("alm-36");
		//BranchArticlesTO branch2 = new BranchArticlesTO();
		//branch2.setIsasociated(true);
		// branch1.setIscommited(100.2);
		//branch2.setItemcode("0011-sfs");
		// branch1.setLocked("Y");
		//branch2.setIscommited(56.5);
		//branch2.setWhscode("alm-55");
		// branch1.setMinstock(1.0);
		// branch1.setMaxstock(10.2);
		// branch1.setMinstock(1.2);
		// branch1.setOnhand(10.2);
		// branch1.setOnhand1(10.2);
		// branch1.setOnorder(2.5);
		// branch1.setMinorder(20.2);
		//branch.add(branch1);
		//branch.add(branch2);

		//parameters.setBranchArticles(branch);

		// parameters.setValidFrom((Date)"23/23/23" );

		// Agregar

		try {
			_result = AdminEJBService.cat_articles_mtto(article,2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Actualizar

		// parameters.setItemName("Honduras UPD");

		// _result=AdminEJBService.cat_articles_mtto(parameters,
		// Common.MTTOUPDATE);

		// Borrar

		// _result = AdminEJBService.cat_articles_mtto(parameters,
		// Common.MTTODELETE);

		System.out.println("luego de servicio");
		System.out.println(_result.getMensaje());

	}

	public static void getArticles() {
		List resp = null;

		ArticlesInTO param = new ArticlesInTO();
		//param.setSellItem("N");
		//param.setPrchseItem("N");
		//param.setItemType("2");
		//param.setItmsIsGrpCod("1");

		try {
			resp = AdminEJBService.getArticles(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<ArticlesTO> iterator = resp.iterator();
		while (iterator.hasNext()) {
			ArticlesTO article = (ArticlesTO) iterator.next();
			System.out.println(article.getItemCode() + " - "
					+ article.getItemName());
		}
	}

	public static void getArticlesByKey() {
		ArticlesTO article = null;

		String code = "001-004-545-6114";

		try {
			article = AdminEJBService.getArticlesByKey(code);
			//System.out.println(article.getPrice(42));
			
			System.out.println(article.getItemCode() + " - "
					+ article.getItemName());
			System.out.println("Almacenes asociados");

			Iterator<BranchArticlesTO> iterator = article.getBranchArticles()
					.iterator();
			while (iterator.hasNext()) {
				BranchArticlesTO branch = (BranchArticlesTO) iterator.next();
				System.out.println(branch.isIsasociated() + " - "
						+ branch.getWhscode() + " - " + branch.getWhsname() + " - "+ branch.getOnhand());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	/*
	 * public static void bracharticles_mtto() {
	 * 
	 * int _result; BranchArticlesTO parameters = new BranchArticlesTO();
	 * parameters.setIsasociated(true); parameters.setIscommited(100.2);
	 * parameters.setItemcode("110100"); parameters.setLocked("Y");
	 * parameters.setWhscode("suc-001"); parameters.setMinstock(1.0);
	 * parameters.setMaxstock(10.2); parameters.setMinstock(1.2);
	 * parameters.setOnhand(10.2); parameters.setOnhand1(10.2);
	 * parameters.setOnorder(2.5); parameters.setMinorder(20.2);
	 * 
	 * // Agregar
	 * 
	 * _result = AdminEJBService.cat_brancharticles_mtto(parameters,
	 * Common.MTTOINSERT);
	 * 
	 * // Actualizar
	 * 
	 * // _result=AdminEJBService.cat_brancharticles_mtto(parameters, //
	 * Common.MTTOUPDATE);
	 * 
	 * // Borrar
	 * 
	 * // _result=AdminEJBService.cat_brancharticles_mtto(parameters, //
	 * Common.MTTODELETE);
	 * 
	 * System.out.println("luego de servicio"); System.out.println(_result);
	 * 
	 * }
	 */

	public static void brach_mtto() {

		int _result = 0;
		BranchTO parameters = new BranchTO();
		parameters.setWhscode("SUC-002");
		parameters.setWhsname("Sucursal de pruebas");
		parameters.setGrp_code("54");
		parameters.setLocked(false);
		parameters.setStreet("Calle");
		parameters.setCity("San Salvador");
		parameters.setCountry("ES");
		parameters.setLocation("M");
		parameters.setUsetax("N");
		parameters.setBalinvntac("1111111");
		parameters.setUsersign(1);

		// Agregar

		/*
		 * _result = AdminEJBService.cat_branch_mtto(parameters,
		 * Common.MTTOINSERT);
		 */

		// Actualizar

		try {
			_result = AdminEJBService.cat_branch_mtto(parameters,
					Common.MTTOUPDATE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Borrar

		// _result=AdminEJBService.cat_branch_mtto(parameters,
		// Common.MTTODELETE);

		System.out.println("luego de servicio");
		System.out.println(_result);

	}

	public static void getBranch() {
		List resp = null;

		String name = null;
		String code = null;

		try {
			resp = AdminEJBService.getBranch(code, name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<BranchTO> iterator = resp.iterator();
		while (iterator.hasNext()) {
			BranchTO branch = (BranchTO) iterator.next();
			System.out.println(branch.getWhscode() + " - "
					+ branch.getWhsname());
		}
	}

	/* listas de precios */

	public static void getPricesList() {
		List resp = null;
		PricesListInTO para = null;

		String name = null;
		String code = null;

		try {
			resp = AdminEJBService.getPricesList(para);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<PricesListTO> iterator = resp.iterator();
		while (iterator.hasNext()) {
			PricesListTO branch = (PricesListTO) iterator.next();
			System.out.println(branch.getListnum() + " - "
					+ branch.getListname() + " - " + branch.getFactor());
		}
	}

	public static void pricesList_mtto() {

		ResultOutTO _result = new ResultOutTO();
		PricesListTO para = new PricesListTO();

		para.setListname("Lista desde Eclipse");
		para.setBase_num(1);
		para.setFactor(2.0);

	

		try {

			 para = AdminEJBService.getPricesListByKey(3);
			 //para.setBase_num(2);
			_result = AdminEJBService.cat_prl0_priceslist_mtto(para, 2, true);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("luego de servicio");
		System.out.println(_result);

	}
	public static void articlesprices() {

		ResultOutTO _result = new ResultOutTO();
		PricesListTO para = new PricesListTO();
	
		try {

			 para = AdminEJBService.getPricesListByKey(2);
			
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("luego de servicio");
		System.out.println(_result);

	}

	
	/*Estados*/
	public static void ListStatus() {
		List resp = null;
	

		try {
			resp = Common.getDocStatusList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<DocStatusTO> iterator = resp.iterator();
		while (iterator.hasNext()) {
			DocStatusTO branch = (DocStatusTO) iterator.next();
			System.out.println(branch.getCode() + " - "
					+ branch.getValue());
		}
	}
	

	public static void findCatalogQS() {

		List catlgLst = null;

		try {
			catlgLst = AdminEJBService.findCatQS("paises");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("luego de servicio mod   ");

		Iterator<CatalogTO> iterator = catlgLst.iterator();
		while (iterator.hasNext()) {
			// System.out.println(iterator.next());
			CatalogTO catalogTO = (CatalogTO) iterator.next();
			System.out.println("--->" + catalogTO.getCatcode() + "-"
					+ catalogTO.getCatvalue() + "-" + catalogTO.getCatvalue2());
		}
	}
}

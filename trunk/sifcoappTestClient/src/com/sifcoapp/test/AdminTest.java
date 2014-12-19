package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import com.sifcoapp.client.AccountingEJBClient;
import com.sifcoapp.client.AdminEJBClient;
import com.sifcoapp.objects.admin.to.AccPeriodTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.admin.to.TablesCatalogTO;
import com.sifcoapp.objects.catalogos.Common;
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

		resp = AdminEJBService.saveEnterprise(parameters);

		System.out.println(resp.getRespCode());

	}

	public static void getEnterpriseInfo() {
		EnterpriseTO resp = null;

		resp = AdminEJBService.getEnterpriseInfo();

		System.out.println(resp.getCompnyName());
		System.out.println(resp.getCompnyAddr());

	}

	public static void findCatalog() {

		List catlgLst = null;

		catlgLst = AdminEJBService.findCatalog("paises");
		System.out.println("luego de servicio mod   ");
		Iterator<CatalogTO> iterator = catlgLst.iterator();
		while (iterator.hasNext()) {
			// System.out.println(iterator.next());
			CatalogTO catalogTO = (CatalogTO) iterator.next();
			System.out.println("--->" + catalogTO.getCatcode() + "-"
					+ catalogTO.getCatvalue() + "-" + catalogTO.getCatvalue2());
		}
	}

	/*
	 * Obtiene el catalogo de tablas del sistema
	 */
	public static void getTablesCatalogTest() {

		List catlgLst = null;

		catlgLst = AdminEJBService.getTablesCatalog();
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

		int _result;
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

		_result = AdminEJBService.cat_tab1_catalogos_mtto(parameters,
				Common.MTTOUPDATE);

		// Borrar

		// _result=AdminEJBService.cat_tab1_catalogos_mtto(parameters,
		// Common.MTTODELETE);

		System.out.println("luego de servicio");
		System.out.println(_result);

	}

	public static void articles_mtto() {

		int _result;
		ArticlesTO parameters = new ArticlesTO();
		parameters.setItemCode("art-001");
		parameters.setItemName("Nombre Prueba 7");
		parameters.setUserSign(2);
		parameters.setItemType("S");
		parameters.setNumInBuy(54.2);
		parameters.setNumInSale(12.23);
		parameters.setOnHand(2.5);
		parameters.setPurPackUn(21.2);
		parameters.setSalPackUn(12.2);
		parameters.setAvgPrice(4.0);

		BranchArticlesTO branch[] = new BranchArticlesTO[1];
		branch[0] = new BranchArticlesTO();
		branch[0].setIsasociated(true);
		branch[0].setIscommited(100.2);
		branch[0].setItemcode("art-001");
		branch[0].setLocked("Y");
		branch[0].setWhscode("suc-001");
		branch[0].setMinstock(1.0);
		branch[0].setMaxstock(10.2);
		branch[0].setMinstock(1.2);
		branch[0].setOnhand(10.2);
		branch[0].setOnhand1(10.2);
		branch[0].setOnorder(2.5);
		branch[0].setMinorder(20.2);

		parameters.setBranchArticles(branch);

		// parameters.setValidFrom((Date)"23/23/23" );

		// Agregar

		_result = AdminEJBService.cat_articles_mtto(parameters,
				Common.MTTOINSERT);

		// Actualizar

		// parameters.setItemName("Honduras UPD");

		// _result=AdminEJBService.cat_articles_mtto(parameters,
		// Common.MTTOUPDATE);

		// Borrar

		// _result = AdminEJBService.cat_articles_mtto(parameters,
		// Common.MTTODELETE);

		System.out.println("luego de servicio");
		System.out.println(_result);

	}

	public static void bracharticles_mtto() {

		int _result;
		BranchArticlesTO parameters = new BranchArticlesTO();
		parameters.setIsasociated(true);
		parameters.setIscommited(100.2);
		parameters.setItemcode("110100");
		parameters.setLocked("Y");
		parameters.setWhscode("suc-001");
		parameters.setMinstock(1.0);
		parameters.setMaxstock(10.2);
		parameters.setMinstock(1.2);
		parameters.setOnhand(10.2);
		parameters.setOnhand1(10.2);
		parameters.setOnorder(2.5);
		parameters.setMinorder(20.2);

		// Agregar

		_result = AdminEJBService.cat_brancharticles_mtto(parameters,
				Common.MTTOINSERT);

		// Actualizar

		// _result=AdminEJBService.cat_brancharticles_mtto(parameters, Common.MTTOUPDATE);

		// Borrar

		// _result=AdminEJBService.cat_brancharticles_mtto(parameters, Common.MTTODELETE);

		System.out.println("luego de servicio");
		System.out.println(_result);

	}

	public static void accPeridod_mtto() {

		int _result;
		int parameters = 2015;		
		int usersing = 1;

		// Agregar

		_result = AdminEJBService.cat_accPeriod_mtto(parameters,usersing,Common.MTTOINSERT);

		// Actualizar

		// _result=AdminEJBService.cat_accPeriod_mtto(parameters,
		// Common.MTTOUPDATE);

		// Borrar

		// _result=AdminEJBService.cat_accPeriod_mtto(parameters,
		// Common.MTTODELETE);

		System.out.println("luego de servicio");
		System.out.println(_result);

	}

	
}

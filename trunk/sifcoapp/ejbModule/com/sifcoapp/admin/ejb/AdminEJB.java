package com.sifcoapp.admin.ejb;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;

import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseOutTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.catalogos.Common;

/**
 * Session Bean implementation class AdminEJB
 */
@Stateless
public class AdminEJB implements AdminEJBRemote {

	/**
	 * Default constructor.
	 */
	public AdminEJB() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Guarda los datos de la empresa
	 * 
	 * @author Rutilio Iraheta
	 * 
	 * @date 02/12/2014
	 * 
	 * @see
	 * com.sifcoapp.admin.ejb.AdminEJBRemote#saveEnterprise(com.sifcoapp.objects
	 * .admin.to.EnterpriseTO)
	 */
	public EnterpriseOutTO saveEnterprise(EnterpriseTO parameters) {

		EnterpriseOutTO enterpriseOutTO = new EnterpriseOutTO();

		enterpriseOutTO.setRespCode(Common.RESP_OK_DB);

		return enterpriseOutTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sifcoapp.admin.ejb.AdminEJBRemote#saveEnterprise(com.sifcoapp.objects
	 * .admin.to.EnterpriseTO)
	 */
	public EnterpriseTO getEnterpriseInfo(int enterpriseCode) {

		EnterpriseTO enterpriseOutTO = new EnterpriseTO();

		enterpriseOutTO.setCode(1);
		enterpriseOutTO.setCompnyAddr("San Bartolo");
		enterpriseOutTO.setCompnyName("ACOETMISAB");
		enterpriseOutTO.setCountry_catalog("01");
		enterpriseOutTO.setCrintHeadr("ACOETMISAB");
		enterpriseOutTO.setE_Mail("contactenos@acoetmisab.com.sv");
		enterpriseOutTO.setFax("2222-1111");
		enterpriseOutTO.setManager("Pepe Perez");
		enterpriseOutTO.setPhone1("2222-2222");
		enterpriseOutTO.setPhone2("2222-3333");
		enterpriseOutTO.setTaxIdNum("1234-5");

		return enterpriseOutTO;
	}

	public EnterpriseTO getEnterpriseInfo() {
		// TODO Auto-generated method stub
		EnterpriseTO enterpriseOutTO = new EnterpriseTO();
		enterpriseOutTO = this.getEnterpriseInfo(0);
		return enterpriseOutTO;
	}

	public List findCatalog(String nameCatalog) {
		// TODO Auto-generated method stub
		List catlgLst=new Vector();
		
		CatalogTO catl1=new CatalogTO();
		catl1.setCodeCatlg("01");
		catl1.setValueCatlg("El Salvador");
		catl1.setCodeTable(1);
		
		CatalogTO catl2=new CatalogTO();
		catl2.setCodeCatlg("02");
		catl2.setValueCatlg("Guatemala");
		catl2.setCodeTable(1);
		
		catlgLst.add(catl1);
		catlgLst.add(catl2);
		
		AdminDAO adminDAO=new AdminDAO();
		
		adminDAO.findCatalog(nameCatalog);
		
		return catlgLst;
	}

}

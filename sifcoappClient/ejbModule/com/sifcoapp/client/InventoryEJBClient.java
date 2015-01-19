package com.sifcoapp.client;
import java.sql.Date;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.bussinessLogic.InventoryEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.inventory.to.GoodsissuesInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;

public class InventoryEJBClient implements InventoryEJBRemote{
	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/InventoryEJB!com.sifcoapp.bussinessLogic.InventoryEJBRemote";
	private static InventoryEJBRemote bean;
	private static Context context = null;

	public InventoryEJBClient() {
	       
        //2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			bean = (InventoryEJBRemote)context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List getListGoodsreceipt(int docnum, Date docdate, int series) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getGoodsissues(GoodsissuesInTO param) {
		// TODO Auto-generated method stub
		List lstPeriods = new Vector();

		lstPeriods = bean.getGoodsissues(param);

		return lstPeriods;
	}
	public boolean inv_goodsissues_mtto(GoodsissuesTO parameters, int accion) {
		// TODO Auto-generated method stub
		return false;
	}

	
}

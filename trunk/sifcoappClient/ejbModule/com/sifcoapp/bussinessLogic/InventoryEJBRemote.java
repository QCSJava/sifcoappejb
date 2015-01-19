package com.sifcoapp.bussinessLogic;

import java.sql.Date;
import java.util.List;

import com.sifcoapp.objects.inventory.to.GoodsissuesInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;

import javax.ejb.Remote;

@Remote
public interface InventoryEJBRemote {
	
	public List getListGoodsreceipt(int docnum, Date docdate, int series);
	
	public List getGoodsissues(GoodsissuesInTO param);
	
	public boolean inv_goodsissues_mtto(GoodsissuesTO parameters,int accion);
	
}

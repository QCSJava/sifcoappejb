package com.sifcoapp.bussinessLogic;

import java.sql.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface InventoryEJBRemote {
	
	public List getListGoodsreceipt(int docnum, Date docdate, int series);
	
	public List getGoodsissues(int docnum, Date docdate, int series);
	
	

}

package com.sifcoapp.bussinessLogic;
import java.util.List;

import com.sifcoapp.objects.inventory.to.GoodsissuesInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptInTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;

import javax.ejb.Remote;

@Remote
public interface InventoryEJBRemote {
	
	public List getGoodsreceipt(GoodsreceiptInTO param);
	
	public int Goodsreceipt_mtto(GoodsreceiptTO param);
	
	public List getGoodsissues(GoodsissuesInTO param);
	
	public boolean inv_goodsissues_mtto(GoodsissuesTO parameters,int accion);
	
}

package com.sifcoapp.bussinessLogic;

import java.util.List;

import com.sifcoapp.objects.inventory.to.GoodsIssuesDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;

import javax.ejb.Remote;

@Remote
public interface InventoryEJBRemote {

	public boolean inv_goodsissues_mtto(GoodsissuesTO parameters, int accion);

	public int inv_goodsIssuesDetail_mtto(GoodsIssuesDetailTO parameters,
			int accion);

	public int inv_goodsReceiptDetail_mtto(GoodsReceiptDetailTO parameters,
			int accion);

	public int inv_GoodsReceipt_mtto(GoodsreceiptTO param,int accion);

	public List getGoodsissues(GoodsissuesInTO param);

	public List getGoodsIssuesDetail(int docentry);

	public List getGoodsReceiptDetail(int docentry);

	public List getGoodsreceipt(GoodsReceiptInTO param);

}

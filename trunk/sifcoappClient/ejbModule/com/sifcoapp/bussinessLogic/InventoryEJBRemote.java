package com.sifcoapp.bussinessLogic;

import java.util.List;

import com.sifcoapp.objects.inventory.to.*;

import javax.ejb.Remote;

@Remote
public interface InventoryEJBRemote {

	public int inv_goodsissues_mtto(GoodsissuesTO parameters, int accion);

	public int inv_goodsIssuesDetail_mtto(GoodsIssuesDetailTO parameters,int accion);

	public int inv_goodsReceiptDetail_mtto(GoodsReceiptDetailTO parameters,int accion);

	public int inv_GoodsReceipt_mtto(GoodsreceiptTO param,int accion);

	public List getGoodsissues(GoodsissuesInTO param);
	public GoodsissuesTO getGoodsissuesByKey(int docentry);
	
	public List getGoodsIssuesDetail(int docentry);

	public List getGoodsReceiptDetail(int docentry);

	public List getGoodsreceipt(GoodsReceiptInTO param);
	
	public GoodsreceiptTO getGoodsReceiptByKey(int docentry);
	
	
	public List getTransfers(TransfersInTO param);
	public TransfersTO getTransfersByKey(int docentry);
	public int inv_transfers_mtto(TransfersTO parameters,int accion);
	
	public List getTransfersDetail(int docentry);
	public int inv_transfersDetail_mtto(TransfersDetailTO parameters, int action);


}

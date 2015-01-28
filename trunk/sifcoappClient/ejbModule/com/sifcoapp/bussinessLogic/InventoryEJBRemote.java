package com.sifcoapp.bussinessLogic;

import java.util.List;

import com.sifcoapp.objects.inventory.to.*;

import javax.ejb.EJBException;
import javax.ejb.Remote;

@Remote
public interface InventoryEJBRemote {

	public int inv_goodsissues_mtto(GoodsissuesTO parameters, int accion)
			throws EJBException;

	public int inv_goodsIssuesDetail_mtto(GoodsIssuesDetailTO parameters,
			int accion) throws EJBException;

	public int inv_goodsReceiptDetail_mtto(GoodsReceiptDetailTO parameters,
			int accion) throws EJBException;

	public int inv_GoodsReceipt_mtto(GoodsreceiptTO param, int accion)
			throws EJBException;

	public List getGoodsissues(GoodsissuesInTO param) throws EJBException;

	public GoodsissuesTO getGoodsissuesByKey(int docentry) throws EJBException;

	public List getGoodsIssuesDetail(int docentry) throws EJBException;

	public List getGoodsReceiptDetail(int docentry) throws EJBException;

	public List getGoodsreceipt(GoodsReceiptInTO param) throws EJBException;

	public GoodsreceiptTO getGoodsReceiptByKey(int docentry) throws EJBException;

	public List getTransfers(TransfersInTO param) throws EJBException;

	public TransfersTO getTransfersByKey(int docentry) throws EJBException;

	public int inv_transfers_mtto(TransfersTO parameters, int accion)
			throws EJBException;

	public List getTransfersDetail(int docentry) throws EJBException;

	public int inv_transfersDetail_mtto(TransfersDetailTO parameters, int action)
			throws EJBException;

}

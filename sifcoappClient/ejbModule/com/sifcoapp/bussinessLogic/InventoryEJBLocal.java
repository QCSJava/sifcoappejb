package com.sifcoapp.bussinessLogic;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;

import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
import com.sifcoapp.objects.inventory.to.InventoryLogTO;
import com.sifcoapp.objects.inventory.to.TransfersInTO;
import com.sifcoapp.objects.inventory.to.TransfersTO;

@Local
public interface InventoryEJBLocal {
	public ResultOutTO inv_goodsissues_mtto(GoodsissuesTO parameters, int accion) throws EJBException;

	public ResultOutTO inv_GoodsReceipt_mtto(GoodsreceiptTO param, int accion)throws EJBException;

	public List getGoodsissues(GoodsissuesInTO param) throws EJBException;

	public GoodsissuesTO getGoodsissuesByKey(int docentry) throws EJBException;

	public List getGoodsIssuesDetail(int docentry) throws EJBException;

	public List getGoodsReceiptDetail(int docentry) throws EJBException;

	public List getGoodsreceipt(GoodsReceiptInTO param) throws EJBException;

	public GoodsreceiptTO getGoodsReceiptByKey(int docentry) throws EJBException;

	public List getTransfers(TransfersInTO param) throws EJBException;

	public TransfersTO getTransfersByKey(int docentry) throws EJBException;

	public ResultOutTO inv_transfers_mtto(TransfersTO parameters, int accion)throws EJBException;

	public List getTransfersDetail(int docentry) throws EJBException;

	
	
	public InventoryLogTO getInventoryLogByKey(int messageid) throws EJBException;
}

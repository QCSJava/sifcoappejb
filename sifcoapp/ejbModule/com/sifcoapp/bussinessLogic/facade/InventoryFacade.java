package com.sifcoapp.bussinessLogic.facade;

import java.util.List;
 



import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.bussinessLogic.InventoryEJB;
import com.sifcoapp.bussinessLogic.InventoryEJBLocal;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
import com.sifcoapp.objects.inventory.to.TransfersInTO;
import com.sifcoapp.objects.inventory.to.TransfersTO;
import com.sifcoapp.objects.transaction.to.InventoryLogTO;

/**
 * Session Bean implementation class InventoryFacade
 */
@Stateless 
public class InventoryFacade implements InventoryFacadeRemote {

	@EJB private InventoryEJBLocal process;
    public InventoryFacade() {
        // TODO Auto-generated constructor stub
    }
    public ResultOutTO inv_GoodsReceipt_mtto(GoodsreceiptTO parameters,
			int action) throws EJBException {
    	//return null;
    	return process.inv_GoodsReceipt_mtto(parameters, action);
    }
	public ResultOutTO inv_goodsissues_mtto(GoodsissuesTO parameters, int accion)
			throws EJBException {
		// TODO Auto-generated method stub
		return process.inv_goodsissues_mtto(parameters, accion);
	}
	public List getGoodsissues(GoodsissuesInTO param) throws EJBException {
		// TODO Auto-generated method stub
		return process.getGoodsissues(param);
	}
	public GoodsissuesTO getGoodsissuesByKey(int docentry) throws EJBException {
		// TODO Auto-generated method stub
		return process.getGoodsissuesByKey(docentry);
	}
	public List getGoodsIssuesDetail(int docentry) throws EJBException {
		// TODO Auto-generated method stub
		return process.getGoodsIssuesDetail(docentry);
	}
	public List getGoodsReceiptDetail(int docentry) throws EJBException {
		// TODO Auto-generated method stub
		return process.getGoodsReceiptDetail(docentry);
	}
	public List getGoodsreceipt(GoodsReceiptInTO param) throws EJBException {
		// TODO Auto-generated method stub
		return process.getGoodsreceipt(param);
	}
	public GoodsreceiptTO getGoodsReceiptByKey(int docentry)
			throws EJBException {
		// TODO Auto-generated method stub
		return process.getGoodsReceiptByKey(docentry);
	}
	public List getTransfers(TransfersInTO param) throws EJBException {
		// TODO Auto-generated method stub
		return process.getTransfers(param);
	}
	public TransfersTO getTransfersByKey(int docentry) throws EJBException {
		// TODO Auto-generated method stub
		return process.getTransfersByKey(docentry);
	}
	public ResultOutTO inv_transfers_mtto(TransfersTO parameters, int accion)
			throws EJBException {
		// TODO Auto-generated method stub
		return process.inv_transfers_mtto(parameters,accion);
	}
	public List getTransfersDetail(int docentry) throws EJBException {
		// TODO Auto-generated method stub
		return process.getTransfersDetail(docentry);
	}
	
}

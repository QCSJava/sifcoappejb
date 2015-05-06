package com.sifcoapp.client;

import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.bussinessLogic.InventoryEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.to.*;

public class InventoryEJBClient {

	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/InventoryEJB!com.sifcoapp.bussinessLogic.InventoryEJBRemote";
	private static InventoryEJBRemote bean;
	private static Context context = null;

	public InventoryEJBClient() {

		// 2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			bean = (InventoryEJBRemote) context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List getGoodsreceipt(GoodsReceiptInTO param) throws Exception {
		// TODO Auto-generated method stub
		List lstPeriods = new Vector();

		lstPeriods = bean.getGoodsreceipt(param);

		return lstPeriods;
	}

	public List getGoodsissues(GoodsissuesInTO param) throws Exception {
		// TODO Auto-generated method stub
		List lstPeriods = new Vector();

		lstPeriods = bean.getGoodsissues(param);

		return lstPeriods;
	}

	public ResultOutTO inv_goodsissues_mtto(GoodsissuesTO parameters, int accion) throws Exception {
		// TODO Auto-generated method stub
		ResultOutTO result;
		result = bean.inv_goodsissues_mtto(parameters, accion);
		return result;
	}

	public ResultOutTO inv_GoodsReceipt_mtto(GoodsreceiptTO param, int accion) throws Exception {
		// TODO Auto-generated method stub
		ResultOutTO _return;

		_return = bean.inv_GoodsReceipt_mtto(param, accion);

		return _return;
	}

	public List getGoodsIssuesDetail(int docentry)throws Exception {
		// TODO Auto-generated method stub
		List lstVector = new Vector();

		lstVector = bean.getGoodsIssuesDetail(docentry);

		return lstVector;
	}

	public List getGoodsReceiptDetail(int docentry) throws Exception {
		// TODO Auto-generated method stub
		List lstVector = new Vector();

		lstVector = bean.getGoodsReceiptDetail(docentry);

		return lstVector;
	}
	
	public GoodsissuesTO getGoodsissuesByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		GoodsissuesTO lstPeriods = new GoodsissuesTO();
		lstPeriods = bean.getGoodsissuesByKey(docentry);
		return lstPeriods;
	}
	public GoodsreceiptTO getGoodsReceiptByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		GoodsreceiptTO lstPeriods = new GoodsreceiptTO();
		lstPeriods = bean.getGoodsReceiptByKey(docentry);
		return lstPeriods;
	}

	public List getTransfers(TransfersInTO param) throws Exception {
		// TODO Auto-generated method stub
		List lstPeriods = new Vector();

		lstPeriods = bean.getTransfers(param);

		return lstPeriods;
	}

	public TransfersTO getTransfersByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		TransfersTO lstPeriods = new TransfersTO();
		lstPeriods = bean.getTransfersByKey(docentry);
		return lstPeriods;
	}

	public ResultOutTO inv_transfers_mtto(TransfersTO parameters, int accion) throws Exception {
		// TODO Auto-generated method stub
		ResultOutTO _return;
		_return = bean.inv_transfers_mtto(parameters, accion);
		return _return;
	}

	public List getTransfersDetail(int docentry) throws Exception {
		// TODO Auto-generated method stub
		List lstVector = new Vector();

		lstVector = bean.getTransfersDetail(docentry);

		return lstVector;
	}

	
	
	public InventoryLogTO getInventoryLogByKey(int messageid) throws EJBException{
		InventoryLogTO _return= new InventoryLogTO();
		_return=bean.getInventoryLogByKey(messageid);
		return _return;
	}
}

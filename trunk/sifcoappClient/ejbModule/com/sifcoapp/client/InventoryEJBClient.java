package com.sifcoapp.client;

import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.bussinessLogic.InventoryEJBRemote;
import com.sifcoapp.clientutility.ClientUtility;
import com.sifcoapp.objects.inventory.to.GoodsIssuesDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
import com.sifcoapp.objects.inventory.to.TransfersDetailTO;
import com.sifcoapp.objects.inventory.to.TransfersInTO;
import com.sifcoapp.objects.inventory.to.TransfersTO;

public class InventoryEJBClient implements InventoryEJBRemote {

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

	public List getGoodsreceipt(GoodsReceiptInTO param) {
		// TODO Auto-generated method stub
		List lstPeriods = new Vector();

		lstPeriods = bean.getGoodsreceipt(param);

		return lstPeriods;
	}

	public List getGoodsissues(GoodsissuesInTO param) {
		// TODO Auto-generated method stub
		List lstPeriods = new Vector();

		lstPeriods = bean.getGoodsissues(param);

		return lstPeriods;
	}

	public int inv_goodsissues_mtto(GoodsissuesTO parameters, int accion) {
		// TODO Auto-generated method stub
		int lstVector;
		lstVector = bean.inv_goodsissues_mtto(parameters, accion);
		return lstVector;
	}

	public int inv_GoodsReceipt_mtto(GoodsreceiptTO param, int accion) {
		// TODO Auto-generated method stub
		int _return;

		_return = bean.inv_GoodsReceipt_mtto(param, accion);

		return _return;
	}

	public int inv_goodsIssuesDetail_mtto(GoodsIssuesDetailTO parameters,
			int accion) {
		// TODO Auto-generated method stub
		int _return = 0;

		_return = bean.inv_goodsIssuesDetail_mtto(parameters, accion);

		return _return;
	}

	public int inv_goodsReceiptDetail_mtto(GoodsReceiptDetailTO parameters,
			int accion) {
		// TODO Auto-generated method stub
		int _return = 0;

		_return = bean.inv_goodsReceiptDetail_mtto(parameters, accion);

		return _return;
	}

	public List getGoodsIssuesDetail(int docentry) {
		// TODO Auto-generated method stub
		List lstVector = new Vector();

		lstVector = bean.getGoodsIssuesDetail(docentry);

		return lstVector;
	}

	public List getGoodsReceiptDetail(int docentry) {
		// TODO Auto-generated method stub
		List lstVector = new Vector();

		lstVector = bean.getGoodsReceiptDetail(docentry);

		return lstVector;
	}
	
	public GoodsissuesTO getGoodsissuesByKey(int docentry) {
		// TODO Auto-generated method stub
		GoodsissuesTO lstPeriods = new GoodsissuesTO();
		lstPeriods = bean.getGoodsissuesByKey(docentry);
		return lstPeriods;
	}
	public GoodsreceiptTO getGoodsReceiptByKey(int docentry) {
		// TODO Auto-generated method stub
		GoodsreceiptTO lstPeriods = new GoodsreceiptTO();
		lstPeriods = bean.getGoodsReceiptByKey(docentry);
		return lstPeriods;
	}

	public List getTransfers(TransfersInTO param) {
		// TODO Auto-generated method stub
		List lstPeriods = new Vector();

		lstPeriods = bean.getTransfers(param);

		return lstPeriods;
	}

	public TransfersTO getTransfersByKey(int docentry) {
		// TODO Auto-generated method stub
		TransfersTO lstPeriods = new TransfersTO();
		lstPeriods = bean.getTransfersByKey(docentry);
		return lstPeriods;
	}

	public int inv_transfers_mtto(TransfersTO parameters, int accion) {
		// TODO Auto-generated method stub
		int lstVector;
		lstVector = bean.inv_transfers_mtto(parameters, accion);
		return lstVector;
	}

	public List getTransfersDetail(int docentry) {
		// TODO Auto-generated method stub
		List lstVector = new Vector();

		lstVector = bean.getTransfersDetail(docentry);

		return lstVector;
	}

	public int inv_transfersDetail_mtto(TransfersDetailTO parameters, int action) {
		// TODO Auto-generated method stub
		int _return = 0;

		_return = bean.inv_transfersDetail_mtto(parameters, action);

		return _return;
	}

}

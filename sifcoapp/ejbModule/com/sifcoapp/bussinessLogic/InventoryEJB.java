package com.sifcoapp.bussinessLogic;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.*;
import com.sifcoapp.objects.inventory.to.*;

/**
 * Session Bean implementation class InventoryEJB
 */
@Stateless
public class InventoryEJB implements InventoryEJBRemote {

	/**
	 * Default constructor.
	 */
	public InventoryEJB() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * public List getListGoodsreceipt(int docnum, Date docdate, int series) {
	 * // TODO Auto-generated method stub GoodsreceiptDAO List _return = new
	 * Vector(); GoodsreceiptDAO DAO = new GoodsreceiptDAO(); _return =
	 * DAO.getListGoodsreceipt(docnum, docdate, series);
	 * 
	 * return _return; }
	 */

	public ResultOutTO inv_goodsissues_mtto(GoodsissuesTO parameters, int action) throws EJBException {
		// TODO Auto-generated method stub
		ResultOutTO _return= new ResultOutTO();
		Double total=0.0;
		GoodsIssuesDAO DAO = new GoodsIssuesDAO();
		DAO.setIstransaccional(true);
		GoodsissuesDetailDAO goodDAO1 = new GoodsissuesDetailDAO(DAO.getConn());
		try {
		Iterator<GoodsIssuesDetailTO> iterator2 = parameters.getGoodIssuesDetail().iterator();
		while (iterator2.hasNext()) {
			GoodsIssuesDetailTO articleDetalle = (GoodsIssuesDetailTO) iterator2.next();
			articleDetalle.setLinetotal(articleDetalle.getQuantity()*articleDetalle.getPrice());
			articleDetalle.setOpenqty(articleDetalle.getQuantity());
			total=total+articleDetalle.getLinetotal();
		}
		parameters.setDoctotal(total);
		_return.setDocentry( DAO.inv_goodsissues_mtto(parameters, action));
		Iterator<GoodsIssuesDetailTO> iterator = parameters.getGoodIssuesDetail().iterator();
		while (iterator.hasNext()) {
			GoodsIssuesDetailTO articleDetalle = (GoodsIssuesDetailTO) iterator.next();
			// Para articulos nuevos
			System.out.println(""+_return+"");
			articleDetalle.setDocentry(_return.getDocentry());
			if (action == Common.MTTOINSERT) {
				goodDAO1.inv_goodsIssuesDetail_mtto(articleDetalle,Common.MTTOINSERT);
			}
			if (action == Common.MTTODELETE) {
				goodDAO1.inv_goodsIssuesDetail_mtto(articleDetalle,Common.MTTODELETE);
			}
		}
		DAO.forceCommit();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		}finally{
			
			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
		
	}

	public ResultOutTO inv_GoodsReceipt_mtto(GoodsreceiptTO parameters, int action)throws EJBException {
		// TODO Auto-generated method stub
		ResultOutTO _return= new ResultOutTO();
		Double total=0.00;
		GoodsReceiptDAO DAO = new GoodsReceiptDAO();
		DAO.setIstransaccional(true);
		GoodReceiptDetailDAO goodDAO1 = new GoodReceiptDetailDAO(DAO.getConn());
		try {
		@SuppressWarnings("unchecked")
		Iterator<GoodsReceiptDetailTO> iterator2 = parameters.getGoodReceiptDetail().iterator();
		while (iterator2.hasNext()) {
			GoodsReceiptDetailTO articleDetalle = (GoodsReceiptDetailTO) iterator2.next();
			articleDetalle.setLinetotal(articleDetalle.getQuantity()*articleDetalle.getPrice());
			articleDetalle.setOpenqty(articleDetalle.getQuantity());
			total=total+articleDetalle.getLinetotal();
		}
		parameters.setDoctotal(total);
		_return.setDocentry(DAO.inv_GoodsReceipt_mtto(parameters, action));
	
		Iterator<GoodsReceiptDetailTO> iterator = parameters.getGoodReceiptDetail().iterator();
		while (iterator.hasNext()) {
			GoodsReceiptDetailTO detalleReceipt = (GoodsReceiptDetailTO) iterator.next();
			// Para articulos nuevos
			detalleReceipt.setDocentry(_return.getDocentry());
			if (action == Common.MTTOINSERT) {
				goodDAO1.inv_goodReceiptDetail_mtto(detalleReceipt,Common.MTTOINSERT);
			}
			if (action == Common.MTTODELETE) {
				goodDAO1.inv_goodReceiptDetail_mtto(detalleReceipt,Common.MTTODELETE);
			}
		}
		DAO.forceCommit();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		}finally{
			
			DAO.forceCloseConnection();
		}
		
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public List getGoodsissues(GoodsissuesInTO param)throws EJBException {
		// TODO Auto-generated method stub GoodsissuesDAO
		List _return = new Vector();
		GoodsIssuesDAO DAO = new GoodsIssuesDAO();
		try {
			_return = DAO.getGoodsissues(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public List getGoodsIssuesDetail(int docentry)throws EJBException {
		// TODO Auto-generated method stub GoodsissuesDAO
		List _return = new Vector();
		GoodsissuesDetailDAO DAO = new GoodsissuesDetailDAO();
		try {
			_return = DAO.getGoodsIssuesDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public List getGoodsReceiptDetail(int docentry)throws EJBException {
		// TODO Auto-generated method stub GoodsissuesDAO
		List _return = new Vector();
		GoodReceiptDetailDAO DAO = new GoodReceiptDetailDAO();
		try {
			_return = DAO.getGoodReceiptDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public List getGoodsreceipt(GoodsReceiptInTO param)throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		GoodsReceiptDAO DAO = new GoodsReceiptDAO();
		try {
			_return = DAO.getGoodsreceipt(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public GoodsissuesTO getGoodsissuesByKey(int docentry)throws EJBException {
		// TODO Auto-generated method stub
		GoodsissuesTO _return = null;
		GoodsIssuesDAO GoodDAO=new GoodsIssuesDAO();
		try {
			_return=GoodDAO.getGoodsissuesByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}
	public GoodsreceiptTO getGoodsReceiptByKey(int docentry)throws EJBException {
		// TODO Auto-generated method stub
		GoodsreceiptTO _return = null;
		GoodsReceiptDAO GoodDAO=new GoodsReceiptDAO();
		try {
			_return=GoodDAO.getGoodsReceiptByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	
	public List getTransfers(TransfersInTO param)throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		TransfersDAO DAO = new TransfersDAO();
		try {
			_return = DAO.getTransfers(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public TransfersTO getTransfersByKey(int docentry)throws EJBException {
		// TODO Auto-generated method stub
		TransfersTO _return = null;
		TransfersDAO TraDAO= new TransfersDAO();
		try {
			_return=TraDAO.getTransfersByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public ResultOutTO inv_transfers_mtto(TransfersTO parameters, int action)throws EJBException {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();
		TransfersDAO Trans= new TransfersDAO();
		Trans.setIstransaccional(true);
		TransfersDetailDAO TransDAO = new TransfersDetailDAO(Trans.getConn());
		try {
			_return.setDocentry(Trans.inv_transfers_mtto(parameters, action));
		
		Iterator<TransfersDetailTO> iterator= parameters.getTransfersDetail().iterator();
		
		while(iterator.hasNext()){
			TransfersDetailTO articleDetalle = (TransfersDetailTO) iterator.next();
			// Para articulos nuevos
			
			articleDetalle.setDocentry(_return.getDocentry());
			if (action == Common.MTTOINSERT) {
				TransDAO.inv_transfersDetail_mtto(articleDetalle,Common.MTTOINSERT);
			}
			if (action == Common.MTTODELETE) {
				TransDAO.inv_transfersDetail_mtto(articleDetalle,Common.MTTODELETE);
			}
		}
		Trans.forceCommit();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Trans.rollBackConnection();
			throw (EJBException) new EJBException(e);
		}finally{
			
			Trans.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados con exito");
		return _return;
	}

	public List getTransfersDetail(int docentry)throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		TransfersDetailDAO DAO = new TransfersDetailDAO();
		try {
			_return=DAO.getTransfersDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}


}

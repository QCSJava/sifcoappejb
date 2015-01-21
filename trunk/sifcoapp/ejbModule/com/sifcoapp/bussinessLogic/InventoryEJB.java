package com.sifcoapp.bussinessLogic;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;

import com.sifcoapp.objects.inventory.dao.GoodReceiptDetailDAO;
import com.sifcoapp.objects.inventory.dao.GoodsIssuesDAO;
import com.sifcoapp.objects.inventory.dao.GoodsissuesDetailDAO;
import com.sifcoapp.objects.inventory.dao.GoodsreceiptDAO;
import com.sifcoapp.objects.inventory.to.GoodsIssuesDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;

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

	public boolean inv_goodsissues_mtto(GoodsissuesTO parameters, int accion) {
		// TODO Auto-generated method stub

		return true;
	}

	public int inv_goodsIssuesDetail_mtto(GoodsIssuesDetailTO parameters,
			int accion) {
		// TODO Auto-generated method stub
		int _return = 0;
		GoodsissuesDetailDAO DAO = new GoodsissuesDetailDAO();
		_return = DAO.inv_goodsIssuesDetail_mtto(parameters, accion);

		return _return;

	}

	public int inv_goodsReceiptDetail_mtto(GoodsReceiptDetailTO parameters,
			int accion) {
		// TODO Auto-generated method stub
		int _return = 0;
		GoodReceiptDetailDAO DAO = new GoodReceiptDetailDAO();
		_return = DAO.inv_goodReceiptDetail_mtto(parameters, accion);

		return _return;

	}

	public int Goodsreceipt_mtto(GoodsreceiptTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List getGoodsissues(GoodsissuesInTO param) {
		// TODO Auto-generated method stub GoodsissuesDAO
		List _return = new Vector();
		GoodsIssuesDAO DAO = new GoodsIssuesDAO();
		_return = DAO.getGoodsissues(param);

		return _return;
	}

	public List getGoodsIssuesDetail(int docentry) {
		// TODO Auto-generated method stub GoodsissuesDAO
		List _return = new Vector();
		GoodsissuesDetailDAO DAO = new GoodsissuesDetailDAO();
		_return = DAO.getGoodsIssuesDetail(docentry);

		return _return;
	}

	public List getGoodsReceiptDetail(int docentry) {
		// TODO Auto-generated method stub GoodsissuesDAO
		List _return = new Vector();
		GoodReceiptDetailDAO DAO = new GoodReceiptDetailDAO();
		_return = DAO.getGoodReceiptDetail(docentry);

		return _return;
	}

	public List getGoodsreceipt(GoodsReceiptInTO param) {
		// TODO Auto-generated method stub
		List _return = new Vector();
		GoodsreceiptDAO DAO = new GoodsreceiptDAO();
		_return = DAO.getGoodsreceipt(param);

		return _return;
	}

}

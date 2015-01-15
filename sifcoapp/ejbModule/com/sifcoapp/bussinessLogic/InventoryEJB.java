package com.sifcoapp.bussinessLogic;

import java.sql.Date;
import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;

import com.sifcoapp.objects.accounting.dao.AccountingDAO;
import com.sifcoapp.objects.inventary.dao.GoodsissuesDAO;
import com.sifcoapp.objects.inventary.dao.GoodsreceiptDAO;

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

	public List getListGoodsreceipt(int docnum, Date docdate, int series) {
		// TODO Auto-generated method stub GoodsreceiptDAO
		List _return = new Vector();
		GoodsreceiptDAO DAO = new GoodsreceiptDAO();
		_return = DAO.getListGoodsreceipt(docnum, docdate, series);

		return _return;
	}

	public List getGoodsissues(int docnum, Date docdate, int series) {
		// TODO Auto-generated method stub GoodsissuesDAO
		List _return = new Vector();
		GoodsissuesDAO DAO = new GoodsissuesDAO();
		_return = DAO.getGoodsissues(docnum, docdate, series);

		return _return;
	}

}

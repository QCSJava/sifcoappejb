package com.sifcoapp.objects.inventory.dao;
import com.sifcoapp.objects.inventory.to.*;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class GoodsissuesDetailDAO extends CommonDAO{
	
	
	public GoodsissuesDetailDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoodsissuesDetailDAO(Connection _conn) {
		super(_conn);
		// TODO Auto-generated constructor stub
	}

	//Retorna de goodIssuesDetail
	public List getGoodsIssuesDetail(int docentry) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_goodsissuesdetail(?)}");		
		this.setInt(1, "_docentry", new Integer(docentry));		
		
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					GoodsIssuesDetailTO document = new GoodsIssuesDetailTO();
					document.setDocentry(rowsetActual.getInt(1));
					document.setLinenum(rowsetActual.getInt(2));
					document.setTargettype(rowsetActual.getInt(3));
					document.setTrgetentry(rowsetActual.getInt(4));
					document.setBaseref(rowsetActual.getString(5));
					document.setBasetype(rowsetActual.getInt(6));
					document.setLinestatus(rowsetActual.getString(7));
					document.setItemcode(rowsetActual.getString(8));
					document.setDscription(rowsetActual.getString(9));
					document.setQuantity(rowsetActual.getDouble(10));
					document.setOpenqty(rowsetActual.getDouble(11));
					document.setPrice(rowsetActual.getDouble(12));
					document.setLinetotal(rowsetActual.getDouble(13));
					document.setWhscode(rowsetActual.getString(14));
					document.setAcctcode(rowsetActual.getString(15));
					document.setUsebaseun(rowsetActual.getString(16));
					document.setObjtype(rowsetActual.getString(17));
					document.setUnitmsr(rowsetActual.getString(18));

					_return.add(document);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	public int inv_goodsIssuesDetail_mtto(GoodsIssuesDetailTO parameters, int action)throws Exception {
		
		int v_resp;
		
		// s.setDbObject("{call sp_gis1_goodsissuedetail_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_gis1_goodsissuedetail_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(1,"_docentry,", new Integer(parameters.getDocentry()));
		this.setInt(2,"_linenum,", new Integer(parameters.getLinenum()));
		this.setInt(3,"_targettype,", new Integer(parameters.getTargettype()));
		this.setInt(4,"_trgetentry,", new Integer(parameters.getTrgetentry()));
		this.setString(5,"_baseref,", parameters.getBaseref());
		this.setInt(6,"_basetype,",new Integer( parameters.getBasetype()));
		this.setString(7,"_linestatus,", parameters.getLinestatus());
		this.setString(8,"_itemcode,", parameters.getItemcode());
		this.setString(9,"_dscription,", parameters.getDscription());
		this.setDouble(10,"_quantity,", new Double(parameters.getQuantity()));
		this.setDouble(11,"_openqty,", new Double(parameters.getOpenqty()));
		this.setDouble(12,"_price,", new Double(parameters.getPrice()));
		this.setDouble(13,"_linetotal,", new Double(parameters.getLinetotal()));
		this.setString(14,"_whscode,", parameters.getWhscode());
		this.setString(15,"_acctcode,", parameters.getAcctcode());
		this.setString(16,"_usebaseun,", parameters.getUsebaseun());
		this.setString(17,"_objtype,", parameters.getObjtype());
		this.setString(18,"_unitmsr,", parameters.getUnitmsr());
		this.setInt(19, "_action", new Integer(action));

		v_resp = this.runUpdate();

		return v_resp;
		
	}
}

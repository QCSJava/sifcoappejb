package com.sifcoapp.objects.sales.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.sales.to.SalesDetailTO;
import com.sun.rowset.CachedRowSetImpl;

public class SalesDetailDAO extends CommonDAO{
	//Retorna de goodIssuesDetail
		public List getSalesDetail(int docentry) throws Exception {
			List _return = new Vector();
			List lstResultSet = null;
			this.setTypeReturn(Common.TYPERETURN_CURSOR);
			this.setDbObject("{call sp_get_salesdetail(?)}");		
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
						SalesDetailTO sales = new SalesDetailTO();
						sales.setDocentry(rowsetActual.getInt(1));
						sales.setLinenum(rowsetActual.getInt(2));
						sales.setItemcode(rowsetActual.getString(3));
						sales.setDscription(rowsetActual.getString(4));
						sales.setQuantity(rowsetActual.getDouble(5));
						sales.setPrice(rowsetActual.getDouble(6));
						sales.setLinetotal(rowsetActual.getDouble(7));
						sales.setWhscode(rowsetActual.getString(8));
						sales.setAcctcode(rowsetActual.getString(9));
						sales.setOcrcode(rowsetActual.getString(10));
						sales.setTaxstatus(rowsetActual.getString(11));
						sales.setTaxcode(rowsetActual.getString(12));
						_return.add(sales);
					}
					rowsetActual.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return _return;
		}

		public int inv_SalesDetail_mtto(SalesDetailTO parameters, int action) throws Exception{
			
			int v_resp = 0;
			
			// s.setDbObject("{call sp_gis1_goodsissued(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
			this.setDbObject("{call sp_salesdetail_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			this.setInt(1,"_docentry", new Integer(parameters.getDocentry()));
			this.setInt(2,"_linenum", new Integer(parameters.getLinenum()));
			this.setString(3,"_itemcode", parameters.getItemcode());
			this.setString(4,"_dscription", parameters.getDscription());
			this.setDouble(5,"_quantity", new Double(parameters.getQuantity()));
			this.setDouble(6,"_price", new Double(parameters.getPrice()));
			this.setDouble(7,"_linetotal", new Double(parameters.getLinetotal()));
			this.setString(8,"_whscode", parameters.getWhscode());
			this.setString(9,"_acctcode", parameters.getAcctcode());
			this.setString(10,"_ocrcode", parameters.getOcrcode());
			this.setString(11,"_taxstatus", parameters.getTaxstatus());
			this.setString(12,"_taxcode", parameters.getTaxcode());
			this.setInt(13, "_action", new Integer(action));

			v_resp = this.runUpdate();

			return v_resp;
			
		}
}

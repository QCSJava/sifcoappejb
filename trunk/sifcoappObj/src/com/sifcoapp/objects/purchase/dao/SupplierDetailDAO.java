package com.sifcoapp.objects.purchase.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.purchase.to.*;
import com.sun.rowset.CachedRowSetImpl;

public class SupplierDetailDAO extends CommonDAO{
	
	public SupplierDetailDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupplierDetailDAO(Connection _conn) {
		super(_conn);
		// TODO Auto-generated constructor stub
	}

		//Retorna de goodIssuesDetail
		public List getsupplierDetail(int docentry) throws Exception {
			List _return = new Vector();
			List lstResultSet = null;
			this.setTypeReturn(Common.TYPERETURN_CURSOR);
			this.setDbObject("{call sp_get_screditnotedetail(?)}");		
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
						SupplierDetailTO Supplier = new SupplierDetailTO();
						Supplier.setDocentry(rowsetActual.getInt(1));
						Supplier.setLinenum(rowsetActual.getInt(2));
						Supplier.setTargettype(rowsetActual.getInt(3));
						Supplier.setBaseref(rowsetActual.getString(4));
						Supplier.setBasetype(rowsetActual.getInt(5));
						Supplier.setBaseentry(rowsetActual.getInt(6));
						Supplier.setBaseline(rowsetActual.getInt(7));
						Supplier.setLinestatus(rowsetActual.getString(8));
						Supplier.setItemcode(rowsetActual.getString(9));
						Supplier.setDscription(rowsetActual.getString(10));
						Supplier.setQuantity(rowsetActual.getDouble(11));
						Supplier.setOpenqty(rowsetActual.getDouble(12));
						Supplier.setPrice(rowsetActual.getDouble(13));
						Supplier.setDiscprcnt(rowsetActual.getDouble(14));
						Supplier.setLinetotal(rowsetActual.getDouble(15));
						Supplier.setWhscode(rowsetActual.getString(16));
						Supplier.setAcctcode(rowsetActual.getString(17));
						Supplier.setTaxstatus(rowsetActual.getString(18));
						Supplier.setPricebefdi(rowsetActual.getDouble(19));
						Supplier.setOcrcode(rowsetActual.getString(20));
						Supplier.setVatgroup(rowsetActual.getString(21));
						Supplier.setPriceafvat(rowsetActual.getDouble(22));
						Supplier.setFactor1(rowsetActual.getDouble(23));
						Supplier.setVatsum(rowsetActual.getDouble(24));
						Supplier.setObjtype(rowsetActual.getString(25));
						Supplier.setGrssprofit(rowsetActual.getDouble(26));
						Supplier.setTaxcode(rowsetActual.getString(27));
						Supplier.setVatappld(rowsetActual.getDouble(28));
						Supplier.setUnitmsr(rowsetActual.getString(29));
						Supplier.setStockpricestockprice(rowsetActual.getDouble(30));
						Supplier.setGtotal(rowsetActual.getDouble(31));
						_return.add(Supplier);
					}
					rowsetActual.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return _return;
		}

		public int inv_SupplierDetail_mtto(SupplierDetailTO parameters, int action) throws Exception{
			
			int v_resp = 0;
			
			// s.setDbObject("{call sp_gis1_goodsissued(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
			this.setDbObject("{call sp_screditnotedetail_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			this.setInt(1,"_docentry,", new Integer(parameters.getDocentry()));
			this.setInt(2,"_linenum,", new Integer(parameters.getLinenum()));
			this.setInt(3,"_targettype,", new Integer(parameters.getTargettype()));
			this.setString(4,"_baseref,", parameters.getBaseref());
			this.setInt(5,"_basetype,", new Integer(parameters.getBasetype()));
			this.setInt(6,"_baseentry,",new Integer(parameters.getBaseentry()));
			this.setInt(7,"_baseline,", new Integer(parameters.getBaseline()));
			this.setString(8,"_linestatus,", parameters.getLinestatus());
			this.setString(9,"_itemcode,", parameters.getItemcode());
			this.setString(10,"_dscription,", parameters.getDscription());
			this.setDouble(11,"_quantity,", new Double(parameters.getQuantity()));
			this.setDouble(12,"_openqty,", new Double(parameters.getOpenqty()));
			this.setDouble(13,"_price,", new Double(parameters.getPrice()));
			this.setDouble(14,"_discprcnt,", new Double(parameters.getDiscprcnt()));
			this.setDouble(15,"_linetotal,", new Double(parameters.getLinetotal()));
			this.setString(16,"_whscode,", parameters.getWhscode());
			this.setString(17,"_acctcode,", parameters.getAcctcode());
			this.setString(18,"_taxstatus,", parameters.getTaxstatus());
			this.setDouble(19,"_pricebefdi,", new Double(parameters.getPricebefdi()));
			this.setString(20,"_ocrcode,", parameters.getOcrcode());
			this.setString(21,"_vatgroup,", parameters.getVatgroup());
			this.setDouble(22,"_priceafvat,", new Double(parameters.getPriceafvat()));
			this.setDouble(23,"_factor1,", new Double(parameters.getFactor1()));
			this.setDouble(24,"_vatsum,", new Double(parameters.getVatsum()));
			this.setString(25,"_objtype,", parameters.getObjtype());
			this.setDouble(26,"_grssprofit,", new Double(parameters.getGrssprofit()));
			this.setString(27,"_taxcode,", parameters.getTaxcode());
			this.setDouble(28,"_vatappld,", new Double(parameters.getVatappld()));
			this.setString(29,"_unitmsr,", parameters.getUnitmsr());
			this.setDouble(30,"_stockpricestockprice,", new Double(parameters.getStockpricestockprice()));
			this.setDouble(31,"_gtotal,", new Double(parameters.getGtotal()));
			this.setInt(32, "_action", new Integer(action));

			v_resp = this.runUpdate();

			return v_resp;
			
		}
}

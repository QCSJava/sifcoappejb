package com.sifcoapp.objects.sales.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.sales.to.ClientCrediDetailTO;
import com.sifcoapp.objects.sales.to.SalesDetailTO;
import com.sun.rowset.CachedRowSetImpl;

public class SalesDetailDAO extends CommonDAO{
	
	public SalesDetailDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalesDetailDAO(Connection _conn) {
		super(_conn);
		// TODO Auto-generated constructor stub
	}

		//Retorna de goodIssuesDetail
		public List getSalesDetail(int docentry) throws Exception {
			List _return = new Vector();
			List lstResultSet = null;
			this.setTypeReturn(Common.TYPERETURN_CURSOR);
			this.setDbObject("{call sp_get_salesdetail(?)}");		
			this.setInt(1, "_docentry", new Integer(docentry));		
			
			lstResultSet = this.runQuery();
			CachedRowSetImpl rowsetActual;
			
			ListIterator liRowset = null;
			liRowset = lstResultSet.listIterator();

			while (liRowset.hasNext()) {
				rowsetActual = (CachedRowSetImpl) liRowset.next();
				try {
					while (rowsetActual.next()) {
						SalesDetailTO sales = new SalesDetailTO();
						sales.setDocentry(rowsetActual.getInt(1));
						sales.setLinenum(rowsetActual.getInt(2));
						sales.setTargettype(rowsetActual.getInt(3));
						sales.setBaseref(rowsetActual.getString(4));
						sales.setBasetype(rowsetActual.getInt(5));
						sales.setBaseentry(rowsetActual.getInt(6));
						sales.setBaseline(rowsetActual.getInt(7));
						sales.setLinestatus(rowsetActual.getString(8));
						sales.setItemcode(rowsetActual.getString(9));
						sales.setDscription(rowsetActual.getString(10));
						sales.setQuantity(rowsetActual.getDouble(11));
						sales.setOpenqty(rowsetActual.getDouble(12));
						sales.setPrice(rowsetActual.getDouble(13));
						sales.setDiscprcnt(rowsetActual.getDouble(14));
						sales.setLinetotal(rowsetActual.getDouble(15));
						sales.setWhscode(rowsetActual.getString(16));
						sales.setAcctcode(rowsetActual.getString(17));
						sales.setTaxstatus(rowsetActual.getString(18));
						sales.setPricebefdi(rowsetActual.getDouble(19));
						sales.setOcrcode(rowsetActual.getString(20));
						sales.setVatgroup(rowsetActual.getString(21));
						sales.setPriceafvat(rowsetActual.getDouble(22));
						sales.setFactor1(rowsetActual.getDouble(23));
						sales.setVatsum(rowsetActual.getDouble(24));
						sales.setObjtype(rowsetActual.getString(25));
						sales.setGrssprofit(rowsetActual.getDouble(26));
						sales.setTaxcode(rowsetActual.getString(27));
						sales.setVatappld(rowsetActual.getDouble(28));
						sales.setUnitmsr(rowsetActual.getString(29));
						sales.setStockpricestockprice(rowsetActual.getDouble(30));
						sales.setGtotal(rowsetActual.getDouble(31));
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

		public int inv_SalesDetail_mtto(SalesDetailTO articleDetalle, int action) throws Exception{
			
			int v_resp = 0;
			
			// s.setDbObject("{call sp_gis1_goodsissued(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
			this.setDbObject("{call sp_salesdetail_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			this.setInt(1,"_docentry,", new Integer(articleDetalle.getDocentry()));
			this.setInt(2,"_linenum,", new Integer(articleDetalle.getLinenum()));
			this.setInt(3,"_targettype,", new Integer(articleDetalle.getTargettype()));
			this.setString(4,"_baseref,", articleDetalle.getBaseref());
			this.setInt(5,"_basetype,", new Integer(articleDetalle.getBasetype()));
			this.setInt(6,"_baseentry,",new Integer(articleDetalle.getBaseentry()));
			this.setInt(7,"_baseline,", new Integer(articleDetalle.getBaseline()));
			this.setString(8,"_linestatus,", articleDetalle.getLinestatus());
			this.setString(9,"_itemcode,", articleDetalle.getItemcode());
			this.setString(10,"_dscription,", articleDetalle.getDscription());
			this.setDouble(11,"_quantity,", new Double(articleDetalle.getQuantity()));
			this.setDouble(12,"_openqty,", new Double(articleDetalle.getOpenqty()));
			this.setDouble(13,"_price,", new Double(articleDetalle.getPrice()));
			this.setDouble(14,"_discprcnt,", new Double(articleDetalle.getDiscprcnt()));
			this.setDouble(15,"_linetotal,", new Double(articleDetalle.getLinetotal()));
			this.setString(16,"_whscode,", articleDetalle.getWhscode());
			this.setString(17,"_acctcode,", articleDetalle.getAcctcode());
			this.setString(18,"_taxstatus,", articleDetalle.getTaxstatus());
			this.setDouble(19,"_pricebefdi,", new Double(articleDetalle.getPricebefdi()));
			this.setString(20,"_ocrcode,", articleDetalle.getOcrcode());
			this.setString(21,"_vatgroup,", articleDetalle.getVatgroup());
			this.setDouble(22,"_priceafvat,", new Double(articleDetalle.getPriceafvat()));
			this.setDouble(23,"_factor1,", new Double(articleDetalle.getFactor1()));
			this.setDouble(24,"_vatsum,", new Double(articleDetalle.getVatsum()));
			this.setString(25,"_objtype,", articleDetalle.getObjtype());
			this.setDouble(26,"_grssprofit,", new Double(articleDetalle.getGrssprofit()));
			this.setString(27,"_taxcode,", articleDetalle.getTaxcode());
			this.setDouble(28,"_vatappld,", new Double(articleDetalle.getVatappld()));
			this.setString(29,"_unitmsr,", articleDetalle.getUnitmsr());
			this.setDouble(30,"_stockpricestockprice,", new Double(articleDetalle.getStockpricestockprice()));
			this.setDouble(31,"_gtotal,", new Double(articleDetalle.getGtotal()));
			this.setInt(32, "_action", new Integer(action));

			v_resp = this.runUpdate();

			return v_resp;
			
		}
}

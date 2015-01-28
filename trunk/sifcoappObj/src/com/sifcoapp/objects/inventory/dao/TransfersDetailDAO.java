package com.sifcoapp.objects.inventory.dao;
import com.sifcoapp.objects.inventory.to.*;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class TransfersDetailDAO extends CommonDAO{
	
	//RETORNA DE LA TABLA TRANSFERSDETAIL (SE USA JUNTOS A EL GET DE LA TABLA PADRE: TRANSFERS) RETORNA TODAS SUS HIJAS
	public List getTransfersDetail(int docentry) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_transfersdetail(?)}");		
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
					TransfersDetailTO transfers = new TransfersDetailTO();
					transfers.setDocentry(rowsetActual.getInt(1));
					transfers.setLinenum(rowsetActual.getInt(2));
					transfers.setTargettype(rowsetActual.getInt(3));
					transfers.setTrgetentry(rowsetActual.getInt(4));
					transfers.setBaseref(rowsetActual.getString(5));
					transfers.setBasetype(rowsetActual.getInt(6));
					transfers.setLinestatus(rowsetActual.getString(7));
					transfers.setItemcode(rowsetActual.getString(8));
					transfers.setDscription(rowsetActual.getString(9));
					transfers.setQuantity(rowsetActual.getDouble(10));
					transfers.setOpenqty(rowsetActual.getDouble(11));
					transfers.setPrice(rowsetActual.getDouble(12));
					transfers.setLinetotal(rowsetActual.getDouble(13));
					transfers.setWhscode(rowsetActual.getString(14));
					transfers.setAcctcode(rowsetActual.getString(15));
					transfers.setUsebaseun(rowsetActual.getString(16));
					transfers.setObjtype(rowsetActual.getString(17));
					transfers.setUnitmsr(rowsetActual.getString(18));
					transfers.setFromwhscode(rowsetActual.getString(19));
					_return.add(transfers);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	//########################CRUD DE LA TABLA TRANSFERSDETAILS (SE USA JUNTO CON EL CRUD DE LA TABLA PADRE: TRANSFERS) GUARDA TODOS LOS DETALLES DE SU PADRE
	public int inv_transfersDetail_mtto(TransfersDetailTO parameters, int action)throws Exception {

		int v_resp = 0;

		// s.setDbObject("{call sp_gis1_goodsissuedetail_mttodddd(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_inv_tra1_transfersdetails_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
		this.setString(19, "_fromwhscode", parameters.getFromwhscode());
		this.setInt(20, "_action", new Integer(action));

		v_resp = this.runUpdate();

		return v_resp;

	}
}

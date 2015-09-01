package com.sifcoapp.objects.bank.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.bank.to.CheckForPaymentInTO;
import com.sifcoapp.objects.bank.to.CheckForPaymentTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sun.rowset.CachedRowSetImpl;

public class CheckForPaymentDAO extends CommonDAO {

	public CheckForPaymentDAO() {
		// TODO Auto-generated constructor stub
	}

	public int ges_cfp0_checkforpayment_mtto(CheckForPaymentTO parameters,
			int action) throws Exception {
		List v_resp;
		// s.setDbObject("{call sp_ges_cfp0_checkforpayment_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_ges_cfp0_checkforpayment_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(1, "_checkkey", new Integer(parameters.getCheckkey()));
		this.setInt(2, "_checknum,", new Integer(parameters.getChecknum()));
		this.setString(3, "_banknum,", parameters.getBanknum());
		this.setString(4, "_bankname,", parameters.getBankname());
		// this.setDate(5, "_checkdate,", parameters.getCheckdate());
		if (parameters.getCheckdate() == null) {
			this.setDate(5, "_checkdate", parameters.getCheckdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getCheckdate().getTime());
			this.setDate(5, "_checkdate", fecha);
		}
		this.setString(6, "_dpstacct,", parameters.getDpstacct());
		this.setString(7, "_acctnum,", parameters.getAcctnum());
		this.setString(8, "_details,", parameters.getDetails());
		this.setString(9, "_transref,", parameters.getTransref());
		
		if (parameters.getPmntdate() == null) {
			this.setDate(10, "_pmntdate", parameters.getPmntdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getPmntdate().getTime());
			this.setDate(10, "_pmntdate", fecha);
		}
		this.setInt(11, "_pmntnum,", new Integer(parameters.getPmntnum()));
		this.setDouble(12, "_checksum,", new Double(parameters.getChecksum()));
		this.setString(13, "_trnsfrable,", parameters.getTrnsfrable());
		this.setString(14, "_vendorcode,", parameters.getVendorcode());
		this.setString(15, "_canceled,", parameters.getCanceled());
		this.setString(16, "_cardoracct,", parameters.getCardoracct());
		this.setString(17, "_printed,", parameters.getPrinted());
		this.setString(18, "_vendorname,", parameters.getVendorname());
		this.setString(19, "_totalwords,", parameters.getTotalwords());
		this.setString(20, "_signature,", parameters.getSignature());
		this.setString(21, "_checkacct,", parameters.getCheckacct());
		this.setInt(22, "_transnum,", new Integer(parameters.getTransnum()));
		this.setDouble(23, "_linessum,", new Double(parameters.getLinessum()));
		this.setString(24, "_address,", parameters.getAddress());
		this.setString(25, "_createjdt,", parameters.getCreatejdt());
		this.setDouble(26, "_vattotal,", new Double(parameters.getVattotal()));
		this.setString(27, "_vatcalcult,", parameters.getVatcalcult());
		// this.setDate(28, "_taxdate,", parameters.getTaxdate());
		if (parameters.getTaxdate() == null) {
			this.setDate(28, "_taxdate", parameters.getTaxdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getTaxdate()
					.getTime());
			this.setDate(28, "_taxdate", fecha);
		}
		this.setInt(29, "_printedby,", new Integer(parameters.getPrintedby()));
		this.setString(30, "_objtype,", parameters.getObjtype());
		this.setString(31, "_countrycod,", parameters.getCountrycod());
		this.setString(32, "_addrname,", parameters.getAddrname());
		this.setString(33, "_prnconfrm,", parameters.getPrnconfrm());
		this.setInt(34, "_bnkactkey,", parameters.getBnkactkey());
		this.setString(35, "_manualchk,", parameters.getManualchk());
		this.setInt(36, "_usersign ", new Integer(parameters.getUsersign()));
		this.setInt(37, "_action", new Integer(action));
		v_resp = this.runQuery();
		return this.getInt();
	}

	public List get_cfp0_checkforpayment(CheckForPaymentInTO parameters)
			throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// s.setDbObject("{call sp_ges_cfp0_checkforpayment_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_get_checkforpayment(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(1, "_checkkey", new Integer(parameters.getCheckkey()));
		this.setInt(2, "_checknum,", new Integer(parameters.getChecknum()));
		this.setString(3, "_banknum,", parameters.getBanknum());
		this.setString(4, "_bankname,", parameters.getBankname());
		// this.setDate(5, "_checkdate,", parameters.getCheckdate());
		if (parameters.getCheckdate() == null) {
			this.setDate(5, "_checkdate", parameters.getCheckdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getCheckdate()
					.getTime());
			this.setDate(5, "_checkdate", fecha);
		}
		this.setString(6, "_dpstacct,", parameters.getDpstacct());
		this.setString(7, "_acctnum,", parameters.getAcctnum());
		this.setString(8, "_details,", parameters.getDetails());
		this.setString(9, "_transref,", parameters.getTransref());
		// this.setDate(10, "_pmntdate,", parameters.getPmntdate());
		if (parameters.getPmntdate() == null) {
			this.setDate(10, "_pmntdate", parameters.getPmntdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getPmntdate()
					.getTime());
			this.setDate(10, "_pmntdate", fecha);
		}
		this.setInt(11, "_pmntnum,", new Integer(parameters.getPmntnum()));
		this.setDouble(12, "_checksum,", new Double(parameters.getChecksum()));
		this.setString(13, "_trnsfrable,", parameters.getTrnsfrable());
		this.setString(14, "_vendorcode,", parameters.getVendorcode());
		this.setString(15, "_canceled,", parameters.getCanceled());
		this.setString(16, "_cardoracct,", parameters.getCardoracct());
		this.setString(17, "_printed,", parameters.getPrinted());
		this.setString(18, "_vendorname,", parameters.getVendorname());
		this.setString(19, "_totalwords,", parameters.getTotalwords());
		this.setString(20, "_signature,", parameters.getSignature());
		this.setString(21, "_checkacct,", parameters.getCheckacct());
		this.setInt(22, "_transnum,", new Integer(parameters.getTransnum()));
		this.setDouble(23, "_linessum,", new Double(parameters.getLinessum()));
		this.setString(24, "_address,", parameters.getAddress());
		this.setString(25, "_createjdt,", parameters.getCreatejdt());
		this.setDouble(26, "_vattotal,", new Double(parameters.getVattotal()));
		this.setString(27, "_vatcalcult,", parameters.getVatcalcult());
		// this.setDate(28, "_taxdate,", parameters.getTaxdate());
		if (parameters.getTaxdate() == null) {
			this.setDate(28, "_taxdate", parameters.getTaxdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getTaxdate()
					.getTime());
			this.setDate(28, "_taxdate", fecha);
		}
		this.setInt(29, "_printedby,", new Integer(parameters.getPrintedby()));
		this.setString(30, "_objtype,", parameters.getObjtype());
		this.setString(31, "_countrycod,", parameters.getCountrycod());
		this.setString(32, "_addrname,", parameters.getAddrname());
		this.setString(33, "_prnconfrm,", parameters.getPrnconfrm());
		this.setInt(34, "_bnkactkey,", parameters.getBnkactkey());
		this.setString(35, "_manualchk,", parameters.getManualchk());
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					CheckForPaymentTO check = new CheckForPaymentTO();
					check.setCheckkey(rowsetActual.getInt(1));
					check.setChecknum(rowsetActual.getInt(2));
					check.setBanknum(rowsetActual.getString(3));
					check.setBankname(rowsetActual.getString(4));
					check.setCheckdate(rowsetActual.getDate(5));
					check.setDpstacct(rowsetActual.getString(6));
					check.setAcctnum(rowsetActual.getString(7));
					check.setDetails(rowsetActual.getString(8));
					check.setTransref(rowsetActual.getString(9));
					check.setPmntdate(rowsetActual.getDate(10));
					check.setPmntnum(rowsetActual.getInt(11));
					check.setChecksum(rowsetActual.getDouble(12));
					check.setTrnsfrable(rowsetActual.getString(13));
					check.setVendorcode(rowsetActual.getString(14));
					check.setCanceled(rowsetActual.getString(15));
					check.setCardoracct(rowsetActual.getString(16));
					check.setPrinted(rowsetActual.getString(17));
					check.setVendorname(rowsetActual.getString(18));
					check.setTotalwords(rowsetActual.getString(19));
					check.setSignature(rowsetActual.getString(20));
					check.setCheckacct(rowsetActual.getString(21));
					check.setTransnum(rowsetActual.getInt(22));
					check.setLinessum(rowsetActual.getDouble(23));
					check.setAddress(rowsetActual.getString(24));
					check.setCreatejdt(rowsetActual.getString(25));
					check.setVattotal(rowsetActual.getDouble(26));
					check.setVatcalcult(rowsetActual.getString(27));
					check.setTaxdate(rowsetActual.getDate(28));
					check.setPrintedby(rowsetActual.getInt(29));
					check.setObjtype(rowsetActual.getString(30));
					check.setCountrycod(rowsetActual.getString(31));
					check.setAddrname(rowsetActual.getString(32));
					check.setPrnconfrm(rowsetActual.getString(33));
					check.setBnkactkey(rowsetActual.getInt(34));
					check.setManualchk(rowsetActual.getString(35));
					check.setUsersign(rowsetActual.getInt(36));
					_return.add(check);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	public CheckForPaymentTO get_cfp0_checkforpaymentByKey(int parameters)
			throws Exception {
		CheckForPaymentTO _return = new CheckForPaymentTO();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_checkforpayment_by_key(?)}");
		this.setInt(1, "_checkkey", parameters);

		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					CheckForPaymentTO check = new CheckForPaymentTO();
					check.setCheckkey(rowsetActual.getInt(1));
					check.setChecknum(rowsetActual.getInt(2));
					check.setBanknum(rowsetActual.getString(3));
					check.setBankname(rowsetActual.getString(4));
					check.setCheckdate(rowsetActual.getDate(5));
					check.setDpstacct(rowsetActual.getString(6));
					check.setAcctnum(rowsetActual.getString(7));
					check.setDetails(rowsetActual.getString(8));
					check.setTransref(rowsetActual.getString(9));
					check.setPmntdate(rowsetActual.getDate(10));
					check.setPmntnum(rowsetActual.getInt(11));
					check.setChecksum(rowsetActual.getDouble(12));
					check.setTrnsfrable(rowsetActual.getString(13));
					check.setVendorcode(rowsetActual.getString(14));
					check.setCanceled(rowsetActual.getString(15));
					check.setCardoracct(rowsetActual.getString(16));
					check.setPrinted(rowsetActual.getString(17));
					check.setVendorname(rowsetActual.getString(18));
					check.setTotalwords(rowsetActual.getString(19));
					check.setSignature(rowsetActual.getString(20));
					check.setCheckacct(rowsetActual.getString(21));
					check.setTransnum(rowsetActual.getInt(22));
					check.setLinessum(rowsetActual.getDouble(23));
					check.setAddress(rowsetActual.getString(24));
					check.setCreatejdt(rowsetActual.getString(25));
					check.setVattotal(rowsetActual.getDouble(26));
					check.setVatcalcult(rowsetActual.getString(27));
					check.setTaxdate(rowsetActual.getDate(28));
					check.setPrintedby(rowsetActual.getInt(29));
					check.setObjtype(rowsetActual.getString(30));
					check.setCountrycod(rowsetActual.getString(31));
					check.setAddrname(rowsetActual.getString(32));
					check.setPrnconfrm(rowsetActual.getString(33));
					check.setBnkactkey(rowsetActual.getInt(34));
					check.setManualchk(rowsetActual.getString(35));
					check.setUsersign(rowsetActual.getInt(36));
					_return = check;
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
}

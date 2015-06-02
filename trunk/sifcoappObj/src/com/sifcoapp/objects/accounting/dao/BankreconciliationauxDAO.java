package com.sifcoapp.objects.accounting.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.accounting.to.JournalEntryInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.accounting.to.bankreconciliationauxInTO;
import com.sifcoapp.objects.accounting.to.bankreconciliationauxTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.transaction.to.TransactionTo;
import com.sun.rowset.CachedRowSetImpl;

public class BankreconciliationauxDAO extends CommonDAO{

	public BankreconciliationauxDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankreconciliationauxDAO(Connection _conn) {
		super(_conn);
		// TODO Auto-generated constructor stub
	}
	public List getBankreconciliationaux(bankreconciliationauxInTO parameters) {
		List _return = new Vector();
		List lstResultSet = null;
		// ############### faltan filtros########################
		double zero = 0.00;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// this.setDject("{call sp_cat_articles_(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5)}");
		this.setDbObject("{call sp_get_bankreconciliationaux (?,?)}");
		this.setInt(1, "_extrmatch", parameters.getExtrmatch());
		if (parameters.getRefdate() == null) {
			this.setDate(2, "_refdate", parameters.getRefdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getRefdate()
					.getTime());
			this.setDate(2, "_refdate", fecha);
		}
		

		try {
			lstResultSet = this.runQuery();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {

					bankreconciliationauxTO journal = new bankreconciliationauxTO();
					journal.setTransid(rowsetActual.getInt(1));
					journal.setBatchnum(rowsetActual.getInt(2));
					journal.setBtfstatus(rowsetActual.getString(3));
					journal.setTranstype(rowsetActual.getString(4));
					journal.setBaseref(rowsetActual.getString(5));
					journal.setRefdate(rowsetActual.getDate(6));
					journal.setMemo(rowsetActual.getString(7));
					journal.setIntrnmatch(rowsetActual.getInt(8));
					journal.setExtrmatch(rowsetActual.getInt(9));
					journal.setRef1(rowsetActual.getString(10));
					journal.setRef2(rowsetActual.getString(11));
					journal.setLoctotal(rowsetActual.getDouble(12));
					journal.setSystotal(rowsetActual.getDouble(13));
					journal.setTranscode(rowsetActual.getString(14));
					journal.setTransrate(rowsetActual.getDouble(15));
					journal.setBtfline(rowsetActual.getInt(16));
					journal.setDuedate(rowsetActual.getDate(17));
					journal.setTaxdate(rowsetActual.getDate(18));
					journal.setFinncpriod(rowsetActual.getInt(19));
					journal.setRefndrprt(rowsetActual.getString(20));
					journal.setObjtype(rowsetActual.getString(21));
					journal.setIndicator(rowsetActual.getString(22));
					journal.setAdjtran(rowsetActual.getString(23));
					journal.setStornodate(rowsetActual.getDate(24));
					journal.setStornototr(rowsetActual.getInt(25));
					journal.setAutostorno(rowsetActual.getString(26));
					journal.setVatdate(rowsetActual.getDate(27));
					journal.setSeries(rowsetActual.getInt(28));
					journal.setNumber(rowsetActual.getInt(29));
					journal.setAutovat(rowsetActual.getString(30));
					journal.setDocseries(rowsetActual.getInt(31));
					journal.setPrinted(rowsetActual.getString(32));
					journal.setDoctype(rowsetActual.getString(33));
					journal.setCreator(rowsetActual.getString(34));
					journal.setSeqcode(rowsetActual.getInt(35));
					journal.setSerial(rowsetActual.getInt(36));
					journal.setAutowt(rowsetActual.getString(37));
					journal.setWtapplied(rowsetActual.getDouble(38));
					journal.setBaseamnt(rowsetActual.getDouble(39));
					journal.setBasevtat(rowsetActual.getDouble(40));
					journal.setBasetrans(rowsetActual.getInt(41));
					journal.setRef3(rowsetActual.getString(42));
					journal.setDeferedtax(rowsetActual.getString(43));
					journal.setAgrno(rowsetActual.getInt(44));
					journal.setSeqnum(rowsetActual.getInt(45));
					journal.setRptperiod(rowsetActual.getString(46));
					journal.setUsersign(rowsetActual.getInt(47));
					_return.add(journal);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return _return;
	}

	

	public int bankreconciliationaux(bankreconciliationauxTO parameters, int action)
			throws Exception {
		List v_resp;
		// this.seObject("{call sp_inv_gre0_good lkkkk(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{? = call sp_cat_journalentry_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(2, "_transid", new Integer(parameters.getTransid()));
		this.setInt(3, "_batchnum", new Integer(parameters.getBatchnum()));
		this.setString(4, "_btfstatus", parameters.getBtfstatus());
		this.setString(5, "_transtype", parameters.getTranstype());
		this.setString(6, "_baseref", parameters.getBaseref());
		if (parameters.getRefdate() == null) {
			this.setDate(7, "_refdate", parameters.getRefdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getRefdate()
					.getTime());
			this.setDate(7, "_refdate", fecha);
		}
		
		this.setString(8, "_memo", parameters.getMemo());
		this.setString(9, "_ref1", parameters.getRef1());
		this.setString(10, "_ref2", parameters.getRef2());
		this.setDouble(11, "_loctotal", new Double(parameters.getLoctotal()));
		this.setDouble(12, "_systotal", new Double(parameters.getSystotal()));
		this.setString(13, "_transcode", parameters.getTranscode());
		this.setDouble(14, "_transrate", new Double(parameters.getTransrate()));
		this.setInt(15, "_btfline", new Integer(parameters.getBtfline()));
		if (parameters.getDuedate() == null) {
			this.setDate(16, "_duedate", parameters.getDuedate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getDuedate()
					.getTime());
			this.setDate(16, "_duedate", fecha);
		}
		if (parameters.getTaxdate() == null) {
			this.setDate(17, "_taxdate", parameters.getTaxdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getTaxdate()
					.getTime());
			this.setDate(17, "_taxdate", fecha);
		}
		this.setInt(18, "_finncpriod", new Integer(parameters.getFinncpriod()));
		this.setString(19, "_refndrprt", parameters.getRefndrprt());
		this.setString(20, "_objtype", parameters.getObjtype());
		this.setString(21, "_indicator", parameters.getIndicator());
		this.setString(22, "_adjtran", parameters.getAdjtran());
		if (parameters.getStornodate() == null) {
			this.setDate(23, "_stornodate", parameters.getStornodate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getStornodate()
					.getTime());
			this.setDate(23, "_stornodate", fecha);
		}
		this.setInt(24, "_stornototr", new Integer(parameters.getStornototr()));
		this.setString(25, "_autostorno", parameters.getAutostorno());
		if (parameters.getVatdate() == null) {
			this.setDate(26, "_vatdate", parameters.getVatdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getVatdate()
					.getTime());
			this.setDate(26, "_vatdate", fecha);
		}
		this.setInt(27, "_series", new Integer(parameters.getSeries()));
		this.setInt(28, "_number", new Integer(parameters.getNumber()));
		this.setString(29, "_autovat", parameters.getAutovat());
		this.setInt(30, "_docseries", new Integer(parameters.getDocseries()));
		this.setString(31, "_printed", parameters.getPrinted());
		this.setString(32, "_doctype", parameters.getDoctype());
		this.setString(33, "_creator", parameters.getCreator());
		this.setInt(34, "_seqcode", new Integer(parameters.getSeqcode()));
		this.setInt(35, "_serial", new Integer(parameters.getSerial()));
		this.setString(36, "_autowt", parameters.getAutowt());
		this.setDouble(37, "_wtapplied", new Double(parameters.getWtapplied()));
		this.setDouble(38, "_baseamnt", new Double(parameters.getBaseamnt()));
		this.setDouble(39, "_basevtat", new Double(parameters.getBasevtat()));
		this.setInt(40, "_basetrans", new Integer(parameters.getBasetrans()));
		this.setString(41, "_ref3", parameters.getRef3());
		this.setString(42, "_deferedtax", parameters.getDeferedtax());
		this.setInt(43, "_agrno", new Integer(parameters.getAgrno()));
		this.setInt(44, "_seqnum", new Integer(parameters.getSeqnum()));
		this.setString(45, "_rptperiod", parameters.getRptperiod());
		this.setInt(46, "_usersign", new Integer(parameters.getUsersign()));
		this.setInt(47, "_action", new Integer(action));

		v_resp = this.runQuery();

		return this.getInt();
	}

	

}

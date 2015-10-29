package com.sifcoapp.objects.ExternalReconciliation.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.ExternalReconciliation.to.ExternalReconciliationTO;
import com.sifcoapp.objects.ExternalReconciliation.to.PendingExternalReconciliationInTO;
import com.sifcoapp.objects.ExternalReconciliation.to.bankreconciliationauxInTO;
import com.sifcoapp.objects.ExternalReconciliation.to.bankreconciliationauxTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.accounting.to.JournalEntryInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesInTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.transaction.to.TransactionTo;
import com.sun.rowset.CachedRowSetImpl;

public class BankreconciliationauxDAO extends CommonDAO {

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

	public int bankreconciliationaux_mtto(bankreconciliationauxTO parameters,
			int action) throws Exception {
		List v_resp;
		// this.seObject("{? = call sp_fin_cba2_bankreconciliationaux(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{? = call sp_fin_cba2_bankreconciliationaux(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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

		this.setInt(8, "_intrnmatch", parameters.getIntrnmatch());
		this.setInt(9, "_extrmatch", parameters.getExtrmatch());
		this.setString(10, "_memo", parameters.getMemo());
		this.setString(11, "_ref1", parameters.getRef1());
		this.setString(12, "_ref2", parameters.getRef2());
		this.setDouble(13, "_loctotal", new Double(parameters.getLoctotal()));
		this.setDouble(14, "_systotal", new Double(parameters.getSystotal()));
		this.setString(15, "_transcode", parameters.getTranscode());
		this.setDouble(16, "_transrate", new Double(parameters.getTransrate()));
		this.setInt(17, "_btfline", new Integer(parameters.getBtfline()));
		if (parameters.getDuedate() == null) {
			this.setDate(18, "_duedate", parameters.getDuedate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getDuedate()
					.getTime());
			this.setDate(18, "_duedate", fecha);
		}
		if (parameters.getTaxdate() == null) {
			this.setDate(19, "_taxdate", parameters.getTaxdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getTaxdate()
					.getTime());
			this.setDate(19, "_taxdate", fecha);
		}
		this.setInt(20, "_finncpriod", new Integer(parameters.getFinncpriod()));
		this.setString(21, "_refndrprt", parameters.getRefndrprt());
		this.setString(22, "_objtype", parameters.getObjtype());
		this.setString(23, "_indicator", parameters.getIndicator());
		this.setString(24, "_adjtran", parameters.getAdjtran());
		if (parameters.getStornodate() == null) {
			this.setDate(25, "_stornodate", parameters.getStornodate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getStornodate()
					.getTime());
			this.setDate(25, "_stornodate", fecha);
		}
		this.setInt(26, "_stornototr", new Integer(parameters.getStornototr()));
		this.setString(27, "_autostorno", parameters.getAutostorno());
		if (parameters.getVatdate() == null) {
			this.setDate(28, "_vatdate", parameters.getVatdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getVatdate()
					.getTime());
			this.setDate(28, "_vatdate", fecha);
		}
		this.setInt(29, "_series", new Integer(parameters.getSeries()));
		this.setInt(30, "_number", new Integer(parameters.getNumber()));
		this.setString(31, "_autovat", parameters.getAutovat());
		this.setInt(32, "_docseries", new Integer(parameters.getDocseries()));
		this.setString(33, "_printed", parameters.getPrinted());
		this.setString(34, "_doctype", parameters.getDoctype());
		this.setString(35, "_creator", parameters.getCreator());
		this.setInt(36, "_seqcode", new Integer(parameters.getSeqcode()));
		this.setInt(37, "_serial", new Integer(parameters.getSerial()));
		this.setString(38, "_autowt", parameters.getAutowt());
		this.setDouble(39, "_wtapplied", new Double(parameters.getWtapplied()));
		this.setDouble(40, "_baseamnt", new Double(parameters.getBaseamnt()));
		this.setDouble(41, "_basevtat", new Double(parameters.getBasevtat()));
		this.setInt(42, "_basetrans", new Integer(parameters.getBasetrans()));
		this.setString(43, "_ref3", parameters.getRef3());
		this.setString(44, "_deferedtax", parameters.getDeferedtax());
		this.setInt(45, "_agrno", new Integer(parameters.getAgrno()));
		this.setInt(46, "_seqnum", new Integer(parameters.getSeqnum()));
		this.setString(47, "_rptperiod", parameters.getRptperiod());
		this.setInt(48, "_usersign", new Integer(parameters.getUsersign()));
		this.setInt(49, "_action", new Integer(action));

		v_resp = this.runQuery();

		return this.getInt();
	}

	public List getJournalEntryDetail_nc(ExternalReconciliationTO parameters)
			throws Exception {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// s.setDbObject("{call sp_gis1_goodsissuedetail_m(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_get_journalentrydetail_nc(?,?)}");
		this.setString(1, "_account", parameters.getAccount());
		if (parameters.getRefDate() == null) {
			this.setDate(2, "_duedate", parameters.getRefDate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getRefDate()
					.getTime());
			this.setDate(2, "_duedate", fecha);
		}

		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			while (rowsetActual.next()) {
				JournalEntryLinesTO journal = new JournalEntryLinesTO();
				journal.setTransid(rowsetActual.getInt(1));
				journal.setLine_id(rowsetActual.getInt(2));
				journal.setAccount(rowsetActual.getString(3));
				journal.setDebit(rowsetActual.getDouble(4));
				journal.setCredit(rowsetActual.getDouble(5));
				journal.setDuedate(rowsetActual.getDate(6));
				journal.setShortname(rowsetActual.getString(7));
				journal.setContraact(rowsetActual.getString(8));
				journal.setLinememo(rowsetActual.getString(9));
				journal.setRef3line(rowsetActual.getString(10));
				journal.setTranstype(rowsetActual.getString(11));
				journal.setRefdate(rowsetActual.getDate(12));
				journal.setRef1(rowsetActual.getString(13));
				journal.setRef2(rowsetActual.getString(14));
				journal.setBaseref(rowsetActual.getString(15));
				journal.setTaxdate(rowsetActual.getDate(16));
				journal.setMthdate(rowsetActual.getDate(17));
				journal.setTomthsum(rowsetActual.getDouble(18));
				journal.setBatchnum(rowsetActual.getInt(19));
				journal.setFinncpriod(rowsetActual.getInt(20));
				journal.setReltransid(rowsetActual.getInt(21));
				journal.setRellineid(rowsetActual.getInt(22));
				journal.setReltype(rowsetActual.getString(23));
				journal.setVatgroup(rowsetActual.getString(24));
				journal.setBasesum(rowsetActual.getDouble(25));
				journal.setVatrate(rowsetActual.getDouble(26));
				journal.setIndicator(rowsetActual.getString(27));
				journal.setObjtype(rowsetActual.getString(28));
				journal.setVatdate(rowsetActual.getDate(29));
				journal.setPaymentref(rowsetActual.getString(30));
				journal.setSysbasesum(rowsetActual.getDouble(31));
				journal.setVatline(rowsetActual.getString(32));
				journal.setVatamount(rowsetActual.getDouble(33));
				journal.setClosed(rowsetActual.getString(34));
				journal.setGrossvalue(rowsetActual.getDouble(35));
				journal.setDebcred(rowsetActual.getString(36));
				journal.setSequencenr(rowsetActual.getInt(37));
				journal.setStornoacc(rowsetActual.getString(38));
				journal.setBalduedeb(rowsetActual.getDouble(39));
				journal.setBalduecred(rowsetActual.getDouble(40));
				journal.setIsnet(rowsetActual.getString(41));
				journal.setTaxtype(rowsetActual.getInt(42));
				journal.setTaxpostacc(rowsetActual.getString(43));
				journal.setTotalvat(rowsetActual.getDouble(44));
				journal.setWtliable(rowsetActual.getString(45));
				journal.setWtline(rowsetActual.getString(46));
				journal.setPayblock(rowsetActual.getString(47));
				journal.setMatchref(rowsetActual.getString(48));
				journal.setOrdered(rowsetActual.getString(49));
				journal.setBplname(rowsetActual.getString(50));
				journal.setVatregnum(rowsetActual.getString(51));
				journal.setSledgerf(rowsetActual.getString(52));
				/*journal.setAcctname(rowsetActual.getString(53));
				journal.setIntrnmatch(rowsetActual.getInt(54));
				journal.setExtrmatch(rowsetActual.getInt(55));*/

				_return.add(journal);
			}
			rowsetActual.close();
		}
		return _return;
	}

	public List getBankreconciliationaux_nc(ExternalReconciliationTO parameters)
			throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);

		// this.setDject("{call sp_cat_articles_(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5)}");
		this.setDbObject("{call sp_get_bankreconciliationaux_nc (?,?)}");
		this.setString(1, "_account", parameters.getAccount());
		if (parameters.getRefDate() == null) {
			this.setDate(2, "_duedate", parameters.getRefDate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getRefDate()
					.getTime());
			this.setDate(2, "_duedate", fecha);
		}
		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
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

		}
		return _return;
	}

	public double getSaldo(String account,Date fecha) throws Exception {

		double saldo = 0.0;
		
		List conceptos = new Vector();
		int groupmask = -1;
		List lstResult = new Vector();
		List lstResultSet = null;

		this.setDbObject("SELECT sum(debit)-sum(credit)as currtotal,t1.groupmask FROM cat_jdt1_journalentrylines t0 join cat_acc0_account t1 on t0.account=t1.acctcode  where t0.account=? and t0.duedate <=? group by t0.account,t1.acctname,t1.groupmask");
		this.setString(1, "_account", account);

		if (fecha == null) {
			this.setDate(2, "_docdate", fecha);
		} else {
			java.sql.Date fecha1 = new java.sql.Date(fecha.getTime());
			this.setDate(2, "_docdate", fecha1);
		}

		lstResultSet = this.runQueryPrepared();

		CachedRowSetImpl rowsetActual;

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					saldo = rowsetActual.getDouble(1);
					groupmask=rowsetActual.getInt(2);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(groupmask==2||groupmask==3||groupmask==5){
			saldo=saldo*-1;
		}
		
		return saldo;

	}
}

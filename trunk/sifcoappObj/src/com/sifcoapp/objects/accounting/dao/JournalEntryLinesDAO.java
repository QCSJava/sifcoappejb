package com.sifcoapp.objects.accounting.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sun.rowset.CachedRowSetImpl;

public class JournalEntryLinesDAO extends CommonDAO{
	
	public JournalEntryLinesDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JournalEntryLinesDAO(Connection _conn) {
		super(_conn);
		// TODO Auto-generated constructor stub
	}

	public List getJournalEntryDetail(int transid) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_journalentrydetail(?)}");		
		this.setInt(1,"_transid", new Integer(transid));		
		
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
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

					_return.add(journal);
				}
				rowsetActual.close();
		}
		return _return;
	}
	
	public int journalEntryLines_mtto(JournalEntryLinesTO parameters,int action) throws Exception{
		int v_resp = 0;
		// s.setDbObject("{call sp_gis1_goodsissuedetail_m(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_journalentrydetail_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(1,"_transid", new Integer(parameters.getTransid()));
		this.setInt(2,"_line_id", new Integer(parameters.getLine_id()));
		this.setString(3,"_account", parameters.getAccount());
		this.setDouble(4,"_debit", new Double(parameters.getDebit()));
		this.setDouble(5,"_credit", new Double(parameters.getCredit()));
		if (parameters.getDuedate() == null) {
			this.setDate(6,"_duedate", parameters.getDuedate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getDuedate()
					.getTime());
			this.setDate(6,"_duedate", fecha);
		}
		this.setString(7,"_shortname", parameters.getShortname());
		this.setString(8,"_contraact", parameters.getContraact());
		this.setString(9,"_linememo", parameters.getLinememo());
		this.setString(10,"_ref3line", parameters.getRef3line());
		this.setString(11,"_transtype", parameters.getTranstype());
		if (parameters.getRefdate() == null) {
			this.setDate(12,"_refdate", parameters.getRefdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getRefdate()
					.getTime());
			this.setDate(12,"_refdate", fecha);
		}
		this.setString(13,"_ref1", parameters.getRef1());
		this.setString(14,"_ref2", parameters.getRef2());
		this.setString(15,"_baseref", parameters.getBaseref());
		if (parameters.getTaxdate() == null) {
			this.setDate(16,"_taxdate", parameters.getTaxdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getTaxdate()
					.getTime());
			this.setDate(16,"_taxdate", fecha);
		}
		if (parameters.getMthdate() == null) {
			this.setDate(17,"_mthdate", parameters.getMthdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getMthdate()
					.getTime());
			this.setDate(17,"_mthdate", fecha);
		}
		this.setDouble(18,"_tomthsum", new Double(parameters.getTomthsum()));
		this.setInt(19,"_batchnum", new Integer(parameters.getBatchnum()));
		this.setInt(20,"_finncpriod", new Integer(parameters.getFinncpriod()));
		this.setInt(21,"_reltransid", new Integer(parameters.getReltransid()));
		this.setInt(22,"_rellineid", new Integer(parameters.getRellineid()));
		this.setString(23,"_reltype", parameters.getReltype());
		this.setString(24,"_vatgroup", parameters.getVatgroup());
		this.setDouble(25,"_basesum", new Double(parameters.getBasesum()));
		this.setDouble(26,"_vatrate",new Double(parameters.getVatrate()));
		this.setString(27,"_indicator", parameters.getIndicator());
		this.setString(28,"_objtype", parameters.getObjtype());
		if (parameters.getVatdate() == null) {
			this.setDate(29,"_vatdate", parameters.getVatdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getVatdate()
					.getTime());
			this.setDate(29,"_vatdate", fecha);
		}
		this.setString(30,"_paymentref", parameters.getPaymentref());
		this.setDouble(31,"_sysbasesum", new Double(parameters.getSysbasesum()));
		this.setString(32,"_vatline", parameters.getVatline());
		this.setDouble(33,"_vatamount", new Double(parameters.getVatamount()));
		this.setString(34,"_closed", parameters.getClosed());
		this.setDouble(35,"_grossvalue", new Double(parameters.getGrossvalue()));
		this.setString(36,"_debcred", parameters.getDebcred());
		this.setInt(37,"_sequencenr", new Integer(parameters.getSequencenr()));
		this.setString(38,"_stornoacc",parameters.getStornoacc());
		this.setDouble(39,"_balduedeb", new Double(parameters.getBalduedeb()));
		this.setDouble(40,"_balduecred", new Double(parameters.getBalduecred()));
		this.setString(41,"_isnet", parameters.getIsnet());
		this.setInt(42,"_taxtype", new Integer(parameters.getTaxtype()));
		this.setString(43,"_taxpostacc", parameters.getTaxpostacc());
		this.setDouble(44,"_totalvat", new Double(parameters.getTotalvat()));
		this.setString(45,"_wtliable", parameters.getWtliable());
		this.setString(46,"_wtline", parameters.getWtline());
		this.setString(47,"_payblock", parameters.getPayblock());
		this.setString(48,"_matchref", parameters.getMatchref());
		this.setString(49,"_ordered", parameters.getOrdered());
		this.setString(50,"_bplname", parameters.getBplname());
		this.setString(51,"_vatregnum", parameters.getVatregnum());
		this.setString(52,"_sledgerf", parameters.getSledgerf());
		this.setInt(53,"_action", new Integer(action));
		v_resp = this.runUpdate();
		return v_resp;
	}
    
	
}

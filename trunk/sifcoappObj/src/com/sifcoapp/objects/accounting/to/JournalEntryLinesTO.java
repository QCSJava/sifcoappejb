package com.sifcoapp.objects.accounting.to;

import java.util.Date;

import com.sifcoapp.objects.common.to.CommonTO;

public class JournalEntryLinesTO extends CommonTO{
	
	private static final long serialVersionUID = -8123142545837530500L;

	private int transid;
	private int line_id;
	private String account;
	private Double debit;
	private Double credit;
	private Date duedate;
	private String shortname;
	private String contraact;
	private String linememo;
	private String ref3line;
	private String transtype;
	private Date refdate;
	private String ref1;
	private String ref2;
	private String baseref;
	private Date taxdate;
	private Date mthdate;
	private Double tomthsum;
	private int batchnum;
	private int finncpriod;
	private int reltransid;
	private int rellineid;
	private String reltype;
	private String vatgroup;
	private Double basesum;
	private Double vatrate;
	private String indicator;
	private String objtype;
	private Date vatdate;
	private String paymentref;
	private Double sysbasesum;
	private String vatline;
	private Double vatamount;
	private String closed;
	private Double grossvalue;
	private String debcred;
	private int sequencenr;
	private String stornoacc;
	private Double balduedeb;
	private Double balduecred;
	private String isnet;
	private int taxtype;
	private String taxpostacc;
	private Double totalvat;
	private String wtliable;
	private String wtline;
	private String payblock;
	private String matchref;
	private String ordered;
	private String bplname;
	private String vatregnum;
	private String sledgerf;
	private String acctname;
	
	public JournalEntryLinesTO(int transid, int line_id, String account,
			Double debit, Double credit, Date duedate, String shortname,
			String contraact, String linememo, String ref3line,
			String transtype, Date refdate, String ref1, String ref2,
			String baseref, Date taxdate, Date mthdate, Double tomthsum,
			int batchnum, int finncpriod, int reltransid, int rellineid,
			String reltype, String vatgroup, Double basesum, Double vatrate,
			String indicator, String objtype, Date vatdate, String paymentref,
			Double sysbasesum, String vatline, Double vatamount, String closed,
			Double grossvalue, String debcred, int sequencenr,
			String stornoacc, Double balduedeb, Double balduecred,
			String isnet, int taxtype, String taxpostacc, Double totalvat,
			String wtliable, String wtline, String payblock, String matchref,
			String ordered, String bplname, String vatregnum, String sledgerf,
			String acctname) {
		super();
		this.transid = transid;
		this.line_id = line_id;
		this.account = account;
		this.debit = debit;
		this.credit = credit;
		this.duedate = duedate;
		this.shortname = shortname;
		this.contraact = contraact;
		this.linememo = linememo;
		this.ref3line = ref3line;
		this.transtype = transtype;
		this.refdate = refdate;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.baseref = baseref;
		this.taxdate = taxdate;
		this.mthdate = mthdate;
		this.tomthsum = tomthsum;
		this.batchnum = batchnum;
		this.finncpriod = finncpriod;
		this.reltransid = reltransid;
		this.rellineid = rellineid;
		this.reltype = reltype;
		this.vatgroup = vatgroup;
		this.basesum = basesum;
		this.vatrate = vatrate;
		this.indicator = indicator;
		this.objtype = objtype;
		this.vatdate = vatdate;
		this.paymentref = paymentref;
		this.sysbasesum = sysbasesum;
		this.vatline = vatline;
		this.vatamount = vatamount;
		this.closed = closed;
		this.grossvalue = grossvalue;
		this.debcred = debcred;
		this.sequencenr = sequencenr;
		this.stornoacc = stornoacc;
		this.balduedeb = balduedeb;
		this.balduecred = balduecred;
		this.isnet = isnet;
		this.taxtype = taxtype;
		this.taxpostacc = taxpostacc;
		this.totalvat = totalvat;
		this.wtliable = wtliable;
		this.wtline = wtline;
		this.payblock = payblock;
		this.matchref = matchref;
		this.ordered = ordered;
		this.bplname = bplname;
		this.vatregnum = vatregnum;
		this.sledgerf = sledgerf;
		this.acctname = acctname;
	}

	public JournalEntryLinesTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public int getTransid() {
		return transid;
	}

	public void setTransid(int transid) {
		this.transid = transid;
	}

	public int getLine_id() {
		return line_id;
	}

	public void setLine_id(int line_id) {
		this.line_id = line_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Double getDebit() {
		return debit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getContraact() {
		return contraact;
	}

	public void setContraact(String contraact) {
		this.contraact = contraact;
	}

	public String getLinememo() {
		return linememo;
	}

	public void setLinememo(String linememo) {
		this.linememo = linememo;
	}

	public String getRef3line() {
		return ref3line;
	}

	public void setRef3line(String ref3line) {
		this.ref3line = ref3line;
	}

	public String getTranstype() {
		return transtype;
	}

	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}

	public Date getRefdate() {
		return refdate;
	}

	public void setRefdate(Date refdate) {
		this.refdate = refdate;
	}

	public String getRef1() {
		return ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	public String getBaseref() {
		return baseref;
	}

	public void setBaseref(String baseref) {
		this.baseref = baseref;
	}

	public Date getTaxdate() {
		return taxdate;
	}

	public void setTaxdate(Date taxdate) {
		this.taxdate = taxdate;
	}

	public Date getMthdate() {
		return mthdate;
	}

	public void setMthdate(Date mthdate) {
		this.mthdate = mthdate;
	}

	public Double getTomthsum() {
		return tomthsum;
	}

	public void setTomthsum(Double tomthsum) {
		this.tomthsum = tomthsum;
	}

	public int getBatchnum() {
		return batchnum;
	}

	public void setBatchnum(int batchnum) {
		this.batchnum = batchnum;
	}

	public int getFinncpriod() {
		return finncpriod;
	}

	public void setFinncpriod(int finncpriod) {
		this.finncpriod = finncpriod;
	}

	public int getReltransid() {
		return reltransid;
	}

	public void setReltransid(int reltransid) {
		this.reltransid = reltransid;
	}

	public int getRellineid() {
		return rellineid;
	}

	public void setRellineid(int rellineid) {
		this.rellineid = rellineid;
	}

	public String getReltype() {
		return reltype;
	}

	public void setReltype(String reltype) {
		this.reltype = reltype;
	}

	public String getVatgroup() {
		return vatgroup;
	}

	public void setVatgroup(String vatgroup) {
		this.vatgroup = vatgroup;
	}

	public Double getBasesum() {
		return basesum;
	}

	public void setBasesum(Double basesum) {
		this.basesum = basesum;
	}

	public Double getVatrate() {
		return vatrate;
	}

	public void setVatrate(Double vatrate) {
		this.vatrate = vatrate;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getObjtype() {
		return objtype;
	}

	public void setObjtype(String objtype) {
		this.objtype = objtype;
	}

	public Date getVatdate() {
		return vatdate;
	}

	public void setVatdate(Date vatdate) {
		this.vatdate = vatdate;
	}

	public String getPaymentref() {
		return paymentref;
	}

	public void setPaymentref(String paymentref) {
		this.paymentref = paymentref;
	}

	public Double getSysbasesum() {
		return sysbasesum;
	}

	public void setSysbasesum(Double sysbasesum) {
		this.sysbasesum = sysbasesum;
	}

	public String getVatline() {
		return vatline;
	}

	public void setVatline(String vatline) {
		this.vatline = vatline;
	}

	public Double getVatamount() {
		return vatamount;
	}

	public void setVatamount(Double vatamount) {
		this.vatamount = vatamount;
	}

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public Double getGrossvalue() {
		return grossvalue;
	}

	public void setGrossvalue(Double grossvalue) {
		this.grossvalue = grossvalue;
	}

	public String getDebcred() {
		return debcred;
	}

	public void setDebcred(String debcred) {
		this.debcred = debcred;
	}

	public int getSequencenr() {
		return sequencenr;
	}

	public void setSequencenr(int sequencenr) {
		this.sequencenr = sequencenr;
	}

	public String getStornoacc() {
		return stornoacc;
	}

	public void setStornoacc(String stornoacc) {
		this.stornoacc = stornoacc;
	}

	public Double getBalduedeb() {
		return balduedeb;
	}

	public void setBalduedeb(Double balduedeb) {
		this.balduedeb = balduedeb;
	}

	public Double getBalduecred() {
		return balduecred;
	}

	public void setBalduecred(Double balduecred) {
		this.balduecred = balduecred;
	}

	public String getIsnet() {
		return isnet;
	}

	public void setIsnet(String isnet) {
		this.isnet = isnet;
	}

	public int getTaxtype() {
		return taxtype;
	}

	public void setTaxtype(int taxtype) {
		this.taxtype = taxtype;
	}

	public String getTaxpostacc() {
		return taxpostacc;
	}

	public void setTaxpostacc(String taxpostacc) {
		this.taxpostacc = taxpostacc;
	}

	public Double getTotalvat() {
		return totalvat;
	}

	public void setTotalvat(Double totalvat) {
		this.totalvat = totalvat;
	}

	public String getWtliable() {
		return wtliable;
	}

	public void setWtliable(String wtliable) {
		this.wtliable = wtliable;
	}

	public String getWtline() {
		return wtline;
	}

	public void setWtline(String wtline) {
		this.wtline = wtline;
	}

	public String getPayblock() {
		return payblock;
	}

	public void setPayblock(String payblock) {
		this.payblock = payblock;
	}

	public String getMatchref() {
		return matchref;
	}

	public void setMatchref(String matchref) {
		this.matchref = matchref;
	}

	public String getOrdered() {
		return ordered;
	}

	public void setOrdered(String ordered) {
		this.ordered = ordered;
	}

	public String getBplname() {
		return bplname;
	}

	public void setBplname(String bplname) {
		this.bplname = bplname;
	}

	public String getVatregnum() {
		return vatregnum;
	}

	public void setVatregnum(String vatregnum) {
		this.vatregnum = vatregnum;
	}

	public String getSledgerf() {
		return sledgerf;
	}

	public void setSledgerf(String sledgerf) {
		this.sledgerf = sledgerf;
	}

	public String getAcctname() {
		return acctname;
	}

	public void setAcctname(String acctname) {
		this.acctname = acctname;
	}

}

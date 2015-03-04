package com.sifcoapp.objects.accounting.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class RecurringPostingsDetailTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6280183447197822465L;
	private String rcurcode;
	private int lineid;
	private String acctcode;
	private String acctdesc;
	private Double debit;
	private Double credit;
	private String currency;
	private int instance;
	private String vatgroup;
	private String vatline;
	private String ctrlacct;
	private String ocrcode;
	private int taxtype;
	private String taxpostacc;
	private String taxcode;
	private String ocrcode1;
	private String ocrcode2;
	private String ocrcode3;
	private String ocrcode4;
	private String wtliable;
	private String wtaxline;
	private Double grossvalue;
	private int bplid;
	public RecurringPostingsDetailTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RecurringPostingsDetailTO(String rcurcode, int lineid,
			String acctcode, String acctdesc, Double debit, Double credit,
			String currency, int instance, String vatgroup, String vatline,
			String ctrlacct, String ocrcode, int taxtype, String taxpostacc,
			String taxcode, String ocrcode1, String ocrcode2, String ocrcode3,
			String ocrcode4, String wtliable, String wtaxline,
			Double grossvalue, int bplid) {
		super();
		this.rcurcode = rcurcode;
		this.lineid = lineid;
		this.acctcode = acctcode;
		this.acctdesc = acctdesc;
		this.debit = debit;
		this.credit = credit;
		this.currency = currency;
		this.instance = instance;
		this.vatgroup = vatgroup;
		this.vatline = vatline;
		this.ctrlacct = ctrlacct;
		this.ocrcode = ocrcode;
		this.taxtype = taxtype;
		this.taxpostacc = taxpostacc;
		this.taxcode = taxcode;
		this.ocrcode1 = ocrcode1;
		this.ocrcode2 = ocrcode2;
		this.ocrcode3 = ocrcode3;
		this.ocrcode4 = ocrcode4;
		this.wtliable = wtliable;
		this.wtaxline = wtaxline;
		this.grossvalue = grossvalue;
		this.bplid = bplid;
	}
	public String getRcurcode() {
		return rcurcode;
	}
	public void setRcurcode(String rcurcode) {
		this.rcurcode = rcurcode;
	}
	public int getLineid() {
		return lineid;
	}
	public void setLineid(int lineid) {
		this.lineid = lineid;
	}
	public String getAcctcode() {
		return acctcode;
	}
	public void setAcctcode(String acctcode) {
		this.acctcode = acctcode;
	}
	public String getAcctdesc() {
		return acctdesc;
	}
	public void setAcctdesc(String acctdesc) {
		this.acctdesc = acctdesc;
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
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getInstance() {
		return instance;
	}
	public void setInstance(int instance) {
		this.instance = instance;
	}
	public String getVatgroup() {
		return vatgroup;
	}
	public void setVatgroup(String vatgroup) {
		this.vatgroup = vatgroup;
	}
	public String getVatline() {
		return vatline;
	}
	public void setVatline(String vatline) {
		this.vatline = vatline;
	}
	public String getCtrlacct() {
		return ctrlacct;
	}
	public void setCtrlacct(String ctrlacct) {
		this.ctrlacct = ctrlacct;
	}
	public String getOcrcode() {
		return ocrcode;
	}
	public void setOcrcode(String ocrcode) {
		this.ocrcode = ocrcode;
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
	public String getTaxcode() {
		return taxcode;
	}
	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}
	public String getOcrcode1() {
		return ocrcode1;
	}
	public void setOcrcode1(String ocrcode1) {
		this.ocrcode1 = ocrcode1;
	}
	public String getOcrcode2() {
		return ocrcode2;
	}
	public void setOcrcode2(String ocrcode2) {
		this.ocrcode2 = ocrcode2;
	}
	public String getOcrcode3() {
		return ocrcode3;
	}
	public void setOcrcode3(String ocrcode3) {
		this.ocrcode3 = ocrcode3;
	}
	public String getOcrcode4() {
		return ocrcode4;
	}
	public void setOcrcode4(String ocrcode4) {
		this.ocrcode4 = ocrcode4;
	}
	public String getWtliable() {
		return wtliable;
	}
	public void setWtliable(String wtliable) {
		this.wtliable = wtliable;
	}
	public String getWtaxline() {
		return wtaxline;
	}
	public void setWtaxline(String wtaxline) {
		this.wtaxline = wtaxline;
	}
	public Double getGrossvalue() {
		return grossvalue;
	}
	public void setGrossvalue(Double grossvalue) {
		this.grossvalue = grossvalue;
	}
	public int getBplid() {
		return bplid;
	}
	public void setBplid(int bplid) {
		this.bplid = bplid;
	}

	
}

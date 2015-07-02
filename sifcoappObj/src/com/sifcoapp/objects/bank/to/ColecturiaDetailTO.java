package com.sifcoapp.objects.bank.to;

import com.sifcoapp.objects.common.to.CommonTO;

import java.util.Date;

public class ColecturiaDetailTO extends CommonTO {

	private static final long serialVersionUID = 188778789848816564L;

	private int docentry;
	private int linenum;
	private String linestatus;
	private String objtype;
	private String dscription;
	private String acctcode;
	private String acctcode2;
	private String acctcode3;
	private String ctlaccount;
	private String ocrcode;
	private int transid;
	private String confirmed;
	private String peymethod;
	private Double paidsum;
	private Double vatsum;
	private String docsubtype;
	private String value1;
	private String value2;
	private String value3;
	private String taxstatus;
	private String aditional_account;
	
	public ColecturiaDetailTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ColecturiaDetailTO(int docentry, int linenum, String linestatus,
			String objtype, String dscription, String acctcode,
			String acctcode2, String acctcode3, String ctlaccount,
			String ocrcode, int transid, String confirmed, String peymethod,
			Double paidsum, Double vatsum, String docsubtype, String value1,
			String value2, String value3,String taxstatus,String aditional_account) {
		super();
		this.docentry = docentry;
		this.linenum = linenum;
		this.linestatus = linestatus;
		this.objtype = objtype;
		this.dscription = dscription;
		this.acctcode = acctcode;
		this.acctcode2 = acctcode2;
		this.acctcode3 = acctcode3;
		this.ctlaccount = ctlaccount;
		this.ocrcode = ocrcode;
		this.transid = transid;
		this.confirmed = confirmed;
		this.peymethod = peymethod;
		this.paidsum = paidsum;
		this.vatsum = vatsum;
		this.docsubtype = docsubtype;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.taxstatus=taxstatus;
		this.aditional_account=aditional_account;
	}

	public int getDocentry() {
		return docentry;
	}

	public void setDocentry(int docentry) {
		this.docentry = docentry;
	}

	public int getLinenum() {
		return linenum;
	}

	public void setLinenum(int linenum) {
		this.linenum = linenum;
	}

	public String getLinestatus() {
		return linestatus;
	}

	public void setLinestatus(String linestatus) {
		this.linestatus = linestatus;
	}

	public String getObjtype() {
		return objtype;
	}

	public void setObjtype(String objtype) {
		this.objtype = objtype;
	}

	public String getDscription() {
		return dscription;
	}

	public void setDscription(String dscription) {
		this.dscription = dscription;
	}

	public String getAcctcode() {
		return acctcode;
	}

	public void setAcctcode(String acctcode) {
		this.acctcode = acctcode;
	}

	public String getAcctcode2() {
		return acctcode2;
	}

	public void setAcctcode2(String acctcode2) {
		this.acctcode2 = acctcode2;
	}

	public String getAcctcode3() {
		return acctcode3;
	}

	public void setAcctcode3(String acctcode3) {
		this.acctcode3 = acctcode3;
	}

	public String getCtlaccount() {
		return ctlaccount;
	}

	public void setCtlaccount(String ctlaccount) {
		this.ctlaccount = ctlaccount;
	}

	public String getOcrcode() {
		return ocrcode;
	}

	public void setOcrcode(String ocrcode) {
		this.ocrcode = ocrcode;
	}

	public int getTransid() {
		return transid;
	}

	public void setTransid(int transid) {
		this.transid = transid;
	}

	public String getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

	public String getPeymethod() {
		return peymethod;
	}

	public void setPeymethod(String peymethod) {
		this.peymethod = peymethod;
	}

	public Double getPaidsum() {
		return paidsum;
	}

	public void setPaidsum(Double paidsum) {
		this.paidsum = paidsum;
	}

	public Double getVatsum() {
		return vatsum;
	}

	public void setVatsum(Double vatsum) {
		this.vatsum = vatsum;
	}

	public String getDocsubtype() {
		return docsubtype;
	}

	public void setDocsubtype(String docsubtype) {
		this.docsubtype = docsubtype;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getTaxstatus() {
		return taxstatus;
	}

	public void setTaxstatus(String taxstatus) {
		this.taxstatus = taxstatus;
	}

	public String getAditional_account() {
		return aditional_account;
	}

	public void setAditional_account(String aditional_account) {
		this.aditional_account = aditional_account;
	}

}

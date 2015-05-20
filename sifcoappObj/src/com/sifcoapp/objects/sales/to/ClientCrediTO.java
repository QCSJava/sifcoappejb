package com.sifcoapp.objects.sales.to;

import java.util.Date;
import java.util.List;

import com.sifcoapp.objects.common.to.CommonTO;


public class ClientCrediTO extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2593855183473932469L;
	/**
	 * 
	 */
	
	
	private int docentry;
	private int docnum;
	private String doctype;
	private String canceled;
	private String docstatus;
	private String objtype;
	private Date docdate;
	private Date docduedate;
	private String cardcode;
	private String numatcard;
	private String cardname;
	private double vatsum;
	private Double discsum;
	private Double doctotal;
	private String ref1;
	private String ref2;
	private String comments;
	private String jrnlmemo;
	private Date paidtodate;
	private int transid;
	private int receiptnum;
	private int groupnum;
	private String confirmed;
	private String createtran;
	private int series;
	private Date taxdate;
	private String filler;
	private Double rounddif;
	private String rounding;
	private Date canceldate;
	private String peymethod;
	private String ctlaccount;
	private String bplname;
	private String vatregnum;
	private Double paidsum;
	private String towhscode;
	private Double nret;
	private String namenp;
	private int quedan;
	private Date fechreciva;
	private Date fquedan;
	private int usersign;
	private Date createdate;
	private int createtime;
	List clientDetails;
	public ClientCrediTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClientCrediTO(int docentry, int docnum, String doctype, String canceled,
			String docstatus, String objtype, Date docdate, Date docduedate,
			String cardcode, String numatcard, String cardname, double vatsum,
			Double discsum, Double doctotal, String ref1, String ref2,
			String comments, String jrnlmemo, Date paidtodate, int transid,
			int receiptnum, int groupnum, String confirmed, String createtran,
			int series, Date taxdate, String filler, Double rounddif,
			String rounding, Date canceldate, String peymethod,
			String ctlaccount, String bplname, String vatregnum,
			Double paidsum, String towhscode, Double nret, String namenp,
			int quedan, Date fechreciva, Date fquedan, int usersign,
			Date createdate, int createtime, List clientDetails) {
		super();
		this.docentry = docentry;
		this.docnum = docnum;
		this.doctype = doctype;
		this.canceled = canceled;
		this.docstatus = docstatus;
		this.objtype = objtype;
		this.docdate = docdate;
		this.docduedate = docduedate;
		this.cardcode = cardcode;
		this.numatcard = numatcard;
		this.cardname = cardname;
		this.vatsum = vatsum;
		this.discsum = discsum;
		this.doctotal = doctotal;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.comments = comments;
		this.jrnlmemo = jrnlmemo;
		this.paidtodate = paidtodate;
		this.transid = transid;
		this.receiptnum = receiptnum;
		this.groupnum = groupnum;
		this.confirmed = confirmed;
		this.createtran = createtran;
		this.series = series;
		this.taxdate = taxdate;
		this.filler = filler;
		this.rounddif = rounddif;
		this.rounding = rounding;
		this.canceldate = canceldate;
		this.peymethod = peymethod;
		this.ctlaccount = ctlaccount;
		this.bplname = bplname;
		this.vatregnum = vatregnum;
		this.paidsum = paidsum;
		this.towhscode = towhscode;
		this.nret = nret;
		this.namenp = namenp;
		this.quedan = quedan;
		this.fechreciva = fechreciva;
		this.fquedan = fquedan;
		this.usersign = usersign;
		this.createdate = createdate;
		this.createtime = createtime;
		this.clientDetails = clientDetails;
	}

	public int getDocentry() {
		return docentry;
	}
	public void setDocentry(int docentry) {
		this.docentry = docentry;
	}
	public int getDocnum() {
		return docnum;
	}
	public void setDocnum(int docnum) {
		this.docnum = docnum;
	}
	public String getDoctype() {
		return doctype;
	}
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}
	public String getCanceled() {
		return canceled;
	}
	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}
	public String getDocstatus() {
		return docstatus;
	}
	public void setDocstatus(String docstatus) {
		this.docstatus = docstatus;
	}
	public String getObjtype() {
		return objtype;
	}
	public void setObjtype(String objtype) {
		this.objtype = objtype;
	}
	public Date getDocdate() {
		return docdate;
	}
	public void setDocdate(Date docdate) {
		this.docdate = docdate;
	}
	public Date getDocduedate() {
		return docduedate;
	}
	public void setDocduedate(Date docduedate) {
		this.docduedate = docduedate;
	}
	public String getCardcode() {
		return cardcode;
	}
	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}
	public String getNumatcard() {
		return numatcard;
	}
	public void setNumatcard(String numatcard) {
		this.numatcard = numatcard;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public double getVatsum() {
		return vatsum;
	}
	public void setVatsum(double vatsum) {
		this.vatsum = vatsum;
	}
	public Double getDiscsum() {
		return discsum;
	}
	public void setDiscsum(Double discsum) {
		this.discsum = discsum;
	}
	public Double getDoctotal() {
		return doctotal;
	}
	public void setDoctotal(Double doctotal) {
		this.doctotal = doctotal;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getJrnlmemo() {
		return jrnlmemo;
	}
	public void setJrnlmemo(String jrnlmemo) {
		this.jrnlmemo = jrnlmemo;
	}
	public Date getPaidtodate() {
		return paidtodate;
	}
	public void setPaidtodate(Date paidtodate) {
		this.paidtodate = paidtodate;
	}
	public int getTransid() {
		return transid;
	}
	public void setTransid(int transid) {
		this.transid = transid;
	}
	public int getReceiptnum() {
		return receiptnum;
	}
	public void setReceiptnum(int receiptnum) {
		this.receiptnum = receiptnum;
	}
	public int getGroupnum() {
		return groupnum;
	}
	public void setGroupnum(int groupnum) {
		this.groupnum = groupnum;
	}
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
	public String getCreatetran() {
		return createtran;
	}
	public void setCreatetran(String createtran) {
		this.createtran = createtran;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
	public Date getTaxdate() {
		return taxdate;
	}
	public void setTaxdate(Date taxdate) {
		this.taxdate = taxdate;
	}
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	public Double getRounddif() {
		return rounddif;
	}
	public void setRounddif(Double rounddif) {
		this.rounddif = rounddif;
	}
	public String getRounding() {
		return rounding;
	}
	public void setRounding(String rounding) {
		this.rounding = rounding;
	}
	public Date getCanceldate() {
		return canceldate;
	}
	public void setCanceldate(Date canceldate) {
		this.canceldate = canceldate;
	}
	public String getPeymethod() {
		return peymethod;
	}
	public void setPeymethod(String peymethod) {
		this.peymethod = peymethod;
	}
	public String getCtlaccount() {
		return ctlaccount;
	}
	public void setCtlaccount(String ctlaccount) {
		this.ctlaccount = ctlaccount;
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
	public Double getPaidsum() {
		return paidsum;
	}
	public void setPaidsum(Double paidsum) {
		this.paidsum = paidsum;
	}
	public String getTowhscode() {
		return towhscode;
	}
	public void setTowhscode(String towhscode) {
		this.towhscode = towhscode;
	}
	public Double getNret() {
		return nret;
	}
	public void setNret(Double nret) {
		this.nret = nret;
	}
	public String getNamenp() {
		return namenp;
	}
	public void setNamenp(String namenp) {
		this.namenp = namenp;
	}
	public int getQuedan() {
		return quedan;
	}
	public void setQuedan(int quedan) {
		this.quedan = quedan;
	}
	public Date getFechreciva() {
		return fechreciva;
	}
	public void setFechreciva(Date fechreciva) {
		this.fechreciva = fechreciva;
	}
	public Date getFquedan() {
		return fquedan;
	}
	public void setFquedan(Date fquedan) {
		this.fquedan = fquedan;
	}
	public int getUsersign() {
		return usersign;
	}
	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public int getCreatetime() {
		return createtime;
	}
	public void setCreatetime(int createtime) {
		this.createtime = createtime;
	}

	public List getclientDetails() {
		return clientDetails;
	}

	public void setclientDetails(List clientDetails) {
		this.clientDetails = clientDetails;
	}
}

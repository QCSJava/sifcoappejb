package com.sifcoapp.objects.bank.to;

import com.sifcoapp.objects.common.to.CommonTO;

import java.util.Date;
import java.util.List;

public class ColecturiaTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5316194859415981457L;
	private int docentry;
	private int transtype;
	private int docnum;
	private int receiptnum;
	private String printed;
	private Date docdate;
	private Date docduedate;
	private String cardcode;
	private String cardname;
	private String comments;
	private String jrnlmemo;
	private int series;
	private Double doctotal;
	private Date taxdate;
	private String ref1;
	private String ref2;
	private int usersign;
	private List ColecturiaDetail;

	public ColecturiaTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ColecturiaTO(int docentry, int transtype, int docnum,
			int receiptnum, String printed, Date docdate, Date docduedate,
			String cardcode, String cardname, String comments, String jrnlmemo,
			int series, Double doctotal, Date taxdate, String ref1,
			String ref2, int usersign, List colecturiaDetail) {
		super();
		this.docentry = docentry;
		this.transtype = transtype;
		this.docnum = docnum;
		this.receiptnum = receiptnum;
		this.printed = printed;
		this.docdate = docdate;
		this.docduedate = docduedate;
		this.cardcode = cardcode;
		this.cardname = cardname;
		this.comments = comments;
		this.jrnlmemo = jrnlmemo;
		this.series = series;
		this.doctotal = doctotal;
		this.taxdate = taxdate;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.usersign = usersign;
		ColecturiaDetail = colecturiaDetail;
	}

	public int getDocentry() {
		return docentry;
	}

	public void setDocentry(int docentry) {
		this.docentry = docentry;
	}

	public int getTranstype() {
		return transtype;
	}

	public void setTranstype(int transtype) {
		this.transtype = transtype;
	}

	public int getDocnum() {
		return docnum;
	}

	public void setDocnum(int docnum) {
		this.docnum = docnum;
	}

	public int getReceiptnum() {
		return receiptnum;
	}

	public void setReceiptnum(int receiptnum) {
		this.receiptnum = receiptnum;
	}

	public String getPrinted() {
		return printed;
	}

	public void setPrinted(String printed) {
		this.printed = printed;
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

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
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

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public Double getDoctotal() {
		return doctotal;
	}

	public void setDoctotal(Double doctotal) {
		this.doctotal = doctotal;
	}

	public Date getTaxdate() {
		return taxdate;
	}

	public void setTaxdate(Date taxdate) {
		this.taxdate = taxdate;
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

	public int getUsersign() {
		return usersign;
	}

	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}

	public List getColecturiaDetail() {
		return ColecturiaDetail;
	}

	public void setColecturiaDetail(List colecturiaDetail) {
		ColecturiaDetail = colecturiaDetail;
	}

}

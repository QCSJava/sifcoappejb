package com.sifcoapp.objects.sales.to;

import java.util.Date;

import com.sifcoapp.objects.common.to.CommonTO;

public class ClientCrediInTO extends CommonTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6864492502125512921L;
	/**
	 * 
	 */

	private int docnum;
	private Date docdate;
	private Date docduedate;
	private int series;
	private String towhscode;
	private String fromwhscode;
	private String ref1;
	private String ref2;
	private String comments;
	public ClientCrediInTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClientCrediInTO(int docnum, Date docdate, Date docduedate, int series,
			String towhscode, String fromwhscode, String ref1, String ref2,
			String comments) {
		super();
		this.docnum = docnum;
		this.docdate = docdate;
		this.docduedate = docduedate;
		this.series = series;
		this.towhscode = towhscode;
		this.fromwhscode = fromwhscode;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.comments = comments;
	}
	public int getDocnum() {
		return docnum;
	}
	public void setDocnum(int docnum) {
		this.docnum = docnum;
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
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
	public String getTowhscode() {
		return towhscode;
	}
	public void setTowhscode(String towhscode) {
		this.towhscode = towhscode;
	}
	public String getFromwhscode() {
		return fromwhscode;
	}
	public void setFromwhscode(String fromwhscode) {
		this.fromwhscode = fromwhscode;
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
	
}

package com.sifcoapp.objects.inventory.to;

import java.util.Date;

import com.sifcoapp.objects.common.to.CommonTO;

public class TransfersInTO extends CommonTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4731810050423090785L;
	/**
	 * 
	 */
	
	private int docnum;
	private Date docdate;
	private Date docduedate;
	private int series;
	public TransfersInTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransfersInTO(int docnum, Date docdate, Date docduedate, int series) {
		super();
		this.docnum = docnum;
		this.docdate = docdate;
		this.docduedate = docduedate;
		this.series = series;
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
	
	
	
}

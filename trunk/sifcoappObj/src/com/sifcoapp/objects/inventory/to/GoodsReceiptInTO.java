package com.sifcoapp.objects.inventory.to;

import java.util.Date;

import com.sifcoapp.objects.common.to.CommonTO;

public class GoodsReceiptInTO extends CommonTO{
	private int docnum;
	private Date docdate;
	private int series;
	public GoodsReceiptInTO() {
		super();
		// TODO Auto-generated constructor stub
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
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	} 
	
}

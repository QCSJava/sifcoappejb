package com.sifcoapp.objects.inventory.to;

import java.sql.Date;
import java.util.List;

import com.sifcoapp.objects.common.to.CommonTO;

public class GoodsreceiptTO extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int docentry;
	private int docnum;
	private String doctype;
	private String canceled;
	private String docstatus;
	private String objtype;
	private Date docdate;
	private Date docduedate;
	private Double doctotal;
	private String ref1;
	private String comments;
	private String jrnlmemo;
	private int transid;
	private int series;
	private String towhscode;
	private String fromwhscode;
	private String confirmed;
	private int usersign;
	private Date createdate;
	private int createtime;
	private List GoodReceiptDetail;
	
	public GoodsreceiptTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GoodsreceiptTO(int docentry, int docnum, String doctype,
			String canceled, String docstatus, String objtype, Date docdate,
			Date docduedate, Double doctotal, String ref1, String comments,
			String jrnlmemo, int transid, int series, String towhscode,
			String fromwhscode, String confirmed, int usersign,
			Date createdate, int createtime, List goodReceiptDetail) {
		super();
		this.docentry = docentry;
		this.docnum = docnum;
		this.doctype = doctype;
		this.canceled = canceled;
		this.docstatus = docstatus;
		this.objtype = objtype;
		this.docdate = docdate;
		this.docduedate = docduedate;
		this.doctotal = doctotal;
		this.ref1 = ref1;
		this.comments = comments;
		this.jrnlmemo = jrnlmemo;
		this.transid = transid;
		this.series = series;
		this.towhscode = towhscode;
		this.fromwhscode = fromwhscode;
		this.confirmed = confirmed;
		this.usersign = usersign;
		this.createdate = createdate;
		this.createtime = createtime;
		GoodReceiptDetail = goodReceiptDetail;
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
	public int getTransid() {
		return transid;
	}
	public void setTransid(int transid) {
		this.transid = transid;
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
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
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

	public List getGoodReceiptDetail() {
		return GoodReceiptDetail;
	}

	public void setGoodReceiptDetail(List goodReceiptDetail) {
		GoodReceiptDetail = goodReceiptDetail;
	}

}

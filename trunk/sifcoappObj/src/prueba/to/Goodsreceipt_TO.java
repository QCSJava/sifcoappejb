package prueba.to;

import java.sql.Date;

import com.sifcoapp.objects.common.to.CommonTO;

public class Goodsreceipt_TO extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private int docentry; 
	 private int docnum; 
	 private String canceled; 
	 private String docstatus; 
	 private String objtype; 
	 private Date docdate; 
	 private Date docduedate; 
	 private Double doctotal; 
     private String comments; 
	 private String ref2; 
	 private int series; 
	 private String whscode; 
	 private String idmovement; 
	 private int itmsgrpcod; 
	 private Date createddatel; 
	 private String modifiedbyl;
	 private String createdbyl; 
	 private Date modifieddatel;
	 
	 
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getRef2() {
		return ref2;
	}
	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
	public String getWhscode() {
		return whscode;
	}
	public void setWhscode(String whscode) {
		this.whscode = whscode;
	}
	public String getIdmovement() {
		return idmovement;
	}
	public void setIdmovement(String idmovement) {
		this.idmovement = idmovement;
	}
	public int getItmsgrpcod() {
		return itmsgrpcod;
	}
	public void setItmsgrpcod(int itmsgrpcod) {
		this.itmsgrpcod = itmsgrpcod;
	}
	public Date getCreateddatel() {
		return createddatel;
	}
	public void setCreateddatel(Date createddatel) {
		this.createddatel = createddatel;
	}
	public String getModifiedbyl() {
		return modifiedbyl;
	}
	public void setModifiedbyl(String modifiedbyl) {
		this.modifiedbyl = modifiedbyl;
	}
	public String getCreatedbyl() {
		return createdbyl;
	}
	public void setCreatedbyl(String createdbyl) {
		this.createdbyl = createdbyl;
	}
	public Date getModifieddatel() {
		return modifieddatel;
	}
	public void setModifieddatel(Date modifieddatel) {
		this.modifieddatel = modifieddatel;
	}
	 
	 
	

}

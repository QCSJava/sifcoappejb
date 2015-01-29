package com.sifcoapp.objects.sales.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class SalesDetailTO extends CommonTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7958579106966310270L;
	private int docentry;
	private int linenum;
	private String itemcode;
	private String dscription;
	private Double quantity;
	private Double price;
	private Double linetotal;
	private String whscode;
	private String acctcode;
	private String ocrcode;
	private String taxstatus;
	private String taxcode;
	public SalesDetailTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SalesDetailTO(int docentry, int linenum, String itemcode,
			String dscription, Double quantity, Double price, Double linetotal,
			String whscode, String acctcode, String ocrcode, String taxstatus,
			String taxcode) {
		super();
		this.docentry = docentry;
		this.linenum = linenum;
		this.itemcode = itemcode;
		this.dscription = dscription;
		this.quantity = quantity;
		this.price = price;
		this.linetotal = linetotal;
		this.whscode = whscode;
		this.acctcode = acctcode;
		this.ocrcode = ocrcode;
		this.taxstatus = taxstatus;
		this.taxcode = taxcode;
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
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getDscription() {
		return dscription;
	}
	public void setDscription(String dscription) {
		this.dscription = dscription;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getLinetotal() {
		return linetotal;
	}
	public void setLinetotal(Double linetotal) {
		this.linetotal = linetotal;
	}
	public String getWhscode() {
		return whscode;
	}
	public void setWhscode(String whscode) {
		this.whscode = whscode;
	}
	public String getAcctcode() {
		return acctcode;
	}
	public void setAcctcode(String acctcode) {
		this.acctcode = acctcode;
	}
	public String getOcrcode() {
		return ocrcode;
	}
	public void setOcrcode(String ocrcode) {
		this.ocrcode = ocrcode;
	}
	public String getTaxstatus() {
		return taxstatus;
	}
	public void setTaxstatus(String taxstatus) {
		this.taxstatus = taxstatus;
	}
	public String getTaxcode() {
		return taxcode;
	}
	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}


}

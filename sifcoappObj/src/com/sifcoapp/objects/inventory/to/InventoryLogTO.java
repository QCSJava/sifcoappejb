package com.sifcoapp.objects.inventory.to;

import java.util.Date;

import com.sifcoapp.objects.common.to.CommonTO;

public class InventoryLogTO extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1896272146139683992L;

	private int messageid;
	private int docentry;
	private int transtype;
	private int doclinenum;
	private Double quantity;
	private Double effectqty;
	private int loctype;
	private String loccode;
	private Double totallc;
	private int baseabsent;
	private int basetype;
	private int accumtype;
	private int actiontype;
	private Double expenseslc;
	private Date docduedate;
	private String itemcode;
	private String bpcardcode;
	private Date docdate;
	private String comment;
	private String jrnlmemo;
	private String ref1;
	private String ref2;
	private int baseline;
	private int snbtype;
	private String ocrcode;
	private String ocrcode2;
	private String ocrcode3;
	private String cardname;
	private String dscription;
	private String base_ref;
	private Double pricerate;
	private Double doctotal;
	private Double price;
	private Date taxdate;
	private int usersign;
	private Date createdate;
	private int createtime;
	public InventoryLogTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InventoryLogTO(int messageid, int docentry, int transtype,
			int doclinenum, Double quantity, Double effectqty, int loctype,
			String loccode, Double totallc, int baseabsent, int basetype,
			int accumtype, int actiontype, Double expenseslc, Date docduedate,
			String itemcode, String bpcardcode, Date docdate, String comment,
			String jrnlmemo, String ref1, String ref2, int baseline,
			int snbtype, String ocrcode, String ocrcode2, String ocrcode3,
			String cardname, String dscription, String base_ref,
			Double pricerate, Double doctotal, Double price, Date taxdate,
			int usersign, Date createdate, int createtime) {
		super();
		this.messageid = messageid;
		this.docentry = docentry;
		this.transtype = transtype;
		this.doclinenum = doclinenum;
		this.quantity = quantity;
		this.effectqty = effectqty;
		this.loctype = loctype;
		this.loccode = loccode;
		this.totallc = totallc;
		this.baseabsent = baseabsent;
		this.basetype = basetype;
		this.accumtype = accumtype;
		this.actiontype = actiontype;
		this.expenseslc = expenseslc;
		this.docduedate = docduedate;
		this.itemcode = itemcode;
		this.bpcardcode = bpcardcode;
		this.docdate = docdate;
		this.comment = comment;
		this.jrnlmemo = jrnlmemo;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.baseline = baseline;
		this.snbtype = snbtype;
		this.ocrcode = ocrcode;
		this.ocrcode2 = ocrcode2;
		this.ocrcode3 = ocrcode3;
		this.cardname = cardname;
		this.dscription = dscription;
		this.base_ref = base_ref;
		this.pricerate = pricerate;
		this.doctotal = doctotal;
		this.price = price;
		this.taxdate = taxdate;
		this.usersign = usersign;
		this.createdate = createdate;
		this.createtime = createtime;
	}
	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
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
	public int getDoclinenum() {
		return doclinenum;
	}
	public void setDoclinenum(int doclinenum) {
		this.doclinenum = doclinenum;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getEffectqty() {
		return effectqty;
	}
	public void setEffectqty(Double effectqty) {
		this.effectqty = effectqty;
	}
	public int getLoctype() {
		return loctype;
	}
	public void setLoctype(int loctype) {
		this.loctype = loctype;
	}
	public String getLoccode() {
		return loccode;
	}
	public void setLoccode(String loccode) {
		this.loccode = loccode;
	}
	public Double getTotallc() {
		return totallc;
	}
	public void setTotallc(Double totallc) {
		this.totallc = totallc;
	}
	public int getBaseabsent() {
		return baseabsent;
	}
	public void setBaseabsent(int baseabsent) {
		this.baseabsent = baseabsent;
	}
	public int getBasetype() {
		return basetype;
	}
	public void setBasetype(int basetype) {
		this.basetype = basetype;
	}
	public int getAccumtype() {
		return accumtype;
	}
	public void setAccumtype(int accumtype) {
		this.accumtype = accumtype;
	}
	public int getActiontype() {
		return actiontype;
	}
	public void setActiontype(int actiontype) {
		this.actiontype = actiontype;
	}
	public Double getExpenseslc() {
		return expenseslc;
	}
	public void setExpenseslc(Double expenseslc) {
		this.expenseslc = expenseslc;
	}
	public Date getDocduedate() {
		return docduedate;
	}
	public void setDocduedate(Date docduedate) {
		this.docduedate = docduedate;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getBpcardcode() {
		return bpcardcode;
	}
	public void setBpcardcode(String bpcardcode) {
		this.bpcardcode = bpcardcode;
	}
	public Date getDocdate() {
		return docdate;
	}
	public void setDocdate(Date docdate) {
		this.docdate = docdate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getJrnlmemo() {
		return jrnlmemo;
	}
	public void setJrnlmemo(String jrnlmemo) {
		this.jrnlmemo = jrnlmemo;
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
	public int getBaseline() {
		return baseline;
	}
	public void setBaseline(int baseline) {
		this.baseline = baseline;
	}
	public int getSnbtype() {
		return snbtype;
	}
	public void setSnbtype(int snbtype) {
		this.snbtype = snbtype;
	}
	public String getOcrcode() {
		return ocrcode;
	}
	public void setOcrcode(String ocrcode) {
		this.ocrcode = ocrcode;
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
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getDscription() {
		return dscription;
	}
	public void setDscription(String dscription) {
		this.dscription = dscription;
	}
	public String getBase_ref() {
		return base_ref;
	}
	public void setBase_ref(String base_ref) {
		this.base_ref = base_ref;
	}
	public Double getPricerate() {
		return pricerate;
	}
	public void setPricerate(Double pricerate) {
		this.pricerate = pricerate;
	}
	public Double getDoctotal() {
		return doctotal;
	}
	public void setDoctotal(Double doctotal) {
		this.doctotal = doctotal;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getTaxdate() {
		return taxdate;
	}
	public void setTaxdate(Date taxdate) {
		this.taxdate = taxdate;
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

}

package com.sifcoapp.objects.transaction.to;

import java.util.Date;

import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.common.to.CommonTO;

public class TransactionTo extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2058667635374192845L;

	private int transseq;
	private int docentry;
	private String docnum;
	private Date docduedate;
	private Date docdate;
	private String comment;
	private String jrnlmemo;
	private int usersign;
	private String ref1;
	private String ref2;
	private int linenum;
	private String itemcode;
	private String dscription;
	private Double quantity;
	private Double price;
	private Double linetotal;
	private String whscode;
	private String acctcode;
	private String cardname;
	private String ocrcode;
	private String vatgroup;
	private Double priceafvat;
	private Double vatsum;
	private String objtype;
	private Double grssprofit;
	private String taxcode;
	private Double vatappld;
	private Double stockprice;
	private Double gtotal;
	private Double inqty;
	private Double outqty;
	private int messageid;
	private Double balance;
	private Double newOnhand;
	private Double newWhsOnhand;
	private Double newAvgprice;
	private String invntact;
	private ArticlesTO article;

	public TransactionTo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTransseq() {
		return transseq;
	}

	public void setTransseq(int transseq) {
		this.transseq = transseq;
	}

	public int getDocentry() {
		return docentry;
	}

	public void setDocentry(int docentry) {
		this.docentry = docentry;
	}

	public String getDocnum() {
		return docnum;
	}

	public void setDocnum(String docnum) {
		this.docnum = docnum;
	}

	public Date getDocduedate() {
		return docduedate;
	}

	public void setDocduedate(Date docduedate) {
		this.docduedate = docduedate;
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

	public int getUsersign() {
		return usersign;
	}

	public void setUsersign(int usersign) {
		this.usersign = usersign;
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

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public String getOcrcode() {
		return ocrcode;
	}

	public void setOcrcode(String ocrcode) {
		this.ocrcode = ocrcode;
	}

	public String getVatgroup() {
		return vatgroup;
	}

	public void setVatgroup(String vatgroup) {
		this.vatgroup = vatgroup;
	}

	public Double getPriceafvat() {
		return priceafvat;
	}

	public void setPriceafvat(Double priceafvat) {
		this.priceafvat = priceafvat;
	}

	public Double getVatsum() {
		return vatsum;
	}

	public void setVatsum(Double vatsum) {
		this.vatsum = vatsum;
	}

	public String getObjtype() {
		return objtype;
	}

	public void setObjtype(String objtype) {
		this.objtype = objtype;
	}

	public Double getGrssprofit() {
		return grssprofit;
	}

	public void setGrssprofit(Double grssprofit) {
		this.grssprofit = grssprofit;
	}

	public String getTaxcode() {
		return taxcode;
	}

	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}

	public Double getVatappld() {
		return vatappld;
	}

	public void setVatappld(Double vatappld) {
		this.vatappld = vatappld;
	}

	public Double getStockprice() {
		return stockprice;
	}

	public void setStockprice(Double stockprice) {
		this.stockprice = stockprice;
	}

	public Double getGtotal() {
		return gtotal;
	}

	public void setGtotal(Double gtotal) {
		this.gtotal = gtotal;
	}

	public Double getInqty() {
		return inqty;
	}

	public void setInqty(Double inqty) {
		this.inqty = inqty;
	}

	public Double getOutqty() {
		return outqty;
	}

	public void setOutqty(Double outqty) {
		this.outqty = outqty;
	}

	public int getMessageid() {
		return messageid;
	}

	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getNewOnhand() {
		return newOnhand;
	}

	public void setNewOnhand(Double newOnhand) {
		this.newOnhand = newOnhand;
	}

	public Double getNewWhsOnhand() {
		return newWhsOnhand;
	}

	public void setNewWhsOnhand(Double newWhsOnhand) {
		this.newWhsOnhand = newWhsOnhand;
	}

	public Double getNewAvgprice() {
		return newAvgprice;
	}

	public void setNewAvgprice(Double newAvgprice) {
		this.newAvgprice = newAvgprice;
	}

	public String getInvntact() {
		return invntact;
	}

	public void setInvntact(String invntact) {
		this.invntact = invntact;
	}

	public ArticlesTO getArticle() {
		return article;
	}

	public void setArticle(ArticlesTO article) {
		this.article = article;
	}

}

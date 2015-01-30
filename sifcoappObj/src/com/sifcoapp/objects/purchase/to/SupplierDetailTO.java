package com.sifcoapp.objects.purchase.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class SupplierDetailTO extends CommonTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9061562316558382609L;
	/**
	 * 
	 */
	private int docentry;
	private int linenum;
	private int targettype;
	private String baseref;
	private int basetype;
	private int baseentry;
	private int baseline;
	private String linestatus;
	private String itemcode;
	private String dscription;
	private Double quantity;
	private Double openqty;
	private Double price;
	private Double discprcnt;
	private Double linetotal;
	private String whscode;
	private String acctcode;
	private String taxstatus;
	private Double pricebefdi;
	private String ocrcode;
	private String vatgroup;
	private Double priceafvat;
	private Double factor1;
	private Double vatsum;
	private String objtype;
	private Double grssprofit;
	private String taxcode;
	private Double vatappld;
	private String unitmsr;
	private Double stockpricestockprice;
	private Double gtotal;
	
	public SupplierDetailTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupplierDetailTO(int docentry, int linenum, int targettype,
			String baseref, int basetype, int baseentry, int baseline,
			String linestatus, String itemcode, String dscription,
			Double quantity, Double openqty, Double price, Double discprcnt,
			Double linetotal, String whscode, String acctcode,
			String taxstatus, Double pricebefdi, String ocrcode,
			String vatgroup, Double priceafvat, Double factor1, Double vatsum,
			String objtype, Double grssprofit, String taxcode, Double vatappld,
			String unitmsr, Double stockpricestockprice, Double gtotal) {
		super();
		this.docentry = docentry;
		this.linenum = linenum;
		this.targettype = targettype;
		this.baseref = baseref;
		this.basetype = basetype;
		this.baseentry = baseentry;
		this.baseline = baseline;
		this.linestatus = linestatus;
		this.itemcode = itemcode;
		this.dscription = dscription;
		this.quantity = quantity;
		this.openqty = openqty;
		this.price = price;
		this.discprcnt = discprcnt;
		this.linetotal = linetotal;
		this.whscode = whscode;
		this.acctcode = acctcode;
		this.taxstatus = taxstatus;
		this.pricebefdi = pricebefdi;
		this.ocrcode = ocrcode;
		this.vatgroup = vatgroup;
		this.priceafvat = priceafvat;
		this.factor1 = factor1;
		this.vatsum = vatsum;
		this.objtype = objtype;
		this.grssprofit = grssprofit;
		this.taxcode = taxcode;
		this.vatappld = vatappld;
		this.unitmsr = unitmsr;
		this.stockpricestockprice = stockpricestockprice;
		this.gtotal = gtotal;
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

	public int getTargettype() {
		return targettype;
	}

	public void setTargettype(int targettype) {
		this.targettype = targettype;
	}

	public String getBaseref() {
		return baseref;
	}

	public void setBaseref(String baseref) {
		this.baseref = baseref;
	}

	public int getBasetype() {
		return basetype;
	}

	public void setBasetype(int basetype) {
		this.basetype = basetype;
	}

	public int getBaseentry() {
		return baseentry;
	}

	public void setBaseentry(int baseentry) {
		this.baseentry = baseentry;
	}

	public int getBaseline() {
		return baseline;
	}

	public void setBaseline(int baseline) {
		this.baseline = baseline;
	}

	public String getLinestatus() {
		return linestatus;
	}

	public void setLinestatus(String linestatus) {
		this.linestatus = linestatus;
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

	public Double getOpenqty() {
		return openqty;
	}

	public void setOpenqty(Double openqty) {
		this.openqty = openqty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscprcnt() {
		return discprcnt;
	}

	public void setDiscprcnt(Double discprcnt) {
		this.discprcnt = discprcnt;
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

	public String getTaxstatus() {
		return taxstatus;
	}

	public void setTaxstatus(String taxstatus) {
		this.taxstatus = taxstatus;
	}

	public Double getPricebefdi() {
		return pricebefdi;
	}

	public void setPricebefdi(Double pricebefdi) {
		this.pricebefdi = pricebefdi;
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

	public Double getFactor1() {
		return factor1;
	}

	public void setFactor1(Double factor1) {
		this.factor1 = factor1;
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

	public String getUnitmsr() {
		return unitmsr;
	}

	public void setUnitmsr(String unitmsr) {
		this.unitmsr = unitmsr;
	}

	public Double getStockpricestockprice() {
		return stockpricestockprice;
	}

	public void setStockpricestockprice(Double stockpricestockprice) {
		this.stockpricestockprice = stockpricestockprice;
	}

	public Double getGtotal() {
		return gtotal;
	}

	public void setGtotal(Double gtotal) {
		this.gtotal = gtotal;
	}

}

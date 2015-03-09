package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

import java.util.Date;
import java.util.List;

public class ArticlesPriceTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3045349286567836074L;

	private String itemcode;
	private String dscription;
	private int pricelist;
	private Double price;
	private boolean ovrwritten;
	private Double factor;
	private String objtype;
	private Double addprice1;
	private Double addprice2;
	private String ovrwrite1;
	private String ovrwrite2;

	public ArticlesPriceTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticlesPriceTO(String itemcode, int pricelist, Double price,
			boolean ovrwritten, Double factor, String objtype, Double addprice1,
			Double addprice2, String ovrwrite1, String ovrwrite2) {
		super();
		this.itemcode = itemcode;
		this.pricelist = pricelist;
		this.price = price;
		this.ovrwritten = ovrwritten;
		this.factor = factor;
		this.objtype = objtype;
		this.addprice1 = addprice1;
		this.addprice2 = addprice2;
		this.ovrwrite1 = ovrwrite1;
		this.ovrwrite2 = ovrwrite2;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public int getPricelist() {
		return pricelist;
	}

	public void setPricelist(int pricelist) {
		this.pricelist = pricelist;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean getOvrwritten() {
		return ovrwritten;
	}

	public void setOvrwritten(boolean ovrwritten) {
		this.ovrwritten = ovrwritten;
	}

	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public String getObjtype() {
		return objtype;
	}

	public void setObjtype(String objtype) {
		this.objtype = objtype;
	}

	public Double getAddprice1() {
		return addprice1;
	}

	public void setAddprice1(Double addprice1) {
		this.addprice1 = addprice1;
	}

	public Double getAddprice2() {
		return addprice2;
	}

	public void setAddprice2(Double addprice2) {
		this.addprice2 = addprice2;
	}

	public String getOvrwrite1() {
		return ovrwrite1;
	}

	public void setOvrwrite1(String ovrwrite1) {
		this.ovrwrite1 = ovrwrite1;
	}

	public String getOvrwrite2() {
		return ovrwrite2;
	}

	public void setOvrwrite2(String ovrwrite2) {
		this.ovrwrite2 = ovrwrite2;
	}

	public String getDscription() {
		return dscription;
	}

	public void setDscription(String dscription) {
		this.dscription = dscription;
	}
	
	

}

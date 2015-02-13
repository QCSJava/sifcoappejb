package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

import java.util.Date;
import java.util.List;

public class ArticlesInTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4867596358661498571L;
	/**
	 * 
	 */
	private String itemCode;
	private String itemName;
	private String itemType;
	private String itmsIsGrpCod;
	private String vatLiable;
	private String codeBars;
	private String prchseItem;
	private String sellItem;
	private String invntItem;
	private String assetItem;
	private String cardCode;
	private String buyUnitMsr;
	private double numInBuy;
	private String salUnitMsr;
	private double salPackUn;
	private String suppCatNum;
	private double purPackUn;
	private double avgPrice;
	private double onHand;
	private String validFor;
	private Date validFrom;
	private Date validTo;
	private String invntryUom;
	private double numInSale;
	private String dfltWH;
	private String wtliable;
	private String sww;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItmsIsGrpCod() {
		return itmsIsGrpCod;
	}

	public void setItmsIsGrpCod(String itmsIsGrpCod) {
		this.itmsIsGrpCod = itmsIsGrpCod;
	}

	public String getVatLiable() {
		return vatLiable;
	}

	public void setVatLiable(String vatLiable) {
		this.vatLiable = vatLiable;
	}

	public String getCodeBars() {
		return codeBars;
	}

	public void setCodeBars(String codeBars) {
		this.codeBars = codeBars;
	}

	public String getPrchseItem() {
		return prchseItem;
	}

	public void setPrchseItem(String prchseItem) {
		this.prchseItem = prchseItem;
	}

	public String getSellItem() {
		return sellItem;
	}

	public void setSellItem(String sellItem) {
		this.sellItem = sellItem;
	}

	public String getInvntItem() {
		return invntItem;
	}

	public void setInvntItem(String invntItem) {
		this.invntItem = invntItem;
	}

	public String getAssetItem() {
		return assetItem;
	}

	public void setAssetItem(String assetItem) {
		this.assetItem = assetItem;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getBuyUnitMsr() {
		return buyUnitMsr;
	}

	public void setBuyUnitMsr(String buyUnitMsr) {
		this.buyUnitMsr = buyUnitMsr;
	}

	public double getNumInBuy() {
		return numInBuy;
	}

	public void setNumInBuy(double numInBuy) {
		this.numInBuy = numInBuy;
	}

	public String getSalUnitMsr() {
		return salUnitMsr;
	}

	public void setSalUnitMsr(String salUnitMsr) {
		this.salUnitMsr = salUnitMsr;
	}

	public double getSalPackUn() {
		return salPackUn;
	}

	public void setSalPackUn(double salPackUn) {
		this.salPackUn = salPackUn;
	}

	public String getSuppCatNum() {
		return suppCatNum;
	}

	public void setSuppCatNum(String suppCatNum) {
		this.suppCatNum = suppCatNum;
	}

	public double getPurPackUn() {
		return purPackUn;
	}

	public void setPurPackUn(double purPackUn) {
		this.purPackUn = purPackUn;
	}

	public double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public double getOnHand() {
		return onHand;
	}

	public void setOnHand(double onHand) {
		this.onHand = onHand;
	}

	public String getValidFor() {
		return validFor;
	}

	public void setValidFor(String validFor) {
		this.validFor = validFor;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public String getInvntryUom() {
		return invntryUom;
	}

	public void setInvntryUom(String invntryUom) {
		this.invntryUom = invntryUom;
	}

	public double getNumInSale() {
		return numInSale;
	}

	public void setNumInSale(double numInSale) {
		this.numInSale = numInSale;
	}

	public String getDfltWH() {
		return dfltWH;
	}

	public void setDfltWH(String dfltWH) {
		this.dfltWH = dfltWH;
	}

	public String getWtliable() {
		return wtliable;
	}

	public void setWtliable(String wtliable) {
		this.wtliable = wtliable;
	}

	public String getSww() {
		return sww;
	}

	public void setSww(String sww) {
		this.sww = sww;
	}

	public ArticlesInTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticlesInTO(String itemCode, String itemName, String itemType,
			String itmsIsGrpCod, String vatLiable, String codeBars,
			String prchseItem, String sellItem, String invntItem,
			String assetItem, String cardCode, String buyUnitMsr,
			double numInBuy, String salUnitMsr, double salPackUn,
			String suppCatNum, double purPackUn, double avgPrice,
			double onHand, String validFor, Date validFrom, Date validTo,
			String invntryUom, double numInSale, String dfltWH,
			String wtliable, String sww) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemType = itemType;
		this.itmsIsGrpCod = itmsIsGrpCod;
		this.vatLiable = vatLiable;
		this.codeBars = codeBars;
		this.prchseItem = prchseItem;
		this.sellItem = sellItem;
		this.invntItem = invntItem;
		this.assetItem = assetItem;
		this.cardCode = cardCode;
		this.buyUnitMsr = buyUnitMsr;
		this.numInBuy = numInBuy;
		this.salUnitMsr = salUnitMsr;
		this.salPackUn = salPackUn;
		this.suppCatNum = suppCatNum;
		this.purPackUn = purPackUn;
		this.avgPrice = avgPrice;
		this.onHand = onHand;
		this.validFor = validFor;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.invntryUom = invntryUom;
		this.numInSale = numInSale;
		this.dfltWH = dfltWH;
		this.wtliable = wtliable;
		this.sww = sww;
	}

}

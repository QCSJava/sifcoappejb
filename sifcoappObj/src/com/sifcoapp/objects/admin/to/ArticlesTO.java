package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;
import java.sql.Date;

public class ArticlesTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1704344823641333854L;

	private String itemCode;
	private String itemName;
	private String itemType;
	private int itmsGrpCod;
	private String vatLiable;
	private String codeBars;
	private String prchseItem;
	private String sellItem;
	private String invntItem;
	private String assetItem;
	private String cardCode;
	private String buyUnitMsr;
	private Double numInBuy;
	private String salUnitMsr;
	private Double salPackUn;
	private String suppCatNum;
	private Double purPackUn;
	private Double avgPrice;
	private Double onHand;
	private String validFor;
	private Date validFrom;
	private Date validTo;
	private String invntryUom;
	private Double numInSale;
	private String dfltWH;
	private String wtliable;
	private String sww;
	private String validComm;
	private Integer userSign;
	private BranchArticlesTO[] branchArticles;

	public ArticlesTO(String itemCode, String itemName, String itemType,
			int itmsGrpCod, String vatLiable, String codeBars,
			String prchseItem, String sellItem, String invntItem,
			String assetItem, String cardCode, String buyUnitMsr,
			Double numInBuy, String salUnitMsr, Double salPackUn,
			String suppCatNum, Double purPackUn, Double avgPrice,
			Double onHand, String validFor, Date validFrom, Date validTo,
			String invntryUom, Double numInSale, String dfltWH,
			String wtliable, String sww, String validComm, Integer userSign,
			BranchArticlesTO[] branchArticles) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemType = itemType;
		this.itmsGrpCod = itmsGrpCod;
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
		this.validComm = validComm;
		this.userSign = userSign;
		this.branchArticles = branchArticles;
	}

	public ArticlesTO() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public int getItmsGrpCod() {
		return itmsGrpCod;
	}

	public void setItmsGrpCod(int itmsGrpCod) {
		this.itmsGrpCod = itmsGrpCod;
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

	public Double getNumInBuy() {
		return numInBuy;
	}

	public void setNumInBuy(Double numInBuy) {
		this.numInBuy = numInBuy;
	}

	public String getSalUnitMsr() {
		return salUnitMsr;
	}

	public void setSalUnitMsr(String salUnitMsr) {
		this.salUnitMsr = salUnitMsr;
	}

	public Double getSalPackUn() {
		return salPackUn;
	}

	public void setSalPackUn(Double salPackUn) {
		this.salPackUn = salPackUn;
	}

	public String getSuppCatNum() {
		return suppCatNum;
	}

	public void setSuppCatNum(String suppCatNum) {
		this.suppCatNum = suppCatNum;
	}

	public Double getPurPackUn() {
		return purPackUn;
	}

	public void setPurPackUn(Double purPackUn) {
		this.purPackUn = purPackUn;
	}

	public Double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public Double getOnHand() {
		return onHand;
	}

	public void setOnHand(Double onHand) {
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

	public Double getNumInSale() {
		return numInSale;
	}

	public void setNumInSale(Double numInSale) {
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

	public String getValidComm() {
		return validComm;
	}

	public void setValidComm(String validComm) {
		this.validComm = validComm;
	}

	public Integer getUserSign() {
		return userSign;
	}

	public void setUserSign(Integer userSign) {
		this.userSign = userSign;
	}

	public BranchArticlesTO[] getBranchArticles() {
		return branchArticles;
	}

	public void setBranchArticles(BranchArticlesTO[] branchArticles) {
		this.branchArticles = branchArticles;
	}
}

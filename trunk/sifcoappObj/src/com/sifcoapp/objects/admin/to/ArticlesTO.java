package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ArticlesTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1704344823641333854L;

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
	private String vatgourpsa;
	private String dfltWH;
	private String wtliable;
	private String sww;
	private String validComm;
	private int userSign;
	private String qrygroup1;
	private String qrygroup2;
	private String qrygroup3;
	private String qrygroup4;
	private String qrygroup5;
	private String qrygroup6;
	private String qrygroup7;
	private String qrygroup8;
	private String qrygroup9;
	private String qrygroup10;

	private List branchArticles;
	private List Articleprices;
	private List vatgourpsaList;
	private double price;

	public ArticlesTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticlesTO(String itemCode, String itemName, String itemType,
			String itmsIsGrpCod, String vatLiable, String codeBars,
			String prchseItem, String sellItem, String invntItem,
			String assetItem, String cardCode, String buyUnitMsr,
			double numInBuy, String salUnitMsr, double salPackUn,
			String suppCatNum, double purPackUn, double avgPrice,
			double onHand, String validFor, Date validFrom, Date validTo,
			String invntryUom, double numInSale, String vatgourpsa,
			String dfltWH, String wtliable, String sww, String validComm,
			int userSign, String qrygroup1, String qrygroup2, String qrygroup3,
			String qrygroup4, String qrygroup5, String qrygroup6,
			String qrygroup7, String qrygroup8, String qrygroup9,
			String qrygroup10, List branchArticles, List articleprices,
			List vatgourpsaList) {
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
		this.vatgourpsa = vatgourpsa;
		this.dfltWH = dfltWH;
		this.wtliable = wtliable;
		this.sww = sww;
		this.validComm = validComm;
		this.userSign = userSign;
		this.qrygroup1 = qrygroup1;
		this.qrygroup2 = qrygroup2;
		this.qrygroup3 = qrygroup3;
		this.qrygroup4 = qrygroup4;
		this.qrygroup5 = qrygroup5;
		this.qrygroup6 = qrygroup6;
		this.qrygroup7 = qrygroup7;
		this.qrygroup8 = qrygroup8;
		this.qrygroup9 = qrygroup9;
		this.qrygroup10 = qrygroup10;
		this.branchArticles = branchArticles;
		Articleprices = articleprices;
		this.vatgourpsaList = vatgourpsaList;
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

	public String getVatgourpsa() {
		return vatgourpsa;
	}

	public void setVatgourpsa(String vatgourpsa) {
		this.vatgourpsa = vatgourpsa;
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

	public int getUserSign() {
		return userSign;
	}

	public void setUserSign(int userSign) {
		this.userSign = userSign;
	}

	public String getQrygroup1() {
		return qrygroup1;
	}

	public void setQrygroup1(String qrygroup1) {
		this.qrygroup1 = qrygroup1;
	}

	public String getQrygroup2() {
		return qrygroup2;
	}

	public void setQrygroup2(String qrygroup2) {
		this.qrygroup2 = qrygroup2;
	}

	public String getQrygroup3() {
		return qrygroup3;
	}

	public void setQrygroup3(String qrygroup3) {
		this.qrygroup3 = qrygroup3;
	}

	public String getQrygroup4() {
		return qrygroup4;
	}

	public void setQrygroup4(String qrygroup4) {
		this.qrygroup4 = qrygroup4;
	}

	public String getQrygroup5() {
		return qrygroup5;
	}

	public void setQrygroup5(String qrygroup5) {
		this.qrygroup5 = qrygroup5;
	}

	public String getQrygroup6() {
		return qrygroup6;
	}

	public void setQrygroup6(String qrygroup6) {
		this.qrygroup6 = qrygroup6;
	}

	public String getQrygroup7() {
		return qrygroup7;
	}

	public void setQrygroup7(String qrygroup7) {
		this.qrygroup7 = qrygroup7;
	}

	public String getQrygroup8() {
		return qrygroup8;
	}

	public void setQrygroup8(String qrygroup8) {
		this.qrygroup8 = qrygroup8;
	}

	public String getQrygroup9() {
		return qrygroup9;
	}

	public void setQrygroup9(String qrygroup9) {
		this.qrygroup9 = qrygroup9;
	}

	public String getQrygroup10() {
		return qrygroup10;
	}

	public void setQrygroup10(String qrygroup10) {
		this.qrygroup10 = qrygroup10;
	}

	public List getBranchArticles() {
		return branchArticles;
	}

	public void setBranchArticles(List branchArticles) {
		this.branchArticles = branchArticles;
	}

	public List getArticleprices() {
		return Articleprices;
	}

	public void setArticleprices(List articleprices) {
		Articleprices = articleprices;
	}

	public List getVatgourpsaList() {
		return vatgourpsaList;
	}

	public void setVatgourpsaList(List vatgourpsaList) {
		this.vatgourpsaList = vatgourpsaList;
	}

	public double getPrice(int selectedListPrice ) {
		Iterator<ArticlesPriceTO> iterator = this.getArticleprices().iterator();
		this.price = -1.0;

		while (iterator.hasNext()) {
			ArticlesPriceTO prices = (ArticlesPriceTO) iterator.next();
			if (prices.getPricelist() == selectedListPrice) {
				this.price = prices.getPrice();
			}
		}

		return price;
	}
}

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
	private String prchseItem;
	private String sellItem;
	private String invntItem;
	private String assetItem;
	
	public ArticlesInTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticlesInTO(String itemCode, String itemName, String itemType,
			String itmsIsGrpCod, String prchseItem, String sellItem,
			String invntItem, String assetItem) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemType = itemType;
		this.itmsIsGrpCod = itmsIsGrpCod;
		this.prchseItem = prchseItem;
		this.sellItem = sellItem;
		this.invntItem = invntItem;
		this.assetItem = assetItem;
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

	

}

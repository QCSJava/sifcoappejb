package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class BranchArticles extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8218947564758845053L;

	private BranchTO branch;
	private String itemcode;
	private String whscode;
	private Double onhand;
	private Double onhand1;
	private Double iscommited;
	private Double onorder;
	private Double minstock;
	private Double maxstock;
	public BranchTO getBranch() {
		return branch;
	}
	public void setBranch(BranchTO branch) {
		this.branch = branch;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getWhscode() {
		return whscode;
	}
	public void setWhscode(String whscode) {
		this.whscode = whscode;
	}
	public Double getOnhand() {
		return onhand;
	}
	public void setOnhand(Double onhand) {
		this.onhand = onhand;
	}
	public Double getOnhand1() {
		return onhand1;
	}
	public void setOnhand1(Double onhand1) {
		this.onhand1 = onhand1;
	}
	public Double getIscommited() {
		return iscommited;
	}
	public void setIscommited(Double iscommited) {
		this.iscommited = iscommited;
	}
	public Double getOnorder() {
		return onorder;
	}
	public void setOnorder(Double onorder) {
		this.onorder = onorder;
	}
	public Double getMinstock() {
		return minstock;
	}
	public void setMinstock(Double minstock) {
		this.minstock = minstock;
	}
	public Double getMaxstock() {
		return maxstock;
	}
	public void setMaxstock(Double maxstock) {
		this.maxstock = maxstock;
	}
	public Double getMinorder() {
		return minorder;
	}
	public void setMinorder(Double minorder) {
		this.minorder = minorder;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	private Double minorder;
	private String locked;
	
	
}

package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class BranchArticlesTO extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8218947564758845053L;

	private BranchTO branch;
	private String itemcode;
	private String whscode;
	private String whsname;	
	private Double onhand;
	private Double onhand1;
	private Double iscommited;
	private Double onorder;
	private Double minstock;
	private Double maxstock;
	private Double minorder;
	private String locked;
	private boolean isasociated;
	
	public BranchArticlesTO(){
		this.setBranch(branch);
		this.setWhsname(whsname);
		this.setItemcode(itemcode);
		this.setWhscode(whscode);
		this.setOnhand(onhand);
		this.setOnhand1(onhand1);
		this.setIscommited(iscommited);
		this.setOnorder(onorder);
		this.setMinorder(minorder);
		this.setMaxstock(maxstock);
		this.setMinstock(minstock);
		this.setLocked(locked);		
		this.setIsasociated(isasociated);	
	}
	public BranchArticlesTO(BranchTO branch, String itemcode, String whscode,  String whsname, Double onhand, Double onhand1, Double iscommited, Double onorder, Double minstock, Double maxstock, String locked, boolean isasociated){	
		this.setBranch(branch);
		this.setItemcode(itemcode);
		this.setWhscode(whscode);
		this.setWhsname(whsname);
		this.setOnhand(onhand);
		this.setOnhand1(onhand1);
		this.setIscommited(iscommited);
		this.setOnorder(onorder);
		this.setMinorder(minorder);
		this.setMaxstock(maxstock);
		this.setMinstock(minstock);
		this.setLocked(locked);		
		this.setIsasociated(isasociated);		
	}
	
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
	public String getWhsname() {
		return whsname;
	}
	public void setWhsname(String whsname) {
		this.whsname = whsname;
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
	public boolean isIsasociated() {		
		return isasociated;
	}
	public void setIsasociated(boolean isasociated) {
		this.isasociated = isasociated;
	}
	
	
	
}

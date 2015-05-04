package com.sifcoapp.objects.inventory.to;

import java.util.Date;

public class InventorylogInTo {
	private int transseq;
	public int getTransseq() {
		return transseq;
	}
	public void setTransseq(int transseq) {
		this.transseq = transseq;
	}
	public int getLayerId() {
		return layerId;
	}
	public void setLayerId(int layerId) {
		this.layerId = layerId;
	}
	public int getCalcPrice() {
		return CalcPrice;
	}
	public void setCalcPrice(int calcPrice) {
		CalcPrice = calcPrice;
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
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
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
	public Double getAvgPrice() {
		return AvgPrice;
	}
	public void setAvgPrice(Double avgPrice) {
		AvgPrice = avgPrice;
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
	public Double getTrasvalue() {
		return trasvalue;
	}
	public void setTrasvalue(Double trasvalue) {
		this.trasvalue = trasvalue;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getTotal_quantity() {
		return total_quantity;
	}
	public void setTotal_quantity(Double total_quantity) {
		this.total_quantity = total_quantity;
	}
	public int getUsersign() {
		return usersign;
	}
	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}
	public InventorylogInTo(int transseq, int layerId, int calcPrice,
			int doclinenum, Double quantity, int loctype, String loccode,
			Double totallc, Double balance, String itemcode, String bpcardcode,
			Double avgPrice, String jrnlmemo, String ref1, String ref2,
			int baseline, int snbtype, Double trasvalue, Double total,
			Double total_quantity, int usersign) {
		super();
		this.transseq = transseq;
		this.layerId = layerId;
		CalcPrice = calcPrice;
		this.doclinenum = doclinenum;
		this.quantity = quantity;
		this.loctype = loctype;
		this.loccode = loccode;
		this.totallc = totallc;
		this.balance = balance;
		this.itemcode = itemcode;
		this.bpcardcode = bpcardcode;
		AvgPrice = avgPrice;
		this.jrnlmemo = jrnlmemo;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.baseline = baseline;
		this.snbtype = snbtype;
		this.trasvalue = trasvalue;
		this.total = total;
		this.total_quantity = total_quantity;
		this.usersign = usersign;
	}
	public InventorylogInTo() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int layerId;
	private int CalcPrice;
	private int doclinenum;
	private Double quantity;
	private int loctype;
	private String loccode;
	private Double totallc;
	private Double balance;
	private String itemcode;
	private String bpcardcode;
	private Double AvgPrice;
	private String jrnlmemo;
	private String ref1;
	private String ref2;
	private int baseline;
	private int snbtype;
	private Double trasvalue;
	private Double total;
	private Double total_quantity;
	private int usersign;

}

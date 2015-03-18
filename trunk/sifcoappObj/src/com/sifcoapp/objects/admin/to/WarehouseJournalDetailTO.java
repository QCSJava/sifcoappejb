package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class WarehouseJournalDetailTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2151981797809716706L;
	private int transseq;
	private int layerid;
	private Double calcprice;
	private Double balance;
	private Double transvalue;
	private Double layerinqty;
	private Double layeroutq;
	private Double revaltotal;
	public WarehouseJournalDetailTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WarehouseJournalDetailTO(int transseq, int layerid,
			Double calcprice, Double balance, Double transvalue,
			Double layerinqty, Double layeroutq, Double revaltotal) {
		super();
		this.transseq = transseq;
		this.layerid = layerid;
		this.calcprice = calcprice;
		this.balance = balance;
		this.transvalue = transvalue;
		this.layerinqty = layerinqty;
		this.layeroutq = layeroutq;
		this.revaltotal = revaltotal;
	}
	public int getTransseq() {
		return transseq;
	}
	public void setTransseq(int transseq) {
		this.transseq = transseq;
	}
	public int getLayerid() {
		return layerid;
	}
	public void setLayerid(int layerid) {
		this.layerid = layerid;
	}
	public Double getCalcprice() {
		return calcprice;
	}
	public void setCalcprice(Double calcprice) {
		this.calcprice = calcprice;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getTransvalue() {
		return transvalue;
	}
	public void setTransvalue(Double transvalue) {
		this.transvalue = transvalue;
	}
	public Double getLayerinqty() {
		return layerinqty;
	}
	public void setLayerinqty(Double layerinqty) {
		this.layerinqty = layerinqty;
	}
	public Double getLayeroutq() {
		return layeroutq;
	}
	public void setLayeroutq(Double layeroutq) {
		this.layeroutq = layeroutq;
	}
	public Double getRevaltotal() {
		return revaltotal;
	}
	public void setRevaltotal(Double revaltotal) {
		this.revaltotal = revaltotal;
	}

}

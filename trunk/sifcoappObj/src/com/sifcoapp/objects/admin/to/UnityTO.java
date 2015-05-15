package com.sifcoapp.objects.admin.to;

import java.util.Date;

import com.sifcoapp.objects.common.to.CommonTO;

public class UnityTO extends CommonTO {
/**
	 * 
	 */
	private static final long serialVersionUID = -5253430276107683520L;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getCardcode() {
		return cardcode;
	}
	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public String getDflaccount() {
		return dflaccount;
	}
	public void setDflaccount(String dflaccount) {
		this.dflaccount = dflaccount;
	}
	public String getRelatedacc1() {
		return relatedacc1;
	}
	public void setRelatedacc1(String relatedacc1) {
		this.relatedacc1 = relatedacc1;
	}
	public String getRelatedacc2() {
		return relatedacc2;
	}
	public void setRelatedacc2(String relatedacc2) {
		this.relatedacc2 = relatedacc2;
	}
	public String getRelatedacc3() {
		return relatedacc3;
	}
	public void setRelatedacc3(String relatedacc3) {
		this.relatedacc3 = relatedacc3;
	}
	public String getRelatedacc4() {
		return relatedacc4;
	}
	public void setRelatedacc4(String relatedacc4) {
		this.relatedacc4 = relatedacc4;
	}
	public Date getPurchasedate() {
		return purchasedate;
	}
	public void setPurchasedate(Date purchasedate) {
		this.purchasedate = purchasedate;
	}
	public int getUsersign() {
		return usersign;
	}
	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}
	public UnityTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UnityTO(String code, String record, String license, String card,
			String driver, String cardcode, String cardname, String notes,
			String type, String status, int year, String brand, Date duedate,
			String dflaccount, String relatedacc1, String relatedacc2,
			String relatedacc3, String relatedacc4, Date purchasedate,
			int usersign) {
		super();
		this.code = code;
		this.record = record;
		this.license = license;
		this.card = card;
		this.driver = driver;
		this.cardcode = cardcode;
		this.cardname = cardname;
		this.notes = notes;
		this.type = type;
		this.status = status;
		this.year = year;
		this.brand = brand;
		this.duedate = duedate;
		this.dflaccount = dflaccount;
		this.relatedacc1 = relatedacc1;
		this.relatedacc2 = relatedacc2;
		this.relatedacc3 = relatedacc3;
		this.relatedacc4 = relatedacc4;
		this.purchasedate = purchasedate;
		this.usersign = usersign;
	}
	private String code;
	private String record;
	private String license;
	private String card;
	private String driver;
	private String cardcode;
	private String cardname;
	private String notes;
	private String type;
	private String status;
	private int year;
	private String brand;
	private Date duedate;
	private String dflaccount;
	private String relatedacc1;
	private String relatedacc2;
	private String relatedacc3;
	private String relatedacc4;
	private Date purchasedate;
	private int usersign;

}

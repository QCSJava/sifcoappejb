package com.sifcoapp.objects.catalog.to;

import com.sifcoapp.objects.common.to.CommonTO;

import java.util.Date;

public class BusinesspartnerTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6266904645558998389L;

	private String cardcode;
	private String cardname;
	private String cardtype;
	private String groupcode;
	private String cmpprivate;
	private String address;
	private String zipcode;
	private String mailaddres;
	private String mailzipcod;
	private String phone1;
	private String phone2;
	private String fax;
	private String cntctprsn;
	private String notes;
	private Double balance;
	private Double checksbal;
	private Double dnotesbal;
	private Double ordersbal;
	private int groupnum;
	private Double creditline;
	private Double debtline;
	private Double discount;
	private String vatstatus;
	private String lictradnum;
	private int exmatchnum;
	private int inmatchnum;
	private int listnum;
	private Double orderbalsy;
	private String transfered;
	private String baltrnsfrd;
	private int commgrcode;
	private String prevyearac;
	private Double balancesys;
	private String cellular;
	private String e_mail;
	private String picture;
	private String dflaccount;
	private String dflbranch;
	private String bankcode;
	private String addid;
	private String fathercard;
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
	private String validfor;
	private String vatgroup;
	private String objtype;
	private String debpayacct;
	private int docentry;
	private String pymcode;
	private String partdelivr;
	private String paymblock;
	private String wtliable;
	private String ninum;
	private String wtcode;
	private String vatregnum;
	private String industry;
	private String business;
	private String isdomestic;
	private String isresident;
	private String dpmclear;
	private String affiliate;
	private String profession;
	private String dpmintact;
	private int industryc;
	private String intracc;
	private String feeacc;
	private int series;
	private int number;
	private String taxidident;
	private String nodiscount;
	private String typecont;
	private String typesn;
	private String nit;
	private String tipocont;
	private String tiposn;
	private String relatedacc1;
	private String relatedacc2;
	private String relatedacc3;
	private String relatedacc4;
	private int usersign;
	private Date updatedate;
	private int updatetime;

	public BusinesspartnerTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinesspartnerTO(String cardcode, String cardname, String cardtype,
			String groupcode, String cmpprivate, String address,
			String zipcode, String mailaddres, String mailzipcod,
			String phone1, String phone2, String fax, String cntctprsn,
			String notes, Double balance, Double checksbal, Double dnotesbal,
			Double ordersbal, int groupnum, Double creditline, Double debtline,
			Double discount, String vatstatus, String lictradnum,
			int exmatchnum, int inmatchnum, int listnum, Double orderbalsy,
			String transfered, String baltrnsfrd, int commgrcode,
			String prevyearac, Double balancesys, String cellular,
			String e_mail, String picture, String dflaccount, String dflbranch,
			String bankcode, String addid, String fathercard, String qrygroup1,
			String qrygroup2, String qrygroup3, String qrygroup4,
			String qrygroup5, String qrygroup6, String qrygroup7,
			String qrygroup8, String qrygroup9, String qrygroup10,
			String validfor, String vatgroup, String objtype,
			String debpayacct, int docentry, String pymcode, String partdelivr,
			String paymblock, String wtliable, String ninum, String wtcode,
			String vatregnum, String industry, String business,
			String isdomestic, String isresident, String dpmclear,
			String affiliate, String profession, String dpmintact,
			int industryc, String intracc, String feeacc, int series,
			int number, String taxidident, String nodiscount, String typecont,
			String typesn, String nit, String tipocont, String tiposn,
			String relatedacc1, String relatedacc2, String relatedacc3,
			String relatedacc4, int usersign, Date updatedate, int updatetime) {
		super();
		this.cardcode = cardcode;
		this.cardname = cardname;
		this.cardtype = cardtype;
		this.groupcode = groupcode;
		this.cmpprivate = cmpprivate;
		this.address = address;
		this.zipcode = zipcode;
		this.mailaddres = mailaddres;
		this.mailzipcod = mailzipcod;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.fax = fax;
		this.cntctprsn = cntctprsn;
		this.notes = notes;
		this.balance = balance;
		this.checksbal = checksbal;
		this.dnotesbal = dnotesbal;
		this.ordersbal = ordersbal;
		this.groupnum = groupnum;
		this.creditline = creditline;
		this.debtline = debtline;
		this.discount = discount;
		this.vatstatus = vatstatus;
		this.lictradnum = lictradnum;
		this.exmatchnum = exmatchnum;
		this.inmatchnum = inmatchnum;
		this.listnum = listnum;
		this.orderbalsy = orderbalsy;
		this.transfered = transfered;
		this.baltrnsfrd = baltrnsfrd;
		this.commgrcode = commgrcode;
		this.prevyearac = prevyearac;
		this.balancesys = balancesys;
		this.cellular = cellular;
		this.e_mail = e_mail;
		this.picture = picture;
		this.dflaccount = dflaccount;
		this.dflbranch = dflbranch;
		this.bankcode = bankcode;
		this.addid = addid;
		this.fathercard = fathercard;
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
		this.validfor = validfor;
		this.vatgroup = vatgroup;
		this.objtype = objtype;
		this.debpayacct = debpayacct;
		this.docentry = docentry;
		this.pymcode = pymcode;
		this.partdelivr = partdelivr;
		this.paymblock = paymblock;
		this.wtliable = wtliable;
		this.ninum = ninum;
		this.wtcode = wtcode;
		this.vatregnum = vatregnum;
		this.industry = industry;
		this.business = business;
		this.isdomestic = isdomestic;
		this.isresident = isresident;
		this.dpmclear = dpmclear;
		this.affiliate = affiliate;
		this.profession = profession;
		this.dpmintact = dpmintact;
		this.industryc = industryc;
		this.intracc = intracc;
		this.feeacc = feeacc;
		this.series = series;
		this.number = number;
		this.taxidident = taxidident;
		this.nodiscount = nodiscount;
		this.typecont = typecont;
		this.typesn = typesn;
		this.nit = nit;
		this.tipocont = tipocont;
		this.tiposn = tiposn;
		this.relatedacc1 = relatedacc1;
		this.relatedacc2 = relatedacc2;
		this.relatedacc3 = relatedacc3;
		this.relatedacc4 = relatedacc4;
		this.usersign = usersign;
		this.updatedate = updatedate;
		this.updatetime = updatetime;
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

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public String getCmpprivate() {
		return cmpprivate;
	}

	public void setCmpprivate(String cmpprivate) {
		this.cmpprivate = cmpprivate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getMailaddres() {
		return mailaddres;
	}

	public void setMailaddres(String mailaddres) {
		this.mailaddres = mailaddres;
	}

	public String getMailzipcod() {
		return mailzipcod;
	}

	public void setMailzipcod(String mailzipcod) {
		this.mailzipcod = mailzipcod;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCntctprsn() {
		return cntctprsn;
	}

	public void setCntctprsn(String cntctprsn) {
		this.cntctprsn = cntctprsn;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getChecksbal() {
		return checksbal;
	}

	public void setChecksbal(Double checksbal) {
		this.checksbal = checksbal;
	}

	public Double getDnotesbal() {
		return dnotesbal;
	}

	public void setDnotesbal(Double dnotesbal) {
		this.dnotesbal = dnotesbal;
	}

	public Double getOrdersbal() {
		return ordersbal;
	}

	public void setOrdersbal(Double ordersbal) {
		this.ordersbal = ordersbal;
	}

	public int getGroupnum() {
		return groupnum;
	}

	public void setGroupnum(int groupnum) {
		this.groupnum = groupnum;
	}

	public Double getCreditline() {
		return creditline;
	}

	public void setCreditline(Double creditline) {
		this.creditline = creditline;
	}

	public Double getDebtline() {
		return debtline;
	}

	public void setDebtline(Double debtline) {
		this.debtline = debtline;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getVatstatus() {
		return vatstatus;
	}

	public void setVatstatus(String vatstatus) {
		this.vatstatus = vatstatus;
	}

	public String getLictradnum() {
		return lictradnum;
	}

	public void setLictradnum(String lictradnum) {
		this.lictradnum = lictradnum;
	}

	public int getExmatchnum() {
		return exmatchnum;
	}

	public void setExmatchnum(int exmatchnum) {
		this.exmatchnum = exmatchnum;
	}

	public int getInmatchnum() {
		return inmatchnum;
	}

	public void setInmatchnum(int inmatchnum) {
		this.inmatchnum = inmatchnum;
	}

	public int getListnum() {
		return listnum;
	}

	public void setListnum(int listnum) {
		this.listnum = listnum;
	}

	public Double getOrderbalsy() {
		return orderbalsy;
	}

	public void setOrderbalsy(Double orderbalsy) {
		this.orderbalsy = orderbalsy;
	}

	public String getTransfered() {
		return transfered;
	}

	public void setTransfered(String transfered) {
		this.transfered = transfered;
	}

	public String getBaltrnsfrd() {
		return baltrnsfrd;
	}

	public void setBaltrnsfrd(String baltrnsfrd) {
		this.baltrnsfrd = baltrnsfrd;
	}

	public int getCommgrcode() {
		return commgrcode;
	}

	public void setCommgrcode(int commgrcode) {
		this.commgrcode = commgrcode;
	}

	public String getPrevyearac() {
		return prevyearac;
	}

	public void setPrevyearac(String prevyearac) {
		this.prevyearac = prevyearac;
	}

	public Double getBalancesys() {
		return balancesys;
	}

	public void setBalancesys(Double balancesys) {
		this.balancesys = balancesys;
	}

	public String getCellular() {
		return cellular;
	}

	public void setCellular(String cellular) {
		this.cellular = cellular;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDflaccount() {
		return dflaccount;
	}

	public void setDflaccount(String dflaccount) {
		this.dflaccount = dflaccount;
	}

	public String getDflbranch() {
		return dflbranch;
	}

	public void setDflbranch(String dflbranch) {
		this.dflbranch = dflbranch;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getAddid() {
		return addid;
	}

	public void setAddid(String addid) {
		this.addid = addid;
	}

	public String getFathercard() {
		return fathercard;
	}

	public void setFathercard(String fathercard) {
		this.fathercard = fathercard;
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

	public String getValidfor() {
		return validfor;
	}

	public void setValidfor(String validfor) {
		this.validfor = validfor;
	}

	public String getVatgroup() {
		return vatgroup;
	}

	public void setVatgroup(String vatgroup) {
		this.vatgroup = vatgroup;
	}

	public String getObjtype() {
		return objtype;
	}

	public void setObjtype(String objtype) {
		this.objtype = objtype;
	}

	public String getDebpayacct() {
		return debpayacct;
	}

	public void setDebpayacct(String debpayacct) {
		this.debpayacct = debpayacct;
	}

	public int getDocentry() {
		return docentry;
	}

	public void setDocentry(int docentry) {
		this.docentry = docentry;
	}

	public String getPymcode() {
		return pymcode;
	}

	public void setPymcode(String pymcode) {
		this.pymcode = pymcode;
	}

	public String getPartdelivr() {
		return partdelivr;
	}

	public void setPartdelivr(String partdelivr) {
		this.partdelivr = partdelivr;
	}

	public String getPaymblock() {
		return paymblock;
	}

	public void setPaymblock(String paymblock) {
		this.paymblock = paymblock;
	}

	public String getWtliable() {
		return wtliable;
	}

	public void setWtliable(String wtliable) {
		this.wtliable = wtliable;
	}

	public String getNinum() {
		return ninum;
	}

	public void setNinum(String ninum) {
		this.ninum = ninum;
	}

	public String getWtcode() {
		return wtcode;
	}

	public void setWtcode(String wtcode) {
		this.wtcode = wtcode;
	}

	public String getVatregnum() {
		return vatregnum;
	}

	public void setVatregnum(String vatregnum) {
		this.vatregnum = vatregnum;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getIsdomestic() {
		return isdomestic;
	}

	public void setIsdomestic(String isdomestic) {
		this.isdomestic = isdomestic;
	}

	public String getIsresident() {
		return isresident;
	}

	public void setIsresident(String isresident) {
		this.isresident = isresident;
	}

	public String getDpmclear() {
		return dpmclear;
	}

	public void setDpmclear(String dpmclear) {
		this.dpmclear = dpmclear;
	}

	public String getAffiliate() {
		return affiliate;
	}

	public void setAffiliate(String affiliate) {
		this.affiliate = affiliate;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getDpmintact() {
		return dpmintact;
	}

	public void setDpmintact(String dpmintact) {
		this.dpmintact = dpmintact;
	}

	public int getIndustryc() {
		return industryc;
	}

	public void setIndustryc(int industryc) {
		this.industryc = industryc;
	}

	public String getIntracc() {
		return intracc;
	}

	public void setIntracc(String intracc) {
		this.intracc = intracc;
	}

	public String getFeeacc() {
		return feeacc;
	}

	public void setFeeacc(String feeacc) {
		this.feeacc = feeacc;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTaxidident() {
		return taxidident;
	}

	public void setTaxidident(String taxidident) {
		this.taxidident = taxidident;
	}

	public String getNodiscount() {
		return nodiscount;
	}

	public void setNodiscount(String nodiscount) {
		this.nodiscount = nodiscount;
	}

	public String getTypecont() {
		return typecont;
	}

	public void setTypecont(String typecont) {
		this.typecont = typecont;
	}

	public String getTypesn() {
		return typesn;
	}

	public void setTypesn(String typesn) {
		this.typesn = typesn;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getTipocont() {
		return tipocont;
	}

	public void setTipocont(String tipocont) {
		this.tipocont = tipocont;
	}

	public String getTiposn() {
		return tiposn;
	}

	public void setTiposn(String tiposn) {
		this.tiposn = tiposn;
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

	public int getUsersign() {
		return usersign;
	}

	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public int getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(int updatetime) {
		this.updatetime = updatetime;
	}

}

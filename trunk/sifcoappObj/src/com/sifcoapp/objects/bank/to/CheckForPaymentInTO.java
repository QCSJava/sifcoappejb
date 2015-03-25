package com.sifcoapp.objects.bank.to;

import com.sifcoapp.objects.common.to.CommonTO;

import java.util.Date;

public class CheckForPaymentInTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6363119571291797999L;
	private int checkkey;
	private int checknum;
	private String banknum;
	private String bankname;
	private Date checkdate;
	private String dpstacct;
	private String acctnum;
	private String details;
	private String transref;
	private Date pmntdate;
	private int pmntnum;
	private Double checksum;
	private String trnsfrable;
	private String vendorcode;
	private String canceled;
	private String cardoracct;
	private String printed;
	private String vendorname;
	private String totalwords;
	private String signature;
	private String checkacct;
	private int transnum;
	private Double linessum;
	private String address;
	private String createjdt;
	private Double vattotal;
	private String vatcalcult;
	private Date taxdate;
	private int printedby;
	private String objtype;
	private String countrycod;
	private String addrname;
	private String prnconfrm;
	private int bnkactkey;
	private String manualchk;
	private int usersign;

	public CheckForPaymentInTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckForPaymentInTO(int checkkey, int checknum, String banknum,
			String bankname, Date checkdate, String dpstacct, String acctnum,
			String details, String transref, Date pmntdate, int pmntnum,
			Double checksum, String trnsfrable, String vendorcode,
			String canceled, String cardoracct, String printed,
			String vendorname, String totalwords, String signature,
			String checkacct, int transnum, Double linessum, String address,
			String createjdt, Double vattotal, String vatcalcult, Date taxdate,
			int printedby, String objtype, String countrycod, String addrname,
			String prnconfrm, int bnkactkey, String manualchk, int usersign) {
		super();
		this.checkkey = checkkey;
		this.checknum = checknum;
		this.banknum = banknum;
		this.bankname = bankname;
		this.checkdate = checkdate;
		this.dpstacct = dpstacct;
		this.acctnum = acctnum;
		this.details = details;
		this.transref = transref;
		this.pmntdate = pmntdate;
		this.pmntnum = pmntnum;
		this.checksum = checksum;
		this.trnsfrable = trnsfrable;
		this.vendorcode = vendorcode;
		this.canceled = canceled;
		this.cardoracct = cardoracct;
		this.printed = printed;
		this.vendorname = vendorname;
		this.totalwords = totalwords;
		this.signature = signature;
		this.checkacct = checkacct;
		this.transnum = transnum;
		this.linessum = linessum;
		this.address = address;
		this.createjdt = createjdt;
		this.vattotal = vattotal;
		this.vatcalcult = vatcalcult;
		this.taxdate = taxdate;
		this.printedby = printedby;
		this.objtype = objtype;
		this.countrycod = countrycod;
		this.addrname = addrname;
		this.prnconfrm = prnconfrm;
		this.bnkactkey = bnkactkey;
		this.manualchk = manualchk;
		this.usersign = usersign;
	}

	public int getCheckkey() {
		return checkkey;
	}

	public void setCheckkey(int checkkey) {
		this.checkkey = checkkey;
	}

	public int getChecknum() {
		return checknum;
	}

	public void setChecknum(int checknum) {
		this.checknum = checknum;
	}

	public String getBanknum() {
		return banknum;
	}

	public void setBanknum(String banknum) {
		this.banknum = banknum;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public Date getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}

	public String getDpstacct() {
		return dpstacct;
	}

	public void setDpstacct(String dpstacct) {
		this.dpstacct = dpstacct;
	}

	public String getAcctnum() {
		return acctnum;
	}

	public void setAcctnum(String acctnum) {
		this.acctnum = acctnum;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getTransref() {
		return transref;
	}

	public void setTransref(String transref) {
		this.transref = transref;
	}

	public Date getPmntdate() {
		return pmntdate;
	}

	public void setPmntdate(Date pmntdate) {
		this.pmntdate = pmntdate;
	}

	public int getPmntnum() {
		return pmntnum;
	}

	public void setPmntnum(int pmntnum) {
		this.pmntnum = pmntnum;
	}

	public Double getChecksum() {
		return checksum;
	}

	public void setChecksum(Double checksum) {
		this.checksum = checksum;
	}

	public String getTrnsfrable() {
		return trnsfrable;
	}

	public void setTrnsfrable(String trnsfrable) {
		this.trnsfrable = trnsfrable;
	}

	public String getVendorcode() {
		return vendorcode;
	}

	public void setVendorcode(String vendorcode) {
		this.vendorcode = vendorcode;
	}

	public String getCanceled() {
		return canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

	public String getCardoracct() {
		return cardoracct;
	}

	public void setCardoracct(String cardoracct) {
		this.cardoracct = cardoracct;
	}

	public String getPrinted() {
		return printed;
	}

	public void setPrinted(String printed) {
		this.printed = printed;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public String getTotalwords() {
		return totalwords;
	}

	public void setTotalwords(String totalwords) {
		this.totalwords = totalwords;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getCheckacct() {
		return checkacct;
	}

	public void setCheckacct(String checkacct) {
		this.checkacct = checkacct;
	}

	public int getTransnum() {
		return transnum;
	}

	public void setTransnum(int transnum) {
		this.transnum = transnum;
	}

	public Double getLinessum() {
		return linessum;
	}

	public void setLinessum(Double linessum) {
		this.linessum = linessum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatejdt() {
		return createjdt;
	}

	public void setCreatejdt(String createjdt) {
		this.createjdt = createjdt;
	}

	public Double getVattotal() {
		return vattotal;
	}

	public void setVattotal(Double vattotal) {
		this.vattotal = vattotal;
	}

	public String getVatcalcult() {
		return vatcalcult;
	}

	public void setVatcalcult(String vatcalcult) {
		this.vatcalcult = vatcalcult;
	}

	public Date getTaxdate() {
		return taxdate;
	}

	public void setTaxdate(Date taxdate) {
		this.taxdate = taxdate;
	}

	public int getPrintedby() {
		return printedby;
	}

	public void setPrintedby(int printedby) {
		this.printedby = printedby;
	}

	public String getObjtype() {
		return objtype;
	}

	public void setObjtype(String objtype) {
		this.objtype = objtype;
	}

	public String getCountrycod() {
		return countrycod;
	}

	public void setCountrycod(String countrycod) {
		this.countrycod = countrycod;
	}

	public String getAddrname() {
		return addrname;
	}

	public void setAddrname(String addrname) {
		this.addrname = addrname;
	}

	public String getPrnconfrm() {
		return prnconfrm;
	}

	public void setPrnconfrm(String prnconfrm) {
		this.prnconfrm = prnconfrm;
	}

	public int getBnkactkey() {
		return bnkactkey;
	}

	public void setBnkactkey(int bnkactkey) {
		this.bnkactkey = bnkactkey;
	}

	public String getManualchk() {
		return manualchk;
	}

	public void setManualchk(String manualchk) {
		this.manualchk = manualchk;
	}

	public int getUsersign() {
		return usersign;
	}

	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

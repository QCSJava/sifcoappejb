package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

public class BranchTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6840211846980087050L;

	private String whscode;
	private String whsname;
	private String grp_code;
	private boolean locked;
	private String street;
	private String city;
	private String country;
	private String location;
	private String usetax;
	private String balinvntac;
	private String salecostac;
	private String transferac;
	private String revenuesac;
	private String varianceac;
	private String decreasac;
	private String increasac;
	private String returnac;
	private String expensesac;
	private String frrevenuac;
	private String frexpensac;
	private String pricedifac;
	private String exchangeac;
	private String balanceacc;
	private String purchaseac;
	private String pareturnac;
	private String purchofsac;
	private String shpdgdsact;
	private String vatrevact;
	private String decresglac;
	private String incresglac;
	private String stokrvlact;
	private String stkoffsact;
	private String wipacct;
	private String wipvaracct;
	private String costrvlact;
	private String cstoffsact;
	private String expclract;
	private String expofstact;
	private String arcmact;
	private String arcmfrnact;
	private String arcmexpact;
	private String apcmact;
	private String apcmfrnact;
	private String revretact;
	private String negstckact;
	private String stkintnact;
	private String purbalact;
	private String whicenact;
	private String whocenact;
	private String excisable;
	private int usersign;

	public BranchTO(String whscode, String whsname, String grp_code,
			boolean locked, String street, String city, String country,
			String location, String usetax, String balinvntac,
			String salecostac, String transferac, String revenuesac,
			String varianceac, String decreasac, String increasac,
			String returnac, String expensesac, String frrevenuac,
			String frexpensac, String pricedifac, String exchangeac,
			String balanceacc, String purchaseac, String pareturnac,
			String purchofsac, String shpdgdsact, String vatrevact,
			String decresglac, String incresglac, String stokrvlact,
			String stkoffsact, String wipacct, String wipvaracct,
			String costrvlact, String cstoffsact, String expclract,
			String expofstact, String arcmact, String arcmfrnact,
			String arcmexpact, String apcmact, String apcmfrnact,
			String revretact, String negstckact, String stkintnact,
			String purbalact, String whicenact, String whocenact,
			String excisable, int usersign) {
		super();
		this.whscode = whscode;
		this.whsname = whsname;
		this.grp_code = grp_code;
		this.locked = locked;
		this.street = street;
		this.city = city;
		this.country = country;
		this.location = location;
		this.usetax = usetax;
		this.balinvntac = balinvntac;
		this.salecostac = salecostac;
		this.transferac = transferac;
		this.revenuesac = revenuesac;
		this.varianceac = varianceac;
		this.decreasac = decreasac;
		this.increasac = increasac;
		this.returnac = returnac;
		this.expensesac = expensesac;
		this.frrevenuac = frrevenuac;
		this.frexpensac = frexpensac;
		this.pricedifac = pricedifac;
		this.exchangeac = exchangeac;
		this.balanceacc = balanceacc;
		this.purchaseac = purchaseac;
		this.pareturnac = pareturnac;
		this.purchofsac = purchofsac;
		this.shpdgdsact = shpdgdsact;
		this.vatrevact = vatrevact;
		this.decresglac = decresglac;
		this.incresglac = incresglac;
		this.stokrvlact = stokrvlact;
		this.stkoffsact = stkoffsact;
		this.wipacct = wipacct;
		this.wipvaracct = wipvaracct;
		this.costrvlact = costrvlact;
		this.cstoffsact = cstoffsact;
		this.expclract = expclract;
		this.expofstact = expofstact;
		this.arcmact = arcmact;
		this.arcmfrnact = arcmfrnact;
		this.arcmexpact = arcmexpact;
		this.apcmact = apcmact;
		this.apcmfrnact = apcmfrnact;
		this.revretact = revretact;
		this.negstckact = negstckact;
		this.stkintnact = stkintnact;
		this.purbalact = purbalact;
		this.whicenact = whicenact;
		this.whocenact = whocenact;
		this.excisable = excisable;
		this.usersign = usersign;
	}

	public BranchTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getGrp_code() {
		return grp_code;
	}

	public void setGrp_code(String grp_code) {
		this.grp_code = grp_code;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getUsetax() {
		return usetax;
	}

	public void setUsetax(String usetax) {
		this.usetax = usetax;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBalinvntac() {
		return balinvntac;
	}

	public void setBalinvntac(String balinvntac) {
		this.balinvntac = balinvntac;
	}

	public String getSalecostac() {
		return salecostac;
	}

	public void setSalecostac(String salecostac) {
		this.salecostac = salecostac;
	}

	public String getTransferac() {
		return transferac;
	}

	public void setTransferac(String transferac) {
		this.transferac = transferac;
	}

	public String getRevenuesac() {
		return revenuesac;
	}

	public void setRevenuesac(String revenuesac) {
		this.revenuesac = revenuesac;
	}

	public String getVarianceac() {
		return varianceac;
	}

	public void setVarianceac(String varianceac) {
		this.varianceac = varianceac;
	}

	public String getDecreasac() {
		return decreasac;
	}

	public void setDecreasac(String decreasac) {
		this.decreasac = decreasac;
	}

	public String getIncreasac() {
		return increasac;
	}

	public void setIncreasac(String increasac) {
		this.increasac = increasac;
	}

	public String getReturnac() {
		return returnac;
	}

	public void setReturnac(String returnac) {
		this.returnac = returnac;
	}

	public String getExpensesac() {
		return expensesac;
	}

	public void setExpensesac(String expensesac) {
		this.expensesac = expensesac;
	}

	public String getFrrevenuac() {
		return frrevenuac;
	}

	public void setFrrevenuac(String frrevenuac) {
		this.frrevenuac = frrevenuac;
	}

	public String getFrexpensac() {
		return frexpensac;
	}

	public void setFrexpensac(String frexpensac) {
		this.frexpensac = frexpensac;
	}

	public String getPricedifac() {
		return pricedifac;
	}

	public void setPricedifac(String pricedifac) {
		this.pricedifac = pricedifac;
	}

	public String getExchangeac() {
		return exchangeac;
	}

	public void setExchangeac(String exchangeac) {
		this.exchangeac = exchangeac;
	}

	public String getBalanceacc() {
		return balanceacc;
	}

	public void setBalanceacc(String balanceacc) {
		this.balanceacc = balanceacc;
	}

	public String getPurchaseac() {
		return purchaseac;
	}

	public void setPurchaseac(String purchaseac) {
		this.purchaseac = purchaseac;
	}

	public String getPareturnac() {
		return pareturnac;
	}

	public void setPareturnac(String pareturnac) {
		this.pareturnac = pareturnac;
	}

	public String getPurchofsac() {
		return purchofsac;
	}

	public void setPurchofsac(String purchofsac) {
		this.purchofsac = purchofsac;
	}

	public String getShpdgdsact() {
		return shpdgdsact;
	}

	public void setShpdgdsact(String shpdgdsact) {
		this.shpdgdsact = shpdgdsact;
	}

	public String getVatrevact() {
		return vatrevact;
	}

	public void setVatrevact(String vatrevact) {
		this.vatrevact = vatrevact;
	}

	public String getDecresglac() {
		return decresglac;
	}

	public void setDecresglac(String decresglac) {
		this.decresglac = decresglac;
	}

	public String getIncresglac() {
		return incresglac;
	}

	public void setIncresglac(String incresglac) {
		this.incresglac = incresglac;
	}

	public String getStokrvlact() {
		return stokrvlact;
	}

	public void setStokrvlact(String stokrvlact) {
		this.stokrvlact = stokrvlact;
	}

	public String getStkoffsact() {
		return stkoffsact;
	}

	public void setStkoffsact(String stkoffsact) {
		this.stkoffsact = stkoffsact;
	}

	public String getWipacct() {
		return wipacct;
	}

	public void setWipacct(String wipacct) {
		this.wipacct = wipacct;
	}

	public String getWipvaracct() {
		return wipvaracct;
	}

	public void setWipvaracct(String wipvaracct) {
		this.wipvaracct = wipvaracct;
	}

	public String getCostrvlact() {
		return costrvlact;
	}

	public void setCostrvlact(String costrvlact) {
		this.costrvlact = costrvlact;
	}

	public String getCstoffsact() {
		return cstoffsact;
	}

	public void setCstoffsact(String cstoffsact) {
		this.cstoffsact = cstoffsact;
	}

	public String getExpclract() {
		return expclract;
	}

	public void setExpclract(String expclract) {
		this.expclract = expclract;
	}

	public String getExpofstact() {
		return expofstact;
	}

	public void setExpofstact(String expofstact) {
		this.expofstact = expofstact;
	}

	public String getArcmact() {
		return arcmact;
	}

	public void setArcmact(String arcmact) {
		this.arcmact = arcmact;
	}

	public String getArcmfrnact() {
		return arcmfrnact;
	}

	public void setArcmfrnact(String arcmfrnact) {
		this.arcmfrnact = arcmfrnact;
	}

	public String getArcmexpact() {
		return arcmexpact;
	}

	public void setArcmexpact(String arcmexpact) {
		this.arcmexpact = arcmexpact;
	}

	public String getApcmact() {
		return apcmact;
	}

	public void setApcmact(String apcmact) {
		this.apcmact = apcmact;
	}

	public String getApcmfrnact() {
		return apcmfrnact;
	}

	public void setApcmfrnact(String apcmfrnact) {
		this.apcmfrnact = apcmfrnact;
	}

	public String getRevretact() {
		return revretact;
	}

	public void setRevretact(String revretact) {
		this.revretact = revretact;
	}

	public String getNegstckact() {
		return negstckact;
	}

	public void setNegstckact(String negstckact) {
		this.negstckact = negstckact;
	}

	public String getStkintnact() {
		return stkintnact;
	}

	public void setStkintnact(String stkintnact) {
		this.stkintnact = stkintnact;
	}

	public String getPurbalact() {
		return purbalact;
	}

	public void setPurbalact(String purbalact) {
		this.purbalact = purbalact;
	}

	public String getWhicenact() {
		return whicenact;
	}

	public void setWhicenact(String whicenact) {
		this.whicenact = whicenact;
	}

	public String getWhocenact() {
		return whocenact;
	}

	public void setWhocenact(String whocenact) {
		this.whocenact = whocenact;
	}

	public String getExcisable() {
		return excisable;
	}

	public void setExcisable(String excisable) {
		this.excisable = excisable;
	}

	public int getUsersign() {
		return usersign;
	}

	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}

}

package com.sifcoapp.objects.transaction.to;

import java.util.List;

import com.sifcoapp.objects.common.to.CommonTO;

public class WarehouseJournalTO extends CommonTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3594041793855993326L;
	private int transseq;
	private int transtype;
	private int createdby;
	private String base_ref;
	private int doclinenum;
	private String itemcode;
	private Double inqty;
	private Double outqty;
	private Double price;
	private String trnsfract;
	private String pricedifac;
	private String varianceac;
	private String returnact;
	private String clearact;
	private String costact;
	private String wipact;
	private Double openstock;
	private Double pricediff;
	private String invntact;
	private int sublinenum;
	private int appobjline;
	private Double expenses;
	private Double openexp;
	private Double allocation;
	private Double openalloc;
	private Double expalloc;
	private Double oexpalloc;
	private Double openpdiff;
	private Double neginvadjs;
	private Double openneginv;
	private String negstckact;
	private Double btransval;
	private Double varval;
	private Double bexpval;
	private Double cogsval;
	private Double bnegaval;
	private String ioffincacc;
	private Double ioffincval;
	private String doffdecacc;
	private Double doffdecval;
	private String decacc;
	private Double decval;
	private Double wipval;
	private String wipvaracc;
	private Double wipvarval;
	private String incact;
	private Double incval;
	private String expcacc;
	private int messageid;
	private int loctype;
	private String loccode;
	private String poststatus;
	private Double sumstock;
	private Double openqty;
	private String paoffacc;
	private Double paoffval;
	private Double openpaoff;
	private String paacc;
	private Double paval;
	private Double openpa;
	private List DetailWarehouse;
	public WarehouseJournalTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WarehouseJournalTO(int transseq, int transtype, int createdby,
			String base_ref, int doclinenum, String itemcode, Double inqty,
			Double outqty, Double price, String trnsfract, String pricedifac,
			String varianceac, String returnact, String clearact,
			String costact, String wipact, Double openstock, Double pricediff,
			String invntact, int sublinenum, int appobjline, Double expenses,
			Double openexp, Double allocation, Double openalloc,
			Double expalloc, Double oexpalloc, Double openpdiff,
			Double neginvadjs, Double openneginv, String negstckact,
			Double btransval, Double varval, Double bexpval, Double cogsval,
			Double bnegaval, String ioffincacc, Double ioffincval,
			String doffdecacc, Double doffdecval, String decacc, Double decval,
			Double wipval, String wipvaracc, Double wipvarval, String incact,
			Double incval, String expcacc, int messageid, int loctype,
			String loccode, String poststatus, Double sumstock, Double openqty,
			String paoffacc, Double paoffval, Double openpaoff, String paacc,
			Double paval, Double openpa, List detailWarehouse) {
		super();
		this.transseq = transseq;
		this.transtype = transtype;
		this.createdby = createdby;
		this.base_ref = base_ref;
		this.doclinenum = doclinenum;
		this.itemcode = itemcode;
		this.inqty = inqty;
		this.outqty = outqty;
		this.price = price;
		this.trnsfract = trnsfract;
		this.pricedifac = pricedifac;
		this.varianceac = varianceac;
		this.returnact = returnact;
		this.clearact = clearact;
		this.costact = costact;
		this.wipact = wipact;
		this.openstock = openstock;
		this.pricediff = pricediff;
		this.invntact = invntact;
		this.sublinenum = sublinenum;
		this.appobjline = appobjline;
		this.expenses = expenses;
		this.openexp = openexp;
		this.allocation = allocation;
		this.openalloc = openalloc;
		this.expalloc = expalloc;
		this.oexpalloc = oexpalloc;
		this.openpdiff = openpdiff;
		this.neginvadjs = neginvadjs;
		this.openneginv = openneginv;
		this.negstckact = negstckact;
		this.btransval = btransval;
		this.varval = varval;
		this.bexpval = bexpval;
		this.cogsval = cogsval;
		this.bnegaval = bnegaval;
		this.ioffincacc = ioffincacc;
		this.ioffincval = ioffincval;
		this.doffdecacc = doffdecacc;
		this.doffdecval = doffdecval;
		this.decacc = decacc;
		this.decval = decval;
		this.wipval = wipval;
		this.wipvaracc = wipvaracc;
		this.wipvarval = wipvarval;
		this.incact = incact;
		this.incval = incval;
		this.expcacc = expcacc;
		this.messageid = messageid;
		this.loctype = loctype;
		this.loccode = loccode;
		this.poststatus = poststatus;
		this.sumstock = sumstock;
		this.openqty = openqty;
		this.paoffacc = paoffacc;
		this.paoffval = paoffval;
		this.openpaoff = openpaoff;
		this.paacc = paacc;
		this.paval = paval;
		this.openpa = openpa;
		DetailWarehouse = detailWarehouse;
	}
	public int getTransseq() {
		return transseq;
	}
	public void setTransseq(int transseq) {
		this.transseq = transseq;
	}
	public int getTranstype() {
		return transtype;
	}
	public void setTranstype(int transtype) {
		this.transtype = transtype;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public String getBase_ref() {
		return base_ref;
	}
	public void setBase_ref(String base_ref) {
		this.base_ref = base_ref;
	}
	public int getDoclinenum() {
		return doclinenum;
	}
	public void setDoclinenum(int doclinenum) {
		this.doclinenum = doclinenum;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public Double getInqty() {
		return inqty;
	}
	public void setInqty(Double inqty) {
		this.inqty = inqty;
	}
	public Double getOutqty() {
		return outqty;
	}
	public void setOutqty(Double outqty) {
		this.outqty = outqty;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getTrnsfract() {
		return trnsfract;
	}
	public void setTrnsfract(String trnsfract) {
		this.trnsfract = trnsfract;
	}
	public String getPricedifac() {
		return pricedifac;
	}
	public void setPricedifac(String pricedifac) {
		this.pricedifac = pricedifac;
	}
	public String getVarianceac() {
		return varianceac;
	}
	public void setVarianceac(String varianceac) {
		this.varianceac = varianceac;
	}
	public String getReturnact() {
		return returnact;
	}
	public void setReturnact(String returnact) {
		this.returnact = returnact;
	}
	public String getClearact() {
		return clearact;
	}
	public void setClearact(String clearact) {
		this.clearact = clearact;
	}
	public String getCostact() {
		return costact;
	}
	public void setCostact(String costact) {
		this.costact = costact;
	}
	public String getWipact() {
		return wipact;
	}
	public void setWipact(String wipact) {
		this.wipact = wipact;
	}
	public Double getOpenstock() {
		return openstock;
	}
	public void setOpenstock(Double openstock) {
		this.openstock = openstock;
	}
	public Double getPricediff() {
		return pricediff;
	}
	public void setPricediff(Double pricediff) {
		this.pricediff = pricediff;
	}
	public String getInvntact() {
		return invntact;
	}
	public void setInvntact(String invntact) {
		this.invntact = invntact;
	}
	public int getSublinenum() {
		return sublinenum;
	}
	public void setSublinenum(int sublinenum) {
		this.sublinenum = sublinenum;
	}
	public int getAppobjline() {
		return appobjline;
	}
	public void setAppobjline(int appobjline) {
		this.appobjline = appobjline;
	}
	public Double getExpenses() {
		return expenses;
	}
	public void setExpenses(Double expenses) {
		this.expenses = expenses;
	}
	public Double getOpenexp() {
		return openexp;
	}
	public void setOpenexp(Double openexp) {
		this.openexp = openexp;
	}
	public Double getAllocation() {
		return allocation;
	}
	public void setAllocation(Double allocation) {
		this.allocation = allocation;
	}
	public Double getOpenalloc() {
		return openalloc;
	}
	public void setOpenalloc(Double openalloc) {
		this.openalloc = openalloc;
	}
	public Double getExpalloc() {
		return expalloc;
	}
	public void setExpalloc(Double expalloc) {
		this.expalloc = expalloc;
	}
	public Double getOexpalloc() {
		return oexpalloc;
	}
	public void setOexpalloc(Double oexpalloc) {
		this.oexpalloc = oexpalloc;
	}
	public Double getOpenpdiff() {
		return openpdiff;
	}
	public void setOpenpdiff(Double openpdiff) {
		this.openpdiff = openpdiff;
	}
	public Double getNeginvadjs() {
		return neginvadjs;
	}
	public void setNeginvadjs(Double neginvadjs) {
		this.neginvadjs = neginvadjs;
	}
	public Double getOpenneginv() {
		return openneginv;
	}
	public void setOpenneginv(Double openneginv) {
		this.openneginv = openneginv;
	}
	public String getNegstckact() {
		return negstckact;
	}
	public void setNegstckact(String negstckact) {
		this.negstckact = negstckact;
	}
	public Double getBtransval() {
		return btransval;
	}
	public void setBtransval(Double btransval) {
		this.btransval = btransval;
	}
	public Double getVarval() {
		return varval;
	}
	public void setVarval(Double varval) {
		this.varval = varval;
	}
	public Double getBexpval() {
		return bexpval;
	}
	public void setBexpval(Double bexpval) {
		this.bexpval = bexpval;
	}
	public Double getCogsval() {
		return cogsval;
	}
	public void setCogsval(Double cogsval) {
		this.cogsval = cogsval;
	}
	public Double getBnegaval() {
		return bnegaval;
	}
	public void setBnegaval(Double bnegaval) {
		this.bnegaval = bnegaval;
	}
	public String getIoffincacc() {
		return ioffincacc;
	}
	public void setIoffincacc(String ioffincacc) {
		this.ioffincacc = ioffincacc;
	}
	public Double getIoffincval() {
		return ioffincval;
	}
	public void setIoffincval(Double ioffincval) {
		this.ioffincval = ioffincval;
	}
	public String getDoffdecacc() {
		return doffdecacc;
	}
	public void setDoffdecacc(String doffdecacc) {
		this.doffdecacc = doffdecacc;
	}
	public Double getDoffdecval() {
		return doffdecval;
	}
	public void setDoffdecval(Double doffdecval) {
		this.doffdecval = doffdecval;
	}
	public String getDecacc() {
		return decacc;
	}
	public void setDecacc(String decacc) {
		this.decacc = decacc;
	}
	public Double getDecval() {
		return decval;
	}
	public void setDecval(Double decval) {
		this.decval = decval;
	}
	public Double getWipval() {
		return wipval;
	}
	public void setWipval(Double wipval) {
		this.wipval = wipval;
	}
	public String getWipvaracc() {
		return wipvaracc;
	}
	public void setWipvaracc(String wipvaracc) {
		this.wipvaracc = wipvaracc;
	}
	public Double getWipvarval() {
		return wipvarval;
	}
	public void setWipvarval(Double wipvarval) {
		this.wipvarval = wipvarval;
	}
	public String getIncact() {
		return incact;
	}
	public void setIncact(String incact) {
		this.incact = incact;
	}
	public Double getIncval() {
		return incval;
	}
	public void setIncval(Double incval) {
		this.incval = incval;
	}
	public String getExpcacc() {
		return expcacc;
	}
	public void setExpcacc(String expcacc) {
		this.expcacc = expcacc;
	}
	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
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
	public String getPoststatus() {
		return poststatus;
	}
	public void setPoststatus(String poststatus) {
		this.poststatus = poststatus;
	}
	public Double getSumstock() {
		return sumstock;
	}
	public void setSumstock(Double sumstock) {
		this.sumstock = sumstock;
	}
	public Double getOpenqty() {
		return openqty;
	}
	public void setOpenqty(Double openqty) {
		this.openqty = openqty;
	}
	public String getPaoffacc() {
		return paoffacc;
	}
	public void setPaoffacc(String paoffacc) {
		this.paoffacc = paoffacc;
	}
	public Double getPaoffval() {
		return paoffval;
	}
	public void setPaoffval(Double paoffval) {
		this.paoffval = paoffval;
	}
	public Double getOpenpaoff() {
		return openpaoff;
	}
	public void setOpenpaoff(Double openpaoff) {
		this.openpaoff = openpaoff;
	}
	public String getPaacc() {
		return paacc;
	}
	public void setPaacc(String paacc) {
		this.paacc = paacc;
	}
	public Double getPaval() {
		return paval;
	}
	public void setPaval(Double paval) {
		this.paval = paval;
	}
	public Double getOpenpa() {
		return openpa;
	}
	public void setOpenpa(Double openpa) {
		this.openpa = openpa;
	}
	public List getDetailWarehouse() {
		return DetailWarehouse;
	}
	public void setDetailWarehouse(List detailWarehouse) {
		DetailWarehouse = detailWarehouse;
	}
	

}

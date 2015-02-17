package com.sifcoapp.objects.admin.to;

import com.sifcoapp.objects.common.to.CommonTO;

import java.util.Date;
import java.util.List;

public class PricesListInTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3045349286567836074L;
	private int listnum;
	private String listname;
	private int base_num;
	private Double factor;
	private int roundsys;
	private int groupcode;
	private String isgrossprc;
	private String validfor;
	private String roundrule;
	private String rndfrmtint;
	private String rndfrmtdec;

	public PricesListInTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PricesListInTO(int listnum, String listname, int base_num,
			Double factor, int roundsys, int groupcode, String isgrossprc,
			String validfor, String roundrule, String rndfrmtint,
			String rndfrmtdec) {
		super();
		this.listnum = listnum;
		this.listname = listname;
		this.base_num = base_num;
		this.factor = factor;
		this.roundsys = roundsys;
		this.groupcode = groupcode;
		this.isgrossprc = isgrossprc;
		this.validfor = validfor;
		this.roundrule = roundrule;
		this.rndfrmtint = rndfrmtint;
		this.rndfrmtdec = rndfrmtdec;
	}

	public int getListnum() {
		return listnum;
	}

	public void setListnum(int listnum) {
		this.listnum = listnum;
	}

	public String getListname() {
		return listname;
	}

	public void setListname(String listname) {
		this.listname = listname;
	}

	public int getBase_num() {
		return base_num;
	}

	public void setBase_num(int base_num) {
		this.base_num = base_num;
	}

	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public int getRoundsys() {
		return roundsys;
	}

	public void setRoundsys(int roundsys) {
		this.roundsys = roundsys;
	}

	public int getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(int groupcode) {
		this.groupcode = groupcode;
	}

	public String getIsgrossprc() {
		return isgrossprc;
	}

	public void setIsgrossprc(String isgrossprc) {
		this.isgrossprc = isgrossprc;
	}

	public String getValidfor() {
		return validfor;
	}

	public void setValidfor(String validfor) {
		this.validfor = validfor;
	}

	public String getRoundrule() {
		return roundrule;
	}

	public void setRoundrule(String roundrule) {
		this.roundrule = roundrule;
	}

	public String getRndfrmtint() {
		return rndfrmtint;
	}

	public void setRndfrmtint(String rndfrmtint) {
		this.rndfrmtint = rndfrmtint;
	}

	public String getRndfrmtdec() {
		return rndfrmtdec;
	}

	public void setRndfrmtdec(String rndfrmtdec) {
		this.rndfrmtdec = rndfrmtdec;
	}

}

package com.sifcoapp.objects.accounting.to;



import com.sifcoapp.objects.common.to.CommonTO;

import java.util.Date;
import java.util.List;

public class AccountTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7118087486780523338L;

	private String acctcode;
	private String acctname;
	private Double currtotal;
	private Double endtotal;
	private String finanse;
	private String budget;
	private String postable;
	private int levels;
	private int grpline;
	private String fathernum;
	private int groupmask;
	private int intrmatch;
	private String acttype;
	private String protected1; // Le agregue el 1 al final porque protected es una palabra reservada de Java
	private Date createdate;
	private Date updatedate;
	private int usersign;
	private String objtype;
	private String validfor;
	private String formatcode;
	private List nodeDetail;

	public AccountTO(String acctcode, String acctname, Double currtotal,
			Double endtotal, String finanse, String budget, String postable,
			int levels, int grpline, String fathernum, int groupmask,
			int intrmatch, String acttype, String protected1, Date createdate,
			Date updatedate, int usersign, String objtype, String validfor,
			String formatcode, List nodedetail) {
		super();
		this.acctcode = acctcode;
		this.acctname = acctname;
		this.currtotal = currtotal;
		this.endtotal = endtotal;
		this.finanse = finanse;
		this.budget = budget;
		this.postable = postable;
		this.levels = levels;
		this.grpline = grpline;
		this.fathernum = fathernum;
		this.groupmask = groupmask;
		this.intrmatch = intrmatch;
		this.acttype = acttype;
		this.protected1 = protected1;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.usersign = usersign;
		this.objtype = objtype;
		this.validfor = validfor;
		this.formatcode = formatcode;
		this.nodeDetail = nodedetail;
	}

	public String getAcctcode() {
		return acctcode;
	}

	public void setAcctcode(String acctcode) {
		this.acctcode = acctcode;
	}

	public String getAcctname() {
		return acctname;
	}

	public void setAcctname(String acctname) {
		this.acctname = acctname;
	}

	public Double getCurrtotal() {
		return currtotal;
	}

	public void setCurrtotal(Double currtotal) {
		this.currtotal = currtotal;
	}

	public Double getEndtotal() {
		return endtotal;
	}

	public void setEndtotal(Double endtotal) {
		this.endtotal = endtotal;
	}

	public String getFinanse() {
		return finanse;
	}

	public void setFinanse(String finanse) {
		this.finanse = finanse;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getPostable() {
		return postable;
	}

	public void setPostable(String postable) {
		this.postable = postable;
	}

	public int getLevels() {
		return levels;
	}

	public void setLevels(int levels) {
		this.levels = levels;
	}

	public int getGrpline() {
		return grpline;
	}

	public void setGrpline(int grpline) {
		this.grpline = grpline;
	}

	public String getFathernum() {
		return fathernum;
	}

	public void setFathernum(String fathernum) {
		this.fathernum = fathernum;
	}

	public int getGroupmask() {
		return groupmask;
	}

	public void setGroupmask(int groupmask) {
		this.groupmask = groupmask;
	}

	public int getIntrmatch() {
		return intrmatch;
	}

	public void setIntrmatch(int intrmatch) {
		this.intrmatch = intrmatch;
	}

	public String getActtype() {
		return acttype;
	}

	public void setActtype(String acttype) {
		this.acttype = acttype;
	}

	public String getProtected1() {
		return protected1;
	}

	public void setProtected1(String protected1) {
		this.protected1 = protected1;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public int getUsersign() {
		return usersign;
	}

	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}

	public String getObjtype() {
		return objtype;
	}

	public void setObjtype(String objtype) {
		this.objtype = objtype;
	}

	public String getValidfor() {
		return validfor;
	}

	public void setValidfor(String validfor) {
		this.validfor = validfor;
	}

	public String getFormatcode() {
		return formatcode;
	}

	public void setFormatcode(String formatcode) {
		this.formatcode = formatcode;
	}

	public AccountTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List getNodedetail() {
		return nodeDetail;
	}

	public void setNodedetail(List nodedetail) {
		this.nodeDetail = nodedetail;
	}

	

}

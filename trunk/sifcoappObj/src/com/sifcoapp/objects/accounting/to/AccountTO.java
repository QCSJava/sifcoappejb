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
	private String frozen;
	private String postable;
	private String fixed;
	private int levels;
	private int grpline;
	private String fathernum;
	private int groupmask;
	private String acttype;
	private String protected1;
	private String objtype;
	private String validfor;
	private String frozenfor;
	private int counter;
	private String formatcode;
	private Date createdate;
	private int usersing;
	private List Child;
	public AccountTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountTO(String acctcode, String acctname, Double currtotal,
			Double endtotal, String finanse, String budget, String frozen,
			String postable, String fixed, int levels, int grpline,
			String fathernum, int groupmask, String acttype, String protected1,
			String objtype, String validfor, String frozenfor, int counter,
			String formatcode, Date createdate, int usersing, List child) {
		super();
		this.acctcode = acctcode;
		this.acctname = acctname;
		this.currtotal = currtotal;
		this.endtotal = endtotal;
		this.finanse = finanse;
		this.budget = budget;
		this.frozen = frozen;
		this.postable = postable;
		this.fixed = fixed;
		this.levels = levels;
		this.grpline = grpline;
		this.fathernum = fathernum;
		this.groupmask = groupmask;
		this.acttype = acttype;
		this.protected1 = protected1;
		this.objtype = objtype;
		this.validfor = validfor;
		this.frozenfor = frozenfor;
		this.counter = counter;
		this.formatcode = formatcode;
		this.createdate = createdate;
		this.usersing = usersing;
		Child = child;
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
	public String getFrozen() {
		return frozen;
	}
	public void setFrozen(String frozen) {
		this.frozen = frozen;
	}
	public String getPostable() {
		return postable;
	}
	public void setPostable(String postable) {
		this.postable = postable;
	}
	public String getFixed() {
		return fixed;
	}
	public void setFixed(String fixed) {
		this.fixed = fixed;
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
	public String getFrozenfor() {
		return frozenfor;
	}
	public void setFrozenfor(String frozenfor) {
		this.frozenfor = frozenfor;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public String getFormatcode() {
		return formatcode;
	}
	public void setFormatcode(String formatcode) {
		this.formatcode = formatcode;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public int getUsersing() {
		return usersing;
	}
	public void setUsersing(int usersing) {
		this.usersing = usersing;
	}
	public List getChild() {
		return Child;
	}
	public void setChild(List child) {
		Child = child;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}

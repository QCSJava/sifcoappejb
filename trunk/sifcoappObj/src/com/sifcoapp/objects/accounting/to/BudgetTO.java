package com.sifcoapp.objects.accounting.to;



import com.sifcoapp.objects.common.to.CommonTO;

import java.util.Date;
import java.util.List;

public class BudgetTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1049143570595305449L;
	/**
	 * 
	 */


	private int absid;
	private String acctcode;
	private String acctname;
	private int bgdcode;
	private String fathercode;
	private Double fthrprcnt;
	private Double debltotal;
	private Double credltotal;
	private Double debrltotal;
	private Double crdrltotal;
	private Double ftridrlsum;
	private Double ftridrssum;
	private Double ftrodrlsum;
	private Double ftrocrlsum;
	private Date financyear;
	private int usersign;
	private String postable;
	private List nodeDetail;
	private Double currtotal;
	public BudgetTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BudgetTO(int absid, String acctcode, String acctname, int bgdcode,
			String fathercode, Double fthrprcnt, Double debltotal,
			Double credltotal, Double debrltotal, Double crdrltotal,
			Double ftridrlsum, Double ftridrssum, Double ftrodrlsum,
			Double ftrocrlsum, Date financyear, int usersign, String postable,
			List nodeDetail, Double currtotal) {
		super();
		this.absid = absid;
		this.acctcode = acctcode;
		this.acctname = acctname;
		this.bgdcode = bgdcode;
		this.fathercode = fathercode;
		this.fthrprcnt = fthrprcnt;
		this.debltotal = debltotal;
		this.credltotal = credltotal;
		this.debrltotal = debrltotal;
		this.crdrltotal = crdrltotal;
		this.ftridrlsum = ftridrlsum;
		this.ftridrssum = ftridrssum;
		this.ftrodrlsum = ftrodrlsum;
		this.ftrocrlsum = ftrocrlsum;
		this.financyear = financyear;
		this.usersign = usersign;
		this.postable = postable;
		this.nodeDetail = nodeDetail;
		this.currtotal = currtotal;
	}
	public int getAbsid() {
		return absid;
	}
	public void setAbsid(int absid) {
		this.absid = absid;
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
	public int getBgdcode() {
		return bgdcode;
	}
	public void setBgdcode(int bgdcode) {
		this.bgdcode = bgdcode;
	}
	public String getFathercode() {
		return fathercode;
	}
	public void setFathercode(String fathercode) {
		this.fathercode = fathercode;
	}
	public Double getFthrprcnt() {
		return fthrprcnt;
	}
	public void setFthrprcnt(Double fthrprcnt) {
		this.fthrprcnt = fthrprcnt;
	}
	public Double getDebltotal() {
		return debltotal;
	}
	public void setDebltotal(Double debltotal) {
		this.debltotal = debltotal;
	}
	public Double getCredltotal() {
		return credltotal;
	}
	public void setCredltotal(Double credltotal) {
		this.credltotal = credltotal;
	}
	public Double getDebrltotal() {
		return debrltotal;
	}
	public void setDebrltotal(Double debrltotal) {
		this.debrltotal = debrltotal;
	}
	public Double getCrdrltotal() {
		return crdrltotal;
	}
	public void setCrdrltotal(Double crdrltotal) {
		this.crdrltotal = crdrltotal;
	}
	public Double getFtridrlsum() {
		return ftridrlsum;
	}
	public void setFtridrlsum(Double ftridrlsum) {
		this.ftridrlsum = ftridrlsum;
	}
	public Double getFtridrssum() {
		return ftridrssum;
	}
	public void setFtridrssum(Double ftridrssum) {
		this.ftridrssum = ftridrssum;
	}
	public Double getFtrodrlsum() {
		return ftrodrlsum;
	}
	public void setFtrodrlsum(Double ftrodrlsum) {
		this.ftrodrlsum = ftrodrlsum;
	}
	public Double getFtrocrlsum() {
		return ftrocrlsum;
	}
	public void setFtrocrlsum(Double ftrocrlsum) {
		this.ftrocrlsum = ftrocrlsum;
	}
	public Date getFinancyear() {
		return financyear;
	}
	public void setFinancyear(Date financyear) {
		this.financyear = financyear;
	}
	public int getUsersign() {
		return usersign;
	}
	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}
	public String getPostable() {
		return postable;
	}
	public void setPostable(String postable) {
		this.postable = postable;
	}
	public List getNodeDetail() {
		return nodeDetail;
	}
	public void setNodeDetail(List nodeDetail) {
		this.nodeDetail = nodeDetail;
	}
	public Double getCurrtotal() {
		return currtotal;
	}
	public void setCurrtotal(Double currtotal) {
		this.currtotal = currtotal;
	}
	
}

package com.sifcoapp.objects.accounting.to;

import java.util.Date;
import java.util.List;

import com.sifcoapp.objects.common.to.CommonTO;

public class RecurringPostingsTO extends CommonTO  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8042459756050283113L;
	private String rcurcode;
	private String rcurdesc;
	private String frequency;
	private int remind;
	private Date lastposted;
	private Date nextdeu;
	private int entrycount;
	private Double volume;
	private String volcurr;
	private Double financvol;
	private String ref1;
	private String ref2;
	private String transcode;
	private String memo;
	private String limitrtrns;
	private int returns;
	private Date limitdate;
	private int instance;
	private String autovat;
	private String managewtax;
	private String ref3;
	private String deferedtax;
	private int usersign;

	private List RecurringPostingsDetail;

	public RecurringPostingsTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecurringPostingsTO(String rcurcode, String rcurdesc,
			String frequency, int remind, Date lastposted, Date nextdeu,
			int entrycount, Double volume, String volcurr, Double financvol,
			String ref1, String ref2, String transcode, String memo,
			String limitrtrns, int returns, Date limitdate, int instance,
			String autovat, String managewtax, String ref3, String deferedtax,
			int usersign, List recurringPostingsDetail) {
		super();
		this.rcurcode = rcurcode;
		this.rcurdesc = rcurdesc;
		this.frequency = frequency;
		this.remind = remind;
		this.lastposted = lastposted;
		this.nextdeu = nextdeu;
		this.entrycount = entrycount;
		this.volume = volume;
		this.volcurr = volcurr;
		this.financvol = financvol;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.transcode = transcode;
		this.memo = memo;
		this.limitrtrns = limitrtrns;
		this.returns = returns;
		this.limitdate = limitdate;
		this.instance = instance;
		this.autovat = autovat;
		this.managewtax = managewtax;
		this.ref3 = ref3;
		this.deferedtax = deferedtax;
		this.usersign = usersign;
		RecurringPostingsDetail = recurringPostingsDetail;
	}

	public String getRcurcode() {
		return rcurcode;
	}

	public void setRcurcode(String rcurcode) {
		this.rcurcode = rcurcode;
	}

	public String getRcurdesc() {
		return rcurdesc;
	}

	public void setRcurdesc(String rcurdesc) {
		this.rcurdesc = rcurdesc;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public int getRemind() {
		return remind;
	}

	public void setRemind(int remind) {
		this.remind = remind;
	}

	public Date getLastposted() {
		return lastposted;
	}

	public void setLastposted(Date lastposted) {
		this.lastposted = lastposted;
	}

	public Date getNextdeu() {
		return nextdeu;
	}

	public void setNextdeu(Date nextdeu) {
		this.nextdeu = nextdeu;
	}

	public int getEntrycount() {
		return entrycount;
	}

	public void setEntrycount(int entrycount) {
		this.entrycount = entrycount;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public String getVolcurr() {
		return volcurr;
	}

	public void setVolcurr(String volcurr) {
		this.volcurr = volcurr;
	}

	public Double getFinancvol() {
		return financvol;
	}

	public void setFinancvol(Double financvol) {
		this.financvol = financvol;
	}

	public String getRef1() {
		return ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	public String getTranscode() {
		return transcode;
	}

	public void setTranscode(String transcode) {
		this.transcode = transcode;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getLimitrtrns() {
		return limitrtrns;
	}

	public void setLimitrtrns(String limitrtrns) {
		this.limitrtrns = limitrtrns;
	}

	public int getReturns() {
		return returns;
	}

	public void setReturns(int returns) {
		this.returns = returns;
	}

	public Date getLimitdate() {
		return limitdate;
	}

	public void setLimitdate(Date limitdate) {
		this.limitdate = limitdate;
	}

	public int getInstance() {
		return instance;
	}

	public void setInstance(int instance) {
		this.instance = instance;
	}

	public String getAutovat() {
		return autovat;
	}

	public void setAutovat(String autovat) {
		this.autovat = autovat;
	}

	public String getManagewtax() {
		return managewtax;
	}

	public void setManagewtax(String managewtax) {
		this.managewtax = managewtax;
	}

	public String getRef3() {
		return ref3;
	}

	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}

	public String getDeferedtax() {
		return deferedtax;
	}

	public void setDeferedtax(String deferedtax) {
		this.deferedtax = deferedtax;
	}

	public int getUsersign() {
		return usersign;
	}

	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}

	public List getRecurringPostingsDetail() {
		return RecurringPostingsDetail;
	}

	public void setRecurringPostingsDetail(List recurringPostingsDetail) {
		RecurringPostingsDetail = recurringPostingsDetail;
	}
	
}

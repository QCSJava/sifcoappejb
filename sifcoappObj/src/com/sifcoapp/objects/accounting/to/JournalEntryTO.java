package com.sifcoapp.objects.accounting.to;

import java.util.Date;
import java.util.List;

import com.sifcoapp.objects.common.to.CommonTO;

public class JournalEntryTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5217890900475113206L;

	private int transid;
	private int batchnum;
	private String btfstatus;
	private String transtype;
	private String baseref;
	private Date refdate;
	private String memo;
	private String ref1;
	private String ref2;
	private Double loctotal;
	private Double systotal;
	private String transcode;
	private Double transrate;
	private int btfline;
	private Date duedate;
	private Date taxdate;
	private int finncpriod;
	private String refndrprt;
	private String objtype;
	private String indicator;
	private String adjtran;
	private Date stornodate;
	private int stornototr;
	private String autostorno;
	private Date vatdate;
	private int series;
	private int number;
	private String autovat;
	private int docseries;
	private String printed;
	private String doctype;
	private String creator;
	private int seqcode;
	private int serial;
	private String autowt;
	private Double wtapplied;
	private Double baseamnt;
	private Double basevtat;
	private int basetrans;
	private String ref3;
	private String deferedtax;
	private int agrno;
	private int seqnum;
	private String rptperiod;
	private int usersign;
	
	private List journalentryList;
	
	public JournalEntryTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JournalEntryTO(int transid, int batchnum, String btfstatus,
			String transtype, String baseref, Date refdate, String memo,
			String ref1, String ref2, Double loctotal, Double systotal,
			String transcode, Double transrate, int btfline, Date duedate,
			Date taxdate, int finncpriod, String refndrprt, String objtype,
			String indicator, String adjtran, Date stornodate, int stornototr,
			String autostorno, Date vatdate, int series, int number,
			String autovat, int docseries, String printed, String doctype,
			String creator, int seqcode, int serial, String autowt,
			Double wtapplied, Double baseamnt, Double basevtat, int basetrans,
			String ref3, String deferedtax, int agrno, int seqnum,
			String rptperiod, int usersign, List journalentryList) {
		super();
		this.transid = transid;
		this.batchnum = batchnum;
		this.btfstatus = btfstatus;
		this.transtype = transtype;
		this.baseref = baseref;
		this.refdate = refdate;
		this.memo = memo;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.loctotal = loctotal;
		this.systotal = systotal;
		this.transcode = transcode;
		this.transrate = transrate;
		this.btfline = btfline;
		this.duedate = duedate;
		this.taxdate = taxdate;
		this.finncpriod = finncpriod;
		this.refndrprt = refndrprt;
		this.objtype = objtype;
		this.indicator = indicator;
		this.adjtran = adjtran;
		this.stornodate = stornodate;
		this.stornototr = stornototr;
		this.autostorno = autostorno;
		this.vatdate = vatdate;
		this.series = series;
		this.number = number;
		this.autovat = autovat;
		this.docseries = docseries;
		this.printed = printed;
		this.doctype = doctype;
		this.creator = creator;
		this.seqcode = seqcode;
		this.serial = serial;
		this.autowt = autowt;
		this.wtapplied = wtapplied;
		this.baseamnt = baseamnt;
		this.basevtat = basevtat;
		this.basetrans = basetrans;
		this.ref3 = ref3;
		this.deferedtax = deferedtax;
		this.agrno = agrno;
		this.seqnum = seqnum;
		this.rptperiod = rptperiod;
		this.usersign = usersign;
		this.journalentryList = journalentryList;
	}

	public int getTransid() {
		return transid;
	}

	public void setTransid(int transid) {
		this.transid = transid;
	}

	public int getBatchnum() {
		return batchnum;
	}

	public void setBatchnum(int batchnum) {
		this.batchnum = batchnum;
	}

	public String getBtfstatus() {
		return btfstatus;
	}

	public void setBtfstatus(String btfstatus) {
		this.btfstatus = btfstatus;
	}

	public String getTranstype() {
		return transtype;
	}

	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}

	public String getBaseref() {
		return baseref;
	}

	public void setBaseref(String baseref) {
		this.baseref = baseref;
	}

	public Date getRefdate() {
		return refdate;
	}

	public void setRefdate(Date refdate) {
		this.refdate = refdate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public Double getLoctotal() {
		return loctotal;
	}

	public void setLoctotal(Double loctotal) {
		this.loctotal = loctotal;
	}

	public Double getSystotal() {
		return systotal;
	}

	public void setSystotal(Double systotal) {
		this.systotal = systotal;
	}

	public String getTranscode() {
		return transcode;
	}

	public void setTranscode(String transcode) {
		this.transcode = transcode;
	}

	public Double getTransrate() {
		return transrate;
	}

	public void setTransrate(Double transrate) {
		this.transrate = transrate;
	}

	public int getBtfline() {
		return btfline;
	}

	public void setBtfline(int btfline) {
		this.btfline = btfline;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public Date getTaxdate() {
		return taxdate;
	}

	public void setTaxdate(Date taxdate) {
		this.taxdate = taxdate;
	}

	public int getFinncpriod() {
		return finncpriod;
	}

	public void setFinncpriod(int finncpriod) {
		this.finncpriod = finncpriod;
	}

	public String getRefndrprt() {
		return refndrprt;
	}

	public void setRefndrprt(String refndrprt) {
		this.refndrprt = refndrprt;
	}

	public String getObjtype() {
		return objtype;
	}

	public void setObjtype(String objtype) {
		this.objtype = objtype;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getAdjtran() {
		return adjtran;
	}

	public void setAdjtran(String adjtran) {
		this.adjtran = adjtran;
	}

	public Date getStornodate() {
		return stornodate;
	}

	public void setStornodate(Date stornodate) {
		this.stornodate = stornodate;
	}

	public int getStornototr() {
		return stornototr;
	}

	public void setStornototr(int stornototr) {
		this.stornototr = stornototr;
	}

	public String getAutostorno() {
		return autostorno;
	}

	public void setAutostorno(String autostorno) {
		this.autostorno = autostorno;
	}

	public Date getVatdate() {
		return vatdate;
	}

	public void setVatdate(Date vatdate) {
		this.vatdate = vatdate;
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

	public String getAutovat() {
		return autovat;
	}

	public void setAutovat(String autovat) {
		this.autovat = autovat;
	}

	public int getDocseries() {
		return docseries;
	}

	public void setDocseries(int docseries) {
		this.docseries = docseries;
	}

	public String getPrinted() {
		return printed;
	}

	public void setPrinted(String printed) {
		this.printed = printed;
	}

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getSeqcode() {
		return seqcode;
	}

	public void setSeqcode(int seqcode) {
		this.seqcode = seqcode;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getAutowt() {
		return autowt;
	}

	public void setAutowt(String autowt) {
		this.autowt = autowt;
	}

	public Double getWtapplied() {
		return wtapplied;
	}

	public void setWtapplied(Double wtapplied) {
		this.wtapplied = wtapplied;
	}

	public Double getBaseamnt() {
		return baseamnt;
	}

	public void setBaseamnt(Double baseamnt) {
		this.baseamnt = baseamnt;
	}

	public Double getBasevtat() {
		return basevtat;
	}

	public void setBasevtat(Double basevtat) {
		this.basevtat = basevtat;
	}

	public int getBasetrans() {
		return basetrans;
	}

	public void setBasetrans(int basetrans) {
		this.basetrans = basetrans;
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

	public int getAgrno() {
		return agrno;
	}

	public void setAgrno(int agrno) {
		this.agrno = agrno;
	}

	public int getSeqnum() {
		return seqnum;
	}

	public void setSeqnum(int seqnum) {
		this.seqnum = seqnum;
	}

	public String getRptperiod() {
		return rptperiod;
	}

	public void setRptperiod(String rptperiod) {
		this.rptperiod = rptperiod;
	}

	public int getUsersign() {
		return usersign;
	}

	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}

	public List getJournalentryList() {
		return journalentryList;
	}

	public void setJournalentryList(List journalentryList) {
		this.journalentryList = journalentryList;
	}

}

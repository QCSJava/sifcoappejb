package com.sifcoapp.objects.accounting.to;

import com.sifcoapp.objects.common.to.CommonTO;

import java.util.Date;

public class AccPeriodTO extends CommonTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7752239649466307256L;

	private int absentry;
	private String acccode;
	private String accname;
	private Date f_refdate;
	private Date t_refdate;
	private Date f_duedate;
	private Date t_duedate;
	private Date f_taxdate;
	private Date t_taxdate;
	private int periodstat;
	private int usersign;

	public AccPeriodTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccPeriodTO(int absentry, String acccode, String accname,
			Date f_refdate, Date t_refdate, Date f_duedate, Date t_duedate,
			Date f_taxdate, Date t_taxdate, int periodstat, int usersign) {
		super();
		this.absentry = absentry;
		this.acccode = acccode;
		this.accname = accname;
		this.f_refdate = f_refdate;
		this.t_refdate = t_refdate;
		this.f_duedate = f_duedate;
		this.t_duedate = t_duedate;
		this.f_taxdate = f_taxdate;
		this.t_taxdate = t_taxdate;
		this.periodstat = periodstat;
		this.usersign = usersign;
	}

	public int getAbsentry() {
		return absentry;
	}

	public void setAbsentry(int absentry) {
		this.absentry = absentry;
	}

	public String getAcccode() {
		return acccode;
	}

	public void setAcccode(String acccode) {
		this.acccode = acccode;
	}

	public String getAccname() {
		return accname;
	}

	public void setAccname(String accname) {
		this.accname = accname;
	}

	public Date getF_refdate() {
		return f_refdate;
	}

	public void setF_refdate(Date f_refdate) {
		this.f_refdate = f_refdate;
	}

	public Date getT_refdate() {
		return t_refdate;
	}

	public void setT_refdate(Date t_refdate) {
		this.t_refdate = t_refdate;
	}

	public Date getF_duedate() {
		return f_duedate;
	}

	public void setF_duedate(Date f_duedate) {
		this.f_duedate = f_duedate;
	}

	public Date getT_duedate() {
		return t_duedate;
	}

	public void setT_duedate(Date t_duedate) {
		this.t_duedate = t_duedate;
	}

	public Date getF_taxdate() {
		return f_taxdate;
	}

	public void setF_taxdate(Date f_taxdate) {
		this.f_taxdate = f_taxdate;
	}

	public Date getT_taxdate() {
		return t_taxdate;
	}

	public void setT_taxdate(Date t_taxdate) {
		this.t_taxdate = t_taxdate;
	}

	public int getPeriodstat() {
		return periodstat;
	}

	public void setPeriodstat(int periodstat) {
		this.periodstat = periodstat;
	}

	public int getUsersign() {
		return usersign;
	}

	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}



}

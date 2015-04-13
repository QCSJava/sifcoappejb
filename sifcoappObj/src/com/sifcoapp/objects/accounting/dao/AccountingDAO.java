package com.sifcoapp.objects.accounting.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.ejb.EJBException;

import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.accounting.to.BudgetTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsDetailTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsInTO;
import com.sifcoapp.objects.accounting.to.RecurringPostingsTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;
import com.sifcoapp.objects.security.to.ProfileDetOutTO;
import com.sun.rowset.CachedRowSetImpl;

public class AccountingDAO extends CommonDAO {
	/*
	 * Mantenimiento de periodos contables
	 */

	public int cat_accPeriod_mtto(AccPeriodTO parameters, int action)
			throws Exception {

		int v_resp = 0;
		// this.setDbObject("{call sp_cat_acc_period_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_cat_acc_period_mtto(?,?,?,?,?,?,?,?,?,?,?)}");
		this.setString(1, "_acccode", parameters.getAcccode());
		this.setString(2, "_accname", parameters.getAccname());
		this.setDate(3, "_f_refdate", parameters.getF_refdate());
		this.setDate(4, "_t_refdate", parameters.getT_refdate());
		this.setDate(5, "_f_duedate", parameters.getF_duedate());
		this.setDate(6, "_t_duedate", parameters.getT_duedate());
		this.setDate(7, "_f_taxdate", parameters.getF_taxdate());
		this.setDate(8, "_t_taxdate", parameters.getT_taxdate());
		this.setInt(9, "_periodstat", new Integer(parameters.getPeriodstat()));
		this.setInt(10, "_usersign", new Integer(parameters.getUsersign()));
		this.setInt(11, "_action", new Integer(action));

		v_resp = this.runUpdate();

		return v_resp;
	}

	public AccountingDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountingDAO(Connection _conn) {
		super(_conn);
		// TODO Auto-generated constructor stub
	}

	public List getTreeAccount() throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_account(?)}");
		this.setInt(1, "_type", 1); // para devolver todas las cuentas
		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		Hashtable _values = new Hashtable();
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					AccountTO account = new AccountTO();
					account.setAcctcode(rowsetActual.getString(1));
					account.setAcctname(rowsetActual.getString(2));
					account.setCurrtotal(rowsetActual.getDouble(3));
					account.setEndtotal(rowsetActual.getDouble(4));
					account.setFinanse(rowsetActual.getString(5));
					account.setBudget(rowsetActual.getString(6));
					account.setFrozen(rowsetActual.getString(7));
					account.setPostable(rowsetActual.getString(8));
					account.setFixed(rowsetActual.getString(9));
					account.setLevels(rowsetActual.getInt(10));
					account.setGrpline(rowsetActual.getInt(11));
					account.setFathernum(rowsetActual.getString(12));
					account.setGroupmask(rowsetActual.getInt(13));
					account.setActtype(rowsetActual.getString(14));
					account.setProtected1(rowsetActual.getString(15));
					account.setObjtype(rowsetActual.getString(16));
					account.setValidfor(rowsetActual.getString(17));
					account.setFrozenfor(rowsetActual.getString(18));
					account.setCounter(rowsetActual.getInt(19));
					account.setFormatcode(rowsetActual.getString(20));
					account.setCreatedate(rowsetActual.getDate(21));
					account.setUsersing(rowsetActual.getInt(22));

					_values.put(account.getAcctcode(), account);
					// _return.add(account);
				}
				AccountTO profileDetTmp = null;
				String _position = null;
				List lstDetProfile = new Vector();

				String[] claves = (String[]) _values.keySet().toArray(
						new String[0]);
				java.util.Arrays.sort(claves);

				// partimos de los nodos sin hijos
				for (String clave : claves) {
					profileDetTmp = (AccountTO) _values.get(clave);
					if (profileDetTmp.getFathernum() == null) {
						profileDetTmp.setCurrtotal(0.00);
						this.filterParent(profileDetTmp, _values,
								profileDetTmp.getAcctcode());
						_return.add(profileDetTmp);
					}
				}
				// while (enParameters.hasMoreElements()) {

				// }
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	private void filterParent(AccountTO parent, Hashtable _allvalues,
			String parentFilter) {

		// Enumeration enParameters = _allvalues.keys();
		AccountTO profileDetTmp = null;
		String _position = null;
		List lstDetProfile = new Vector();

		// partimos de los nodos sin hijos

		String[] claves = (String[]) _allvalues.keySet().toArray(new String[0]);
		java.util.Arrays.sort(claves);

		for (String clave : claves) {
			// _position = (String) enParameters.nextElement();
			profileDetTmp = (AccountTO) _allvalues.get(clave);

			String padre = profileDetTmp.getFathernum();

			if (padre != null && padre.equals(parentFilter)) {

				this.filterParent(profileDetTmp, _allvalues,
						profileDetTmp.getAcctcode());

				lstDetProfile.add(profileDetTmp);

				parent.setCurrtotal(parent.getCurrtotal()
						+ profileDetTmp.getCurrtotal());

			}

		}

		parent.setChild(lstDetProfile);

	}

	public List getAccount(int type) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_account(?)}");
		this.setInt(1, "_type", type);

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					AccountTO account = new AccountTO();
					account.setAcctcode(rowsetActual.getString(1));
					account.setAcctname(rowsetActual.getString(2));
					account.setCurrtotal(rowsetActual.getDouble(3));
					account.setEndtotal(rowsetActual.getDouble(4));
					account.setFinanse(rowsetActual.getString(5));
					account.setBudget(rowsetActual.getString(6));
					account.setFrozen(rowsetActual.getString(7));
					account.setPostable(rowsetActual.getString(8));
					account.setFixed(rowsetActual.getString(9));
					account.setLevels(rowsetActual.getInt(10));
					account.setGrpline(rowsetActual.getInt(11));
					account.setFathernum(rowsetActual.getString(12));
					account.setGroupmask(rowsetActual.getInt(13));
					account.setActtype(rowsetActual.getString(14));
					account.setProtected1(rowsetActual.getString(15));
					account.setObjtype(rowsetActual.getString(16));
					account.setValidfor(rowsetActual.getString(17));
					account.setFrozenfor(rowsetActual.getString(18));
					account.setCounter(rowsetActual.getInt(19));
					account.setFormatcode(rowsetActual.getString(20));
					account.setCreatedate(rowsetActual.getDate(21));

					_return.add(account);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	// ######### RETORNA REGISTRO DE ACCOUNT POR FILTROS
	// ############################
	// ######### RETORNA REGISTRO DE ACCOUNT POR FILTROS
	// ############################
	public List getAccountByFilter(String acctcode, String acctname)
			throws Exception {
		return getAccountByFilter(acctcode, acctname, null);
	}

	public List getAccountByFilter(String acctcode, String acctname,
			String postable) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_acc0_account(?,?,?)}");
		this.setString(1, "_acctcode", acctcode);
		this.setString(2, "_acctname", acctname);
		this.setString(3, "_postable", postable);
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					AccountTO account = new AccountTO();
					account.setAcctcode(rowsetActual.getString(1));
					account.setAcctname(rowsetActual.getString(2));
					account.setCurrtotal(rowsetActual.getDouble(3));
					account.setEndtotal(rowsetActual.getDouble(4));
					account.setFinanse(rowsetActual.getString(5));
					account.setBudget(rowsetActual.getString(6));
					account.setFrozen(rowsetActual.getString(7));
					account.setPostable(rowsetActual.getString(8));
					account.setFixed(rowsetActual.getString(9));
					account.setLevels(rowsetActual.getInt(10));
					account.setGrpline(rowsetActual.getInt(11));
					account.setFathernum(rowsetActual.getString(12));
					account.setGroupmask(rowsetActual.getInt(13));
					account.setActtype(rowsetActual.getString(14));
					account.setProtected1(rowsetActual.getString(15));
					account.setObjtype(rowsetActual.getString(16));
					account.setValidfor(rowsetActual.getString(17));
					account.setFrozenfor(rowsetActual.getString(18));
					account.setCounter(rowsetActual.getInt(19));
					account.setFormatcode(rowsetActual.getString(20));
					account.setCreatedate(rowsetActual.getDate(21));
					account.setUsersing(rowsetActual.getInt(22));

					_return.add(account);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	// ######### RETORNA REGISTRO DE ACCOUNT POR CLAVE
	// ############################
	public AccountTO getAccountByKey(String acctcode) throws Exception {
		AccountTO _return = new AccountTO();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_acc0_account_by_key(?)}");
		this.setString(1, "_acctcode", acctcode);

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					AccountTO account = new AccountTO();
					account.setAcctcode(rowsetActual.getString(1));
					account.setAcctname(rowsetActual.getString(2));
					account.setCurrtotal(rowsetActual.getDouble(3));
					account.setEndtotal(rowsetActual.getDouble(4));
					account.setFinanse(rowsetActual.getString(5));
					account.setBudget(rowsetActual.getString(6));
					account.setFrozen(rowsetActual.getString(7));
					account.setPostable(rowsetActual.getString(8));
					account.setFixed(rowsetActual.getString(9));
					account.setLevels(rowsetActual.getInt(10));
					account.setGrpline(rowsetActual.getInt(11));
					account.setFathernum(rowsetActual.getString(12));
					account.setGroupmask(rowsetActual.getInt(13));
					account.setActtype(rowsetActual.getString(14));
					account.setProtected1(rowsetActual.getString(15));
					account.setObjtype(rowsetActual.getString(16));
					account.setValidfor(rowsetActual.getString(17));
					account.setFrozenfor(rowsetActual.getString(18));
					account.setCounter(rowsetActual.getInt(19));
					account.setFormatcode(rowsetActual.getString(20));
					account.setCreatedate(rowsetActual.getDate(21));
                    account.setUsersing(rowsetActual.getInt(22));
					_return = account;
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	// ##################### MANTEMINIENTO DE LA TABLA ACCOUNT
	// ############################
	public int account_mtto_new(AccountTO parameters) throws Exception{
		
		int v_resp = 0;
		// this.setDbObject("{call sp_cat_acc_period(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_acc_account_updtre(?,?,?,?,?)}");
	
		this.setDouble(1,"_endtotal",0.0);
		this.setInt(2,"_levels", parameters.getLevels());
		this.setInt(3,"_grpline", parameters.getGrpline());
		this.setString(4,"_fathernum", parameters.getFathernum());
		this.setInt(5,"_groupmask", parameters.getGroupmask());
		
		v_resp= this.runUpdate();
		return v_resp;
		
	}
	public int cat_acc0_ACCOUNT_mtto(AccountTO parameters, int action)
			throws Exception {

		int v_resp = 0;
		// this.setDbObject("{call sp_cat_acc_period(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_acc0_account_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setString(1,"_acctcode", parameters.getAcctcode());
		this.setString(2,"_acctname", parameters.getAcctname());
		this.setDouble(3,"_currtotal", parameters.getCurrtotal());
		this.setDouble(4,"_endtotal", parameters.getEndtotal());
		this.setString(5,"_finanse", parameters.getFinanse());
		this.setString(6,"_budget", parameters.getBudget());
		this.setString(7,"_frozen", parameters.getFrozen());
		this.setString(8,"_postable", parameters.getPostable());
		this.setString(9,"_fixed", parameters.getFixed());
		this.setInt(10,"_levels", parameters.getLevels());
		this.setInt(11,"_grpline", parameters.getGrpline());
		this.setString(12,"_fathernum", parameters.getFathernum());
		this.setInt(13,"_groupmask", parameters.getGroupmask());
		this.setString(14,"_acttype", parameters.getActtype());
		this.setString(15,"_protected", parameters.getProtected1());
		this.setString(16,"_objtype", parameters.getObjtype());
		this.setString(17,"_validfor", parameters.getValidfor());
		this.setString(18,"_frozenfor", parameters.getFrozenfor());
		this.setInt(19,"_counter", parameters.getCounter());
		this.setString(20,"_formatcode", parameters.getFormatcode());
		this.setDate(21,"_createdate", parameters.getCreatedate());
		this.setInt(22,"_usersig",parameters.getUsersing());
		this.setInt(23,"_action", action);

		v_resp = this.runUpdate();

		return v_resp;
	}

	public List getAccPeriods() throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{? = call sp_get_acc_period()}");

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					AccPeriodTO period = new AccPeriodTO();
					period.setAbsentry(rowsetActual.getInt(1));
					period.setAcccode(rowsetActual.getString(2));
					period.setAccname(rowsetActual.getString(3));
					period.setF_refdate(rowsetActual.getDate(4));
					period.setT_refdate(rowsetActual.getDate(5));
					period.setF_duedate(rowsetActual.getDate(6));
					period.setT_duedate(rowsetActual.getDate(7));
					period.setF_taxdate(rowsetActual.getDate(8));
					period.setT_taxdate(rowsetActual.getDate(9));
					period.setPeriodstat(rowsetActual.getInt(10));
					period.setUsersign(rowsetActual.getInt(11));
					_return.add(period);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	public int cat_accAssignment_mtto(AccassignmentTO parameters, int action)
			throws Exception {
		Date financyear = null;
		Date f_refdate = null;
		Date t_refdate = null;
		Date f_duedate = null;
		Date t_duedate = null;
		Date f_taxdate = null;
		Date t_taxdate = null;
		int v_resp = 0;
		this.setDbObject("{call sp_cat_acc_assignment_mtto(?,?)}");
		if (parameters.getFinancyear() != null) {
			financyear = new java.sql.Date(parameters.getFinancyear().getTime());
		}
		if (parameters.getF_refdate() != null) {
			f_refdate = new java.sql.Date(parameters.getF_refdate().getTime());
		}
		if (parameters.getT_refdate() != null) {
			t_refdate = new java.sql.Date(parameters.getT_refdate().getTime());
		}
		if (parameters.getF_duedate() != null) {
			f_duedate = new java.sql.Date(parameters.getF_duedate().getTime());
		}
		if (parameters.getT_duedate() != null) {
			t_duedate = new java.sql.Date(parameters.getT_duedate().getTime());
		}
		if (parameters.getF_taxdate() != null) {
			f_taxdate = new java.sql.Date(parameters.getF_taxdate().getTime());
		}
		if (parameters.getT_taxdate() != null) {
			t_taxdate = new java.sql.Date(parameters.getT_taxdate().getTime());
		}

		Object[] param = { parameters.getAbsentry(), parameters.getPeriodcat(),
				financyear, parameters.getYear(), parameters.getPeriodname(),
				parameters.getSubtype(), parameters.getPeriodnum(), f_refdate,
				t_refdate, f_duedate, t_duedate, f_taxdate, t_taxdate,
				parameters.getLinkact_1(), parameters.getLinkact_2(),
				parameters.getLinkact_3(), parameters.getLinkact_4(),
				parameters.getLinkact_5(), parameters.getLinkact_6(),
				parameters.getLinkact_8(), parameters.getLinkact_9(),
				parameters.getLinkact_10(), parameters.getLinkact_11(),
				parameters.getLinkact_12(), parameters.getLinkact_13(),
				parameters.getLinkact_14(), parameters.getLinkact_15(),
				parameters.getLinkact_16(), parameters.getLinkact_17(),
				parameters.getLinkact_18(), parameters.getDfltincom(),
				parameters.getExmptincom(), parameters.getDfltexpn(),
				parameters.getForgnincm(), parameters.getEcincome(),
				parameters.getForgnexpn(), parameters.getDfltratedi(),
				parameters.getDecresglac(), parameters.getLinkact_27(),
				parameters.getDftstockob(), parameters.getLinkact_19(),
				parameters.getLinkact_20(), parameters.getLinkact_21(),
				parameters.getLinkact_22(), parameters.getLinkact_23(),
				parameters.getLinkact_24(), parameters.getLinkact_25(),
				parameters.getLinkact_26(), parameters.getIncresglac(),
				parameters.getRturnngact(), parameters.getCogm_act(),
				parameters.getAloccstact(), parameters.getVariancact(),
				parameters.getPricdifact(), parameters.getCdownpymnt(),
				parameters.getVdownpymnt(), parameters.getCboercvble(),
				parameters.getCboeonclct(), parameters.getCboepresnt(),
				parameters.getCboediscnt(), parameters.getCunpaidboe(),
				parameters.getVboepayble(), parameters.getVasstboepy(),
				parameters.getCopendebts(), parameters.getVopendebts(),
				parameters.getPurchseact(), parameters.getPareturnac(),
				parameters.getPaoffsetac(), parameters.getLinkact_28(),
				parameters.getExdiffact(), parameters.getBalanceact(),
				parameters.getBnkchgact(), parameters.getLinkact_29(),
				parameters.getLinkact_30(), parameters.getIncmacct(),
				parameters.getExpnacct(), parameters.getVatrevact(),
				parameters.getExpclract(), parameters.getExpofstact(),
				parameters.getCostrevact(), parameters.getRepomoact(),
				parameters.getWipvaracct(), parameters.getSalevatoff(),
				parameters.getPurcvatoff(), parameters.getDpmsalact(),
				parameters.getDpmpuract(), parameters.getExpvaract(),
				parameters.getCostoffact(), parameters.getEcexepnses(),
				parameters.getStockact(), parameters.getDflinprcss(),
				parameters.getDfltincstm(), parameters.getDfltprofit(),
				parameters.getDfltloss(), parameters.getVassets(),
				parameters.getStockrvact(), parameters.getStkrvofact(),
				parameters.getWipacct(), parameters.getDfltcard(),
				parameters.getShpdgdsact(), parameters.getGlrvoffact(),
				parameters.getOverpayap(), parameters.getUndrpayap(),
				parameters.getOverpayar(), parameters.getUndrpayar(),
				parameters.getArcmact(), parameters.getArcmexpact(),
				parameters.getArcmfrnact(), parameters.getArcmeuact(),
				parameters.getApcmact(), parameters.getApcmfrnact(),
				parameters.getApcmeuact(), parameters.getNegstckact(),
				parameters.getStkintnact(), parameters.getGlgainxdif(),
				parameters.getGllossxdif(), parameters.getAmountdiff(),
				parameters.getSlfinvincm(), parameters.getSlfinvexpn(),
				parameters.getOnholdact(), parameters.getPlaact(),
				parameters.getIcclract(), parameters.getOcclract(),
				parameters.getPurbalact(), parameters.getWhicenact(),
				parameters.getWhocenact(), parameters.getSaldpmint(),
				parameters.getPurdpmint(), parameters.getExrateondt(),
				parameters.getEurecvact(), parameters.getEupayact(),
				parameters.getWipoffset(), parameters.getStockoffst(),
				parameters.getDunintrst(), parameters.getDunfee(),
				parameters.getTdsinterst(), parameters.getTdscharges(),
				parameters.getUsersign(), parameters.getPdfltwt(),
				parameters.getSdfltwt(), parameters.isShandlewt(),
				parameters.isPhandlewt() };

		Array aArray = null;
		try {
			aArray = conn.createArrayOf("varchar", param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.setArrayString(1, "_param", aArray);
		this.setInt(2, "_action", new Integer(action));

		v_resp = this.runUpdate();

		return v_resp;
	}

	public AccassignmentTO getAccAssignment() throws Exception {
		AccassignmentTO _return = new AccassignmentTO();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{? = call sp_get_acc_assignment()}");

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					_return.setAbsentry(rowsetActual.getInt(1));
					_return.setPeriodcat(rowsetActual.getString(2));
					_return.setFinancyear(rowsetActual.getDate(3));
					_return.setYear(rowsetActual.getInt(4));
					_return.setPeriodname(rowsetActual.getString(5));
					_return.setSubtype(rowsetActual.getString(6));
					_return.setPeriodnum(rowsetActual.getInt(7));
					_return.setF_refdate(rowsetActual.getDate(8));
					_return.setT_refdate(rowsetActual.getDate(9));
					_return.setF_duedate(rowsetActual.getDate(10));
					_return.setT_duedate(rowsetActual.getDate(11));
					_return.setF_taxdate(rowsetActual.getDate(12));
					_return.setT_taxdate(rowsetActual.getDate(13));
					_return.setLinkact_1(rowsetActual.getString(14));
					_return.setLinkact_2(rowsetActual.getString(15));
					_return.setLinkact_3(rowsetActual.getString(16));
					_return.setLinkact_4(rowsetActual.getString(17));
					_return.setLinkact_5(rowsetActual.getString(18));
					_return.setLinkact_6(rowsetActual.getString(19));
					_return.setLinkact_8(rowsetActual.getString(20));
					_return.setLinkact_9(rowsetActual.getString(21));
					_return.setLinkact_10(rowsetActual.getString(22));
					_return.setLinkact_11(rowsetActual.getString(23));
					_return.setLinkact_12(rowsetActual.getString(24));
					_return.setLinkact_13(rowsetActual.getString(25));
					_return.setLinkact_14(rowsetActual.getString(26));
					_return.setLinkact_15(rowsetActual.getString(27));
					_return.setLinkact_16(rowsetActual.getString(28));
					_return.setLinkact_17(rowsetActual.getString(29));
					_return.setLinkact_18(rowsetActual.getString(30));
					_return.setDfltincom(rowsetActual.getString(31));
					_return.setExmptincom(rowsetActual.getString(32));
					_return.setDfltexpn(rowsetActual.getString(33));
					_return.setForgnincm(rowsetActual.getString(34));
					_return.setEcincome(rowsetActual.getString(35));
					_return.setForgnexpn(rowsetActual.getString(36));
					_return.setDfltratedi(rowsetActual.getString(37));
					_return.setDecresglac(rowsetActual.getString(38));
					_return.setLinkact_27(rowsetActual.getString(39));
					_return.setDftstockob(rowsetActual.getString(40));
					_return.setLinkact_19(rowsetActual.getString(41));
					_return.setLinkact_20(rowsetActual.getString(42));
					_return.setLinkact_21(rowsetActual.getString(43));
					_return.setLinkact_22(rowsetActual.getString(44));
					_return.setLinkact_23(rowsetActual.getString(45));
					_return.setLinkact_24(rowsetActual.getString(46));
					_return.setLinkact_25(rowsetActual.getString(47));
					_return.setLinkact_26(rowsetActual.getString(48));
					_return.setIncresglac(rowsetActual.getString(49));
					_return.setRturnngact(rowsetActual.getString(50));
					_return.setCogm_act(rowsetActual.getString(51));
					_return.setAloccstact(rowsetActual.getString(52));
					_return.setVariancact(rowsetActual.getString(53));
					_return.setPricdifact(rowsetActual.getString(54));
					_return.setCdownpymnt(rowsetActual.getString(55));
					_return.setVdownpymnt(rowsetActual.getString(56));
					_return.setCboercvble(rowsetActual.getString(57));
					_return.setCboeonclct(rowsetActual.getString(58));
					_return.setCboepresnt(rowsetActual.getString(59));
					_return.setCboediscnt(rowsetActual.getString(60));
					_return.setCunpaidboe(rowsetActual.getString(61));
					_return.setVboepayble(rowsetActual.getString(62));
					_return.setVasstboepy(rowsetActual.getString(63));
					_return.setCopendebts(rowsetActual.getString(64));
					_return.setVopendebts(rowsetActual.getString(65));
					_return.setPurchseact(rowsetActual.getString(66));
					_return.setPareturnac(rowsetActual.getString(67));
					_return.setPaoffsetac(rowsetActual.getString(68));
					_return.setLinkact_28(rowsetActual.getString(69));
					_return.setExdiffact(rowsetActual.getString(70));
					_return.setBalanceact(rowsetActual.getString(71));
					_return.setBnkchgact(rowsetActual.getString(72));
					_return.setLinkact_29(rowsetActual.getString(73));
					_return.setLinkact_30(rowsetActual.getString(74));
					_return.setIncmacct(rowsetActual.getString(75));
					_return.setExpnacct(rowsetActual.getString(76));
					_return.setVatrevact(rowsetActual.getString(77));
					_return.setExpclract(rowsetActual.getString(78));
					_return.setExpofstact(rowsetActual.getString(79));
					_return.setCostrevact(rowsetActual.getString(80));
					_return.setRepomoact(rowsetActual.getString(81));
					_return.setWipvaracct(rowsetActual.getString(82));
					_return.setSalevatoff(rowsetActual.getString(83));
					_return.setPurcvatoff(rowsetActual.getString(84));
					_return.setDpmsalact(rowsetActual.getString(85));
					_return.setDpmpuract(rowsetActual.getString(86));
					_return.setExpvaract(rowsetActual.getString(87));
					_return.setCostoffact(rowsetActual.getString(88));
					_return.setEcexepnses(rowsetActual.getString(89));
					_return.setStockact(rowsetActual.getString(90));
					_return.setDflinprcss(rowsetActual.getString(91));
					_return.setDfltincstm(rowsetActual.getString(92));
					_return.setDfltprofit(rowsetActual.getString(93));
					_return.setDfltloss(rowsetActual.getString(94));
					_return.setVassets(rowsetActual.getString(95));
					_return.setStockrvact(rowsetActual.getString(96));
					_return.setStkrvofact(rowsetActual.getString(97));
					_return.setWipacct(rowsetActual.getString(98));
					_return.setDfltcard(rowsetActual.getString(99));
					_return.setShpdgdsact(rowsetActual.getString(100));
					_return.setGlrvoffact(rowsetActual.getString(101));
					_return.setOverpayap(rowsetActual.getString(102));
					_return.setUndrpayap(rowsetActual.getString(103));
					_return.setOverpayar(rowsetActual.getString(104));
					_return.setUndrpayar(rowsetActual.getString(105));
					_return.setArcmact(rowsetActual.getString(106));
					_return.setArcmexpact(rowsetActual.getString(107));
					_return.setArcmfrnact(rowsetActual.getString(108));
					_return.setArcmeuact(rowsetActual.getString(109));
					_return.setApcmact(rowsetActual.getString(110));
					_return.setApcmfrnact(rowsetActual.getString(111));
					_return.setApcmeuact(rowsetActual.getString(112));
					_return.setNegstckact(rowsetActual.getString(113));
					_return.setStkintnact(rowsetActual.getString(114));
					_return.setGlgainxdif(rowsetActual.getString(115));
					_return.setGllossxdif(rowsetActual.getString(116));
					_return.setAmountdiff(rowsetActual.getString(117));
					_return.setSlfinvincm(rowsetActual.getString(118));
					_return.setSlfinvexpn(rowsetActual.getString(119));
					_return.setOnholdact(rowsetActual.getString(120));
					_return.setPlaact(rowsetActual.getString(121));
					_return.setIcclract(rowsetActual.getString(122));
					_return.setOcclract(rowsetActual.getString(123));
					_return.setPurbalact(rowsetActual.getString(124));
					_return.setWhicenact(rowsetActual.getString(125));
					_return.setWhocenact(rowsetActual.getString(126));
					_return.setSaldpmint(rowsetActual.getString(127));
					_return.setPurdpmint(rowsetActual.getString(128));
					_return.setExrateondt(rowsetActual.getString(129));
					_return.setEurecvact(rowsetActual.getString(130));
					_return.setEupayact(rowsetActual.getString(131));
					_return.setWipoffset(rowsetActual.getString(132));
					_return.setStockoffst(rowsetActual.getString(133));
					_return.setDunintrst(rowsetActual.getString(134));
					_return.setDunfee(rowsetActual.getString(135));
					_return.setTdsinterst(rowsetActual.getString(136));
					_return.setTdscharges(rowsetActual.getString(137));
					_return.setUsersign(rowsetActual.getInt(138));

					_return.setShandlewt(rowsetActual.getBoolean(139));
					_return.setPhandlewt(rowsetActual.getBoolean(140));
					_return.setSdfltwt(rowsetActual.getString(141));
					_return.setPdfltwt(rowsetActual.getString(142));
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	// ############## MANTENIMIENTO DE LA TABLA BUDGET######################
	public int cat_budget_mtto(BudgetTO parameters, int action)
			throws Exception {
		int v_resp = 0;
		// this.setDbObject("{call sp_cat_acc_peri(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_cat_budget_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(1, "_absid", new Integer(parameters.getAbsid()));
		this.setString(2, "_acctcode ", parameters.getAcctcode());
		this.setInt(3, "_bgdcode ", new Integer(parameters.getBgdcode()));
		this.setString(4, "_fathercode ", parameters.getFathercode());
		this.setDouble(5, "_fthrprcnt ", new Double(parameters.getFthrprcnt()));
		this.setDouble(6, "_debltotal ", new Double(parameters.getDebltotal()));
		this.setDouble(7, "_credltotal ",
				new Double(parameters.getCredltotal()));
		this.setDouble(8, "_debrltotal ",
				new Double(parameters.getDebrltotal()));
		this.setDouble(9, "_crdrltotal ",
				new Double(parameters.getCrdrltotal()));
		this.setDouble(10, "_ftridrlsum ",
				new Double(parameters.getFtridrlsum()));
		this.setDouble(11, "_ftridrssum ",
				new Double(parameters.getFtridrssum()));
		this.setDouble(12, "_ftrodrlsum ",
				new Double(parameters.getFtrodrlsum()));
		this.setDouble(13, "_ftrocrlsum ",
				new Double(parameters.getFtrocrlsum()));
		java.sql.Date fecha = new java.sql.Date(parameters.getFinancyear()
				.getTime());
		this.setDate(14, "_financyear ", fecha);
		this.setInt(15, "_usersign ", new Integer(parameters.getUsersign()));
		this.setInt(16, "_action ", new Integer(action));

		v_resp = this.runUpdate();

		return v_resp;

	}

	public List getBudget(int _bgdcode) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{ call sp_get_budget(?)}");
		this.setInt(1, "_bgdcode", new Integer(_bgdcode));
		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		Hashtable _values = new Hashtable();
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					BudgetTO budget = new BudgetTO();
					budget.setAbsid(rowsetActual.getInt(1));
					budget.setAcctcode(rowsetActual.getString(2));
					budget.setAcctname(rowsetActual.getString(3));
					budget.setBgdcode(rowsetActual.getInt(4));
					budget.setFathercode(rowsetActual.getString(5));
					budget.setFthrprcnt(rowsetActual.getDouble(6));
					budget.setDebltotal(rowsetActual.getDouble(7));
					budget.setCredltotal(rowsetActual.getDouble(8));
					budget.setDebrltotal(rowsetActual.getDouble(9));
					budget.setCrdrltotal(rowsetActual.getDouble(10));
					budget.setFtridrlsum(rowsetActual.getDouble(11));
					budget.setFtridrssum(rowsetActual.getDouble(12));
					budget.setFtrodrlsum(rowsetActual.getDouble(13));
					budget.setFtrocrlsum(rowsetActual.getDouble(14));
					budget.setFinancyear(rowsetActual.getDate(15));
					budget.setUsersign(rowsetActual.getInt(16));
					budget.setPostable(rowsetActual.getString(17));
					budget.setCurrtotal(rowsetActual.getDouble(18));
					_values.put(budget.getAcctcode(), budget);
				}
				BudgetTO profileDetTmp = null;
				String _position = null;
				List lstDetProfile = new Vector();

				String[] claves = (String[]) _values.keySet().toArray(
						new String[0]);
				java.util.Arrays.sort(claves);

				// partimos de los nodos sin hijos
				for (String clave : claves) {
					profileDetTmp = (BudgetTO) _values.get(clave);
					if (profileDetTmp.getFathercode() == null) {
						profileDetTmp.setCurrtotal(0.00);
						this.filterParentBudget(profileDetTmp, _values,
								profileDetTmp.getAcctcode());
						_return.add(profileDetTmp);
					}
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	private void filterParentBudget(BudgetTO parent, Hashtable _allvalues,
			String parentFilter) {

		// Enumeration enParameters = _allvalues.keys();
		BudgetTO profileDetTmp = null;
		String _position = null;
		List lstDetProfile = new Vector();

		// partimos de los nodos sin hijos

		String[] claves = (String[]) _allvalues.keySet().toArray(new String[0]);
		java.util.Arrays.sort(claves);

		for (String clave : claves) {
			// _position = (String) enParameters.nextElement();
			profileDetTmp = (BudgetTO) _allvalues.get(clave);

			String padre = profileDetTmp.getFathercode();

			if (padre != null && padre.equals(parentFilter)) {

				this.filterParentBudget(profileDetTmp, _allvalues,
						profileDetTmp.getAcctcode());

				lstDetProfile.add(profileDetTmp);

				parent.setCurrtotal(parent.getCurrtotal()
						+ profileDetTmp.getCurrtotal());

			}

		}

		parent.setNodeDetail(lstDetProfile);

	}

	// ################ MANTENIMIENTO DE LA TABLA RecurringPostings
	// ###########################

	public int fin_recurringPosting_mtto(RecurringPostingsTO parameters,
			int action) throws Exception {
		int v_resp = 0;
		// this.setDbObject("{call sp_cat_acc_peri(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_fin_rep0_recurringpostings_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setString(1, "_rcurcode", parameters.getRcurcode());
		this.setString(2, "_rcurdesc ", parameters.getRcurdesc());
		this.setString(3, "_frequency ", parameters.getFrequency());
		this.setInt(4, "_remind ", new Integer(parameters.getRemind()));
		if (parameters.getLastposted() == null) {
			this.setDate(5, "_lastposted ", parameters.getLastposted());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getLastposted()
					.getTime());
			this.setDate(5, "_lastposted ", fecha);
		}
		if (parameters.getNextdeu() == null) {
			this.setDate(6, "_nextdeu ", parameters.getNextdeu());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getNextdeu()
					.getTime());
			this.setDate(6, "_nextdeu ", fecha);
		}
		this.setInt(7, "_entrycount ", new Integer(parameters.getEntrycount()));
		this.setDouble(8, "_volume ", new Double(parameters.getVolume()));
		this.setString(9, "_volcurr ", parameters.getVolcurr());
		this.setDouble(10, "_financvol ", new Double(parameters.getFinancvol()));
		this.setString(11, "_ref1 ", parameters.getRef1());
		this.setString(12, "_ref2 ", parameters.getRef2());
		this.setString(13, "_transcode ", parameters.getTranscode());
		this.setString(14, "_memo ", parameters.getMemo());
		this.setString(15, "_limitrtrns ", parameters.getLimitrtrns());
		this.setInt(16, "_returns ", new Integer(parameters.getReturns()));
		if (parameters.getLimitdate() == null) {
			this.setDate(17, "_limitdate ", parameters.getLimitdate());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getLimitdate()
					.getTime());
			this.setDate(17, "_limitdate ", fecha);
		}
		this.setInt(18, "_instance ", new Integer(parameters.getInstance()));
		this.setString(19, "_autovat ", parameters.getAutovat());
		this.setString(20, "_managewtax ", parameters.getManagewtax());
		this.setString(21, "_ref3 ", parameters.getRef3());
		this.setString(22, "_deferedtax ", parameters.getDeferedtax());
		this.setInt(23, "_usersign ", new Integer(parameters.getUsersign()));
		this.setInt(24, "_action ", new Integer(action));
		v_resp = this.runUpdate();

		return v_resp;

	}

	public List getrecurringPosting(RecurringPostingsInTO parameters)
			throws Exception {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_recurringpostings(?,?,?,?,?,?)}");
		this.setString(1, "_rcurcode", parameters.getRcurcode());
		this.setString(2, "_rcurdesc ", parameters.getRcurdesc());
		this.setString(3, "_ref1 ", parameters.getRef1());
		this.setString(4, "_ref2 ", parameters.getRef2());
		this.setString(5, "_ref3 ", parameters.getRef3());
		this.setString(6, "_memo ", parameters.getMemo());

		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					RecurringPostingsTO Diary = new RecurringPostingsTO();
					Diary.setRcurcode(rowsetActual.getString(1));
					Diary.setRcurdesc(rowsetActual.getString(2));
					Diary.setFrequency(rowsetActual.getString(3));
					Diary.setRemind(rowsetActual.getInt(4));
					Diary.setLastposted(rowsetActual.getDate(5));
					Diary.setNextdeu(rowsetActual.getDate(6));
					Diary.setEntrycount(rowsetActual.getInt(7));
					Diary.setVolume(rowsetActual.getDouble(8));
					Diary.setVolcurr(rowsetActual.getString(9));
					Diary.setFinancvol(rowsetActual.getDouble(10));
					Diary.setRef1(rowsetActual.getString(11));
					Diary.setRef2(rowsetActual.getString(12));
					Diary.setTranscode(rowsetActual.getString(13));
					Diary.setMemo(rowsetActual.getString(14));
					Diary.setLimitrtrns(rowsetActual.getString(15));
					Diary.setReturns(rowsetActual.getInt(16));
					Diary.setLimitdate(rowsetActual.getDate(17));
					Diary.setInstance(rowsetActual.getInt(18));
					Diary.setAutovat(rowsetActual.getString(19));
					Diary.setManagewtax(rowsetActual.getString(20));
					Diary.setRef3(rowsetActual.getString(21));
					Diary.setDeferedtax(rowsetActual.getString(22));
					Diary.setUsersign(rowsetActual.getInt(23));
					Diary.setUpdatedate(rowsetActual.getDate(24));
					Diary.setUpdatetime(rowsetActual.getInt(25));

					_return.add(Diary);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	public RecurringPostingsTO getrecurringPosting_by_key(String _rcurcode,
			int _instance) throws Exception {
		RecurringPostingsTO _return = new RecurringPostingsTO();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_recurringpostings_by_key(?,?)}");
		this.setString(1, "_rcurcode", _rcurcode);
		this.setInt(2, "_instance", new Integer(_instance));
		AccountingDAO DAO = new AccountingDAO();
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					RecurringPostingsTO Diary = new RecurringPostingsTO();
					Diary.setRcurcode(rowsetActual.getString(1));
					Diary.setRcurdesc(rowsetActual.getString(2));
					Diary.setFrequency(rowsetActual.getString(3));
					Diary.setRemind(rowsetActual.getInt(4));
					Diary.setLastposted(rowsetActual.getDate(5));
					Diary.setNextdeu(rowsetActual.getDate(6));
					Diary.setEntrycount(rowsetActual.getInt(7));
					Diary.setVolume(rowsetActual.getDouble(8));
					Diary.setVolcurr(rowsetActual.getString(9));
					Diary.setFinancvol(rowsetActual.getDouble(10));
					Diary.setRef1(rowsetActual.getString(11));
					Diary.setRef2(rowsetActual.getString(12));
					Diary.setTranscode(rowsetActual.getString(13));
					Diary.setMemo(rowsetActual.getString(14));
					Diary.setLimitrtrns(rowsetActual.getString(15));
					Diary.setReturns(rowsetActual.getInt(16));
					Diary.setLimitdate(rowsetActual.getDate(17));
					Diary.setInstance(rowsetActual.getInt(18));
					Diary.setAutovat(rowsetActual.getString(19));
					Diary.setManagewtax(rowsetActual.getString(20));
					Diary.setRef3(rowsetActual.getString(21));
					Diary.setDeferedtax(rowsetActual.getString(22));
					Diary.setUsersign(rowsetActual.getInt(23));
					Diary.setUpdatedate(rowsetActual.getDate(24));
					Diary.setUpdatetime(rowsetActual.getInt(25));
					Diary.setRecurringPostingsDetail(DAO
							.getrecurringPostingDetail(
									rowsetActual.getString(1), _instance));

					_return = Diary;
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;

	}

	public int fin_recurringPostingDetail_mtto(
			RecurringPostingsDetailTO parameters, int action) throws Exception {
		int v_resp = 0;
		// this.setDbObject("{call sp_cat_acc_perwwwwwwwwwwwwwwwwwwwwwi(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_fin_rep1_recurringpostingsdetai_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setString(1, "_rcurcode", parameters.getRcurcode());
		this.setInt(2, "_lineid ", new Integer(parameters.getLineid()));
		this.setString(3, "_acctcode ", parameters.getAcctcode());
		this.setString(4, "_acctdesc ", parameters.getAcctdesc());
		this.setDouble(5, "_debit ", new Double(parameters.getDebit()));
		this.setDouble(6, "_credit ", new Double(parameters.getCredit()));
		this.setString(7, "_currency ", parameters.getCurrency());
		this.setInt(8, "_instance ", new Integer(parameters.getInstance()));
		this.setString(9, "_vatgroup ", parameters.getVatgroup());
		this.setString(10, "_vatline ", parameters.getVatline());
		this.setString(11, "_ctrlacct ", parameters.getCtrlacct());
		this.setString(12, "_ocrcode ", parameters.getOcrcode());
		this.setInt(13, "_taxtype ", new Integer(parameters.getTaxtype()));
		this.setString(14, "_taxpostacc ", parameters.getTaxpostacc());
		this.setString(15, "_taxcode ", parameters.getTaxcode());
		this.setString(16, "_ocrcode1 ", parameters.getOcrcode1());
		this.setString(17, "_ocrcode2 ", parameters.getOcrcode2());
		this.setString(18, "_ocrcode3 ", parameters.getOcrcode3());
		this.setString(19, "_ocrcode4 ", parameters.getOcrcode4());
		this.setString(20, "_wtliable ", parameters.getWtliable());
		this.setString(21, "_wtaxline ", parameters.getWtaxline());
		this.setDouble(22, "_grossvalue ",
				new Double(parameters.getGrossvalue()));
		this.setInt(23, "_bplid ", new Integer(parameters.getBplid()));
		this.setInt(24, "_action ", new Integer(action));

		v_resp = this.runUpdate();

		return v_resp;

	}

	public List getrecurringPostingDetail(String _rcurcode, int _instance)
			throws Exception {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_recurringpostingsdetai(?,?)}");
		this.setString(1, "_rcurcode", _rcurcode);
		this.setInt(2, "_instance", new Integer(_instance));

		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					RecurringPostingsDetailTO Diary = new RecurringPostingsDetailTO();
					Diary.setRcurcode(rowsetActual.getString(1));
					Diary.setLineid(rowsetActual.getInt(2));
					Diary.setAcctcode(rowsetActual.getString(3));
					Diary.setAcctdesc(rowsetActual.getString(4));
					Diary.setDebit(rowsetActual.getDouble(5));
					Diary.setCredit(rowsetActual.getDouble(6));
					Diary.setCurrency(rowsetActual.getString(7));
					Diary.setInstance(rowsetActual.getInt(8));
					Diary.setVatgroup(rowsetActual.getString(9));
					Diary.setVatline(rowsetActual.getString(10));
					Diary.setCtrlacct(rowsetActual.getString(11));
					Diary.setOcrcode(rowsetActual.getString(12));
					Diary.setTaxtype(rowsetActual.getInt(13));
					Diary.setTaxpostacc(rowsetActual.getString(14));
					Diary.setTaxcode(rowsetActual.getString(15));
					Diary.setOcrcode1(rowsetActual.getString(16));
					Diary.setOcrcode2(rowsetActual.getString(17));
					Diary.setOcrcode3(rowsetActual.getString(18));
					Diary.setOcrcode4(rowsetActual.getString(19));
					Diary.setWtliable(rowsetActual.getString(20));
					Diary.setWtaxline(rowsetActual.getString(21));
					Diary.setGrossvalue(rowsetActual.getDouble(22));
					Diary.setBplid(rowsetActual.getInt(23));

					_return.add(Diary);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	public List getrecurringPostingExecute() throws Exception {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{? = call sp_get_recurringpostings_execute()}");

		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					RecurringPostingsTO Diary = new RecurringPostingsTO();
					Diary.setRcurcode(rowsetActual.getString(1));
					Diary.setRcurdesc(rowsetActual.getString(2));
					Diary.setFrequency(rowsetActual.getString(3));
					Diary.setRemind(rowsetActual.getInt(4));
					Diary.setLastposted(rowsetActual.getDate(5));
					Diary.setNextdeu(rowsetActual.getDate(6));
					Diary.setEntrycount(rowsetActual.getInt(7));
					Diary.setVolume(rowsetActual.getDouble(8));
					Diary.setVolcurr(rowsetActual.getString(9));
					Diary.setFinancvol(rowsetActual.getDouble(10));
					Diary.setRef1(rowsetActual.getString(11));
					Diary.setRef2(rowsetActual.getString(12));
					Diary.setTranscode(rowsetActual.getString(13));
					Diary.setMemo(rowsetActual.getString(14));
					Diary.setLimitrtrns(rowsetActual.getString(15));
					Diary.setReturns(rowsetActual.getInt(16));
					Diary.setLimitdate(rowsetActual.getDate(17));
					Diary.setInstance(rowsetActual.getInt(18));
					Diary.setAutovat(rowsetActual.getString(19));
					Diary.setManagewtax(rowsetActual.getString(20));
					Diary.setRef3(rowsetActual.getString(21));
					Diary.setDeferedtax(rowsetActual.getString(22));
					Diary.setUsersign(rowsetActual.getInt(23));
					Diary.setUpdatedate(rowsetActual.getDate(24));
					Diary.setUpdatetime(rowsetActual.getInt(25));

					_return.add(Diary);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
}

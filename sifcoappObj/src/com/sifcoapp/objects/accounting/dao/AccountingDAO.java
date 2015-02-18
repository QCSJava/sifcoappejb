package com.sifcoapp.objects.accounting.dao;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
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
					account.setPostable(rowsetActual.getString(7));
					account.setLevels(rowsetActual.getInt(8));
					account.setGrpline(rowsetActual.getInt(9));
					account.setFathernum(rowsetActual.getString(10));
					account.setGroupmask(rowsetActual.getInt(11));
					account.setIntrmatch(rowsetActual.getInt(12));
					account.setActtype(rowsetActual.getString(13));
					account.setProtected1(rowsetActual.getString(14));
					account.setCreatedate(rowsetActual.getDate(15));
					account.setUpdatedate(rowsetActual.getDate(16));
					account.setUsersign(rowsetActual.getInt(17));
					account.setObjtype(rowsetActual.getString(18));
					account.setValidfor(rowsetActual.getString(19));
					account.setFormatcode(rowsetActual.getString(20));

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

		parent.setNodedetail(lstDetProfile);

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
					account.setPostable(rowsetActual.getString(7));
					account.setLevels(rowsetActual.getInt(8));
					account.setGrpline(rowsetActual.getInt(9));
					account.setFathernum(rowsetActual.getString(10));
					account.setGroupmask(rowsetActual.getInt(11));
					account.setIntrmatch(rowsetActual.getInt(12));
					account.setActtype(rowsetActual.getString(13));
					account.setProtected1(rowsetActual.getString(14));
					account.setCreatedate(rowsetActual.getDate(15));
					account.setUpdatedate(rowsetActual.getDate(16));
					account.setUsersign(rowsetActual.getInt(17));
					account.setObjtype(rowsetActual.getString(18));
					account.setValidfor(rowsetActual.getString(19));
					account.setFormatcode(rowsetActual.getString(20));

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
					account.setPostable(rowsetActual.getString(7));
					account.setLevels(rowsetActual.getInt(8));
					account.setGrpline(rowsetActual.getInt(9));
					account.setFathernum(rowsetActual.getString(10));
					account.setGroupmask(rowsetActual.getInt(11));
					account.setIntrmatch(rowsetActual.getInt(12));
					account.setActtype(rowsetActual.getString(13));
					account.setProtected1(rowsetActual.getString(14));
					account.setCreatedate(rowsetActual.getDate(15));
					account.setUpdatedate(rowsetActual.getDate(16));
					account.setUsersign(rowsetActual.getInt(17));
					account.setObjtype(rowsetActual.getString(18));
					account.setValidfor(rowsetActual.getString(19));
					account.setFormatcode(rowsetActual.getString(20));

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
					account.setPostable(rowsetActual.getString(7));
					account.setLevels(rowsetActual.getInt(8));
					account.setGrpline(rowsetActual.getInt(9));
					account.setFathernum(rowsetActual.getString(10));
					account.setGroupmask(rowsetActual.getInt(11));
					account.setIntrmatch(rowsetActual.getInt(12));
					account.setActtype(rowsetActual.getString(13));
					account.setProtected1(rowsetActual.getString(14));
					account.setCreatedate(rowsetActual.getDate(15));
					account.setUpdatedate(rowsetActual.getDate(16));
					account.setUsersign(rowsetActual.getInt(17));
					account.setObjtype(rowsetActual.getString(18));
					account.setValidfor(rowsetActual.getString(19));
					account.setFormatcode(rowsetActual.getString(20));
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
	public int cat_acc0_ACCOUNT_mtto(AccountTO parameters, int action)
			throws Exception {

		int v_resp = 0;
		// this.setDbObject("{call sp_cat_acc_period(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_acc0_account_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setString(1, "_acctcode", parameters.getAcctcode());
		this.setString(2, "_acctname", parameters.getAcctname());
		this.setDouble(3, "_currtotal", new Double(parameters.getCurrtotal()));
		this.setDouble(4, "_endtotal", new Double(parameters.getEndtotal()));
		this.setString(5, "_finanse", parameters.getFinanse());
		this.setString(6, "_budget", parameters.getBudget());
		this.setString(7, "_postable", parameters.getPostable());
		this.setInt(8, "_levels", new Integer(parameters.getLevels()));
		this.setInt(9, "_grpline", new Integer(parameters.getGrpline()));
		this.setString(10, "_fathernum", parameters.getFathernum());
		this.setInt(11, "_groupmask", new Integer(parameters.getGroupmask()));
		this.setInt(12, "_intrmatch", new Integer(parameters.getIntrmatch()));
		this.setString(13, "_acttype", parameters.getActtype());
		this.setString(14, "_protected", parameters.getProtected1());
		this.setInt(15, "_usersign", new Integer(parameters.getUsersign()));
		this.setString(16, "_objtype", parameters.getObjtype());
		this.setString(17, "_validfor", parameters.getValidfor());
		this.setString(18, "_formatcode", parameters.getFormatcode());
		this.setInt(19, "_action", new Integer(action));

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

		int v_resp = 0;
		this.setDbObject("{call sp_cat_acc_assignment_mtto(?,?)}");

		Object[] param = { parameters.getAbsentry(), parameters.getPeriodcat(),
				parameters.getFinancyear(), parameters.getYear(),
				parameters.getPeriodname(), parameters.getSubtype(),
				parameters.getPeriodnum(), parameters.getF_refdate(),
				parameters.getT_refdate(), parameters.getF_duedate(),
				parameters.getT_duedate(), parameters.getF_taxdate(),
				parameters.getT_taxdate(), parameters.getLinkact_1(),
				parameters.getLinkact_2(), parameters.getLinkact_3(),
				parameters.getLinkact_4(), parameters.getLinkact_5(),
				parameters.getLinkact_6(), parameters.getLinkact_8(),
				parameters.getLinkact_9(), parameters.getLinkact_10(),
				parameters.getLinkact_11(), parameters.getLinkact_12(),
				parameters.getLinkact_13(), parameters.getLinkact_14(),
				parameters.getLinkact_15(), parameters.getLinkact_16(),
				parameters.getLinkact_17(), parameters.getLinkact_18(),
				parameters.getDfltincom(), parameters.getExmptincom(),
				parameters.getDfltexpn(), parameters.getForgnincm(),
				parameters.getEcincome(), parameters.getForgnexpn(),
				parameters.getDfltratedi(), parameters.getDecresglac(),
				parameters.getLinkact_27(), parameters.getDftstockob(),
				parameters.getLinkact_19(), parameters.getLinkact_20(),
				parameters.getLinkact_21(), parameters.getLinkact_22(),
				parameters.getLinkact_23(), parameters.getLinkact_24(),
				parameters.getLinkact_25(), parameters.getLinkact_26(),
				parameters.getIncresglac(), parameters.getRturnngact(),
				parameters.getCogm_act(), parameters.getAloccstact(),
				parameters.getVariancact(), parameters.getPricdifact(),
				parameters.getCdownpymnt(), parameters.getVdownpymnt(),
				parameters.getCboercvble(), parameters.getCboeonclct(),
				parameters.getCboepresnt(), parameters.getCboediscnt(),
				parameters.getCunpaidboe(), parameters.getVboepayble(),
				parameters.getVasstboepy(), parameters.getCopendebts(),
				parameters.getVopendebts(), parameters.getPurchseact(),
				parameters.getPareturnac(), parameters.getPaoffsetac(),
				parameters.getLinkact_28(), parameters.getExdiffact(),
				parameters.getBalanceact(), parameters.getBnkchgact(),
				parameters.getLinkact_29(), parameters.getLinkact_30(),
				parameters.getIncmacct(), parameters.getExpnacct(),
				parameters.getVatrevact(), parameters.getExpclract(),
				parameters.getExpofstact(), parameters.getCostrevact(),
				parameters.getRepomoact(), parameters.getWipvaracct(),
				parameters.getSalevatoff(), parameters.getPurcvatoff(),
				parameters.getDpmsalact(), parameters.getDpmpuract(),
				parameters.getExpvaract(), parameters.getCostoffact(),
				parameters.getEcexepnses(), parameters.getStockact(),
				parameters.getDflinprcss(), parameters.getDfltincstm(),
				parameters.getDfltprofit(), parameters.getDfltloss(),
				parameters.getVassets(), parameters.getStockrvact(),
				parameters.getStkrvofact(), parameters.getWipacct(),
				parameters.getDfltcard(), parameters.getShpdgdsact(),
				parameters.getGlrvoffact(), parameters.getOverpayap(),
				parameters.getUndrpayap(), parameters.getOverpayar(),
				parameters.getUndrpayar(), parameters.getArcmact(),
				parameters.getArcmexpact(), parameters.getArcmfrnact(),
				parameters.getArcmeuact(), parameters.getApcmact(),
				parameters.getApcmfrnact(), parameters.getApcmeuact(),
				parameters.getNegstckact(), parameters.getStkintnact(),
				parameters.getGlgainxdif(), parameters.getGllossxdif(),
				parameters.getAmountdiff(), parameters.getSlfinvincm(),
				parameters.getSlfinvexpn(), parameters.getOnholdact(),
				parameters.getPlaact(), parameters.getIcclract(),
				parameters.getOcclract(), parameters.getPurbalact(),
				parameters.getWhicenact(), parameters.getWhocenact(),
				parameters.getSaldpmint(), parameters.getPurdpmint(),
				parameters.getExrateondt(), parameters.getEurecvact(),
				parameters.getEupayact(), parameters.getWipoffset(),
				parameters.getStockoffst(), parameters.getDunintrst(),
				parameters.getDunfee(), parameters.getTdsinterst(),
				parameters.getTdscharges(), parameters.getUsersign(),
				parameters.getPdfltwt(), parameters.getSdfltwt(),
				parameters.isShandlewt(), parameters.isPhandlewt() };

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

}

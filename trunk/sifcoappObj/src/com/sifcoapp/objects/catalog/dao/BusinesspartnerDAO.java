package com.sifcoapp.objects.catalog.dao;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sun.rowset.CachedRowSetImpl;
public class BusinesspartnerDAO extends CommonDAO{
	
	//##################################  CRUD DE TABLA BUSINESSPARTNER ####################################
	public int inv_cat_bpa_businesspartner_mtto(BusinesspartnerTO parameters, int action) throws Exception {
		int v_resp = 0;
		// s.setDbObject("{call sp_gis1_goodsissuedetail_mtto   (1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_cat_bpa0_businesspartner_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setString(1,"_cardcode,", parameters.getCardcode());
		this.setString(2,"_cardname,", parameters.getCardname());
		this.setString(3,"_cardtype,", parameters.getCardtype());
		this.setString(4,"_groupcode,", parameters.getGroupcode());
		this.setString(5,"_cmpprivate,", parameters.getCmpprivate());
		this.setString(6,"_address,", parameters.getAddress());
		this.setString(7,"_zipcode,", parameters.getZipcode());
		this.setString(8,"_mailaddres,", parameters.getMailaddres());
		this.setString(9,"_mailzipcod,", parameters.getMailzipcod());
		this.setString(10,"_phone1,", parameters.getPhone1());
		this.setString(11,"_phone2,", parameters.getPhone2());
		this.setString(12,"_cntctprsn,", parameters.getCntctprsn());
		this.setString(13,"_notes,", parameters.getNotes());
		this.setDouble(14,"_balance,", new Double(parameters.getBalance()));
		this.setDouble(15,"_checksbal,", new Double(parameters.getChecksbal()));
		this.setDouble(16,"_ordersbal,", new Double(parameters.getOrdersbal()));
		this.setDouble(17,"_creditline,", new Double(parameters.getCreditline()));
		this.setDouble(18,"_debtline,", new Double(parameters.getDebtline()));
		this.setDouble(19,"_discount,", new Double(parameters.getDiscount()));
		this.setString(20,"_vatstatus,", parameters.getVatstatus());
		this.setInt(21,"_listnum,", new Integer(parameters.getListnum()));
		this.setString(22,"_transfered,", parameters.getTransfered());
		this.setString(23,"_baltrnsfrd,", parameters.getBaltrnsfrd());
		this.setString(24,"_cellular,", parameters.getCellular());
		this.setString(25,"_e_mail,", parameters.getE_mail());
		this.setString(26,"_picture,", parameters.getPicture());
		this.setString(27,"_dflaccount,", parameters.getDflaccount());
		this.setString(28,"_dflbranch,", parameters.getDflbranch());
		this.setString(29,"_bankcode,", parameters.getBankcode());
		this.setString(30,"_addid,", parameters.getAddid());
		this.setString(31,"_qrygroup1,", parameters.getQrygroup1());
		this.setString(32,"_qrygroup2", parameters.getQrygroup2());
		this.setString(33,"_qrygroup3", parameters.getQrygroup3());
		this.setString(34,"_qrygroup4", parameters.getQrygroup4());
		this.setString(35,"_qrygroup5", parameters.getQrygroup5());
		this.setString(36,"_qrygroup6", parameters.getQrygroup6());
		this.setString(37,"_qrygroup7", parameters.getQrygroup7());
		this.setString(38,"_qrygroup8", parameters.getQrygroup8());
		this.setString(39,"_qrygroup9", parameters.getQrygroup9());
		this.setString(40,"_qrygroup10", parameters.getQrygroup10());
		this.setString(41,"_validfor", parameters.getValidfor());
		this.setString(42,"_vatgroup", parameters.getVatgroup());
		this.setString(43,"_objtype", parameters.getObjtype());
		this.setString(44,"_debpayacct", parameters.getDebpayacct());
		this.setInt(45,"_docentry", new Integer(parameters.getDocentry()));
		this.setString(46,"_pymcode", parameters.getPymcode());
		this.setString(47,"_partdelivr", parameters.getPartdelivr());
		this.setString(48,"_paymblock", parameters.getPaymblock());
		this.setString(49,"_wtliable", parameters.getWtliable());
		this.setString(50,"_ninum", parameters.getNinum());
		this.setString(51,"_wtcode", parameters.getWtcode());
		this.setString(52,"_vatregnum", parameters.getVatregnum());
		this.setString(53,"_industry", parameters.getIndustry());
		this.setString(54,"_business", parameters.getBusiness());
		this.setString(55,"_isdomestic", parameters.getIsdomestic());
		this.setString(56,"_isresident", parameters.getIsresident());
		this.setString(57,"_profession", parameters.getProfession());
		this.setInt(58,"_industryc", new Integer(parameters.getIndustryc()));
		this.setString(59,"_intracc", parameters.getIntracc());
		this.setString(60,"_feeacc", parameters.getFeeacc());
		this.setInt(61,"_series", new Integer(parameters.getSeries()));
		this.setString(62,"_taxidident", parameters.getTaxidident());
		this.setString(63,"_typecont", parameters.getTypecont());
		this.setString(64,"_typesn", parameters.getTypesn());
		this.setString(65,"_nit", parameters.getNit());
		this.setInt(66,"_usersign", new Integer(parameters.getUsersign()));

		this.setInt(67, "_action", new Integer(action));
		v_resp = this.runUpdate();
		return v_resp;
	}
//RETORNA DE REGISTROS DE LA TABLA BUSINESSPARTNER POR FILTRO (5 FILTROS)
	public List get_businesspartner(BusinesspartnerInTO parameters)throws Exception {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_businesspartner(?,?,?,?,?)}");
		this.setString(1, "_cardcode", parameters.getCardcode());
		this.setString(2, "_cardname", parameters.getCardname());
		this.setInt(3, "_groupcode",new Integer(parameters.getGroupcode()));
		this.setString(4, "_cardtype", parameters.getCardtype());
		this.setString(5, "_nit", parameters.getNit());
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					BusinesspartnerTO partner = new BusinesspartnerTO();
					partner.setCardcode(rowsetActual.getString(1));
					partner.setCardname(rowsetActual.getString(2));
					partner.setCardtype(rowsetActual.getString(3));
					partner.setGroupcode(rowsetActual.getString(4));
					partner.setCmpprivate(rowsetActual.getString(5));
					partner.setAddress(rowsetActual.getString(6));
					partner.setZipcode(rowsetActual.getString(7));
					partner.setMailaddres(rowsetActual.getString(8));
					partner.setMailzipcod(rowsetActual.getString(9));
					partner.setPhone1(rowsetActual.getString(10));
					partner.setPhone2(rowsetActual.getString(11));
					partner.setCntctprsn(rowsetActual.getString(12));
					partner.setNotes(rowsetActual.getString(13));
					partner.setBalance(rowsetActual.getDouble(14));
					partner.setChecksbal(rowsetActual.getDouble(15));
					partner.setOrdersbal(rowsetActual.getDouble(16));
					partner.setCreditline(rowsetActual.getDouble(17));
					partner.setDebtline(rowsetActual.getDouble(18));
					partner.setDiscount(rowsetActual.getDouble(19));
					partner.setVatstatus(rowsetActual.getString(20));
					partner.setListnum(rowsetActual.getInt(21));
					partner.setTransfered(rowsetActual.getString(22));
					partner.setBaltrnsfrd(rowsetActual.getString(23));
					partner.setCellular(rowsetActual.getString(24));
					partner.setE_mail(rowsetActual.getString(25));
					partner.setPicture(rowsetActual.getString(26));
					partner.setDflaccount(rowsetActual.getString(27));
					partner.setDflbranch(rowsetActual.getString(28));
					partner.setBankcode(rowsetActual.getString(29));
					partner.setAddid(rowsetActual.getString(30));
					partner.setQrygroup1(rowsetActual.getString(31));
					partner.setQrygroup2(rowsetActual.getString(32));
					partner.setQrygroup3(rowsetActual.getString(33));
					partner.setQrygroup4(rowsetActual.getString(34));
					partner.setQrygroup5(rowsetActual.getString(35));
					partner.setQrygroup6(rowsetActual.getString(36));
					partner.setQrygroup7(rowsetActual.getString(37));
					partner.setQrygroup8(rowsetActual.getString(38));
					partner.setQrygroup9(rowsetActual.getString(39));
					partner.setQrygroup10(rowsetActual.getString(40));
					partner.setValidfor(rowsetActual.getString(41));
					partner.setVatgroup(rowsetActual.getString(42));
					partner.setObjtype(rowsetActual.getString(43));
					partner.setDebpayacct(rowsetActual.getString(44));
					partner.setDocentry(rowsetActual.getInt(45));
					partner.setPymcode(rowsetActual.getString(46));
					partner.setPartdelivr(rowsetActual.getString(47));
					partner.setPaymblock(rowsetActual.getString(48));
					partner.setWtliable(rowsetActual.getString(49));
					partner.setNinum(rowsetActual.getString(50));
					partner.setWtcode(rowsetActual.getString(51));
					partner.setVatregnum(rowsetActual.getString(52));
					partner.setIndustry(rowsetActual.getString(53));
					partner.setBusiness(rowsetActual.getString(54));
					partner.setIsdomestic(rowsetActual.getString(55));
					partner.setIsresident(rowsetActual.getString(56));
					partner.setProfession(rowsetActual.getString(57));
					partner.setIndustryc(rowsetActual.getInt(58));
					partner.setIntracc(rowsetActual.getString(59));
					partner.setFeeacc(rowsetActual.getString(60));
					partner.setSeries(rowsetActual.getInt(61));
					partner.setTaxidident(rowsetActual.getString(62));
					partner.setTypecont(rowsetActual.getString(63));
					partner.setTypesn(rowsetActual.getString(64));
					partner.setNit(rowsetActual.getString(65));
					partner.setUsersign(rowsetActual.getInt(66));

					_return.add(partner);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}
	
	//RETORNA DE LA TABLA BUSINEESPARTNER UN REGISTRO POR CLAVE
	public BusinesspartnerTO get_businesspartnerByKey(String parameters)throws Exception {
		BusinesspartnerTO _return = new BusinesspartnerTO();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_businesspartner_by_key(?)}");
		this.setString(1, "_cardcode", parameters);
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					BusinesspartnerTO partner = new BusinesspartnerTO();
					partner.setCardcode(rowsetActual.getString(1));
					partner.setCardname(rowsetActual.getString(2));
					partner.setCardtype(rowsetActual.getString(3));
					partner.setGroupcode(rowsetActual.getString(4));
					partner.setCmpprivate(rowsetActual.getString(5));
					partner.setAddress(rowsetActual.getString(6));
					partner.setZipcode(rowsetActual.getString(7));
					partner.setMailaddres(rowsetActual.getString(8));
					partner.setMailzipcod(rowsetActual.getString(9));
					partner.setPhone1(rowsetActual.getString(10));
					partner.setPhone2(rowsetActual.getString(11));
					partner.setCntctprsn(rowsetActual.getString(12));
					partner.setNotes(rowsetActual.getString(13));
					partner.setBalance(rowsetActual.getDouble(14));
					partner.setChecksbal(rowsetActual.getDouble(15));
					partner.setOrdersbal(rowsetActual.getDouble(16));
					partner.setCreditline(rowsetActual.getDouble(17));
					partner.setDebtline(rowsetActual.getDouble(18));
					partner.setDiscount(rowsetActual.getDouble(19));
					partner.setVatstatus(rowsetActual.getString(20));
					partner.setListnum(rowsetActual.getInt(21));
					partner.setTransfered(rowsetActual.getString(22));
					partner.setBaltrnsfrd(rowsetActual.getString(23));
					partner.setCellular(rowsetActual.getString(24));
					partner.setE_mail(rowsetActual.getString(25));
					partner.setPicture(rowsetActual.getString(26));
					partner.setDflaccount(rowsetActual.getString(27));
					partner.setDflbranch(rowsetActual.getString(28));
					partner.setBankcode(rowsetActual.getString(29));
					partner.setAddid(rowsetActual.getString(30));
					partner.setQrygroup1(rowsetActual.getString(31));
					partner.setQrygroup2(rowsetActual.getString(32));
					partner.setQrygroup3(rowsetActual.getString(33));
					partner.setQrygroup4(rowsetActual.getString(34));
					partner.setQrygroup5(rowsetActual.getString(35));
					partner.setQrygroup6(rowsetActual.getString(36));
					partner.setQrygroup7(rowsetActual.getString(37));
					partner.setQrygroup8(rowsetActual.getString(38));
					partner.setQrygroup9(rowsetActual.getString(39));
					partner.setQrygroup10(rowsetActual.getString(40));
					partner.setValidfor(rowsetActual.getString(41));
					partner.setVatgroup(rowsetActual.getString(42));
					partner.setObjtype(rowsetActual.getString(43));
					partner.setDebpayacct(rowsetActual.getString(44));
					partner.setDocentry(rowsetActual.getInt(45));
					partner.setPymcode(rowsetActual.getString(46));
					partner.setPartdelivr(rowsetActual.getString(47));
					partner.setPaymblock(rowsetActual.getString(48));
					partner.setWtliable(rowsetActual.getString(49));
					partner.setNinum(rowsetActual.getString(50));
					partner.setWtcode(rowsetActual.getString(51));
					partner.setVatregnum(rowsetActual.getString(52));
					partner.setIndustry(rowsetActual.getString(53));
					partner.setBusiness(rowsetActual.getString(54));
					partner.setIsdomestic(rowsetActual.getString(55));
					partner.setIsresident(rowsetActual.getString(56));
					partner.setProfession(rowsetActual.getString(57));
					partner.setIndustryc(rowsetActual.getInt(58));
					partner.setIntracc(rowsetActual.getString(59));
					partner.setFeeacc(rowsetActual.getString(60));
					partner.setSeries(rowsetActual.getInt(61));
					partner.setTaxidident(rowsetActual.getString(62));
					partner.setTypecont(rowsetActual.getString(63));
					partner.setTypesn(rowsetActual.getString(64));
					partner.setNit(rowsetActual.getString(65));
					partner.setUsersign(rowsetActual.getInt(66));
					_return=partner;
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

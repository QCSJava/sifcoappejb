package com.sifcoapp.objects.catalog.dao;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sun.rowset.CachedRowSetImpl;
public class BusinesspartnerDAO extends CommonDAO{
	
	//##################################  CRUD DE TABLA BUSINESSPARTNER ####################################
	public int inv_cat_bpa_businesspartner_mtto(BusinesspartnerTO parameters, int action) throws Exception {
		int v_resp = 0;
		// s.setDbObject("{call sp_gis1_goodsissuedetail_mtto   (1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_cat_bpa0_businesspartner_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setString(1,"_cardcode", parameters.getCardcode());
		this.setString(2,"_cardname ", parameters.getCardname());
		this.setString(3,"_cardtype ", parameters.getCardtype());
		this.setString(4,"_groupcode ", parameters.getGroupcode());
		this.setString(5,"_cmpprivate ", parameters.getCmpprivate());
		this.setString(6,"_address ", parameters.getAddress());
		this.setString(7,"_zipcode ", parameters.getZipcode());
		this.setString(8,"_mailaddres ", parameters.getMailaddres());
		this.setString(9,"_mailzipcod ", parameters.getMailzipcod());
		this.setString(10,"_phone1 ", parameters.getPhone1());
		this.setString(11,"_phone2 ", parameters.getPhone2());
		this.setString(12,"_fax ", parameters.getFax());
		this.setString(13,"_cntctprsn ", parameters.getCntctprsn());
		this.setString(14,"_notes ",parameters.getNotes());
		this.setDouble(15,"_balance ",new Double( parameters.getBalance()));
		this.setDouble(16,"_checksbal ",new Double( parameters.getChecksbal()));
		this.setDouble(17,"_dnotesbal ",new Double( parameters.getDnotesbal()));
		this.setDouble(18,"_ordersbal ",new Double( parameters.getOrdersbal()));
		this.setInt(19,"_groupnum ",new Integer( parameters.getGroupnum()));
		this.setDouble(20,"_creditline ",new Double( parameters.getCreditline()));
		this.setDouble(21,"_debtline ",new Double( parameters.getDebtline()));
		this.setDouble(22,"_discount ",new Double( parameters.getDiscount()));
		this.setString(23,"_vatstatus ", parameters.getVatstatus());
		this.setString(24,"_lictradnum ", parameters.getLictradnum());
		this.setInt(25,"_exmatchnum ",new Integer( parameters.getExmatchnum()));
		this.setInt(26,"_inmatchnum ",new Integer( parameters.getInmatchnum()));
		this.setInt(27,"_listnum ",new Integer( parameters.getListnum()));
		this.setDouble(28,"_orderbalsy ",new Double( parameters.getOrderbalsy()));
		this.setString(29,"_transfered ", parameters.getTransfered());
		this.setString(30,"_baltrnsfrd ", parameters.getBaltrnsfrd());
		this.setInt(31,"_commgrcode ",new Integer( parameters.getCommgrcode()));
		this.setString(32,"_prevyearac ", parameters.getPrevyearac());
		this.setDouble(33,"_balancesys ",new Double( parameters.getBalancesys()));
		this.setString(34,"_cellular ", parameters.getCellular());
		this.setString(35,"_e_mail ", parameters.getE_mail());
		this.setString(36,"_picture ", parameters.getPicture());
		this.setString(37,"_dflaccount ", parameters.getDflaccount());
		this.setString(38,"_dflbranch ", parameters.getDflbranch());
		this.setString(39,"_bankcode ", parameters.getBankcode());
		this.setString(40,"_addid ", parameters.getAddid());
		this.setString(41,"_fathercard ", parameters.getFathercard());
		this.setString(42,"_qrygroup1 ", parameters.getQrygroup1());
		this.setString(43,"_qrygroup2 ", parameters.getQrygroup2());
		this.setString(44,"_qrygroup3 ", parameters.getQrygroup3());
		this.setString(45,"_qrygroup4 ", parameters.getQrygroup4());
		this.setString(46,"_qrygroup5 ", parameters.getQrygroup5());
		this.setString(47,"_qrygroup6 ", parameters.getQrygroup6());
		this.setString(48,"_qrygroup7 ", parameters.getQrygroup7());
		this.setString(49,"_qrygroup8 ", parameters.getQrygroup8());
		this.setString(50,"_qrygroup9 ", parameters.getQrygroup9());
		this.setString(51,"_qrygroup10 ", parameters.getQrygroup10());
		this.setString(52,"_validfor ", parameters.getValidfor());
		this.setString(53,"_vatgroup ", parameters.getVatgroup());
		this.setString(54,"_objtype ", parameters.getObjtype());
		this.setString(55,"_debpayacct ", parameters.getDebpayacct());
		this.setInt(56,"_docentry ",new Integer( parameters.getDocentry()));
		this.setString(57,"_pymcode ", parameters.getPymcode());
		this.setString(58,"_partdelivr ", parameters.getPartdelivr());
		this.setString(59,"_paymblock ", parameters.getPaymblock());
		this.setString(60,"_wtliable ", parameters.getWtliable());
		this.setString(61,"_ninum ", parameters.getNinum());
		this.setString(62,"_wtcode ", parameters.getWtcode());
		this.setString(63,"_vatregnum ", parameters.getVatregnum());
		this.setString(64,"_industry ", parameters.getIndustry());
		this.setString(65,"_business ", parameters.getBusiness());
		this.setString(66,"_isdomestic ", parameters.getIsdomestic());
		this.setString(67,"_isresident ", parameters.getIsresident());
		this.setString(68,"_dpmclear ", parameters.getDpmclear());
		this.setString(69,"_affiliate ", parameters.getAffiliate());
		this.setString(70,"_profession ", parameters.getProfession());
		this.setString(71,"_dpmintact ", parameters.getDpmintact());
		this.setInt(72,"_industryc ",new Integer( parameters.getIndustryc()));
		this.setString(73,"_intracc ", parameters.getIntracc());
		this.setString(74,"_feeacc ", parameters.getFeeacc());
		this.setInt(75,"_series ",new Integer( parameters.getSeries()));
		this.setInt(76,"_number ",new Integer( parameters.getNumber()));
		this.setString(77,"_taxidident ", parameters.getTaxidident());
		this.setString(78,"_nodiscount ", parameters.getNodiscount());
		this.setString(79,"_typecont ", parameters.getTypecont());
		this.setString(80,"_typesn ", parameters.getTypesn());
		this.setString(81,"_nit ", parameters.getNit());
		this.setString(82,"_tipocont ", parameters.getTipocont());
		this.setString(83,"_tiposn ", parameters.getTiposn());
		this.setString(84,"_relatedacc1 ", parameters.getRelatedacc1());
		this.setString(85,"_relatedacc2 ", parameters.getRelatedacc2());
		this.setString(86,"_relatedacc3 ", parameters.getRelatedacc3());
		this.setString(87,"_relatedacc4 ", parameters.getRelatedacc4());
		this.setInt(88,"_usersign ",new Integer( parameters.getUsersign()));

		this.setInt(89, "_action", new Integer(action));
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
					partner.setFax(rowsetActual.getString(12));
					partner.setCntctprsn(rowsetActual.getString(13));
					partner.setNotes(rowsetActual.getString(14));
					partner.setBalance(rowsetActual.getDouble(15));
					partner.setChecksbal(rowsetActual.getDouble(16));
					partner.setDnotesbal(rowsetActual.getDouble(17));
					partner.setOrdersbal(rowsetActual.getDouble(18));
					partner.setGroupnum(rowsetActual.getInt(19));
					partner.setCreditline(rowsetActual.getDouble(20));
					partner.setDebtline(rowsetActual.getDouble(21));
					partner.setDiscount(rowsetActual.getDouble(22));
					partner.setVatstatus(rowsetActual.getString(23));
					partner.setLictradnum(rowsetActual.getString(24));
					partner.setExmatchnum(rowsetActual.getInt(25));
					partner.setInmatchnum(rowsetActual.getInt(26));
					partner.setListnum(rowsetActual.getInt(27));
					partner.setOrderbalsy(rowsetActual.getDouble(28));
					partner.setTransfered(rowsetActual.getString(29));
					partner.setBaltrnsfrd(rowsetActual.getString(30));
					partner.setCommgrcode(rowsetActual.getInt(31));
					partner.setPrevyearac(rowsetActual.getString(32));
					partner.setBalancesys(rowsetActual.getDouble(33));
					partner.setCellular(rowsetActual.getString(34));
					partner.setE_mail(rowsetActual.getString(35));
					partner.setPicture(rowsetActual.getString(36));
					partner.setDflaccount(rowsetActual.getString(37));
					partner.setDflbranch(rowsetActual.getString(38));
					partner.setBankcode(rowsetActual.getString(39));
					partner.setAddid(rowsetActual.getString(40));
					partner.setFathercard(rowsetActual.getString(41));
					partner.setQrygroup1(rowsetActual.getString(42));
					partner.setQrygroup2(rowsetActual.getString(43));
					partner.setQrygroup3(rowsetActual.getString(44));
					partner.setQrygroup4(rowsetActual.getString(45));
					partner.setQrygroup5(rowsetActual.getString(46));
					partner.setQrygroup6(rowsetActual.getString(47));
					partner.setQrygroup7(rowsetActual.getString(48));
					partner.setQrygroup8(rowsetActual.getString(49));
					partner.setQrygroup9(rowsetActual.getString(50));
					partner.setQrygroup10(rowsetActual.getString(51));
					partner.setValidfor(rowsetActual.getString(52));
					partner.setVatgroup(rowsetActual.getString(53));
					partner.setObjtype(rowsetActual.getString(54));
					partner.setDebpayacct(rowsetActual.getString(55));
					partner.setDocentry(rowsetActual.getInt(56));
					partner.setPymcode(rowsetActual.getString(57));
					partner.setPartdelivr(rowsetActual.getString(58));
					partner.setPaymblock(rowsetActual.getString(59));
					partner.setWtliable(rowsetActual.getString(60));
					partner.setNinum(rowsetActual.getString(61));
					partner.setWtcode(rowsetActual.getString(62));
					partner.setVatregnum(rowsetActual.getString(63));
					partner.setIndustry(rowsetActual.getString(64));
					partner.setBusiness(rowsetActual.getString(65));
					partner.setIsdomestic(rowsetActual.getString(66));
					partner.setIsresident(rowsetActual.getString(67));
					partner.setDpmclear(rowsetActual.getString(68));
					partner.setAffiliate(rowsetActual.getString(69));
					partner.setProfession(rowsetActual.getString(70));
					partner.setDpmintact(rowsetActual.getString(71));
					partner.setIndustryc(rowsetActual.getInt(72));
					partner.setIntracc(rowsetActual.getString(73));
					partner.setFeeacc(rowsetActual.getString(74));
					partner.setSeries(rowsetActual.getInt(75));
					partner.setNumber(rowsetActual.getInt(76));
					partner.setTaxidident(rowsetActual.getString(77));
					partner.setNodiscount(rowsetActual.getString(78));
					partner.setTypecont(rowsetActual.getString(79));
					partner.setTypesn(rowsetActual.getString(80));
					partner.setNit(rowsetActual.getString(81));
					partner.setTipocont(rowsetActual.getString(82));
					partner.setTiposn(rowsetActual.getString(83));
					partner.setRelatedacc1(rowsetActual.getString(84));
					partner.setRelatedacc2(rowsetActual.getString(85));
					partner.setRelatedacc3(rowsetActual.getString(86));
					partner.setRelatedacc4(rowsetActual.getString(87));
					partner.setUsersign(rowsetActual.getInt(88));
					partner.setUpdatedate(rowsetActual.getDate(89));
					partner.setUpdatetime(rowsetActual.getInt(90));

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
					partner.setFax(rowsetActual.getString(12));
					partner.setCntctprsn(rowsetActual.getString(13));
					partner.setNotes(rowsetActual.getString(14));
					partner.setBalance(rowsetActual.getDouble(15));
					partner.setChecksbal(rowsetActual.getDouble(16));
					partner.setDnotesbal(rowsetActual.getDouble(17));
					partner.setOrdersbal(rowsetActual.getDouble(18));
					partner.setGroupnum(rowsetActual.getInt(19));
					partner.setCreditline(rowsetActual.getDouble(20));
					partner.setDebtline(rowsetActual.getDouble(21));
					partner.setDiscount(rowsetActual.getDouble(22));
					partner.setVatstatus(rowsetActual.getString(23));
					partner.setLictradnum(rowsetActual.getString(24));
					partner.setExmatchnum(rowsetActual.getInt(25));
					partner.setInmatchnum(rowsetActual.getInt(26));
					partner.setListnum(rowsetActual.getInt(27));
					partner.setOrderbalsy(rowsetActual.getDouble(28));
					partner.setTransfered(rowsetActual.getString(29));
					partner.setBaltrnsfrd(rowsetActual.getString(30));
					partner.setCommgrcode(rowsetActual.getInt(31));
					partner.setPrevyearac(rowsetActual.getString(32));
					partner.setBalancesys(rowsetActual.getDouble(33));
					partner.setCellular(rowsetActual.getString(34));
					partner.setE_mail(rowsetActual.getString(35));
					partner.setPicture(rowsetActual.getString(36));
					partner.setDflaccount(rowsetActual.getString(37));
					partner.setDflbranch(rowsetActual.getString(38));
					partner.setBankcode(rowsetActual.getString(39));
					partner.setAddid(rowsetActual.getString(40));
					partner.setFathercard(rowsetActual.getString(41));
					partner.setQrygroup1(rowsetActual.getString(42));
					partner.setQrygroup2(rowsetActual.getString(43));
					partner.setQrygroup3(rowsetActual.getString(44));
					partner.setQrygroup4(rowsetActual.getString(45));
					partner.setQrygroup5(rowsetActual.getString(46));
					partner.setQrygroup6(rowsetActual.getString(47));
					partner.setQrygroup7(rowsetActual.getString(48));
					partner.setQrygroup8(rowsetActual.getString(49));
					partner.setQrygroup9(rowsetActual.getString(50));
					partner.setQrygroup10(rowsetActual.getString(51));
					partner.setValidfor(rowsetActual.getString(52));
					partner.setVatgroup(rowsetActual.getString(53));
					partner.setObjtype(rowsetActual.getString(54));
					partner.setDebpayacct(rowsetActual.getString(55));
					partner.setDocentry(rowsetActual.getInt(56));
					partner.setPymcode(rowsetActual.getString(57));
					partner.setPartdelivr(rowsetActual.getString(58));
					partner.setPaymblock(rowsetActual.getString(59));
					partner.setWtliable(rowsetActual.getString(60));
					partner.setNinum(rowsetActual.getString(61));
					partner.setWtcode(rowsetActual.getString(62));
					partner.setVatregnum(rowsetActual.getString(63));
					partner.setIndustry(rowsetActual.getString(64));
					partner.setBusiness(rowsetActual.getString(65));
					partner.setIsdomestic(rowsetActual.getString(66));
					partner.setIsresident(rowsetActual.getString(67));
					partner.setDpmclear(rowsetActual.getString(68));
					partner.setAffiliate(rowsetActual.getString(69));
					partner.setProfession(rowsetActual.getString(70));
					partner.setDpmintact(rowsetActual.getString(71));
					partner.setIndustryc(rowsetActual.getInt(72));
					partner.setIntracc(rowsetActual.getString(73));
					partner.setFeeacc(rowsetActual.getString(74));
					partner.setSeries(rowsetActual.getInt(75));
					partner.setNumber(rowsetActual.getInt(76));
					partner.setTaxidident(rowsetActual.getString(77));
					partner.setNodiscount(rowsetActual.getString(78));
					partner.setTypecont(rowsetActual.getString(79));
					partner.setTypesn(rowsetActual.getString(80));
					partner.setNit(rowsetActual.getString(81));
					partner.setTipocont(rowsetActual.getString(82));
					partner.setTiposn(rowsetActual.getString(83));
					partner.setRelatedacc1(rowsetActual.getString(84));
					partner.setRelatedacc2(rowsetActual.getString(85));
					partner.setRelatedacc3(rowsetActual.getString(86));
					partner.setRelatedacc4(rowsetActual.getString(87));
					partner.setUsersign(rowsetActual.getInt(88));
					partner.setUpdatedate(rowsetActual.getDate(89));
					partner.setUpdatetime(rowsetActual.getInt(90));


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

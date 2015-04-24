package com.sifcoapp.objects.admin.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.ejb.EJBException;

import com.sifcoapp.objects.accounting.to.AccPeriodTO;
import com.sifcoapp.objects.admin.to.ArticlesInTO;
import com.sifcoapp.objects.admin.to.ArticlesPriceTO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.EnterpriseTO;
import com.sifcoapp.objects.admin.to.PricesListInTO;
import com.sifcoapp.objects.admin.to.PricesListTO;
import com.sifcoapp.objects.admin.to.TablesCatalogTO;
import com.sifcoapp.objects.admin.to.WarehouseJournalDetailTO;
import com.sifcoapp.objects.admin.to.WarehouseJournalTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.dao.CommonDAO;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.dao.GoodsissuesDetailDAO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesInTO;
import com.sifcoapp.objects.inventory.to.GoodsissuesTO;
import com.sun.rowset.CachedRowSetImpl;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.deploymentExtensionType;

public class AdminDAO extends CommonDAO {

	public AdminDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminDAO(Connection _conn) {
		super(_conn);
		// TODO Auto-generated constructor stub
	}

	/*
	 * Retorna un catalogo specifico de la base de datos
	 */
	public List findCatalog(String nameCatalog) throws Exception {

		List lstResult = new Vector();
		List lstResultSet = null;

		System.out.println("Desde DAO");
		this.setTypeReturn(Common.TYPERETURN_RESULTSET);
		this.setDbObject("{call sp_get_catalog(?)}");
		// this.setString(1, "return");
		this.setString(1, "IN_CAT_NAME", nameCatalog);

		lstResultSet = this.runQuery();
		System.out.println("return psg");

		CachedRowSetImpl rowsetActual;

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		// Iterator<CachedRowSetImpl> iterator = lstResult.iterator();
		while (liRowset.hasNext()) {

			rowsetActual = (CachedRowSetImpl) liRowset.next();

			try {
				while (rowsetActual.next()) {
					/*
					 * System.out.println(rowsetActual.getString(1));
					 * System.out.println(rowsetActual.getString(2));
					 * System.out.println(rowsetActual.getString(3));
					 */

					lstResult.add(new CatalogTO(rowsetActual.getString(1),
							rowsetActual.getInt(2), rowsetActual.getString(3),
							rowsetActual.getString(4), rowsetActual
									.getString(5), rowsetActual.getString(6),
							rowsetActual.getInt(7)));
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return lstResult;

	}

	/*
	 * Retorna un catalogo specifico de la base de datos
	 */
	public CatalogTO findCatalogByKey(String catcode, int tablecode)
			throws Exception {

		CatalogTO _return = new CatalogTO();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_catalogbykey(?,?)}");
		this.setString(1, "_catcode", catcode);
		this.setInt(2, "_tablecode", tablecode);

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		// Iterator<CachedRowSetImpl> iterator = lstResult.iterator();
		while (liRowset.hasNext()) {

			rowsetActual = (CachedRowSetImpl) liRowset.next();

			try {
				while (rowsetActual.next()) {

					CatalogTO catalogo = new CatalogTO();
					catalogo.setCatcode(rowsetActual.getString(1));
					catalogo.setTablecode(rowsetActual.getInt(2));
					catalogo.setCatvalue(rowsetActual.getString(3));
					catalogo.setCatvalue2(rowsetActual.getString(4));
					catalogo.setCatvalue3(rowsetActual.getString(5));
					_return = catalogo;
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return _return;

	}

	public List findCatalogByKey_List(String catcode, int tablecode)
			throws Exception {

		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_catalogbykey(?,?)}");
		this.setString(1, "_catcode", catcode);
		this.setInt(2, "_tablecode", tablecode);

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		// Iterator<CachedRowSetImpl> iterator = lstResult.iterator();
		while (liRowset.hasNext()) {

			rowsetActual = (CachedRowSetImpl) liRowset.next();

			try {
				while (rowsetActual.next()) {

					CatalogTO catalogo = new CatalogTO();
					catalogo.setCatcode(rowsetActual.getString(1));
					catalogo.setTablecode(rowsetActual.getInt(2));
					catalogo.setCatvalue(rowsetActual.getString(3));
					catalogo.setCatvalue2(rowsetActual.getString(4));
					catalogo.setCatvalue3(rowsetActual.getString(5));
					_return.add(catalogo);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return _return;

	}

	/*
	 * Obtiene los registros del catalogo de tablas del sistema
	 * 
	 * @author Rutilio
	 */
	public List getTablesCatalog() throws Exception {
		List _return = new Vector();
		List lstResultSet = null;
		TablesCatalogTO _returnTO = new TablesCatalogTO();

		System.out.println("Desde DAO");
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{? = call sp_get_tables_catalog()}");

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		// Iterator<CachedRowSetImpl> iterator = lstResult.iterator();
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();

			try {
				while (rowsetActual.next()) {
					_return.add(new TablesCatalogTO(rowsetActual.getInt(1),
							rowsetActual.getString(2), rowsetActual
									.getString(3)));
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	/*
	 * Mantenimiento tabla de catalogos de sistema
	 */
	public int cat_tab1_catalogos_mtto(CatalogTO parameters, int action)
			throws Exception {
		int _return;

		this.setDbObject("{call sp_cat_tab1_catalogos_mtto(?,?,?,?,?,?,?,?)}");

		this.setString(1, "_catcode", parameters.getCatcode());
		this.setInt(2, "_tablecode", new Integer(parameters.getTablecode()));
		this.setString(3, "_catvalue", parameters.getCatvalue());
		this.setString(4, "_catvalue2", parameters.getCatvalue2());
		this.setString(5, "_catvalue3", parameters.getCatvalue3());
		this.setString(6, "_catstatus", parameters.getCatstatus());
		this.setInt(7, "_usersign", new Integer(parameters.getUsersign()));
		this.setInt(8, "_action", new Integer(action));

		_return = this.runUpdate();

		return _return;
	}

	/*
	 * Actualiza los datos de la empresa
	 */
	public int updEnterprise(EnterpriseTO parameters) throws Exception {

		int v_resp = 0;

		this.setDbObject("{call sp_upd_enterprise(?,?,?,?,?,?,?,?,?,?)}");

		this.setString(1, "_name", parameters.getCompnyName());
		this.setString(2, "_addr", parameters.getCompnyAddr());
		this.setString(3, "_country", parameters.getCountry_catalog());
		this.setString(4, "_printheadr", parameters.getCrintHeadr());
		this.setString(5, "_phone1", parameters.getPhone1());
		this.setString(6, "_phone2", parameters.getPhone2());
		this.setString(7, "_fax", parameters.getFax());
		this.setString(8, "_e_mail", parameters.getE_Mail());
		this.setString(9, "_manager", parameters.getManager());
		this.setString(10, "_taxidnum", parameters.getTaxIdNum());
		v_resp = this.runUpdate();

		return v_resp;

	}

	/*
	 * Obtiene la informacion de la empresa
	 */
	public EnterpriseTO getEnterpriseInfo(int enterpriseCode) throws Exception {

		List lstResult = new Vector();
		List lstResultSet = null;
		EnterpriseTO _return = new EnterpriseTO();

		System.out.println("Desde DAO");
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{? = call sp_get_enterprise_info()}");

		lstResultSet = this.runQuery();
		System.out.println("return psg");

		CachedRowSetImpl rowsetActual;

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		// Iterator<CachedRowSetImpl> iterator = lstResult.iterator();
		while (liRowset.hasNext()) {

			rowsetActual = (CachedRowSetImpl) liRowset.next();

			try {
				while (rowsetActual.next()) {

					_return.setCode(rowsetActual.getInt(1));
					_return.setCompnyName(rowsetActual.getString(2));
					_return.setCompnyAddr(rowsetActual.getString(3));
					_return.setCountry_catalog(rowsetActual.getString(4));
					_return.setCrintHeadr(rowsetActual.getString(5));
					_return.setPhone1(rowsetActual.getString(6));
					_return.setPhone2(rowsetActual.getString(7));
					_return.setFax(rowsetActual.getString(8));
					_return.setE_Mail(rowsetActual.getString(9));
					_return.setManager(rowsetActual.getString(10));
					_return.setTaxIdNum(rowsetActual.getString(11));
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return _return;
	}

	/*
	 * Guarda los cambios en los articulos
	 */
	public int cat_articles_mtto(ArticlesTO parameters, int action)
			throws Exception {

		int v_resp = 0;
		// this.setDject("{call sp_cat_articles_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_cat_articles_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		if (parameters.getValidFrom() == null) {
			this.setDate(21, "_validfrom", parameters.getValidFrom());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getValidFrom()
					.getTime());
			this.setDate(21, "_validfrom", fecha);
		}
		if (parameters.getValidTo() == null) {
			this.setDate(22, "_validto", parameters.getValidTo());
		} else {
			java.sql.Date fecha = new java.sql.Date(parameters.getValidTo()
					.getTime());
			this.setDate(22, "_validto", fecha);
		}
		this.setString(1, "_itemcode", parameters.getItemCode());
		this.setString(2, "_itemname", parameters.getItemName());
		this.setString(3, "_itemtype", parameters.getItemType());
		this.setString(4, "_itmsgrpcod", parameters.getItmsIsGrpCod());
		this.setString(5, "_vatliable", parameters.getVatLiable());
		this.setString(6, "_codebars", parameters.getCodeBars());
		this.setString(7, "_prchseitem", parameters.getPrchseItem());
		this.setString(8, "_sellitem", parameters.getSellItem());
		this.setString(9, "_invntitem", parameters.getInvntItem());
		this.setString(10, "_assetitem", parameters.getAssetItem());
		this.setString(11, "_cardcode", parameters.getCardCode());
		this.setString(12, "_buyunitmsr", parameters.getBuyUnitMsr());
		this.setDouble(13, "_numinbuy", new Double(parameters.getNumInBuy()));
		this.setString(14, "_salunitmsr", parameters.getSalUnitMsr());
		this.setDouble(15, "_salpackun", new Double(parameters.getSalPackUn()));
		this.setString(16, "_suppcatnum", parameters.getSuppCatNum());
		this.setDouble(17, "_purpackun", new Double(parameters.getSalPackUn()));
		this.setDouble(18, "_avgprice", new Double(parameters.getAvgPrice()));
		this.setDouble(19, "_onhand", new Double(parameters.getOnHand()));
		this.setString(20, "_validfor", parameters.getValidFor());
		this.setString(23, "_invntryuom", parameters.getInvntryUom());
		this.setDouble(24, "_numinsale", new Double(parameters.getNumInSale()));
		this.setString(25, "_dfltwh", parameters.getDfltWH());
		this.setString(26, "_wtliable", parameters.getWtliable());
		this.setString(27, "_sww", parameters.getSww());
		this.setString(28, "_validcomm", parameters.getValidComm());
		this.setInt(29, "_usersign", new Integer(parameters.getUserSign()));
		this.setString(30, "_vatgourpsa,", parameters.getVatgourpsa());
		this.setInt(31, "_action", new Integer(action));

		v_resp = this.runUpdate();

		return v_resp;
	}

	public List getArticles(ArticlesInTO parameters) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// this.setDbObject("{call sp_get_articles(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_get_articles(?,?,?,?,?,?,?,?)}");
		this.setString(1, "_itemcode", parameters.getItemCode());
		this.setString(2, "_itemname", parameters.getItemName());
		this.setString(3, "_itemtype", parameters.getItemType());
		this.setString(4, "_itmsgrpcod", parameters.getItmsIsGrpCod());
		this.setString(5, "_prchseitem", parameters.getPrchseItem());
		this.setString(6, "_sellitem", parameters.getSellItem());
		this.setString(7, "_invntitem", parameters.getInvntItem());
		this.setString(8, "_assetitem", parameters.getAssetItem());

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					ArticlesTO article = new ArticlesTO();
					article.setItemCode(rowsetActual.getString(1));
					article.setItemName(rowsetActual.getString(2));
					article.setItemType(rowsetActual.getString(3));
					article.setItmsIsGrpCod(rowsetActual.getString(4));
					article.setVatLiable(rowsetActual.getString(5));
					article.setCodeBars(rowsetActual.getString(6));
					article.setPrchseItem(rowsetActual.getString(7));
					article.setSellItem(rowsetActual.getString(8));
					article.setInvntItem(rowsetActual.getString(9));
					article.setAssetItem(rowsetActual.getString(10));
					article.setCardCode(rowsetActual.getString(11));
					article.setBuyUnitMsr(rowsetActual.getString(12));
					article.setNumInBuy(rowsetActual.getDouble(13));
					article.setSalUnitMsr(rowsetActual.getString(14));
					article.setSalPackUn(rowsetActual.getDouble(15));
					article.setSuppCatNum(rowsetActual.getString(16));
					article.setPurPackUn(rowsetActual.getDouble(17));
					article.setAvgPrice(rowsetActual.getDouble(18));
					article.setOnHand(rowsetActual.getDouble(19));
					article.setValidFor(rowsetActual.getString(20));
					article.setValidFrom(rowsetActual.getDate(21));
					article.setValidTo(rowsetActual.getDate(22));
					article.setInvntryUom(rowsetActual.getString(23));
					article.setNumInSale(rowsetActual.getDouble(24));
					article.setDfltWH(rowsetActual.getString(25));
					article.setWtliable(rowsetActual.getString(26));
					article.setSww(rowsetActual.getString(27));
					article.setValidComm(rowsetActual.getString(28));
					article.setUserSign(rowsetActual.getInt(29));
					article.setVatgourpsa(rowsetActual.getString(30));

					_return.add(article);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	public ArticlesTO getArticlesByKey(String itemcode) throws Exception {
		ArticlesTO _return = new ArticlesTO();
		List lstResultSet = null;
		int tablecode = 10;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// this.setDbObject("{call sp_get_articles(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_get_articles_by_key(?)}");
		this.setString(1, "_itemcode", itemcode);

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					ArticlesTO article = new ArticlesTO();
					article.setItemCode(rowsetActual.getString(1));
					article.setItemName(rowsetActual.getString(2));
					article.setItemType(rowsetActual.getString(3));
					article.setItmsIsGrpCod(rowsetActual.getString(4));
					article.setVatLiable(rowsetActual.getString(5));
					article.setCodeBars(rowsetActual.getString(6));
					article.setPrchseItem(rowsetActual.getString(7));
					article.setSellItem(rowsetActual.getString(8));
					article.setInvntItem(rowsetActual.getString(9));
					article.setAssetItem(rowsetActual.getString(10));
					article.setCardCode(rowsetActual.getString(11));
					article.setBuyUnitMsr(rowsetActual.getString(12));
					article.setNumInBuy(rowsetActual.getDouble(13));
					article.setSalUnitMsr(rowsetActual.getString(14));
					article.setSalPackUn(rowsetActual.getDouble(15));
					article.setSuppCatNum(rowsetActual.getString(16));
					article.setPurPackUn(rowsetActual.getDouble(17));
					article.setAvgPrice(rowsetActual.getDouble(18));
					article.setOnHand(rowsetActual.getDouble(19));
					article.setValidFor(rowsetActual.getString(20));
					article.setValidFrom(rowsetActual.getDate(21));
					article.setValidTo(rowsetActual.getDate(22));
					article.setInvntryUom(rowsetActual.getString(23));
					article.setNumInSale(rowsetActual.getDouble(24));
					article.setDfltWH(rowsetActual.getString(25));
					article.setWtliable(rowsetActual.getString(26));
					article.setSww(rowsetActual.getString(27));
					article.setValidComm(rowsetActual.getString(28));
					article.setUserSign(rowsetActual.getInt(29));
					article.setVatgourpsa(rowsetActual.getString(30));
					article.setBranchArticles(getBranchArticles(itemcode));
					article.setArticleprices(getArticlePrices(itemcode));
					article.setVatgourpsaList(findCatalogByKey_List(
							article.getVatgourpsa(), tablecode));
					_return = article;

				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	public List getBranchArticles(String itemcode) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// this.setDbObject("{call sp_get_branch_articles_by_key(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_get_branch_articles_by_key(?)}");
		this.setString(1, "_itemcode", itemcode);

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					BranchArticlesTO brachArticle = new BranchArticlesTO();
					brachArticle.setItemcode(rowsetActual.getString(1));
					brachArticle.setWhscode(rowsetActual.getString(2));
					brachArticle.setOnhand(rowsetActual.getDouble(3));
					brachArticle.setOnhand1(rowsetActual.getDouble(4));
					brachArticle.setIscommited(rowsetActual.getDouble(5));
					brachArticle.setOnorder(rowsetActual.getDouble(6));
					brachArticle.setMinstock(rowsetActual.getDouble(7));
					brachArticle.setMaxstock(rowsetActual.getDouble(8));
					brachArticle.setMinorder(rowsetActual.getDouble(9));
					brachArticle.setLocked(rowsetActual.getString(10));
					brachArticle.setWhsname(rowsetActual.getString(11));
					brachArticle.setIsasociated(false);
					if (rowsetActual.getString(1) != null) {
						brachArticle.setIsasociated(true);
					}
					;

					_return.add(brachArticle);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	/*
	 * Guarda los cambios en los articulos
	 */
	public int cat_brancharticles_mtto(BranchArticlesTO parameters, int action)
			throws Exception {

		int v_resp = 0;
		// this.setDbObject("{call sp_cat_brancharticles_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_cat_brancharticles_mtto(?,?,?,?,?,?,?,?,?,?,?)}");
		this.setString(1, "_itemcode", parameters.getItemcode());
		this.setString(2, "_whscode", parameters.getWhscode());
		this.setDouble(3, "_onhand", new Double(parameters.getOnhand()));
		this.setDouble(4, "_onhand1", new Double(parameters.getOnhand1()));
		this.setDouble(5, "_iscommited", new Double(parameters.getIscommited()));
		this.setDouble(6, "_onorder", new Double(parameters.getOnorder()));
		this.setDouble(7, "_minstock", new Double(parameters.getMinstock()));
		this.setDouble(8, "_maxstock", new Double(parameters.getMaxstock()));
		this.setDouble(9, "_minorder", new Double(parameters.getMinorder()));
		this.setString(10, "_locked", parameters.getLocked());
		this.setInt(11, "_action", new Integer(action));

		v_resp = this.runUpdate();

		return v_resp;
	}

	/*
	 * Guarda los cambios en los articulos
	 */
	public int cat_branch_mtto(BranchTO parameters, int action)
			throws Exception {

		int v_resp = 0;
		// thsetDbObject("{call sp_cat_branch_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2)}");
		this.setDbObject("{call sp_cat_branch_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setString(1, "_whscode", parameters.getWhscode());
		this.setString(2, "_whsname", parameters.getWhsname());
		this.setString(3, "_grp_code", parameters.getGrp_code());
		this.setBool(4, "_locked", parameters.isLocked());
		this.setString(5, "_street", parameters.getStreet());
		this.setString(6, "_city", parameters.getCity());
		this.setString(7, "_country", parameters.getCountry());
		this.setString(8, "_location", parameters.getLocation());
		this.setString(9, "_usetax", parameters.getUsetax());
		this.setString(10, "_balinvntac", parameters.getBalinvntac());
		this.setString(11, "_salecostac", parameters.getSalecostac());
		this.setString(12, "_transferac", parameters.getTransferac());
		this.setString(13, "_revenuesac", parameters.getRevenuesac());
		this.setString(14, "_varianceac", parameters.getVarianceac());
		this.setString(15, "_decreasac", parameters.getDecreasac());
		this.setString(16, "_increasac", parameters.getIncreasac());
		this.setString(17, "_returnac", parameters.getReturnac());
		this.setString(18, "_expensesac", parameters.getExpensesac());
		this.setString(19, "_frrevenuac", parameters.getFrrevenuac());
		this.setString(20, "_frexpensac", parameters.getFrexpensac());
		this.setString(21, "_pricedifac", parameters.getPricedifac());
		this.setString(22, "_exchangeac", parameters.getExchangeac());
		this.setString(23, "_balanceacc", parameters.getBalanceacc());
		this.setString(24, "_purchaseac", parameters.getPurchaseac());
		this.setString(25, "_pareturnac", parameters.getPareturnac());
		this.setString(26, "_purchofsac", parameters.getPurchofsac());
		this.setString(27, "_shpdgdsact", parameters.getShpdgdsact());
		this.setString(28, "_vatrevact", parameters.getVatrevact());
		this.setString(29, "_decresglac", parameters.getDecresglac());
		this.setString(30, "_incresglac", parameters.getIncresglac());
		this.setString(31, "_stokrvlact", parameters.getStokrvlact());
		this.setString(32, "_stkoffsact", parameters.getStkoffsact());
		this.setString(33, "_wipacct", parameters.getWipacct());
		this.setString(34, "_wipvaracct", parameters.getWipvaracct());
		this.setString(35, "_costrvlact", parameters.getCostrvlact());
		this.setString(36, "_cstoffsact", parameters.getCstoffsact());
		this.setString(37, "_expclract", parameters.getExpclract());
		this.setString(38, "_expofstact", parameters.getExpofstact());
		this.setString(39, "_arcmact", parameters.getArcmact());
		this.setString(40, "_arcmfrnact", parameters.getArcmfrnact());
		this.setString(41, "_arcmexpact", parameters.getArcmexpact());
		this.setString(42, "_apcmact", parameters.getApcmact());
		this.setString(43, "_apcmfrnact", parameters.getApcmfrnact());
		this.setString(44, "_revretact", parameters.getRevretact());
		this.setString(45, "_negstckact", parameters.getNegstckact());
		this.setString(46, "_stkintnact", parameters.getStkintnact());
		this.setString(47, "_purbalact", parameters.getPurbalact());
		this.setString(48, "_whicenact", parameters.getWhicenact());
		this.setString(49, "_whocenact", parameters.getWhocenact());
		this.setString(50, "_excisable", parameters.getExcisable());
		this.setInt(51, "_usersign", parameters.getUsersign());
		this.setInt(52, "_action", new Integer(action));

		v_resp = this.runUpdate();

		return v_resp;
	}

	public List getBranch(String whscode, String whsname) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// this.setDbOct("{call sp_get_branch(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_get_branch(?,?)}");
		this.setString(1, "_whscode", whscode);
		this.setString(2, "_whsname", whsname);

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					BranchTO branch = new BranchTO();
					branch.setWhscode(rowsetActual.getString(1));
					branch.setWhsname(rowsetActual.getString(2));
					branch.setGrp_code(rowsetActual.getString(3));
					branch.setLocked(rowsetActual.getBoolean(4));
					branch.setStreet(rowsetActual.getString(5));
					branch.setCity(rowsetActual.getString(6));
					branch.setCountry(rowsetActual.getString(7));
					branch.setLocation(rowsetActual.getString(8));
					branch.setUsetax(rowsetActual.getString(9));
					branch.setBalinvntac(rowsetActual.getString(10));
					branch.setSalecostac(rowsetActual.getString(11));
					branch.setTransferac(rowsetActual.getString(12));
					branch.setRevenuesac(rowsetActual.getString(13));
					branch.setVarianceac(rowsetActual.getString(14));
					branch.setDecreasac(rowsetActual.getString(15));
					branch.setIncreasac(rowsetActual.getString(16));
					branch.setReturnac(rowsetActual.getString(17));
					branch.setExpensesac(rowsetActual.getString(18));
					branch.setFrrevenuac(rowsetActual.getString(19));
					branch.setFrexpensac(rowsetActual.getString(20));
					branch.setPricedifac(rowsetActual.getString(21));
					branch.setExchangeac(rowsetActual.getString(22));
					branch.setBalanceacc(rowsetActual.getString(23));
					branch.setPurchaseac(rowsetActual.getString(24));
					branch.setPareturnac(rowsetActual.getString(25));
					branch.setPurchofsac(rowsetActual.getString(26));
					branch.setShpdgdsact(rowsetActual.getString(27));
					branch.setVatrevact(rowsetActual.getString(28));
					branch.setDecresglac(rowsetActual.getString(29));
					branch.setIncresglac(rowsetActual.getString(30));
					branch.setStokrvlact(rowsetActual.getString(31));
					branch.setStkoffsact(rowsetActual.getString(32));
					branch.setWipacct(rowsetActual.getString(33));
					branch.setWipvaracct(rowsetActual.getString(34));
					branch.setCostrvlact(rowsetActual.getString(35));
					branch.setCstoffsact(rowsetActual.getString(36));
					branch.setExpclract(rowsetActual.getString(37));
					branch.setExpofstact(rowsetActual.getString(38));
					branch.setArcmact(rowsetActual.getString(39));
					branch.setArcmfrnact(rowsetActual.getString(40));
					branch.setArcmexpact(rowsetActual.getString(41));
					branch.setApcmact(rowsetActual.getString(42));
					branch.setApcmfrnact(rowsetActual.getString(43));
					branch.setRevretact(rowsetActual.getString(44));
					branch.setNegstckact(rowsetActual.getString(45));
					branch.setStkintnact(rowsetActual.getString(46));
					branch.setPurbalact(rowsetActual.getString(47));
					branch.setWhicenact(rowsetActual.getString(48));
					branch.setWhocenact(rowsetActual.getString(49));
					branch.setExcisable(rowsetActual.getString(50));
					branch.setUsersign(rowsetActual.getInt(51));
					_return.add(branch);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	public BranchTO getBranchByKey(String whscode) throws Exception {
		BranchTO _return = new BranchTO();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// th
		// etDbObject("{call sp_get_branch_by_key(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_get_branch_by_key(?)}");
		this.setString(1, "_whscode", whscode);

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		System.out.println("return psg");

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					BranchTO branch = new BranchTO();
					branch.setWhscode(rowsetActual.getString(1));
					branch.setWhsname(rowsetActual.getString(2));
					branch.setGrp_code(rowsetActual.getString(3));
					branch.setLocked(rowsetActual.getBoolean(4));
					branch.setStreet(rowsetActual.getString(5));
					branch.setCity(rowsetActual.getString(6));
					branch.setCountry(rowsetActual.getString(7));
					branch.setLocation(rowsetActual.getString(8));
					branch.setUsetax(rowsetActual.getString(9));
					branch.setBalinvntac(rowsetActual.getString(10));
					branch.setSalecostac(rowsetActual.getString(11));
					branch.setTransferac(rowsetActual.getString(12));
					branch.setRevenuesac(rowsetActual.getString(13));
					branch.setVarianceac(rowsetActual.getString(14));
					branch.setDecreasac(rowsetActual.getString(15));
					branch.setIncreasac(rowsetActual.getString(16));
					branch.setReturnac(rowsetActual.getString(17));
					branch.setExpensesac(rowsetActual.getString(18));
					branch.setFrrevenuac(rowsetActual.getString(19));
					branch.setFrexpensac(rowsetActual.getString(20));
					branch.setPricedifac(rowsetActual.getString(21));
					branch.setExchangeac(rowsetActual.getString(22));
					branch.setBalanceacc(rowsetActual.getString(23));
					branch.setPurchaseac(rowsetActual.getString(24));
					branch.setPareturnac(rowsetActual.getString(25));
					branch.setPurchofsac(rowsetActual.getString(26));
					branch.setShpdgdsact(rowsetActual.getString(27));
					branch.setVatrevact(rowsetActual.getString(28));
					branch.setDecresglac(rowsetActual.getString(29));
					branch.setIncresglac(rowsetActual.getString(30));
					branch.setStokrvlact(rowsetActual.getString(31));
					branch.setStkoffsact(rowsetActual.getString(32));
					branch.setWipacct(rowsetActual.getString(33));
					branch.setWipvaracct(rowsetActual.getString(34));
					branch.setCostrvlact(rowsetActual.getString(35));
					branch.setCstoffsact(rowsetActual.getString(36));
					branch.setExpclract(rowsetActual.getString(37));
					branch.setExpofstact(rowsetActual.getString(38));
					branch.setArcmact(rowsetActual.getString(39));
					branch.setArcmfrnact(rowsetActual.getString(40));
					branch.setArcmexpact(rowsetActual.getString(41));
					branch.setApcmact(rowsetActual.getString(42));
					branch.setApcmfrnact(rowsetActual.getString(43));
					branch.setRevretact(rowsetActual.getString(44));
					branch.setNegstckact(rowsetActual.getString(45));
					branch.setStkintnact(rowsetActual.getString(46));
					branch.setPurbalact(rowsetActual.getString(47));
					branch.setWhicenact(rowsetActual.getString(48));
					branch.setWhocenact(rowsetActual.getString(49));
					branch.setExcisable(rowsetActual.getString(50));
					branch.setUsersign(rowsetActual.getInt(51));
					_return = branch;
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	/* Manejo de listas de precios */
	public int cat_prl0_priceslist_mtto(PricesListTO parameters, int action)
			throws Exception {

		List v_resp;
		// thsetDbObject("{? = call sp_prl0_priceslist_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2)}");
		this.setDbObject("{? = call sp_prl0_priceslist_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

		this.setInt(2, "_listnum,", new Integer(parameters.getListnum()));
		this.setString(3, "_listname,", parameters.getListname());
		this.setInt(4, "_base_num,", new Integer(parameters.getBase_num()));
		this.setDouble(5, "_factor,", new Double(parameters.getFactor()));
		this.setInt(6, "_roundsys,", new Integer(parameters.getRoundsys()));
		this.setInt(7, "_groupcode,", new Integer(parameters.getGroupcode()));
		this.setString(8, "_isgrossprc,", parameters.getIsgrossprc());
		this.setString(9, "_validfor,", parameters.getValidfor());
		this.setString(10, "_roundrule,", parameters.getRoundrule());
		this.setString(11, "_rndfrmtint,", parameters.getRndfrmtint());
		this.setString(12, "_rndfrmtdec,", parameters.getRndfrmtdec());
		this.setInt(13, "_usersign,", new Integer(parameters.getUsersign()));
		this.setInt(14, "_action", new Integer(action));

		v_resp = this.runQuery();

		return this.getInt();
	}

	public List getPricesList(PricesListInTO parameters) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// this.setDbOct("{call sp_get_branch(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{? = call sp_get_priceslist()}");

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					PricesListTO result = new PricesListTO();
					result.setListnum(rowsetActual.getInt(1));
					result.setListname(rowsetActual.getString(2));
					result.setBase_num(rowsetActual.getInt(3));
					result.setFactor(rowsetActual.getDouble(4));
					result.setRoundsys(rowsetActual.getInt(5));
					result.setGroupcode(rowsetActual.getInt(6));
					result.setIsgrossprc(rowsetActual.getString(7));
					result.setValidfor(rowsetActual.getString(8));
					result.setRoundrule(rowsetActual.getString(9));
					result.setRndfrmtint(rowsetActual.getString(10));
					result.setRndfrmtdec(rowsetActual.getString(11));
					result.setUsersign(rowsetActual.getInt(12));

					_return.add(result);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	public PricesListTO getPricesListByKey(int listnum) throws Exception {
		PricesListTO _return = new PricesListTO();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// this.setDbOct("{call sp_get_branch(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_get_priceslist_by_key(?)}");
		this.setInt(1, "_listnum", listnum);

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					PricesListTO result = new PricesListTO();
					result.setListnum(rowsetActual.getInt(1));
					result.setListname(rowsetActual.getString(2));
					result.setBase_num(rowsetActual.getInt(3));
					result.setFactor(rowsetActual.getDouble(4));
					result.setRoundsys(rowsetActual.getInt(5));
					result.setGroupcode(rowsetActual.getInt(6));
					result.setIsgrossprc(rowsetActual.getString(7));
					result.setValidfor(rowsetActual.getString(8));
					result.setRoundrule(rowsetActual.getString(9));
					result.setRndfrmtint(rowsetActual.getString(10));
					result.setRndfrmtdec(rowsetActual.getString(11));
					result.setUsersign(rowsetActual.getInt(12));
					result.setArticlesPrices(getArticlesPrices(listnum));

					_return = result;
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	public int cat_art1_articlesprice_mtto(ArticlesPriceTO parameters,
			int action) throws Exception {
		int v_resp = 0;
		// thsetDbObject("{call sp_art1_articlesprice_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2)}");
		this.setDbObject("{call sp_art1_articlesprice_mtto(?,?,?,?,?,?,?,?,?,?,?)}");
		this.setString(1, "_itemcode,", parameters.getItemcode());
		this.setInt(2, "_pricelist,", parameters.getPricelist());
		this.setDouble(3, "_price,", parameters.getPrice());
		this.setBool(4, "_ovrwritten,", parameters.getOvrwritten());
		this.setDouble(5, "_factor,", parameters.getFactor());
		this.setString(6, "_objtype,", parameters.getObjtype());
		this.setDouble(7, "_addprice1,", parameters.getAddprice1());
		this.setDouble(8, "_addprice2,", parameters.getAddprice2());
		this.setString(9, "_ovrwrite1,", parameters.getOvrwrite1());
		this.setString(10, "_ovrwrite2,", parameters.getOvrwrite2());
		this.setInt(11, "_action", new Integer(action));

		v_resp = this.runUpdate();

		return v_resp;
	}

	public List getArticlesPrices(int listnum) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// this.setDbOct("{call sp_get_branch(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_get_articlesprice(?)}");
		this.setInt(1, "_listnum", listnum);

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					ArticlesPriceTO result = new ArticlesPriceTO();
					result.setItemcode(rowsetActual.getString(1));
					result.setPricelist(rowsetActual.getInt(2));
					result.setPrice(rowsetActual.getDouble(3));
					result.setOvrwritten(rowsetActual.getBoolean(4));
					result.setFactor(rowsetActual.getDouble(5));
					result.setObjtype(rowsetActual.getString(6));
					result.setAddprice1(rowsetActual.getDouble(7));
					result.setAddprice2(rowsetActual.getDouble(8));
					result.setOvrwrite1(rowsetActual.getString(9));
					result.setOvrwrite2(rowsetActual.getString(10));
					result.setDscription(rowsetActual.getString(11));

					_return.add(result);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	public List getArticlePrices(String itemcode) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;

		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		// this.setDbOct("{call sp_get_branch(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_get_articleprices(?)}");
		this.setString(1, "_itemcode", itemcode);

		lstResultSet = this.runQuery();

		CachedRowSetImpl rowsetActual;

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					ArticlesPriceTO result = new ArticlesPriceTO();
					result.setItemcode(rowsetActual.getString(1));
					result.setPricelist(rowsetActual.getInt(2));
					result.setPrice(rowsetActual.getDouble(3));
					result.setOvrwritten(rowsetActual.getBoolean(4));
					result.setFactor(rowsetActual.getDouble(5));
					result.setObjtype(rowsetActual.getString(6));
					result.setAddprice1(rowsetActual.getDouble(7));
					result.setAddprice2(rowsetActual.getDouble(8));
					result.setOvrwrite1(rowsetActual.getString(9));
					result.setOvrwrite2(rowsetActual.getString(10));
					result.setDscription(rowsetActual.getString(11));

					_return.add(result);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	public int cat_art1_articlesprice_delete(int listnum) throws Exception {

		int v_resp = 0;
		// thsetDbObject("{call sp_prl0_priceslist_mtto(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2)}");
		this.setDbObject("{call sp_art1_articlesprice_delete(?)}");
		this.setInt(1, "_listnum", listnum);

		v_resp = this.runUpdate();

		return v_resp;
	}

	/* Mantenimiento de la tabla warehouse y su detalle */
	public int adm_warehousejournal_mtto(WarehouseJournalTO parameters,
			int accion) throws Exception {

		List v_resp;
		// t.setDbObject("{call sp_inv_gis0_goodsissues_mtto    (1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{? = call sp_adm_warehousejournal_mtto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		this.setInt(2, "_transseq", new Integer(parameters.getTransseq()));
		this.setInt(3, "_transtype ", new Integer(parameters.getTranstype()));
		this.setInt(4, "_createdby ", new Integer(parameters.getCreatedby()));
		this.setString(5, "_base_ref ", parameters.getBase_ref());
		this.setInt(6, "_doclinenum ", new Integer(parameters.getDoclinenum()));
		this.setString(7, "_itemcode ", parameters.getItemcode());
		this.setDouble(8, "_inqty ", new Double(parameters.getInqty()));
		this.setDouble(9, "_outqty ", new Double(parameters.getOutqty()));
		this.setDouble(10, "_price ", new Double(parameters.getPrice()));
		this.setString(11, "_trnsfract ", parameters.getTrnsfract());
		this.setString(12, "_pricedifac ", parameters.getPricedifac());
		this.setString(13, "_varianceac ", parameters.getVarianceac());
		this.setString(14, "_returnact ", parameters.getReturnact());
		this.setString(15, "_clearact ", parameters.getClearact());
		this.setString(16, "_costact ", parameters.getCostact());
		this.setString(17, "_wipact ", parameters.getWipact());
		this.setDouble(18, "_openstock ", new Double(parameters.getOpenstock()));
		this.setDouble(19, "_pricediff ", new Double(parameters.getPricediff()));
		this.setString(20, "_invntact ", parameters.getInvntact());
		this.setInt(21, "_sublinenum ", new Integer(parameters.getSublinenum()));
		this.setInt(22, "_appobjline ", new Integer(parameters.getAppobjline()));
		this.setDouble(23, "_expenses ", new Double(parameters.getExpenses()));
		this.setDouble(24, "_openexp ", new Double(parameters.getOpenexp()));
		this.setDouble(25, "_allocation ",
				new Double(parameters.getAllocation()));
		this.setDouble(26, "_openalloc ", new Double(parameters.getOpenalloc()));
		this.setDouble(27, "_expalloc ", new Double(parameters.getExpalloc()));
		this.setDouble(28, "_oexpalloc ", new Double(parameters.getOexpalloc()));
		this.setDouble(29, "_openpdiff ", new Double(parameters.getOpenpdiff()));
		this.setDouble(30, "_neginvadjs ",
				new Double(parameters.getNeginvadjs()));
		this.setDouble(31, "_openneginv ",
				new Double(parameters.getOpenneginv()));
		this.setString(32, "_negstckact ", parameters.getNegstckact());
		this.setDouble(33, "_btransval ", new Double(parameters.getBtransval()));
		this.setDouble(34, "_varval ", new Double(parameters.getVarval()));
		this.setDouble(35, "_bexpval ", new Double(parameters.getBexpval()));
		this.setDouble(36, "_cogsval ", new Double(parameters.getCogsval()));
		this.setDouble(37, "_bnegaval ", new Double(parameters.getBnegaval()));
		this.setString(38, "_ioffincacc ", parameters.getIoffincacc());
		this.setDouble(39, "_ioffincval ",
				new Double(parameters.getIoffincval()));
		this.setString(40, "_doffdecacc ", parameters.getDoffdecacc());
		this.setDouble(41, "_doffdecval ",
				new Double(parameters.getDoffdecval()));
		this.setString(42, "_decacc ", parameters.getDecacc());
		this.setDouble(43, "_decval ", new Double(parameters.getDecval()));
		this.setDouble(44, "_wipval ", new Double(parameters.getWipval()));
		this.setString(45, "_wipvaracc ", parameters.getWipvaracc());
		this.setDouble(46, "_wipvarval ", new Double(parameters.getWipvarval()));
		this.setString(47, "_incact ", parameters.getIncact());
		this.setDouble(48, "_incval ", new Double(parameters.getIncval()));
		this.setString(49, "_expcacc ", parameters.getExpcacc());
		this.setInt(50, "_messageid ", new Integer(parameters.getMessageid()));
		this.setInt(51, "_loctype ", new Integer(parameters.getLoctype()));
		this.setString(52, "_loccode ", parameters.getLoccode());
		this.setString(53, "_poststatus ", parameters.getPoststatus());
		this.setDouble(54, "_sumstock ", new Double(parameters.getSumstock()));
		this.setDouble(55, "_openqty ", new Double(parameters.getOpenqty()));
		this.setString(56, "_paoffacc ", parameters.getPaoffacc());
		this.setDouble(57, "_paoffval ", new Double(parameters.getPaoffval()));
		this.setDouble(58, "_openpaoff ", new Double(parameters.getOpenpaoff()));
		this.setString(59, "_paacc ", parameters.getPaacc());
		this.setDouble(60, "_paval ", new Double(parameters.getPaval()));
		this.setDouble(61, "_openpa ", new Double(parameters.getOpenpa()));

		this.setInt(62, "_action", new Integer(accion));
		v_resp = this.runQuery();
		// System.out.println(this.getInt());

		return this.getInt();

	}

	public WarehouseJournalTO getWarehouseJournalByKey(int transseq)
			throws Exception {
		WarehouseJournalTO _return = new WarehouseJournalTO();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_warehousejournal_by_key(?)}");
		this.setInt(1, "_transseq", new Integer(transseq));
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		AdminDAO Detail = new AdminDAO();
		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					WarehouseJournalTO warehouse = new WarehouseJournalTO();
					warehouse.setTransseq(rowsetActual.getInt(1));
					warehouse.setTranstype(rowsetActual.getInt(2));
					warehouse.setCreatedby(rowsetActual.getInt(3));
					warehouse.setBase_ref(rowsetActual.getString(4));
					warehouse.setDoclinenum(rowsetActual.getInt(5));
					warehouse.setItemcode(rowsetActual.getString(6));
					warehouse.setInqty(rowsetActual.getDouble(7));
					warehouse.setOutqty(rowsetActual.getDouble(8));
					warehouse.setPrice(rowsetActual.getDouble(9));
					warehouse.setTrnsfract(rowsetActual.getString(10));
					warehouse.setPricedifac(rowsetActual.getString(11));
					warehouse.setVarianceac(rowsetActual.getString(12));
					warehouse.setReturnact(rowsetActual.getString(13));
					warehouse.setClearact(rowsetActual.getString(14));
					warehouse.setCostact(rowsetActual.getString(15));
					warehouse.setWipact(rowsetActual.getString(16));
					warehouse.setOpenstock(rowsetActual.getDouble(17));
					warehouse.setPricediff(rowsetActual.getDouble(18));
					warehouse.setInvntact(rowsetActual.getString(19));
					warehouse.setSublinenum(rowsetActual.getInt(20));
					warehouse.setAppobjline(rowsetActual.getInt(21));
					warehouse.setExpenses(rowsetActual.getDouble(22));
					warehouse.setOpenexp(rowsetActual.getDouble(23));
					warehouse.setAllocation(rowsetActual.getDouble(24));
					warehouse.setOpenalloc(rowsetActual.getDouble(25));
					warehouse.setExpalloc(rowsetActual.getDouble(26));
					warehouse.setOexpalloc(rowsetActual.getDouble(27));
					warehouse.setOpenpdiff(rowsetActual.getDouble(28));
					warehouse.setNeginvadjs(rowsetActual.getDouble(29));
					warehouse.setOpenneginv(rowsetActual.getDouble(30));
					warehouse.setNegstckact(rowsetActual.getString(31));
					warehouse.setBtransval(rowsetActual.getDouble(32));
					warehouse.setVarval(rowsetActual.getDouble(33));
					warehouse.setBexpval(rowsetActual.getDouble(34));
					warehouse.setCogsval(rowsetActual.getDouble(35));
					warehouse.setBnegaval(rowsetActual.getDouble(36));
					warehouse.setIoffincacc(rowsetActual.getString(37));
					warehouse.setIoffincval(rowsetActual.getDouble(38));
					warehouse.setDoffdecacc(rowsetActual.getString(39));
					warehouse.setDoffdecval(rowsetActual.getDouble(40));
					warehouse.setDecacc(rowsetActual.getString(41));
					warehouse.setDecval(rowsetActual.getDouble(42));
					warehouse.setWipval(rowsetActual.getDouble(43));
					warehouse.setWipvaracc(rowsetActual.getString(44));
					warehouse.setWipvarval(rowsetActual.getDouble(45));
					warehouse.setIncact(rowsetActual.getString(46));
					warehouse.setIncval(rowsetActual.getDouble(47));
					warehouse.setExpcacc(rowsetActual.getString(48));
					warehouse.setMessageid(rowsetActual.getInt(49));
					warehouse.setLoctype(rowsetActual.getInt(50));
					warehouse.setLoccode(rowsetActual.getString(51));
					warehouse.setPoststatus(rowsetActual.getString(52));
					warehouse.setSumstock(rowsetActual.getDouble(53));
					warehouse.setOpenqty(rowsetActual.getDouble(54));
					warehouse.setPaoffacc(rowsetActual.getString(55));
					warehouse.setPaoffval(rowsetActual.getDouble(56));
					warehouse.setOpenpaoff(rowsetActual.getDouble(57));
					warehouse.setPaacc(rowsetActual.getString(58));
					warehouse.setPaval(rowsetActual.getDouble(59));
					warehouse.setOpenpa(rowsetActual.getDouble(60));

					warehouse.setDetailWarehouse(Detail
							.getwarehousejournalDetail(rowsetActual.getInt(1)));
					_return = warehouse;
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	public int adm_warehousejournalDetail_mtto(
			WarehouseJournalDetailTO parameters, int accion) throws Exception {
		int v_resp;
		// t.setDbObject("{call sp_inv_gis0_goodsissues_mtto     (1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1)}");
		this.setDbObject("{call sp_adm_warehousejournallayer_mtto(?,?,?,?,?,?,?,?,?)}");
		this.setInt(1, "_transseq", new Integer(parameters.getTransseq()));
		this.setInt(2, "_layerid", new Integer(parameters.getLayerid()));
		this.setDouble(3, "_calcprice", new Double(parameters.getCalcprice()));
		this.setDouble(4, "_balance", new Double(parameters.getBalance()));
		this.setDouble(5, "_transvalue", new Double(parameters.getTransvalue()));
		this.setDouble(6, "_layerinqty", new Double(parameters.getLayerinqty()));
		this.setDouble(7, "_layeroutq", new Double(parameters.getLayeroutq()));
		this.setDouble(8, "_revaltotal", new Double(parameters.getRevaltotal()));

		this.setInt(9, "_action", new Integer(accion));
		v_resp = this.runUpdate();
		// System.out.println(this.getInt());

		return v_resp;

	}

	public List getwarehousejournalDetail(int transseq) throws Exception {
		List _return = new Vector();
		List lstResultSet = null;
		this.setTypeReturn(Common.TYPERETURN_CURSOR);
		this.setDbObject("{call sp_get_warehousejournallayer_by_key(?)}");
		this.setInt(1, "_transseq", new Integer(transseq));
		lstResultSet = this.runQuery();
		CachedRowSetImpl rowsetActual;
		System.out.println("return psg");
		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();

		while (liRowset.hasNext()) {
			rowsetActual = (CachedRowSetImpl) liRowset.next();
			try {
				while (rowsetActual.next()) {
					WarehouseJournalDetailTO warehouse = new WarehouseJournalDetailTO();
					warehouse.setTransseq(rowsetActual.getInt(1));
					warehouse.setLayerid(rowsetActual.getInt(2));
					warehouse.setCalcprice(rowsetActual.getDouble(3));
					warehouse.setBalance(rowsetActual.getDouble(4));
					warehouse.setTransvalue(rowsetActual.getDouble(5));
					warehouse.setLayerinqty(rowsetActual.getDouble(6));
					warehouse.setLayeroutq(rowsetActual.getDouble(7));
					warehouse.setRevaltotal(rowsetActual.getDouble(8));

					_return.add(warehouse);
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _return;
	}

	/*
	 * busqueda de catalogos por query string Rutilio Iraheta Marzo 2015
	 */
	public List findCatQS(String nameCatalog) throws Exception {
		List lstResult = new Vector();
		List lstResultSet = null;

		System.out.println("Desde DAO");
		this.setDbObject("SELECT c.catcode, c.tablecode, c.catvalue, "
				+ "c.catvalue2,c.catvalue3,c.catstatus, c.usersign "
				+ "FROM cat_tab1_catalogos c,cat_tab0_tables t  "
				+ "where c.tablecode=t.tablecode  and t.tablename=?");
		// this.setString(1, "return");
		this.setString(1, "IN_CAT_NAME", nameCatalog);

		lstResultSet = this.runQueryPrepared();
		System.out.println("return psg");

		CachedRowSetImpl rowsetActual;

		ListIterator liRowset = null;
		liRowset = lstResultSet.listIterator();
		// Iterator<CachedRowSetImpl> iterator = lstResult.iterator();
		while (liRowset.hasNext()) {

			rowsetActual = (CachedRowSetImpl) liRowset.next();

			try {
				while (rowsetActual.next()) {
					/*
					 * System.out.println(rowsetActual.getString(1));
					 * System.out.println(rowsetActual.getString(2));
					 * System.out.println(rowsetActual.getString(3));
					 */

					lstResult.add(new CatalogTO(rowsetActual.getString(1),
							rowsetActual.getInt(2), rowsetActual.getString(3),
							rowsetActual.getString(4), rowsetActual
									.getString(5), rowsetActual.getString(6),
							rowsetActual.getInt(7)));
				}
				rowsetActual.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return lstResult;
	}

	public ResultOutTO Update_inventory_articles(ArticlesInTO Article)
			throws EJBException {

		List brachArticles = new Vector();
		ResultOutTO _return = new ResultOutTO();
		BranchArticlesTO brachArt = new BranchArticlesTO();
		ArticlesTO Article2 = new ArticlesTO();

		try {
			brachArticles = getBranchArticles(Article.getItemCode());
			Article2 = getArticlesByKey(Article.getItemCode());
			double AvgPrice = 0;
			double total;
			double total_quantity = 0;
			double quantity;
			List lstResultSet = null;

			if (Article.getObjtype().equals("30")
					|| Article.getObjtype().equals("11")
					|| Article.getObjtype().equals("20")) {
				// nueva cantidad de
				// articulos------------------------------------------------------
				total_quantity = Article2.getOnHand() + Article.getOnHand();
				// monto
				// total----------------------------------------------------------------------
				total = (Article2.getAvgPrice() * Article2.getOnHand())
						+ (Article.getOnHand() * Article.getAvgPrice());
				// nuevo
				// precio---------------------------------------------------------------------
				AvgPrice = total / total_quantity;
			} else {
				// nueva cantidad de
				// articulos------------------------------------------------------
				total_quantity = Article2.getOnHand() - Article.getOnHand();

			}
			// ------------------------------------------------------------------------------------------------------------------------
			// Actualizacion de monto de articulos por alamacen
			// ------------------------------------------------------------------------------------------------------------------------

			Iterator<BranchArticlesTO> iterator = brachArticles.iterator();
			while (iterator.hasNext()) {
				BranchArticlesTO branch2 = (BranchArticlesTO) iterator.next();
				if (branch2.getWhscode().equals(Article.getSww())
						&& branch2.getItemcode().equals(Article.getItemCode())) {
					if (Article.getObjtype().equals("30")
							|| Article.getObjtype().equals("11")
							|| Article.getObjtype().equals("20")) {
						quantity = branch2.getOnhand() + Article.getOnHand();
					} else {
						quantity = branch2.getOnhand() - Article.getOnHand();
					}

					this.setDbObject("UPDATE cat_art1_brancharticles SET  onhand=? WHERE itemcode=? And whscode=?");
					this.setDouble(1, "_onhand", quantity);
					this.setString(2, "_itemcode", Article.getItemCode());
					this.setString(3, "whscode", Article.getSww());

					lstResultSet = this.runQueryPrepared();

				}

			}

			// ------------------------------------------------------------------------------------------------------------------------
			// Actualizacion de monto y precio de articulos en la tabla
			// articulos
			// ------------------------------------------------------------------------------------------------------------------------
		
			if (!Article.getObjtype().equals("30")|| !Article.getObjtype().equals("11")|| !Article.getObjtype().equals("20")){
				AvgPrice=Article2.getAvgPrice();
			}
			
			this.setDbObject("UPDATE cat_art0_articles"
					+ "SET  avgprice=?, onhand=? WHERE itemcode=?");

			this.setDouble(1, "_avgprice", AvgPrice);
			this.setString(2, "_onhand", total_quantity);
			this.setString(3, "_itemcode", Article.getItemCode());

			lstResultSet = this.runQueryPrepared();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos actualizados correctamente");
		return _return;

	}
}

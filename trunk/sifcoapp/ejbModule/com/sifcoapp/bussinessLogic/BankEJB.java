package com.sifcoapp.bussinessLogic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.admin.ejb.ParameterEJB;
import com.sifcoapp.objects.accounting.dao.AccountingDAO;
import com.sifcoapp.objects.accounting.to.AccassignmentTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.dao.ParameterDAO;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchTO;
import com.sifcoapp.objects.admin.to.CatalogTO;
import com.sifcoapp.objects.admin.to.parameterTO;
import com.sifcoapp.objects.bank.dao.CheckForPaymentDAO;
import com.sifcoapp.objects.bank.dao.ColecturiaConceptDAO;
import com.sifcoapp.objects.bank.dao.ColecturiaDAO;
import com.sifcoapp.objects.bank.dao.ColecturiaDetailDAO;
import com.sifcoapp.objects.bank.to.CheckForPaymentInTO;
import com.sifcoapp.objects.bank.to.CheckForPaymentTO;
import com.sifcoapp.objects.bank.to.ColecturiaConceptTO;
import com.sifcoapp.objects.bank.to.ColecturiaDetailTO;
import com.sifcoapp.objects.bank.to.ColecturiaInTO;
import com.sifcoapp.objects.bank.to.ColecturiaTO;
import com.sifcoapp.objects.catalog.dao.BusinesspartnerDAO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerAcountTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
import com.sifcoapp.objects.purchase.dao.PurchaseDetailDAO;
import com.sifcoapp.objects.purchase.to.PurchaseDetailTO;
import com.sifcoapp.objects.sales.to.SalesDetailTO;
import com.sifcoapp.objects.sales.to.SalesInTO;
import com.sifcoapp.objects.sales.to.SalesTO;

@Stateless
public class BankEJB implements BankEJBRemote {

	private Double zero = 0.00;

	public BankEJB() {
		// TODO Auto-generated constructor stub
	}

	public ResultOutTO ges_cfp0_checkforpayment_mtto(
			CheckForPaymentTO parameters, int action) throws EJBException {

		ResultOutTO _return = new ResultOutTO();
		CheckForPaymentDAO DAO = new CheckForPaymentDAO();
		DAO.setIstransaccional(true);

		if (parameters.getChecksum() == null) {
			parameters.setChecksum(zero);
		}
		if (parameters.getLinessum() == null) {
			parameters.setLinessum(zero);
		}
		if (parameters.getVattotal() == null) {
			parameters.setVattotal(zero);
		}

		try {
			_return.setDocentry(DAO.ges_cfp0_checkforpayment_mtto(parameters,
					action));
			DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos ingresados correctamente");
		return _return;
	}

	public List get_cfp0_checkforpayment(CheckForPaymentInTO parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		CheckForPaymentDAO DAO = new CheckForPaymentDAO();

		if (parameters.getChecksum() == null) {
			parameters.setChecksum(zero);
		}
		if (parameters.getLinessum() == null) {
			parameters.setLinessum(zero);
		}
		if (parameters.getVattotal() == null) {
			parameters.setVattotal(zero);
		}

		try {
			_return = DAO.get_cfp0_checkforpayment(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;//
	}

	public CheckForPaymentTO get_cfp0_checkforpaymentByKey(int parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		CheckForPaymentTO _return = new CheckForPaymentTO();//
		CheckForPaymentDAO DAO = new CheckForPaymentDAO();
		try {
			_return = DAO.get_cfp0_checkforpaymentByKey(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO ges_ges_col0_colecturia_mtto(ColecturiaTO parameters,
			int action) throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		JournalEntryTO journal = new JournalEntryTO();
		ResultOutTO res_jour = new ResultOutTO();
		ColecturiaDAO DAO = new ColecturiaDAO();
		ColecturiaDetailDAO DAO1 = new ColecturiaDetailDAO(DAO.getConn());
		DAO.setIstransaccional(true);
		DAO1.setIstransaccional(true);
		List sale = new Vector();
		parameterTO parameter = new parameterTO();
		ParameterEJB ejb = new ParameterEJB();
		parameter = ejb.getParameterbykey(9);

		if (parameters.getDoctotal() == null) {
			parameters.setDoctotal(zero);
		}

		try {

			_return.setDocentry(DAO.ges_ges_col0_colecturia_mtto(parameters,
					action));
			parameters.setDocentry(_return.getDocentry());
			// Acciones con los hijos
			Iterator<ColecturiaDetailTO> iterator = parameters
					.getColecturiaDetail().iterator();
			while (iterator.hasNext()) {
				ColecturiaDetailTO detail = (ColecturiaDetailTO) iterator
						.next();
              
				if(detail.getPaidsum()!=zero){
				
				if (detail.getPaidsum() == null) {
					detail.setPaidsum(zero);
				}
				if (detail.getVatsum() == null) {
					detail.setVatsum(zero);
				}
				// Para articulos nuevos
				detail.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					DAO1.ges_ges_col0_colecturiadetail_mtto(detail,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					DAO1.ges_ges_col0_colecturiadetail_mtto(detail,
							Common.MTTODELETE);
				}

				if (detail.getLinenum() == Integer.parseInt(parameter
						.getValue1())) {
					sale = detail.getFacturas();

				}
				
				}else{
					parameters.getColecturiaDetail().remove(detail);
				}
			}

			if (parameters.getSeries() == 1) {

				journal = fill_JournalEntry(parameters);
				AccountingEJB account = new AccountingEJB();
				res_jour = account.journalEntry_mtto(journal,
						Common.MTTOINSERT, DAO.getConn());
				ResultOutTO nuevo = new ResultOutTO();
				if (sale != null) {
					nuevo = actualizar_sale(sale, DAO.getConn(), parameters);
				}
			}

			if (parameters.getSeries() == 2) {

				journal = fill_JournalEntry_anular(parameters);
				AccountingEJB account1 = new AccountingEJB();
				res_jour = account1.journalEntry_mtto(journal,
						Common.MTTOINSERT, DAO.getConn());

				ResultOutTO nuevo1 = new ResultOutTO();
				nuevo1 = actualizar_sale2(DAO.getConn(), parameters);
			}

			DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos ingresados correctamente");
		return _return;
	}

	public List get_ges_colecturia(ColecturiaInTO parameters)
			throws EJBException {
		List _return = new Vector();
		ColecturiaDAO DAO = new ColecturiaDAO();

		if (parameters.getDoctotal() == null) {
			parameters.setDoctotal(zero);
		}

		try {
			_return = DAO.get_ges_colecturia(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ColecturiaTO get_ges_colecturiaByKey(int parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		ColecturiaTO _return = new ColecturiaTO();
		ColecturiaDAO DAO = new ColecturiaDAO();
		try {
			_return = DAO.get_ges_colecturiaByKey(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public List get_ges_colecturiaConcept() throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		ColecturiaConceptDAO DAO = new ColecturiaConceptDAO();
		try {
			_return = DAO.get_ges_colecturiaConcept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public List get_ges_colecturiaConcept1(String Code) throws EJBException {
		// TODO Auto-generated method stub
		List _return = new Vector();
		ColecturiaConceptDAO DAO = new ColecturiaConceptDAO();
		ColecturiaConceptTO colecturia = new ColecturiaConceptTO();
		List facturas = new Vector();
		SalesInTO aux = new SalesInTO();
		aux.setCardcode(Code);
		SalesEJB sales = new SalesEJB();
		try {
			facturas = sales.getSales(aux);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// cosulta del colecturia concept con su saldo de la cuenta
		try {
			_return = DAO.get_ges_colecturiaConcept1(Code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		// filtra las facturas
		facturas = filtro_consulta(facturas);
		if (facturas != null) {
			parameterTO parameter = new parameterTO();
			ParameterEJB ejb = new ParameterEJB();
			parameter = ejb.getParameterbykey(9);

			Iterator<ColecturiaConceptTO> iterator = _return.iterator();
			while (iterator.hasNext()) {
				ColecturiaConceptTO concept = (ColecturiaConceptTO) iterator
						.next();
				if (concept.getLinenum() == Integer.parseInt(parameter
						.getValue1())) {
					concept.setFacturas(facturas);

				}

			}
		}
		return _return;
	}

	public ResultOutTO ges_ges_col2_colecturiaConcepts_mtto(
			ColecturiaConceptTO parameters, int action) throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		ColecturiaConceptDAO DAO = new ColecturiaConceptDAO();
		ColecturiaConceptTO lstPeriods = new ColecturiaConceptTO();
		List lstPeriods3 = null;
		// nuevo.setDocentry(1);

		try {
			lstPeriods3 = DAO.get_ges_colecturiaConcept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			_return.setDocentry(DAO.ges_ges_col2_colecturiaConcepts_mtto(
					parameters, action));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		_return.setCodigoError(0);
		_return.setMensaje("datos almacenados correctamente");
		return _return;
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------
	// llenado de journal entry para el mantenimiento de colecturia
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------
	public JournalEntryTO fill_JournalEntry(ColecturiaTO parameters)
			throws Exception {
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		parameterTO cuentas = new parameterTO();
		String Objtype = "";
		boolean ind = false;
		Double total = zero;
		int n = 1;
		int count = 1;
		double total_sum = 0.0;
		List list_param = parameters.getColecturiaDetail();
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();
		AdminDAO admin = new AdminDAO();
		// consulta para encontrar todas las cuentas asignadas para el asiento
		// contable
		// cuenta de pasivo administrativo
		BusinesspartnerDAO bDAO = new BusinesspartnerDAO();

		aux = bDAO.get_businesspartnerAcount(parameters.getCardcode());

		ParameterDAO DAO = new ParameterDAO();
		cuentas = DAO.getParameterbykey(10);

		// recorre la lista de listas para encontrar los detalles de el asiento
		// contable
		List detail = new Vector();

		for (Object obj2 : list_param) {

			ColecturiaDetailTO colecturia_c = (ColecturiaDetailTO) obj2;
			String linememo = " ";
			Double sum = zero;
			String acc = null;
			String pasivo = null;
			double ingreso;
			String caja;
			String business = null;
			for (Object obj : aux) {
				BusinesspartnerAcountTO bus = (BusinesspartnerAcountTO) obj;
				// pendiente de confirmar si
				if (bus.getAcctype() == colecturia_c.getLinenum()) {
					business = bus.getAcctcode();
				}
			}

			sum = colecturia_c.getPaidsum();
			total_sum = total_sum + sum;
			acc = colecturia_c.getAcctcode();
			linememo = colecturia_c.getDscription();

			// llenado de las cuentas por concepto de colecturia segun la cuenta
			// que tienen asignadas

			// codigo de cuenta del socio de negocio
			JournalEntryLinesTO art1 = new JournalEntryLinesTO();
			art1.setLine_id(n);
			art1.setAccount(business);
			art1.setCredit(sum);
			art1.setDuedate(parameters.getDocduedate());
			art1.setShortname(business);
			art1.setContraact(colecturia_c.getAcctcode());
			art1.setLinememo("pago de colecturia");
			art1.setRefdate(parameters.getDocduedate());
			art1.setRef1(parameters.getRef1());
			// art1.setRef2();
			art1.setBaseref(parameters.getRef1());
			art1.setTaxdate(parameters.getDocduedate());
			// art1.setFinncpriod(finncpriod);
			art1.setReltransid(-1);
			art1.setRellineid(-1);
			art1.setReltype("N");
			art1.setObjtype("5");
			art1.setVatline("N");
			art1.setVatamount(0.0);
			art1.setClosed("N");
			art1.setGrossvalue(0.0);
			art1.setBalduedeb(0.0);
			art1.setBalduecred(art1.getCredit());
			art1.setIsnet("Y");
			art1.setTaxtype(0);
			art1.setTaxpostacc("N");
			art1.setTotalvat(0.0);
			art1.setWtliable("N");
			art1.setWtline("N");
			art1.setPayblock("N");
			art1.setOrdered("N");
			art1.setTranstype(colecturia_c.getObjtype());
			detail.add(art1);

			n++;

			// consulta para encontrar la cuenta asignada para el IVA tomando
			// como
			// venta iva debito
			admin = new AdminDAO();

			CatalogTO Catalog = new CatalogTO();
			Catalog = admin.findCatalogByKey("IVA", 10);
			if (Catalog.getCatvalue2() == null) {
				throw new Exception("No tiene cuenta asignada para impuestos");
			}
			String iva_c = Catalog.getCatvalue2();

			JournalEntryLinesTO art2 = new JournalEntryLinesTO();

			// ----------------------------------------------------------------------------------------------------------------------------------
			// cuenta de efectivo y caja
			// ----------------------------------------------------------------------------------------------------------------------------------

			art2.setLine_id(n);
			art2.setAccount(colecturia_c.getAcctcode());
			art2.setDebit(sum);
			art2.setDuedate(parameters.getDocduedate());
			art2.setShortname(colecturia_c.getAcctcode());
			art2.setContraact(business);
			art2.setLinememo("pago de colecturia ");
			art2.setRefdate(parameters.getDocduedate());
			art2.setRef1(parameters.getRef1());
			// art2.setRef2();
			art2.setBaseref(parameters.getRef1());
			art2.setTaxdate(parameters.getDocduedate());
			// art1.setFinncpriod(finncpriod);
			art2.setReltransid(-1);
			art2.setRellineid(-1);
			art2.setReltype("N");
			art2.setObjtype("5");
			art2.setVatline("N");
			art2.setVatamount(0.0);
			art2.setClosed("N");
			art2.setGrossvalue(0.0);
			art2.setBalduedeb(sum);
			art2.setBalduecred(0.0);
			art2.setIsnet("Y");
			art2.setTaxtype(0);
			art2.setTaxpostacc("N");
			art2.setTotalvat(0.0);
			art2.setWtliable("N");
			art2.setWtline("N");
			art2.setPayblock("N");
			art2.setOrdered("N");
			art2.setTranstype(colecturia_c.getObjtype());
			n = n + 1;
			//
			detail.add(art2);

			// falta condicion de cuando llevar
			if (colecturia_c.getAditional_account() != null && colecturia_c.getAditional_account().equals("Y")) {

				// ----------------------------------------------------------------------------------------------------------------------------------
				// calculo del iva y del ingreso administrativo segundo asiento
				// contable
				// ----------------------------------------------------------------------------------------------------------------------------------

				JournalEntryLinesTO art3 = new JournalEntryLinesTO();
				// ----------------------------------------------------------------------------------------------------------------------------------
				// pasivo administrativo
				// ----------------------------------------------------------------------------------------------------------------------------------
				art3.setLine_id(n); 
				art3.setAccount(colecturia_c.getAcctcode2());
				art3.setDebit(sum);
				art3.setDuedate(parameters.getDocduedate());
				art3.setShortname(colecturia_c.getAcctcode2());
				art3.setContraact(colecturia_c.getAcctcode3() + "-" + iva_c);
				art3.setLinememo("pago de colecturia");
				art3.setRefdate(parameters.getDocduedate());
				art3.setRef1(parameters.getRef1());
				// art2.setRef2();
				art3.setBaseref(parameters.getRef1());
				art3.setTaxdate(parameters.getDocduedate());
				// art1.setFinncpriod(finncpriod);
				art3.setReltransid(-1);
				art3.setRellineid(-1);
				art3.setReltype("N");
				art3.setObjtype("5");
				art3.setVatline("N");
				art3.setVatamount(0.0);
				art3.setClosed("N");
				art3.setGrossvalue(0.0);
				art3.setBalduedeb(sum);
				art3.setBalduecred(0.0);
				art3.setIsnet("Y");
				art3.setTaxtype(0);
				art3.setTaxpostacc("N");
				art3.setTotalvat(0.0);
				art3.setWtliable("N");
				art3.setWtline("N");
				art3.setPayblock("N");
				art3.setOrdered("N");
				// at2.setTranstype(parameters.getObjtype());
				n = n + 1;
				detail.add(art3);

				if (colecturia_c.getTaxstatus() != null && colecturia_c.getTaxstatus().equals("Y")) {
					// ----------------------------------------------------------------------------------------------------------------------------------
					// iva debito fiscal
					// ----------------------------------------------------------------------------------------------------------------------------------

					Double iva = Double.parseDouble(Catalog.getCatvalue()) / 100;
					ingreso = sum / (1 + iva);
					Double t_iva = ingreso * iva;

					JournalEntryLinesTO art4 = new JournalEntryLinesTO();

					art4.setLine_id(n);
					art4.setAccount(iva_c);

					art4.setCredit(t_iva);

					art4.setDuedate(parameters.getDocduedate());
					art4.setShortname(iva_c);
					art4.setContraact(colecturia_c.getAcctcode2());
					art4.setLinememo("pago de colecturia");
					art4.setRefdate(parameters.getDocduedate());
					art4.setRef1(parameters.getRef1());
					// rt2.setRef2();
					art4.setBaseref(parameters.getRef1());
					art4.setTaxdate(parameters.getDocduedate());
					// art1.setFinncpriod(finncpriod);
					art4.setReltransid(-1);
					art4.setRellineid(-1);
					art4.setReltype("N");
					art4.setObjtype("5");
					art4.setVatline("N");
					art4.setVatamount(0.0);
					art4.setClosed("N");
					art4.setGrossvalue(0.0);
					art4.setBalduedeb(0.0);
					art4.setBalduecred(t_iva);
					art4.setIsnet("Y");
					art4.setTaxtype(0);
					art4.setTaxpostacc("N");
					art4.setTotalvat(0.0);
					art4.setWtliable("N");
					art4.setWtline("N");
					art4.setPayblock("N");
					art4.setOrdered("N");
					// t2.setTranstype(parameters.getObjtype());
					n = n + 1;
					detail.add(art4);
				} else {

					ingreso = sum;
				}
				// ----------------------------------------------------------------------------------------------------------------------------------
				// ingreso administrativo
				// ----------------------------------------------------------------------------------------------------------------------------------
				JournalEntryLinesTO art5 = new JournalEntryLinesTO();

				art5.setLine_id(n);
				art5.setAccount(colecturia_c.getAcctcode3());
				art5.setCredit(ingreso);
				art5.setDuedate(parameters.getDocduedate());
				art5.setShortname(colecturia_c.getAcctcode3());
				art5.setContraact(colecturia_c.getAcctcode2());
				art5.setLinememo("pago de colecturia");
				art5.setRefdate(parameters.getDocduedate());
				art5.setRef1(parameters.getRef1());
				// art2.setRef2();
				art5.setBaseref(parameters.getRef1());
				art5.setTaxdate(parameters.getDocduedate());
				// art1.setFinncpriod(finncpriod);
				art5.setReltransid(-1);
				art5.setRellineid(-1);
				art5.setReltype("N");
				art5.setObjtype("5");
				art5.setVatline("N");
				art5.setVatamount(0.0);
				art5.setClosed("N");
				art5.setGrossvalue(0.0);
				art5.setBalduedeb(0.0);
				art5.setBalduecred(ingreso);
				art5.setIsnet("Y");
				art5.setTaxtype(0);
				art5.setTaxpostacc("N");
				art5.setTotalvat(0.0);
				art5.setWtliable("N");
				art5.setWtline("N");
				art5.setPayblock("N");
				art5.setOrdered("N");
				// 5.setTranstype(parameters.getObjtype());
				detail.add(art5);
				n++;

			}

		}
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// llenado del asiento contable
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// LLenado del padre

		nuevo.setBtfstatus("O");
		nuevo.setTranstype(Objtype);
		nuevo.setBaseref(Integer.toString(parameters.getDocnum()));
		nuevo.setRefdate(parameters.getDocdate());
		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setRef1(Integer.toString(parameters.getDocnum()));
		nuevo.setRef2(parameters.getRef1());
		nuevo.setLoctotal(total_sum);
		nuevo.setSystotal(total_sum);
		nuevo.setTransrate(0.0);
		nuevo.setDuedate(parameters.getDocduedate());
		nuevo.setTaxdate(parameters.getDocdate());
		nuevo.setFinncpriod(0);
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setRefndrprt("N");
		nuevo.setObjtype("5");
		nuevo.setAdjtran("N");
		nuevo.setAutostorno("N");
		nuevo.setSeries(0);
		nuevo.setAutovat("N");
		nuevo.setDocseries(0);
		nuevo.setPrinted("N");
		nuevo.setAutowt("N");
		nuevo.setDeferedtax("N");
		nuevo.setJournalentryList(detail);
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------
		// metodo para reagrupar cuentas del mismo codigo
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------
		JournalEntryTO journal = new JournalEntryTO();
		journal = fill_JournalEntry_Unir(nuevo);
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------

		return journal;

	}

	public JournalEntryTO fill_JournalEntry_Unir(JournalEntryTO parameters)
			throws Exception {
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		boolean ind = false;
		Double total = zero;
		Double sum_debe = 0.0;
		Double sum_credit = 0.0;
		int n = 1;
		// copiando la lista de los detalles de el asiento contable
		List list = parameters.getJournalentryList();
		// --------------------------------------------------------
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();

		// recorre la lista de detalles
		for (Object obj : list) {
			ind = false;
			JournalEntryLinesTO good = (JournalEntryLinesTO) obj;
			String cod = good.getAccount();
			List lisHija = new Vector();

			// comparando lista aux de nodos visitados
			for (Object obj2 : aux) {
				JournalEntryLinesTO good2 = (JournalEntryLinesTO) obj2;
				if (cod.equals(good2.getAccount())) {
					ind = true;
				}
			}
			// compara el codigo de cuenta para hacer una sumatoria y guardarlo
			// en otra lista
			if (ind == false) {
				for (Object obj3 : list) {
					JournalEntryLinesTO good3 = (JournalEntryLinesTO) obj3;
					if (cod.equals(good3.getAccount())) {
						lisHija.add(good3);
					}
				}
				// guarda en la lista de listas
				listas.add(lisHija);
			}

			aux.add(good);

		}

		// recorre la lista de listas para encontrar los detalles de el asiento
		// contable
		List detail = new Vector();
		for (List obj1 : listas) {
			List listaDet = obj1;
			Double sum = zero;
			String acc = null;
			String c_acc = null;
			sum_debe = zero;
			sum_credit = zero;
			for (Object obj2 : listaDet) {
				JournalEntryLinesTO oldjournal = (JournalEntryLinesTO) obj2;
				if (oldjournal.getDebit() == null) {
					oldjournal.setDebit(0.0);
				}
				if (oldjournal.getCredit() == null) {
					oldjournal.setCredit(zero);
				}
				sum_debe = sum_debe + oldjournal.getDebit();
				sum_credit = sum_credit + oldjournal.getCredit();
				acc = oldjournal.getAccount();
				c_acc = oldjournal.getContraact();
			}

			// asiento contable

			JournalEntryLinesTO art1 = new JournalEntryLinesTO();
			// -----------------------------------------------------------------------------------
			// encontrando el saldo si es deudor o acreedor
			// -----------------------------------------------------------------------------------
			Double saldo = sum_debe - sum_credit;
			if (saldo != 0) {
				if (saldo > 0) {
					art1.setDebit(saldo);
					art1.setBalduedeb(saldo);
					art1.setBalduecred(zero);
				} else {
					saldo = saldo * -1;
					art1.setCredit(saldo);
					art1.setBalduecred(saldo);
					art1.setBalduedeb(zero);
				}
			} else {
				art1.setDebit(zero);
				art1.setBalduedeb(saldo);
				art1.setBalduecred(zero);
			}

			// --------------------------------------------------------------------------------------------------------------------------------------------------------
			// llenado del asiento contable
			// --------------------------------------------------------------------------------------------------------------------------------------------------------

			art1.setLine_id(n);
			art1.setAccount(acc);
			art1.setDuedate(parameters.getDuedate());
			art1.setShortname(acc);
			art1.setContraact(c_acc);
			art1.setLinememo("Pago de Colecturia");
			art1.setRefdate(parameters.getDuedate());
			art1.setRef1(parameters.getRef1());
			// art1.setRef2();
			art1.setBaseref(parameters.getRef1());
			art1.setTaxdate(parameters.getTaxdate());
			// art1.setFinncpriod(finncpriod);
			art1.setReltransid(-1);
			art1.setRellineid(-1);
			art1.setReltype("N");
			art1.setObjtype("5");
			art1.setVatline("N");
			art1.setVatamount(zero);
			art1.setClosed("N");
			art1.setGrossvalue(zero);
			art1.setIsnet("Y");
			art1.setTaxtype(0);
			art1.setTaxpostacc("N");
			art1.setTotalvat(0.0);
			art1.setWtliable("N");
			art1.setWtline("N");
			art1.setPayblock("N");
			art1.setOrdered("N");
			art1.setTranstype(parameters.getTranstype());
			detail.add(art1);
			n++;

		}
		nuevo.setBtfstatus("O");
		nuevo.setTranstype(parameters.getTranstype());
		nuevo.setBaseref(parameters.getBaseref());
		nuevo.setRefdate(parameters.getRefdate());
		nuevo.setMemo(parameters.getMemo());
		nuevo.setRef1(parameters.getRef1());
		nuevo.setRef2(parameters.getRef2());
		nuevo.setLoctotal(parameters.getLoctotal());
		nuevo.setSystotal(parameters.getSystotal());
		nuevo.setTransrate(zero);
		nuevo.setDuedate(parameters.getDuedate());
		nuevo.setTaxdate(parameters.getTaxdate());
		nuevo.setFinncpriod(0);
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setRefndrprt("N");
		nuevo.setObjtype("5");
		nuevo.setAdjtran("N");
		nuevo.setAutostorno("N");
		nuevo.setSeries(0);
		nuevo.setAutovat("N");

		nuevo.setDocseries(0);
		nuevo.setPrinted("N");
		nuevo.setAutowt("N");
		nuevo.setDeferedtax("N");
		nuevo.setJournalentryList(detail);

		return nuevo;

	}

	public JournalEntryTO fill_JournalEntry_anular(ColecturiaTO parameters)
			throws Exception {
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		parameterTO cuentas = new parameterTO();
		String Objtype = "";
		boolean ind = false;
		Double total = zero;
		int n = 1;
		int count = 1;
		double total_sum = zero;
		List list_param = parameters.getColecturiaDetail();
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();
		AdminDAO admin = new AdminDAO();
		// consulta para encontrar todas las cuentas asignadas para el asiento
		// contable
		// cuenta de pasivo administrativo
		BusinesspartnerDAO bDAO = new BusinesspartnerDAO();

		aux = bDAO.get_businesspartnerAcount(parameters.getCardcode());

		ParameterDAO DAO = new ParameterDAO();
		cuentas = DAO.getParameterbykey(10);

		// recorre la lista de listas para encontrar los detalles de el asiento
		// contable
		List detail = new Vector();

		for (Object obj2 : list_param) {

			ColecturiaDetailTO colecturia_c = (ColecturiaDetailTO) obj2;
			String linememo = " ";
			Double sum = zero;
			String acc = null;
			String pasivo = null;
			double ingreso;
			String caja;
			String business = null;
			for (Object obj : aux) {
				BusinesspartnerAcountTO bus = (BusinesspartnerAcountTO) obj;
				// pendiente de confirmar si
				if (bus.getAcctype() == colecturia_c.getLinenum()) {
					business = bus.getAcctcode();
				}
			}

			sum = colecturia_c.getPaidsum();
			total_sum = total_sum + sum;
			acc = colecturia_c.getAcctcode();
			linememo = colecturia_c.getDscription();

			// llenado de las cuentas por concepto de colecturia segun la cuenta
			// que tienen asignadas

			// codigo de cuenta del socio de negocio
			JournalEntryLinesTO art1 = new JournalEntryLinesTO();
			art1.setLine_id(n);
			art1.setAccount(business);
			// art1.setCredit(sum);
			art1.setDebit(sum);
			art1.setDuedate(parameters.getDocduedate());
			art1.setShortname(business);
			art1.setContraact(colecturia_c.getAcctcode());
			art1.setLinememo("pago de colecturia");
			art1.setRefdate(parameters.getDocduedate());
			art1.setRef1(parameters.getRef1());
			// art1.setRef2();
			art1.setBaseref(parameters.getRef1());
			art1.setTaxdate(parameters.getDocduedate());
			// art1.setFinncpriod(finncpriod);
			art1.setReltransid(-1);
			art1.setRellineid(-1);
			art1.setReltype("N");
			art1.setObjtype("5");
			art1.setVatline("N");
			art1.setVatamount(0.0);
			art1.setClosed("N");
			art1.setGrossvalue(0.0);
			art1.setBalduedeb(art1.getCredit());
			art1.setBalduecred(0.0);
			art1.setIsnet("Y");
			art1.setTaxtype(0);
			art1.setTaxpostacc("N");
			art1.setTotalvat(0.0);
			art1.setWtliable("N");
			art1.setWtline("N");
			art1.setPayblock("N");
			art1.setOrdered("N");
			art1.setTranstype(colecturia_c.getObjtype());
			detail.add(art1);

			n++;

			// consulta para encontrar la cuenta asignada para el IVA tomando
			// como
			// venta iva debito
			admin = new AdminDAO();

			CatalogTO Catalog = new CatalogTO();
			Catalog = admin.findCatalogByKey("IVA", 10);
			if (Catalog.getCatvalue2() == null) {
				throw new Exception("No tiene cuenta asignada para impuestos");
			}
			String iva_c = Catalog.getCatvalue2();

			JournalEntryLinesTO art2 = new JournalEntryLinesTO();

			// ----------------------------------------------------------------------------------------------------------------------------------
			// cuenta de efectivo y caja
			// ----------------------------------------------------------------------------------------------------------------------------------

			art2.setLine_id(n);
			art2.setAccount(colecturia_c.getAcctcode());
			// art2.setDebit(sum);
			art2.setCredit(sum);
			art2.setDuedate(parameters.getDocduedate());
			art2.setShortname(colecturia_c.getAcctcode());
			art2.setContraact(business);
			art2.setLinememo("pago de colecturia ");
			art2.setRefdate(parameters.getDocduedate());
			art2.setRef1(parameters.getRef1());
			// art2.setRef2();
			art2.setBaseref(parameters.getRef1());
			art2.setTaxdate(parameters.getDocduedate());
			// art1.setFinncpriod(finncpriod);
			art2.setReltransid(-1);
			art2.setRellineid(-1);
			art2.setReltype("N");
			art2.setObjtype("5");
			art2.setVatline("N");
			art2.setVatamount(zero);
			art2.setClosed("N");
			art2.setGrossvalue(zero);
			art2.setBalduedeb(zero);
			art2.setBalduecred(sum);
			art2.setIsnet("Y");
			art2.setTaxtype(0);
			art2.setTaxpostacc("N");
			art2.setTotalvat(zero);
			art2.setWtliable("N");
			art2.setWtline("N");
			art2.setPayblock("N");
			art2.setOrdered("N");
			art2.setTranstype(colecturia_c.getObjtype());
			n = n + 1;
			//
			detail.add(art2);

			// falta condicion de cuando llevar
			if (colecturia_c.getAditional_account().equals("Y")) {

				// ----------------------------------------------------------------------------------------------------------------------------------
				// calculo del iva y del ingreso administrativo segundo asiento
				// contable
				// ----------------------------------------------------------------------------------------------------------------------------------

				JournalEntryLinesTO art3 = new JournalEntryLinesTO();
				// ----------------------------------------------------------------------------------------------------------------------------------
				// pasivo administrativo
				// ----------------------------------------------------------------------------------------------------------------------------------
				art3.setLine_id(n);
				art3.setAccount(colecturia_c.getAcctcode2());
				// art3.setDebit(sum);
				art3.setCredit(sum);
				art3.setDuedate(parameters.getDocduedate());
				art3.setShortname(colecturia_c.getAcctcode2());
				art3.setContraact(colecturia_c.getAcctcode3() + "-" + iva_c);
				art3.setLinememo("pago de colecturia");
				art3.setRefdate(parameters.getDocduedate());
				art3.setRef1(parameters.getRef1());
				// art2.setRef2();
				art3.setBaseref(parameters.getRef1());
				art3.setTaxdate(parameters.getDocduedate());
				// art1.setFinncpriod(finncpriod);
				art3.setReltransid(-1);
				art3.setRellineid(-1);
				art3.setReltype("N");
				art3.setObjtype("5");
				art3.setVatline("N");
				art3.setVatamount(zero);
				art3.setClosed("N");
				art3.setGrossvalue(zero);
				art3.setBalduedeb(zero);
				art3.setBalduecred(sum);
				art3.setIsnet("Y");
				art3.setTaxtype(0);
				art3.setTaxpostacc("N");
				art3.setTotalvat(zero);
				art3.setWtliable("N");
				art3.setWtline("N");
				art3.setPayblock("N");
				art3.setOrdered("N");
				// at2.setTranstype(parameters.getObjtype());
				n = n + 1;
				detail.add(art3);

				if (colecturia_c.getTaxstatus().equals("Y")) {
					// ----------------------------------------------------------------------------------------------------------------------------------
					// iva debito fiscal
					// ----------------------------------------------------------------------------------------------------------------------------------

					Double iva = Double.parseDouble(Catalog.getCatvalue()) / 100;
					ingreso = sum / (1 + iva);
					Double t_iva = ingreso * iva;

					JournalEntryLinesTO art4 = new JournalEntryLinesTO();

					art4.setLine_id(n);
					art4.setAccount(iva_c);

					// art4.setCredit(0.0);
					art4.setDebit(t_iva);
					art4.setDuedate(parameters.getDocduedate());
					art4.setShortname(iva_c);
					art4.setContraact(colecturia_c.getAcctcode2());
					art4.setLinememo("pago de colecturia");
					art4.setRefdate(parameters.getDocduedate());
					art4.setRef1(parameters.getRef1());
					// rt2.setRef2();
					art4.setBaseref(parameters.getRef1());
					art4.setTaxdate(parameters.getDocduedate());
					// art1.setFinncpriod(finncpriod);
					art4.setReltransid(-1);
					art4.setRellineid(-1);
					art4.setReltype("N");
					art4.setObjtype("5");
					art4.setVatline("N");
					art4.setVatamount(zero);
					art4.setClosed("N");
					art4.setGrossvalue(zero);
					art4.setBalduedeb(t_iva);
					art4.setBalduecred(zero);
					art4.setIsnet("Y");
					art4.setTaxtype(0);
					art4.setTaxpostacc("N");
					art4.setTotalvat(zero);
					art4.setWtliable("N");
					art4.setWtline("N");
					art4.setPayblock("N");
					art4.setOrdered("N");
					// t2.setTranstype(parameters.getObjtype());
					n = n + 1;
					detail.add(art4);
				} else {

					ingreso = sum;
				}
				// ----------------------------------------------------------------------------------------------------------------------------------
				// ingreso administrativo
				// ----------------------------------------------------------------------------------------------------------------------------------
				JournalEntryLinesTO art5 = new JournalEntryLinesTO();

				art5.setLine_id(n);
				art5.setAccount(colecturia_c.getAcctcode3());
				// art5.setCredit(ingreso);
				art5.setDebit(ingreso);
				art5.setDuedate(parameters.getDocduedate());
				art5.setShortname(colecturia_c.getAcctcode3());
				art5.setContraact(colecturia_c.getAcctcode2());
				art5.setLinememo("pago de colecturia");
				art5.setRefdate(parameters.getDocduedate());
				art5.setRef1(parameters.getRef1());
				// art2.setRef2();
				art5.setBaseref(parameters.getRef1());
				art5.setTaxdate(parameters.getDocduedate());
				// art1.setFinncpriod(finncpriod);
				art5.setReltransid(-1);
				art5.setRellineid(-1);
				art5.setReltype("N");
				art5.setObjtype("5");
				art5.setVatline("N");
				art5.setVatamount(zero);
				art5.setClosed("N");
				art5.setGrossvalue(zero);
				art5.setBalduedeb(ingreso);
				art5.setBalduecred(0.0);
				art5.setIsnet("Y");
				art5.setTaxtype(0);
				art5.setTaxpostacc("N");
				art5.setTotalvat(zero);
				art5.setWtliable("N");
				art5.setWtline("N");
				art5.setPayblock("N");
				art5.setOrdered("N");
				// 5.setTranstype(parameters.getObjtype());
				detail.add(art5);
				n++;

			}

		}
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// llenado del asiento contable
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// LLenado del padre

		nuevo.setBtfstatus("O");
		nuevo.setTranstype(Objtype);
		nuevo.setBaseref(Integer.toString(parameters.getDocnum()));
		nuevo.setRefdate(parameters.getDocdate());
		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setRef1(Integer.toString(parameters.getDocnum()));
		nuevo.setRef2(parameters.getRef1());
		nuevo.setLoctotal(total_sum);
		nuevo.setSystotal(total_sum);
		nuevo.setTransrate(zero);
		nuevo.setDuedate(parameters.getDocduedate());
		nuevo.setTaxdate(parameters.getDocdate());
		nuevo.setFinncpriod(0);
		nuevo.setUsersign(parameters.getUsersign());
		nuevo.setRefndrprt("N");
		nuevo.setObjtype("5");
		nuevo.setAdjtran("N");
		nuevo.setAutostorno("N");
		nuevo.setSeries(0);
		nuevo.setAutovat("N");
		nuevo.setDocseries(0);
		nuevo.setPrinted("N");
		nuevo.setAutowt("N");
		nuevo.setDeferedtax("N");
		nuevo.setJournalentryList(detail);
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------
		// metodo para reagrupar cuentas del mismo codigo
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------
		JournalEntryTO journal = new JournalEntryTO();
		journal = fill_JournalEntry_Unir(nuevo);
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------

		return journal;

	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------
	//
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------
	public List filtro_consulta(List sales) throws EJBException {
		List lista = new Vector();
		if (sales != null) {
			Iterator<SalesTO> iterator = sales.iterator();
			while (iterator.hasNext()) {
				SalesTO sale = (SalesTO) iterator.next();
				if (sale.getSeries() != 0 && sale.getSeries() == 3
						&& !sale.getCanceled().equals("Y")
						&& !sale.getDocstatus().equals("C")) {
					lista.add(sale);
				}

			}
		} else {
			lista = null;
		}
		return lista;

	}

	public ResultOutTO actualizar_sale(List sales, Connection conn,
			ColecturiaTO parameter) throws EJBException {
		List lista = new Vector();
		ResultOutTO _return = new ResultOutTO();
		SalesEJB ejb = new SalesEJB();
		Iterator<SalesTO> iterator = sales.iterator();
		while (iterator.hasNext()) {
			SalesTO sale = (SalesTO) iterator.next();
			sale.setDocstatus("C");
			sale.setPaidtodate(parameter.getTaxdate());
			sale.setReceiptnum(parameter.getDocentry());
			sale.setPaidsum(sale.getDoctotal());
			try {
				_return = ejb.inv_Sales_update(sale, Common.MTTOUPDATE, conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return _return;

	}

	// revertir pago de colecturia
	public ResultOutTO actualizar_sale2(Connection conn, ColecturiaTO parameter)
			throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		SalesEJB EJB = new SalesEJB();
		List sales = new Vector();
		SalesInTO nuevo = new SalesInTO();
		nuevo.setReceiptnum(parameter.getReceiptnum());
		try {
			sales = EJB.getSales(nuevo);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (sales != null) {
			List lista = new Vector();

			SalesEJB ejb = new SalesEJB();
			Iterator<SalesTO> iterator = sales.iterator();
			while (iterator.hasNext()) {
				SalesTO sale = (SalesTO) iterator.next();
				sale.setDocstatus("O");
				sale.setPaidtodate(null);
				sale.setReceiptnum(0);
				sale.setPaidsum(zero);
				try {
					_return = ejb.inv_Sales_update(sale, Common.MTTOUPDATE,
							conn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return _return;
	}

}

package com.sifcoapp.bussinessLogic;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

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
import com.sifcoapp.objects.catalog.to.BusinesspartnerInTO;
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.inventory.to.GoodsReceiptDetailTO;
import com.sifcoapp.objects.inventory.to.GoodsreceiptTO;
import com.sifcoapp.objects.purchase.dao.PurchaseDetailDAO;
import com.sifcoapp.objects.purchase.to.PurchaseDetailTO;
import com.sifcoapp.objects.sales.to.SalesDetailTO;
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

		return _return;
	}

	public CheckForPaymentTO get_cfp0_checkforpaymentByKey(int parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		CheckForPaymentTO _return = new CheckForPaymentTO();
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

		if (parameters.getDoctotal() == null) {
			parameters.setDoctotal(zero);
		}

		try {
			_return.setDocentry(DAO.ges_ges_col0_colecturia_mtto(parameters,
					action));
			// Acciones con los hijos
			Iterator<ColecturiaDetailTO> iterator = parameters
					.getColecturiaDetail().iterator();
			while (iterator.hasNext()) {
				ColecturiaDetailTO detail = (ColecturiaDetailTO) iterator
						.next();

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
			}
			journal = fill_JournalEntry(parameters);
			AccountingEJB account = new AccountingEJB();
			res_jour = account.journalEntry_mtto(journal, Common.MTTOINSERT, DAO.getConn());

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

	public ResultOutTO ges_ges_col2_colecturiaConcepts_mtto(
			ColecturiaConceptTO parameters, int action) throws EJBException {
		ResultOutTO _return = new ResultOutTO();
		ColecturiaConceptDAO DAO = new ColecturiaConceptDAO();
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

	public JournalEntryTO fill_JournalEntry(ColecturiaTO parameters)
			throws Exception {
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		parameterTO cuentas = new parameterTO();
		String Objtype="";
		boolean ind = false;
		Double total = zero;
		int n = 1;
		List list = parameters.getColecturiaDetail();
		List aux = new Vector();
		List<List> listas = new Vector();
		List aux1 = new Vector();
		// consulta para encontrar todas las cuentas asignadas para el asiento
		// contable
		// cuenta de pasivo administrativo
		ParameterDAO DAO = new ParameterDAO();
		cuentas = DAO.getParameterbykey(7);

		// recorre la lista de detalles

		for (Object obj : list) {
			ColecturiaDetailTO good = (ColecturiaDetailTO) obj;
			String cod = good.getCtlaccount();
			List lisHija = new Vector();
			Objtype=good.getObjtype();
			// compara el codigo de cuenta para hacer una sumatoria y guardarlo
			// en otra lista
			
			
			if(!cod.equals(null)){
			
				for (Object obj2 : aux) {
				ColecturiaDetailTO good2 = (ColecturiaDetailTO) obj2;
				if (cod.equals(good2.getCtlaccount())) {
					ind = true;
				}
			}
			
			if (ind == false) {
				for (Object obj3 : list) {
					ColecturiaDetailTO good3 = (ColecturiaDetailTO) obj3;
					if (cod.equals(good3.getCtlaccount())) {
						lisHija.add(good3);
					}
				}
				// guarda en la lista de listas
				listas.add(lisHija);
			}
			// agrega cada nodo visitado
			aux.add(good);
			}
		}
		// recorre la lista de listas para encontrar los detalles de el asiento
		// contable
		List detail = new Vector();
		for (List obj1 : listas) {
			List listaDet = obj1;
			String linememo = " ";
			Double sum = zero;
			String acc = null;
			for (Object obj2 : listaDet) {
				ColecturiaDetailTO newGood = (ColecturiaDetailTO) obj2;
				sum = sum + Double.parseDouble(newGood.getValue2());
				acc = newGood.getAcctcode();
				linememo = newGood.getDscription();
			}
			// sumatoria de todos los conceptos de colecturia para abonar en la
			// contracuenta
			total = total + sum;

			// llenado de las cuentas por concepto de colecturia segun la cuenta
			// q llevan
			JournalEntryLinesTO art1 = new JournalEntryLinesTO();
			art1.setLine_id(n);
			art1.setAccount(acc);
			// art1.setDebit(sum);
			art1.setCredit(sum);
			art1.setDuedate(parameters.getDocduedate());
			art1.setShortname(acc);
			art1.setContraact("");
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
			art1.setBalduecred(sum);
			art1.setIsnet("Y");
			art1.setTaxtype(0);
			art1.setTaxpostacc("N");
			art1.setTotalvat(0.0);
			art1.setWtliable("N");
			art1.setWtline("N");
			art1.setPayblock("N");
			art1.setOrdered("N");
			art1.setTranstype(Objtype);
			detail.add(art1);

			n++;
		}

		// consulta para encontrar la cuenta asignada para el IVA tomando como
		// venta iva debito

		AdminDAO admin = new AdminDAO();
		CatalogTO Catalog = new CatalogTO();
		Catalog = admin.findCatalogByKey("IVA", 12);
		if (Catalog.getCatvalue2() == null) {
			throw new Exception("No tiene cuenta asignada para impuestos");
		}
		String iva_c = Catalog.getCatvalue2();

		JournalEntryLinesTO art2 = new JournalEntryLinesTO();
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
		// nuevo.setLoctotal(sum);
		// nuevo.setSystotal(sum);
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
		// ----------------------------------------------------------------------------------------------------------------------------------
		// cuenta pasivo administrativo
		// ----------------------------------------------------------------------------------------------------------------------------------
	     n=n+1;	
		art2.setLine_id(n);
		art2.setAccount(cuentas.getValue1());
		art2.setDebit(total);
		art2.setDuedate(parameters.getDocduedate());
		art2.setShortname(cuentas.getValue1());
		// art2.setContraact(branch.getBalinvntac());
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
		art2.setBalduedeb(0.0);
		// art2.setBalduecred(sum);
		art2.setIsnet("Y");
		art2.setTaxtype(0);
		art2.setTaxpostacc("N");
		art2.setTotalvat(0.0);
		art2.setWtliable("N");
		art2.setWtline("N");
		art2.setPayblock("N");
		art2.setOrdered("N");
		n = n + 1;
		// art2.setTranstype(parameters.getObjtype());
		detail.add(art2);

		// ----------------------------------------------------------------------------------------------------------------------------------
		// calculo del iva y del ingreso administrativo
		// ----------------------------------------------------------------------------------------------------------------------------------
		Double iva = Double.parseDouble(Catalog.getCatvalue()) / 100;
		Double ingreso = total / (1 + iva);
		Double t_iva = ingreso * iva;
		JournalEntryLinesTO art3 = new JournalEntryLinesTO();
		// ----------------------------------------------------------------------------------------------------------------------------------
		// ingreso administrativo
		// ----------------------------------------------------------------------------------------------------------------------------------
		art3.setLine_id(n);
		art3.setAccount(cuentas.getValue1());

		art3.setCredit(ingreso);
		art3.setDuedate(parameters.getDocduedate());
		art3.setShortname(cuentas.getValue1());
		// art2.setContraact(branch.getBalinvntac());
		art3.setLinememo("entrada de mercancias");
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
		art3.setBalduedeb(0.0);
		art3.setBalduecred(ingreso);
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
		// ----------------------------------------------------------------------------------------------------------------------------------
		// iva debito fiscal
		// ----------------------------------------------------------------------------------------------------------------------------------
		JournalEntryLinesTO art4 = new JournalEntryLinesTO();

		art4.setLine_id(n);
		art4.setAccount(iva_c);

		art4.setCredit(t_iva);

		art4.setDuedate(parameters.getDocduedate());
		art4.setShortname(iva_c);
		// art2.setContraact(branch.getBalinvntac());
		art4.setLinememo("entrada de mercancias");
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
		// ----------------------------------------------------------------------------------------------------------------------------------
		// iva debito fiscal
		// ----------------------------------------------------------------------------------------------------------------------------------
		JournalEntryLinesTO art5 = new JournalEntryLinesTO();

		art5.setLine_id(n);
		art5.setAccount(cuentas.getValue3());
        art5.setDebit(total);
		art5.setDuedate(parameters.getDocduedate());
		art5.setShortname(cuentas.getValue3());
		// art2.setContraact(branch.getBalinvntac());
		art5.setLinememo("entrada de mercancias");
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
		art5.setBalduedeb(total);
		art5.setBalduecred(0.0);
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

		nuevo.setJournalentryList(detail);
		return nuevo;

	}

}

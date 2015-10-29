package com.sifcoapp.bussinessLogic;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.admin.ejb.ParameterEJB;
import com.sifcoapp.objects.accounting.dao.AccountingDAO;
import com.sifcoapp.objects.accounting.dao.JournalEntryLinesDAO;
import com.sifcoapp.objects.accounting.to.AccountTO;
import com.sifcoapp.objects.accounting.to.JournalEntryLinesTO;
import com.sifcoapp.objects.accounting.to.JournalEntryTO;
import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.admin.dao.ParameterDAO;
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
import com.sifcoapp.objects.catalog.to.BusinesspartnerTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
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
			if (action == Common.MTTOINSERT) {
				parameters.setObjtype("40");
				_return.setDocentry(DAO.ges_cfp0_checkforpayment_mtto(
						parameters, action));
				parameters.setCheckkey(_return.getDocentry());
				// llenado del asiento contable del chekforapayment

				AccountingEJB acounting = new AccountingEJB();

				JournalEntryTO journal = new JournalEntryTO();
				journal = journal_CheckForPayment(parameters);
				ResultOutTO resultado = acounting.journalEntry_mtto(journal,
						Common.MTTOINSERT, DAO.getConn());

				// Actualizando el documento de Contrl de cheques emitidos con
				// el transid del journalEntry
				parameters.setTransref(resultado.getDocentry() + "");
				int result = DAO.ges_cfp0_checkforpayment_mtto(parameters,
						Common.MTTOUPDATE);

			}
			if (action == Common.MTTOUPDATE) {
				_return.setDocentry(DAO.ges_cfp0_checkforpayment_mtto(
						parameters, Common.MTTOUPDATE));
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
		// ---------------------------------------------------------------------------
		// conexion principal
		// ---------------------------------------------------------------------------
		ColecturiaDAO DAO = new ColecturiaDAO();
		DAO.setIstransaccional(true);
		// ---------------------------------------------------------------------------
		// ---------------------------------------------------------------------------
		ColecturiaDetailDAO DAO1 = new ColecturiaDetailDAO(DAO.getConn());
		DAO1.setIstransaccional(true);
		List sale = new Vector();
		parameterTO parameter = new parameterTO();

		ParameterEJB ejb = new ParameterEJB();
		parameter = ejb.getParameterbykey(9);

		List aux = new Vector();
		// aqui la validacion de abono a capital si no se ha pagado por completo
		// el interes
		

		if (parameters.getDoctotal() == null) {
			parameters.setDoctotal(zero);
		}

		try {
			
			_return=validateColecturia(parameters);
			if(_return.getCodigoError()!=0){
				_return.setCodigoError(1);
				
			}else{
			
			
			if (action == Common.MTTOINSERT) {

				_return.setDocentry(DAO.ges_ges_col0_colecturia_mtto(
						parameters, action));
				parameters.setDocentry(_return.getDocentry());
				// Acciones con los hijos
				Iterator<ColecturiaDetailTO> iterator = parameters
						.getColecturiaDetail().iterator();
				while (iterator.hasNext()) {
					ColecturiaDetailTO detail = (ColecturiaDetailTO) iterator
							.next();

					if (detail.getPaidsum() != 0.0) {

						if (detail.getPaidsum() == null) {
							detail.setPaidsum(zero);
						}
						if (detail.getVatsum() == null) {
							detail.setVatsum(zero);
						}

						detail.setObjtype("41");
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
						aux.add(detail);
					}
				}

				parameters.setColecturiaDetail(aux);
				// ---------------------------------------------------------------------------
				// Asiento de coelcturia Series = 2
				// ---------------------------------------------------------------------------
				if (parameters.getSeries() == 1) {

					journal = fill_JournalEntry(parameters);
					AccountingEJB account = new AccountingEJB();
					res_jour = account.journalEntry_mtto(journal,
							Common.MTTOINSERT, DAO.getConn());
					ResultOutTO nuevo = new ResultOutTO();
					if (sale.size() != 0) {
						nuevo = actualizar_sale(sale, DAO.getConn(), parameters);
					}
					// Generar asiento de liquidación para unidades propias
					if (parameters.getPrinted().equals("1")) {
						journal = fill_JournalEntry_liquidacion(parameters);
						account = new AccountingEJB();
						res_jour = account.journalEntry_mtto(journal,
								Common.MTTOINSERT, DAO.getConn());

					}
				}
				// ---------------------------------------------------------------------------
				// Asiento de reversión Series = 2
				// ---------------------------------------------------------------------------
				if (parameters.getSeries() == 2) {

					journal = fill_JournalEntry_anular(parameters);
					AccountingEJB account1 = new AccountingEJB();
					res_jour = account1.journalEntry_mtto(journal,
							Common.MTTOINSERT, DAO.getConn());

					ColecturiaTO colecturia = new ColecturiaTO();

					colecturia = get_ges_colecturiaByKey(parameters
							.getReceiptnum());
					// cambia estado a 2 indicando que el pago de colecturia ha
					// sido anulado
					colecturia.setTranstype(2);
					// actualizando el pago de colecturia anterior
					DAO.ges_ges_col0_colecturia_mtto(colecturia,
							Common.MTTOUPDATE);
					// actualiza el campo de pago de facturas de cancelado a no
					// cancelado
					ResultOutTO nuevo1 = new ResultOutTO();
					nuevo1 = actualizar_sale2(DAO.getConn(), parameters);

					if (parameters.getPrinted().equals("1")) {
						journal = fill_JournalEntry_liquidacion_anulacion(parameters);
						account1 = new AccountingEJB();
						res_jour = account1.journalEntry_mtto(journal,
								Common.MTTOINSERT, DAO.getConn());

					}
				}

			} else {
				DAO.ges_ges_col0_colecturia_mtto(parameters, Common.MTTOUPDATE);
			}
			
				_return.setCodigoError(0);
				_return.setMensaje("Datos ingresados con exito");
			
			
			}// fin del if de la validacion 
				DAO.forceCommit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}
		
		return _return;
	}

	public ResultOutTO validateColecturia(ColecturiaTO parameter) {
		ResultOutTO result = new ResultOutTO();
		// asingnacion de variables;
		ParameterDAO DAO = new ParameterDAO();
		parameterTO prestamo = new parameterTO();
		parameterTO interes = new parameterTO();

		try {
			// validacion si el cierre de caja del dia anterior ya se realizo
			/*result = validateCloseDiary_colecturia(parameter.getUsersign());
			if (result.getCodigoError() != 0) {
				result.setCodigoError(1);
				result.setMensaje("no se ha realizado el cierre de caja de el dia anterior");
				return result;
			}*/
			// validacion de prestamos
			prestamo = DAO.getParameterbykey(11);

			DAO = new ParameterDAO();
			interes = DAO.getParameterbykey(12);

			List list = new Vector();
			list = parameter.getColecturiaDetail();
			for (Object object : list) {

				ColecturiaDetailTO colecturia = (ColecturiaDetailTO) object;

				// consulta el concepto de prestamos
				if (prestamo.getValue1().equals(Integer.toString(colecturia.getLinenum()))) {

					// cosulta si existe un monto a pagar en el concepto de
					// prestamo
					if (colecturia.getPaidsum() > 0.0) {
						// si el pago de prestamos > 0.0
						for (Object object2 : list) {

							ColecturiaDetailTO colecturia2 = (ColecturiaDetailTO) object2;
							if (interes.getValue1().equals(Integer.toString(colecturia2.getLinenum()))) {
								// si el saldo del interes es igual a cero o el
								// saldo - paidsum=0
								if (Double.parseDouble(colecturia2.getValue1()) <= 0.0
										|| (Double.parseDouble(colecturia2
												.getValue1()) - colecturia2
												.getPaidsum()) == 0.0) {
									result.setMensaje("colecturia valida");
									result.setCodigoError(0);
								}else {
									result.setMensaje("Debe Pagar los intereses antes que el capital del préstamo ");
									result.setCodigoError(1);
									return result;
								}
							} 
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ResultOutTO validateCloseDiary_colecturia(int usersign)
			throws Exception {
		ResultOutTO result = new ResultOutTO();

		ColecturiaDAO DAO = new ColecturiaDAO();

		int num = DAO.getvalidatecloseColecturia(usersign);
		if (num > 0) {
			result.setCodigoError(0);
			result.setMensaje("Cierre realizado");
		} else {
			result.setCodigoError(1);
			result.setMensaje("cierre de colecturia no realizad");
		}

		return result;
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

	public ColecturiaTO get_ges_colecturiaByKey_print(int parameters)
			throws EJBException {
		// TODO Auto-generated method stub
		ColecturiaTO _return = new ColecturiaTO();
		ColecturiaDAO DAO = new ColecturiaDAO();
		try {
			_return = DAO.get_ges_colecturiaByKey_print(parameters);
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

	// metodo que devuelve los conceptos de colecturia por socios con saldos de
	// las cuentas

	public List get_ges_colecturiaConcept1(String Code) throws EJBException {
		// TODO Auto-generated method stub

		ResultOutTO resultado = new ResultOutTO();
		List _return = new Vector();
		ColecturiaConceptDAO DAO = new ColecturiaConceptDAO();
		ColecturiaConceptTO colecturia = new ColecturiaConceptTO();
		List facturas = new Vector();
		SalesInTO aux = new SalesInTO();
		aux.setCardcode(Code);
		SalesEJB sales = new SalesEJB();
		try {
			// actualizando intereses moratorios
			resultado = interes_moratorio(Code);

			// consulta para devolver facturas de diesel del socio
			facturas = sales.getSales(aux);

			// cosulta del colecturia concept con su saldo de la cuenta
			_return = DAO.get_ges_colecturiaConcept1(Code);

			// filtra las facturas
			facturas = filtro_consulta(facturas);
			if (facturas != null) {
				parameterTO parameter = new parameterTO();
				parameterTO Ap = new parameterTO();
				parameterTO Ah = new parameterTO();
				parameterTO Ut = new parameterTO();
				// para cosultar el busines partner para saber si es socio o
				// propio
				BusinesspartnerTO busines = new BusinesspartnerTO();
				BusinesspartnerDAO Bus = new BusinesspartnerDAO();
				busines = Bus.get_businesspartnerByKey(Code);

				ParameterEJB ejb = new ParameterEJB();
				parameter = ejb.getParameterbykey(9);

				double ahorro;
				double aportacion;
				double utilidad;
				double GLs = 0.0;
				double Total_sales = 0.0;
				// -------------------------------------------------------------------------------------------------------------------------------------
				// cosultando los conceptos correspondientes a ahorro,aportacion
				// y
				// utilidad de diesel
				// -------------------------------------------------------------------------------------------------------------------------------------
				Ap = ejb.getParameterbykey(23);
				Ah = ejb.getParameterbykey(24);
				Ut = ejb.getParameterbykey(25);

				for (Object object : facturas) {
					SalesTO sale = (SalesTO) object;
					SalesTO salebykey = new SalesTO();
					salebykey = sales.getSalesByKey(sale.getDocentry());

					List detalle = new Vector();
					Total_sales = Total_sales + sale.getDoctotal();

					detalle = salebykey.getSalesDetails();

					for (Object object2 : detalle) {
						SalesDetailTO detail = (SalesDetailTO) object2;
						GLs = GLs + detail.getQuantity();
					}
				}

				// -------------------------------------------------------------------------------------------------------------------------------------
				// calculando
				// -------------------------------------------------------------------------------------------------------------------------------------
				// para el ahorro
				ahorro = GLs * (Double.parseDouble(Ah.getValue2()));
				// para el aporte
				aportacion = GLs * (Double.parseDouble(Ap.getValue2()));
				// para la utilidad
				utilidad = GLs * (Double.parseDouble(Ut.getValue1()));
				// -------------------------------------------------------------------------------------------------------------------------------------

				// -------------------------------------------------------------------------------------------------------------------------------------
				Iterator<ColecturiaConceptTO> iterator = _return.iterator();

				while (iterator.hasNext()) {
					ColecturiaConceptTO concept = (ColecturiaConceptTO) iterator
							.next();
					// para ver si hay una cuenta para el asiento contable

					if (concept.getObjtype() != null
							&& !concept.getObjtype().isEmpty()) {
						concept.setAcctcode3(concept.getObjtype());
					}
					// CONCEPTO CORRESPONDIENTE A LAS FACTURAS DE DIESEL
					if (concept.getLinenum() == Integer.parseInt(parameter
							.getValue1())) {
						concept.setFacturas(facturas);
						concept.setPaidsum(Total_sales);

					}
					// ahorro diesel
					if (concept.getLinenum() == Integer
							.parseInt(Ah.getValue1())) {
						concept.setPaidsum(ahorro);
					}

					// ahorro diesel
					if (concept.getLinenum() == Integer
							.parseInt(Ap.getValue1())) {
						// si groupcode es igual a 1 entonces es unidad propia
						// sino es unidad asociado
						if (busines.getGroupcode().equals("1")) {
							aportacion = aportacion + utilidad;
							concept.setPaidsum(aportacion);
						} else {
							concept.setPaidsum(aportacion);
						}

					}

				}
			}

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
		ColecturiaConceptTO lstPeriods = new ColecturiaConceptTO();
		List lstPeriods3 = null;

		try {
			parameters.setObjtype("42");
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
		// consulta para encontrar todas las cuentas asignadas para el asiento
		// contable
		// cuenta de pasivo administrativo
		BusinesspartnerDAO bDAO = new BusinesspartnerDAO();

		aux = bDAO.get_businesspartnerAcount(parameters.getCardcode());

		// Concepto que identifica la cuota del motorista
		ParameterDAO DAO = new ParameterDAO();
		cuentas = DAO.getParameterbykey(15);

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

			// llenado de las cuentas por concepto de colecturia segun la cuenta
			// que tienen asignadas

			// codigo de cuenta del socio de negocio
			if (colecturia_c.getLinenum() != Integer.parseInt(cuentas
					.getValue1())) {

				sum = colecturia_c.getPaidsum();
				total_sum = total_sum + sum;
				acc = colecturia_c.getAcctcode();
				linememo = colecturia_c.getDscription();

				JournalEntryLinesTO art1 = new JournalEntryLinesTO();
				art1.setLine_id(n);
				art1.setAccount(business);
				art1.setCredit(sum);
				art1.setDuedate(parameters.getDocdate());
				art1.setShortname(business);
				art1.setContraact(colecturia_c.getAcctcode());
				art1.setLinememo("pago de colecturia");
				art1.setRefdate(parameters.getDocdate());
				art1.setRef1(Integer.toString(parameters.getDocentry()));
				// art1.setRef2();
				art1.setBaseref(parameters.getRef1());
				art1.setTaxdate(parameters.getDocdate());
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

				// consulta para encontrar la cuenta asignada para el IVA
				// tomando
				// como
				// venta iva debito
				AdminDAO admin = new AdminDAO();

				CatalogTO Catalog = new CatalogTO();
				Catalog = admin.findCatalogByKey("IVA", 10);
				if (Catalog.getCatvalue2() == null) {
					throw new Exception(
							"No tiene cuenta asignada para impuestos");
				}
				String iva_c = Catalog.getCatvalue2();

				JournalEntryLinesTO art2 = new JournalEntryLinesTO();

				// ----------------------------------------------------------------------------------------------------------------------------------
				// cuenta de efectivo y caja
				// ----------------------------------------------------------------------------------------------------------------------------------

				art2.setLine_id(n);
				art2.setAccount(colecturia_c.getAcctcode());
				art2.setDebit(sum);
				art2.setDuedate(parameters.getDocdate());
				art2.setShortname(colecturia_c.getAcctcode());
				art2.setContraact(business);
				art2.setLinememo("pago de colecturia ");
				art2.setRefdate(parameters.getDocdate());
				art2.setRef1(Integer.toString(parameters.getDocentry()));
				// art2.setRef2();
				art2.setBaseref(parameters.getRef1());
				art2.setTaxdate(parameters.getDocdate());
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

				// if (colec)

				// falta condicion de cuando llevar
				if (colecturia_c.getAditional_account() != null
						&& colecturia_c.getAditional_account().equals("Y")) {

					// ----------------------------------------------------------------------------------------------------------------------------------
					// calculo del iva y del ingreso administrativo segundo
					// asiento
					// contable
					// ----------------------------------------------------------------------------------------------------------------------------------

					JournalEntryLinesTO art3 = new JournalEntryLinesTO();
					// ----------------------------------------------------------------------------------------------------------------------------------
					// pasivo administrativo
					// ----------------------------------------------------------------------------------------------------------------------------------
					art3.setLine_id(n);
					art3.setAccount(colecturia_c.getAcctcode2());
					art3.setDebit(sum);
					art3.setDuedate(parameters.getDocdate());
					art3.setShortname(colecturia_c.getAcctcode2());
					art3.setContraact(colecturia_c.getAcctcode3() + "-" + iva_c);
					art3.setLinememo("pago de colecturia");
					art3.setRefdate(parameters.getDocdate());
					art3.setRef1(Integer.toString(parameters.getDocentry()));
					// art2.setRef2();
					art3.setBaseref(parameters.getRef1());
					art3.setTaxdate(parameters.getDocdate());
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

					art3.setTranstype(colecturia_c.getObjtype());
					n = n + 1;
					detail.add(art3);

					if (colecturia_c.getTaxstatus() != null
							&& colecturia_c.getTaxstatus().equals("Y")) {
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

						art4.setDuedate(parameters.getDocdate());
						art4.setShortname(iva_c);
						art4.setContraact(colecturia_c.getAcctcode2());
						art4.setLinememo("pago de colecturia");
						art4.setRefdate(parameters.getDocdate());
						art4.setRef1(parameters.getRef1());
						// rt2.setRef2();
						art4.setBaseref(parameters.getRef1());
						art4.setTaxdate(parameters.getDocdate());
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
						art4.setTranstype(colecturia_c.getObjtype());
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
					art5.setDuedate(parameters.getDocdate());
					art5.setShortname(colecturia_c.getAcctcode3());
					art5.setContraact(colecturia_c.getAcctcode2());
					art5.setLinememo("pago de colecturia");
					art5.setRefdate(parameters.getDocdate());
					art5.setRef1(Integer.toString(parameters.getDocentry()));
					// art2.setRef2();
					art5.setBaseref(parameters.getRef1());
					art5.setTaxdate(parameters.getDocdate());
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
					art5.setTranstype(colecturia_c.getObjtype());
					detail.add(art5);
					n++;

				}
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
		nuevo.setMemo("pago de conceptos de colecturia");
		nuevo.setRef1(Integer.toString(parameters.getDocnum()));
		nuevo.setRef2(parameters.getRef1());
		nuevo.setLoctotal(total_sum);
		nuevo.setSystotal(total_sum);
		nuevo.setTransrate(0.0);
		nuevo.setDuedate(parameters.getDocdate());
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
		nuevo.setTranstype("41");
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------
		// metodo para reagrupar cuentas del mismo codigo
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------
		JournalEntryTO journal = new JournalEntryTO();
		journal = fill_JournalEntry_Unir(nuevo);
		// journal=nuevo;
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
			art1.setLinememo(parameters.getMemo());
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

		// consulta para encontrar todas las cuentas asignadas para el asiento
		// contable
		// cuenta de pasivo administrativo
		BusinesspartnerDAO bDAO = new BusinesspartnerDAO();

		aux = bDAO.get_businesspartnerAcount(parameters.getCardcode());

		ParameterDAO DAO = new ParameterDAO();
		cuentas = DAO.getParameterbykey(15);

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

			// llenado de las cuentas por concepto de colecturia segun la cuenta
			// que tienen asignadas

			if (colecturia_c.getLinenum() != Integer.parseInt(cuentas
					.getValue1())) {

				sum = colecturia_c.getPaidsum();
				total_sum = total_sum + sum;
				acc = colecturia_c.getAcctcode();
				linememo = colecturia_c.getDscription();

				JournalEntryLinesTO art1 = new JournalEntryLinesTO();
				art1.setLine_id(n);
				art1.setAccount(business);
				// art1.setCredit(sum);
				art1.setDebit(sum);
				art1.setDuedate(parameters.getDocdate());
				art1.setShortname(business);
				art1.setContraact(colecturia_c.getAcctcode());
				art1.setLinememo("reversion de pago de colecturia");
				art1.setRefdate(parameters.getDocdate());
				art1.setRef1(Integer.toString(parameters.getDocentry()));
				// art1.setRef2();
				art1.setBaseref(parameters.getRef1());
				art1.setTaxdate(parameters.getTaxdate());
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

				// consulta para encontrar la cuenta asignada para el IVA
				// tomando
				// como
				// venta iva debito
				AdminDAO admin = new AdminDAO();

				CatalogTO Catalog = new CatalogTO();
				Catalog = admin.findCatalogByKey("IVA", 10);
				if (Catalog.getCatvalue2() == null) {
					throw new Exception(
							"No tiene cuenta asignada para impuestos");
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
				art2.setDuedate(parameters.getDocdate());
				art2.setShortname(colecturia_c.getAcctcode());
				art2.setContraact(business);
				art2.setLinememo("Reversion pago de colecturia ");
				art2.setRefdate(parameters.getDocdate());
				art2.setRef1(Integer.toString(parameters.getDocentry()));
				// art2.setRef2();
				art2.setBaseref(parameters.getRef1());
				art2.setTaxdate(parameters.getTaxdate());
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
					// calculo del iva y del ingreso administrativo segundo
					// asiento
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
					art3.setDuedate(parameters.getDocdate());
					art3.setShortname(colecturia_c.getAcctcode2());
					art3.setContraact(colecturia_c.getAcctcode3() + "-" + iva_c);
					art3.setLinememo("Reversion pago de colecturia");
					art3.setRefdate(parameters.getDocdate());
					art3.setRef1(Integer.toString(parameters.getDocentry()));
					// art2.setRef2();
					art3.setBaseref(parameters.getRef1());
					art3.setTaxdate(parameters.getDocdate());
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
					art3.setTranstype(colecturia_c.getObjtype());
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
						art4.setDuedate(parameters.getDocdate());
						art4.setShortname(iva_c);
						art4.setContraact(colecturia_c.getAcctcode2());
						art4.setLinememo("reversion pago de colecturia");
						art4.setRefdate(parameters.getDocdate());
						art4.setRef1(Integer.toString(parameters.getDocentry()));
						// rt2.setRef2();
						art4.setBaseref(parameters.getRef1());
						art4.setTaxdate(parameters.getTaxdate());
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
						art4.setTranstype(colecturia_c.getObjtype());
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
					art5.setDuedate(parameters.getDocdate());
					art5.setShortname(colecturia_c.getAcctcode3());
					art5.setContraact(colecturia_c.getAcctcode2());
					art5.setLinememo("Reversion  pago de colecturia");
					art5.setRefdate(parameters.getDocdate());
					art5.setRef1(Integer.toString(parameters.getDocentry()));
					// art2.setRef2();
					art5.setBaseref(parameters.getRef1());
					art5.setTaxdate(parameters.getDocdate());
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
					art5.setTranstype(colecturia_c.getObjtype());
					detail.add(art5);
					n++;

				}
			}
		}
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// llenado del asiento contable
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// LLenado del padre

		nuevo.setBtfstatus("O");
		nuevo.setTranstype("41");
		nuevo.setBaseref(Integer.toString(parameters.getDocnum()));
		nuevo.setRefdate(parameters.getDocdate());
		nuevo.setMemo(parameters.getJrnlmemo());
		nuevo.setRef1(Integer.toString(parameters.getDocnum()));
		nuevo.setRef2(parameters.getRef1());
		nuevo.setLoctotal(total_sum);
		nuevo.setSystotal(total_sum);
		nuevo.setTransrate(zero);
		nuevo.setDuedate(parameters.getDocdate());
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
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------
		// metodo para reagrupar cuentas del mismo codigo
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------
		JournalEntryTO journal = new JournalEntryTO();
		journal = fill_JournalEntry_Unir(nuevo);
		// journal=nuevo;
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------

		return journal;

	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------
	// Asiento contable para la liquidacion de unidades propias
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------
	public JournalEntryTO fill_JournalEntry_liquidacion(ColecturiaTO parameters)
			throws Exception {
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		parameterTO cuentas = new parameterTO();
		parameterTO cuota_mot = new parameterTO();
		String Objtype = "";
		boolean ind = false;
		Double total = zero;
		int n = 1;
		int count = 1;
		double total_sum = 0.0;
		List list_param = parameters.getColecturiaDetail();

		List<List> listas = new Vector();
		List aux1 = new Vector();

		// consultando numero de concepto de ingreso
		ParameterDAO DAO = new ParameterDAO();
		cuentas = DAO.getParameterbykey(14);

		// recorre la lista de listas para encontrar los detalles de el asiento
		// contable
		List detail = new Vector();
		String ingreso = null;
		double t_ingreso = 0.0;
		String objtype = null;
		Double sum = zero;

		for (Object obj2 : list_param) {

			ColecturiaDetailTO colecturia_c = (ColecturiaDetailTO) obj2;
			String linememo = " ";

			ingreso = colecturia_c.getObjtype();

			linememo = colecturia_c.getDscription();

			// llenado de las cuentas por concepto de colecturia segun la cuenta
			// que tienen asignadas

			// filtrando que no sea el concepto de ingreso
			if (colecturia_c.getLinenum() != Integer.parseInt(cuentas
					.getValue1())) {

				// filtrando solo los conceptos que tengas cuentas para
				// liquidacion de ingresos
				if (colecturia_c.getCtlaccount() != null) {
					// sumando el valor de la liquidacion para compararla con el
					// ingreso
					sum = sum + colecturia_c.getPaidsum();

					JournalEntryLinesTO art1 = new JournalEntryLinesTO();
					art1.setLine_id(n);
					art1.setAccount(colecturia_c.getCtlaccount());
					art1.setDebit(colecturia_c.getPaidsum());
					art1.setDuedate(parameters.getDocdate());
					art1.setShortname(colecturia_c.getCtlaccount());
					art1.setContraact("");
					art1.setLinememo("liquidacion de unidades propias ");
					art1.setRefdate(parameters.getDocdate());
					art1.setRef1(Integer.toString(parameters.getDocentry()));
					// art1.setRef2();
					art1.setBaseref(parameters.getRef1());
					art1.setTaxdate(parameters.getTaxdate());
					// art1.setFinncpriod(finncpriod);
					art1.setReltransid(-1);
					art1.setRellineid(-1);
					art1.setReltype("N");
					art1.setObjtype("5");
					art1.setVatline("N");
					art1.setVatamount(0.0);
					art1.setClosed("N");
					art1.setGrossvalue(0.0);
					art1.setBalduedeb(art1.getDebit());
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
				}
			} else {
				ingreso = colecturia_c.getAcctcode();

			}
		}
		// ----------------------------------------------------------------------------------------------------------------------------------
		// consulta del concepto de ingreso
		// ----------------------------------------------------------------------------------------------------------------------------------
		List conceptos = new Vector();
		conceptos = get_ges_colecturiaConcept1(parameters.getCardcode());

		for (Object object : conceptos) {
			ColecturiaConceptTO concepto = (ColecturiaConceptTO) object;
			if (concepto.getLinenum() == Integer.parseInt(cuentas.getValue1())) {
				ingreso = concepto.getOcrcode();
			}

		}

		if (ingreso.equals(null)) {
			throw new Exception(
					"no existe cuenta asiganada a ingresos para la liquidacion");
		}

		JournalEntryLinesTO art2 = new JournalEntryLinesTO();
		art2.setLine_id(n);
		art2.setAccount(ingreso);
		art2.setCredit(sum);
		art2.setDuedate(parameters.getDocdate());
		art2.setShortname(ingreso);
		art2.setContraact("");
		art2.setLinememo("liquidacion de unidades propias ");
		art2.setRefdate(parameters.getDocdate());
		art2.setRef1(Integer.toString(parameters.getDocentry()));
		// art2.setRef2();
		art2.setBaseref(parameters.getRef1());
		art2.setTaxdate(parameters.getTaxdate());
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
		art2.setBalduecred(sum);
		art2.setIsnet("Y");
		art2.setTaxtype(0);
		art2.setTaxpostacc("N");
		art2.setTotalvat(0.0);
		art2.setWtliable("N");
		art2.setWtline("N");
		art2.setPayblock("N");
		art2.setOrdered("N");
		art2.setTranstype("41");

		//
		detail.add(art2);

		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// llenado del asiento contable
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// LLenado del padre

		nuevo.setBtfstatus("O");
		nuevo.setTranstype(objtype);
		nuevo.setBaseref(Integer.toString(parameters.getDocnum()));
		nuevo.setRefdate(parameters.getDocdate());
		nuevo.setMemo("liquidacion de unidades propias ");
		nuevo.setRef1(Integer.toString(parameters.getDocnum()));
		nuevo.setRef2(parameters.getRef1());
		nuevo.setLoctotal(sum);
		nuevo.setSystotal(sum);
		nuevo.setTransrate(0.0);
		nuevo.setDuedate(parameters.getDocdate());
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
		nuevo.setTranstype("41");
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------
		// metodo para reagrupar cuentas del mismo codigo
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------
		JournalEntryTO journal = new JournalEntryTO();
		journal = fill_JournalEntry_Unir(nuevo);
		// journal=nuevo;
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------

		return journal;

	}

	public JournalEntryTO fill_JournalEntry_liquidacion_anulacion(
			ColecturiaTO parameters) throws Exception {
		JournalEntryTO nuevo = new JournalEntryTO();
		ResultOutTO _result = new ResultOutTO();
		parameterTO cuentas = new parameterTO();
		parameterTO cuota_mot = new parameterTO();
		String Objtype = "";
		boolean ind = false;
		Double total = zero;
		int n = 1;
		int count = 1;
		double total_sum = 0.0;
		List list_param = parameters.getColecturiaDetail();

		List<List> listas = new Vector();
		List aux1 = new Vector();

		// consultando numero de concepto de ingreso
		ParameterDAO DAO = new ParameterDAO();
		cuentas = DAO.getParameterbykey(14);
		DAO = new ParameterDAO();
		cuentas = DAO.getParameterbykey(14);
		// recorre la lista de listas para encontrar los detalles de el asiento
		// contable
		List detail = new Vector();
		String ingreso = null;
		double t_ingreso = 0.0;
		String objtype = null;
		Double sum = zero;

		for (Object obj2 : list_param) {

			ColecturiaDetailTO colecturia_c = (ColecturiaDetailTO) obj2;
			String linememo = " ";

			ingreso = colecturia_c.getObjtype();

			linememo = colecturia_c.getDscription();

			// llenado de las cuentas por concepto de colecturia segun la cuenta
			// que tienen asignadas

			// filtrando que no sea el concepto de ingreso
			if (colecturia_c.getLinenum() != Integer.parseInt(cuentas
					.getValue1())) {

				// filtrando solo los conceptos que tengas cuentas para
				// liquidacion de ingresos
				if (colecturia_c.getCtlaccount() != null) {
					// sumando el valor de la liquidacion para compararla con el
					// ingreso
					sum = sum + colecturia_c.getPaidsum();

					JournalEntryLinesTO art1 = new JournalEntryLinesTO();
					art1.setLine_id(n);
					art1.setAccount(colecturia_c.getCtlaccount());
					// art1.setDebit(colecturia_c.getPaidsum());
					art1.setCredit(colecturia_c.getPaidsum());
					art1.setDuedate(parameters.getDocdate());
					art1.setShortname(colecturia_c.getCtlaccount());
					art1.setContraact("");
					art1.setLinememo("Anulacion liquidacion de unidades propias ");
					art1.setRefdate(parameters.getDocdate());
					art1.setRef1(Integer.toString(parameters.getDocentry()));
					// art1.setRef2();
					art1.setBaseref(parameters.getRef1());
					art1.setTaxdate(parameters.getTaxdate());
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
				}
			} else {
				ingreso = colecturia_c.getAcctcode();

			}
		}
		// ----------------------------------------------------------------------------------------------------------------------------------
		// consulta del concepto de ingreso
		// ----------------------------------------------------------------------------------------------------------------------------------
		List conceptos = new Vector();
		conceptos = get_ges_colecturiaConcept1(parameters.getCardcode());

		for (Object object : conceptos) {
			ColecturiaConceptTO concepto = (ColecturiaConceptTO) object;
			if (concepto.getLinenum() == Integer.parseInt(cuentas.getValue1())) {
				ingreso = concepto.getOcrcode();
			}

		}

		if (ingreso.equals(null)) {
			throw new Exception(
					"no existe cuenta asiganada a ingresos para la liquidacion");
		}

		JournalEntryLinesTO art2 = new JournalEntryLinesTO();
		art2.setLine_id(n);
		art2.setAccount(ingreso);
		// art2.setCredit();
		art2.setDebit(sum);
		art2.setDuedate(parameters.getDocdate());
		art2.setShortname(ingreso);
		art2.setContraact("");
		art2.setLinememo("Anulacion liquidacion de unidades propias ");
		art2.setRefdate(parameters.getDocduedate());
		art2.setRef1(Integer.toString(parameters.getDocentry()));
		// art2.setRef2();
		art2.setBaseref(parameters.getRef1());
		art2.setTaxdate(parameters.getTaxdate());
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
		art2.setTranstype(objtype);

		//
		detail.add(art2);

		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// llenado del asiento contable
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// LLenado del padre

		nuevo.setBtfstatus("O");
		nuevo.setTranstype(objtype);
		nuevo.setBaseref(Integer.toString(parameters.getDocnum()));
		nuevo.setRefdate(parameters.getDocdate());
		nuevo.setMemo("Anulacion liquidacion de unidades propias ");
		nuevo.setRef1(Integer.toString(parameters.getDocnum()));
		nuevo.setRef2(parameters.getRef1());
		nuevo.setLoctotal(sum);
		nuevo.setSystotal(sum);
		nuevo.setTransrate(0.0);
		nuevo.setDuedate(parameters.getDocdate());
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
		nuevo.setTranstype("41");
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------
		// metodo para reagrupar cuentas del mismo codigo
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------
		JournalEntryTO journal = new JournalEntryTO();
		journal = fill_JournalEntry_Unir(nuevo);
		// journal=nuevo;
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------

		return journal;

	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------------------------------------------
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

	public JournalEntryTO journal_CheckForPayment(CheckForPaymentTO parameters)
			throws Exception {
		JournalEntryTO journal = new JournalEntryTO();
		if (parameters.getChecksum() == 0) {
			throw new Exception("debe ser un monto mayor que cero");

		}
		List detail = new Vector();

		String cardcode = parameters.getVendorcode();

		BusinesspartnerTO busines = new BusinesspartnerTO();

		CatalogEJB admin = new CatalogEJB();

		busines = admin.get_businesspartnerBykey(cardcode);

		if (busines == null) {
			throw new Exception("no se encuentra socio de negocio");

		}

		JournalEntryLinesTO art1 = new JournalEntryLinesTO();
		// cuenta de socio de negocio
		art1.setLine_id(1);
		art1.setAccount(busines.getDebpayacct());
		art1.setDebit(parameters.getChecksum());
		art1.setDuedate(parameters.getPmntdate());
		art1.setShortname(busines.getDebpayacct());
		art1.setContraact(parameters.getCheckacct());
		art1.setLinememo("emision de cheque a proveedor ");
		art1.setRefdate(parameters.getPmntdate());
		art1.setRef1(Integer.toString(parameters.getCheckkey()));
		// art1.setRef2();
		art1.setBaseref(art1.getRef1());
		art1.setTaxdate(parameters.getPmntdate());
		// art1.setFinncpriod(finncpriod);
		art1.setReltransid(-1);
		art1.setRellineid(-1);
		art1.setReltype("N");
		art1.setObjtype("5");
		art1.setVatline("N");
		art1.setVatamount(0.0);
		art1.setClosed("N");
		art1.setGrossvalue(0.0);
		art1.setBalduedeb(art1.getDebit());
		art1.setBalduecred(0.0);
		art1.setIsnet("Y");
		art1.setTaxtype(0);
		art1.setTaxpostacc("N");
		art1.setTotalvat(0.0);
		art1.setWtliable("N");
		art1.setWtline("N");
		art1.setPayblock("N");
		art1.setOrdered("N");
		art1.setTranstype(parameters.getObjtype());
		detail.add(art1);

		// cuenta del banco

		JournalEntryLinesTO art2 = new JournalEntryLinesTO();

		art2.setLine_id(2);
		art2.setAccount(parameters.getCheckacct());
		art2.setCredit(parameters.getChecksum());
		art2.setDuedate(parameters.getPmntdate());
		art2.setShortname(parameters.getCheckacct());
		art2.setContraact(busines.getDebpayacct());
		art2.setLinememo("emision de cheque a proveedor ");
		art2.setRefdate(parameters.getPmntdate());
		art2.setRef1(Integer.toString(parameters.getCheckkey()));
		// rt1.setRef2();
		art2.setBaseref(art1.getRef1());
		art2.setTaxdate(parameters.getPmntdate());
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
		art2.setBalduecred(art2.getCredit());
		art2.setIsnet("Y");
		art2.setTaxtype(0);
		art2.setTaxpostacc("N");
		art2.setTotalvat(0.0);
		art2.setWtliable("N");
		art2.setWtline("N");
		art2.setPayblock("N");
		art2.setOrdered("N");
		art2.setTranstype(parameters.getObjtype());
		detail.add(art2);
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		// LLenado del padre

		journal.setBtfstatus("O");
		journal.setTranstype("-1");
		journal.setBaseref(Integer.toString(parameters.getCheckkey()));
		journal.setRefdate(parameters.getPmntdate());
		journal.setMemo("emision de cheques a proveedor ");
		journal.setRef1(Integer.toString(parameters.getCheckkey()));
		journal.setRef2(journal.getRef1());
		journal.setLoctotal(parameters.getChecksum());
		journal.setSystotal(parameters.getChecksum());
		journal.setTransrate(0.0);
		journal.setDuedate(parameters.getPmntdate());
		journal.setTaxdate(parameters.getPmntdate());
		journal.setFinncpriod(0);
		journal.setUsersign(parameters.getUsersign());
		journal.setRefndrprt("N");
		journal.setObjtype("5");
		journal.setAdjtran("N");
		journal.setAutostorno("N");
		journal.setSeries(0);
		journal.setAutovat("N");
		journal.setDocseries(0);
		journal.setPrinted("N");
		journal.setAutowt("N");
		journal.setDeferedtax("N");
		journal.setJournalentryList(detail);
		journal.setObjtype(parameters.getObjtype());

		journal.setJournalentryList(detail);
		return journal;
	}

	public int get_dias(String cuenta, String objtype, Connection conn)
			throws Exception {

		int Days = 0;
		JournalEntryLinesDAO DAO = new JournalEntryLinesDAO(conn);
		DAO.setIstransaccional(true);

		Days = DAO.getdays(cuenta, objtype);

		return Days;
	}

	public int dias_prestamo(String cuenta, Connection conn) throws Exception {

		int Days = 0;
		JournalEntryLinesDAO DAO = new JournalEntryLinesDAO(conn);
		DAO.setIstransaccional(true);

		Days = DAO.getdays_credit(cuenta);

		return Days;
	}

	public ResultOutTO interes_moratorio(String cardcode) throws Exception {
		BusinesspartnerDAO DAO = new BusinesspartnerDAO();
		DAO.setIstransaccional(true);
		ResultOutTO resultado = new ResultOutTO();
		try {

			resultado = interes_moratorio(cardcode, DAO.getConn());
		} catch (Exception e) {
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {
			DAO.forceCloseConnection();
		}

		return resultado;
	}

	public ResultOutTO interes_moratorio(String cardcode, Connection conn)
			throws Exception {
		ResultOutTO _result = new ResultOutTO();
		BusinesspartnerAcountTO busines = new BusinesspartnerAcountTO();
		ColecturiaConceptTO colecturia = new ColecturiaConceptTO();
		AccountTO account = new AccountTO();
		AccountTO account2 = new AccountTO();
		List conceptos = new Vector();
		double interes = 0.0;
		int dias_prestamo = 0;
		String codpres;
		String cuenta;

		BusinesspartnerDAO DAO = new BusinesspartnerDAO(conn);
		DAO.setIstransaccional(true);
		ParameterDAO parameter = new ParameterDAO(conn);
		parameter.setIstransaccional(true);
		AccountingDAO accounting = new AccountingDAO(conn);
		accounting.setIstransaccional(true);
		ColecturiaConceptDAO concept = new ColecturiaConceptDAO(conn);
		concept.setIstransaccional(true);

		// consultando el numero de concepto correspondiente a prestamos
		parameterTO parameterTO = new parameterTO();
		parameterTO = parameter.getParameterbykey(11);

		codpres = parameterTO.getValue1();
		busines.setCardcode(cardcode);
		busines.setAcctype(Integer.parseInt(codpres));
		// cosultando el codigo de la cuenta configurada en el concepto de
		// prestamospor socio
		busines = DAO.get_businesspartnerAcount_FCredit(busines);
		cuenta = busines.getAcctcode();
		// consultando el codigo de cuenta para encontrar su saldo si es mayor
		// que cero el socio posee un prestamo sino sera igual que cero
		account = accounting.getAccountByKey(cuenta);

		if (account.getCurrtotal() != null && account.getCurrtotal() > 0.0) {

			parameterTO = parameter.getParameterbykey(12);

			dias_prestamo = dias_prestamo(cuenta, conn);

			// codigo del concepto de interes moratorio
			codpres = parameterTO.getValue1();
			double i;
			busines.setCardcode(cardcode);
			busines.setAcctype(Integer.parseInt(codpres));
			// cosultando el codigo de la cuenta configurada en el concepto de
			// prestamospor socio
			busines = DAO.get_businesspartnerAcount_FCredit(busines);
			cuenta = busines.getAcctcode();

			int days = get_dias(cuenta, "50", conn);

			// validacion si es el primer asiento que se realizas o si es un
			// refinanciamiento
			// si days=-1 no existe transaccion
			// si days > dias_prestamo ... se ha realizado un nuevo prestamo
			if (days == -1 || (days > dias_prestamo)) {
				days = dias_prestamo;
			}
			// si ya se hiso el registro mostrara day=0

			if (days == 0) {
				_result.setCodigoError(1);
				_result.setMensaje("transaccion registrada anteriormente");
				return _result;
			}

			// consultando el porcentage de interes y el monto a cargar a la
			// cuenta

			parameterTO = parameter.getParameterbykey(13);
			i = ((Double.parseDouble(parameterTO.getValue1())) / 100) / 30;
			interes = (i * account.getCurrtotal()) * days;
			ColecturiaConceptTO concepto = new ColecturiaConceptTO();
			conceptos = concept.get_ges_colecturiaConcept();
			for (Object object : conceptos) {
				colecturia = (ColecturiaConceptTO) object;
				if (colecturia.getLinenum() == Integer.parseInt(codpres)) {
					concepto = colecturia;
				}

			}
			// asiento contable correspondiente al calculo del interes moratorio

			JournalEntryTO journal = new JournalEntryTO();
			List detail = new Vector();

			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);

			journal.setBtfstatus("O");
			journal.setTranstype("50");
			journal.setBaseref("50");
			journal.setRefdate(sqlDate);
			journal.setMemo("registro de interes moratorio a la fecha");
			journal.setRef1(Integer.toString(journal.getTransid()));
			journal.setRef2(journal.getRef1());
			journal.setLoctotal(interes);
			journal.setSystotal(interes);
			journal.setTransrate(zero);
			journal.setDuedate(sqlDate);
			journal.setTaxdate(sqlDate);
			journal.setFinncpriod(0);
			journal.setUsersign(0);
			journal.setRefndrprt("N");
			journal.setObjtype("5");
			journal.setAdjtran("N");
			journal.setAutostorno("N");
			journal.setSeries(0);
			journal.setAutovat("N");
			journal.setDocseries(0);
			journal.setPrinted("N");
			journal.setAutowt("N");
			journal.setDeferedtax("N");

			// lineas del asiento contable

			JournalEntryLinesTO art1 = new JournalEntryLinesTO();
			JournalEntryLinesTO art2 = new JournalEntryLinesTO();
			// cuenta de socio de negocio
			art1.setLine_id(1);
			art1.setAccount(cuenta);
			art1.setDebit(interes);
			art1.setDuedate(sqlDate);
			art1.setShortname(cuenta);
			art1.setContraact(concepto.getAcctcode2());
			art1.setLinememo("registro de interes moratorio a la fecha");
			art1.setRefdate(sqlDate);
			art1.setRef1(Integer.toString(concepto.getLinenum()));
			// art1.setRef2();
			art1.setBaseref(art1.getRef1());
			art1.setTaxdate(sqlDate);
			// art1.setFinncpriod(finncpriod);
			art1.setReltransid(-1);
			art1.setRellineid(-1);
			art1.setReltype("N");
			art1.setObjtype("5");
			art1.setVatline("N");
			art1.setVatamount(0.0);
			art1.setClosed("N");
			art1.setGrossvalue(0.0);
			art1.setBalduedeb(interes);
			art1.setBalduecred(0.0);
			art1.setIsnet("Y");
			art1.setTaxtype(0);
			art1.setTaxpostacc("N");
			art1.setTotalvat(0.0);
			art1.setWtliable("N");
			art1.setWtline("N");
			art1.setPayblock("N");
			art1.setOrdered("N");
			art1.setTranstype("50");
			detail.add(art1);

			art2.setLine_id(2);
			art2.setAccount(concepto.getAcctcode2());
			art2.setCredit(interes);
			art2.setDuedate(sqlDate);
			art2.setShortname(concepto.getAcctcode2());
			art2.setContraact(cuenta);
			art2.setLinememo("registro de interes moratorios");
			art2.setRefdate(sqlDate);
			art2.setRef1(Integer.toString(concepto.getLinenum()));
			// rt1.setRef2();
			art2.setBaseref(art1.getRef1());
			art2.setTaxdate(sqlDate);
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
			art2.setBalduecred(art2.getCredit());
			art2.setIsnet("Y");
			art2.setTaxtype(0);
			art2.setTaxpostacc("N");
			art2.setTotalvat(0.0);
			art2.setWtliable("N");
			art2.setWtline("N");
			art2.setPayblock("N");
			art2.setOrdered("N");
			art2.setTranstype("50");
			detail.add(art2);

			journal.setJournalentryList(detail);
			AccountingEJB acounting = new AccountingEJB();

			_result = acounting.journalEntry_mtto(journal, Common.MTTOINSERT,
					conn);

		}

		return _result;

	}

}

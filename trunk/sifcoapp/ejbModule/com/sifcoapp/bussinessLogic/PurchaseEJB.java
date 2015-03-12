package com.sifcoapp.bussinessLogic;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.purchase.dao.*;
import com.sifcoapp.objects.purchase.to.*;

/**
 * Session Bean implementation class SalesEJB
 */
@Stateless
public class PurchaseEJB implements PurchaseEJBRemote {

	/**
	 * Default constructor.
	 */
	public PurchaseEJB() {
		// TODO Auto-generated constructor stub
	}

	public List getPurchase(PurchaseInTO param) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		PurchaseDAO DAO = new PurchaseDAO();

		try {
			_return = DAO.getPurchase(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public PurchaseTO getPurchaseByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		PurchaseTO _return = new PurchaseTO();
		PurchaseDAO DAO = new PurchaseDAO();
		try {
			_return = DAO.getPurchaseByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO inv_Purchase_mtto(PurchaseTO parameters, int action)
			throws Exception {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();
		//Double total = 0.0;
		PurchaseDAO DAO = new PurchaseDAO();
		DAO.setIstransaccional(true);
		PurchaseDetailDAO goodDAO1 = new PurchaseDetailDAO(DAO.getConn());
		goodDAO1.setIstransaccional(true);
		try {
			Iterator<PurchaseDetailTO> iterator2 = parameters
					.getpurchaseDetails().iterator();
			while (iterator2.hasNext()) {
				PurchaseDetailTO articleDetalle = (PurchaseDetailTO) iterator2
						.next();
				//articleDetalle.setLinetotal(articleDetalle.getQuantity()* articleDetalle.getPrice());
				articleDetalle.setDiscprcnt(articleDetalle.getQuantity()); // ############//
																			// DATOS//
																			// ESTATICOS//
																			// ##########
				articleDetalle.setOpenqty(articleDetalle.getQuantity());
				//articleDetalle.setPricebefdi(articleDetalle.getPrice());
				//articleDetalle.setPriceafvat(articleDetalle.getPrice());
				articleDetalle.setFactor1(articleDetalle.getQuantity());
				//articleDetalle.setVatsum(articleDetalle.getPrice());
				//articleDetalle.setGrssprofit(articleDetalle.getPrice());
				//articleDetalle.setVatappld(articleDetalle.getPrice());
				//articleDetalle.setStockpricestockprice(articleDetalle.getPrice());
				//articleDetalle.setGtotal(articleDetalle.getQuantity());
				//total = total + articleDetalle.getLinetotal();
			}
			//parameters.setDoctotal(total);
			parameters.setDiscsum(0.00); // /////////############ DATOS QUEMADOS
											// #######################
			parameters.setNret(0.00);
			parameters.setPaidsum(0.00);
			parameters.setRounddif(0.00);
			_return.setDocentry(DAO.inv_Purchase_mtto(parameters, action));

			Iterator<PurchaseDetailTO> iterator = parameters
					.getpurchaseDetails().iterator();
			while (iterator.hasNext()) {
				PurchaseDetailTO articleDetalle = (PurchaseDetailTO) iterator
						.next();
				// Para articulos nuevos
				articleDetalle.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					goodDAO1.inv_PurchaseDetail_mtto(articleDetalle,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					goodDAO1.inv_PurchaseDetail_mtto(articleDetalle,
							Common.MTTODELETE);
				}
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
		_return.setMensaje("Datos guardados correctamente");
		return _return;
	}

	// ####purchasequantition
	public List getPurchaseQuotation(PurchaseQuotationInTO param) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		PurchaseQuotationDAO DAO = new PurchaseQuotationDAO();

		try {
			_return = DAO.getPurchaseQuotation(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public PurchaseQuotationTO getPurchaseQuotationByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		PurchaseQuotationTO _return = new PurchaseQuotationTO();
		PurchaseQuotationDAO DAO = new PurchaseQuotationDAO();
		try {
			_return = DAO.getPurchaseQuotationByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO inv_PurchaseQuotation_mtto(PurchaseQuotationTO parameters, int action)
			throws Exception {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();
		Double total = 0.0;
		PurchaseQuotationDAO DAO = new PurchaseQuotationDAO();
		DAO.setIstransaccional(true);
		PurchaseQuotationDetailDAO goodDAO1 = new PurchaseQuotationDetailDAO(DAO.getConn());
		goodDAO1.setIstransaccional(true);
		try {
			Iterator<PurchaseQuotationDetailTO> iterator2 = parameters.getPurchaseQuotationDetails().iterator();
			while (iterator2.hasNext()) {
				PurchaseQuotationDetailTO articleDetalle = (PurchaseQuotationDetailTO) iterator2.next();
				//articleDetalle.setLinetotal(articleDetalle.getQuantity()* articleDetalle.getPrice());
				articleDetalle.setDiscprcnt(articleDetalle.getQuantity()); // ############//
																			// DATOS//
																			// ESTATICOS//
																			// ##########
				articleDetalle.setOpenqty(articleDetalle.getQuantity());
				//articleDetalle.setPricebefdi(articleDetalle.getPrice());
				//articleDetalle.setPriceafvat(articleDetalle.getPrice());
				articleDetalle.setFactor1(articleDetalle.getQuantity());
				//articleDetalle.setVatsum(articleDetalle.getPrice());
				//articleDetalle.setGrssprofit(articleDetalle.getPrice());
				//articleDetalle.setVatappld(articleDetalle.getPrice());
				//articleDetalle.setStockpricestockprice(articleDetalle.getPrice());
				//articleDetalle.setGtotal(articleDetalle.getQuantity());
				//total = total + articleDetalle.getLinetotal();
			}
			//parameters.setDoctotal(total);
			parameters.setDiscsum(0.00); // /////////############ DATOS QUEMADOS
											// #######################
			parameters.setNret(0.00);
			parameters.setPaidsum(0.00);
			parameters.setRounddif(0.00);
			_return.setDocentry(DAO.inv_PurchaseQuotation_mtto(parameters, action));

			Iterator<PurchaseQuotationDetailTO> iterator = parameters
					.getPurchaseQuotationDetails().iterator();
			while (iterator.hasNext()) {
				PurchaseQuotationDetailTO articleDetalle = (PurchaseQuotationDetailTO) iterator
						.next();
				// Para articulos nuevos
				articleDetalle.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					goodDAO1.inv_PurchaseQuotationDetail_mtto(articleDetalle,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					goodDAO1.inv_PurchaseQuotationDetail_mtto(articleDetalle,
							Common.MTTODELETE);
				}
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
		_return.setMensaje("Datos guardados correctamente");
		return _return;
	}
	
	
	public List getPurchaseDetail(int docentry) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		PurchaseDetailDAO DAO = new PurchaseDetailDAO();

		try {
			_return = DAO.getpurchaseDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public List getSupplier(SupplierInTO param) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		SupplierDAO DAO = new SupplierDAO();

		try {
			_return = DAO.getSupplier(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public SupplierTO getSupplierByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		SupplierTO _return = new SupplierTO();
		SupplierDAO DAO = new SupplierDAO();
		try {
			_return = DAO.getSupplierByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO inv_Supplier_mtto(SupplierTO parameters, int action)
			throws Exception {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();
		//Double total = 0.0;
		SupplierDAO DAO = new SupplierDAO();
		DAO.setIstransaccional(true);
		SupplierDetailDAO goodDAO1 = new SupplierDetailDAO(DAO.getConn());
		goodDAO1.setIstransaccional(true);
		try {
			Iterator<SupplierDetailTO> iterator2 = parameters
					.getsupplierDetails().iterator();
			while (iterator2.hasNext()) {
				SupplierDetailTO articleDetalle = (SupplierDetailTO) iterator2
						.next();
				//articleDetalle.setLinetotal(articleDetalle.getQuantity()* articleDetalle.getPrice());
				articleDetalle.setDiscprcnt(articleDetalle.getQuantity()); // ############//
																			// DATOS//
																			// ESTATICOS//
																			// ##########
				articleDetalle.setOpenqty(articleDetalle.getQuantity());
				//articleDetalle.setPricebefdi(articleDetalle.getPrice());
				//articleDetalle.setPriceafvat(articleDetalle.getPrice());
				articleDetalle.setFactor1(articleDetalle.getQuantity());
				//articleDetalle.setVatsum(articleDetalle.getPrice());
				//articleDetalle.setGrssprofit(articleDetalle.getPrice());
				//articleDetalle.setVatappld(articleDetalle.getPrice());
				//articleDetalle.setStockpricestockprice(articleDetalle.getPrice());
				//articleDetalle.setGtotal(articleDetalle.getQuantity());
				//total = total + articleDetalle.getLinetotal();
			}
			//parameters.setDoctotal(total);
			parameters.setDiscsum(0.00); // /////////############ DATOS QUEMADOS
											// #######################
			parameters.setNret(0.00);
			parameters.setPaidsum(0.00);
			parameters.setRounddif(0.00);
			_return.setDocentry(DAO.inv_Supplier_mtto(parameters, action));

			Iterator<SupplierDetailTO> iterator = parameters
					.getsupplierDetails().iterator();
			while (iterator.hasNext()) {
				SupplierDetailTO articleDetalle = (SupplierDetailTO) iterator
						.next();
				// Para articulos nuevos
				articleDetalle.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					goodDAO1.inv_SupplierDetail_mtto(articleDetalle,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					goodDAO1.inv_SupplierDetail_mtto(articleDetalle,
							Common.MTTODELETE);
				}
			}
			DAO.forceCommit();
			_return.setCodigoError(0);
			_return.setMensaje("Datos guardados correctamente");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		} finally {

			DAO.forceCloseConnection();
		}

		return _return;
	}

	public List getSupplierDetail(int docentry) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		SupplierDetailDAO DAO = new SupplierDetailDAO();

		try {
			_return = DAO.getsupplierDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

}

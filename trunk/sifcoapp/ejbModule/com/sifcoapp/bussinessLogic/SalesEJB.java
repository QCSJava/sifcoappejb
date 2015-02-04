package com.sifcoapp.bussinessLogic;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.sales.DAO.*;
import com.sifcoapp.objects.sales.to.*;

/**
 * Session Bean implementation class SalesEJB
 */
@Stateless
public class SalesEJB implements SalesEJBRemote {

	/**
	 * Default constructor.
	 */
	public SalesEJB() {
		// TODO Auto-generated constructor stub
	}

	public List getSales(SalesInTO param) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		SalesDAO DAO = new SalesDAO();

		try {
			_return = DAO.getSales(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public SalesTO getSalesByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		SalesTO _return = new SalesTO();
		SalesDAO DAO = new SalesDAO();
		try {
			_return = DAO.getSalesByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO inv_Sales_mtto(SalesTO parameters, int action)
			throws Exception {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();
		Double total = 0.0;
		SalesDAO DAO = new SalesDAO();
		DAO.setIstransaccional(true);
		SalesDetailDAO goodDAO1 = new SalesDetailDAO(DAO.getConn());
		goodDAO1.setIstransaccional(true);
		try {
			Iterator<SalesDetailTO> iterator2 = parameters.getSalesDetails()
					.iterator();
			while (iterator2.hasNext()) {
				SalesDetailTO articleDetalle = (SalesDetailTO) iterator2.next();
				articleDetalle.setLinetotal(articleDetalle.getQuantity()* articleDetalle.getPrice());
				articleDetalle.setDiscprcnt(articleDetalle.getQuantity()); // ############// DATOS// ESTATICOS// ##########											
				articleDetalle.setOpenqty(articleDetalle.getQuantity());
				articleDetalle.setPricebefdi(articleDetalle.getPrice());
				articleDetalle.setPriceafvat(articleDetalle.getPrice());
				articleDetalle.setFactor1(articleDetalle.getQuantity());
				articleDetalle.setVatsum(articleDetalle.getPrice());
				articleDetalle.setGrssprofit(articleDetalle.getPrice());
				articleDetalle.setVatappld(articleDetalle.getPrice());
				articleDetalle.setStockpricestockprice(articleDetalle.getPrice());
				articleDetalle.setGrssprofit(articleDetalle.getPrice());
				articleDetalle.setGtotal(articleDetalle.getQuantity());
				total = total + articleDetalle.getLinetotal();
			}
			parameters.setDoctotal(total);
			parameters.setDiscsum(0.00); // /////////############ DATOS QUEMADOS
											// #######################
			parameters.setNret(0.00);
			parameters.setPaidsum(0.00);
			parameters.setRounddif(0.00);
			_return.setDocentry(DAO.inv_Sales_mtto(parameters, action));

			Iterator<SalesDetailTO> iterator = parameters.getSalesDetails()
					.iterator();
			while (iterator.hasNext()) {
				SalesDetailTO articleDetalle = (SalesDetailTO) iterator.next();
				// Para articulos nuevos
				articleDetalle.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					goodDAO1.inv_SalesDetail_mtto(articleDetalle,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					goodDAO1.inv_SalesDetail_mtto(articleDetalle,
							Common.MTTODELETE);
				}
			}
			DAO.forceCommit();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DAO.rollBackConnection();
			throw (EJBException) new EJBException(e);
		}finally{
			
			DAO.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos guardados correctamente");
		return _return;
	}

	public List getSalesDetail(int docentry) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		SalesDetailDAO DAO = new SalesDetailDAO();

		try {
			_return = DAO.getSalesDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}
	
	
	
	public List getClientCredi(ClientCrediInTO param) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		ClientCrediDAO DAO = new ClientCrediDAO();

		try {
			_return = DAO.getClientCredi(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public ClientCrediTO getClientCrediByKey(int docentry) throws Exception {
		// TODO Auto-generated method stub
		ClientCrediTO _return = new ClientCrediTO();
		ClientCrediDAO DAO = new ClientCrediDAO();
		try {
			_return = DAO.getClientCrediByKey(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public ResultOutTO inv_ClientCredi_mtto(ClientCrediTO parameters, int action)
			throws Exception {
		// TODO Auto-generated method stub
		ResultOutTO _return = new ResultOutTO();
		Double total = 0.0;
		ClientCrediDAO DAO = new ClientCrediDAO();
		DAO.setIstransaccional(true);
		ClientCrediDetailDAO goodDAO1 = new ClientCrediDetailDAO(DAO.getConn());
		goodDAO1.setIstransaccional(true);
		try {
			Iterator<ClientCrediDetailTO> iterator2 = parameters.getclientDetails()
					.iterator();
			while (iterator2.hasNext()) {
				ClientCrediDetailTO articleDetalle = (ClientCrediDetailTO) iterator2.next();
				articleDetalle.setLinetotal(articleDetalle.getQuantity()* articleDetalle.getPrice());
				articleDetalle.setDiscprcnt(articleDetalle.getQuantity()); // ############// DATOS// ESTATICOS// ##########											
				articleDetalle.setOpenqty(articleDetalle.getQuantity());
				articleDetalle.setPricebefdi(articleDetalle.getPrice());
				articleDetalle.setPriceafvat(articleDetalle.getPrice());
				articleDetalle.setFactor1(articleDetalle.getQuantity());
				articleDetalle.setVatsum(articleDetalle.getPrice());
				articleDetalle.setGrssprofit(articleDetalle.getPrice());
				articleDetalle.setVatappld(articleDetalle.getPrice());
				articleDetalle.setStockpricestockprice(articleDetalle.getPrice());
				articleDetalle.setGrssprofit(articleDetalle.getPrice());
				articleDetalle.setGtotal(articleDetalle.getQuantity());
				total = total + articleDetalle.getLinetotal();
			}
			parameters.setDoctotal(total);
			parameters.setDiscsum(0.00); // /////////############ DATOS QUEMADOS
											// #######################
			parameters.setNret(0.00);
			parameters.setPaidsum(0.00);
			parameters.setRounddif(0.00);
			_return.setDocentry(DAO.inv_ClientCredi_mtto(parameters, action));

			Iterator<ClientCrediDetailTO> iterator = parameters.getclientDetails()
					.iterator();
			while (iterator.hasNext()) {
				ClientCrediDetailTO articleDetalle = (ClientCrediDetailTO) iterator.next();
				// Para articulos nuevos
				articleDetalle.setDocentry(_return.getDocentry());
				if (action == Common.MTTOINSERT) {
					goodDAO1.inv_ClientCrediDetail_mtto(articleDetalle,
							Common.MTTOINSERT);
				}
				if (action == Common.MTTODELETE) {
					goodDAO1.inv_ClientCrediDetail_mtto(articleDetalle,
							Common.MTTODELETE);
				}
			}
		DAO.forceCommit();	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				DAO.rollBackConnection();
				throw (EJBException) new EJBException(e);
			}finally{
				
				DAO.forceCloseConnection();
			}
			_return.setCodigoError(0);
			_return.setMensaje("Datos guardados correctamente");		
		return _return;
	}

	public List getClientCrediDetail(int docentry) throws Exception {
		// TODO Auto-generated method stub
		List _return;
		ClientCrediDetailDAO DAO = new ClientCrediDetailDAO();

		try {
			_return = DAO.getClientCrediDetail(docentry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

}

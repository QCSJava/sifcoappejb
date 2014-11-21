package com.sifcoapp.bussinessLogic;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;

import com.sifcoapp.objects.accounting.to.AccPeriodOutTO;

/**
 * Session Bean implementation class AccountingEJB
 */
@Stateless
public class AccountingEJB implements AccountingEJBRemote {

    /**
     * Default constructor. 
     */
    public AccountingEJB() {
        // TODO Auto-generated constructor stub
    }
    public List getAccPeriods(){
    	List lstPeriods=new Vector();
    	
    	AccPeriodOutTO accPeriodOutTO = new AccPeriodOutTO();
    	AccPeriodOutTO accPeriodOutTO1 = new AccPeriodOutTO();
    	
    	accPeriodOutTO.setCantidadPeriodo("cant1");
    	accPeriodOutTO.setCodigoPeriodo("CodigoPeriodo1");
    	accPeriodOutTO.setCodResp(1);
    	accPeriodOutTO.setEjercicio("Ejercicio1");
    	accPeriodOutTO.setFechaConta("20/11/2014");
    	accPeriodOutTO.setFechaDocumento("20/11/2014");
    	accPeriodOutTO.setFechaVencimiento("20/11/2014");
    	accPeriodOutTO.setIndicadorPeriodo("indicadorPeriodo1");
    	accPeriodOutTO.setInicioEjercicio("inicioEjercicio");
    	accPeriodOutTO.setNombrePeriodo("nombrePeriodo");
    	accPeriodOutTO.setStatusPeriodo("statusPeriodo");
    	accPeriodOutTO.setSubPeriodo("subPeriodo");
    	lstPeriods.add(accPeriodOutTO);
    	
    	accPeriodOutTO1.setCantidadPeriodo("cant1");
    	accPeriodOutTO1.setCodigoPeriodo("CodigoPeriodo1");
    	accPeriodOutTO1.setCodResp(1);
    	accPeriodOutTO1.setEjercicio("Ejercicio1");
    	accPeriodOutTO1.setFechaConta("20/11/2014");
    	accPeriodOutTO1.setFechaDocumento("20/11/2014");
    	accPeriodOutTO1.setFechaVencimiento("20/11/2014");
    	accPeriodOutTO1.setIndicadorPeriodo("indicadorPeriodo1");
    	accPeriodOutTO1.setInicioEjercicio("inicioEjercicio");
    	accPeriodOutTO1.setNombrePeriodo("nombrePeriodo");
    	accPeriodOutTO1.setStatusPeriodo("statusPeriodo");
    	accPeriodOutTO1.setSubPeriodo("subPeriodo");
    	lstPeriods.add(accPeriodOutTO1);
    	
    	return lstPeriods;
    }
}

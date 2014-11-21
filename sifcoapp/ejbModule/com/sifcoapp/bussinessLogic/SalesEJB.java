package com.sifcoapp.bussinessLogic;

import javax.ejb.Stateless;

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

	public String doSales() {
		String retorno;
		// TODO Auto-generated method stub
		retorno="Do Sales!";
		return retorno;
	}

}

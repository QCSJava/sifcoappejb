package com.sifcoapp.test;

import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.SalesEJBClient;

public class SalesTest {
	private static SalesEJBClient SalesEJBService=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if 		(SalesEJBService==null)
			SalesEJBService=new SalesEJBClient();
		
		String retorno=null;
		
		try {
			retorno=SalesEJBService.doSales();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(retorno);
		
	}
}

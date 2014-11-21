package com.sifcoapp.test;

import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.AccountingEJBClient;
import com.sifcoapp.client.SecurityEJBClient;
import com.sifcoapp.objects.security.to.ProfileInTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;

public class AccountingTest {
	private static AccountingEJBClient AccountingEJBService=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if 		(AccountingEJBService==null)
			AccountingEJBService=new AccountingEJBClient();
		
		List lstPeriods=new Vector();
		
		lstPeriods=AccountingEJBService.getAccPeriods();
		
		System.out.println(lstPeriods);
		
	}
}

package com.sifcoapp.client;

import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.objects.security.to.ProfileInTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;
import com.sifcoapp.security.ejb.*;
import com.sifcoapp.clientutility.ClientUtility;

public class SecurityEJBClient implements SecurityEJBRemote {
	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/SecurityEJB!com.sifcoapp.security.ejb.SecurityEJBRemote";
	private static SecurityEJBRemote bean;
	private static Context context = null;

	public SecurityEJBClient() {

		// 2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			bean = (SecurityEJBRemote) context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UserAppOutTO UserValidate(UserAppInTO usr) {
		UserAppOutTO usrOutTO = null;

		usrOutTO = bean.UserValidate(usr);

		return usrOutTO;

	}

	public String SayHello(String hellomsg) {
		// TODO Auto-generated method stub
		String message;

		message = bean.SayHello(hellomsg);

		return message;
	}

	public static void main(String[] args) {

		SecurityEJBRemote bean;
		Context context;
		try {
			context = ClientUtility.getInitialContext();
			bean = (SecurityEJBRemote) context.lookup(LOOKUP_STRING);

			UserAppOutTO usrOutTO = new UserAppOutTO();
			UserAppInTO usr = new UserAppInTO();
			usrOutTO = bean.UserValidate(usr);
			System.out.println(usrOutTO);
			System.out.println(usrOutTO.getValidUser());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * @author Rutilio Iraheta
	 * 
	 * @date 11 nov 2014
	 * 
	 * @see
	 * com.sifcoapp.security.ejb.SecurityEJBRemote#GetUserProfile(com.sifcoapp
	 * .objects.security.to.ProfileInTO)
	 */
	public ProfileOutTO GetUserProfile(ProfileInTO usrProfile) {
		// TODO Auto-generated method stub
		ProfileOutTO profileOutTO = new ProfileOutTO();

		profileOutTO = bean.GetUserProfile(usrProfile);

		return profileOutTO;
	}

}

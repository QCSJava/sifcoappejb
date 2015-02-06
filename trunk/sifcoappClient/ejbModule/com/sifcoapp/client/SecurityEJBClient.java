package com.sifcoapp.client;

import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;
import com.sifcoapp.objects.security.to.UserTO;
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
	public ProfileOutTO GetUserProfile(UserAppInTO usr) {
		// TODO Auto-generated method stub
		ProfileOutTO profileOutTO = new ProfileOutTO();

		profileOutTO = bean.GetUserProfile(usr);

		return profileOutTO;
	}

	public int cat_users_mtto(UserTO parameters, int action) {
		// TODO Auto-generated method stub
		int _return = 0;

		_return = bean.cat_users_mtto(parameters, action);

		return _return;
	}

	public List getUser() {
		// TODO Auto-generated method stub
		List _return = null;

		_return = bean.getUser();

		return _return;
	}

	public UserTO getUserByNickname(String nickname) throws Exception {
		// TODO Auto-generated method stub
		UserTO _return= new UserTO();
		_return= bean.getUserByNickname(nickname);
		return _return;
	}

	public List getProfile() throws Exception {
		// TODO Auto-generated method stub
		List _return = null;

		_return = bean.getProfile();

		return _return;
	}

}

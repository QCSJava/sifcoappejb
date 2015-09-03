package com.sifcoapp.client;

import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.NamingException;

import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.security.to.AdmProfileTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.ProfileTO;
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
	
	public List GetUserProfile_Mtto(int id_perfil) {
		// TODO Auto-generated method stub
		List profileOutTO = new Vector();

		profileOutTO = bean.GetUserProfile_Mtto(id_perfil);

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

	public UserTO getUserByNickname(String nickname) throws EJBException {
		// TODO Auto-generated method stub
		UserTO _return= new UserTO();
		_return= bean.getUserByNickname(nickname);
		return _return;
	}

	public List getProfile() throws EJBException {
		// TODO Auto-generated method stub
		List _return = null;

		_return = bean.getProfile();

		return _return;
	}
	
	public List getProfile(String _profile) throws EJBException {
		// TODO Auto-generated method stub
		List _return = null;

		_return = bean.getProfile(_profile);

		return _return;
	}

	public ResultOutTO adm_profile_mtto(AdmProfileTO parameters, int action)
			throws EJBException {
		// TODO Auto-generated method stub
		ResultOutTO _return =  new ResultOutTO();

		_return = bean.adm_profile_mtto(parameters, action);

		return _return;
	}

	public List getAdmProfile() throws EJBException {
		// TODO Auto-generated method stub
		List _return = null;

		_return = bean.getAdmProfile();

		return _return;
	}

	public List getAdmProfile_by_key(int profilecode)
			throws EJBException {
		// TODO Auto-generated method stub
		List _return= new Vector();
		_return= bean.getAdmProfile_by_key(profilecode);
		return _return;
	}
	
	public ResultOutTO Usr1_profile_mtto(ProfileTO parameters, int action)
			throws EJBException {
		// TODO Auto-generated method stub
		ResultOutTO _return = new  ResultOutTO();

		_return = bean.Usr1_profile_mtto(parameters, action);

		return _return;
	}

	public List getUsr1Profile() throws EJBException {
		// TODO Auto-generated method stub
		List _return = null;

		_return = bean.getUsr1Profile();

		return _return;
	}

	public ProfileTO getUsr1Profile_by_key(int profilecode)
			throws EJBException {
		// TODO Auto-generated method stub
		ProfileTO _return= new ProfileTO();
		_return= bean.getUsr1Profile_by_key(profilecode);
		return _return;
	}

}

package com.sifcoapp.security.ejb;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;
import com.sifcoapp.objects.security.to.UserTO;

@Remote
public interface SecurityEJBRemote {
	public UserAppOutTO UserValidate(UserAppInTO usr) throws EJBException;

	public ProfileOutTO GetUserProfile(UserAppInTO usr) throws EJBException;

	public int cat_users_mtto(UserTO parameters, int action)
			throws EJBException;

	public List getUser() throws EJBException;
	
	public UserTO getUserByNickname(String nickname) throws Exception;
}

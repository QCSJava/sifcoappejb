package com.sifcoapp.security.ejb;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.security.to.AdmProfileTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.ProfileTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;
import com.sifcoapp.objects.security.to.UserTO;

@Remote
public interface SecurityEJBRemote {
	public UserAppOutTO UserValidate(UserAppInTO usr) throws EJBException;

	public ProfileOutTO GetUserProfile(UserAppInTO usr) throws EJBException;
	
	public ProfileOutTO GetUserProfile_Mtto(UserAppInTO usr) throws EJBException;

	public int cat_users_mtto(UserTO parameters, int action)
			throws EJBException;

	public List getUser() throws EJBException;
	
	public UserTO getUserByNickname(String nickname) throws EJBException;

	public List getProfile() throws EJBException;
	
	public List getProfile(String _profile) throws EJBException;
	
	public ResultOutTO adm_profile_mtto(AdmProfileTO parameters, int action)throws EJBException;
	
	public List getAdmProfile()throws EJBException;
	
	public List getAdmProfile_by_key(int profilecode)throws EJBException;
	
	public ResultOutTO Usr1_profile_mtto(ProfileTO parameters, int action)throws EJBException;
	
	public List getUsr1Profile()throws EJBException;
	
	public ProfileTO getUsr1Profile_by_key(int profilecode)throws EJBException;
}

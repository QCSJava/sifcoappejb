package com.sifcoapp.security.ejb;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.security.dao.UserDAO;
import com.sifcoapp.objects.security.to.ProfileDetOutTO;
import com.sifcoapp.objects.security.to.ProfileInTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;
import com.sifcoapp.objects.security.to.UserTO;

/**
 * Session Bean implementation class SecurityEJB
 */
@Stateless
public class SecurityEJB implements SecurityEJBRemote {

	/**
	 * Default constructor.
	 */
	public SecurityEJB() {
		// TODO Auto-generated constructor stub
	}

	public UserAppOutTO UserValidate(UserAppInTO usr) {
		// TODO Auto-generated method stub
		UserAppOutTO usrValid = new UserAppOutTO();
		ProfileOutTO usrprofile = null;
		UserDAO userdao = new UserDAO();
		userdao.setIstransaccional(true);
		try {
			usrValid = userdao.getUserValid(usr);
		

		if (usrValid.getValidUser() == 0) {
			// usuario Valido
			System.out.println("validate " + usr.getIdUserApp());
			userdao.initCommon();
			usrprofile = userdao.getUsrProfileHeader(usr.getIdUserApp());
			usrValid.setUsrprofile(usrprofile);
		}
		// ToDo: forceconn
		if (userdao.getConn() != null) {
			try {
				userdao.getConn().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw (EJBException) new EJBException(e);
			}
		}

		// usrValid.setDesc_usr("Administrator");
		// usrValid.setId_perfil(1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e1);
		}
		return usrValid;
	}

	// cambio
	public ProfileOutTO GetUserProfile(UserAppInTO usr) {
		ProfileOutTO usrProfileOut = new ProfileOutTO();
		UserDAO usrDAO = new UserDAO();
		usrDAO.setIstransaccional(true);

		try {
			usrProfileOut = usrDAO.getUsrProfileHeader(usr.getIdUserApp());
		
		usrProfileOut.setProfile_det(usrDAO.getUsrProfileDetail(usrProfileOut
				.getId_perfil()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		// TODO Auto-generated method stub

		return usrProfileOut;
	}

	public int cat_users_mtto(UserTO parameters, int action) {
		// TODO Auto-generated method stub
		int _return = 0;

		UserDAO userdao = new UserDAO();
		userdao.setIstransaccional(true);
		try {
			_return = userdao.cat_users_mtto(parameters, action);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			userdao.rollBackConnection();
			throw (EJBException) new EJBException(e);
		}finally{
			
			userdao.forceCloseConnection();
		}

		return _return;
	}

	public List getUser() {
		// TODO Auto-generated method stub
		List _return = null;

		UserDAO userdao = new UserDAO();
		try {
			_return = userdao.getUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}

	public UserTO getUserByNickname(String nickname) throws Exception {
		// TODO Auto-generated method stub
		UserTO _return= new UserTO();
		UserDAO userdao= new UserDAO();
		try {
			_return = userdao.getUserByNickname(nickname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

}

package com.sifcoapp.security.ejb;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.objects.admin.dao.AdminDAO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.common.to.ResultOutTO;
import com.sifcoapp.objects.security.dao.UserDAO;
import com.sifcoapp.objects.security.to.AdmProfileTO;
import com.sifcoapp.objects.security.to.ProfileDetOutTO;
import com.sifcoapp.objects.security.to.ProfileInTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.ProfileTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;
import com.sifcoapp.objects.security.to.UserTO;
import com.sifcoapp.objects.common.to.ResultOutTO;

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

				userdao.getConn().close();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e1);
		} finally {

			userdao.forceCloseConnection();
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

			usrProfileOut.setProfile_det(usrDAO
					.getUsrProfileDetail(usrProfileOut.getId_perfil()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		} finally {

			usrDAO.forceCloseConnection();
		}

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
		} finally {

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

	public UserTO getUserByNickname(String nickname) throws EJBException {
		// TODO Auto-generated method stub
		UserTO _return = new UserTO();
		UserDAO userdao = new UserDAO();
		try {
			_return = userdao.getUserByNickname(nickname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}
		return _return;
	}

	public List getProfile() throws EJBException {
		// TODO Auto-generated method stub
		List _return = null;

		UserDAO userdao = new UserDAO();
		try {
			_return = userdao.getProfile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}
	public List getProfile(String _profile) throws EJBException {
		// TODO Auto-generated method stub
		List _return = null;

		UserDAO userdao = new UserDAO();
		try {
			_return = userdao.getProfile(_profile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}
	
public ResultOutTO adm_profile_mtto(AdmProfileTO parameters, int action)throws EJBException{
	ResultOutTO _return = new ResultOutTO();
	int resul;
	UserDAO userdao = new UserDAO();
	userdao.setIstransaccional(true);
	try {
		resul = userdao.adm_profile_mtto(parameters, action);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		userdao.rollBackConnection();
		throw (EJBException) new EJBException(e);
	} finally {

		userdao.forceCloseConnection();
	}
	_return.setCodigoError(0);
	_return.setMensaje("Datos Ingresados Correctamente");
	return _return;
}
	
	public List getAdmProfile()throws EJBException{
		List _return = null;

		UserDAO userdao = new UserDAO();
		try {
			_return = userdao.getAdmProfile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		}

		return _return;
	}
	
	public List getAdmProfile_by_key(int profilecode)throws EJBException{
		List ProfileOut = new Vector();
		UserDAO usrDAO = new UserDAO();
		usrDAO.setIstransaccional(true);

		try {
			ProfileOut = usrDAO.getAdmProfile_by_key(profilecode);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (EJBException) new EJBException(e);
		} finally {

			usrDAO.forceCloseConnection();
		}

		return ProfileOut;
	}
	
	
	
	
	
	public ResultOutTO Usr1_profile_mtto(ProfileTO parameters, int action)throws EJBException{
		ResultOutTO _return = new ResultOutTO();
		int resul=0;
		UserDAO userdao = new UserDAO();
		userdao.setIstransaccional(true);
		try {
			resul = userdao.Usr1_profile_mtto(parameters, action);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			userdao.rollBackConnection();
			throw (EJBException) new EJBException(e);
		} finally {

			userdao.forceCloseConnection();
		}
		_return.setCodigoError(0);
		_return.setMensaje("Datos Ingresados Correctamente");
		return _return;
	}
		
		public List getUsr1Profile()throws EJBException{
			List _return = null;

			UserDAO userdao = new UserDAO();
			try {
				_return = userdao.getUsr1Profile();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw (EJBException) new EJBException(e);
			}

			return _return;
		}
		
		public ProfileTO getUsr1Profile_by_key(int profilecode)throws EJBException{
			ProfileTO ProfileOut = new ProfileTO();
			UserDAO usrDAO = new UserDAO();
			usrDAO.setIstransaccional(true);

			try {
				ProfileOut = usrDAO.getUsr1Profile_by_key(profilecode);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw (EJBException) new EJBException(e);
			} finally {

				usrDAO.forceCloseConnection();
			}

			return ProfileOut;
		}
}

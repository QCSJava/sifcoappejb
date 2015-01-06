package com.sifcoapp.security.ejb;

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
				e.printStackTrace();
			}
		}

		// usrValid.setDesc_usr("Administrator");
		// usrValid.setId_perfil(1);

		return usrValid;
	}

	public String SayHello(String hellomsg) {
		// TODO Auto-generated method stub
		hellomsg = hellomsg + "EJB";
		System.out.println(hellomsg);
		return hellomsg;
	}

	// cambio
	public ProfileOutTO GetUserProfile(UserAppInTO usr) {
		ProfileOutTO usrProfileOut = new ProfileOutTO();
		List profileDetLst = new Vector();
		List profileDet1Lst = new Vector();
		List profileDet11Lst = new Vector();
		List profileDet2Lst = new Vector();
		List profileDet3Lst = new Vector();

		ProfileDetOutTO profileDet = new ProfileDetOutTO();
		ProfileDetOutTO profileDet1 = new ProfileDetOutTO();
		ProfileDetOutTO profileDet2 = new ProfileDetOutTO();
		ProfileDetOutTO profileDetL1 = new ProfileDetOutTO();
		ProfileDetOutTO profileDetL11 = new ProfileDetOutTO();
		ProfileDetOutTO profileDetL12 = new ProfileDetOutTO();
		ProfileDetOutTO profileDetL13 = new ProfileDetOutTO();
		ProfileDetOutTO profileDetL131 = new ProfileDetOutTO();

		ProfileDetOutTO profileDet1L1 = new ProfileDetOutTO();

		ProfileDetOutTO profileDetL2 = new ProfileDetOutTO();
		ProfileDetOutTO profileDetL3 = new ProfileDetOutTO();

		// usrProfile.setId_perfil(1);
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * UserDAO userdao = new UserDAO(); userdao.setIstransaccional(true);
		 * usrProfileOut=userdao.getUsrProfileHeader(usrProfile.getId_perfil());
		 * usrProfileOut
		 * .setProfile_det(userdao.getUserProfiles(usrProfile).getProfile_det
		 * ()); System.out.println(usrProfileOut.getDesc_perfil());
		 */

		/*
		 * 
		 * 
		 * usrProfileOut.setDesc_perfil("Administrator");
		 * usrProfileOut.setId_perfil(1);
		 * 
		 * 
		 * 
		 * profileDet.setDesc_perfil_det("Gestion");
		 * profileDet.setUrl_perfil_det("#"); profileDet.setId_perfil_det(1);
		 * profileDet.setParent_id(0);
		 * 
		 * 
		 * 
		 * profileDetL1.setDesc_perfil_det("Inicializacion");
		 * profileDetL1.setUrl_perfil_det("#");
		 * profileDetL1.setId_perfil_det(3); profileDetL1.setParent_id(1);
		 * 
		 * 
		 * 
		 * profileDetL11.setDesc_perfil_det("Detalles de sociedad");
		 * profileDetL11.setUrl_perfil_det("/faces/detalle_sociedad.xhtml");
		 * profileDetL11.setId_perfil_det(4); profileDetL11.setParent_id(3);
		 * 
		 * 
		 * profileDet11Lst.add(profileDetL11);
		 * 
		 * 
		 * profileDetL12.setDesc_perfil_det("Periodos Contables");
		 * profileDetL12.setUrl_perfil_det("#");
		 * profileDetL12.setId_perfil_det(5); profileDetL12.setParent_id(3);
		 * 
		 * 
		 * profileDet11Lst.add(profileDetL12);
		 * 
		 * 
		 * profileDetL13.setDesc_perfil_det("Autorizaciones");
		 * profileDetL13.setUrl_perfil_det("#");
		 * profileDetL13.setId_perfil_det(6); profileDetL13.setParent_id(3);
		 * 
		 * 
		 * profileDet11Lst.add(profileDetL13);
		 * 
		 * 
		 * profileDetL131.setDesc_perfil_det("Autorizaciones Generales");
		 * profileDetL131.setUrl_perfil_det("#");
		 * profileDetL131.setId_perfil_det(7); profileDetL131.setParent_id(6);
		 * 
		 * profileDetL1.setNodeDetail(profileDet11Lst);
		 * 
		 * profileDet1Lst.add(profileDetL1);
		 * profileDet.setNodeDetail(profileDet1Lst);
		 * 
		 * 
		 * 
		 * profileDet1.setDesc_perfil_det("Inventarios");
		 * profileDet1.setUrl_perfil_det("#"); profileDet1.setId_perfil_det(2);
		 * profileDet1.setParent_id(0);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * profileDet1L1.setDesc_perfil_det("Maestro Productos");
		 * 
		 * profileDet1L1.setUrl_perfil_det(
		 * "/faces/view/mtto/ArticleInventoryMaster.xhtml");
		 * profileDet1L1.setId_perfil_det(20);
		 * 
		 * profileDet1L1.setParent_id(2);
		 * 
		 * 
		 * profileDet2Lst.add(profileDet1L1);
		 * 
		 * profileDet1.setNodeDetail(profileDet2Lst);
		 * 
		 * 
		 * profileDetLst.add(profileDet); profileDetLst.add(profileDet1);
		 * 
		 * 
		 * profileDetL12.setDesc_perfil_det("Periodos Contables");
		 * profileDetL12.setUrl_perfil_det("/faces/periodos.xhtml");
		 * profileDetL12.setId_perfil_det(4); profileDetL12.setParent_id(1);
		 * 
		 * 
		 * profileDetL13.setDesc_perfil_det("Autorizaciones Generales");
		 * profileDetL13.setUrl_perfil_det("#");
		 * profileDetL13.setId_perfil_det(6); profileDetL13.setParent_id(1);
		 * 
		 * profileDet1.setNodeDetail(profileDet2Lst);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * usrProfileOut.setProfile_det(profileDetLst);
		 */

		UserDAO usrDAO = new UserDAO();
		usrDAO.setIstransaccional(true);

		usrProfileOut = usrDAO.getUsrProfileHeader(usr.getIdUserApp());
		usrProfileOut.setProfile_det(usrDAO.getUsrProfileDetail(usrProfileOut
				.getId_perfil()));

		// TODO Auto-generated method stub

		return usrProfileOut;
	}

	public int cat_users_mtto(UserTO parameters, int action) {
		// TODO Auto-generated method stub
		int _return;

		UserDAO userdao = new UserDAO();
		_return = userdao.cat_users_mtto(parameters, action);

		return _return;
	}

	public List getUser() {
		// TODO Auto-generated method stub
		List _return;

		UserDAO userdao = new UserDAO();
		_return = userdao.getUser();

		return _return;
	}

}

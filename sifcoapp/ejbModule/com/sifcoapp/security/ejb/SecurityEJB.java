package com.sifcoapp.security.ejb;

import javax.ejb.Stateless;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.security.dao.UserDAO;
import com.sifcoapp.objects.security.to.ProfileDetOutTO;
import com.sifcoapp.objects.security.to.ProfileInTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;

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
		/*if (usr.getIdUserApp().equals("admin")&&usr.getPasswordUserApp().equals("adminadmin"))
			usrValid.setValidUser(Common.VALID);
		else
			usrValid.setValidUser(Common.INVALID);*/
		
		UserDAO userdao = new UserDAO();
		usrValid=userdao.getUserValid(usr);
			
		usrValid.setDesc_usr("Administrator");
		usrValid.setId_perfil(1);
		
		return usrValid;
	}

    
    public String SayHello(String hellomsg) {
		// TODO Auto-generated method stub
		hellomsg=hellomsg+"EJB";
		System.out.println(hellomsg);
		return hellomsg;
	}
//cambio
	public ProfileOutTO GetUserProfile(ProfileInTO usrProfile) {
		ProfileOutTO usrProfileOut = new ProfileOutTO();
		List profileDetLst=new Vector();
		List profileDet1Lst=new Vector();
		List profileDet11Lst=new Vector();
		List profileDet2Lst=new Vector();
		List profileDet3Lst=new Vector();
		
		ProfileDetOutTO profileDet=new ProfileDetOutTO();
		ProfileDetOutTO profileDet1=new ProfileDetOutTO();
		ProfileDetOutTO profileDet2=new ProfileDetOutTO();
		ProfileDetOutTO profileDetL1=new ProfileDetOutTO();
		ProfileDetOutTO profileDetL11=new ProfileDetOutTO();
		ProfileDetOutTO profileDetL12=new ProfileDetOutTO();
		ProfileDetOutTO profileDetL13=new ProfileDetOutTO();
		ProfileDetOutTO profileDetL131=new ProfileDetOutTO();
	
		ProfileDetOutTO profileDetL2=new ProfileDetOutTO();
		ProfileDetOutTO profileDetL3=new ProfileDetOutTO();
		
		
		UserDAO userdao = new UserDAO();
		usrProfileOut=userdao.getUserProfiles(usrProfile);
		
		
		usrProfileOut.setDesc_perfil("Administrator");
		usrProfileOut.setId_perfil(1);
		
		
		profileDet.setDesc_perfil_det("Gestion");
		profileDet.setUrl_perfil_det("#");
		profileDet.setId_perfil_det(1);
		profileDet.setParent_id(0);
		
		
		
		profileDetL1.setDesc_perfil_det("Inicializacion");
		profileDetL1.setUrl_perfil_det("#");
		profileDetL1.setId_perfil_det(3);
		profileDetL1.setParent_id(1);
		
				
		profileDetL11.setDesc_perfil_det("Detalles de sociedad");
		profileDetL11.setUrl_perfil_det("/faces/detalle_sociedad.xhtml");
		profileDetL11.setId_perfil_det(4);
		profileDetL11.setParent_id(3);
				
		profileDet11Lst.add(profileDetL11);
		
		profileDetL12.setDesc_perfil_det("Periodos Contables");
		profileDetL12.setUrl_perfil_det("#");
		profileDetL12.setId_perfil_det(5);
		profileDetL12.setParent_id(3);
		
		profileDet11Lst.add(profileDetL12);
		
		profileDetL13.setDesc_perfil_det("Autorizaciones");
		profileDetL13.setUrl_perfil_det("#");
		profileDetL13.setId_perfil_det(6);
		profileDetL13.setParent_id(3);
		
		profileDet11Lst.add(profileDetL13);
		
		profileDetL131.setDesc_perfil_det("Autorizaciones Generales");
		profileDetL131.setUrl_perfil_det("#");
		profileDetL131.setId_perfil_det(7);
		profileDetL131.setParent_id(6);
		
		
		profileDet1.setDesc_perfil_det("Inventarios");
		profileDet1.setUrl_perfil_det("#");
		profileDet1.setId_perfil_det(2);
		profileDet1.setParent_id(0);
		
		profileDetL1.setNodeDetail(profileDet11Lst);
		
		profileDet1Lst.add(profileDetL1);
		profileDet.setNodeDetail(profileDet1Lst);
		
		profileDetLst.add(profileDet);
		profileDetLst.add(profileDet1);
		
		
		profileDetL12.setDesc_perfil_det("Periodos Contables");
		profileDetL12.setUrl_perfil_det("/faces/periodos.xhtml");
		profileDetL12.setId_perfil_det(4);
		profileDetL12.setParent_id(1);
		
				
		profileDetL13.setDesc_perfil_det("Autorizaciones Generales");
		profileDetL13.setUrl_perfil_det("#");
		profileDetL13.setId_perfil_det(6);
		profileDetL13.setParent_id(1);

		
		usrProfileOut.setProfile_det(profileDetLst);
		
		// TODO Auto-generated method stub
		
		return usrProfileOut;
	}

}

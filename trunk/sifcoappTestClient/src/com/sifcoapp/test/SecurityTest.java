package com.sifcoapp.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.AdminEJBClient;
import com.sifcoapp.client.HelloWorldClient;
import com.sifcoapp.client.SecurityEJBClient;
import com.sifcoapp.objects.admin.to.ArticlesTO;
import com.sifcoapp.objects.admin.to.BranchArticlesTO;
import com.sifcoapp.objects.catalogos.Common;
import com.sifcoapp.objects.security.dao.UserDAO;
import com.sifcoapp.objects.security.to.ProfileDetOutTO;
import com.sifcoapp.objects.security.to.ProfileInTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;
import com.sifcoapp.objects.security.to.UserTO;
import com.sifcoapp.objects.utilities.PasswordService;

public class SecurityTest {
	private static SecurityEJBClient SecurityEJBService = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (SecurityEJBService == null)
			SecurityEJBService = new SecurityEJBClient();

		String v_method = args[0];

		/*
		 * List lstPeriods=new Vector();
		 * 
		 * lstPeriods=AccountingEJBService.getAccPeriods();
		 * 
		 * System.out.println(lstPeriods);
		 */

		try {
			SecurityTest.class.getMethod(args[0], null).invoke(null, null);
			// testPeriods();

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void GetUserProfile() {
		// TODO Auto-generated method stub
		if (SecurityEJBService == null)
			SecurityEJBService = new SecurityEJBClient();

		UserAppInTO usr = new UserAppInTO();
		UserAppOutTO usrRes = new UserAppOutTO();
		
		usr.setIdUserApp("admin");
		usr.setPasswordUserApp("+GW1NiOxIf007lQmx5Llwzr4wic=");



		usrRes=SecurityEJBService.UserValidate(usr);
		

		// TODO Auto-generated method stub
		System.out.println("Usuario Valido");
		System.out.println(usrRes.getValidUser());
		
		System.out.println("Datos perfil");
		System.out.println("id" + usrRes.getUsrprofile().getId_perfil());
		System.out.println("desc" + usrRes.getUsrprofile().getDesc_perfil());
				
		

		ProfileInTO profileInTO=new ProfileInTO();
		ProfileOutTO profileOutTO=new ProfileOutTO();
		
				

		profileOutTO=SecurityEJBService.GetUserProfile(usr);
		

		System.out.println(profileOutTO.getDesc_perfil());
		while (true){
			ProfileDetOutTO profileDetOutTO= new ProfileDetOutTO();
			List profileDetOutTOLst= new Vector();
			profileDetOutTOLst=profileOutTO.getProfile_det();
			//System.out.println(profileDetOutTO.getDesc_perfil_det());
			

			Iterator<ProfileDetOutTO> iterator = profileDetOutTOLst.iterator();
			while (iterator.hasNext()) {
				//System.out.println(iterator.next());
				ProfileDetOutTO profileDetOutTO1=(ProfileDetOutTO)iterator.next();


				System.out.println("->"+profileDetOutTO1.getDesc_perfil_det());
				if (profileDetOutTO1.getNodeDetail()!=null){

					//ProfileDetOutTO profileDetOutTO2=(ProfileDetOutTO)iterator.next();
					List profileDetOutTOLst1= profileDetOutTO1.getNodeDetail();
					Iterator<ProfileDetOutTO> iterator1 = profileDetOutTOLst1.iterator();


					try{
						while (iterator1.hasNext()) {
							ProfileDetOutTO profileDetOutTO3=(ProfileDetOutTO)iterator1.next();


							System.out.println("-->"+profileDetOutTO3.getDesc_perfil_det());
							System.out.println("-->"+profileDetOutTO3.getUrl_perfil_det());
							if (profileDetOutTO3.getNodeDetail()!=null){

								//ProfileDetOutTO profileDetOutTO2=(ProfileDetOutTO)iterator.next();
								List profileDetOutTOLst2= profileDetOutTO3.getNodeDetail();
								Iterator<ProfileDetOutTO> iterator2 = profileDetOutTOLst2.iterator();
								try{




									while (iterator2.hasNext()) {
										ProfileDetOutTO profileDetOutTO4=(ProfileDetOutTO)iterator2.next();
										System.out.println("--->"+profileDetOutTO4.getDesc_perfil_det());
										System.out.println("--->"+profileDetOutTO4.getUrl_perfil_det());
									}	





								}catch(Exception ex){
									

								}
								

							}
						}	

					}catch(Exception ex){
						

					}
					

				}
			}
			

			break;
		}
		// UserDAO userdao = new UserDAO();
		// System.out.println("Conexion");
		// userdao.getUserValid();
	}

	public static void user_mtto() throws Exception {

		int _result;
		UserTO parameters = new UserTO();

		// parameters.setUsersign();
		parameters.setNickname("admin");
		parameters.setUsername("Juan Carlos");
		parameters.setLastname("Castro");
		parameters.setPassword(PasswordService.getInstance().encrypt("jc"));
		parameters.setProfilecode(1);
		parameters.setLocked("N");
		parameters.setCusersign(0);

		// Agregar

		_result = SecurityEJBService.cat_users_mtto(parameters,
				Common.MTTOINSERT);

		// Actualizar
		/*
		 * parameters.setUsersign(11);
		 * parameters.setPassword(PasswordService.getInstance().encrypt("JC"));
		 * _result=SecurityEJBService.cat_users_mtto(parameters,
		 * Common.MTTOUPDATE);
		 */

		// Borrar
		/*
		 * parameters.setUsersign(13);
		 * _result=SecurityEJBService.cat_users_mtto(parameters,
		 * Common.MTTODELETE);
		 */

		System.out.println("luego de servicio");
		System.out.println(_result);

	}

	public static void getUser() {
		List resp = null;

		String name = null;
		String code = "art-001";

		resp = SecurityEJBService.getUser();

		Iterator<UserTO> iterator = resp.iterator();
		while (iterator.hasNext()) {
			UserTO user = (UserTO) iterator.next();
			System.out.println(user.getNickname() + " - "
					+ user.getUsername()+ " - "
					+ user.getUserdate());
		}
	}
}

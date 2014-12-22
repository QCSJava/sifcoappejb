package com.sifcoapp.test;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.sifcoapp.client.HelloWorldClient;
import com.sifcoapp.client.SecurityEJBClient;
import com.sifcoapp.objects.security.dao.UserDAO;
import com.sifcoapp.objects.security.to.ProfileDetOutTO;
import com.sifcoapp.objects.security.to.ProfileInTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;
import com.sifcoapp.objects.utilities.PasswordService;

public class SecurityTest {
	private static SecurityEJBClient SecurityEJBService=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if 		(SecurityEJBService==null)
			SecurityEJBService=new SecurityEJBClient();
		
		UserAppInTO usr = new UserAppInTO();
		UserAppOutTO usrRes = new UserAppOutTO();
		usr.setIdUserApp("admin");
		//usr.setPasswordUserApp("+GW1NiOxIf007lQmx5Llwzr4wic=");
		
		try {
			System.out.println("encrytp " + PasswordService.getInstance().encrypt("admin123"));
			usr.setPasswordUserApp(PasswordService.getInstance().encrypt("admin123"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		usrRes=SecurityEJBService.UserValidate(usr);
		
		// TODO Auto-generated method stub
		System.out.println("Usuario Valido");
		System.out.println(usrRes.getValidUser());
		
		ProfileInTO profileInTO=new ProfileInTO();
		ProfileOutTO profileOutTO=new ProfileOutTO();
		
				
		profileOutTO=SecurityEJBService.GetUserProfile(profileInTO);
		
		System.out.println(profileOutTO.getDesc_perfil());
		while (true){
			ProfileDetOutTO profileDetOutTO= new ProfileDetOutTO();
			List profileDetOutTOLst= new Vector();
			profileDetOutTOLst=profileOutTO.getProfile_det();
			System.out.println(profileDetOutTO.getDesc_perfil_det());
			
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
							if (profileDetOutTO3.getNodeDetail()!=null){
								//ProfileDetOutTO profileDetOutTO2=(ProfileDetOutTO)iterator.next();
								List profileDetOutTOLst2= profileDetOutTO3.getNodeDetail();
								Iterator<ProfileDetOutTO> iterator2 = profileDetOutTOLst2.iterator();
								try{
									while (iterator2.hasNext()) {
										ProfileDetOutTO profileDetOutTO4=(ProfileDetOutTO)iterator2.next();
										System.out.println("--->"+profileDetOutTO4.getDesc_perfil_det());
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
		//UserDAO userdao = new UserDAO();
		//System.out.println("Conexion");
		//userdao.getUserValid();
	}
}

package com.sifcoapp.bussinessLogic;

import javax.ejb.Remote;

import com.sifcoapp.objects.security.to.UserAppOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;

@Remote
public interface SecurityServicesRemote {
	public UserAppOutTO UserValidate(UserAppInTO usr);
}
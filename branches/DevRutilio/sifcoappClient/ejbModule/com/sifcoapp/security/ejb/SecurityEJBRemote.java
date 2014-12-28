package com.sifcoapp.security.ejb;
import javax.ejb.Remote;

import com.sifcoapp.objects.security.to.ProfileInTO;
import com.sifcoapp.objects.security.to.ProfileOutTO;
import com.sifcoapp.objects.security.to.UserAppInTO;
import com.sifcoapp.objects.security.to.UserAppOutTO;

@Remote
public interface SecurityEJBRemote {
	public UserAppOutTO UserValidate(UserAppInTO usr);
	public String SayHello(String hellomsg);
	public ProfileOutTO GetUserProfile(UserAppInTO usr);

}

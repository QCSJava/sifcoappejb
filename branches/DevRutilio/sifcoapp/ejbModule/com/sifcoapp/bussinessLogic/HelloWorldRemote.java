package com.sifcoapp.bussinessLogic;

import javax.ejb.Remote;

@Remote
public interface HelloWorldRemote {
	public String SayHello(String hellomsg);
	
}

package com.sifcoapp.bussinessLogic;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class HelloWorld
 */
@Stateless
public class HelloWorld implements HelloWorldRemote {

    /**
     * Default constructor. 
     */
    public HelloWorld() {
        // TODO Auto-generated constructor stub
    	//@Remote (HelloWorldRemote.class)
    	System.out.println("hola mundo bean");
    }

	public String SayHello(String hellomsg) {
		// TODO Auto-generated method stub
		hellomsg=hellomsg+"EJB";
		System.out.println(hellomsg);
		return hellomsg;
	}

}

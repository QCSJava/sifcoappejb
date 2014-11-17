package com.sifcoapp.client;
import javax.naming.Context;
import javax.naming.NamingException;


import com.sifcoapp.bussinessLogic.HelloWorldRemote;
import com.sifcoapp.client.interfaces.HelloWorldClientInterfaces;
//import com.sifcoapp.client.interfaces.HelloWorldRemote;
import com.sifcoapp.clientutility.ClientUtility;
public class HelloWorldClient implements HelloWorldClientInterfaces{
	private static final String LOOKUP_STRING = "java:global/sifcoappEAR/sifcoapp/HelloWorld!com.sifcoapp.bussinessLogic.HelloWorldRemote";
	private static HelloWorldRemote bean;
	private static Context context = null;
	public HelloWorldClient(){
		       
        //2. Lookup and cast
		try {
			context = ClientUtility.getInitialContext();
			bean = (HelloWorldRemote)context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public static void main(String[] args) {
    	//HelloWorldRemote bean = doLookup();
    	//.out.println(HelloWorld.class.getSimpleName());
         //3. Call business logic
        System.out.println(bean.SayHello("hola youXXX"));
    }
 
    private static HelloWorldRemote doLookup(){
        Context context = null;
        //HelloWorldRemote bean = null;
        try{
                    //1. Obtaining Context
            context = ClientUtility.getInitialContext();       
                //2. Lookup and cast
            bean = (HelloWorldRemote)context.lookup(LOOKUP_STRING);
        }catch(NamingException e){
            e.printStackTrace();
        }
        return bean;
    }

	
	public String SayHello(String hellomsg) {
		// TODO Auto-generated method stub
		String message;
		
		message= bean.SayHello(hellomsg);
		
		return message;
	}
}

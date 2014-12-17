/**
 * 
 */
package com.sifcoapp.clientutility;

	import java.util.Properties;
	import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	

	/**
	 * @author ri00642
	 *
	 */
	public class ClientUtility {
	    /*location of JBoss JNDI Service provider the client will use.
	    It should be URL string.*/

	    private static final String PROVIDER_HOST = "localhost";
	    private static final String PROVIDER_PORT = "3700";
	     
	    /*Factory that creates initial context objects.
	    fully qualified class name. */
	    private static final String INITIAL_CONTEXT_FACTORY =
	            "com.sun.enterprise.naming.SerialInitContextFactory";
	 
	    private static Context initialContext;
	     
	    public static Context getInitialContext() throws NamingException
	    {
	        if (initialContext == null) {
	            
	            Properties props = new Properties();
	            props.setProperty("java.naming.factory.initial",INITIAL_CONTEXT_FACTORY);
	            props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");  
	            props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl"); 
	            props.setProperty("org.omg.CORBA.ORBInitialHost", PROVIDER_HOST); // default!
	            props.setProperty("org.omg.CORBA.ORBInitialPort", PROVIDER_PORT); // default!
	            InitialContext context = new InitialContext(props);
	            
	            initialContext = new InitialContext(props);
	        }
	        return initialContext;
	    }
	}


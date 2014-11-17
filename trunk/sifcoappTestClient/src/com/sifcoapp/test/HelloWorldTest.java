/**
 * 
 */
package com.sifcoapp.test;

import com.sifcoapp.client.HelloWorldClient;
/**
 * @author ri00642
 *
 */
public class HelloWorldTest {

	/**
	 * @param args
	 */
	private static HelloWorldClient HelloWorldService=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if 		(HelloWorldService==null)
			HelloWorldService=new HelloWorldClient();
		System.out.println(HelloWorldService.SayHello("say helloooo"));
		
		
	}

}

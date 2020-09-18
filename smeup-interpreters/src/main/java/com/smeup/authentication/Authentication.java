package com.smeup.authentication;

import java.io.IOException;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;

public class Authentication {
	
	private String dns;
	private String userId;
	private String password;

	
	public Authentication(String dns, String userId, String password) 
	{
		this.dns = dns; 
		this.userId = userId;
		this.password = password;
	}
	
	public boolean authenticate() throws AS400SecurityException, IOException
	{
		AS400 system = new AS400(dns);
		
		boolean auth = system.authenticate(userId, password);
		
		return auth;
	}
	
}
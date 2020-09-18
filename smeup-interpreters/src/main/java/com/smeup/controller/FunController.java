package com.smeup.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.smeup.interpreters.InterpreterCaller;
import com.smeup.mu.runtime.InterpreterException;
import com.smeup.session.Kokos1Session;
import com.smeup.smeup.connector.fun.FUN;
import com.smeup.smeup.connector.fun.FUNParser;
import com.smeup.yamlReader.*;

import it.smea.transformer.smeupobj.SmeupObject;


@Controller
public class FunController {
	
	private static final String AUTH_SERVER_DNS = "srvlab01.smeup.com";
	
	@RequestMapping(
			value = "/fun",
			method = RequestMethod.POST,
			consumes = "application/x-www-form-urlencoded"
			)
	public @ResponseBody String recall(
			@RequestParam("fun") String fun,
			@Context HttpServletRequest request) throws InterpreterException
	{
		String response = "";
		
		
		
		//if(request.getHeader("KOKOS1_AUTH").equals(Session.TOKEN))
		//{
			//setSession(user, device, env, server, sessionId);
		    
		    //DALLA FUN RICAVO IL NOME DEL PROGRAMMA DA CHIAMARE
		    FUNParser parser = new FUNParser();		
			FUN funObj = null;
			try {
				funObj = parser.parse(fun);
				
			} catch (Exception exc) {
				throw new InterpreterException("<Error>Wrong syntax in fun</Error>");
			}	
			
			String service = funObj.getFunzione();
			
		    // LEGGO IL REGISTRO DEI PROGRAMMI (.YML) E RICAVO L'INTERPRETE DEL PROGRAMMA RICAVATO
			FindInterpreter findInt= new FindInterpreter();	
			String interpreter = findInt.find(service);
			
			//RICHIAMO IL PROGRAMMA SULL'INTERPRETE SPECIFICO
			if(interpreter.equals(""))
			{
				response = "<Error>Program " + service + " not register in register.yml</Error>";
			}else {
				InterpreterCaller caller = new InterpreterCaller(funObj, interpreter);
				
				//RITORNA XML DAL PROGRAMA CHIAMATO
				try {
					response = caller.call();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				System.out.println("response : " + response);
			}			
//		}else {
//			response = "<Error>KOKOS1_TOKEN is wrong</Error>";
//		}
//		
		return response;
		
	}
	
	@RequestMapping(
			value = "/authentication",
			method = RequestMethod.POST,
			consumes = "application/x-www-form-urlencoded"
			)
	public @ResponseBody String authentication(
			@RequestParam("user") String user, 
			@RequestParam("pwd") String pwd,
			@RequestParam("env") String env,
			@RequestParam("server") String server,
			@RequestParam("device") String device,
			@RequestParam("sessionId") String sessionId,		
			@Context HttpServletRequest request) throws InterpreterException
	{
		if(executeLogin(user, pwd))
		{
			String kokos1_token = "fdnnwdkdjbwicbwwecnwi";
			request.getSession().setAttribute("KOKOS1_SESSION", new Kokos1Session(user, device, env, server, sessionId, kokos1_token));			
		}else {
			return "<Error>Error on authentication</Error>";
		}
		return null;
	}
	
	private boolean executeLogin(String userId, String pwd) {
    	
    	boolean login = false;
    	
	    try {
	        AS400 system = new AS400(AUTH_SERVER_DNS);			
	        login = system.authenticate(userId, pwd);
	        
		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    return login;
	}
	
	/*private String generateToken(int length)
	{
		byte[] array = new byte[length];
	    new Random().nextBytes(array);
	    //return new String(array, Charset.forName("UTF-8"));
	    return new String(array);
	}*/
	
	
}

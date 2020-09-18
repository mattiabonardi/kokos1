package com.smeup.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.as400.access.AS400SecurityException;
import com.smeup.authentication.Authentication;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController {
	@RequestMapping(method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody String auth(@RequestParam(name = "userId") String userId, @RequestParam(name = "psw") String psw)
	{
		String response = "";
		String dns = "srvlab01.smeup.com";
		
		Authentication auth = new Authentication(dns, userId, psw);
		
		try {
			if(auth.authenticate()==true)
			{
				response = "UTENTE AUTENTICATO";
			}else {
				response = "UTENTE NON AUTENTICATO";
			}
		} catch (AS400SecurityException e) {
			response = "UTENTE NON AUTENTICATO";
			e.printStackTrace();
		} catch (IOException e) {
			response = "UTENTE NON AUTENTICATO";
			e.printStackTrace();
		}
		
		return response;
	}
	
}

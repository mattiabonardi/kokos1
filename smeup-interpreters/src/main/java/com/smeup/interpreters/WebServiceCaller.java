package com.smeup.interpreters;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.smeup.smeup.connector.fun.FUN;

import it.smea.transformer.smeupobj.SmeupObject;

public class WebServiceCaller {
	private static final String WS_HOST = "localhost/webserver/";
	
	public WebServiceCaller() {
		
	}
	
	public String call(FUN fun)
	{
		String response = null;
		
		String params = "";
		String funzione = "";
		
		SmeupObject[] objs = fun.getSmeupObjs();
		
		funzione = objs[1].getCodice().toString()+".php";
		params = objs[2].getCodice().toString();
		
		if(!funzione.equals(""))
		{
			response = sendPost(funzione, params);
		}
		
		System.out.println("RISPOSTA DA PHP : " + response);
		
		return response;
		
		
	}
	
	private String sendPost(String funzione, String params)
	{
		String xml = null;
		
		WebTarget target = ClientBuilder.newClient().target(WS_HOST+funzione);		
		//Builder request =  target.request(MediaType.TEXT_HTML);
		Builder request =  target.request();
		
		Form form = new Form();
		form.param("p", params);
		
		Response response = request.post(Entity.form(form), Response.class);
		
		xml = response.readEntity(String.class);
		
		response.close();
		
		return xml;
	}
	
	

}

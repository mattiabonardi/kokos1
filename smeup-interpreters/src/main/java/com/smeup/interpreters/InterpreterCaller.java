package com.smeup.interpreters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smeup.javaServices.ScriptDebugService;
import com.smeup.javaServices.SpotlightService;
import com.smeup.sch.interpreter.entity.Fun;
import com.smeup.smeup.connector.fun.FUN;

public class InterpreterCaller {
	private static final Logger LOGGER = LoggerFactory.getLogger(InterpreterCaller.class.getName());
	
	private FUN fun;
	private String interpreter;
	
	public InterpreterCaller(FUN fun, String interpreter)
	{
		this.fun = fun;
		this.interpreter = interpreter;
	}
	
	public String call() throws IOException
	{
		String response = "";
		
		switch(interpreter)
		{
		case "jariko" :
		{
			JarikoCaller jariko = new JarikoCaller();
			response = jariko.call(fun);
		}break;
		
		case "WebService" :
		{
			WebServiceCaller wsCaller = new WebServiceCaller();
			response = wsCaller.call(fun);
		}break;
		
		case "*SCO" :
		{
			ScpSchInterpreterCaller scpSchCaller = new ScpSchInterpreterCaller();
			response = scpSchCaller.call(fun);
		}break;
		
		case "LOSER_09" :
		{
			ScpLayInterpreterCaller scpLayCaller = new ScpLayInterpreterCaller();
			response = scpLayCaller.call(fun);
		}break;
		
		case "B£_063_02" :
		{
			SpotlightService ser = new SpotlightService();
			response = ser.call(fun);
		}break;
		
		case "B£SER_22" :
		{
			if(fun.getMetodo().equals("DOC.SEZ"))
			{
				ScriptDebugService scriptDebug = new ScriptDebugService();
				response = scriptDebug.call(fun);
			}else {
				response = "";
			}
		}break;

		default: 
			{
				LOGGER.error("servizio " + fun.getFunzione() + " non esistente");
				response = "<Error>service " + fun.getFunzione() + " not found in system</Error>";
			}
		}
		
		return response;
	}
}

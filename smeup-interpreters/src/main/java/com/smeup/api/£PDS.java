package com.smeup.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.smeup.rpgparser.interpreter.*;

public class £PDS implements Program{

	@Override
	public List<Value> execute(SystemInterface arg0, LinkedHashMap<String, Value> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProgramParam> params() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*private String user;
	private String device;
	private String env;
	private String server;
	private String sessionId;
	
	private boolean varying = false;
	
	public £PDS()
	{
		System.out.println("Create a £PDS program instance");
	}
	
	public List<Value> execute(SystemInterface systemInterface, LinkedHashMap<String, Value> parms) {
		cleanVariables();
		
		System.out.println("user " + Session.USER);
		System.out.println("dev " + Session.DEVICE);
		System.out.println("env " + Session.ENV);
		System.out.println("ser " + Session.SERVER);
		System.out.println("sessionId " + Session.SESSIONID);
		
		ArrayList<Value> arrayListResponse = new ArrayList<Value>();	
		arrayListResponse.add(new StringValue(Session.USER, varying));
		arrayListResponse.add(new StringValue(Session.DEVICE, varying));
		arrayListResponse.add(new StringValue(Session.ENV, varying));
		arrayListResponse.add(new StringValue(Session.SERVER, varying));
		arrayListResponse.add(new StringValue(Session.SESSIONID, varying));
		
	
		return arrayListResponse;
	}

	public List<ProgramParam> params() {
		return new ArrayList<ProgramParam>() {{
			add(new ProgramParam("USER", new StringType(50, varying)));
			add(new ProgramParam("DEVICE", new StringType(50, varying)));
			add(new ProgramParam("ENV", new StringType(50, varying)));
			add(new ProgramParam("SERVER", new StringType(50, varying)));
			add(new ProgramParam("SESSIONID", new StringType(50, varying)));
			}};
		}
	
	private void cleanVariables() {
		user = "";
		device = "";
		env = "";
		server = "";
		sessionId = "";
	}
	
	
	*/
}
		



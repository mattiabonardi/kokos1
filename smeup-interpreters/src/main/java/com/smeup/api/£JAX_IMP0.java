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
import com.smeup.smeup.connector.fun.FUN;
import com.smeup.smeup.connector.fun.FUNParser;

public class £JAX_IMP0 implements Program{

	//VARIABILI DI OUTPUT
	private String £JAXXML; //XML
	
	//VARIABILI DI CLASSE
	private LinkedHashMap<String, Value> additionalParams = new LinkedHashMap<String, Value>();
	private FUN funObj;
	private boolean varying = false;
	
	public £JAX_IMP0()
	{
	}
	
	public List<Value> execute(SystemInterface systemInterface, LinkedHashMap<String, Value> parms) {
		
		if(additionalParams.size() > 0) {
			parms.putAll(additionalParams);
		}
		
		Value value = parms.get("FUN");
		FUNParser parser = new FUNParser();		
		funObj = parser.parse(value.asString().getValue());
		String cmp = funObj.getProgramma();
				
		switch(cmp)
		{
			case "EXB" : 
			{
				buildEXB();
			}break;
			case "TRE" :
			{
				buildTRE();
			}break;
			case "EDT" :
			{
				buildTRE();
			}break;
			case "FBK" :
			{
				£JAXXML = "";
			}
		}
		
		ArrayList<Value> arrayListResponse = new ArrayList<Value>();	
		arrayListResponse.add(new StringValue(£JAXXML, varying));
	
		return arrayListResponse;
	}


	public List<ProgramParam> params() {
		return new ArrayList<ProgramParam>() {{
			add(new ProgramParam("£JAXXML", new StringType(1000, varying)));
			}};
		}
	
	private void buildEXB() {
		
		£JAXXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<UiSmeup Testo=\" - \">"
				+ "<Service Titolo1=\"\" Titolo2=\"\" "
				+ "Funzione=\"" + funObj.toString() + "\" "
				+ "Servizio=\"" + funObj.getFunzione() + "\" "
				+ "TSep=\".\" DSep=\",\" "
				+ "IdFun=\"0000000000000\" NumSes=\"000000\"/>";
	}
	
	private void buildTRE() {
		
		£JAXXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<Base Testo=\" - \">" 
				+ "<Service Titolo1=\"\" Titolo2=\"\"" 
				+ "Funzione=\"" + funObj.toString() + "\" "
				+ "Servizio=\"" + funObj.getFunzione() + "\" "
				+ "TSep=\".\" DSep=\",\" "
				+ "IdFun=\"0000000000000\" NumSes=\"000000\"/>";
	}

	public void setAdditionalParams(LinkedHashMap<String, Value> additionalParams) {
		this.additionalParams = additionalParams;
	}
	
}
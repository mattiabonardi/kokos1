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

//$JAX_AGRI - GRIGLIA XML EXB

public class £JAX_AGRI implements Program{
	//VARIABILI DI INPUT
	private String £JAXSWK; //SCHIERA DEFINIZIONE COLONNE 

	//VARIABILI DI OUTPUT
	private String £JAXGRI; //XML
	
	//VARIABILI DI CLASSE
	private boolean varying = false;
	
	private String codice;
	private String text;
	private String length;
	private String io;
	private String ogg;
	
	public £JAX_AGRI()
	{
		System.out.println("Create a £JAX_AGRI program instance");
	}
	
	public List<Value> execute(SystemInterface systemInterface, LinkedHashMap<String, Value> parms) {
		cleanVariables();
		
		Value value = parms.get("£JAXSWK");
		System.out.println(value.asString().getValue());
		
		ArrayList<Value> arrayListResponse = new ArrayList<Value>();	
		arrayListResponse.add(new StringValue(£JAXSWK, varying));
		arrayListResponse.add(new StringValue(£JAXGRI, varying));

	
		return arrayListResponse;
	}


	public List<ProgramParam> params() {
		return new ArrayList<ProgramParam>() {{
			add(new ProgramParam("£JAXSWK", new StringType(5000, varying)));
			add(new ProgramParam("£JAXGRI", new StringType(50000, varying)));
			}};
		}
	
	private void cleanVariables()
	{
		£JAXSWK = ""; 
		£JAXGRI = ""; 
	}
	
	private void getValues()
	{
		//KLAB                                                      
		//** SWK001 DddddddddddddddddddddddddddddOooooooooooooooooooooINnnn R	
		codice = £JAXSWK.substring(0, 10).trim();
		text = £JAXSWK.substring(10, 39).trim();
		ogg = £JAXSWK.substring(39, 60).trim();
		io = £JAXSWK.substring(60, 61).trim();
		length = £JAXSWK.substring(61, 66).trim();
	}
	
	private void buildXML()
	{
		
	}
	
}
		



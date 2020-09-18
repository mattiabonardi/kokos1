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

//$G80 - FUNZIONI SU IFT
//FUNZIONI API
//LETTURA ELENCO CARTELLE 
//FU = READ | ME = *DIR | PH = path
//			 LETTURA FILE
public class £G80 implements Program{
	//VARIABILI DI INPUT
	private String £G80FU; //FUNZIONE - 
	private String £G80ME; //METODO (*DIR directory | *STMF file)
	private String £G80PH; //PATH
	private String £G80UN; //CODIFICA CARATTERI (SCRITTURA)
	
	//VARIABILI DI OUTPUT
	private String £G80OG; //PATH
	private String £G80CO; //CONTENTUTO
	private String £G8035; //INDICATORE DI ERRORE -> 1 SE ERRORE
	private String £G80SZ; //DIMENSIONE FILE IN BYTE
	
	//VARIABILI DI CLASSE
	private int puntatore;
	private boolean varying = false;
	private String[] fileName;
	
	public £G80()
	{
		System.out.println("Create a £G80 program instance");
		£G80FU = ""; 
		£G80ME = ""; 
		£G80PH = ""; 
		£G80UN = ""; 
		£G80OG = ""; 
		£G80CO = "";
		£G80SZ = "";
		£G8035 = "0";
		puntatore = 0;
	}
	
	public List<Value> execute(SystemInterface systemInterface, LinkedHashMap<String, Value> parms) {
		cleanVariables();
		for (Map.Entry<String, ? extends Value> entry : parms.entrySet()) {
			switch(entry.getKey())
			{
			case "£G80FU" :
			{
				this.£G80FU = ((StringValue)entry.getValue()).getValue().trim();
			}break;
			case "£G80ME" : 
			{
				this.£G80ME = ((StringValue)entry.getValue()).getValue().trim();
			}break;
			case "£G80PH" :
			{
				this.£G80PH = ((StringValue)entry.getValue()).getValue().trim();
			}break;
			case "£G80UN" :
			{
				this.£G80UN = ((StringValue)entry.getValue()).getValue().trim();
			}
			case "£G80CO" :
			{
				this.£G80CO = ((StringValue)entry.getValue()).getValue().trim();
			}	
			}
		}
		
		fixPath();
		
		switch(£G80FU)
		{
		case "" :
		{
			check();
		}break;
		case "READ" :
		{
			read();
		}break;
		case "DELETE" :
		{
			delete();
		}break;
		case "WRITE" :
		{
			write();
		}break;
		case "BINARY" :
		{
			binary();
		}break;
		default : £G8035 = "1";
		}
		
		
		ArrayList<Value> arrayListResponse = new ArrayList<Value>();	
		arrayListResponse.add(new StringValue(£G80FU, varying));
		arrayListResponse.add(new StringValue(£G80ME, varying));
		arrayListResponse.add(new StringValue(£G80PH, varying));
		arrayListResponse.add(new StringValue(£G80OG, varying));
		arrayListResponse.add(new StringValue(£G80CO, varying));
		arrayListResponse.add(new StringValue(£G80SZ, varying));
		arrayListResponse.add(new StringValue(£G8035, varying));
	
		return arrayListResponse;
	}


	public List<ProgramParam> params() {
		return new ArrayList<ProgramParam>() {{
			add(new ProgramParam("£G80FU", new StringType(10, varying)));
			add(new ProgramParam("£G80ME", new StringType(10, varying)));
			add(new ProgramParam("£G80PH", new StringType(500, varying)));
			add(new ProgramParam("£G80OG", new StringType(500, varying)));
			add(new ProgramParam("£G80CO", new StringType(5000, varying)));
			add(new ProgramParam("£G80SZ", new StringType(50, varying)));
			add(new ProgramParam("£G8035", new StringType(1, varying)));
			}};
		}
	
	private void cleanVariables()
	{
		£G80FU = ""; 
		£G80ME = ""; 
		£G80PH = ""; 
		£G80UN = ""; 
		£G80OG = ""; 
		£G80CO = "";
		£G80SZ = "";
		£G8035 = ""; 
	}
	
	private void fixPath()
	{
		if(£G80PH.trim()!="")
			£G80PH = £G80PH.replace("\\", "/");
	}
		
	//FUNZIONE READ
	private void read()
	{	
		File file = new File(£G80PH);
		switch(£G80ME)
		{
		
		//LETTURA CONTENUTO FILE
		case "*STMF" :
		{
			if(file.exists())
			{
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader(file));
					
					String s;
					try {
						s = reader.readLine();
						
						//aggiungo ad un ArrayList tutte le righe lette
						  while(s!=null){
							  £G80CO += s + "\r\n";
							  s = reader.readLine();
						  }
						  
						  reader.close();
						  £G80SZ = "" + file.length();
						  £G80OG = £G80PH;
						  £G8035 = "0";
					} catch (IOException e) {
						£G8035 = "1";
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					£G8035 = "1";
					e.printStackTrace();
				}
			}
		}break;
		
		//LETTURA CONTENUTO DIRECTORY
		case "*DIR" :
		{
			if(puntatore==0)
			{
				fileName = file.list();
			}
			
			if(puntatore<fileName.length)
			{
				£G80CO = fileName[puntatore];
				£G8035 = "0";
				puntatore++;
			}else {
				£G8035 = "1";
				puntatore = 0;
			}
				    
		}break;
	
	default : £G8035 = "1";
	}
		
	}
	
	//FUNZIONE DELETE
	private void delete() {	
		
		//DELETE FILE, DIRECTORY	

		File file = new File(£G80PH);

		if (!file.exists())
			£G8035 = "1";	

		if (!file.canWrite())
			£G8035 = "1";	

		boolean success = file.delete();

		if (!success)
		{
			£G8035 = "1";
		}else {
			£G8035 = "0";
		}
	}
	
	//FUNZIONE CHECK
	private void check()
	{
		File file = new File(£G80PH);
		
		if(!file.exists())
		{
			£G8035 = "1";
		}else {
			£G8035 = "0";
		}
		
		£G80OG = £G80PH;
	}
	
	//FUNZIONE WRITE
	private void write() 
	{	

		File file = new File(£G80PH);
		
		switch(£G80ME)
		{
		
		//SCRITTURA SU FILE
		case "*STMF" :
		{
			if(!file.exists())
			{
				try{
					file.createNewFile();
					
					FileWriter fw = new FileWriter(file);
					fw.write(£G80CO);
					fw.flush();
					fw.close();
					
					£G8035 = "0";
					£G80SZ = "" + file.length();
					£G80OG = £G80PH;
				}
				catch(IOException e) {
					£G8035 = "1";
				}
			}else {
				try{
					file.createNewFile();
					
					FileWriter fw = new FileWriter(file);
					fw.write(£G80CO);
					fw.flush();
					fw.close();
					
					£G8035 = "0";
					£G80SZ = "" + file.length();
					£G80OG = £G80PH;
				}
				catch(IOException e) {
					£G8035 = "1";
				}
			}
			
		}break;
		
		case "*DIR" :
		{
			if(!file.exists())
			{
				try {
					file.createNewFile();
				} catch (IOException e) {
					£G8035 = "1";
				}
			}else {
				£G8035 = "1";
			}
		}break;
		
		default : £G8035 = "1";
		
		}
	}
	
	//FUNZIONE BINARY - salva il file da contenuto in binario
	private void binary()
	{
		byte[] decoded = Base64.getDecoder().decode(£G80CO);
		try {
			£G80CO = new String(decoded, "UTF-8");
			
			File file = new File(£G80PH);
			
			try {
				file.createNewFile();
				
				FileWriter fw = new FileWriter(file);
				fw.write(£G80CO);
				fw.flush();
				fw.close();
				
				£G8035 = "0";
				£G80SZ = "" + file.length();
				£G80OG = £G80PH;
			} catch (IOException e) {
				£G8035 = "1";
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e) {
			£G8035 = "1";
			e.printStackTrace();
		}
		
		
	}
}
		



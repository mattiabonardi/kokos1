package com.smeup.javaServices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.smeup.config.Configuration;
import com.smeup.smeup.connector.fun.FUN;

import it.smea.transformer.smeupobj.SmeupObject;

public class ScriptDebugService {
	
	public ScriptDebugService()
	{
		
	}
	
	public String call(FUN fun) throws IOException
	{
		String response = "";
		String cdata = "";
		
		SmeupObject[] objs = fun.getSmeupObjs();
		String fileName = objs[0].getCodice();
		
		File file = new File(Configuration.SCPSCH_SOURCE_DIR + fileName + ".sch");
		  
		BufferedReader reader = new BufferedReader(new FileReader(file));
			 
		String s = reader.readLine();

		  //aggiungo ad un ArrayList tutte le righe lette
		  while(s!=null){
			  cdata += s + "\r\n";
			  s = reader.readLine();
		  }
		  
		  response = buildEdtXml(cdata, fileName, fun);
		
		return response;
		
	}
	
	private String buildEdtXml(String content, String fileName, FUN fun)
	 {
		 String xml ="";
		 
		 xml =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Base Testo=\"\"> <Service Titolo1=\"\" Titolo2=\"\" Funzione=\"" + fun.toString() + "\" Servizio=\"" + fun.getFunzione() + "\" TSep=\".\" DSep=\",\" IdFun=\"0000000000001\" NumSes=\"000001\"/>" + 
		 		"<Header><Livello Caratteristiche=\"A01\"/></Header>" + 
		 		"<Key><K1 Tipo=\"MB\" Parametro=\"SCP_SCH\" Codice=\""+fileName+"\" Testo=\"\" Obb=\"3\" Mod=\"1\"/>"+
		 		"<K2 Tipo=\"OJ\" Parametro=\"*LIB\" Codice=\"OPNLIB\" Testo=\"\" Obb=\" \" Mod=\"2\"/>" + 
		 		"</Key>" + 
		 		"<Setup>" + 
		 		"<Program>" + 
		 		"<EDT Tipo=\"FIX\" Lung=\"100\" NoMod=\"1\" Control=\"EDT_SCH\"/>" + 
		 		"</Program>" + 
		 		"</Setup>" +
		 		"<Contenuto><![CDATA[" + content + "]]> </Contenuto>"+
		 		"<Messaggi><Esito Stato=\"OK\"/></Messaggi></Base>";
		 
		 return xml;
	 }

}

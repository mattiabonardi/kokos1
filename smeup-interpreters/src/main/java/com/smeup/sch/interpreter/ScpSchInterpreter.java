package com.smeup.sch.interpreter;

import java.util.ArrayList;

import com.smeup.sch.interpreter.entity.Din;
import com.smeup.sch.interpreter.entity.Fun;
import com.smeup.sch.interpreter.entity.LayoutSezione;
import com.smeup.sch.interpreter.entity.Ogg;
import com.smeup.sch.interpreter.entity.SetupComponent;
import com.smeup.sch.interpreter.entity.Sez;
import com.smeup.sch.interpreter.entity.Sub;
import com.smeup.sch.interpreter.entity.Var;
import com.smeup.smeup.connector.fun.FUN;

public class ScpSchInterpreter {
	
	ArrayList<String> closeTags = new ArrayList<String>();
	
	public ScpSchInterpreter()
	{		
	}
	
	public String interpretate(ArrayList<String> lines, String fileName, FUN funzione)
	{	
		Sez sez = new Sez();
		Sub sub = new Sub();
   	 	Fun fun = new Fun();
   	 	Ogg ogg = new Ogg();
   	 	Din din = new Din();
   	 	Var var = new Var();
   	 	SetupComponent setup = new SetupComponent();
   	 	
   	 	boolean lastSez = false;
   	 	
   	 	//aggiungo una riga vuota in fondo in modo da non avere problemi con funzioni che controllano la riga successiva
   	 	lines.add(" ");
		
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xml += "<UiSmeup>";
		xml += "<Service Funzione=\""+funzione.toString()+"\" />";
		
		//<Service Titolo1="Utente Applicativo" Titolo2="${USERDEC}"
		//Funzione="${FUN}" Servizio="JATRE_18C" TSep="." DSep="," IdFun="0628105725005" NumSes="876437" />
	 	
		try {

			int i = 0;
			boolean ctrlISch = true;
	        while(i<lines.size() && ctrlISch==true)
	        {
	        	lines.set(i, lines.get(i).trim());
        		
	        	//controllo se ci sono delle istruzioni
	        	if(lines.get(i).startsWith("::") && lines.get(i).length()>4)
        		{
	        		System.out.println("righe " + lines.get(i));
	        		
	        		//controllo se ci sono variabili &OG....
	        		if(lines.get(i).contains("&OG.") || lines.get(i).contains("&PA.") || lines.get(i).contains("&IN."))
	        		{
	        			lines.set(i, var.change(lines.get(i), funzione));
	        		}
	        		
	        		String[] app = lines.get(i).split(" ");
        			 
	        		//controllo le istruzioni dopo i ::
	        		switch(app[0].substring(app[0].indexOf(".")-1, app[0].indexOf(".")+4))
			        {
				    	case "G.SEZ" :
				        {
				        	//chiudo i tag della sezione precedente, se è la prima sezione trovata, non farà nulla
				        	xml += printCloseTags();
				        	
				       	 	xml += sez.buildXml(lines.get(i));
				        	addCloseTags("</Sez>");
				        	
				        	//ogni volta che trova una sezione, viene memorizzata 
				        	lastSez = true;
				        	 
				         }break;
				         
				         case "G.SUB" :
				         {
				        	 xml += sub.buildXml(lines.get(i));
				        	 
				        	 addCloseTags("</Sub>");
				        	 addCloseTags("</"+sub.getComponent()+">");
				        	 
				         }break;
				         
				         case "D.FUN" :
				         {
				        	 xml += fun.buildXml(lines.get(i));
				        	 addCloseTags("</Dati>");
				         }break;
				         
				         case "G.SET" :
				         {
				        	 xml += setup.buildXml(lines.get(i), sub.getComponent());
				         }break;
				         
				         case "D.OGG" :
				         {
				        	 //algoritmo di annidamento <Dati><Oggetto /><Oggetto />...</Dati>
				        	 if(!lines.get(i-1).contains("::D.OGG")) 
				        	 {
			        			 xml += "<Dati><Base>";
			        			 
			        			 if(lines.get(i+1).contains("::D.OGG"))
			        			 {
			        				 xml += ogg.buildXml(lines.get(i));
			        			 }else {
			        				 xml += ogg.buildXml(lines.get(i));
			        				 xml += "</Base></Dati>";
			        			 }
				        	 }else {
				        		 if(lines.get(i+1).contains("::D.OGG"))
			        			 {
			        				 xml += ogg.buildXml(lines.get(i));
			        			 }else {
			        				 xml += ogg.buildXml(lines.get(i));
			        				 xml += "</Base></Dati>";
			        			 }
				        	 }
			        		
				         }break;
				         
				         case "G.DIN" :
				         {
				        	//algoritmo di annidamento <Targets><Target /><Target />...</Targets>
				        	 if(!lines.get(i-1).contains("::G.DIN")) //è IL PR
				        	 {
			        			 xml += "<Targets>";
			        			 
			        			 if(lines.get(i+1).contains("::G.DIN"))
			        			 {
			        				 xml += din.buildXml(lines.get(i));
			        			 }else {
			        				 xml += din.buildXml(lines.get(i));
			        				 xml += "</Targets>";
			        			 }
				        	 }else {
				        		 if(lines.get(i+1).contains("::G.DIN"))
			        			 {
			        				 xml += din.buildXml(lines.get(i));
			        			 }else {
			        				 xml += din.buildXml(lines.get(i));
			        				 xml += "</Targets>";
			        			 }
				        	 }
			        		
				         }break;
				         
				         case "D.SCH" : 
				         {
				        	 xml += fun.buildSubSchXml(fileName, lines.get(i));
				         }break;
				         
				         case "I.SCH" :
				         {
				        	 //interrompo l'interprete
				        	 ctrlISch = false;
				        	 
				        	 //cancello tutte le righe dopo
				        	 int x = i;
				        	 while(x<lines.size())
				        	 {
				        		 lines.remove(x);
				        	 }
				        	 
				         }break;
	
			         }
		         }else {
		        	 //rimuovo righe non conteneti istruzioni
	        		 lines.remove(i);
	        		 i--;
		         }
        		 
	        		
	        	 i++;
	         }		     
	         
	        //chiudo i tag dell'ultima sezione
	         if(lastSez==true)
	         {
	        	 xml += printCloseTags();
	        	 
	         }else {
	        	 xml = "Sinstassi script di scheda errata";
	         }        
	         
	         String[] temp = lines.toArray(new String[0]);
	         
	         LayoutSezione layoutSez = new LayoutSezione();
	         xml += layoutSez.buildXml(temp, fileName);
	         
	         xml += "</UiSmeup>";
	         
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

		return xml;
	}

	private void addCloseTags(String tag)
	{
		closeTags.add(tag);
	}
	
	public String printCloseTags()
	{
		String xml="";
		
		int i = closeTags.size()-1;
		
		while(i>-1)
		{
			xml += closeTags.get(i);
			i--;
		}
		
		closeTags.clear(); 

		return xml;
	}

}

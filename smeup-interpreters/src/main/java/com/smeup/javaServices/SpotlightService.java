package com.smeup.javaServices;

import com.smeup.smeup.connector.fun.FUN;

//SERVIZIO PER LE CHIAMATE IN SPOTLIGTH
//SERVIZIO B£_063_02
public class SpotlightService {
	
	public SpotlightService()
	{
		
	}
	
	public String call(FUN fun)
	{
		String xml = "";
		String input = fun.getInput().substring(4, fun.getInput().length()-1);
		
		if(input.contains("F("))
		{
			xml = "<?xml version=\"1.0\" encoding=\"WINDOWS-1252\"?>"
				 + "<Base Testo=\" Oggetto - \">;"
				 + "<Service Titolo1=\"\" Titolo2=\"Oggetto\";"
				 + "Funzione=\"" + fun.toString() + " "
				 + " Servizio=\"B£_063_02\" TSep=\".\" DSep=\",\" IdFun=\"0000000000001\" NumSes=\"000001\"/>"
				 + "<Header>"
				 + "<Livello Caratteristiche=\"201\"/>"
				 + "<Livello Caratteristiche=\"A01\"/> "  
				 + "</Header>"
				 + "<Oggetto Nome=\"\" Tipo=\"\" Parametro=\"\" Codice=\".\" Testo=\"...\" "
				 + "Exec=\"" + input + "\" Fld=\"\" Leaf=\"\"/>" 
				 + "</Base>";
		}else {
			if(input.contains("SCH ") || input.contains("sch "))
			{
				String funzione = "F(EXD;*SCO;) 2(MB;SCP_SCH;" + input.substring(4) + ")";
				
				xml = "<?xml version=\"1.0\" encoding=\"WINDOWS-1252\"?>"
						 + "<Base Testo=\" Oggetto - \">;"
						 + "<Service Titolo1=\"\" Titolo2=\"Oggetto\";"
						 + "Funzione=\"" + fun.toString() + " "
						 + " Servizio=\"B£_063_02\" TSep=\".\" DSep=\",\" IdFun=\"0000000000001\" NumSes=\"000001\"/>"
						 + "<Header>"
						 + "<Livello Caratteristiche=\"201\"/>"
						 + "<Livello Caratteristiche=\"A01\"/> "  
						 + "</Header>"
						 + "<Oggetto Nome=\"\" Tipo=\"\" Parametro=\"\" Codice=\".\" Testo=\"...\" "
						 + "Exec=\"" + funzione + "\" Fld=\"\" Leaf=\"\"/>" 
						 + "</Base>";
			}
		}	
		
		return xml;
	}

}

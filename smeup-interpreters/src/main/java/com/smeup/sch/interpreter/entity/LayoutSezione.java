package com.smeup.sch.interpreter.entity;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class LayoutSezione {
	
	public LayoutSezione()
	{
		
	}
	
	public ArrayList<Sez> parse(String[] lines)
	{
		ArrayList<Sez> sezioni = new ArrayList<Sez>();
		for(int i=0; i<lines.length;i++)
		{
			if(lines[i].contains("G.SEZ"))
			{
				Sez s = new Sez();
				s.parseLine(lines[i]);
				sezioni.add(s);
			}
		}
		
		return sezioni;
	}
	
	//ALGORITMO PER GENERARE LA SEZIONE LAYOUT
	public String buildXml(String[] lines, String schName)
	{
		String xml ="";
		ArrayList<Sez> sezioni = new ArrayList<Sez>();
		ArrayList<String> xmlList = new ArrayList<String>();
		sezioni = parse(lines);
		
	
	    ArrayList<Sez> temp = new ArrayList<Sez>();
	    
	    xml += "<Layout Scheda=\"MB;SCP_SCH;"+schName+"\" Lib=\"OJ;*LIB;OPNLIB\" >";
	    
	    //se c'è solo una sezione
	    if(sezioni.size()==1)
	    {
	    	xml += sezioni.get(0).buildLayoutXml() + "</Sez>";
	    }
	    
	    for(int i=0; i<sezioni.size()-1; i++)
	    {
    		if(sezioni.get(i).getName().equals(sezioni.get(i+1).getFatherAtt()))
		    {
		    	xml += sezioni.get(i).buildLayoutXml();
		    }else {
		    	xml += sezioni.get(i).buildLayoutXml() + "</Sez>";
		    }
    		
    		//tags di chiusura degli elementi
    		if(sezioni.get(i).getName().length() > sezioni.get(i+1).getName().length())
    		{
    			int differenza = sezioni.get(i).getName().length() - sezioni.get(i+1).getName().length();
    			
    			for(int x=0; x<differenza; x++)
    			{
    				xml += "</Sez>";
    			}
    		}
    		
    		//ultimo elemento
    		if(i==sezioni.size()-2)
    		{
    			xml += sezioni.get(sezioni.size()-1).buildLayoutXml();
    			
    			int differenza = sezioni.get(i+1).getName().length();
    				
				for(int x=0; x<differenza; x++)
				{
					xml += "</Sez>";
				}

    		}
	    }

	    xml += "</Layout>";


		return xml;
	}

}

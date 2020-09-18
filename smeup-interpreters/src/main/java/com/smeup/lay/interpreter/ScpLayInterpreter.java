package com.smeup.lay.interpreter;

import java.util.ArrayList;

import com.smeup.lay.interpreter.entity.LayFld;
import com.smeup.lay.interpreter.entity.LaySez;
import com.smeup.sch.interpreter.entity.LayoutSezione;

public class ScpLayInterpreter {

	public ScpLayInterpreter()
	{
		
	}
		
	public String interpretate(ArrayList<String> linesToInterpretate, String fileName) {
		String xml = "";
		
        xml += buildXml(linesToInterpretate);

		return xml;
	}
	
	//ALGORITMO PER GENERARE LA SEZIONE LAYOUT
	public String buildXml(ArrayList<String> lines)
	{
		String xml ="";
		int a = 0;
		ArrayList<LayFld> fields = new ArrayList<LayFld>();
		ArrayList<LaySez> sezioni = new ArrayList<LaySez>();
		
		int i = 0;
		String sezTemp = null;
		System.out.println("lines : " + lines.size());
		while(i<lines.size())
        {
        	lines.set(i, lines.get(i).trim());
    		
        	//controllo se ci sono delle istruzioni
        	if(lines.get(i).startsWith("::") && lines.get(i).length()>4)
    		{			
        		System.out.println(lines.get(i).substring(2, 5));
				switch(lines.get(i).substring(2, 5))
				{
					
				case "Sez" :
				{
					LaySez sez = new LaySez();
					sez.parseLine(lines.get(i));
					sezioni.add(sez);
					sezTemp = sez.getName();
				}break;
				case "Fld" :
				{
					LayFld fld = new LayFld();
					fld.parseLine(lines.get(i));
					fld.setSez(sezTemp);
				 	fields.add(fld);
				}
				}
    		}
        	i++;
        }
		
		System.out.println("Sez : " + sezioni.size());
		System.out.println("fld : " + fields.size());
		
	    xml += "<Layout>";
	    
	    //se c'è solo una sezione
	    if(sezioni.size()==1)
	    {
	    	xml += sezioni.get(0).buildLayoutXml();
	    	if(fields.size()==1)
	    	{
	    		xml += fields.get(0).buildXml();		
			}
	    	xml += "</Sez>";
	    }else {

		    int z = 0;
		    for(int i1=0; i1<sezioni.size()-1; i1++)
		    {
		    	//PRIMA SEZ è PRIMA DELLA SECONDA
	    		if(sezioni.get(i1).getName().equals(sezioni.get(i1+1).getFatherAtt()))
			    {
			    	xml += sezioni.get(i1).buildLayoutXml();
			    	while(fields.get(z).getSez().equals(sezioni.get(i1).getName()))
			    	{
			    		xml += fields.get(z).buildXml();
			    		z++;
			    	}
			    }else {
			    	xml += sezioni.get(i1).buildLayoutXml();
			    	
			    	while(fields.get(z).getSez().equals(sezioni.get(i1).getName()))
			    	{
			    		xml += fields.get(z).buildXml();
			    		z++;
			    	}
			    	
			    	xml +="</Sez>";
			    }
	    		
	    		//tags di chiusura degli elementi
	    		if(sezioni.get(i1).getName().length() > sezioni.get(i1+1).getName().length())
	    		{
	    			int differenza = sezioni.get(i1).getName().length() - sezioni.get(i1+1).getName().length();
	    			
	    			for(int x=0; x<differenza; x++)
	    			{
	    				xml += "</Sez>";
	    			}
	    		}
	    		
	    		//ultimo elemento
	    		if(i1==sezioni.size()-2)
	    		{
	    			xml += sezioni.get(sezioni.size()-1).buildLayoutXml();
	    			for(int j=z; j<z; j++)
	    			{
			    		xml += fields.get(z).buildXml();
			    	}
	    			
	    			int differenza = sezioni.get(i1+1).getName().length();
	    				
					for(int x=0; x<differenza; x++)
					{
						xml += "</Sez>";
					}
	
	    		}
		    }
	    }

	    xml += "</Layout>";


		return xml;
	}

}

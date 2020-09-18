package com.smeup.sch.interpreter.entity;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Sub {
	//private String line;
	private String component;
	
	public Sub()
	{

	}
	
	public String parseLine(String line)
	{
		component = line.substring(8, 11);
		
		/*for(int i=1; i<app.length; i++)
		{
			
			switch(app[i].substring(0, app[i].indexOf("\"")-1))
			{
				
				case "Id":
				{
					id = app[i].substring(app[i].indexOf("=\"")+2, app[i].length()-1);
				}break;
				
				case "Tit":
				{
					tit = app[i].substring(app[i].indexOf("=\"")+2, app[i].length()-1);
				}
			}
		}	*/
		return line.substring(11);
	}
	
	public String buildXml(String line)
	{
		String params = parseLine(line);
		String xml = null;
		
		xml = "<Sub"+params+">";
		xml += "<"+component+">";		 
		
		return xml;
	}
	
	public String getComponent()
	{
		return component;
	}

}

package com.smeup.sch.interpreter.entity;

import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Sez {
	//private String line;
	private String name; //Pos
	private String dim;
	private String fatherAtt;
	private String dimAtt;
	
	public Sez()
	{

	}
	
	public void parseLine(String line)
	{
		String[] app = line.split(" ");
		
		
		for(int i=1; i<app.length; i++)
		{
			switch(app[i].substring(0, app[i].indexOf("(")))
			{
				case "Pos":
				{
					name = app[i].substring(app[i].indexOf("(")+1, app[i].indexOf(")"));
				}break;
				
				case "Dim":
				{
					dim = app[i].substring(app[i].indexOf("(")+1, app[i].indexOf(")"));
				}break;

			}
		}
		
		findDimAtt();
		findFatherAtt();
	}
	
	public String buildXml(String line)
	{
		parseLine(line);
		String xml = null;
		
		xml = "<Sez Name=\"S"+name+"\" >";

		return xml;
	}
	
	public String buildLayoutXml()
	{
		String xml = null;
		if(dim==null)
		{
			dim="";
		}
		xml = "<Sez Name=\"S"+name+"\" "+dimAtt+"=\""+dim+"\" >";

		return xml;
	}
	
	public void findFatherAtt()
	{
		if(this.name.length()>1)
		{
			this.fatherAtt = name.substring(0, this.name.length()-1);
		}else {
			this.fatherAtt = "";
		}
	}
	
	public void findDimAtt()
	{
		try{
			Integer.parseInt(String.valueOf(this.name.charAt(this.name.length()-1)));
			dimAtt = "Top";
			
		}catch (Exception e) {

			dimAtt = "Left";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDim() {
		return dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

	public String getFatherAtt() {
		return fatherAtt;
	}

	public void setFatherAtt(String fatherAtt) {
		this.fatherAtt = fatherAtt;
	}

	public String getDimAtt() {
		return dimAtt;
	}

	public void setDimAtt(String dimAtt) {
		this.dimAtt = dimAtt;
	}
	

	
	

}

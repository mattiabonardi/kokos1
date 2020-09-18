package com.smeup.sch.interpreter.entity;

import java.util.regex.Pattern;

public class Ogg {
	private String tipo = "";
	private String parametro = "";
	private String codice = "";
	private String testo = "";
	private String fun = "";

	public Ogg()
	{
		
	}
	
	private void parseLine(String line)
	{
		//Condizione per D() per spazi
		String temp1;
		if(line.contains("D("))
		{
			temp1 = line.substring(line.indexOf("D("));
			testo = temp1.substring(temp1.indexOf("D(")+2, temp1.indexOf(")"));	
		}

		//String[] app = line.substring(7).split(Pattern.quote(")"));
		String[] app = line.split(" ");

		for(int i=0; i<app.length; i++)
		{
			app[i] = app[i].trim();
			
			if(!app[i].contains("D.OGG") && app[i].contains("("))
			{
				switch(app[i].substring(0, app[i].indexOf("(")))
				{
					
					case "T":
					{
						tipo = app[i].substring(app[i].indexOf("(")+1, app[i].length()-1);
					}break;
					
					case "P":
					{
						String temp = app[i].substring(app[i].indexOf("(")+1, app[i].length()-1);
						if(temp.contains("("))
						{
							fun += " P(" + temp + ")";
						}else {
							parametro = temp;
						}
					}break;
					
					case "K":
					{
						codice = app[i].substring(app[i].indexOf("(")+1, app[i].length()-1);
					}break;
					
					case "E":
					{
						fun = "";
						fun = app[i].substring(app[i].indexOf("(")+1);
					}break;
					
					case "1":
					{
						fun+= " " + app[i];
					}break;
					
					case "2":
					{
						fun+= " " + app[i];
					}break;
					
					case "3":
					{
						fun+= " " + app[i];
					}break;
					
					case "4":
					{
						fun+= " " + app[i];
					}break;
					
					case "5":
					{
						fun+= " " + app[i];
					}break;
					
					case "6":
					{
						fun+= " " + app[i];
					}break;
					
					case "INPUT":
					{
						fun+= " " + app[i];
					}break;
					
					default: 
				}


				
			}
		}

	}
	
	public String buildXml(String line)
	{
		parseLine(line);
		String xml = null;
		
		xml = "<Oggetto Tipo=\""+tipo+"\" Parametro=\""+parametro+"\" Codice=\""+codice+"\" Testo=\""+testo+"\" />";
		
		if(!fun.equals(""))
		{
			fun = fun.substring(0, fun.length()-1);
			xml = "<Oggetto Tipo=\""+tipo+"\" Parametro=\""+parametro+"\" Codice=\""+codice+"\" Testo=\""+testo+"\" Exec=\""+fun+"\" />";
		}	
		
		resetAttr();
		
		return xml;
	}
	
	public void resetAttr()
	{
		this.tipo = "";
		this.parametro = "";
		this.codice = "";
		this.testo = "";
		this.fun = "";
	}

}

package com.smeup.sch.interpreter.entity;

public class Fun {
	private String fun;
	private String nam;
	
	public Fun()
	{

	}
	
	private void parseLine(String line)
	{
		fun = line.substring(line.indexOf("F("));
	}
	
	private void parseSubSchLine(String line)
	{
		String[] app = line.split(" ");
		
		for(int i=1; i<app.length; i++)
		{
			switch(app[i].substring(0, app[i].indexOf("(")))
			{
				case "Nam":
				{
					nam = app[i].substring(app[i].indexOf("\"")+1, app[i].length()-1);
				}break;
				
			}
		}		
	}
	
	public String buildXml(String line)
	{
		parseLine(line);
		String xml = null;
		
		xml = "<Dati Funzione=\""+fun+"\">";
		
		return xml;
	}
	
	public String buildSubSchXml(String scriptName, String line)
	{
		parseSubSchLine(line);
		String xml = null;
		
		xml = "<Dati Funzione=\"F(EXD;*SCO;) 2(MB;SCP_SCH;"+scriptName+") 4(;;"+nam+")\" />";
		
		return xml;
	}



}

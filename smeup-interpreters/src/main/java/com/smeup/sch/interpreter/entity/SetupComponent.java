package com.smeup.sch.interpreter.entity;

public class SetupComponent {
	private String component;

	
	public SetupComponent()
	{

	}
	
	private String parseLine(String line)
	{
		return line.substring(11);
	}
	
	public String buildXml(String line, String component)
	{
		String params = parseLine(line);
		String xml = null;
		
		xml = "<Setup"+ params + " _Type=\"" + component + "\" />";
		 
		return xml;
	}

}

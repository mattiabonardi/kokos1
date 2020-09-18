package com.smeup.lay.interpreter.entity;

public class LayFld {
	private String sez;
	private String attribute;
	
	public LayFld()
	{
		
	}
	
	public void parseLine(String line)
	{		
		line = line.trim();
		attribute = line.substring(5);	
	}

	public String buildXml()
	{
		String xml = null;
		xml = "<Fld "+ attribute + "/>";
		return xml;
	}

	public String getSez()
	{
		return sez;
	}
	
	public void setSez(String sez)
	{
		this.sez = sez;
	}
	
	

}

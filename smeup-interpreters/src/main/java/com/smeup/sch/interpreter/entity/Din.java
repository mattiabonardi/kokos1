package com.smeup.sch.interpreter.entity;

public class Din {
	private String parseLine(String line)
	{
		return line.substring(7);
	}
	
	public String buildXml(String line)
	{
		String params = parseLine(line);
		String xml = null;
		
		xml = "<Target"+params+" />";
		
		return xml;
	}

}

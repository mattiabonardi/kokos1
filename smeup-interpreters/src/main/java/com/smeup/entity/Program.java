package com.smeup.entity;

public class Program {
	
	private String name;
	private String ext;
	private String interpreter;
	
	public Program()
	{		
	}
	
	public Program(String name, String ext, String interpreter)
	{
		this.name = name;
		this.ext= ext;
		this.interpreter = interpreter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(String interpreter) {
		this.interpreter = interpreter;
	}

}

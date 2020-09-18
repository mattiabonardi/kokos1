package com.smeup.jd;

import java.util.*;

import com.smeup.rpgparser.interpreter.*;



/*
 * Java class that impersonates an RPG program
 * It's called by the CALLJAVA.rpgle in the examples folder.
 * See the ExampleCallJava class
 */
public class JAX_SND implements Program {
	
	private String response = null;
	
	
	public JAX_SND() {
		System.out.println("Create a JAX_SND program instance");
	}
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public List<Value> execute(SystemInterface systemInterface, LinkedHashMap<String, Value> parms) {
			
		Value value = parms.get("$$XML");
		
		setResponse(value.asString().getValue());		
		
		return new ArrayList<Value>();
	}

	public List<ProgramParam> params() {
		return new ArrayList<ProgramParam>() {{
			add(new ProgramParam("$$XML", new StringType(30000, false)));
		}};
	}

	





	
}


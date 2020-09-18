package com.smeup.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.smeup.rpgparser.interpreter.Program;
import com.smeup.rpgparser.interpreter.ProgramParam;
import com.smeup.rpgparser.interpreter.StringType;
import com.smeup.rpgparser.interpreter.StringValue;
import com.smeup.rpgparser.interpreter.SystemInterface;
import com.smeup.rpgparser.interpreter.Value;

public class £JAX_ARIG_I implements Program {
	
	//VARIABILI DI INPUT OUTPUT
	private String £JAXXML; //XML
	
	private boolean varying = false;

	@Override
	public List<Value> execute(SystemInterface systemInterface, LinkedHashMap<String, Value> parms) {
		
		buildXML();
		
		ArrayList<Value> arrayListResponse = new ArrayList<Value>();	
		arrayListResponse.add(new StringValue(£JAXXML, varying));
	
		return arrayListResponse;
	}

	@Override
	public List<ProgramParam> params() {
		return new ArrayList<ProgramParam>() {{
			add(new ProgramParam("£JAXXML", new StringType(1000, varying)));
			}};
	}
	
	private void buildXML()
	{
		£JAXXML = "<Righe>";
	}

}

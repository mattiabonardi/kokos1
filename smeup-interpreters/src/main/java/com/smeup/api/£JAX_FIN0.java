package com.smeup.api;

import java.util.*;
import com.smeup.rpgparser.interpreter.*;
import com.smeup.smeup.connector.fun.FUN;
import com.smeup.smeup.connector.fun.FUNParser;

public class £JAX_FIN0 implements Program {
	
	private LinkedHashMap<String, Value> additionalParams = new LinkedHashMap<String, Value>();
	private String response = null;
	private String £JAXXML;
	private FUN funObj;
	
	
	public £JAX_FIN0() {
	}
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public List<Value> execute(SystemInterface systemInterface, LinkedHashMap<String, Value> parms) {
		
		if(additionalParams.size() > 0) {
			parms.putAll(additionalParams);
		}
		
		Value value = parms.get("$$XML");
		£JAXXML = value.asString().getValue();
		Value fun = parms.get("FUN");
		
		FUNParser parser = new FUNParser();		
		funObj = parser.parse(fun.asString().getValue());
		String cmp = funObj.getProgramma();
				
		switch(cmp)
		{
		case "EXB" : 
		{
			buildEXB();
		}break;
		case "TRE" :
		{
			buildTRE();
		}break;
		case "EDT" :
		{
			buildTRE();
		}break;
		}
		
		return new ArrayList<Value>();
	}

	public List<ProgramParam> params() {
		return new ArrayList<ProgramParam>() {{
			add(new ProgramParam("$$XML", new StringType(30000, false)));
		}};
	}
	
	private void buildEXB()
	{
		setResponse(£JAXXML.trim() + "<Setup>" + 
				"<Program Context=\"\"/>" + 
				"</Setup>" + 
				"<UIPopup/><Setup>" + 
				"<Program Context=\"\">" + 
				"<EXB>" + 
				"<UserSetups Exist=\"\"/>" + 
				"</EXB>" + 
				"<UserSetups Disabled=\"Yes\"/>" + 
				"</Program>" + 
				"</Setup>" + 
				"</UiSmeup>");
	}
	
	private void buildTRE()
	{
		setResponse(£JAXXML.trim() + "</Base>");
	}
	
	public void setAdditionalParams(LinkedHashMap<String, Value> additionalParams) {
		this.additionalParams = additionalParams;
	}

	





	
}


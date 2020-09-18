package com.smeup.interpreters;

import com.smeup.mu.runtime.InterpreterException;
import com.smeup.mu.runtime.RuntimeEngine;
import com.smeup.smeup.connector.fun.FUN;




public class JarikoCaller {
	
	public JarikoCaller()
	{
		
	}
	
	public String call(FUN fun)
	{
		String response = "";
		
		RuntimeEngine re = new RuntimeEngine();
		try {
			response = re.executeFun(fun.toString());
		} catch (InterpreterException e) {
			
			e.printStackTrace();
		}
		
		return response;
	}

}

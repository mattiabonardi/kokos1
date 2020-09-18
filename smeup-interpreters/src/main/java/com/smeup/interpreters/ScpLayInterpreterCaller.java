package com.smeup.interpreters;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smeup.sch.interpreter.ScpSchInterpreter;
import com.smeup.config.Configuration;
import com.smeup.lay.interpreter.ScpLayInterpreter;
import com.smeup.smeup.connector.fun.FUN;

import it.smea.transformer.smeupobj.SmeupObject;

public class ScpLayInterpreterCaller {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScpLayInterpreterCaller.class.getName());
	
	public ScpLayInterpreterCaller()
	{
		
	}
	
	public String call(FUN fun) throws IOException
	{
		String xml = "";
		
		ArrayList<String> linesToInterpretate = new ArrayList<String>();
		
		ScpReader reader = new ScpReader();
		ScpLayInterpreter interpreter = new ScpLayInterpreter();
		
		SmeupObject[] objs = fun.getSmeupObjs();
		String fileName = objs[1].getCodice().toString();
		
		linesToInterpretate = reader.readScpLay(fileName);

		if(linesToInterpretate!=null)
		{
			xml = interpreter.interpretate(linesToInterpretate, fileName);
		}else {
			LOGGER.error("SCP LAY "+ fileName + "not found in " + Configuration.SCPLAY_SOURCE_DIR);
			xml = "<Error>SCP LAY "+ fileName + "not found in " + Configuration.SCPLAY_SOURCE_DIR + "</Error>";
		}
		
		return xml; 
	}

}

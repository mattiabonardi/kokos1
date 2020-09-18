package com.smeup.interpreters;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smeup.config.Configuration;
import com.smeup.sch.interpreter.ScpSchInterpreter;
import com.smeup.smeup.connector.fun.FUN;
import com.smeup.smeup.connector.fun.FUNParser;

import it.smea.transformer.smeupobj.SmeupObject;

public class ScpSchInterpreterCaller {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScpSchInterpreterCaller.class.getName());
	
	
	public ScpSchInterpreterCaller()
	{
		
	}
	
	public String call(FUN fun)
	{
		String xml ="";
		ArrayList<String> linesToInterpretate = new ArrayList<String>();
		
		ScpReader reader = new ScpReader();
		ScpSchInterpreter interpreter = new ScpSchInterpreter();
		
		SmeupObject[] objs = fun.getSmeupObjs();
		String fileName = objs[1].getCodice().toString();
		String schName = objs[3].getCodice();
		
		//se si tratta di una sottoscheda ::I.SCH -> ha un oggetto 4 nei parametri della fun
		if(schName!="")
		{
			try {
				linesToInterpretate = reader.readSubSch(fileName, schName);
				if(linesToInterpretate!=null)
				{
					xml = interpreter.interpretate(linesToInterpretate, fileName, fun);
				}else {
					LOGGER.error("SUB SCH "+ schName + "not found in " + Configuration.SCPSCH_SOURCE_DIR + fileName);
					xml = "<Error>SUB SCH "+ schName + "not found in " + Configuration.SCPSCH_SOURCE_DIR + fileName + "</Error>";
				}
				
			} catch (IOException e) {
				LOGGER.error(e.toString());
				xml = "<Error>" + e.toString() + "</Error>";
			}
		}else {
			//se si tratta di una scheda normale
			try {
				linesToInterpretate = reader.readScpSch(fileName);
				if(linesToInterpretate!=null)
				{
					xml = interpreter.interpretate(linesToInterpretate, fileName, fun);
				}else {
					LOGGER.error("SCH "+ fileName + "not found in " + Configuration.SCPSCH_SOURCE_DIR);
					xml = "<Error>SCH "+ fileName + "not found in " + Configuration.SCPSCH_SOURCE_DIR + "</Error>";
				}
			} catch (IOException e) {
				LOGGER.error(e.toString());
				xml = "<Error>" + e.toString() + "</Error>";
				
			}			
		}
		
		return xml;
	}
	
	

}

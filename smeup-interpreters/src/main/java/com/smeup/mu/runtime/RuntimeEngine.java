package com.smeup.mu.runtime;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.smeup.api.£JAX_IMP0;
import com.smeup.api.£JAX_FIN0;
import com.smeup.config.Configuration;
import com.smeup.rpgparser.execution.CommandLineProgram;
import com.smeup.rpgparser.execution.RunnerKt;
import com.smeup.rpgparser.interpreter.ProgramParam;
import com.smeup.rpgparser.interpreter.StringType;
import com.smeup.rpgparser.interpreter.StringValue;
import com.smeup.rpgparser.interpreter.Value;
import com.smeup.rpgparser.jvminterop.JavaSystemInterface;
import com.smeup.rpgparser.rpginterop.DirRpgProgramFinder;
import com.smeup.rpgparser.rpginterop.RpgSystem;
import com.smeup.smeup.connector.fun.FUN;
import com.smeup.smeup.connector.fun.FUNParser;

public class RuntimeEngine {
	

	public RuntimeEngine() {

		RpgSystem.INSTANCE.addProgramFinder(new DirRpgProgramFinder(new File(Configuration.RPG_SOURCE_DIR)));
	}
	
	public String executeFun(String fun) throws InterpreterException {
		
		System.out.println("FUN : " + fun);
		
		FUNParser parser = new FUNParser();		
		FUN funObj = null;
		try {
			funObj = parser.parse(fun);
		} catch (Exception exc) {
			throw new InterpreterException("Wrong syntax in fun");
		}
				
		String source = getSource(funObj.getFunzione());
		
		System.out.println("Source: " + source.substring(0,50));
		
		ArrayList<String> entryValues = buildEntryValues(funObj);

		return interpretate(source, entryValues, fun);
	}
	
	
	public String interpretate(String source, ArrayList<String> entryValues, String fun) throws InterpreterException {
		String rpgSource = lineEndingConversion(source); 

		// To handle system.out response
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteArrayOutputStream);
		
		JavaSystemInterface javaSystemInterface = new JavaSystemInterface(printStream);
		
		javaSystemInterface.addJavaInteropPackage("com.smeup.api");
		
		LinkedHashMap<String, Value> parms = new LinkedHashMap<String, Value>();
		parms.put("FUN", new StringValue(fun, false));
		
		£JAX_IMP0 obj_£JAX_IMP0 = (£JAX_IMP0) javaSystemInterface.findProgram("£JAX_IMP0");
		£JAX_FIN0 obj_£JAX_FIN0 = (£JAX_FIN0) javaSystemInterface.findProgram("£JAX_FIN0");
		obj_£JAX_IMP0.setAdditionalParams(parms);
		obj_£JAX_FIN0.setAdditionalParams(parms);
				
		CommandLineProgram commandLineProgram = RunnerKt.getProgram(rpgSource, javaSystemInterface);
		//commandLineProgram.setTraceMode(false);		
		
		try {
			commandLineProgram.singleCall(entryValues);
			System.out.println("Single call executed!");
		} catch (Throwable t) {
			System.out.println("Error on single call: " + t.getMessage());
			throw new InterpreterException(t.getMessage());			
		}
		
		String response = obj_£JAX_FIN0.getResponse().trim();	
		return response;		
	}

	private String lineEndingConversion(String rpgContent) {
		Scanner scanner = new Scanner(rpgContent);
		StringBuilder result = new StringBuilder();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			result.append(line);
			result.append(System.lineSeparator());
		}
		scanner.close();
		return result.toString();
	}
	
	private ArrayList<String> buildEntryValues(FUN funObj) {
		
		
		/**
		 * Entry semplificata (senza DS)
		 * 
		 *  Componente:
			$UIBPG                       10
			Programma:
			$UIBFU                       10
			Funzione/metodo:
			$UIBME                       10
			T1:
			$UIBT1                        2
			P1:
			$UIBP1                       10
			K1:
			$UIBK1                       15
			T2:
			$UIBT2                        2
			P2:
			$UIBP2                       10
			K2:
			$UIBK2                       15
			T3:
			$UIBT3                        2
			P3:
			$UIBP3                       10
			K3:
			$UIBK3                       15
			P:
			$UIBPA                      256
			T4:
			$UIBT4                        2
			P4:
			$UIBP4                       10
			K4:
			$UIBK4                       15
			T5:
			$UIBT5                        2
			P5:
			$UIBP5                       10
			K5:
			$UIBK5                       15
			T6:
			$UIBT6                        2
			P6:
			$UIBP6                       10
			K6:
			$UIBK6                       15
			INPUT:
			$UIBD1                    30000
		 */
		
		ArrayList<String> entry = new ArrayList<>();
		
		entry.add(funObj.getProgramma());
		entry.add(funObj.getFunzione());
		entry.add(funObj.getMetodo());
		entry.add(funObj.getSmeupObjs(0).getTipo());
		entry.add(funObj.getSmeupObjs(0).getParametro());
		entry.add(funObj.getSmeupObjs(0).getCodice());
		entry.add(funObj.getSmeupObjs(1).getTipo());
		entry.add(funObj.getSmeupObjs(1).getParametro());
		entry.add(funObj.getSmeupObjs(1).getCodice());
		entry.add(funObj.getSmeupObjs(2).getTipo());
		entry.add(funObj.getSmeupObjs(2).getParametro());
		entry.add(funObj.getSmeupObjs(2).getCodice());
		entry.add(funObj.getParametro());
		entry.add(funObj.getSmeupObjs(3).getTipo());
		entry.add(funObj.getSmeupObjs(3).getParametro());
		entry.add(funObj.getSmeupObjs(3).getCodice());
		entry.add(funObj.getSmeupObjs(4).getTipo());
		entry.add(funObj.getSmeupObjs(4).getParametro());
		entry.add(funObj.getSmeupObjs(4).getCodice());
		entry.add(funObj.getSmeupObjs(5).getTipo());
		entry.add(funObj.getSmeupObjs(5).getParametro());
		entry.add(funObj.getSmeupObjs(5).getCodice());
		entry.add(funObj.getInput());
		
		return entry;
	}

	private String getSource(String programma) throws InterpreterException {
		
		System.out.println("Get source from: " + Configuration.RPG_SOURCE_DIR + programma + ".rpgle");
		
		String result = null;
		
		InputStream is;
		try {
			is = new FileInputStream(Configuration.RPG_SOURCE_DIR + programma + ".rpgle");
			if (is != null) {
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		        result = reader.lines().collect(Collectors.joining(System.lineSeparator()));
		        reader.close();
		    }
		} catch (FileNotFoundException e) {
			System.out.println("Source file " + Configuration.RPG_SOURCE_DIR + programma + ".rpgle not found");
			throw new InterpreterException("Source file " + Configuration.RPG_SOURCE_DIR + programma + ".rpgle not found");
		} catch (IOException e) {

		}
	    
	    return result;
	}	
}

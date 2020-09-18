package com.smeup.yamlReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.smeup.config.Configuration;
import com.smeup.entity.Program;

public class FindInterpreter {
	
	public FindInterpreter()
	{
	}
	
	private ArrayList<Program> getProgram()
	{
		ArrayList<Program> list = new ArrayList();
		
		try{
            YamlReader yr = new YamlReader(new FileReader(Configuration.PROGRAM_ARCHIVE_DIR+Configuration.PROGRAM_ARCHIVE));
            while (true) {
            	
            	//Map crea un oggetto CHIAVE - VALORE
                Map<String, String> map = (Map<String, String>) yr.read();
                if (map == null) {
                    break;
                }
                
                Program prog = new Program(map.get("name"), map.get("ext"), map.get("interpreter"));
                list.add(prog);
                
            }
        }catch (FileNotFoundException | YamlException ex) {
            System.out.println(ex.getMessage());
        }
		
		return list;
	}
	
	public String find(String programName)
	{
		String interpreter = "";
		ArrayList<Program> list = new ArrayList<Program>();
		
		list = getProgram();
		
		//algoritmo di ricerca - da cambiare perchè lento
		for(int i=0; i<list.size(); i++)
		{
			if(programName.equals(list.get(i).getName()))
			{
				interpreter = list.get(i).getInterpreter();
			}
		}
		

		return interpreter;
	}
}

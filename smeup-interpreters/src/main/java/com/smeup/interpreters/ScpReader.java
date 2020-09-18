package com.smeup.interpreters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.smeup.config.Configuration;

public class ScpReader {
	String file;
	
	public ScpReader()
	{
		
	}
	
	//lettura di una scheda normale
	public ArrayList<String> readScpSch(String file) throws IOException
	{
		ArrayList<String> lines = new ArrayList<String>();
		file = file + ".sch";
		FileReader fileReader;
		try {
			fileReader = new FileReader(Configuration.SCPSCH_SOURCE_DIR+file);
		
			BufferedReader fileBufereReader = new BufferedReader(fileReader); 
			 
			String s = fileBufereReader.readLine();
			
			//aggiungo ad un ArrayList tutte le righe lette
			while(s!=null){
				lines.add(s);
			    s = fileBufereReader.readLine();
			}
			

		} catch (FileNotFoundException e) {
			return null;
		}
		
		return lines;
	}
	
	//lettura di una sottoscheda -> ::I.SCH 
	public ArrayList<String> readSubSch(String file, String schName) throws IOException
	{
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<String> subSch = new ArrayList<String>();
		file = file + ".sch";
		FileReader fileReader;

		try {
			fileReader = new FileReader(Configuration.SCPSCH_SOURCE_DIR+file);
			
		
			BufferedReader fileBufereReader = new BufferedReader(fileReader); 
			 
			String s = fileBufereReader.readLine();
			
			//aggiungo ad un ArrayList tutte le righe lette
			while(s!=null){
				lines.add(s);
			    s = fileBufereReader.readLine();
			}
			 
			//trovo la riga dove inizia la sottoscheda ::I.SCH
			int start = 0;
			boolean findStart = false;
			for(int i=0; i<lines.size(); i++)
			{
				if(lines.get(i).contains("Nam("+schName+")"))
				{
					start = i;
					findStart = true;
					break;
				}
			}
			
			//trovo la riga dove finisce la sottscheda
			int end = 0;
			boolean findEnd = false;
			if(findStart)
			{
				for(int i=start; i<lines.size(); i++)
				{
					if(lines.get(i).contains("::I.SCH.END"))
					{
						end = i;
						findEnd = true;
						break;
					}
				}
			}

			//copio le righe della sottoscheda in un altro ArrayList
			int j = 0;
			if(findStart && findEnd)
			{
				for(int i=start+1; i<end; i++)
				{
					subSch.add(lines.get(i).toString());
					j++;
				}
			}
			

		} catch (FileNotFoundException e) {
			return null;
		}
		
		
		return subSch;
	}
	
	public ArrayList<String> readScpLay(String file) throws IOException
	{
		ArrayList<String> lines = new ArrayList<String>();
		file = file + ".lay";
		FileReader fileReader;
		try {
			fileReader = new FileReader(Configuration.SCPLAY_SOURCE_DIR+file);
		
			BufferedReader fileBufereReader = new BufferedReader(fileReader); 
			 
			String s = fileBufereReader.readLine();
			
			//aggiungo ad un ArrayList tutte le righe lette
			while(s!=null){
				lines.add(s);
			    s = fileBufereReader.readLine();
			}
			

		} catch (FileNotFoundException e) {
			return null;
		}
		
		return lines;
	}
}



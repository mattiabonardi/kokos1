package com.smeup.sch.interpreter.entity;

import com.smeup.smeup.connector.fun.FUN;

import it.smea.transformer.smeupobj.SmeupObject;

public class Var {
	
	public Var()
	{
		
	}
	
	public String change(String line, FUN fun)
	{
		SmeupObject[] objs = fun.getSmeupObjs();
		String input = fun.getInput();
		String p = fun.getParametro();
		
		//INPUT 
		if(line.contains("&IN."))
		{
			if(!input.equals(null))
			{
				String temp = line.substring(line.indexOf("&IN")+4);
				boolean ciclo = true;
				int i = 0;
				String var = "";
				while(ciclo && temp.length() > i)
				{
					if(temp.charAt(i) == ')' || temp.charAt(i) == ']' || temp.charAt(i) == '(' || temp.charAt(i) == '[' || temp.charAt(i) == ' ' || temp.charAt(i) == '"')
					{
						ciclo = false;
					}else {
						var += temp.charAt(i);
						i++;
					}
				}
				
				//recupero la variabile
				String valoreVar;
				if(line.contains(var))
				{
					String temp2 = input.substring(input.indexOf(var)+var.length()+1);
					valoreVar = temp2.substring(0, temp2.indexOf(")"));
					
					//stampo la variabile nella line
					line = line.replace("&IN."+var, valoreVar);
				}
		
			}
		}
		
		
		//INPUT 
		if(line.contains("&PA."))
		{
			if(!p.equals(null))
			{
				String temp = line.substring(line.indexOf("&PA")+4);
				boolean ciclo = true;
				int i = 0;
				String var = "";
				while(ciclo && temp.length() > i)
				{
					if(temp.charAt(i) == ')' || temp.charAt(i) == ']' || temp.charAt(i) == '(' || temp.charAt(i) == '[' || temp.charAt(i) == ' ' || temp.charAt(i) == '"')
					{
						ciclo = false;
					}else {
						var += temp.charAt(i);
						i++;
					}
				}
				
				//recupero la variabile
				String valoreVar;
				if(line.contains(var))
				{
					String temp2 = p.substring(p.indexOf(var)+var.length()+1);
					valoreVar = temp2.substring(0, temp2.indexOf(")"));
					
					//stampo la variabile nella line
					line = line.replace("&PA."+var, valoreVar);
				}
		
			}
		}
		
		//OBJ 1
		if(line.contains("&OG.T1"))
		{
			if(!objs[0].getTipo().equals(null))
			{
				line = line.replace("&OG.T1", objs[0].getTipo());
			}
		}
		
		if(line.contains("&OG.P1"))
		{
			if(!objs[0].getParametro().equals(null))
			{
				line = line.replace("&OG.P1", objs[0].getParametro());
			}
		}
		
		if(line.contains("&OG.K1"))
		{
			if(!objs[0].getCodice().equals(null))
			{
				line = line.replaceAll("&OG.K1", objs[0].getCodice());
			}
		}
		
		
		//OBJ 2
		if(line.contains("&OG.T2"))
		{
			if(!objs[0].getTipo().equals(null))
			{
				line = line.replace("&OG.T2", objs[1].getTipo());
			}
		}
		
		if(line.contains("&OG.P2"))
		{
			if(!objs[0].getParametro().equals(null))
			{
				line = line.replace("&OG.P2", objs[1].getParametro());
			}
		}
		
		
		if(line.contains("&OG.K2"))
		{
			if(!objs[0].getCodice().equals(null))
			{
				line = line.replaceAll("&OG.K2", objs[1].getCodice());
			}
		}
		
		
		//OBJ 3
		if(line.contains("&OG.T3"))
		{
			if(!objs[0].getTipo().equals(null))
			{
				line = line.replace("&OG.T3", objs[2].getTipo());
			}
		}
		
		if(line.contains("&OG.P3"))
		{
			if(!objs[0].getParametro().equals(null))
			{
				line = line.replace("&OG.P3", objs[2].getParametro());
			}
		}
		
		
		if(line.contains("&OG.K3"))
		{
			if(!objs[0].getCodice().equals(null))
			{
				line = line.replaceAll("&OG.K3", objs[2].getCodice());
			}
		}
		
		
		//OBJ 4
		if(line.contains("&OG.T4"))
		{
			if(!objs[0].getTipo().equals(null))
			{
				line = line.replace("&OG.T4", objs[3].getTipo());
			}
		}
		
		if(line.contains("&OG.P4"))
		{
			if(!objs[0].getParametro().equals(null))
			{
				line = line.replace("&OG.P4", objs[3].getParametro());
			}
		}
		
		
		if(line.contains("&OG.K4"))
		{
			if(!objs[0].getCodice().equals(null))
			{
				line = line.replaceAll("&OG.K4", objs[3].getCodice());
			}
		}
		
		
		//OBJ 5
		if(line.contains("&OG.T5"))
		{
			if(!objs[0].getTipo().equals(null))
			{
				line = line.replace("&OG.T5", objs[4].getTipo());
			}
		}
		
		if(line.contains("&OG.P5"))
		{
			if(!objs[0].getParametro().equals(null))
			{
				line = line.replace("&OG.P5", objs[4].getParametro());
			}
		}
		
		
		if(line.contains("&OG.K5"))
		{
			if(!objs[0].getCodice().equals(null))
			{
				line = line.replaceAll("&OG.K5", objs[4].getCodice());
			}
		}
		
		
		//OBJ 6
		if(line.contains("&OG.T6"))
		{
			if(!objs[0].getTipo().equals(null))
			{
				line = line.replace("&OG.T6", objs[5].getTipo());
			}
		}
		
		if(line.contains("&OG.P6"))
		{
			if(!objs[0].getParametro().equals(null))
			{
				line = line.replace("&OG.P6", objs[5].getParametro());
			}
		}
		
		
		if(line.contains("&OG.K6"))
		{
			if(!objs[0].getCodice().equals(null))
			{
				line = line.replaceAll("&OG.K6", objs[5].getCodice());
			}
		}
		
		
		return line;
	}
	

}

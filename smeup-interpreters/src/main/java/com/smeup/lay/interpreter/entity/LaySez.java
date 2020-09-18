package com.smeup.lay.interpreter.entity;

public class LaySez {
	private String name; //Pos
	private String dim;
	private String fatherAtt;
	private String dimAtt;
	private String style;
	
	public LaySez()
	{
		
	}
	
	public void parseLine(String line)
	{
		String[] app = line.split(" ");
		
		for(int i=1; i<app.length; i++)
		{		
			app[i] = app[i].trim();
			switch(app[i].substring(0, app[i].indexOf("=")))
			{
				case "Pos":
				{
					name = app[i].substring(app[i].indexOf("=")+2, app[i].length()-1);
				}break;
				
				case "Dim":
				{
					dim = app[i].substring(app[i].indexOf("=")+2, app[i].length()-1);
				}break;
				
				case "Sty":
				{
					style = app[i].substring(app[i].indexOf("=")+2, app[i].length()-1);
				}break;

			}
		}
		
		findDimAtt();
		findFatherAtt();
	}
	
	public void findFatherAtt()
	{
		if(this.name.length()>1)
		{
			this.fatherAtt = name.substring(0, this.name.length()-1);
		}else {
			this.fatherAtt = "";
		}
	}
	
	public void findDimAtt()
	{
		try{
			Integer.parseInt(String.valueOf(this.name.charAt(this.name.length()-1)));
			dimAtt = "Top";
			
		}catch (Exception e) {

			dimAtt = "Left";
		}
	}
	
	public String buildLayoutXml()
	{
		String xml = null;
		if(dim==null)
		{
			dim="";
		}
		xml = "<Sez Name=\"S"+name+"\" "+dimAtt+"=\""+dim+"\" Dim=\""+dim+"\" Pos=\""+name+"\" Sty=\""+style+"\">";

		return xml;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDim() {
		return dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

	public String getFatherAtt() {
		return fatherAtt;
	}

	public void setFatherAtt(String fatherAtt) {
		this.fatherAtt = fatherAtt;
	}

	public String getDimAtt() {
		return dimAtt;
	}

	public void setDimAtt(String dimAtt) {
		this.dimAtt = dimAtt;
	}
	
	

}

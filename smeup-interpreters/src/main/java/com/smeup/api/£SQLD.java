package com.smeup.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.smeup.rpgparser.interpreter.*;

public class £SQLD implements Program{
	
	private String £SQLD_FUNFU;
	private String £SQLD_FUNME;
	private String £SQLDString;
	private String £SQLD35;
	private String £SQLDCOD;
	
	private boolean start;
	private Connection connection = null;
	private Statement statement;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private String[] columnName;
	private int columnCount;
	
	
	private boolean varying = false;
	
	public £SQLD()
	{
		System.out.println("Create a £PDS program instance");
	}
	
	public List<Value> execute(SystemInterface systemInterface, LinkedHashMap<String, Value> parms) {
		cleanVariables();
		for (Map.Entry<String, ? extends Value> entry : parms.entrySet()) {
			switch(entry.getKey())
			{
			case "£SQLD_FUNFU" :
			{
				this.£SQLD_FUNFU = ((StringValue)entry.getValue()).getValue().trim();
			}break;
			case "£SQLD_FUNME" : 
			{
				this.£SQLD_FUNME = ((StringValue)entry.getValue()).getValue().trim();
			}break;
			case "£SQLDString" :
			{
				this.£SQLDString = ((StringValue)entry.getValue()).getValue().trim();
			}break;
			
			}
		}
		
		switch(£SQLD_FUNFU)
		{
		case "OPEN" :
		{
			open();
		}break;
		case "CLOSE" :
		{
			close();
		}break;
		case "NXTREC" :
		{
			read();
		}break;
		case "RETINT" :
		{
			getColumnXml();
		}break;
		
		default : £SQLD35 = "1";
		}
		
		ArrayList<Value> arrayListResponse = new ArrayList<Value>();	
		arrayListResponse.add(new StringValue(£SQLD_FUNFU, varying));
		arrayListResponse.add(new StringValue(£SQLD_FUNME, varying));
		arrayListResponse.add(new StringValue(£SQLDString, varying));
		arrayListResponse.add(new StringValue(£SQLD35, varying));
		arrayListResponse.add(new StringValue(£SQLDCOD, varying));
	
		return arrayListResponse;
	}

	public List<ProgramParam> params() {
		return new ArrayList<ProgramParam>() {{
			add(new ProgramParam("£SQLD_FUNFU", new StringType(50, varying)));
			add(new ProgramParam("£SQLD_FUNME", new StringType(50, varying)));
			add(new ProgramParam("£SQLDString", new StringType(5000, varying)));
			add(new ProgramParam("£SQLD35", new StringType(1, varying)));
			add(new ProgramParam("£SQLDCOD", new StringType(4, varying)));
			}};
		}
	
	private void cleanVariables() {
		£SQLD_FUNFU = "";
		£SQLD_FUNME = "";
		£SQLD35 = "";
		£SQLDString = "";
		£SQLDCOD = "";
	}
	
	//TEST CONNECTION
	private void open() 
	{		
		statement = null;
		rs = null;
		rsmd = null;
		columnName = null;
		columnCount = 0;
		
		try {
			if(connection != null)
	        connection.close();
	    }catch (SQLException e) {
	        }

		try {

	        Class.forName("com.ibm.as400.access.AS400JDBCDriver");
	        connection = DriverManager.getConnection("jdbc:as400://srvlab01.smeup.com;naming=system;", "BONMAI", "glsd1234");
	        
	        statement = connection.createStatement();
	        start = true;
		}catch (ClassNotFoundException | SQLException e) {
		    e.printStackTrace();
		    £SQLD35 = "1";
		}
		
		if(connection != null) {
			£SQLD35 = "0";
			£SQLDCOD = "1";
		}else {
			£SQLD35 = "1";
		}
	}
	
	public void close()
	{
		try {
	        connection.close();
	    }catch (SQLException e) {
	    	£SQLDCOD = "-1";
	    }
		
		if(connection == null) {
			£SQLD35 = "0";
		}else {
			£SQLD35 = "1";
		}	
	}
	
	//LETTURA RECORD
	private void read()
	{
		switch(£SQLD_FUNME)
		{
		case "MAT" :
		{
			if(start)
			{
				try {						
					rs = statement.executeQuery(£SQLDString);
					rsmd = rs.getMetaData();
					findData();
					start = false;
					
					if(rs.next())
					{
						£SQLDString = rs.getString(1).trim();					
						for(int i=2; i<columnCount+1; i++)
						{
							£SQLDString += "|" + rs.getString(i).trim();
						}
					}else {
						£SQLDCOD = "100";
					}
				} catch (SQLException e) {
					£SQLD35 = "1";
					£SQLDCOD = "-1";
					e.printStackTrace();
				}
			}else {
				try {
					if(rs.next())
					{
						£SQLDString = rs.getString(1).trim();
						for(int i=2; i<columnCount+1; i++)
						{
							£SQLDString += "|" + rs.getString(i).trim();
						}
					}else {
						£SQLDCOD = "100";
					}
				} catch (SQLException e) {
					£SQLD35 = "1";
					£SQLDCOD = "-1";
				}
			}
		}break;
		
		case "DS" :
		{
			
		}break;
		
		default : £SQLD35 = "1";
		}
	
	}
	
	//RECUPERA IL NOME DEI CAMPI
	private void findData()
	{		
		String temp = £SQLDString.substring(£SQLDString.indexOf("SELECT") + 6, £SQLDString.indexOf("FROM"));
		
		if(temp.contains("*"))
		{
			try {
				columnCount = rsmd.getColumnCount();
				columnName = new String[columnCount];
				for(int i=0; i<columnCount; i++)
				{
					columnName[i] = rsmd.getColumnName(i+1);	
				}
				
			} catch (SQLException e) {
				£SQLD35 = "1";
				£SQLDCOD = "-1";
				e.printStackTrace();
			}
		}else {
			if(temp.contains(","))
			{
				temp = temp.trim();
				columnName = temp.split(",");
				columnCount = columnName.length;
			}else {
				columnName = new String[1];
				columnName[0] = temp.trim();
				columnCount = 1;
			}
		}
	}
	
	public void getColumnXml()
	{
		switch(£SQLD_FUNME)
		{
		case "MAT1" :
		{
			£SQLDString = "<Griglia>";
			
			for(int i=0; i<columnCount; i++)
			{
				£SQLDString +="<Colonna "
							+ "Cod=\"" + columnName[i] + "\" "
							+ "Txt=\"" + columnName[i] + "\" "
							+ "Lun=\"99\" IO=\"O\" />";
			}
			
			£SQLDString += "</Griglia>";
		}break;
		default : £SQLD35 = "1";
		}
	}
}
		



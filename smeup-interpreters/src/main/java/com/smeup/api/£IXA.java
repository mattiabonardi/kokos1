package com.smeup.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.smeup.rpgparser.interpreter.*;

//$G80 - FUNZIONI SU IFT
//FUNZIONI API
//LETTURA ELENCO CARTELLE 
//FU = READ | ME = *DIR | PH = path
//			 LETTURA FILE
public class £IXA implements Program{
	//VARIABILI DI CONFIGURAZIONE
	private static final String DNS_SERVER = "srvlab01.smeup.com";
	private static final String DB_FILE = "W_BUSFIO/VERAPG0F";
	
	//VARIABILI DI INPUT
	private String £IXAFU; //FUNZIONE - 
	private String £IXAME; //METODO (chiave)
	private String £IXANK; //NUMERO DI CHIAVI
	private String £IXAVL; //VALORI IN INGRESSO
	
	//VARIABILI DI OUTPUT
	private String £IXARES; //RECORD DIVISO IN |
	private String £IXA35;
	private String £IXACOD;
	
	//DS VERAPG
	private String V£ATV0;
	private String V£CDC;
	private String V£COD0;
	private String V£COD2;
	private String V£COD3;
	private String V£COD4;
	private String V£COD5;
	private String V£COD6;
	private String V£COD7;
	private String V£COD8;
	private String V£COD9;
	private String V£DATA;
	private String V£IDOJ;
	private String V£NOME;
	private String V£TIPO;
	
	//VARIABILI DI CLASSE
	private boolean varying = false;
	private boolean start;
	private String sql;
	private Connection connection = null;
	private Statement statement;
	private ResultSet rs;
	private String[] values;
	private ResultSetMetaData rsmd;
	private String[] columnName;
	private int columnCount;
	
	public £IXA()
	{
	}
	
	public List<Value> execute(SystemInterface systemInterface, LinkedHashMap<String, Value> parms) {
		cleanVariables();
		
		for (Map.Entry<String, ? extends Value> entry : parms.entrySet()) {
			switch(entry.getKey())
			{
			case "£IXAFU" :
			{
				this.£IXAFU = ((StringValue)entry.getValue()).getValue().trim();
			}break;
			case "£IXAME" : 
			{
				this.£IXAME = ((StringValue)entry.getValue()).getValue().trim();
			}break;
			case "£IXANK" :
			{
				this.£IXANK = ((StringValue)entry.getValue()).getValue().trim();
			}break;
			case "£IXAVL" :
			{
				this.£IXAVL = ((StringValue)entry.getValue()).getValue().trim();
			}

			}
		}
		
		switch(£IXAFU)
		{
		case "SETLL" :
		{
			setll();
		}break;
		case "READE" :
		{
			read();
		}break;
		case "CHA" :
		{
			chain();
		}break;
		case "WRI" :
		{
			write();
		}break;
		case "UPD" :
		{
			upload();
		}break;
		case "DEL" :
		{
			delete();
		}break;
		case "RETINT" :
		{
			getColumnXml();
		}break;
		default : £IXA35 = "1";
		}
		
		
		ArrayList<Value> arrayListResponse = new ArrayList<Value>();	
		arrayListResponse.add(new StringValue(£IXAFU, varying));
		arrayListResponse.add(new StringValue(£IXAME, varying));
		arrayListResponse.add(new StringValue(£IXANK, varying));
		arrayListResponse.add(new StringValue(£IXAVL, varying));
		arrayListResponse.add(new StringValue(£IXARES, varying));
		arrayListResponse.add(new StringValue(£IXA35, varying));
		arrayListResponse.add(new StringValue(£IXACOD, varying));
	
		return arrayListResponse;
	}

	public List<ProgramParam> params() {
		return new ArrayList<ProgramParam>() {{
			add(new ProgramParam("£IXAFU", new StringType(10, varying)));
			add(new ProgramParam("£IXAME", new StringType(10, varying)));
			add(new ProgramParam("£IXANK", new StringType(2, varying)));
			add(new ProgramParam("£IXAVL", new StringType(1000, varying)));
			add(new ProgramParam("£IXARES", new StringType(5000, varying)));
			add(new ProgramParam("£IXA35", new StringType(1, varying)));
			add(new ProgramParam("£IXACOD", new StringType(4, varying)));
			}};
		}
	
	private void cleanVariables()
	{
		£IXAFU = ""; 
		£IXAME = ""; 
		£IXANK = ""; 
		£IXARES = ""; 
		£IXA35 = ""; 
		£IXACOD = "";
	}
	
	private void getValue()
	{
		values = £IXAVL.split("|");
	}
	
	//FUNZIONE SETLL
	private void setll()
	{
		System.out.println("SETLL START");
		switch(£IXAME)
		{
		/*case "9L" : //V£DATA - V£CDC
		{
			getValue();
			
			if(£IXANK.equals("1"))
			{
				V£DATA = values[0].trim();
				sql = "SELECT * FROM " + DB_FILE + "WHERE V£DATA='" + V£DATA + "'"; 
			}
			sql = "SELECT * FROM VERAPG9L WHERE ";
		}*/
		case "0L" : //V£IDOJ
		{
			V£IDOJ = £IXAVL;
			//sql = "SELECT * FROM " + DB_FILE + "WHERE V£IDOJ=\"" + V£IDOJ + "\"";
			sql = "SELECT * FROM W_BUSFIO/VERAPG0F";
			//sql = "SELECT * FROM " + DB_FILE;
			open();
		}
		}
	}
	
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
	        System.out.println("OPEN OK");
		}catch (ClassNotFoundException | SQLException e) {
		    e.printStackTrace();
		    £IXA35 = "1";
		}
		
		if(connection != null) {
			£IXA35 = "0";
		}else {
			£IXA35 = "1";
		}
	}
	
	//FUNZIONE READ
	private void read()
	{
		if(start)
		{
			try {						
				rs = statement.executeQuery(sql);
				rsmd = rs.getMetaData();
				findData();
				start = false;
				
				if(rs.next())
				{
					£IXARES = rs.getString(1).trim();					
					for(int i=2; i<columnCount+1; i++)
					{
						£IXARES += "|" + rs.getString(i).trim();
					}
				}else {
					£IXACOD = "100";
				}
			} catch (SQLException e) {
				£IXA35 = "1";
				£IXACOD = "-1";
				e.printStackTrace();
			}
		}else {
			try {
				if(rs.next())
				{
					£IXARES = rs.getString(1).trim();
					for(int i=2; i<columnCount+1; i++)
					{
						£IXARES += "|" + rs.getString(i).trim();
					}
				}else {
					£IXACOD = "100";
					close();
				}
			} catch (SQLException e) {
				£IXA35 = "1";
				£IXACOD = "-1";
			}
		}
	}
	
	private void upload() {
		// TODO Auto-generated method stub
		
	}

	private void write() {
		// TODO Auto-generated method stub
		
	}

	private void chain() {
		// TODO Auto-generated method stub
		
	}
	
	private void delete() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	//RECUPERA IL NOME DEI CAMPI
	private void findData()
	{		
		try {
			columnCount = rsmd.getColumnCount();
			columnName = new String[columnCount];
			for(int i=0; i<columnCount; i++)
			{
				columnName[i] = rsmd.getColumnName(i+1);	
			}
		} catch (SQLException e) {
			£IXA35 = "1";
			e.printStackTrace();
		}			
	}
	
	public void close()
	{
		try {
	        connection.close();
	    }catch (SQLException e) {
	    	£IXA35 = "-1";
	    }
		
		if(connection == null) {
			£IXA35 = "0";
		}else {
			£IXA35 = "1";
		}	
	}
	
	private void getColumnXml()
	{
		£IXARES = "<Griglia>";
			
		for(int i=0; i<columnCount; i++)
		{
			£IXARES +="<Colonna "
						+ "Cod=\"" + columnName[i] + "\" "
						+ "Txt=\"" + columnName[i] + "\" "
						+ "Lun=\"99\" IO=\"O\" />";
		}
		
		£IXARES += "</Griglia>";
		
	}
					
				
	
}
		



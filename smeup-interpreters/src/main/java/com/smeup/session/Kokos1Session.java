package com.smeup.session;

public class Kokos1Session {
	public String USER;
	public String DEVICE;
	public String ENV;
	public String SERVER;
	public String SESSIONID;
	public String KOKOS1_TOKEN;
	
	public Kokos1Session(String user, String device, String env, String server, String sessionId, String kokos1_token)
	{
		this.USER = user;
		this.DEVICE = device;
		this.ENV = env;
		this.SERVER = server;
		this.SESSIONID = sessionId;
		this.KOKOS1_TOKEN = kokos1_token;
	}

	public String getUSER() {
		return USER;
	}

	public void setUSER(String uSER) {
		USER = uSER;
	}

	public String getDEVICE() {
		return DEVICE;
	}

	public void setDEVICE(String dEVICE) {
		DEVICE = dEVICE;
	}

	public String getENV() {
		return ENV;
	}

	public void setENV(String eNV) {
		ENV = eNV;
	}

	public String getSERVER() {
		return SERVER;
	}

	public void setSERVER(String sERVER) {
		SERVER = sERVER;
	}

	public String getSESSIONID() {
		return SESSIONID;
	}

	public void setSESSIONID(String sESSIONID) {
		SESSIONID = sESSIONID;
	}

	public String getKOKOS1_TOKEN() {
		return KOKOS1_TOKEN;
	}

	public void setKOKOS1_TOKEN(String kOKOS1_TOKEN) {
		KOKOS1_TOKEN = kOKOS1_TOKEN;
	}
	
	
}

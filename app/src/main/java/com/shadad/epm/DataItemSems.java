package com.shadad.epm;

import com.google.gson.annotations.SerializedName;

public class DataItemSems {

	@SerializedName("STACK")
	private String sTACK;

	@SerializedName("NO")
	private String nO;

	@SerializedName("DATE")
	private String dATE;

	@SerializedName("STATUS")
	private String sTATUS;

	@SerializedName("AMPER")
	private String aMPER;

	@SerializedName("VOLT")
	private String vOLT;

	@SerializedName("POWER")
	private String pOWER;

	@SerializedName("TIME")
	private String tIME;

	@SerializedName("ID")
	private String iD;

	public String getSTACK(){
		return sTACK;
	}

	public String getNO(){
		return nO;
	}

	public String getDATE(){
		return dATE;
	}

	public String getSTATUS(){
		return sTATUS;
	}

	public String getAMPER(){
		return aMPER;
	}

	public String getVOLT(){
		return vOLT;
	}

	public String getPOWER(){
		return pOWER;
	}

	public String getTIME(){
		return tIME;
	}

	public String getID(){
		return iD;
	}
}
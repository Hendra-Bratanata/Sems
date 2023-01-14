package com.shadad.epm;

import com.google.gson.annotations.SerializedName;

public class DataItemHardware {

	@SerializedName("STACK")
	private String sTACK;

	@SerializedName("STATUS")
	private String sTATUS;

	@SerializedName("ID")
	private String iD;

	public String getSTACK(){
		return sTACK;
	}

	public String getSTATUS(){
		return sTATUS;
	}

	public String getID(){
		return iD;
	}
}
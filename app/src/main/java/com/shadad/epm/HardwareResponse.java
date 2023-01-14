package com.shadad.epm;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HardwareResponse{

	@SerializedName("data")
	private List<DataItemHardware> data;

	public List<DataItemHardware> getData(){
		return data;
	}
}
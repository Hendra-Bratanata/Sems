package com.shadad.epm;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SemsResponse {

	@SerializedName("data")
	private List<DataItemSems> data;

	public List<DataItemSems> getData(){
		return data;
	}
}
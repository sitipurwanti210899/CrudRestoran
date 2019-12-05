package com.gosigitgo.crudrestoran.model;


import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private Data data;

	@SerializedName("sukses")
	private boolean sukses;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}
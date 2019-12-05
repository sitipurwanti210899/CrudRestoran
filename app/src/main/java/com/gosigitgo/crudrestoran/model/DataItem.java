package com.gosigitgo.crudrestoran.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class DataItem implements Parcelable {

	@SerializedName("menu_harga")
	private String menuHarga;

	@SerializedName("menu_gambar")
	private String menuGambar;

	@SerializedName("menu_nama")
	private String menuNama;

	@SerializedName("menu_id")
	private String menuId;

	public void setMenuHarga(String menuHarga){
		this.menuHarga = menuHarga;
	}

	public String getMenuHarga(){
		return menuHarga;
	}

	public void setMenuGambar(String menuGambar){
		this.menuGambar = menuGambar;
	}

	public String getMenuGambar(){
		return menuGambar;
	}

	public void setMenuNama(String menuNama){
		this.menuNama = menuNama;
	}

	public String getMenuNama(){
		return menuNama;
	}

	public void setMenuId(String menuId){
		this.menuId = menuId;
	}

	public String getMenuId(){
		return menuId;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.menuHarga);
		dest.writeString(this.menuGambar);
		dest.writeString(this.menuNama);
		dest.writeString(this.menuId);
	}

	public DataItem() {
	}

	protected DataItem(Parcel in) {
		this.menuHarga = in.readString();
		this.menuGambar = in.readString();
		this.menuNama = in.readString();
		this.menuId = in.readString();
	}

	public static final Parcelable.Creator<DataItem> CREATOR = new Parcelable.Creator<DataItem>() {
		@Override
		public DataItem createFromParcel(Parcel source) {
			return new DataItem(source);
		}

		@Override
		public DataItem[] newArray(int size) {
			return new DataItem[size];
		}
	};
}
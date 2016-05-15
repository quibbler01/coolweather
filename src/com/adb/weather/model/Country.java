package com.adb.weather.model;

public class Country {
	private int id;
	private String provinceName;
	private String provinceCode;
	private int cityId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryName() {
		return provinceName;
	}

	public void setCountryName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCountryCode() {
		return provinceCode;
	}

	public void setCountryCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
}

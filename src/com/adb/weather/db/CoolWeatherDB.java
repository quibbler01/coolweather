package com.adb.weather.db;

import java.util.ArrayList;
import java.util.List;

import com.adb.weather.model.City;
import com.adb.weather.model.Country;
import com.adb.weather.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
	/*
	 * dataBase name
	 */
	public static final String DB_NAME = "cool_weather";
	/*
	 * dataBase version
	 */
	public static final int VERSION = 1;
	private static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;

	/*
	 * Method private
	 */
	private CoolWeatherDB(Context context) {
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	public synchronized static CoolWeatherDB getInstance(Context context) {
		if (coolWeatherDB == null) {
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}

	/*
	 * Province存储到本地数据库
	 * 
	 */
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}

	}

	/*
	 * 从数据库读取Province
	 */
	public List<Province> loadProvince() {
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("Province", null, null, null, null, null, null);
		if (cursor.moveToNext()) {
			do {
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
			} while (cursor.moveToNext());
		}
		return list;
	}

	/*
	 * write city to dataBase
	 */
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("provinceId]", city.getProvinceid());
			db.insert("City", null, values);
		}
	}

	/*
	 * read city from dataBase
	 */
	public List<City> lodaCity(int provinceId) {
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City", null, null, null, null, null, null);
		if (cursor.moveToNext()) {
			do {
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setProvinceId(provinceId);
				list.add(city);

			} while (cursor.moveToNext());
		}
		return list;
	}

	/*
	 * Country to dataBase
	 */
	public void saveCountry(Country country) {
		ContentValues values = new ContentValues();
		if (country != null) {
			values.put("country_name", country.getCountryName());
			values.put("country_code", country.getCountryCode());
			values.put("city_id", country.getCityId());
			db.insert("Country", null, values);
		}

	}

	/*
	 * read Country from dataBase
	 */
	public List<Country> loadCountry(int cityId) {
		List<Country> list = new ArrayList<Country>();
		Cursor cursor = db.query("Country", null, "city_id=?", new String[] { String.valueOf(cityId) }, null, null,
				null);
		if (cursor.moveToNext()) {
			do {
				Country country = new Country();
				country.setId(cursor.getInt(cursor.getColumnIndex("id")));
				country.setCountryCode(cursor.getString(cursor.getColumnIndex("country_code")));
				country.setCountryName(cursor.getString(cursor.getColumnIndex("country_name")));
				country.setCityId(cityId);
				list.add(country);
			} while (cursor.moveToNext());
		}
		return list;
	}

}

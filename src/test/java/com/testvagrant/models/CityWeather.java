package com.testvagrant.models;

import lombok.Data;

@Data
public class CityWeather {
	
	private String cityName;
	private String condition;
	private float tempCelcius;
	private float tempFarnhit;
	private float humidity;
	

}

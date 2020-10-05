package com.testvagrant.models;

import lombok.Data;

@Data
public class CityWeather {
	
	private String cityName;
	private String condition;
	private double tempCelcius;
	private double tempFarnhit;
	private int humidity;
	

}

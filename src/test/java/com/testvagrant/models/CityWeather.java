package com.testvagrant.models;

import lombok.Data;

@Data
public class CityWeather {
	
	private String cityName;
	private float tempCelcius;
	private float tempFarnhit;

}

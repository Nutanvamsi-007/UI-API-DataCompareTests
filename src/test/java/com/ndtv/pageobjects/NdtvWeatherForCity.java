package com.ndtv.pageobjects;

import org.openqa.selenium.WebElement;

import com.testvagrant.models.CityWeather;

public class NdtvWeatherForCity extends NdtvWeatherPage {
	
	private CityWeather cityWeather;
	
	private WebElement cityOnMap;

	public NdtvWeatherForCity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CityWeather getCityTempDetails(String cityName) {
		
		return new CityWeather();
	}

	
	

}

package com.ndtv.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testvagrant.models.CityWeather;

public class NdtvWeatherPage {
	
	@FindBy(id="searchBox")
	private WebElement searchBox;
	
	private WebElement cityOnMap;
	
	public NdtvWeatherPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public CityWeather getCityTempDetails() {
		
		return new CityWeather();
	}

}

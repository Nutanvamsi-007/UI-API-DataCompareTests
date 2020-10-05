package com.ndtv.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testvagrant.models.CityWeather;
import com.testvagrant.testsetup.SetupBaseWebDriver;

public class NdtvCityWeatherContainer extends SetupBaseWebDriver{
	
	@FindBy(xpath="//div[@class=\"leaflet-popup-content\"]/div/span/b")
	private List<WebElement> detailsContainer;
	
	private String cityName;
	
	private CityWeather cityWeather;

	public NdtvCityWeatherContainer(String cityName) {
		this.cityName=cityName;
		PageFactory.initElements(driver, this);
	}
	
	
	public CityWeather getCityWeatherDetails() {
		cityWeather=new CityWeather();
		cityWeather.setCityName(cityName);
		List<String> detailsList = new ArrayList<String>();
		for(int i=0;i< detailsContainer.size();i++) {
			detailsList.add(detailsContainer.get(i).getText());
		}
		for(int i=0;i< detailsList.size();i++) {
			if(detailsList.get(i).contains("Condition : ")) {
				cityWeather.setCondition(detailsList.get(i).replaceAll("Condition : ", ""));
			}
			if(detailsList.get(i).contains("Humidity: ")) {
				cityWeather.setHumidity(Float.parseFloat(detailsList.get(i).replaceAll("Humidity: ", "").replaceAll("%", "")));		
			}
			if(detailsList.get(i).contains("Temp in Degrees: ")) {
				cityWeather.setTempCelcius(Float.parseFloat(detailsList.get(i).replaceAll("Temp in Degrees: ", "")));
			}
			if(detailsList.get(i).contains("Temp in Fahrenheit: ")) {
				cityWeather.setTempFarnhit(Float.parseFloat(detailsList.get(i).replaceAll("Temp in Fahrenheit: ", "")));
			}
		}
		return cityWeather;
	}
}

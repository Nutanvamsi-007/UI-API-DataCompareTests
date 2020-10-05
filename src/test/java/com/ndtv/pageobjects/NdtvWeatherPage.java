package com.ndtv.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testvagrant.datacomparator.WeatherDataComparator_NDTV_OpenWeather;
import com.testvagrant.testsetup.SetupBaseWebDriver;

public class NdtvWeatherPage extends SetupBaseWebDriver {
	
	private static final Logger logger = LogManager.getLogger(NdtvWeatherPage.class);
	
	@FindBy(id="searchBox")
	private WebElement searchBox;
	
	@FindBy(id="map_canvas")
	private WebElement mapCanvas;
	
	@FindBy(xpath=".//*[@id=\"messages\"]/div[*]/label")
	private List<WebElement> cityOptions;

	@FindBy(xpath=".//input[@type='checkbox' and @checked='checked']")
	private List<WebElement> selectedCities;
	
	
	private List<String> availableCityNames;
	

	public NdtvWeatherPage() {
		PageFactory.initElements(driver, this);
		this.availableCityNames=getAllCityNames();
	}
	
	public boolean searchBoxEnabled() {
		return searchBox.isEnabled();
	}
	
	public boolean map_canvas_displayed() {
		return mapCanvas.isDisplayed();
	}
	
	public List<String> getAllCityNames(){
		List<String> availableCities = new ArrayList<String>();
		for(int i=0;i< cityOptions.size();i++) {
			availableCities.add(cityOptions.get(i).getText());
			//System.out.println(cityOptions.get(i).getText());
		}
		return availableCities;
	}
	
	public List<String> getSelectedCityNames(){
		List<String> selectedCitiesNames = new ArrayList<String>();
		for(int i=0;i< selectedCities.size();i++) {
			selectedCitiesNames.add(selectedCities.get(i).getText());
			//System.out.println(selectedCities.get(i).getText());
		}
		return selectedCitiesNames;
	}
	
	public List<String> getAvailableCityNames() {
		return availableCityNames;
	}
	
	public void setCityNameOnSearchBox(String city) throws Exception {
		if(!availableCityNames.contains(city)) {
			logger.error("City not in Ndvt Weather Page List of Cities");
			throw new Exception("City not in Ndvt Weather Page List of Cities");
		}
		searchBox.sendKeys(city);
		if(!driver.findElement(By.id(city)).isSelected()) {
			driver.findElement(By.id(city)).click();
		}
		
	}
	
	public boolean cityWeatherContainerOnMap(String city) {
		return driver.findElement(By.xpath("//*[@title='"+city+"']")).isDisplayed();
	}
	
	public String getCityDetailsFromContainer(String city) {
		driver.findElement(By.xpath("//*[@title='"+city+"']")).click();
		return driver.findElement(By.xpath("//span[contains(text(),'"+city+"')]")).getText();
	}
	
	public void uncheckSelectedCities() {
		for(int i=0;i< selectedCities.size();i++) {
				if( selectedCities.get(i).isSelected()) {
					selectedCities.get(i).click();
				}
		}
	}
	
	public NdtvCityWeatherContainer getCityTemperaturepDetailsFromContainer(String city) {
		driver.findElement(By.xpath("//*[@title='"+city+"']")).click();
		return new NdtvCityWeatherContainer(city);
	}


}

package com.ndtv.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testvagrant.testsetup.SetupBaseWebDriver;

public class NdtvWeatherPage extends SetupBaseWebDriver {
	
	@FindBy(id="searchBox")
	private WebElement searchBox;
	
	@FindBy(id="map_canvas")
	private WebElement mapCanvas;
	
	@FindBy(xpath=".//*[@id=\"messages\"]/div[*]/label")
	private List<WebElement> cityOptions;

	
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
	
	public List<String> getAvailableCityNames() {
		return availableCityNames;
	}
	
	public void setCityNameOnSearchBox(String city) throws InterruptedException {
		searchBox.sendKeys(city);
		if(!driver.findElement(By.id(city)).isSelected()) {
			Thread.sleep(2000);
			driver.findElement(By.id(city)).click();
		}
		
	}
	
	public boolean cityWeatherContainerOnMap(String city) {
		return driver.findElement(By.xpath("//*[@title='"+city+"']")).isDisplayed();
	}
	
	public String getCityTempDetailsFromContainer(String city) {
		driver.findElement(By.xpath("//*[@title='"+city+"']")).click();
		return driver.findElement(By.xpath("//span[contains(text(),'"+city+"')]")).getText();
	}

}

package com.ndtv.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NdtvHomePage {
	
	private WebDriver driver;

	@FindBy(id="h_sub_menu")
	private WebElement subMenu;
	
	@FindBy(linkText="WEATHER")
	private WebElement weather;
	
	public NdtvHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public String getHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean weatherLinkAvailable() {
		subMenu.click();
		return weather.isDisplayed();
	}
	
	public NdtvWeatherPage accessWeather() {
		subMenu.click();
		weather.click();
		return new NdtvWeatherPage(driver);
	}
}

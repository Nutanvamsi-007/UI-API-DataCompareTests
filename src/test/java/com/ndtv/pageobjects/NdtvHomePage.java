package com.ndtv.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testvagrant.testsetup.SetupBaseWebDriver;
import com.testvagrant.testsetup.WebDriverUtils;

public class NdtvHomePage extends SetupBaseWebDriver {
	
	
	@FindBy(css=".notnow")
	private WebElement noThanks;

	@FindBy(id="h_sub_menu")
	private WebElement subMenu;
	
	@FindBy(linkText="WEATHER")
	private WebElement weather;
	
	public NdtvHomePage() {
		PageFactory.initElements(driver, this);
		handleNdtvHomePage();
	}
	
	void handleNdtvHomePage() {
		if(noThanks.isDisplayed()) {
			noThanks.click();
		}
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
		WebDriverUtils.waitForPageLoaded(driver);
		return new NdtvWeatherPage();
	}
}

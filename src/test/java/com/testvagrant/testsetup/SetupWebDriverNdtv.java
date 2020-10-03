package com.testvagrant.testsetup;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SetupWebDriverNdtv {
	
	static SetupBaseWebDriver baseWebDriver = new SetupBaseWebDriver();
	protected static WebDriver driver=baseWebDriver.getDriver();
	
	@BeforeAll
	static void handleNdtvHomePage() {
		WebElement noThanks = driver.findElement(By.cssSelector(".notnow"));
		if(noThanks.isDisplayed()) {
			noThanks.click();
		}
	}
	
	@AfterAll
	static void closeAll() {
		baseWebDriver.close();
	}
	
	protected WebDriver getDriver() {
		return driver;
	}
	


}

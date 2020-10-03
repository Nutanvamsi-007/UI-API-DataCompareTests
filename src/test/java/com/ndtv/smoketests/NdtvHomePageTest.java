package com.ndtv.smoketests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.testvagrant.testsetup.SetupWebDriverNdtv;

public class NdtvHomePageTest extends SetupWebDriverNdtv {
	
	private WebDriver driver=super.getDriver();
	
	@Test
	void verify_NavElement_forWeather_exists() {
		driver.findElement(By.id("h_sub_menu")).click();;
		driver.findElement(By.linkText("WEATHER")).click();
	}

}

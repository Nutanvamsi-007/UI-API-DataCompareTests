package com.ndtv.smoketests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.ndtv.pageobjects.NdtvHomePage;
import com.testvagrant.testsetup.SetupWebDriverNdtv;

public class NdtvHomePageTest extends SetupWebDriverNdtv {
	
	private WebDriver driver=super.getDriver();
	NdtvHomePage homePage = new NdtvHomePage(driver);
	
	
	@Test
	void verify_HomePageTitle() {
		assertEquals(homePage.getHomePageTitle(), "NDTV: Latest News, India News, Breaking News, Business, Bollywood, Cricket, Videos & Photos");
	}
	
	@Test
	void verify_NavElement_forWeather_exists() {
		assertTrue(homePage.weatherLinkAvailable());
	}

}

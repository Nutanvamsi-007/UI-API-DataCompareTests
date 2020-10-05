package com.ndtv.smoketests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ndtv.pageobjects.NdtvHomePage;
import com.ndtv.pageobjects.NdtvWeatherPage;
import com.testvagrant.testsetup.SetupBaseWebDriver;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Feature("Home Page Smoke Tests")
public class NdtvHomePageTest extends SetupBaseWebDriver {
	
	NdtvHomePageTest() {
		super();
	}

	NdtvHomePage homePage;
	NdtvWeatherPage weatherPage;
	
	@BeforeEach
	void setup() {
		initialize();
		homePage = new NdtvHomePage();
	}
	
	
	@Test()
	@Story("Ndtv should have correct Title")
	@Description("This Test validate correct Home Page Titile")
	void verify_HomePageTitle() {
		assertEquals(homePage.getHomePageTitle(), "NDTV: Latest News, India News, Breaking News, Business, Bollywood, Cricket, Videos & Photos");
	}
	
	@Test
	@Story("Ndtv should have Nav element for Weather Page Navigation")
	@Description("This Test validates WEATHER Nav Element is present")
	void verify_NavElement_forWeather_exists() {
		assertTrue(homePage.weatherLinkAvailable());
	}

}

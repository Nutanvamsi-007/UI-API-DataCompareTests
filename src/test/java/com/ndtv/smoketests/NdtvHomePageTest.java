package com.ndtv.smoketests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ndtv.pageobjects.NdtvHomePage;
import com.ndtv.pageobjects.NdtvWeatherPage;
import com.testvagrant.testsetup.SetupBaseWebDriver;

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
	
	
	@Test
	void verify_HomePageTitle() {
		assertEquals(homePage.getHomePageTitle(), "NDTV: Latest News, India News, Breaking News, Business, Bollywood, Cricket, Videos & Photos");
	}
	
	@Test
	void verify_NavElement_forWeather_exists() {
		assertTrue(homePage.weatherLinkAvailable());
	}

}

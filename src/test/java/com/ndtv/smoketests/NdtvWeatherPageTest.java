package com.ndtv.smoketests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.ndtv.pageobjects.NdtvHomePage;
import com.ndtv.pageobjects.NdtvWeatherPage;
import com.testvagrant.testsetup.SetupBaseWebDriver;

public class NdtvWeatherPageTest extends SetupBaseWebDriver  {
	
	NdtvWeatherPageTest() {
		super();
	}

	NdtvHomePage homePage;
	NdtvWeatherPage weatherPage;
	
	@BeforeEach
	void setup() {
		initialize();
		homePage = new NdtvHomePage();
		weatherPage = homePage.accessWeather();
		
	}
	
	@Test
	void verify_seachBox_enabled() {
		assertTrue(weatherPage.searchBoxEnabled());
	}
	
	@Test
	void verify_Map_canvas_displayed() {
		assertTrue(weatherPage.map_canvas_displayed());
	}
	
	@Test
	void verify_city_search_options_available() {
		assertTrue(weatherPage.getAvailableCityNames().size() > 0);
	}
	

}

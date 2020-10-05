package com.ndtv.smoketests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ndtv.pageobjects.NdtvHomePage;
import com.ndtv.pageobjects.NdtvWeatherPage;
import com.testvagrant.testsetup.SetupBaseWebDriver;

import io.qameta.allure.Description;
import io.qameta.allure.Story;

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
	@Story("Ndtv Weather Page should have a search box to enter City")
	@Description("This Test validates Weather Page has a City Seach Box enabled")
	void verify_seachBox_enabled() {
		assertTrue(weatherPage.searchBoxEnabled());
	}
	
	@Test
	@Story("Ndtv Weather Page should have a World Map Canvas")
	@Description("This Test validates a Map canvas is displayed")
	void verify_Map_canvas_displayed() {
		assertTrue(weatherPage.map_canvas_displayed());
	}
	
	@Test
	@Story("Ndtv Weather Page on loading should have list of Cities List for search")
	@Description("This Test validates list of Cities List for search are available")
	void verify_city_search_options_available() {
		assertTrue(weatherPage.getAvailableCityNames().size() > 0);
	}
	

}

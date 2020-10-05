package com.ndtv.smoketests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.ndtv.pageobjects.NdtvHomePage;
import com.ndtv.pageobjects.NdtvWeatherPage;
import com.testvagrant.testsetup.SetupBaseWebDriver;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Feature("Ndtv Weather Page specific City Smoke Tests")
public class NdtvWeatherPageCityTest extends SetupBaseWebDriver  {
	
	NdtvWeatherPageCityTest() {
		super();
	}

	NdtvHomePage homePage;
	NdtvWeatherPage weatherPage;
	
	@BeforeEach
	void setup() {
		initialize();
		homePage = new NdtvHomePage();
		weatherPage = homePage.accessWeather();
		// weatherPage = new NdtvWeatherPage();
	}
	
	@ParameterizedTest
	@Story("Given city if supported by Ndtv Weather should be displayed on Map")
	@Description("This Test validates City with Temperature details is present on Map canvas")
	@CsvFileSource(resources = "/cities.csv", numLinesToSkip = 1)
	void verify_city_available_on_map_with_temperature(String city) throws Exception {
		weatherPage.setCityNameOnSearchBox(city);
		assertTrue(weatherPage.cityWeatherContainerOnMap(city));
	}
	
	@ParameterizedTest
	@Story("Given city displayed on Map, clicking on it should reveal City Details container on Map")
	@Description("This Test validates City Details container on Map")
	@CsvFileSource(resources = "/cities.csv", numLinesToSkip = 1)
	void verify_clciking_city_on_map_shows_details(String city) throws Exception  {
		weatherPage.setCityNameOnSearchBox(city);
		assertTrue(weatherPage.getCityDetailsFromContainer(city).contains(city));
	}

}

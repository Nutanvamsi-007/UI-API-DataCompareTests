package com.testvagrant.datacomparator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.ndtv.pageobjects.NdtvCityWeatherContainer;
import com.ndtv.pageobjects.NdtvHomePage;
import com.ndtv.pageobjects.NdtvWeatherPage;
import com.testvagrant.models.CityWeather;
import com.testvagrant.testsetup.ParamsAUT;
import com.testvagrant.testsetup.SetupBaseWebDriver;
import com.testvagrant.testsetup.SetupExtensionOpenWeatherAPI;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@ExtendWith(SetupExtensionOpenWeatherAPI.class)
public class WeatherDataComparator_NDTV_OpenWeather extends SetupBaseWebDriver{
	
	private static final Logger logger = LogManager.getLogger(WeatherDataComparator_NDTV_OpenWeather.class);
	
	private String metricForTest=ParamsAUT.getInstance().getValue("temp-metric");
	private float variance=Float.parseFloat(ParamsAUT.getInstance().getValue("temp-vairnace"));
	private String apiKey=ParamsAUT.getInstance().getValue("wather-api-key");
	
	WeatherDataComparator_NDTV_OpenWeather(){
		super();
	}
	
	NdtvHomePage homePage;
	NdtvWeatherPage weatherPage;
	NdtvCityWeatherContainer cityWeatherContainer;
	CityWeather cityWeatherUI,cityWeatherApi;
	
	@BeforeEach
	void setup() {
		initialize();
		homePage = new NdtvHomePage();
		weatherPage = homePage.accessWeather();
		//weatherPage = new NdtvWeatherPage();
		RestAssured.basePath=ParamsAUT.getInstance().getValue("api-base-path-weather-data");
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/cities.csv", numLinesToSkip = 1)
	void compare_Temperature_ndtvui_openweatherapi_within_givenVariance(String city) {

		
		//NDTV Weather Data for City
		weatherPage.uncheckSelectedCities();
		weatherPage.setCityNameOnSearchBox(city);
		cityWeatherContainer=weatherPage.getCityTemperaturepDetailsFromContainer(city);
		cityWeatherUI=cityWeatherContainer.getCityWeatherDetails();
		logger.info("Ndtv reported "+city+" : "+ cityWeatherUI.getTempCelcius() +"C; "+ cityWeatherUI.getTempFarnhit() +"F;");
		
		//OpenWeatherApi Weather Data for City
		String metric = metricForTest.equalsIgnoreCase("Celsius") ? "metric" :  "imperial";

		Response response = given().params("q", city, "appid",apiKey,"units", metric).when().get().then().log().ifValidationFails().statusCode(200).extract().response();
		
		//Validate city name matches between API and UI
		cityWeatherApi=new CityWeather();
		cityWeatherApi.setCityName(response.path("name").toString());
		assertEquals(cityWeatherApi.getCityName(), cityWeatherUI.getCityName());
		
		//Validate temperature matches between API and UI
		Float temeperature = Float.parseFloat(response.path("main.temp").toString());
		if(metricForTest.equalsIgnoreCase("Celsius")) {
			cityWeatherApi.setTempCelcius(temeperature);
			logger.info("OpenWather reported "+city+" : "+cityWeatherApi.getTempCelcius()+"C;");
			assertThat(cityWeatherApi.getTempCelcius(), either(is(greaterThan((cityWeatherUI.getTempCelcius()-variance)))).or(is(lessThan((cityWeatherUI.getTempCelcius()+variance)))));
		}else if(metricForTest.equalsIgnoreCase("Fharenheit")) {
			cityWeatherApi.setTempFarnhit(temeperature);
			logger.info("OpenWather reported "+city+" : "+cityWeatherApi.getTempFarnhit()+"F;");
			assertThat(cityWeatherApi.getTempCelcius(), either(is(greaterThan((cityWeatherUI.getTempFarnhit()-variance)))).or(is(lessThan((cityWeatherUI.getTempFarnhit()+variance)))));
		}
	
	}

}

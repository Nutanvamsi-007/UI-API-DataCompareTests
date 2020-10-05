package com.testvagrant.datacomparator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.ndtv.pageobjects.NdtvHomePage;
import com.ndtv.pageobjects.NdtvWeatherPage;
import com.testvagrant.testsetup.ParamsAUT;
import com.testvagrant.testsetup.SetupBaseWebDriver;
import com.testvagrant.testsetup.SetupExtensionOpenWeatherAPI;

import io.restassured.RestAssured;

@ExtendWith(SetupExtensionOpenWeatherAPI.class)
public class WeatherDataComparator_NDTV_OpenWeather extends SetupBaseWebDriver{
	
	private String metric=ParamsAUT.getInstance().getValue("temp-metric");
	private float variance=Float.parseFloat(ParamsAUT.getInstance().getValue("temp-vairnace"));
	private String apiKey=ParamsAUT.getInstance().getValue("wather-api-key");
	
	WeatherDataComparator_NDTV_OpenWeather(){
		super();
	}
	
	NdtvHomePage homePage;
	NdtvWeatherPage weatherPage;
	
	@BeforeEach
	void setup() {
		initialize();
		homePage = new NdtvHomePage();
		weatherPage = homePage.accessWeather();
		
		RestAssured.basePath=ParamsAUT.getInstance().getValue("api-base-path-weather-data");
		
	}
	
	@Test
	void compare_Temperature_ndtvui_openweatherapi() {
		if(metric.equalsIgnoreCase("Celsius")) {
			metric = "metric";
		}else if(metric.equalsIgnoreCase("Fahrenheit")){
			metric = "imperial";
		}
		
		given()
			.params("q", "Bengaluru", "appid",apiKey,"units", metric)
		.when()
			.get()
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.body(containsString("main"),containsString("temp"));
	}

}

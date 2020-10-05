package com.openweatherapi.smoketests;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.testvagrant.testsetup.SetupExtensionOpenWeatherAPI;
import com.testvagrant.testsetup.ParamsAUT;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

@ExtendWith(SetupExtensionOpenWeatherAPI.class)
public class OpeanWeatherResponseDataTest {
	
	private String apiKey=ParamsAUT.getInstance().getValue("wather-api-key");
	
	@BeforeAll
	static void setup() {
		RestAssured.basePath=ParamsAUT.getInstance().getValue("api-base-path-weather-data");
	}
	
	@Test
	void verify_response_byCityName_isOK_hasSameCityName() {
		
		given()
			.params("q", "Bengaluru", "appid",apiKey )
		.when()
			.get()
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.and()
			.body("name", equalTo("Bengaluru"));
			
		
	}
	
}

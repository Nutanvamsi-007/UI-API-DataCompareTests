package com.openweatherapi.smoketests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.testvagrant.testsetup.ParamsAUT;
import com.testvagrant.testsetup.SetupExtensionOpenWeatherAPI;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.RestAssured;

@ExtendWith(SetupExtensionOpenWeatherAPI.class)
public class OpenWeatherResponseSchemaTest {
	
	private String apiKey=ParamsAUT.getInstance().getValue("wather-api-key");
	
	@BeforeAll
	static void setup() {
		RestAssured.basePath=ParamsAUT.getInstance().getValue("api-base-path-weather-data");
	}
	
	@Test
	@Story("Request to OpenWeatherApi current Data API with a City Name responds with Key Information")
	@Description("This Test validates response has City Temperature ")
	void verify_response_has_keyFields() {
		given()
			.params("q", "Bengaluru", "appid",apiKey )
		.when()
			.get()
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.body(containsString("main"),containsString("temp"));
	}


}

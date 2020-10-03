package com.testvagrant.testsetup;

import io.restassured.RestAssured;

public class SetupExtensionOpenWeatherAPI extends SetupExtensionBase {

	@Override
	void setup() {

		RestAssured.baseURI=ParamsAUT.getInstance().getValue("api-base-url");
		
	}
	
	@Override
	  public void close() throws Throwable {}

}

# UI-API-DataCompareTests
This repo serves as a Test Framework both Selenium UI and RestAssured API Test Scripts. A Data comparison between UI elements and API responses is also configured.  

## Setup Requirements

####  Must

* Java 1.8 or above (add Java 'bin' directory to Environment 'path' variable)

####  Optional ones are only required if you want to run from an IDE Environment or to view Allure Reports

* Any Java supported IDE
* Maven (add Maven 'bin' directory to Environment 'path' variable)
   *  Note: Maven Wrapper is already provided with this repo
* Allure Command Line Tool (Download form here https://docs.qameta.io/allure/ and add allure 'bin' directory to Environment 'path' variables)

## Executing Tests in the Test Framework

###   Clone Repository

Clone this Repo to your local or remote environment

###   Test Data Setup

* Add the City Names for testing to csv File: /src/test/resources/cities.csv
* Temperature metric and threshold variance can be specified in File: /src/test/resources/TemperatureVariance.properties
  * Valid values for 'temp-metric' are either 'Celsius' or 'Fahrenheit'
  * Threshold variance support double Data Type

###   Direct command-line/shell execution steps

To execute all Tests, from Project main direcoty:
```sh
$ mvnw test
```

To Execute Tests for Ndtv UI vs OpenWeatherApi API Weather Data comparision:
```sh
$ mvnw test -Dtest=WeatherDataComparator_NDTV_OpenWeather
```

To execute any Test Class:
```sh
$ mvnw test -Dtest=<TestClassName>
```

To execute any specific methods in any Test Class:
```sh
$ mvnw test -Dtest=<TestClassName>#<TestName>
```
  
###   Executing from Java IDE

Import as a Maven Project into the IDE of your choice and:
  * RunAs Junit5 Test
  * Run 'mvn clean test' or 'mvnw clean test' (mvnw should be preferred)

## Accessing Allure Reports after Test Execution

> As mentioned in Requirements, Allure Command Line Tool is required on your machine.

From command-line/shell:
```sh
$ allure serve <Absolute Path to Allure Results direcotory Ex: C:\Projects\UI-API-DataCompareTests\allure-results> 
```

package com.testvagrant.testsetup;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetupBaseWebDriver {
	
	protected WebDriver driver;
	
	SetupBaseWebDriver() {
		
		String targetBrowser=ParamsAUT.getInstance().getValue("browser-name");
		switch (targetBrowser) {
			case "Chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "Firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "IE":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
			case "Edge-Chromium":
				WebDriverManager.chromiumdriver().setup();
				driver = new EdgeDriver();
				break;
			default:
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
		}
		
		driver.manage().window().maximize();
		driver.get(ParamsAUT.getInstance().getValue("ui-base-url"));
		waitForPageLoaded();
	}
	

	void close() {
		if(driver!=null) {
			driver.close();
			driver.quit();
		}
	}
	
	void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println("Timeout waiting for Page Load Request to complete.");
		}
	}

	protected WebDriver getDriver() {
		return driver;
	}
	
	

}

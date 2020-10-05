package com.testvagrant.testsetup;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetupBaseWebDriver {
	
	protected static WebDriver driver;
	
	protected SetupBaseWebDriver() {

	}
	
	public static void initialize() {
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
		WebDriverUtils.waitForPageLoaded(driver);
	}
	
	@AfterEach
	public void close() {
		if(driver!=null) {
			driver.close();
			driver.quit();
		}
	}
	
	
	

}

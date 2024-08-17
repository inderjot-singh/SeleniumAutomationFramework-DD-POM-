package com.mavenstore.testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.mavenstore.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();

	String baseURL = readConfig.getbaseURL();

	String browser = readConfig.getBrowser();

	public static Logger logger;

	public static WebDriver driver;

	@BeforeClass
	public void setup() {

		switch (browser.toLowerCase()) {

		case "firefox":

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();
			
			break;

		case "chrome":

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
			
			break;
			
		default:
			
			driver = null;
			
			break;	
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		logger = LogManager.getLogger("MavenStoreV1");
		
		logger.info(browser + " browser opened");
	}
	
	@AfterClass
	public void tearDown() {
		
		//driver.close();
		
		driver.quit();
		
		logger.info(browser + " browser closed.");
	}
	
	public void captureScreenShot(WebDriver driver, String testName) throws IOException {
		
		TakesScreenshot ss = (TakesScreenshot) driver;
		
		File src = ss.getScreenshotAs(OutputType.FILE);
		
		File dest = new File(System.getProperty("user.dir") + "\\screenshots\\" + testName + ".png");
		
		FileUtils.copyFile(src, dest);
	}

}

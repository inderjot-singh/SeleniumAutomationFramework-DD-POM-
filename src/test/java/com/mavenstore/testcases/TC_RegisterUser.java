package com.mavenstore.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mavenstore.pageobjects.HomePage;

public class TC_RegisterUser extends BaseClass{
	
	@Test
	public void registerUser() throws IOException {
		
		HomePage homepage = new HomePage(driver);
		
		driver.get(baseURL);
		
		logger.info(baseURL + " url opened.");
		
		//Assert.assertEquals(driver.getCurrentUrl(), "https://www.automationexercise.com/login");
		
		//captureScreenShot(driver, "loginpage");
		
		homepage.clickOnLogin();
		
		if(driver.getCurrentUrl().equals("https://www.automationexercise.com/login")) {
			
			logger.info("Login/Signup Button clicked.");
			logger.info("registerUser passed");
			Assert.assertTrue(true);
		}
		else {
			logger.info("registerUser failed");
			captureScreenShot(driver, "registerUser");
			Assert.assertTrue(false);
		}
		
	}

}

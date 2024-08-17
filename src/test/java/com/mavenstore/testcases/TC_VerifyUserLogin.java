package com.mavenstore.testcases;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import com.mavenstore.pageobjects.HomePage;
import com.mavenstore.pageobjects.LoggedInHomePage;
import com.mavenstore.pageobjects.LoginPage;

import junit.framework.Assert;

public class TC_VerifyUserLogin extends BaseClass {

	@Test
	public void verifyUserLogin() throws IOException {

		driver.get(baseURL);
		logger.info(baseURL + " url opened.");

		HomePage homePage = new HomePage(driver);
		homePage.clickOnLogin();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail("testuser01@email.com");
		loginPage.enterPassword("testuser01");
		loginPage.clickLoginButton();

		LoggedInHomePage loggedInHomePage = new LoggedInHomePage(driver);

		try {
			if (loggedInHomePage.verifyUsername("TestUser01") == true) {

				logger.info("Username matched on home page");
				Assert.assertTrue(true);
			} /*
				 * else { logger.info("Username not matched on home page");
				 * captureScreenShot(driver, "verifyUserLogin"); Assert.assertTrue(false); }
				 */
		} catch (NoSuchElementException e) {
			logger.info("Username not matched on home page");
			captureScreenShot(driver, "verifyUserLogin");
			Assert.assertTrue(false);
		}
	}

}

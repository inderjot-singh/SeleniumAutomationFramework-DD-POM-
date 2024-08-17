package com.mavenstore.testcases;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mavenstore.pageobjects.HomePage;
import com.mavenstore.pageobjects.LoggedInHomePage;
import com.mavenstore.pageobjects.LoginPage;
import com.mavenstore.utilities.ReadExcelFile;

import junit.framework.Assert;

public class TC_VerifyUserLoginDataDriven extends BaseClass {

	@Test(dataProvider = "LoginDataProvider")
	public void verifyUserLogin(String userEmail, String userPwd, String expectedUsername) throws IOException {

		driver.get(baseURL);
		logger.info(baseURL + " url opened.");

		HomePage homePage = new HomePage(driver);
		homePage.clickOnLogin();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail(userEmail);
		loginPage.enterPassword(userPwd);
		loginPage.clickLoginButton();
		
		LoggedInHomePage loggedInHomePage = new LoggedInHomePage(driver);

		try {
			if (loggedInHomePage.verifyUsername(expectedUsername) == true) {

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
		
		loggedInHomePage.clickLogout();
	}

	@DataProvider(name = "LoginDataProvider")
	public String[][] LoginDataProvider() {

		String fileName = System.getProperty("user.dir") + "\\TestData\\MavenStoreTestData.xlsx";
		int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginTestData");
		int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginTestData");
		String data[][] = new String[ttlRows - 1][ttlColumns];
		for (int i = 1; i < ttlRows; i++)// rows =1,2
		{
			for (int j = 0; j < ttlColumns; j++)// col=0, 1,2
			{
				data[i - 1][j] = ReadExcelFile.getCellValue(fileName, "LoginTestData", i, j);
			}
		}
		return data;
	}
}

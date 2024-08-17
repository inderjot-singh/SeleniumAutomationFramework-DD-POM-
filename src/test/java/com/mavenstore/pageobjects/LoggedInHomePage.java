package com.mavenstore.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedInHomePage {

	WebDriver ldriver;

	public LoggedInHomePage(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	public String getUsername(String userName) {

		String userxpath = "//b[normalize-space()='" + userName + "']";
		return userxpath;
	}

	public Boolean verifyUsername(String userName) {

		Boolean isDispalyed = ldriver.findElement(By.xpath(getUsername(userName))).isDisplayed();
		return isDispalyed;
	}

	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement logoutButton;

	public void clickLogout() {

		logoutButton.click();
	}
}

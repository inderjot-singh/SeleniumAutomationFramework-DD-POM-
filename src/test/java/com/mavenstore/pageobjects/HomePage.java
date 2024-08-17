package com.mavenstore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver ldriver; // create object of WebDriver

	public HomePage(WebDriver rdriver) {

		ldriver = rdriver;

		PageFactory.initElements(rdriver, this); // create constructor of class HomePage
	}

	@FindBy(linkText = "Signup / Login")
	WebElement login; // find element by PageFactory method

	public void clickOnLogin() {

		login.click();
	}

}

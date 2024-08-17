package com.mavenstore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//input[@data-qa='login-email']")
	WebElement loginEmail;

	public void enterEmail(String email) {

		loginEmail.sendKeys(email);
	}

	@FindBy(xpath = "//input[@data-qa='login-password']")
	WebElement loginPassword;

	public void enterPassword(String password) {

		loginPassword.sendKeys(password);
	}

	@FindBy(xpath = "//button[@data-qa='login-button']")
	WebElement loginButton;

	public void clickLoginButton() {

		loginButton.click();
	}
}

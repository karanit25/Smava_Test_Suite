package com.smava.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.smava.base.TestBase;

public class LoginPage extends TestBase {
	
	// Page Factory - Object Repo

	@FindBy(id = "applicant0.loginForm.email")
	WebElement inputemailID;

	@FindBy(id = "applicant0.loginForm.password")
	WebElement inputPassword;

	@FindBy(xpath = "//button[@class='button login-form__submit']")
	WebElement loginButton;

	@FindBy(id = "forwardButtonkredit2day")
	WebElement credit2dayContinue;

	@FindBy(xpath = "//div[contains(text(),'E-Mail darf nicht leer sein.')]")
	WebElement incorrectMailIdMsg;

	@FindBy(xpath = "//div[contains(text(),'Passwort darf nicht leer sein.')]")
	WebElement incorrectPasswordMsg;

	@FindBy(xpath = "//span[contains(text(),'Ihre Benutzername oder Passwort ist falsch')]")
	WebElement incorrectLoginInfo;
	
	// Initialize the page objects

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Set email id and password

	public void setUserInfo(String email,String password) {
		inputemailID.sendKeys(email);
		inputPassword.sendKeys(password);

	}
	
	//Click on login button will display error message for incorrect login info 

	public String clickOnAnmeldenSubmit() throws InterruptedException {
		loginButton.click();
		waitForvisibilityOf(incorrectLoginInfo);
		String msg = incorrectLoginInfo.getText();
		return msg;
		

	}

}

package com.smava.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.smava.base.TestBase;

public class LoanPersonInfoPage extends TestBase {
	
	// Page Factory - Object Repo

	@FindBy(xpath = "//span[contains(text(),'Anmelden »') and @class='login-form__link']")
	WebElement anmeldenLink;

	@FindBy(xpath = "//span[contains(text(),'Schon smava Kunde?')]")
	WebElement alreadyCustomerQ;

	@FindBy(id = "applicant0.personal.salutation-MR")
	WebElement salutationMR;

	@FindBy(id = "applicant0.personal.salutation-MRS")
	WebElement salutationMRS;

	@FindBy(id = "applicant0.personal.firstName")
	WebElement firstName;

	@FindBy(id = "applicant0.personal.lastName")
	WebElement lastName;

	@FindBy(id = "applicant0.personal.birthDate")
	WebElement birthDate;

	@FindBy(id = "applicant0.contacts.phoneMobile")
	WebElement phoneMobile;

	@FindBy(id = "applicant0.contacts.email")
	WebElement eMail;

	@FindBy(id = "applicant0.conditions.schufaAgreementAccepted")
	WebElement schufaAgreementAccepted;

	@FindBy(id = "applicant0.conditions.newsletterSubscription")
	WebElement newsletterSubscription;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitButton;
	
	// Initialize the page objects

	public LoanPersonInfoPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Verify Page Title and Lable specified "already customer"

	public String verifyPersonPageTitle() {
		String title = driver.getTitle();
		Assert.assertTrue(alreadyCustomerQ.isDisplayed());
		return title;

	}
	
	//Click on Login link will navigate to Login Page

	public LoginPage clickOnAnmelden() {
		anmeldenLink.click();
		return new LoginPage();
	}
}

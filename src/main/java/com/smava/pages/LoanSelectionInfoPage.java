package com.smava.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.smava.base.TestBase;

public class LoanSelectionInfoPage extends TestBase {
	
	// Page Factory - Object Repo

	@FindBy(id = "applicant0.loan.selection.category")
	WebElement selectedCategory; 

	@FindBy(id = "applicant0.loan.selection.amount")
	WebElement selectedAmount; 

	@FindBy(id = "applicant0.loan.selection.duration")
	WebElement selectedDuration; 

	@FindBy(xpath = "//a[@id='applicant0.applicantsCount-1' and @type='button']")
	WebElement noBorrowers;

	@FindBy(xpath = "//a[@id='applicant0.applicantsCount-2' and @type='button']")
	WebElement twoBorrowers;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement nextButton;

	@FindBy(xpath = "//img[@src='/Bilder/logobank/kredit2day.png']")
	WebElement kredit2dayLogo;
	
	// Initialize the page objects

	public LoanSelectionInfoPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Verify Page Title

	public String verifyLoanSelectionPageTitle() {
		String title = driver.getTitle();
		return title;
			}
	
	//Verify Loan Provider Logo

	public boolean verifyLoanProvider() {
		boolean logo = kredit2dayLogo.isDisplayed();
		return logo;
		

	}
	//Verify selected options on Home Page
		
	public String verifyLoanSelectedCategory() {
		String category = new Select(selectedCategory).getFirstSelectedOption().getText().toString();
		return category;
	}
	
	public String verifyLoanSelectedAmount() {
		String amount = new Select(selectedAmount).getFirstSelectedOption().getText().toString();
		return amount;
	}
	
	public String verifyLoanSelectedDuration() {
		String duration = new Select(selectedDuration).getFirstSelectedOption().getText().toString();
		return duration;
	}
	
	//This page will navigate to LoanPersonInfoPage

	public LoanPersonInfoPage clickOnNext() {
		nextButton.click();
		return new LoanPersonInfoPage();

	}

}

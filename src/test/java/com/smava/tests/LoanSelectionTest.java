package com.smava.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.smava.base.TestBase;
import com.smava.pages.HomePage;
import com.smava.pages.LoanPersonInfoPage;
import com.smava.pages.LoanSelectionInfoPage;
import com.smava.pages.LoginPage;
import com.smava.util.TestUtil;

public class LoanSelectionTest extends TestBase {
	
	HomePage homePage;
	LoanSelectionInfoPage loanSelectionInfoPage;
	LoanPersonInfoPage loanPersonInfoPage;
	LoginPage loginPage;
	TestUtil testUtil;
	
	public LoanSelectionTest(){
		// call base class constructor
		super(); 
	}
	
	@BeforeMethod
	//Webdriver Setup and initialize page objects
	public void setup(){
		initialization();
		homePage = new HomePage();
		loanSelectionInfoPage = new LoanSelectionInfoPage();
		loanPersonInfoPage = new LoanPersonInfoPage();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		
	}
	
	@Test
	public void verifyLoanSelectionTest() throws Exception{
		//Home Page Assertions
		String titleHome = homePage.validateHomePageTitle();
		Assert.assertEquals(titleHome, "SMAVA - Das Online-Vergleichsportal für Kredite | SMAVA");
		
		//Select Loan options
		homePage.setLoanAttributes(prop.getProperty("amountNum"),prop.getProperty("durationNum"),prop.getProperty("useNum"));
		loanSelectionInfoPage = homePage.selectKredit2Day();
		
		//Loan Selection Page assertion 
		String titleLoanSelect = loanSelectionInfoPage.verifyLoanSelectionPageTitle();
		Assert.assertEquals(titleLoanSelect, "smava Kreditantrag");
		
		//Verify Loan Provider by logo
		boolean logo = loanSelectionInfoPage.verifyLoanProvider();
		Assert.assertTrue(logo);
		
		//Verify selected loan attributes
		String selectedCategory = loanSelectionInfoPage.verifyLoanSelectedCategory();
		Assert.assertEquals(selectedCategory, prop.getProperty("category"));
		String selectedAmount = loanSelectionInfoPage.verifyLoanSelectedAmount();
		Assert.assertEquals(selectedAmount, prop.getProperty("amount"));
		String selectedDuration = loanSelectionInfoPage.verifyLoanSelectedDuration();
		Assert.assertEquals(selectedDuration, prop.getProperty("duration"));
		
		//Continue to Next Page
		loanPersonInfoPage = loanSelectionInfoPage.clickOnNext();
		
		//Person Info Page to fill data or Login
		String titlePersonPage = loanPersonInfoPage.verifyPersonPageTitle();
		Assert.assertEquals(titlePersonPage, "smava Kreditantrag");
		loginPage = loanPersonInfoPage.clickOnAnmelden();
		
		//Provide Login Info
		loginPage.setUserInfo(prop.getProperty("emailid"),prop.getProperty("password"));
		String errorMsg = loginPage.clickOnAnmeldenSubmit();
		
		//Unsuccessful login - check error message
		Assert.assertEquals(errorMsg, "Ihre Benutzername oder Passwort ist falsch");
		
	}
			
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}

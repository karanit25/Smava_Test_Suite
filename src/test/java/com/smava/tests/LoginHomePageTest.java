package com.smava.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.smava.base.TestBase;
import com.smava.pages.HomePage;
import com.smava.pages.LoginErrorPage;

public class LoginHomePageTest extends TestBase {
	
	HomePage homePage;
	LoginErrorPage loginErrorPage;
	
	public LoginHomePageTest(){
		// call base class constructor
		super(); 
	}
	
	@BeforeMethod
	//Webdriver Setup and initialize page objects
	public void setup(){
		initialization();
		homePage = new HomePage();
		loginErrorPage = new LoginErrorPage();
	}
	
	@Test
	public void verifyLoginHomePageTest() {
		//Home Page Assertions
		String titleHome = homePage.validateHomePageTitle();
		Assert.assertEquals(titleHome, "SMAVA - Das Online-Vergleichsportal für Kredite | SMAVA");
		
		//Login From Popup Window
		loginErrorPage = homePage.LoginPopup(prop.getProperty("emailid"),prop.getProperty("password"));
		
		//Login Error Page title verification
		String titleLoginErrorPage = loginErrorPage.verifyLoginErrorPageTitle();
		Assert.assertEquals(titleLoginErrorPage, "Sicheres Einloggen mit Zugangsdaten - smava.de");
		
		//Verify login error message displayed
		String loginErrorMessage = loginErrorPage.verifyErrorMessage();
		Assert.assertTrue(loginErrorMessage.contains("Ihre Angaben zum Einloggen sind ungültig"));
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}

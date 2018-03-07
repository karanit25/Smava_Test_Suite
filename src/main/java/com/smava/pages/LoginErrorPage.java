package com.smava.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.smava.base.TestBase;

public class LoginErrorPage extends TestBase {
	
	// Page Factory - Object Repo
	
	@FindBy(xpath = "//div[@class='box-inner']/ul/li")
	WebElement errorMessage;
	
	
	// Initialize the page objects
		public LoginErrorPage() {
			PageFactory.initElements(driver, this);
		}
		
		//Verify Page Title
		
		public String verifyLoginErrorPageTitle(){
			String title = driver.getTitle();
				return title;
		}
		//Verify Login Error message displayed on page
		
		public String verifyErrorMessage(){
			String msg = errorMessage.getText();
			return msg;
		}
			


}

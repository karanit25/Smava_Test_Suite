package com.smava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.smava.base.TestBase;

public class HomePage extends TestBase {

	// Page Factory - Object Repo

	@FindBy(id = "myselect")
	WebElement amountSelectBox;

	@FindBy(css = "div[data-value='2750']")
	WebElement scrollAmountWindow;

	@FindBy(id = "myselect2")
	WebElement durationSelectBox;

	@FindBy(css = "div[data-value='24']")
	WebElement scrollDurationWindow;

	@FindBy(id = "myselect3")
	WebElement useSelectBox;

	@FindBy(css = "div[data-value='886']")
	WebElement scrollUseWindow;

	@FindBy(xpath = "//a[@id='forwardButtonkredit2day']")
	WebElement credit2dayContinue;
	
	@FindBy(xpath = "//div[@class='menu transition visible']")
	WebElement visibilityCheck;
	
	@FindBy(xpath = "//a[text()='Anmelden' and @class='item login-popup uppercase']")
	WebElement loginLink;
	
	@FindBy(xpath = "//form[@class='signonForm signonFormPopup']/div/input[@id='signonForm.email']")
	WebElement emailTextPopup;
	
	@FindBy(xpath = "//form[@class='signonForm signonFormPopup']/div/input[@id='signonForm.password']")
	WebElement passwordTextPopup;
	
	@FindBy(xpath = "//form[@class='signonForm signonFormPopup']/button[@type='submit']")
	WebElement loginButtonPopup;
	
	@FindBy(xpath = "//a[@class='item login-popup uppercase visible']")
	WebElement popupVisible;
	

	// Initialize the page objects
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	//Verify Page Title

	public String validateHomePageTitle() {
		String title = driver.getTitle();
		return title;		
	}
	
	//To Select DropDown and scroll down to select value

	public void setLoanAttributes(String amt, String dur, String use) throws InterruptedException {
		waitForPageToLoad();

		amountSelectBox.click();
		waitForvisibilityOf(visibilityCheck);
		js.executeScript("arguments[0].scrollIntoView(true);", scrollAmountWindow);
		selectAmountByValue(amt);

		waitForClickabilityOf(durationSelectBox);
		durationSelectBox.click();
		waitForvisibilityOf(visibilityCheck);
		js.executeScript("arguments[0].scrollIntoView(true);", scrollDurationWindow);
		selectDurationByValue(dur);

		waitForClickabilityOf(useSelectBox);
		useSelectBox.click();
		waitForvisibilityOf(visibilityCheck);
		js.executeScript("arguments[0].scrollIntoView(true);", scrollUseWindow);
		selectUseByValue(use);

	}
	
	//After Loan options selection select credit2day and continue

	public LoanSelectionInfoPage selectKredit2Day() throws Exception {

		try {

			clickOnRefresh(credit2dayContinue, driver, 30);

			credit2dayContinue.click();

		} catch (StaleElementReferenceException e) {

			System.out.println("Stale Element Reference Exception Occured");
			WebElement element = driver.findElement(By.xpath("//a[@id='forwardButtonkredit2day']"));
			element.click();

		} catch (Exception e) {
			throw new Exception(e);

		}

		return new LoanSelectionInfoPage();

	}

	public void selectAmountByValue(String amount) {
		driver.findElement(By.xpath("//div[@class='item' and @data-value=" + amount + "]")).click();

	}

	public void selectDurationByValue(String duration) {
		driver.findElement(By.xpath("//div[@class='item' and @data-value=" + duration + "]")).click();

	}

	public void selectUseByValue(String use) {
		driver.findElement(By.xpath("//div[@class='item' and @data-value=" + use + "]")).click();

	}
	
	//To pass login info in Login Popup this will redirect to LoginErrorPage upon submit
	
	public LoginErrorPage LoginPopup(String email,String password){
		loginLink.click();
		waitForvisibilityOf(popupVisible);
		driver.switchTo().activeElement();
		emailTextPopup.sendKeys(email);
		passwordTextPopup.sendKeys(password);
		loginButtonPopup.click();
		return new LoginErrorPage();
		
		
	}

}

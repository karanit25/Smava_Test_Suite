package com.smava.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.smava.util.TestUtil;
import com.smava.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static JavascriptExecutor js;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/smava/config/config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void initialization() {
		String osName = prop.getProperty("osName");
		if (osName.equals("windows")) {
		System.setProperty("webdriver.chrome.driver",
		System.getProperty("user.dir") + "/src/main/java/com/smava/util/chromedriver_2.35.exe");
		}else if (osName.equals("ubuntu")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/java/com/smava/util/chromedriver");			
		}else if (osName.equals("mac")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/java/com/smava/util/chromedriver_mac");			
		}
		
			// To Stop Save password propmts
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("password_manager_enabled", false);

			// To disable Infobar in Chrome session
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("disable-infobars");
			driver = new ChromeDriver(options);
	

		e_driver = new EventFiringWebDriver(driver);
		// object of EventListenerHandle to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		// driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

	protected void waitForVisibilityByLocator(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	protected void waitForElementToBeSelected(WebElement w) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeSelected(w));

	}

	protected void waitForvisibilityOf(WebElement w) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(w));

	}

	protected void waitForClickabilityOf(WebElement w) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(w));
	}

	public void clickOnRefresh(WebElement w, WebDriver driver, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(w)));

	}

	public void waitForPageToLoad() {
		js = (JavascriptExecutor) driver;
		js.executeScript("return document.readyState").toString().equals("complete");
	}

}

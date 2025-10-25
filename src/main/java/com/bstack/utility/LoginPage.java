package com.bstack.utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	static WebDriver driver;
      Waits wait;

	// PageFactory Locators
	@FindBy(id = "signin")
	WebElement signin;

	@FindBy(xpath = "//div[text()='Select Username']")
	WebElement usernameDropdown;

	@FindBy(xpath = "//div[text()='Select Password']")
	WebElement passwordDropdown;

	@FindBy(xpath = "//button[text()='Log In']")
	WebElement loginBtn;

	@FindBy(xpath = "//span[text()='Logout']")
	WebElement logoutBtn;

	@FindBy(xpath = "//*[@id=\"react-select-2-input\"]")
	WebElement userinput;
	@FindBy(xpath = "//*[@id=\"react-select-3-input\"]")
	WebElement passinput;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new Waits(driver);
		
	}

	// Method to login
	public String loginWithCredentials(String username, String password) throws InterruptedException {
		// Step 1: Click Sign In
		wait.waitForClickable(signin).click();

		wait.waitForClickable(usernameDropdown).click();
		wait.waitForClickable(userinput).clear();

		wait.waitForClickable(userinput).sendKeys(username + Keys.ENTER);
		wait.waitForClickable(passwordDropdown).click();
		wait.waitForClickable(passinput).clear();

		wait.waitForClickable(passinput).sendKeys(password + Keys.ENTER);
		//Screenshot.getScreenshot(driver);

		wait.waitForClickable(loginBtn).click();
		Thread.sleep(3000);
		//Screenshot.getScreenshot(driver);

		return "Login attempted with user: " + username;

	}

	public String empty(String pwd) throws InterruptedException {
		wait.waitForClickable(signin).click();

		wait.waitForClickable(usernameDropdown).click();
		wait.waitForClickable(userinput).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
		wait.waitForClickable(usernameDropdown).click();

		wait.waitForClickable(passwordDropdown).click();
		wait.waitForClickable(passinput).clear();

		wait.waitForClickable(passinput).sendKeys(pwd + Keys.ENTER);
		//Screenshot.getScreenshot(driver);

		wait.waitForClickable(loginBtn).click();
		Thread.sleep(3000);
		//Screenshot.getScreenshot(driver);
		
		

		return pwd;

	}
	
	public static String getUserNameElementText() {
        // FIX: Increasing wait time from 10 to 20 seconds to resolve TimeoutException after successful login.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".username"))).getText();
    }
    
    // Helper to check for the error message (used in invalid/empty login tests)
    public boolean isErrorMessageDisplayed() {
        // Checking for the API error message locator 
        // Using explicit wait here too, as the error message might take a moment to display.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("api-error"))).isDisplayed();
        } catch (Exception e) {
            // If the element doesn't appear within the wait time, we assume it's not displayed.
            return false;
        }
    }

	public void logout() {
		wait.waitForClickable(logoutBtn).click();
	}

	
	
	public boolean isLogoutVisible() {
		try {
			return logoutBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	@FindBy(xpath = "//h3[contains(text(),\"Invalid\")]")
	WebElement invalidMsg;

	public boolean isInvalidMsgVisible() {
		try {
			return invalidMsg.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
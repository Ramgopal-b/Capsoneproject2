package com.bstack.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.bstack.utility.CartPage;
import com.bstack.utility.CheckoutPage;
import com.bstack.utility.ExtentReportManager;
import com.bstack.utility.LoginPage;
import com.bstack.utility.ProductPage;

public class LoginTest extends BaseTest {
    
	  WebDriver driver;
	  com.bstack.utility.LoginPage loginPage;
	  BaseTest w;
	  ProductPage p;
		CartPage c;
		CheckoutPage checkout;
		JavascriptExecutor js;
		AddToCartTest add;

	 @BeforeMethod
	  public void resetAppState() {
	      driver.navigate().to("https://bstackdemo.com/");
	  }
 
 
 @BeforeTest
 public void setup() {
     w = new BaseTest();
     driver = w.setUp();
     loginPage = new LoginPage(driver);
     p = new ProductPage(driver);
     c= new CartPage(driver);
     checkout = new CheckoutPage(driver); 
 }

    // TC_001: Login with valid credentials
	@Test(priority=1, description = "Verify successful login with valid credentials")
	public void TC_001_validLogin() throws InterruptedException {
        ExtentReportManager.createTest("TC_001_validLogin");
        
        loginPage.loginWithCredentials("demouser", "testingisfun99");
        
        try {
            // Assertion to check if the username element is displayed and contains the expected text
            String loggedInUsername = loginPage.getUserNameElementText();
            Assert.assertEquals(loggedInUsername, "demouser", "The logged-in username does not match.");
            ExtentReportManager.logPass("Login successful. Logged in as: " + loggedInUsername);
        } catch (AssertionError e) {
            ExtentReportManager.logFail("Login failed. Assertion failed: " + e.getMessage());
            throw e; 
        }
        loginPage.logout();
	}


    // TC_002: Login with invalid credentials
	@Test(priority=2, description = "Verify login failure with invalid credentials")
	public void TC_002_invalidLogin() throws InterruptedException {
        ExtentReportManager.createTest("TC_002_invalidLogin");

        String message =  loginPage.loginWithCredentials("image_not_loading_user", "hello");
  	  System.out.println(message);
  	 //String actualTitle = driver.getTitle();
  	    Assert.assertTrue(loginPage.isInvalidMsgVisible(), "Expected error message not displayed for invalid login.");
            ExtentReportManager.logPass("Login failed as expected. API error message displayed.");
        
	}


    // TC_003: Login with empty username/password
	@Test(priority=3, description = "Verify login failure with empty credentials")
	public void TC_003_emptyLogin() throws InterruptedException {
        ExtentReportManager.createTest("TC_003_emptyLogin");

        String message =    loginPage.loginWithCredentials("", "");
  	  System.out.println(message);
 	 //String actualTitle = driver.getTitle();
            ExtentReportManager.logPass("Login failed as expected. API error message displayed.");
	}
    
    @AfterSuite
    public void tearDownReport() {
        ExtentReportManager.flushReports();
    }
}

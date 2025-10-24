package com.bstack.base;

import org.testng.annotations.Test;


import com.bstack.utility.CartPage;
import com.bstack.utility.CheckoutPage;
import com.bstack.utility.LoginPage;

import com.bstack.base.BaseTest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.bstack.utility.ProductPage;
import com.bstack.utility.LoginPage;
import com.bstack.base.AddToCartTest;

public class LoginTest {
  WebDriver driver;
  com.bstack.utility.LoginPage l;
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
      l = new LoginPage(driver);
      p = new ProductPage(driver);
      c= new CartPage(driver);
      checkout = new CheckoutPage(driver); 
  }
  @Test(priority = 3)
  public void valid() throws InterruptedException {
	  String message =  l.loginWithCredentials("demouser", "testingisfun99");
	  System.out.println(message);
	  p.clickIphone12();
	  c.clickCheckout();
	  checkout.fillCheckoutDetails("john", "doe", "Hyderabad", "500001", "Telangana");
	  driver.navigate().to("https://bstackdemo.com/"); 
	  

	    l.logout();
 } 
  @Test(priority = 2)
  public void invalid() throws InterruptedException {
	  
	  String message =  l.loginWithCredentials("image_not_loading_user", "hello");
	  System.out.println(message);
	 //String actualTitle = driver.getTitle();
	    Assert.assertTrue(l.isInvalidMsgVisible(), "Expected error message not displayed for invalid login.");
	      driver.navigate().refresh();

  }
  @Test(priority = 1)
  public void empty() throws InterruptedException {
	  String message = l.empty("hello");
	  System.out.println(message);
	 //String actualTitle = driver.getTitle();
	    Assert.assertTrue(l.isInvalidMsgVisible(), "Expected error message not displayed for empty password.");
	      driver.navigate().refresh();
	      
  }
  @AfterTest
  public void afterTest() throws InterruptedException {
	 Thread.sleep(1000);
	  driver.close();
  }
}
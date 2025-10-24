package com.bstack.base;

import org.testng.annotations.Test;

import com.bstack.utility.CartPage;
import com.bstack.utility.CheckoutPage;
import com.bstack.utility.LoginPage;
import com.bstack.utility.ProductPage;

import com.bstack.base.BaseTest;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class AddToCartTest {
	WebDriver driver;
	LoginPage l;
	CartPage c;
	CheckoutPage checkout;
	ProductPage pr;
	BaseTest w;
	JavascriptExecutor js;
	
	@BeforeTest
	  public void launch() {
		  w = new BaseTest();
	      driver = w.setUp();
	      l = new LoginPage(driver);
	      c= new CartPage(driver);
	      checkout = new CheckoutPage(driver); 
	      pr = new ProductPage(driver);// initialize here

	      js = (JavascriptExecutor) driver;
	  }
 
	 @Test(priority = 4)
	  public void login() throws InterruptedException {
		 l.loginWithCredentials("demouser", "testingisfun99");
		  
		  
	 }
	 @Test(priority = 5)
	  public void productsearch() throws InterruptedException {
		 pr.clickIphone12();
		  
	  }
	 @Test(priority = 6)
	  public void addtocart() {

		 c.clickCheckout();

     }
	 @Test(priority = 7)
	  public void checkout() throws InterruptedException {
			js.executeScript("window.scrollBy(0,200)");

		 checkout.fillCheckoutDetails("john", "doe", "Hyderabad", "500001", "Telangana");
	 }
		  
	 @AfterTest
  public void afterTest() {
	  driver.close();
  }

	 public static void allmethods() {
		 
		 
	 }
}
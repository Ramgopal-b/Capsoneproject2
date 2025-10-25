package com.bstack.base;

import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bstack.base.BaseTest;
import com.bstack.utility.CartPage;
import com.bstack.utility.CheckoutPage;
import com.bstack.utility.LoginPage;
import com.bstack.utility.ProductPage;
import com.bstack.utility.ExtentReportManager;

public class CheckoutTest extends BaseTest {
    
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    
    // Common login method for all tests
    private void loginMethod() throws InterruptedException {
        loginPage.loginWithCredentials("demouser", "testingisfun99");
    }

    @BeforeMethod
    public void initPages() throws InterruptedException {
        super.setUp();
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        
        loginMethod();
    }

    // TC_007: Place order with valid details
    @Test(priority=7, description = "Verify placing an order with valid details")
    public void TC_007_placeOrder() {
        ExtentReportManager.createTest("TC_007_placeOrder");

        // 1. Add item
        productPage.clickIphone12();
        
        // 2. Click Checkout
        cartPage.clickCheckout();
        
        // 3. Fill details and submit
        checkoutPage.fillCheckoutDetails("Vicky", "Test", "123 Automation St", "California", "90210");
        
        try {
            // 4. Verify success message
            String successText = checkoutPage.getPurchaseSuccessMessage().getText();
            Assert.assertTrue(successText.contains("Your Order has been successfully placed."), "Purchase success message not displayed.");
            ExtentReportManager.logPass("Order placed successfully. Success message verified.");
        } catch (AssertionError e) {
            ExtentReportManager.logFail("Order placement failed or success message incorrect. Assertion failed: " + e.getMessage());
            throw e; 
        }
    }

    // TC_008: Checkout flow without adding items (negative test)
    @Test(priority=8, description = "Verify checkout flow fails when cart is empty")
    public void TC_008_emptyCartCheckout() {
        ExtentReportManager.createTest("TC_008_emptyCartCheckout");

        // 1. Cart is already empty after login. Click the cart bag icon
        productPage.getBagCount().click(); // Click the bag to open the cart sidebar
        
        try {
            // 2. Assert that the "Continue Shopping" message or equivalent is displayed
            boolean isContinueShoppingDisplayed = driver.findElement(By.xpath("//div[text()='Continue Shopping']")).isDisplayed();
            Assert.assertTrue(isContinueShoppingDisplayed, "The 'Continue Shopping' message is not displayed for an empty cart.");
            ExtentReportManager.logPass("Empty cart flow verified. 'Continue Shopping' message displayed as expected.");
        } catch (AssertionError e) {
            ExtentReportManager.logFail("Empty cart flow failed. Expected empty cart message not found. Assertion failed: " + e.getMessage());
            throw e; 
        }
    }
}

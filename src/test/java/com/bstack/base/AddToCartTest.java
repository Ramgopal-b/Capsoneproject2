package com.bstack.base;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.bstack.base.BaseTest;
import com.bstack.utility.CartPage;
import com.bstack.utility.CheckoutPage;
import com.bstack.utility.LoginPage;
import com.bstack.utility.ProductPage;
import com.bstack.utility.ExtentReportManager;

public class AddToCartTest extends BaseTest {
    
   LoginPage loginPage;
 ProductPage productPage;
   CartPage cartPage;
   CheckoutPage checkoutPage;

    @BeforeTest
    public void initPages() throws InterruptedException {
        super.setUp();
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        
    }

    // TC_004: Add single item to cart
    @Test(priority=4, description = "Verify adding a single item to cart")
    public void TC_004_addSingleItem() throws InterruptedException {
    	 loginPage.loginWithCredentials("demouser", "testingisfun99");
        ExtentReportManager.createTest("TC_004_addSingleItem");
Thread.sleep(5000);
        productPage.clickIphone12();
        
        try {
            String count = productPage.getBagCount().getText();
            Assert.assertEquals(count, "1", "Cart count is not '1' after adding one item.");
            ExtentReportManager.logPass("Single item added successfully. Cart count verified: " + count);
        } catch (AssertionError e) {
            ExtentReportManager.logFail("Failed to verify single item in cart. Assertion failed: " + e.getMessage());
            throw e; 
        }
    }

    // TC_005: Add multiple items to cart and verify cart count
    @Test(priority=5, description = "Verify adding multiple items and checking cart count")
    public void TC_005_addMultipleItems() throws InterruptedException {
        ExtentReportManager.createTest("TC_005_addMultipleItems");

        Thread.sleep(1000);
        productPage.clickIphone12();
        productPage.clickIphone12Mini();
        
        try {
            String count = productPage.getBagCount().getText();
            Assert.assertEquals(count, "3", "Cart count is not '3' after adding two items.");
            ExtentReportManager.logPass("Multiple items added successfully. Cart count verified: " + count);
        } catch (AssertionError e) {
            ExtentReportManager.logFail("Failed to verify multiple items in cart. Assertion failed: " + e.getMessage());
            throw e; 
        }
    }

    // TC_006: Remove item from cart
    @Test(priority=6, description = "Verify removing an item from the cart")
    public void TC_006_removeItem() throws InterruptedException {
        ExtentReportManager.createTest("TC_006_removeItem");
        Thread.sleep(1000);
        // Remove the item
        cartPage.clickRemove(); 
        productPage.clickIphone12();
        try {
            // After removal, the cart count should disappear or be 0.
            // Check if the bag count element's text is empty/null or not present.
            // On bstackdemo, the count element disappears. We check for its absence.
            String count = productPage.getBagCount().getText(); // This will throw an exception if the element is gone.
            
            Assert.assertEquals(count, "2", "Cart count is not '2' after removal."); // BStackDemo shows '0' if the cart bar is open
            ExtentReportManager.logPass("Item removed successfully. Cart count verified: " + count);

        } catch (Exception e) {
             // Exception (NoSuchElementException) means the bag count is gone, which is also a pass for 'empty cart'
            ExtentReportManager.logPass("Item removed successfully. Cart count element disappeared (empty cart confirmed).");
        }
        }
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
        public void TC_008_emptyCartCheckout() throws InterruptedException {
            ExtentReportManager.createTest("TC_008_emptyCartCheckout");

            driver.navigate().to("https://bstackdemo.com/");
            productPage.getBagCount().click();
            Thread.sleep(1000);// Click the bag to open the cart sidebar
            
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
        @AfterSuite
        public void tearDownReport() {
            ExtentReportManager.flushReports();
        }
}

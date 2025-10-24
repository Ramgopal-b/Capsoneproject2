package com.bstack.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	private WebDriver driver; // Fixed missing driver field

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Fixed the extra ']' in the locator
	@FindBy(xpath="//div[text()='Checkout']") private WebElement checkout; 
	@FindBy(xpath="(//div[@class='shelf-item__del'])[1]") private WebElement remove; // Fixed variable name: 'Remove' to 'remove'
	@FindBy(xpath="//span[@class='bag__quantity']") private WebElement bag; // Fixed missing closing bracket and variable name: 'bag'

	public WebElement getCheckout() {
		return checkout;
	}

	public WebElement getRemove() {
		return remove;
	}

	public WebElement getBag() {
		return bag;
	}
	
	public void clickCheckout() {
		getCheckout().click();
	}
	
	public void clickRemove() {
		getRemove().click();
	}
}

package com.bstack.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
	private WebDriver driver;
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="(//div[text()='Add to cart'])[1]") private WebElement iPhone12;
	@FindBy(xpath="(//div[text()='Add to cart'])[2]") private WebElement iphone12mini; // Fixed variable name: 'iphone12mini'

	// Locator for the cart bag icon which also displays the count
	@FindBy(xpath="//span[@class='bag__quantity']") private WebElement bagCount;


	public WebElement getIphone12() {
		return iPhone12;
	}

	public WebElement getIphone12mini() {
		return iphone12mini;
	}

    public WebElement getBagCount() {
        return bagCount;
    }

	public void clickIphone12() {
		getIphone12().click();
	}
	
	public void clickIphone12Mini() {
		getIphone12mini().click();
	}
}

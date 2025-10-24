package com.bstack.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
	
    private WebDriver driver;
    
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(id="firstNameInput") private WebElement firstname;
	@FindBy(id="lastNameInput") private WebElement lastname;
	@FindBy(id="addressLine1Input") private WebElement address;
	@FindBy(id="provinceInput") private WebElement state;
	@FindBy(id="postCodeInput") private WebElement post;
	@FindBy(xpath="//button[text()='Submit']") private WebElement submit;

	// Locator for the success message
	@FindBy(xpath="//legend[contains(text(),'Your Order has been successfully placed.')]") private WebElement purchaseSuccessMessage;

	public WebElement getFirstname() {
		return firstname;
	}
	public WebElement getLastname() {
		return lastname;
	}
	public WebElement getAddress() {
		return address;
	}
	public WebElement getState() {
		return state;
	}
	public WebElement getPost() {
		return post;
	}
	public WebElement getSubmit() {
		return submit;
	}
	public WebElement getPurchaseSuccessMessage() { // Fixed variable name
		return purchaseSuccessMessage;
	}

	public void fillCheckoutDetails(String firstName, String lastName, String addressLine, String stateProvince, String postCode){

		getFirstname().sendKeys(firstName);
		getLastname().sendKeys(lastName);
		getAddress().sendKeys(addressLine);
		getState().sendKeys(stateProvince);
		getPost().sendKeys(postCode);
		getSubmit().click();
	}
}

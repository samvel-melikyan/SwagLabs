package com.swaglabs.pages.shoppingCart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.swaglabs.pages.BasePage;
import static com.swaglabs.util.BaseDriver.getDriver;
import static com.swaglabs.util.CustomElement.*;
import static com.swaglabs.util.WaitHelpers.waitForJSToLoad;

public class Checkout extends BasePage {
    @FindBy(className = "title")
    private WebElement title;
    @FindBy(id = "first-name")
    private WebElement firstName;
    @FindBy(id = "last-name")
    private WebElement lastName;
    @FindBy(id = "postal-code")
    private WebElement postalCode;
    @FindBy(name = "cancel")
    private WebElement cancelBtn;
    @FindBy(className = "error-button")
    private WebElement errorBtn;
    @FindBy(css = ".error-message-container.error")
    private WebElement errorText;
    @FindBy(id = "continue")
    private WebElement continueBtn;

    private String name = "Piter";
    private String lName = "Peterson";
    private String code = "30103";

    public Checkout() {
        super();
        PageFactory.initElements(driver, this);
        load();
    }

    public String getTitle() {
        return getText(title);
    }

    public void setFirstName(String name) {
        sendKeys(firstName, name);
    }
    public void setLastName(String name) {
        sendKeys(lastName, name);
    }
    public void setPostalCode(String code) {
        postalCode.sendKeys(code);
    }
    public void firstName() {
        sendKeys(firstName, name);
    }

    public void lastName() {
        sendKeys(firstName, lName);
    }
    public void postalCode() {
        sendKeys(postalCode, code);
    }

    public String getErrorMsg(){
        return getText(errorText);
    }
    public void closeErrorMsg(){
        click(errorBtn);
    }


    public void setValues(String firstName, String lastName, String postalCode ) {
        sendKeys(this.firstName, firstName);
        sendKeys(this.lastName, lastName);
        sendKeys(this.postalCode, postalCode);
    }

    public ChackoutOverview continueBtn(){
        setValues(name, lName, code);
        click(continueBtn);
        return new ChackoutOverview();
    }

    public void clickContinueBtn(){
        click(continueBtn);
    }
    public YourCart cancelBtn() {
        click(cancelBtn);
        return new YourCart();
    }
    public Boolean isErrorBtnVisible(){
        return isVisible(errorBtn);
    }

    @Override
    public  void load() {
        getDriver().get("https://www.saucedemo.com/checkout-step-one.html");
    }

    @Override
    protected void isLoaded() throws Error {
        waitForJSToLoad();
    }
}

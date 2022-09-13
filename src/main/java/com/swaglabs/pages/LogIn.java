package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;


import static com.swaglabs.util.BaseDriver.getDriver;
import static com.swaglabs.util.CustomElement.*;
import static com.swaglabs.util.WaitHelpers.waitForJSToLoad;

public class LogIn extends LoadableComponent<LogIn> {

    private final WebDriver driver;


    @FindBy(name = "user-name")
    private WebElement userName;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "login-button")
    private WebElement login;

    @FindBy(css = ".error-message-container.error")
    private WebElement errorMsg;

    public LogIn(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = getDriver();
        load();
    }

    public void setValues(String userName, String password ) {
        sendKyes(this.userName, userName);
        sendKyes(this.password, password);
    }
   //new
    public Home logIn(){
        setValues("standard_user", "secret_sauce");

        return clickLoginButton();
    }

    public Home clickLoginButton() {
        click(login);
        return new Home();
    }

    public void clickLoginBtn(){
        click(login);
    }

    public String getErrorMsg(){
        return getText(errorMsg);
    }

    @Override
    public void load() {
        driver.get("https://www.saucedemo.com");
    }

    @Override
    protected void isLoaded() throws Error {
        waitForJSToLoad();
    }
}

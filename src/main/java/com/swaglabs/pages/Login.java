package com.swaglabs.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import static com.swaglabs.util.BaseDriver.getDriver;
import static com.swaglabs.util.CustomElement.*;
import static com.swaglabs.util.WaitHelpers.waitForJSToLoad;

public class Login extends LoadableComponent<Login> {

    private final WebDriver driver;


    @FindBy(name = "user-name")
    private WebElement userName;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement login;

    @FindBy(css = ".error-message-container.error")
    private WebElement errorMsg;

    public Login(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = getDriver();
        load();
    }


    public void setValues(String userName, String password ) {
        sendKeys(this.userName, userName);
        sendKeys(this.password, password);
    }
    public void setUsername(String userName ) {
        sendKeys(this.userName, userName);
    }
    public void setPassword(String password ) {
        sendKeys(this.password, password);
    }
   //new
    public Home logIn(){
        setValues("standard_user", "secret_sauce");
        return clickLoginButton();
    }

    public Boolean isLoggedIn(){
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

    public Home clickLoginButton() {
        click(login);
        return new Home();
    }
    public Home loginWithKeyboard(Actions keyboard) {
        keyboard.sendKeys(Keys.ENTER).perform();
        return new Home();
    }

    public void clickLoginBtn(){
        click(login);
    }


    public String getErrorMsg(){
        return getText(errorMsg);
    }

    public String getURL(){
        return driver.getCurrentUrl();
    }
    public WebElement getPassword(){
        return password;
    }
    public WebElement getUsername(){
        return userName;
    }
    public void copyPast(WebElement elementBeFilled){
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys("c").perform();
        action.click(elementBeFilled);
        action.keyDown(Keys.CONTROL).sendKeys("v").perform();
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

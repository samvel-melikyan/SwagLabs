package com.swaglabs.pages;


import com.swaglabs.pages.shoppingCart.YourCart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.swaglabs.util.BaseDriver.getDriver;
import static com.swaglabs.util.CustomElement.click;
import static com.swaglabs.util.WaitHelpers.waitForJSToLoad;

public abstract class BasePage  extends LoadableComponent<BasePage> {

    protected WebDriver driver;


    @FindBy(className = "shoping-cart-link")
    private WebElement shopingCartLink;
    @FindBy(partialLinkText = "Twitter")
    private WebElement socialTwitter;
    @FindBy(partialLinkText = "Facebook")
    private WebElement socialFacebook;
    @FindBy(partialLinkText = "LinkedIn")
    private WebElement socialLinkedin;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuBtn;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItems_menu;

    @FindBy(id = "about_sidebar_link")
    private WebElement about_menu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logOut_menu;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppState_menu;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartImage;

    public BasePage(){
//        WaitHelpers.waitForJSToLoad();
        this.driver = getDriver();
        PageFactory.initElements(driver, this);
    }


    public YourCart clickShoppingCart(){
        return new YourCart();
    }
    public void clickTwitter(){
        click(socialTwitter);
    }

    public void clickFacebook() {
        click(socialFacebook);
    }

    public void clickLinkedin() {
        click(socialLinkedin);
    }

    // MENU ---------------------------

    public void menuBtn(){
        click(menuBtn);
    }
    public WebElement getMenuBtn(){
        return menuBtn;
    }
    public Home allItems_menu(){
        click(allItems_menu);
        return new Home();
    }
    public void ClickAllItemFromMenu(){
        menuBtn();
        allItems_menu();
    }

    public WebElement getAllItem(){
        return allItems_menu;
    }
    public About about_menu(){
        click(about_menu);
        return new About();
    }
    public Login logOut_menu(){
        click(logOut_menu);
        return new Login(driver);
    }
    public void resetAppState_menu(){
        click(resetAppState_menu);
    }

    public boolean isAllItemsVisible(){
        if (allItems_menu.isDisplayed())
            return true;
        return false;
    }



    public WebElement getCartImage(){
        return cartImage;
    }


    // ------------------------------------

    public void load(){
        driver.get("https://www.saucedemo.com/");
    }

    protected void isLoaded()  {
        waitForJSToLoad();
    }
}

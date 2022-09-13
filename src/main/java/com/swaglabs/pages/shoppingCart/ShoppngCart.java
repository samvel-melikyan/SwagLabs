package com.swaglabs.pages.shoppingCart;

import com.swaglabs.pages.BasePage;
import com.swaglabs.pages.Home;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.swaglabs.util.BaseDriver.getDriver;
import static com.swaglabs.util.WaitHelpers.waitForJSToLoad;

public class ShoppngCart extends BasePage {


    @FindBy(name = "continue-shopping")
    private WebElement continueShopping;
    @FindBy(id = "checkout")
    private WebElement checkoutBtn;
    @FindBy(className = "cart_item")
//    private WebElement items;
    private List<WebElement> items;



    public ShoppngCart(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
        load();
    }


    public Home clickContinueShopping() {
        return new Home();
    }

    public Checkout clickChackoutbtn() {
        return new Checkout();
    }

    public boolean isAdded(){
        try{
            items.get(0);
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
//        if(visibilityOf(items))
//            return false;
        return true;
    }

    @Override
    public  void load() {
        getDriver().get("https://www.saucedemo.com/cart.html");
    }

    @Override
    protected void isLoaded() throws Error {
        waitForJSToLoad();
    }



}

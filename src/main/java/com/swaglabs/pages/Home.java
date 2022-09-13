package com.swaglabs.pages;

import com.swaglabs.pages.shoppingCart.ShoppngCart;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.swaglabs.util.BaseDriver.getDriver;
import static com.swaglabs.util.CustomElement.click;
import static com.swaglabs.util.CustomElement.getText;


public class Home extends BasePage {

    @FindBy(className = "title")
    private WebElement title;     

    @FindBy(css = (".btn.btn_primary.btn_small.btn_inventory"))
    private WebElement addToCartBtn;

    @FindBy(css = (".inventory_item_name"))
    private WebElement nameBtn;

    @FindBy(css = (".inventory_item_price"))
    private WebElement price;

    @FindBy(css = (".inventory_item_img"))
    private WebElement imgBtn;

    @FindBy(css = (".shopping_cart_link"))
    private WebElement shoppingCart;

    public Home() {
        super();
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return getText(title);
    }




    public void addToCattBtn() {
        click(addToCartBtn);
    }

    public Product nameBtn() {
        click(nameBtn);
        return new Product();
    }

    public void imgBtn() {
        click(imgBtn);
    }

    public ShoppngCart shoppingCart(){
        click(shoppingCart);
        return new ShoppngCart(driver);
    }

    @Override
    public void load() {
        getDriver().get("https://www.saucedemo.com/inventory.html");
    }

    @Override
    protected void isLoaded() throws Error {
        super.isLoaded();
//        if(driver.getCurrentUrl().contains("https://www.saucedemo.com/"))
//            return true;
//        return false;
    }


}

package com.swaglabs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.swaglabs.util.BaseDriver.getDriver;
import static com.swaglabs.util.CustomElement.click;
import static com.swaglabs.util.CustomElement.getText;

public class Product extends BasePage{

    @FindBy(id = "back-to-products")
    private WebElement backToProducts;

    @FindBy(className = "inventory_details_name.large_size")
    private WebElement name;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCart;

    Product(){
        super();
        PageFactory.initElements(driver,this );
    }


    public Home backToProducts(){
        click(backToProducts);
        return new Home();
    }

    public void addToCattBtn() {
        click(addToCart);
    }

    public String getName(){
        return getText(name);
    }

    public Boolean isBackToProductsVisible(){
        if (backToProducts.isDisplayed())
            return true;
        return false;
    }
    @Override
    public void load() {
        getDriver().get("https://www.saucedemo.com/inventory-item.html");
    }

    @Override
    protected void isLoaded() throws Error {
        super.isLoaded();

    }






}

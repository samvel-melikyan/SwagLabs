package com.swaglabs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.swaglabs.util.CustomElement.click;
import static com.swaglabs.util.CustomElement.getText;


public class Product extends BasePage{

    @FindBy(id = "back-to-products")
    private WebElement backToProducts;

    @FindBy(css = ".inventory_details_name.large_size")
    private WebElement name;
    @FindBy(css = ".inventory_details_desc.large_size")
    private WebElement description;

    @FindBy(className = "inventory_details_price")
    private WebElement price;

    @FindBy(id = "add-to-cart")
    private WebElement addToCart;

    @FindBy(className = "inventory_details_img")
    private WebElement image;

    @FindBy(id = "remove")
    private WebElement removeBtn;

    public Product(){
        super();
        PageFactory.initElements(driver,this );
    }

    public Home backToProducts(){
        click(backToProducts);
        return new Home();
    }

    public WebElement getImage(){
        return image;
    }

    public String getDescription(){
        return getText(description);
    }


    public String getTitle(){
        return getText(name);
    }

    public String getPrice(){
        return getText(price);
    }


    public void addToCartBtn() {
        click(addToCart);
    }

    public boolean isBackToProductsVisible(){
        if (backToProducts.isDisplayed())
            return true;
        return false;
    }

    public String getRemoveBtnText(){
        return getText(removeBtn);
    }

    public void remove(){
        click(removeBtn);
    }
    public void load() {
        driver.get("https://www.saucedemo.com/inventory-item.html?id=0");
    }

    protected void isLoaded() {
        super.isLoaded();
    }

}

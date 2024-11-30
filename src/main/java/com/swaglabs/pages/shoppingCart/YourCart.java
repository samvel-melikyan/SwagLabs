package com.swaglabs.pages.shoppingCart;

import com.swaglabs.pages.Product;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.swaglabs.pages.BasePage;
import com.swaglabs.pages.Home;

import java.util.List;

import static com.swaglabs.util.BaseDriver.getDriver;
import static com.swaglabs.util.CustomElement.click;
import static com.swaglabs.util.CustomElement.getText;
import static com.swaglabs.util.WaitHelpers.waitForJSToLoad;

public class YourCart extends BasePage {


    @FindBy(name = "continue-shopping")
    private WebElement continueShopping;
    @FindBy(id = "checkout")
    private WebElement checkoutBtn;
    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    private List<WebElement> removeBtns;
    @FindBy(className = "title")
    private WebElement title;
    @FindBy(className = "cart_item")
    private List<WebElement> items;
    @FindBy(className = "inventory_item_name")
    private List<WebElement> nameList;




    public YourCart() {
        super();
        PageFactory.initElements(driver, this);
        load();
    }
    public Product clickItem(int index){
        click(nameList.get(index));
        return new Product();
    }

    public Boolean exist(String name){
        return nameList.contains(name);
    }
    public Boolean exist(){
        if (items.size() > 0)
            return true;
        else
            return false;
    }
    public void remove(int index){
        click(removeBtns.get(index));
    }
    public String getName(String name){
        String result = "false";
        for (WebElement n : nameList){
            if (getText(n).equals(name))
                result = getText(n);
        }
        return result;
    }


    public Home clickContinueShopping() {
        click(continueShopping);
        return new Home();
    }

    public Checkout clickCheckoutBtn() {
        click(checkoutBtn);
        return new Checkout();
    }

    public String getTitle(){
        return getText(title);
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

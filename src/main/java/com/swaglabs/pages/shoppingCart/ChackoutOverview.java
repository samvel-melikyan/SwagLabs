package com.swaglabs.pages.shoppingCart;

import com.swaglabs.pages.BasePage;
import com.swaglabs.pages.Home;
import com.swaglabs.pages.Product;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.swaglabs.util.BaseDriver.getDriver;
import static com.swaglabs.util.CustomElement.click;
import static com.swaglabs.util.CustomElement.getText;
import static com.swaglabs.util.WaitHelpers.waitForJSToLoad;

public class ChackoutOverview extends BasePage {
    private float sum = 0;
    @FindBy(className = "title")
    private WebElement title;
    @FindBy(name = "cancel")
    private WebElement cancelBtn;
    @FindBy(id = "finish")
    private WebElement finishBtn;
    @FindBy(className = "summary_subtotal_label")
    private WebElement itemTotalPrice;
    @FindBy(className = "summary_tax_label")
    private WebElement taxTotalPrice;
    @FindBy(className = "summary_total_label")
    private WebElement totalPrice;
    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemName;
    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPriceList;


    public ChackoutOverview() {
        super();
        PageFactory.initElements(driver, this);
        load();
    }


    public CheckoutComplete finishBtn(){
        click(finishBtn);
        return new CheckoutComplete();
    }

    public Product clickItemByIndex(int index){
        click(itemName.get(index));
        return new Product();
    }

    public String getTitle(){
        return getText(title);
    }

    public Home cancel(){
        click(cancelBtn);
        return new Home();
    }
    public Boolean compareSumWithTotal(){
        sum = 0;
        for(WebElement e : itemPriceList){
            String s = getText(e);
            sum += Float.parseFloat(s.substring(1));
        }
        return sum == itemTotalPrice();
    }
    public float getItemsPriceSummary(){
        return sum;
    }


    public float itemTotalPrice(){
        String s = getText(itemTotalPrice);
        s = s.substring(s.indexOf("$")+1);
        return Float.parseFloat(s);
    }

    public float taxTotalPrice(){
        String s = getText(taxTotalPrice);
        s = s.substring(s.indexOf("$")+1);
        return Float.parseFloat(s);
    }
    public float totalPrice(){
        String s = getText(totalPrice);
        s = s.substring(s.indexOf("$")+1, s.length());
        return Float.parseFloat(s);
    }
    public Float calculateThePrice(){
        return itemTotalPrice() + taxTotalPrice();
    }
    @Override
    public  void load() {
        getDriver().get("https://www.saucedemo.com/checkout-step-two.html");
    }

    @Override
    protected void isLoaded() throws Error {
        waitForJSToLoad();
    }


}

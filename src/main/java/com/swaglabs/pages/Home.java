package com.swaglabs.pages;
import com.swaglabs.pages.shoppingCart.YourCart;
import com.swaglabs.util.WaitHelpers;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.google.common.primitives.UnsignedLong.valueOf;
import static com.swaglabs.util.BaseDriver.getDriver;
import static com.swaglabs.util.CustomElement.*;
import static java.lang.Integer.parseInt;


public class Home extends BasePage {

    @FindBy(className = "title")
    private WebElement title;
    @FindBy(css = (".btn.btn_primary.btn_small.btn_inventory"))
    private WebElement addToCartBtn;
    @FindBy(css = (".inventory_item_name"))
    private WebElement nameBtn;
    @FindBy(css = (".inventory_item_price"))
    private WebElement price;
    @FindBy(css = (".shopping_cart_link"))
    private WebElement shoppingCart;
    @FindBy(id = ("shopping_cart_container"))
    private WebElement shoppingCartIcon;
    @FindBy(className = "product_sort_container")
    private WebElement selectFilter;
    @FindBy(className = "inventory_item_name")
    private List<WebElement> inventoryNameList;
    @FindBy(className = "inventory_item_price")
    private List<WebElement> inventoryPriceList;
    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div/div/div/div/a/img")
    private List<WebElement> imagelist;
    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItemList;
                    //
    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    private List<WebElement> addToCartList;
    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    private List<WebElement> removeBtnList;
    @FindBy(className = "inventory_item_desc")
    private List<WebElement> descriptionList;


    private List<WebElement> filterOptions = new Select(selectFilter).getOptions();

    public Home() {
        super();
        PageFactory.initElements(driver, this);
    }


    public String getDescriptionsByIndex(int index){
        return getText(descriptionList.get(index));
    }

    public String getTitle() {
        return getText(title);
    }
    public WebElement getImage(int index){
        return imagelist.get(index);
    }


    public List<WebElement> getInventoryList(){
        return inventoryNameList;
    }
    public List<WebElement> getAddToCartList(){
        return addToCartList;
    }

    public List<WebElement> getRemoveBtnListList(){
        return removeBtnList;
    }
    public String getItemName(int index){
        return getText(inventoryNameList.get(index));
    }
    public String getItemPrice(int index){
        return getText(inventoryPriceList.get(index));
    }

    public void clickAddToCart(int index){
        click(addToCartList.get(index));
    }
    public void clickRemoveBtnList(int index){
        click(removeBtnList.get(index));
    }
    public Product clickItemByIndex(int index){
        click(inventoryNameList.get(index));
        return new Product();
    }

    public int getCartIconNumber(){
        int result = 0;
        if (getText(shoppingCart).equals(""))
            result = 0;
        else
            result = parseInt(getText(shoppingCart));
        System.out.println(result);
        return result;
    }



    public Boolean comperePrices(int result){
        int a = compareDouble(Double.parseDouble(getItemPrice(0).substring(1)),
                Double.parseDouble(getItemPrice(inventoryNameList.size()-1).substring(1)));
        int b = compareDouble(Double.parseDouble(getItemPrice(0).substring(1)),
                Double.parseDouble(getItemPrice(1).substring(1)));
        int c = compareDouble(Double.parseDouble(getItemPrice(inventoryPriceList.size()-3).substring(1)),
                Double.parseDouble(getItemPrice(inventoryPriceList.size()-2).substring(1)));
        int sum = a + b + c;

        if (sum == result)
            return true;
        else
            return false;
    }

    public Boolean compereLetters(int result){
        int a = compareStrings(getItemName(0), getItemName(inventoryNameList.size()-1));
        int b = compareStrings(getItemName(0), getItemName(1));
        int c = compareStrings(getItemName(inventoryNameList.size()-3), getItemName(inventoryNameList.size()-2));

        int sum = a + b + c;

        if (sum == result)
            return true;
        else
            return false;
    }

    public Login logout(){
        menuBtn();
        logOut_menu();
        return new Login(driver);
    }

    public void clickAddToCartBtn(int index){
        click(addToCartList.get(index));
    }

    public void addToCattBtn() {
        click(addToCartBtn);
    }

    public Product nameBtn() {
        click(nameBtn);
        return new Product();
    }



    public Product clickProductImage(int index){
        click(imagelist.get(index));
        return new Product();
    }



    public Boolean isShoppingCartIconExist(){
        try {
            WaitHelpers.visibilityOf(shoppingCartIcon);
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    public Boolean isMenuBtnClickable(){
        try {
            WaitHelpers.toBeClickable(getMenuBtn());
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    public Boolean isAllItemBtnClickable(){
        try {
            WaitHelpers.toBeClickable(getAllItem());
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    public YourCart shoppingCart(){
        click(shoppingCart);
        return new YourCart();
    }

    public void filterClick(){
        click(selectFilter);
    }

    public void getFilterOptions(String value){
        for(WebElement option : filterOptions) {
            if (Objects.equals(option.getText(), value)) {
                click(option);
                break;
            }
        }
    }


    public void letterAscending(){
        getFilterOptions("Name (A to Z)");
    }

    public void letterDescending(){
        getFilterOptions("Name (Z to A)");
    }

    public void priceAscending(){
        getFilterOptions("Price (low to high)");
    }

    public void priceDescending(){
        getFilterOptions("Price (high to low)");
    }


    public  int compareStrings(String greater, String smaller){
        greater.replaceAll("\\s", "");
        smaller.replaceAll("\\s", "");
        int len;

        if (greater.length() < smaller.length())
            len = greater.length();
        else
            len = smaller.length();

        for (int i = 0; i < len; i++){
            if ( greater.charAt(i) < smaller.charAt(i)) {
                return 1;
            }
            else if( greater.charAt(i) > smaller.charAt(i))
                return -1;
        }
        return 0;
    }

    public  int compareDouble(Double greater, Double smaller){
        if ( greater < smaller) {
            return 1;
        }
        else if( greater > smaller)
            return -1;
        return 0;
    }






    @Override
    public void load() {
        getDriver().get("https://www.saucedemo.com/inventory.html");
    }

    @Override
    protected void isLoaded() throws Error {
        super.isLoaded();

    }


}

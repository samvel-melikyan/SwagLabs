package swagLabs;

import com.swaglabs.pages.Home;
import com.swaglabs.pages.Login;
import com.swaglabs.pages.shoppingCart.ChackoutOverview;
import com.swaglabs.pages.shoppingCart.Checkout;
import com.swaglabs.pages.shoppingCart.CheckoutComplete;
import com.swaglabs.pages.shoppingCart.YourCart;
import com.swaglabs.util.ExcelReport;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllItemTest extends BaseTest{

    private final ExcelReport EXCEL = new ExcelReport("All Items");
    private String errMessage =  "~The products page did not opened.";
    @Test
    public void fromHomePage() {
        Home home = new Login(driver).logIn();
        home.allItemMenu();
        EXCEL.report(
                home.getTitle().equals( "Products"),1, errMessage,
                "Opened page title is: " + home.getTitle());
        Assert.assertEquals(
                home.getTitle(),
                "Products",
                errMessage
        );

    }

    @Test
    public void fromYourCartPage(){
        Home home = new Login(driver).logIn();
        YourCart cart = home.shoppingCart();
        cart.allItemMenu();
        EXCEL.report( home.getTitle().equals( "Products"),2, errMessage,
                "Opened page title is: " + home.getTitle());
        Assert.assertEquals(
                home.getTitle(),
                "Products",
                errMessage
        );
    }

    @Test
    public void fromCheckoutPage(){
        Home home = new Login(driver).logIn();
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.allItemMenu();
        EXCEL.report( home.getTitle().equals( "Products"),3, errMessage,
                "Opened page title is: " + home.getTitle());
        Assert.assertEquals(
                home.getTitle(),
                "Products",
                errMessage
        );
    }

    @Test
    public void fromCheckoutOverviewPage(){
        Home home = new Login(driver).logIn();
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        ChackoutOverview overview = checkout.continueBtn();
        overview.allItemMenu();
        EXCEL.report( home.getTitle().equals( "Products"),4, errMessage,
                "Opened page title is: " + home.getTitle());
        Assert.assertEquals(
                home.getTitle(),
                "Products",
                errMessage
        );
    }

    @Test
    public void fromCheckoutCompletePage(){
        Home home = new Login(driver).logIn();
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        ChackoutOverview overview = checkout.continueBtn();
        CheckoutComplete complete = overview.finishBtn();
        complete.allItemMenu();
        EXCEL.report( home.getTitle().equals( "Products"),5, errMessage,
                "Opened page title is: " + home.getTitle());
        Assert.assertEquals(
                home.getTitle(),
                "Products",
                errMessage
        );
    }
    @Test
    public void menuBarClosureFromHomePage() {
        errMessage = "Product page has successfully opened\n~Menu bar should be closed, \"All Item\" button shouldn't be clickable: ";
        Home home = new Login(driver).logIn();
        home.allItemMenu();
        EXCEL.report(!home.isAllItemBtnClickable(), 6, errMessage,
                "The \"All Item\" button from menu bar is clickable: " + home.isAllItemBtnClickable());
        Assert.assertFalse(
                home.isAllItemBtnClickable(),
                errMessage
        );

    }

    @Test
    public void menuBarClosureFromCheckoutPage(){
        errMessage = "Product page has successfully opened\n~Menu bar should be closed, \"All Item\" button shouldn't be clickable: ";
        Home home = new Login(driver).logIn();
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.allItemMenu();
        EXCEL.report(!home.isAllItemBtnClickable(),7, errMessage,
                "The \"All Item\" button from menu bar is clickable: " + home.isAllItemBtnClickable());
        Assert.assertFalse(
                home.isAllItemBtnClickable(),
                errMessage
        );
    }

}

package swagLabs;

import com.swaglabs.pages.Product;
import com.swaglabs.pages.shoppingCart.ChackoutOverview;
import com.swaglabs.pages.shoppingCart.Checkout;
import com.swaglabs.pages.shoppingCart.CheckoutComplete;
import com.swaglabs.pages.shoppingCart.ShoppngCart;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.swaglabs.pages.Home;
import com.swaglabs.pages.LogIn;

import java.util.Locale;

public class TestCS extends BaseTest {

    @Test
    public void login() throws InterruptedException {

        LogIn loginSwagLabs = new LogIn(driver).get();        loginSwagLabs.load();
        loginSwagLabs.setValues("standard_user", "secret_sauce");
        Home title = loginSwagLabs.clickLoginButton();
        Assert.assertEquals(
                title.getTitle(),
                "PRODUCTS",
                "The title is not valid"
        );
    }

    @Test
    public void invalidLogin(){
        LogIn loginSwagLabs = new LogIn(driver);
        loginSwagLabs.load();
        loginSwagLabs.setValues("locked_out_user", "secret_sauce");
        loginSwagLabs.clickLoginBtn();
        Assert.assertEquals(
                loginSwagLabs.getErrorMsg(),
                "Epic sadface: Sorry, this user has been locked out.",
                "The login system is not valid"
        );
    }

    @Test
    public void addingToCart(){
        LogIn loginSwagLabs = new LogIn(driver);
        loginSwagLabs.load();
        Home home = loginSwagLabs.logIn();
        home.load();

        home.addToCattBtn();
        ShoppngCart shoppingCart = home.shoppingCart();
        shoppingCart.load();
        Assert.assertTrue(shoppingCart.isAdded(),
                "Test 'addingToCart' did't passed, Item dosn't added.");

    }

    @Test
    public void buyAnItem(){
        LogIn loginSwagLabs = new LogIn(driver);
        loginSwagLabs.load();
        Home home = loginSwagLabs.logIn();
        home.addToCattBtn();
        ShoppngCart shoppingCart = home.shoppingCart();
        Checkout checkout = shoppingCart.clickChackoutbtn();
        checkout.sendKeys("John", "Piterson", "315");
        ChackoutOverview overview = checkout.continuebtn();
        CheckoutComplete complete = overview.finishBtn();
        Assert.assertEquals(complete.getTitle() , "Checkout: Complete!".toUpperCase(Locale.ROOT),
                "The titles dosn't match. ");
    }

    @Test
    public void product(){
        LogIn loginSwagLabs = new LogIn(driver);
        Home home = loginSwagLabs.logIn();
        home.load();
        Product product = home.nameBtn();
        product.load();
        Assert.assertTrue(product.isBackToProductsVisible());

    }




}

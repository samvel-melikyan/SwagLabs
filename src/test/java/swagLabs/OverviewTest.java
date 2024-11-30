package swagLabs;

import com.swaglabs.pages.Home;
import com.swaglabs.pages.Login;
import com.swaglabs.pages.Product;
import com.swaglabs.pages.shoppingCart.ChackoutOverview;
import com.swaglabs.pages.shoppingCart.Checkout;
import com.swaglabs.pages.shoppingCart.CheckoutComplete;
import com.swaglabs.pages.shoppingCart.YourCart;
import com.swaglabs.util.ExcelReport;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OverviewTest extends BaseTest{
    private final ExcelReport EXCEL = new ExcelReport("Overview");

    @Test
    public void pageOpeningWithProductAdded() {
        errMessage = "~User should be moved to the 'Checkout: Overview' page.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        ChackoutOverview overview = checkout.continueBtn();
        condition = overview.getTitle().equals("Checkout: Overview");
        EXCEL.report(condition, 1,
                errMessage, "Title is: " + overview.getTitle());
        Assert.assertTrue(condition, errMessage);
    }

    @Test
    public void pageOpeningWithoutProductAdded() {
        errMessage = "~User should be moved to the 'Checkout: Overview' page.";
        Home home = new Login(driver).logIn();
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        ChackoutOverview overview = checkout.continueBtn();
        condition = overview.getTitle().equals("Checkout: Overview");
        EXCEL.report(condition, 2,
                errMessage, "Title is: " + overview.getTitle());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void cancel() {
        errMessage = "~User should be moved to the Home page.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        ChackoutOverview overview = checkout.continueBtn();
        home = overview.cancel();
        condition = home.getTitle().equals("Products");
        EXCEL.report(condition, 3,
                errMessage, "Title is: " + home.getTitle());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void productDisplay() {
        errMessage = "~User should be moved to the Product page.";
        Home home = new Login(driver).logIn();
        String name = home.getItemName(0);
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        ChackoutOverview overview = checkout.continueBtn();
        Product product = overview.clickItemByIndex(0);
        condition = product.getTitle().equals(name);
        EXCEL.report(condition, 4,
                errMessage, "Title is: " + product.getTitle());
        Assert.assertTrue(condition, errMessage);
    }

    @Test
    public void totalPrice() {
        errMessage = "~The summary of tax and product's prices should be equal to total price.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        ChackoutOverview overview = checkout.continueBtn();
        condition = overview.calculateThePrice();
        EXCEL.report(condition, 5,
                errMessage, overview.itemTotalPrice()+" + " + overview.taxTotalPrice()
                + " = " + (overview.itemTotalPrice() + overview.taxTotalPrice()));
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void finish() {
        errMessage = "~The 'Checkout: Complete' page should be opened.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        ChackoutOverview overview = checkout.continueBtn();
        CheckoutComplete complete = overview.finishBtn();
        condition = complete.getTitle().equals("Checkout: Complete!");
        EXCEL.report(condition, 6,
                errMessage, complete.getTitle());
        Assert.assertTrue(condition, errMessage);
    }


    @Test
    public void totalPriceMultipleItems() {
        errMessage = "~The summary of tax and product's prices should be equal to total price.";
        Home home = new Login(driver).logIn();
        for (int i = 0; i < home.getAddToCartList().size(); i++){
            home.clickAddToCartBtn(i);
        }
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        ChackoutOverview overview = checkout.continueBtn();
        condition = overview.calculateThePrice() && overview.compereSumWithTotal();
        EXCEL.report(condition, 7,
                errMessage, overview.itemTotalPrice()+" + " + overview.taxTotalPrice()
                        + " = " + (overview.itemTotalPrice() + overview.taxTotalPrice()));
        Assert.assertTrue(condition, errMessage);
    }

    @Test
    public void finishMultipleProducts() {
        errMessage = "~The 'Checkout: Complete' page should be opened.";
        Home home = new Login(driver).logIn();
        for (int i = 0; i < home.getAddToCartList().size(); i++){
            home.clickAddToCartBtn(i);
        }
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        ChackoutOverview overview = checkout.continueBtn();
        CheckoutComplete complete = overview.finishBtn();
        condition = complete.getTitle().equals("Checkout: Complete!");
        EXCEL.report(condition, 8,
                errMessage, complete.getTitle());
        Assert.assertTrue(condition, errMessage);
    }

    @Test
    public void amountOfProductPriceAndTotalPrice() {
        errMessage = "~The summary products prices should be equal to total price.";
        Home home = new Login(driver).logIn();
        for (int i = 0; i < home.getAddToCartList().size(); i++){
            home.clickAddToCartBtn(i);
        }
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        ChackoutOverview overview = checkout.continueBtn();
        condition = overview.compereSumWithTotal();
        EXCEL.report(condition, 9,
                errMessage, "Products price summary: " + overview.getItemsPriceSummary() +
                        "\nTotal item price: " +overview.itemTotalPrice());
        Assert.assertTrue(condition, errMessage);
    }





}

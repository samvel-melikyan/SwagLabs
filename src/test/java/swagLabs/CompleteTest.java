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

public class CompleteTest extends BaseTest{
    private final ExcelReport EXCEL = new ExcelReport("Complete");

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
        EXCEL.report(condition, 1,
                errMessage, complete.getTitle());
        Assert.assertTrue(condition, errMessage);
    }

    @Test
    public void backHome() {
        errMessage = "~The Home page should be opened.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        ChackoutOverview overview = checkout.continueBtn();
        CheckoutComplete complete = overview.finishBtn();
        home = complete.backToProductsBtn();
        condition = home.getTitle().equals("Products");
        EXCEL.report(condition, 2,
                errMessage, complete.getTitle());
        Assert.assertTrue(condition, errMessage);
    }
}

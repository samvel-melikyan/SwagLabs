package swagLabs;

import com.swaglabs.pages.Home;
import com.swaglabs.pages.Login;
import com.swaglabs.pages.Product;
import com.swaglabs.pages.shoppingCart.Checkout;
import com.swaglabs.pages.shoppingCart.YourCart;
import com.swaglabs.util.ExcelReport;
import com.swaglabs.util.TakeScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class YourCartTest extends BaseTest{
    private final ExcelReport EXCEL = new ExcelReport("Your Cart");
    @Test
    public void yourCartBtn(){
        errMessage = "~Your Cart page did not opened or Title did not match.";
        Home home = new Login(driver).logIn();
        YourCart cart = home.shoppingCart();
        condition = cart.getTitle().equals("Your Cart");
        EXCEL.report(condition, 1,
                errMessage, "Title is: \n" + cart.getTitle());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void productOpening(){
        errMessage = "~Product page did not opened or Title/Name did not match.";
        index = 0;
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(index);
        String expectedName = home.getItemName(index);
        YourCart cart = home.shoppingCart();
        Product product = cart.clickItem(index);
        condition = expectedName.equals(product.getTitle());
        EXCEL.report(condition, 2,
                errMessage, "Entered products name is: \n" + product.getTitle());
        Assert.assertTrue(condition, errMessage);
    }

    @Test
    public void productRemoving(){
        errMessage = "~Product did not removed.";
        index = 0;
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(index);
        String name = home.getItemName(index);
        YourCart cart = home.shoppingCart();
        cart.remove(index);
        condition = !cart.exist(name);
        EXCEL.report(condition, 3,
                errMessage, "Product removed: \n" + condition);
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void continueBtn(){
        errMessage = "~Home page did not opened.";
        Home home = new Login(driver).logIn();
        YourCart cart = home.shoppingCart();
        home = cart.clickContinueShopping();
        condition = home.getTitle().contains("Products");
        EXCEL.report(condition, 4,
                errMessage, "Moved to Home page: \n" + condition);
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void checkoutBtn(){
        errMessage = "~Checkout page did not opened.";
        Home home = new Login(driver).logIn();
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        condition = checkout.getTitle().contains("Checkout: Your Information");
        EXCEL.report(condition, 5,
                errMessage, "Moved to Checkout page: \n" + condition);
        Assert.assertTrue(condition, errMessage);
    }

    @Test
    public void iconVariation() throws IOException {
        errMessage = "~Icons did not match.";
        Home home = new Login(driver).logIn();
        TakeScreenshot screenshot1 = new TakeScreenshot();
        home.clickAddToCartBtn(1);
        screenshot1.saveImage(home.getImage(1));
        TakeScreenshot screenshot2 = new TakeScreenshot();
        home.clickAddToCartBtn(2);
        screenshot2.saveImage(home.getImage(2));
        BufferedImage expected = screenshot1.getExpectedImage();
        BufferedImage actual = screenshot1.getActualImage();
        condition = new ImageDiffer().makeDiff(expected, actual).hasDiff();
        TakeScreenshot screenshot3 = new TakeScreenshot();
        home.clickRemoveBtnList(0);
        screenshot3.saveImage(home.getImage(0));
        expected = screenshot1.getExpectedImage();
        actual = screenshot1.getActualImage();
        condition = new ImageDiffer().makeDiff(expected, actual).hasDiff() && condition;
        EXCEL.report(condition, 6,
                errMessage, "Icons match: \n" + condition);
        Assert.assertTrue(condition, errMessage);
    }

    @Test
    public void iconCountVariation() throws IOException, InterruptedException {
        errMessage = "~Icons did not changed appropriately.";
        Home home = new Login(driver).logIn();
        int count = home.getCartIconNumber();
        home.clickAddToCartBtn(1);
        condition = count < home.getCartIconNumber();
        count = home.getCartIconNumber();
        home.clickAddToCartBtn(2);
        condition = count < home.getCartIconNumber() && condition;
        EXCEL.report(condition, 7,
                errMessage, "Icons count changed appropriately: \n" + condition);
        Assert.assertTrue(condition, errMessage);
    }
}

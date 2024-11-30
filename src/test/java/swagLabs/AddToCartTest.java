package swagLabs;

import com.swaglabs.pages.Home;
import com.swaglabs.pages.Login;
import com.swaglabs.pages.Product;
import com.swaglabs.pages.shoppingCart.YourCart;
import com.swaglabs.util.ExcelReport;
import com.swaglabs.util.TakeScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class AddToCartTest extends BaseTest{
    private final ExcelReport excel = new ExcelReport("Add to Cart");

    @Test
    public void buttonVariation(){
        Home home = new Login(driver).logIn();
        home.clickAddToCart(0);
        String actual = home.getRemoveBtnListList().get(0).getText();
        condition = actual.equals("Remove");
        excel.report(condition, 1,
                "~Button doesn't changed.", "Button text is: \n" + actual);
        Assert.assertTrue(
                condition,
                "~Button doesn't changed."
        );
    }
    @Test
    public void basketIconVariation() throws IOException {
        errMessage = "~Basket Icon doesn't changed.";
        TakeScreenshot screenshot = new TakeScreenshot();
        Home home = new Login(driver).logIn();
        screenshot.saveImage(home.getCartImage());
        home.clickAddToCart(1);
        TakeScreenshot screenshot2 = new TakeScreenshot();
        screenshot2.saveImage(home.getCartImage());
        BufferedImage expected = screenshot.getExpectedImage();
        BufferedImage actual = screenshot.getActualImage();
        condition = new ImageDiffer().makeDiff(expected, actual).hasDiff();
        excel.report(condition, 2,
                errMessage,
                "Basket icon changed: \n" + condition);
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void addingToCart(){
        errMessage = "~Product doesn't added.";
        Home home = new Login(driver).logIn();
        home.clickAddToCart(2);
        String name = home.getItemName(2);
        YourCart cart = home.shoppingCart();
        condition = cart.getName(name).equals(name);
        excel.report(condition, 3,
                errMessage, "Product added to cart: \n" + condition);
        Assert.assertTrue(
                condition,
                errMessage
        );
    }
    @Test
    public void buttonVariationProductPage(){
        Home home = new Login(driver).logIn();
        Product product = home.clickItemByIndex(0);
        product.addToCartBtn();
        String actual = product.getRemoveBtnText();
        condition = actual.equals("Remove");
        excel.report(condition, 4,
                "~Button doesn't changed.", "Button text is: \n" + actual);
        Assert.assertTrue(
                condition,
                "~Button doesn't changed."
        );
    }

    @Test
    public void basketIconVariationProductPage() throws IOException {
        errMessage = "~Basket Icon doesn't changed.";
        TakeScreenshot screenshot = new TakeScreenshot();
        Home home = new Login(driver).logIn();
        screenshot.saveImage(home.getCartImage());
        Product product = home.clickItemByIndex(0);
        product.addToCartBtn();
        TakeScreenshot screenshot2 = new TakeScreenshot();
        screenshot2.saveImage(home.getCartImage());
        BufferedImage expected = screenshot.getExpectedImage();
        BufferedImage actual = screenshot.getActualImage();
        condition = new ImageDiffer().makeDiff(expected, actual).hasDiff();
        excel.report(condition, 5,
                errMessage,
                "Basket icon changed: \n" + condition);
        Assert.assertTrue(condition, errMessage);
    }

    @Test
    public void addingToCartProductPage(){
        errMessage = "~Product doesn't added.";
        Home home = new Login(driver).logIn();
        Product product = home.clickItemByIndex(0);
        String name = product.getTitle();
        product.addToCartBtn();
        YourCart cart = product.clickShoppingCart();
        condition = cart.getName(name).equals(name);
        excel.report(condition, 6,
                errMessage, "Product added to cart: \n" + cart.getName(name));
        Assert.assertTrue(
                condition,
                errMessage
        );
    }

    @Test
    public void remove(){
        index = 0;
        errMessage = "~Product doesn't removed.";
        Home home = new Login(driver).logIn();
        home.clickAddToCart(index);
        home.clickRemoveBtnList(index);
        YourCart cart = home.shoppingCart();
        condition = cart.exist();
        excel.report(!condition, 7,
                errMessage, "Product remove from cart: \n" + !condition);
        Assert.assertFalse(
                condition,
                errMessage
        );
    }

    @Test
    public void removeProductPage(){
        errMessage = "~Product doesn't removed.";
        Home home = new Login(driver).logIn();
        Product product = home.clickItemByIndex(0);
        product.addToCartBtn();
        product.remove();
        YourCart cart = product.clickShoppingCart();
        condition = cart.exist();
        excel.report(!condition, 8,
                errMessage, "Product removed from cart: \n" + !condition);
        Assert.assertFalse(
                condition,
                errMessage
        );
    }

    @Test
    public void removeShoppingCartPage(){
        errMessage = "~Product doesn't removed.";
        Home home = new Login(driver).logIn();
        Product product = home.clickItemByIndex(0);
        product.addToCartBtn();
        YourCart cart = product.clickShoppingCart();
        cart.remove(0);
        condition = cart.exist();
        excel.report(!condition, 9,
                errMessage, "Product removed from cart: \n" + !condition);
        Assert.assertFalse(
                condition,
                errMessage
        );
    }


}

package swagLabs;

import com.swaglabs.pages.Home;
import com.swaglabs.pages.Login;
import com.swaglabs.pages.Product;
import com.swaglabs.util.ExcelReport;
import com.swaglabs.util.TakeScreenshot;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProductDisplayTest extends BaseTest {
    private ExcelReport EXCEL = new ExcelReport("Product Display");


    @Test
    public void productsTitle() {
        index = 0;
        Home home = new Login(driver).logIn();
        String productTitle = home.getItemName(index);
        Product product = home.clickItemByIndex(index);
        EXCEL.report(productTitle.equals(product.getTitle()), 1,
                "~Titles are different.",
                "From Home page: \n" + productTitle +
                        "\nFrom product display page: \n" + product.getTitle());
        Assert.assertEquals(
                productTitle,
                product.getTitle(),
                "~Titles are different."
        );
    }


    @Test
    public void productsPrice() {
        Home home = new Login(driver).logIn();
        index = 0;
        String productPrice = home.getItemPrice(index);
        Product product = home.clickItemByIndex(index);
        EXCEL.report(productPrice.equals(product.getPrice()), 2,
                "~Titles are different.",
                "From Home page: \n" + productPrice +
                        "\nFrom product display page: \n" + product.getPrice());
        Assert.assertEquals(
                productPrice,
                product.getPrice(),
                "~Titles are different."
        );
    }

    @Test
    public void productsImage() {
        Home home = new Login(driver).logIn();
        Product product = home.clickItemByIndex(0);
        Boolean imagePresent = (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].complete && typeof arguments[0]" +
                        ".naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", product.getImage());
        EXCEL.report(imagePresent, 3,
                "~Image didn't exist.",
                "Product image exist: \n" + imagePresent);
        Assert.assertTrue(imagePresent);
    }

    @Test
    public void addToCart() throws IOException {
        TakeScreenshot screenshot = new TakeScreenshot();
        Home home = new Login(driver).logIn();
        screenshot.saveImage(home.getCartImage());
        Product product = home.clickItemByIndex(0);
        product.addToCartBtn();
        TakeScreenshot screenshot2 = new TakeScreenshot();
        screenshot2.saveImage(product.getCartImage());
        BufferedImage expected = screenshot.getExpectedImage();
        BufferedImage actual = screenshot.getActualImage();
        Boolean isDifferent = new ImageDiffer().makeDiff(expected, actual).hasDiff();
        EXCEL.report(isDifferent, 4,
                "~Item didn't added to Cart.",
                "Item added to Cart: \n" + isDifferent);
        Assert.assertTrue(isDifferent);
    }
    @Test
    public void backToProducts(){
        String errMessage =  "~The products page did not opened.";
        Home home = new Login(driver).logIn();
        Product product = home.clickItemByIndex(0);
        home = product.backToProducts();
        Boolean condition = home.getTitle().equals("Products");
        EXCEL.report(condition, 5,
                errMessage,
                home.getTitle());
        Assert.assertTrue(
                condition,
                errMessage
        );
    }


}

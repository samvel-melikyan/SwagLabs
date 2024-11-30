package swagLabs;

import com.swaglabs.pages.Home;
import com.swaglabs.pages.Login;
import com.swaglabs.pages.Product;
import com.swaglabs.util.ExcelReport;
import com.swaglabs.util.TakeScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeTest extends BaseTest{

    private final ExcelReport EXCEL = new ExcelReport("Home");

    @Test
    public void pageOpening()  {
        errMessage = "~User should be able to login.";
        Home home = new Login(driver).logIn();
        condition = home.getTitle().equals("Products");
        EXCEL.report(
                condition,1,
                errMessage,
                "Page Title is: " + home.getTitle());
        Assert.assertTrue(
                condition,
                errMessage
        );
    }
    @Test
    public void productTitleFunctionality()  {
        errMessage = "~User should be moved to the product page.";
        Home home = new Login(driver).logIn();
        String name = (home.getItemName(0));
        Product product = home.clickItemByIndex(0);
        condition = product.getTitle().equals(name);
        driver.navigate().back();
        product = home.clickItemByIndex(home.getInventoryList().size()-1);
        name = product.getTitle();
        driver.navigate().back();
        condition = condition && name.equals(home.getItemName(home.getInventoryList().size()-1));
        EXCEL.report(
                condition,2,
                errMessage,
                "Titles equal: " + condition);
        Assert.assertTrue(
                condition,
                errMessage
        );
    }
    @Test
    public void productPictureFunctionality()  {
        errMessage = "~User should be moved to the product page, each time.";
        Home home = new Login(driver).logIn();
        String name = home.getItemName(0);
        Product product = home.clickProductImage(0);
        condition = product.getTitle().equals(name);
        driver.navigate().back();
        int lastIndex = home.getInventoryList().size() - 1;
        name = home.getItemName(lastIndex);
        product = home.clickProductImage(lastIndex);
        condition = condition && product.getTitle().equals(name);
        EXCEL.report(
                condition,3,
                errMessage,
                "Page opened: " + condition);
        Assert.assertTrue(
                condition,
                errMessage
        );
    }

    @Test
    public void productTitleIdentity()  {
        errMessage = "~Titles should be different.";
        Home home = new Login(driver).logIn();
        int lastIndex = home.getInventoryList().size()-1;
        condition = home.getItemName(0).equals(home.getItemName(1));
        condition = condition && home.getItemName(lastIndex).equals(home.getItemName(2));
        EXCEL.report(
                !condition,4,
                errMessage,
                "Titles are:\n- " + home.getItemName(0) +
                         "\n- " + home.getItemName(1) + "\n- "
                         + home.getItemName(lastIndex) + "\n- " + home.getItemName(2));
        Assert.assertFalse(
                condition,
                errMessage
        );
    }

    @Test
    public void productImageIdentity() throws IOException {
        errMessage = "~Images should be different.";
        Home home = new Login(driver).logIn();
        int lastIndex = home.getInventoryList().size()-1;
        TakeScreenshot screenshot = new TakeScreenshot();
        screenshot.saveImage(home.getImage(0));
        TakeScreenshot screenshot2 = new TakeScreenshot();
        screenshot2.saveImage(home.getImage(1));
        BufferedImage expected = screenshot.getExpectedImage();
        BufferedImage actual = screenshot.getActualImage();
        condition = new ImageDiffer().makeDiff(expected, actual).hasDiff();
        TakeScreenshot screenshot3 = new TakeScreenshot();
        screenshot3.saveImage(home.getImage(4));
        TakeScreenshot screenshot4 = new TakeScreenshot();
        screenshot4.saveImage(home.getImage(lastIndex));
        expected = screenshot.getExpectedImage();
        actual = screenshot.getActualImage();
        condition = condition && new ImageDiffer().makeDiff(expected, actual).hasDiff();
        EXCEL.report(
                condition,5,
                errMessage,
                "Images are different: " + condition);
        Assert.assertTrue(
                condition,
                errMessage
        );
    }

    @Test
    public void description(){
        errMessage = "Descriptions should be the same in product page.";
        Home home = new Login(driver).logIn();
        Product product = home.clickItemByIndex(0);
        String firstItem = product.getDescription();
        driver.navigate().back();
        product = home.clickItemByIndex(1);
        String secondItem = product.getDescription();
        driver.navigate().back();
        condition = home.getDescriptionsByIndex(0).equals(firstItem);
        condition = condition && home.getDescriptionsByIndex(1).equals(firstItem);
        EXCEL.report(!condition, 6,
                errMessage, firstItem + "\n" + secondItem);
        Assert.assertFalse(
                condition,
                errMessage
        );
    }

    @Test
    public void price(){
        errMessage = "Prices should be the same in product page.";
        Home home = new Login(driver).logIn();
        Product product = home.clickItemByIndex(0);
        String firstItem = product.getPrice();
        driver.navigate().back();
        product = home.clickItemByIndex(1);
        String secondItem = product.getPrice();
        driver.navigate().back();
        condition = home.getDescriptionsByIndex(0).equals(firstItem);
        condition = condition && home.getDescriptionsByIndex(1).equals(firstItem);
        EXCEL.report(!condition, 7,
                errMessage, home.getItemPrice(0) + "/" + firstItem +
                        "\n" + home.getItemPrice(1) + "/" + secondItem);
        Assert.assertFalse(
                condition,
                errMessage
        );
    }
    @Test
    public void image() throws IOException, InterruptedException, AWTException {
        errMessage = "~Images should be the same in both places.";
        Home home = new Login(driver).logIn();
        TakeScreenshot screenshot = new TakeScreenshot();
        screenshot.download(home.getImage(0));
        Product product = home.clickProductImage(0);
        TakeScreenshot screenshot1 = new TakeScreenshot();
        screenshot1.download(product.getImage());
        BufferedImage expected = screenshot.getExpectedImage();
        BufferedImage actual = screenshot.getActualImage();
        condition = new ImageDiffer().makeDiff(expected, actual).hasDiff();
        EXCEL.report(!condition, 8,
                errMessage, "Images are different: " + condition);
        Assert.assertFalse(condition, errMessage);
    }
}

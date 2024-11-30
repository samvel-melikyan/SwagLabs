package swagLabs;

import com.swaglabs.pages.Home;
import com.swaglabs.pages.Login;
import com.swaglabs.util.ExcelReport;
import com.swaglabs.util.TakeScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuBarTest extends BaseTest{
    private final ExcelReport EXCEL = new ExcelReport("Menu Bar");

    @Test
    public void menuBarOpening() {
        errMessage = "~Menu bar should be opened.";
        Home home = new Login(driver).logIn();
        home.menuBtn();
        condition = home.isAllItemBtnClickable();
        EXCEL.report(condition,1, errMessage,
                "Menu bar opened: " + condition);
        Assert.assertTrue(condition, errMessage);
    }

    @Test
    public void menuAbout() {
        errMessage = "~User should be moved to About page.";
        Home home = new Login(driver).logIn();
        home.menuBtn();
        home.about_menu();
        condition = driver.getCurrentUrl().equals("https://saucelabs.com/");
        EXCEL.report(condition,2, errMessage,
                "Moved to About page: " + condition + "\n" + driver.getCurrentUrl());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void resetAppState() throws IOException {
        errMessage = "~All products, added to cart, should be removed.";
        Home home = new Login(driver).logIn();
        TakeScreenshot screenshot = new TakeScreenshot();
        screenshot.saveImage(home.getCartImage());
        home.clickAddToCartBtn(0);
        home.clickAddToCartBtn(2);
        home.menuBtn();
        home.resetAppState_menu();
        TakeScreenshot screenshot1 = new TakeScreenshot();
        screenshot1.saveImage(home.getCartImage());
        BufferedImage expected = screenshot.getExpectedImage();
        BufferedImage actual = screenshot.getActualImage();
        condition = new ImageDiffer().makeDiff(expected, actual).hasDiff();
        EXCEL.report(!condition,3, errMessage,
                "App is reseted: " + !condition );
        Assert.assertFalse(condition, errMessage);
    }






}

package swagLabs;

import com.swaglabs.pages.Home;
import com.swaglabs.pages.Login;
import com.swaglabs.util.ExcelReport;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class FooterTest extends BaseTest{
    private final ExcelReport EXCEL = new ExcelReport("Footer");
    ArrayList<String> tabs;
    @Test
    public void twitter() throws InterruptedException {
        errMessage = "~Twitter should been opened.";
        Home home = new Login(driver).logIn();
        home.clickTwitter();
        tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(5000);
        condition = driver.getCurrentUrl().equals("https://x.com/saucelabs");
        EXCEL.report(condition,1, errMessage,
                "Twitter is opened: " + condition  + "\n" + driver.getCurrentUrl());
        Assert.assertTrue(
                condition,
                errMessage);
    }
    @Test
    public void facebook() throws InterruptedException {
        errMessage = "~Facebook should been opened.";
        Home home = new Login(driver).logIn();
        home.clickFacebook();
        tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(5000);
        condition = driver.getCurrentUrl().equals("https://www.facebook.com/saucelabs");
        EXCEL.report(condition,2, errMessage,
                "Facebook is opened: " + condition + "\n" + driver.getCurrentUrl());
        Assert.assertTrue(
                condition,
                errMessage);
    }


    @Test
    public void linkedIn() throws InterruptedException {
        errMessage = "~LinkedIn should been opened.";
        Home home = new Login(driver).logIn();
        home.clickLinkedin();
        tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(5000);
        condition = driver.getCurrentUrl().equals("https://www.linkedin.com/company/sauce-labs/");
        EXCEL.report(condition,3, errMessage,
                "LinkedIn is opened: " + condition + "\n" + driver.getCurrentUrl());
        Assert.assertTrue(
                condition,
                errMessage);
    }
}

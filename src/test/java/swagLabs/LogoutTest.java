package swagLabs;

import com.swaglabs.pages.Home;
import com.swaglabs.pages.Login;
import com.swaglabs.util.ExcelReport;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest{
    private ExcelReport EXCEL =  new ExcelReport("Logout");
    @Test
    public void fromMenu(){
        Home home = new Login(driver).logIn();
        home.menuBtn();
        home.logOut_menu();
        EXCEL.report(
                driver.getCurrentUrl().equals("https://www.saucedemo.com/"),1, "~Login page does not opened",
                "Login page opened: " + driver.getCurrentUrl().equals("https://www.saucedemo.com/"));
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/",
                "~Login page does not opened"
        );
    }
    @Test
    public void accountStability(){
        Home home = new Login(driver).logIn();
        home.addToCattBtn();
        home.logout().logIn();
        EXCEL.report(
                home.isShoppingCartIconExist(),2, "~Actions done before has not been saved",
                "Actions done before has preserved : " + home.isShoppingCartIconExist());
        Assert.assertTrue(
            home.isShoppingCartIconExist(),
            "~Actions done before has not been saved"
        );

    }
    @Test
    public void logoutAndForward(){
        Home home = new Login(driver).logIn();
        home.logout();
        driver.navigate().forward();
        EXCEL.report(
                driver.getCurrentUrl().equals("https://www.saucedemo.com/"),3, "~User should not be able to login",
                "User has logged in : " + driver.getCurrentUrl().equals("https://www.saucedemo.com/"));
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/",
                "~User should not be able to login"
        );
    }

    @Test
    public void immediatelyLogInAndOut(){
        Home home = new Login(driver).logIn();
        home.logout();
        Home title = new Login(driver).logIn();
        EXCEL.report(
                title.getTitle().equals("Products"),4, "~User should be logged in.",
                "User has logged in : " + title.getTitle().equals("Products"));
        Assert.assertEquals(
                title.getTitle(),
                "Products",
                "~User should be logged in."
        );
    }




}

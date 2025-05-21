package swagLabs;


import com.swaglabs.util.CustomElement;
import com.swaglabs.util.ExcelReport;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.swaglabs.pages.Home;
import com.swaglabs.pages.Login;

import java.io.IOException;

import static com.swaglabs.util.BaseDriver.getDriver;


public class LoginTest extends BaseTest {

    private final ExcelReport EXCEL =  new ExcelReport("Login");

    private String username = "standard_user";  //  problem_user
    private String password = "secret_sauce";

    @Test
    public void validCredentials()  {
        errMessage = "~User should be able to login.";
        Login login = new Login(driver);
        login.setValues(username, password);
        Home title = login.clickLoginButton();
        EXCEL.report(
                title.getTitle().equals("Products"),1,
                errMessage,
                "Logged in: " + title.getTitle().equals("Products"));
        Assert.assertEquals(
                title.getTitle(),
                "Products",
                errMessage
        );
    }
    @Test
    public void invalidCredentials()  {
        errMessage = "Epic sadface: Username and password do not match any user in this service";
        Login login = new Login(driver);
        login.setValues(username+"invalid", password+"invalid");
        login.clickLoginBtn();
        EXCEL.report(
                login.getErrorMsg().equals(errMessage),2,
                "~User should not be able to login.\n" + errMessage,
                "Logged in: " + login.isLoggedIn());
        Assert.assertEquals(
                login.getErrorMsg(),
                errMessage,
                "~Error didn't appeared or didn't match"
        );
    }
    @Test
    public void invalidPassword()  {
        errMessage = "Epic sadface: Username and password do not match any user in this service";
                Login login = new Login(driver);
        login.setValues(username, password+"invalid");
        login.clickLoginBtn();
        EXCEL.report(
                login.getErrorMsg().equals(errMessage),3,
                "~User should not be able to login.\n" + errMessage,
                "Logged in: " + login.isLoggedIn());
        Assert.assertEquals(
                login.getErrorMsg(),
                errMessage,
                "~Error didn't appeared or didn't match"
        );
    }
    @Test
    public void withoutCredentials()  {
        errMessage = "Epic sadface: Username is required";
        Login login = new Login(driver);
        login.clickLoginBtn();
        EXCEL.report(
                login.getErrorMsg().equals(errMessage),4,
                "~User should not be able to login.\n" + errMessage,
                "Logged in: " + login.isLoggedIn());
        Assert.assertEquals(
                login.getErrorMsg(),
                errMessage,
                "~Error didn't appeared or didn't match"
        );
    }
    @Test
    public void invalidUsername()  {
        errMessage = "Epic sadface: Username and password do not match any user in this service";
        Login login = new Login(driver);
        login.setValues(username+"invalid", password);
        login.clickLoginBtn();
        EXCEL.report(
                login.getErrorMsg().equals(errMessage),5,
                "~User should not be able to login.\n" + errMessage,
                "Logged in: " + login.isLoggedIn());
        Assert.assertEquals(
                login.getErrorMsg(),
                errMessage,
                "~Error didn't appeared or didn't match"
        );
    }
    @Test
    public void withoutPassword()  {
        errMessage = "Epic sadface: Password is required";
        Login login = new Login(driver);
        login.setUsername(username);
        login.clickLoginBtn();
        EXCEL.report(
                login.getErrorMsg().equals(errMessage),6,
                "~User should not be able to login.\n" + errMessage,
                "Logged in: " + login.isLoggedIn());
        Assert.assertEquals(
                login.getErrorMsg(),
                "Epic sadface: Password is required",
                "~Error didn't appeared or didn't match"
        );
    }

    @Test
    public void withoutUsername()  {
        errMessage = "Epic sadface: Username is required";
        Login login = new Login(driver);
        login.setPassword(password);
        login.clickLoginBtn();
        EXCEL.report(
                login.getErrorMsg().equals(errMessage),7,
                "~User should not be able to login.\n" + errMessage,
                "Logged in: " + login.isLoggedIn());
        Assert.assertEquals(
                login.getErrorMsg(),
                errMessage,
                "~Error didn't appeared or didn't match"
        );
    }
    @Test
    public void passwordInUppercase()  {
        errMessage = "Epic sadface: Username and password do not match any user in this service";
        Login login = new Login(driver);
        login.setValues(username, password.toUpperCase());
        login.clickLoginBtn();
        EXCEL.report(
                login.getErrorMsg().equals(errMessage),8,
                "~User should not be able to login.\n" + errMessage,
                "Logged in: " + login.isLoggedIn());
        Assert.assertEquals(
                login.getErrorMsg(),
                errMessage,
                "~Error didn't appeared or didn't match"
        );
    }
    @Test
    public void usernameInUppercase()  {
        errMessage = "Epic sadface: Username and password do not match any user in this service";
        Login login = new Login(driver);
        login.setValues(username.toUpperCase(), password);
        login.clickLoginBtn();
        EXCEL.report(
                login.getErrorMsg().equals(errMessage),9,
                "~User should not be able to login.\n" + errMessage,
                "Logged in: " + login.isLoggedIn());
        Assert.assertEquals(
                login.getErrorMsg(),
                errMessage,
                "~Error didn't appeared or didn't match"
        );
    }
    @Test
    public void passwordWithSpace()  {
        errMessage = "Epic sadface: Username and password do not match any user in this service";
        Login login = new Login(driver);
        login.setValues(username, password + "  ");
        login.clickLoginBtn();
        EXCEL.report(
                login.getErrorMsg().equals(errMessage),10,
                "~User should not be able to login.\n" + errMessage,
                "Logged in: " + login.isLoggedIn());
        Assert.assertEquals(
                login.getErrorMsg(),
                errMessage,
                "~Error didn't appeared or didn't match"
        );
    }
    @Test
    public void usernameWithSpace()  {
        errMessage = "Epic sadface: Username and password do not match any user in this service";
        Login login = new Login(driver);
        login.setValues(username+"  ", password);
        login.clickLoginBtn();
        EXCEL.report(
                login.getErrorMsg().equals(errMessage),11,
                "~User should not be able to login.\n" + errMessage,
                "Logged in: " + login.isLoggedIn());
        Assert.assertEquals(
                login.getErrorMsg(),
                errMessage,
                "~Error didn't appeared or didn't match"
        );
    }
    @Test
    public void keyBoardActions()  {
        Login login = new Login(driver);
        Actions keyboard = new Actions(driver);
        keyboard.sendKeys(Keys.TAB, username).perform();
        keyboard.sendKeys(Keys.TAB, password).perform();
        keyboard.sendKeys(Keys.TAB).perform();
        Home title = login.loginWithKeyboard(keyboard);
        EXCEL.report(
                title.getTitle().equals("Products"),12,
                "~User should be able to login.",
                "Logged in: " + login.isLoggedIn());
        Assert.assertEquals(
                title.getTitle(),
                "Products",
                "~The title is not valid"
        );
    }
    @Test
    public void placeholder()  {
        Login login = new Login(driver);
        String placeholder = login.getPassword().getAttribute("placeholder");
        EXCEL.report(
                placeholder.equals("Password"),13,
                "~Placeholder should exist.",
                "Placeholder exist: " + placeholder.equals("Password"));
        Assert.assertEquals(
                placeholder,
                "Password",
                "~Placeholder doesn't exist"
        );
    }
    @Test
    public void navigateBackAndLogin(){
        errMessage = "Epic sadface: Username is required";
        Login login = new Login(driver);
        login.setValues(username, password);
        login.clickLoginBtn();
        driver.navigate().back();
        login.clickLoginBtn();
        EXCEL.report(
                login.getErrorMsg().equals(errMessage),14,
                "~User should not be able to login.\n" + errMessage,
                "Logged in: " + login.isLoggedIn());
        Assert.assertEquals(
                login.getErrorMsg(),
                errMessage,
                "~Error didn't appeared or didn't match"
        );
    }

    @Test
    public void backAndForward(){
        Login login = new Login(driver);
        login.setValues(username, password);
        login.clickLoginBtn();
        driver.navigate().back();
        driver.navigate().forward();
        EXCEL.report(
                !login.isLoggedIn(),15,
                "~User should not be able to login.",
                "Logged in: " + login.isLoggedIn());
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/",
                "~Error didn't appeared or didn't match"
        );
    }
    @Test
    public void passwordInDotted(){
        errMessage = "Password's text is not hidden";
        Login login = new Login(driver);
        login.setPassword("text should be hidden");
        String password = login.getPassword().getAttribute("type");
        EXCEL.report(
                password.equals("password"),16,
                "~Password should be in dots.\n" + errMessage,
                "Password doted: " + password.equals("password"));
        Assert.assertEquals(
                password,
                "password",
                "Password's text is not hidden"

        );
    }

    @Test
    public void copyPasswordsText(){
        Login login = new Login(driver);
        login.setPassword(password);
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys(Keys.ARROW_LEFT).perform();
        login.copyPast(login.getUsername());
        String usernameValue = login.getUsername().getAttribute("value");
        EXCEL.report(
                !usernameValue.equals(password),17,
                "~Password should not be copied.",
                "Password copied: " + !usernameValue.equals(password));
        Assert.assertNotEquals(
                usernameValue,
                password,
                "~Text should not be copied"
                );
    }

    @Test
    public void closeBrowser() throws IOException {
        errMessage = "Epic sadface: You can only access '/inventory.html' when you are logged in.";
        Login login = new Login(driver);
        login.setValues(username, password);
        login.clickLoginBtn();
        tearDown();
        setUp();
        driver.get("https://www.saucedemo.com/inventory.html");
        String actual = CustomElement.getText(driver.findElement(By.cssSelector(".error-message-container.error")));
        EXCEL.report(
                actual.equals(errMessage),18,
                "~Should not be able to enter to page.",
                "Logged in: " + getDriver().getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"));
        Assert.assertEquals(
                actual,
                errMessage,
                "~Should not be able to enter to page."
        );
        tearDown();
    }


}

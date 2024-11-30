package swagLabs;

import com.swaglabs.pages.Home;
import com.swaglabs.pages.Login;
import com.swaglabs.pages.shoppingCart.ChackoutOverview;
import com.swaglabs.pages.shoppingCart.Checkout;
import com.swaglabs.pages.shoppingCart.YourCart;
import com.swaglabs.util.ExcelReport;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest{

    private final ExcelReport EXCEL = new ExcelReport("Checkout");
    @Test
    public void pageOpeningWithProductAdded() {
        errMessage = "~User should be moved to the Checkout page.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        condition = checkout.getTitle().equals("Checkout: Your Information");
        EXCEL.report(condition, 1,
                errMessage, "Title is: " + checkout.getTitle());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void pageOpeningWithoutProductAdded() {
        errMessage = "~User should be moved to the Checkout page.";
        Home home = new Login(driver).logIn();
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        condition = checkout.getTitle().equals("Checkout: Your Information");
        EXCEL.report(condition, 2,
                errMessage, "Title is: " + checkout.getTitle());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void cancel(){
        errMessage = "~User should be moved back to the Your Cart page.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        cart = checkout.cancelBtn();
        condition = cart.getTitle().equals("Your Cart");
        EXCEL.report(condition, 3,
                errMessage, "Title is: " + cart.getTitle());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void fillingReqFields(){
        errMessage = "~User should be moved to the 'Checkout: Overview' page.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        ChackoutOverview overview = checkout.continueBtn();
        condition = overview.getTitle().equals("Checkout: Overview");
        EXCEL.report(condition, 4,
                errMessage, "Title is: " + overview.getTitle());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void withoutReqFields(){
        errMessage = "~An error should been displayed, user should been stopped to go forward.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.clickContinueBtn();
        Boolean title = checkout.getTitle().equals("Checkout");
        try {
            checkout.getTitle().equals("Checkout");
        }catch (ElementNotVisibleException e) {
            e.printStackTrace();
            title = false;
        }
        condition = checkout.isErrorBtnVisible() && title;
        EXCEL.report(condition, 5,
                errMessage, checkout.getErrorMsg());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void onlyFirstName(){
        errMessage = "~An error should been displayed, user should been stopped to go forward.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.firstName();
        checkout.clickContinueBtn();
        Boolean title = checkout.getTitle().equals("Checkout");
        try {
            checkout.getTitle().equals("Checkout");
        }catch (ElementNotVisibleException e) {
            e.printStackTrace();
            title = false;
        }
        condition = checkout.isErrorBtnVisible() && title;
        EXCEL.report(condition, 6,
                errMessage, checkout.getErrorMsg());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void onlyLastName(){
        errMessage = "~An error should been displayed, user should been stopped to go forward.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.lastName();
        checkout.clickContinueBtn();
        Boolean title = checkout.getTitle().equals("Checkout");
        try {
            checkout.getTitle().equals("Checkout");
        }catch (ElementNotVisibleException e) {
            e.printStackTrace();
            title = false;
        }
        condition = checkout.isErrorBtnVisible() && title;
        EXCEL.report(condition, 7,
                errMessage, checkout.getErrorMsg());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void onlyPostalCode(){
        errMessage = "~An error should been displayed, user should been stopped to go forward.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.postalCode();
        checkout.clickContinueBtn();
        Boolean title = checkout.getTitle().equals("Checkout");
        try {
            checkout.getTitle().equals("Checkout");
        }catch (ElementNotVisibleException e) {
            e.printStackTrace();
            title = false;
        }
        condition = checkout.isErrorBtnVisible() && title;
        EXCEL.report(condition, 8,
                errMessage, checkout.getErrorMsg());
        Assert.assertTrue(condition, errMessage);

    }
    @Test
    public void withoutPostalCode(){
        errMessage = "~An error should been displayed, user should been stopped to go forward.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.firstName();
        checkout.lastName();
        checkout.clickContinueBtn();
        Boolean title = checkout.getTitle().equals("Checkout");
        try {
            checkout.getTitle().equals("Checkout");
        }catch (ElementNotVisibleException e) {
            e.printStackTrace();
            title = false;
        }
        condition = checkout.isErrorBtnVisible() && title;
        EXCEL.report(condition, 9,
                errMessage, checkout.getErrorMsg());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void firstNameAndPostalCode(){
        errMessage = "~An error should been displayed, user should been stopped to go forward.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.firstName();
        checkout.postalCode();
        checkout.clickContinueBtn();
        Boolean title = checkout.getTitle().equals("Checkout");
        try {
            checkout.getTitle().equals("Checkout");
        }catch (ElementNotVisibleException e) {
            e.printStackTrace();
            title = false;
        }
        condition = checkout.isErrorBtnVisible() && title;
        EXCEL.report(condition, 10,
                errMessage, checkout.getErrorMsg());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void closeErrorIssue(){
        errMessage = "~Error message should be closed.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.clickContinueBtn();
        checkout.closeErrorMsg();
        condition = checkout.isErrorBtnVisible();
        EXCEL.report(!condition, 11,
                errMessage, "Error message closed: " + condition);
        Assert.assertFalse(condition, errMessage);
    }
    @Test
    public void withKeyboardKeys(){
        errMessage = "~User should be moved to the 'Checkout: Overview' page.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        cart.clickCheckoutBtn();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.TAB).perform();
        action.sendKeys(Keys.TAB, "Piter").perform();
        action.sendKeys(Keys.TAB, "Peterson").perform();
        action.sendKeys(Keys.TAB, "30103").perform();
        action.sendKeys(Keys.ENTER).perform();
        ChackoutOverview overview = new ChackoutOverview();
        condition = overview.getTitle().equals("Checkout: Overview");
        EXCEL.report(condition, 12,
                errMessage, "Title is: " + overview.getTitle());
        Assert.assertTrue(condition, errMessage);
    }

    @Test
    public void numbersAndLettersInPostalCode(){
        errMessage = "~An error should been displayed, user should been stopped to go forward.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.firstName();
        checkout.lastName();
        checkout.setPostalCode("0020abc");
        checkout.clickContinueBtn();
        Boolean title = checkout.getTitle().equals("Checkout");
        try {
            checkout.getTitle().equals("Checkout");
        }catch (ElementNotVisibleException e) {
            e.printStackTrace();
            title = false;
        }
        condition = checkout.isErrorBtnVisible() && title;
        EXCEL.report(condition, 13,
                errMessage, checkout.getErrorMsg());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void numbersAndSignsInPostalCode(){
        errMessage = "~An error should been displayed, user should been stopped to go forward.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.firstName();
        checkout.lastName();
        checkout.setPostalCode("0020-22%");
        checkout.clickContinueBtn();
        Boolean title = checkout.getTitle().equals("Checkout");
        try {
            checkout.getTitle().equals("Checkout");
        }catch (ElementNotVisibleException e) {
            e.printStackTrace();
            title = false;
        }
        condition = checkout.isErrorBtnVisible() && title;
        EXCEL.report(condition, 14,
                errMessage, checkout.getErrorMsg());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void lettersInPostalCode(){
        errMessage = "~An error should been displayed, user should been stopped to go forward.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.firstName();
        checkout.lastName();
        checkout.setPostalCode("abc");
        checkout.clickContinueBtn();
        Boolean title = checkout.getTitle().equals("Checkout");
        try {
            checkout.getTitle().equals("Checkout");
        }catch (ElementNotVisibleException e) {
            e.printStackTrace();
            title = false;
        }
        condition = checkout.isErrorBtnVisible() && title;
        EXCEL.report(condition, 15,
                errMessage, checkout.getErrorMsg());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void signsWithFirstName(){
        errMessage = "~An error should been displayed, user should been stopped to go forward.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.setFirstName("__Piter__2345");
        checkout.lastName();
        checkout.postalCode();
        checkout.clickContinueBtn();
        Boolean title = checkout.getTitle().equals("Checkout");
        try {
            checkout.getTitle().equals("Checkout");
        }catch (ElementNotVisibleException e) {
            e.printStackTrace();
            title = false;
        }
        condition = checkout.isErrorBtnVisible() && title;
        EXCEL.report(condition, 16,
                errMessage, checkout.getErrorMsg());
        Assert.assertTrue(condition, errMessage);
    }
    @Test
    public void signsWithLastName(){
        errMessage = "~An error should been displayed, user should been stopped to go forward.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.setLastName("Piterson__098$$$");
        checkout.clickContinueBtn();
        Boolean title = checkout.getTitle().equals("Checkout");
        try {
            checkout.getTitle().equals("Checkout");
        }catch (ElementNotVisibleException e) {
            e.printStackTrace();
            title = false;
        }
        condition = checkout.isErrorBtnVisible() && title;
        EXCEL.report(condition, 17,
                errMessage, checkout.getErrorMsg());
        Assert.assertTrue(condition, errMessage);
    }

    @Test
    public void emptySpace(){
        errMessage = "~An error should been displayed, user should been stopped to go forward.";
        Home home = new Login(driver).logIn();
        home.clickAddToCartBtn(0);
        YourCart cart = home.shoppingCart();
        Checkout checkout = cart.clickCheckoutBtn();
        checkout.setValues(" ", " ", " ");
        checkout.clickContinueBtn();
        Boolean title = checkout.getTitle().equals("Checkout");
        try {
            checkout.getTitle().equals("Checkout");
        }catch (ElementNotVisibleException e) {
            e.printStackTrace();
            title = false;
        }
        condition = checkout.isErrorBtnVisible() && title;
        EXCEL.report(condition, 18,
                errMessage, "User entered Overview page: \n" + !condition);
        Assert.assertTrue(condition, errMessage);
    }



















}

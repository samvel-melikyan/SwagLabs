package swagLabs;


import org.testng.Assert;
import org.testng.annotations.Test;
import swaglabs.pages.Home;
import swaglabs.pages.LogIn;


public class Login extends BaseTest {
    private String username = "standard_user";
    private String password = "secret_sauce";


    @Test
    public void validCredentials()  {
        LogIn login = new LogIn(driver);
        login.setValues(username, password);
        Home title = login.clickLoginButton();
        Assert.assertEquals(
                title.getTitle(),
                "Products",
                "The title is not valid"
        );
    }
    public void invalidCredentials()  {
        LogIn login = new LogIn(driver);
        login.setValues(username+"invalid", password+"invalid");
        login.clickLoginButton();
        Assert.assertEquals(
                login.getErrorMsg(),
                "Username and password do not match any user in this service.",
                "Error messages did not match"
        );
    }

}
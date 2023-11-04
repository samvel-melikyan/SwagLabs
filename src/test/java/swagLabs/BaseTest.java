package swagLabs;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static swaglabs.util.BaseDriver.getDriver;
import static swaglabs.util.BaseDriver.setDriver;

public class BaseTest{
    protected WebDriver driver;


    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = getDriver();
//        getDriver().get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null) {
            driver.quit();
            setDriver(null);
        }
    }


}

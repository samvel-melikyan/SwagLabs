package swagLabs;

import com.swaglabs.util.ExcelReport;
import com.swaglabs.util.TakeScreenshot;
import io.qameta.allure.model.TestResult;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

import static com.swaglabs.util.BaseDriver.getDriver;
import static com.swaglabs.util.BaseDriver.setDriver;

public class BaseTest{
    protected Boolean condition;
    protected String errMessage;
    protected WebDriver driver;
    protected int index;
    private ITestResult testResult;


    public void copyPast(WebElement elementBeFilled){
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys("c").perform();
        action.click(elementBeFilled);
        action.keyDown(Keys.CONTROL).sendKeys("v").perform();
    }

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() throws IOException {
        if(driver != null) {
            driver.quit();
            setDriver(null);
        }
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println("> Screenshot has captured while failure.");
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("com/swaglabs/util/failureScreenshot" + "/photo" + new TakeScreenshot().failureNumber() + ".png"));
        }
    }

}

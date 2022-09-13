package com.swaglabs.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.swaglabs.util.BaseDriver.getDriver;

public class WaitHelpers {

    private static WebDriverWait wait;

    public static int waitTime;


    public static void toBeClickable(WebElement element) {
        wait = new WebDriverWait(getDriver(), waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void visibilityOf( WebElement element) {
        wait = new WebDriverWait(getDriver(), waitTime);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void toBeSelected( WebElement element) {
        wait = new WebDriverWait(getDriver(), waitTime);
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    //  RETURNS BOOLEAN IF TITLE IS EQUAL TO GIVEN TEXT

    public static void testToBePresent(WebDriver driver, WebElement element, String text) {
        wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void title(WebDriver driver, String title) {
        wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.titleIs(title));
    }

    public static boolean waitForJQuaryToLoad() {

        WebDriverWait wait = new WebDriverWait(getDriver(), 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) executeJavaScript(getDriver(), "return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        return wait.until(jQueryLoad);
    }


    public static boolean waitForJSToLoad() {
        wait = new WebDriverWait(getDriver(), 30);
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return executeJavaScript(getDriver(), "return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jsLoad);
    }



    private static Object executeJavaScript(WebDriver driver, String s) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(s);
    }


}

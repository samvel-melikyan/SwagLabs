package com.swaglabs.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseDriver {
    private static WebDriver driver = null;

    private static FirefoxOptions options;






    public static void setDriver(WebDriver driver){
        BaseDriver.driver = driver;
    }

    public static WebDriver getDriver(){
        if (driver == null) {
            driver = new FirefoxDriver(setOptions());
        }
        return driver;
    }

    private static FirefoxOptions setOptions(){

        options = new FirefoxOptions();
        options.addArguments("start-maximized");
        options.addArguments("-headless");

        return options;
    }

}

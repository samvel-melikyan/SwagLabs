package com.swaglabs.pages;

import org.openqa.selenium.support.PageFactory;

import static com.swaglabs.util.BaseDriver.getDriver;

public class About extends BasePage {




    public About() {
        super();
        this.load();
        PageFactory.initElements(driver, this);
    }

    public String getURL(){
        return driver.getCurrentUrl();
    }

    @Override
    public void load() {
        getDriver().get("https://saucelabs.com/");
    }

    @Override
    protected void isLoaded() throws Error {
        super.isLoaded();
//        if(driver.getCurrentUrl().contains("https://www.saucedemo.com/"))
//            return true;
//        return false;
    }
}

package com.swaglabs.util;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class CustomElement {


    public static String toUpperCase(){
        return "";
    }


    public static void click(WebElement element){
        WaitHelpers.toBeClickable( element);
        element.click();
    }
    public static void sendKeys(WebElement element, String key){
        WaitHelpers.visibilityOf( element);
        element.clear();
        element.sendKeys(key);
    }

    public static String getText(WebElement element){
        WaitHelpers.visibilityOf(element);
        return element.getText();
    }

    public static String getAttribute(WebElement element, String value){
        WaitHelpers.visibilityOf(element);
        return element.getAttribute(value);
    }

    public static Boolean isVisible(WebElement element){
        try {
            WaitHelpers.visibilityOf(element);
        }catch (TimeoutException e){
            System.out.println("hrer---------------");
            e.printStackTrace();
            return false;
        }
        return true;
    }


//    public static void select(WebElement element){
//        // until element is selected
//        toBeSelected(driver, element);
//
//    }



}

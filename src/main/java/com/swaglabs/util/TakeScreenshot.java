package com.swaglabs.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TakeScreenshot {
    private WebDriver driver;
    private String directoryPath = "src/main/java/com/swaglabs/util/screenshots";
    private String name = "/photo" + number() + ".png";
    private String formatName = "png";

//  constructors
    public TakeScreenshot() {
        this.driver = BaseDriver.getDriver();
    }

    public TakeScreenshot(String directoryPath) {
        this.driver = BaseDriver.getDriver();
        this.directoryPath = directoryPath;
    }

    public TakeScreenshot(String directoryPath, String formatName) {
        this.driver = BaseDriver.getDriver();
        this.directoryPath = directoryPath;
        this.formatName = formatName;
    }
//  -------------------------------

    public Screenshot takeScreenshot(WebElement element) {
        return new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, element);
    }

    public void saveImage(WebElement element) throws IOException {
        ImageIO.write(takeScreenshot(element).getImage(), formatName, new File(directoryPath + name));
    }

    public BufferedImage getExpectedImage() throws IOException {
        return ImageIO.read(new File(directoryPath + "/photo" + (number() - 2) + ".png"));
    }

    public BufferedImage getActualImage() throws IOException {
        return ImageIO.read(new File(directoryPath + "/photo" + (number() - 1) + ".png"));
    }

    public String getFilePath(int i) {
        return directoryPath + "/photo" + (number() - i) + ".png";
    }

    public Boolean isFileExist() {
        File file = new File(directoryPath + "/photo" + (number() - 1) + ".png");
        if (file.exists())
            return true;
        else
            return false;
    }

    public void download(WebElement element) throws IOException {
        URL url = new URL(element.getAttribute("src"));
        ImageIO.write(ImageIO.read(url), formatName, new File(directoryPath + name));//"/picture" + number() + ".png"));
    }

    public int number() {
        return new File(directoryPath).listFiles().length;
    }
    public int failureNumber() {
        return new File("src/main/java/com/swaglabs/util/failureScreenshots").listFiles().length;
    }




}



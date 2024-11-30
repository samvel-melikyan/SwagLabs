package swagLabs;

import com.swaglabs.pages.Home;
import com.swaglabs.pages.Login;
import com.swaglabs.util.ExcelReport;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FilterTest extends BaseTest{

    private final ExcelReport EXCEL =  new ExcelReport("Filter");
    private String errMessage = "~Products are not sorted, by ";
    @Test
    public void letterAscending(){
        Home home = new Login(driver).logIn();
        home.letterAscending();
        EXCEL.report(
                home.compereLetters(3),1, errMessage + "title, in ascending order.",
                "Products are sorted by ascending order : " + home.compereLetters(3));
        Assert.assertTrue(
                home.compereLetters(3),
                errMessage + "title, in ascending order."
        );
    }

    @Test
    public void letterDescending(){
        Home home = new Login(driver).logIn();
        home.letterDescending();
        EXCEL.report(
                home.compereLetters(-3),2, errMessage + "title, in descending order.",
                "Products are sorted by descending order : " + home.compereLetters(-3));
        Assert.assertTrue(
                home.compereLetters(-3),
                errMessage + "title, in descending order."
        );
    }
    @Test
    public void priceAscending(){
        Home home = new Login(driver).logIn();
        home.priceAscending();
        EXCEL.report(
                home.comperePrices(3),3, errMessage + "price, in ascending order.",
                "Products are sorted by ascending order : " + home.comperePrices(3));
        Assert.assertTrue(
                home.comperePrices(3),
                errMessage + "price, in ascending order."
        );
    }

    @Test
    public void priceDescending(){
        Home home = new Login(driver).logIn();
        home.priceDescending();
        EXCEL.report(
                home.comperePrices(-3),4, errMessage + "price, in descending order.",
                "Products are sorted by descending order : " + home.comperePrices(-3));
        Assert.assertTrue(
                home.comperePrices(-3),
                errMessage + "price, in descending order."
        );
    }

}


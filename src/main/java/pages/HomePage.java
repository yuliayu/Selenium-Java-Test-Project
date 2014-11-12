package pages;

import actors.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.WebDriverWrapper;
import utils.Log4Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by test on 11/3/14.
 */

public class HomePage {

    private static final By searchField = By.id("searchbox");
    private static final By searchBtn = By.id("doSearch");
    public static final String SITE = "http://hotline.ua";
    private static final By registerBtn = By.className("reg");



    protected WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void open()
    {
        driver.get(SITE);
    }


    public RegistrationPage goRegistration()
    {
        driver.findElement(registerBtn).click();
        return new RegistrationPage(driver);
    }

    public GoodsPage findProduct(String product)
    {
        Log4Test.info("Searching for the " + product);
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(product);
        driver.findElement(searchBtn).submit();
        driver.manage().timeouts().implicitlyWait(WebDriverWrapper.TIME_TO_WAIT, TimeUnit.SECONDS);
        return new GoodsPage(driver);
    }

}

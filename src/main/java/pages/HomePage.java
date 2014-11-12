package pages;

import actors.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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

    public FilterPage findCatalogItem(String category, String subcategory)
    {
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(By.xpath("//b[contains(text(), '" + category + "')]"));
        Log4Test.info("Opening fridge category from menu...");
        action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//a[contains(text(), '" + subcategory + "')]"))).click().build().perform();
        driver.manage().timeouts().implicitlyWait(WebDriverWrapper.TIME_TO_WAIT, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@id='filters']//a[contains(text(), 'LG')]")).click();


       // Select menu = new Select(driver.findElement(By.className("m-name")));
        //menu.selectByVisibleText("Бытовая техника");

        return new FilterPage(driver);
    }


}

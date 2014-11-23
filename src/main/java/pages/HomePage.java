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
    public final  By CLOSESELECTOR = By.id("shade");


    protected WebDriverWrapper driver;

    public HomePage(WebDriverWrapper driver)
    {
        this.driver = driver;
    }

    public void open()
    {
        driver.get(SITE);
        closeSelector();
    }

    public void closeSelector() {
       WebElement shade = driver.findElement(CLOSESELECTOR);
        if (shade.isDisplayed())
        {
            shade.click();
        }
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

    public FilterPage findFridgeItem()

    {
        Actions actions = new Actions(driver.getOriginalDriver());
        actions.moveToElement(driver.findElement(By.xpath("//a[@href='/bt/']")));
        Log4Test.info("Opening fridge category from menu...");
        actions.perform();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@href='/bt/holodilniki/']")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);



        // Select menu = new Select(driver.findElement(By.className("m-name")));
        //menu.selectByVisibleText("Бытовая техника");

        return new FilterPage(driver);
    }


}

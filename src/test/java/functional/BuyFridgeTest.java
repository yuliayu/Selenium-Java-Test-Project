package functional;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import selenium.WebDriverFactory;
import selenium.WebDriverWrapper;
import utils.Log4Test;
import utils.PropertyLoader;

import java.util.concurrent.TimeUnit;

/**
 * Created by bionic on 11/12/14.
 */
public class BuyFridgeTest {

    public static WebDriverWrapper driver;
    @BeforeSuite
    public void setEnv()
    {
        driver = WebDriverFactory.initDriver("firefox");
//        driver = new HtmlUnitDriver();
    }

    @Test
 // public  WebDriverWrapper driver;

    public void buyFridge()
    {

        driver.get("http://hotline.ua");
      Actions actions = new Actions(driver.getOriginalDriver());

       /*
        actions.moveToElement(driver.findElement(By.xpath("//a[@href='/bt/']")));
        actions.perform();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
       driver.findElement(By.xpath("//a[@href='/bt/holodilniki/']")).click();
      // driver.findElement(By.className("op_all")).click();
        driver.findElement(By.xpath("//*[@id='filters']//a[contains(text),'LG']")).click();
      //  ((JavascriptExecutor) driver.getOriginalDriver().executeScript("arguments[0].setAttribute()");
      */
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.id("shade")).click();

        WebElement we = driver.findElement(By.xpath("//*[@href='/bt/']"));
        Log4Test.info("Opening fridge category from menu...");
        actions.moveToElement(we).perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Log4Test.info("PAss");
       driver.findElement(By.xpath("//a[@href='/bt/holodilniki/']")).click();

      driver.findElement(By.xpath("//div[@id='filters']//a[contains(text(), 'LG')]")).click();
        try
        {
            Thread.sleep(5000);

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }
    @AfterSuite
    public void closeEnv()
    {
        if (driver!=null)
        {
            driver.quit();
        }
    }
}

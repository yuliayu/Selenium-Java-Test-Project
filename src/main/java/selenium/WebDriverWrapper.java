package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log4Test;

import java.util.List;
import java.util.Set;

public class WebDriverWrapper implements WebDriver,TakesScreenshot{
    /**
     * Created by bionic on 11/10/14.
     */
    public WebDriver driver;
    public static final int TIME_TO_WAIT = 25;


    public WebDriverWrapper(WebDriver driver)
    {
        this.driver=driver;
    }

    @Override
    public void get(String s) {
        driver.get(s);

    }

    public WebDriver getOriginalDriver()
    {
        return driver;
    }

    @Override
    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle()
    {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_TO_WAIT);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_TO_WAIT);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
       return  driver.findElement(by);
    }

    @Override
    public String getPageSource()
    {
        return driver.getPageSource();
    }

    @Override
    public void close()
    {
        driver.close();
    }

    @Override
    public void quit()
    {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles()
    {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle()
    {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo()
    {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate()
    {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }
    @Override
    public <X> X getScreenshotAs(OutputType<X> outType) {
        try {
            if (driver instanceof FirefoxDriver) {
                return ((FirefoxDriver) driver).getScreenshotAs(outType);
            } else if (driver instanceof ChromeDriver) {
                return ((ChromeDriver) driver).getScreenshotAs(outType);
            } else if (driver instanceof InternetExplorerDriver) {
                return ((InternetExplorerDriver) driver).getScreenshotAs(outType);
            } else {
                return null;
            }
        }
        catch (Exception e)
        {
            Log4Test.error("Unexpected exception in getScreenshotAs: " + e.getMessage());
        }
        return null;
    }

}



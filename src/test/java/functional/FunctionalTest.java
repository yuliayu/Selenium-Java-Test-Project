package functional;

import org.openqa.selenium.By;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import selenium.WebDriverFactory;
import selenium.WebDriverWrapper;
import utils.PropertyLoader;

/**
 * Created by bionic on 11/17/14.
 */
public class FunctionalTest {
    public WebDriverWrapper driver;
    @BeforeSuite
    public void setEnv()

    {
        driver = WebDriverFactory.initDriver(PropertyLoader.loadProperty("browser.name"));
        driver.get("http://hotline.ua");
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

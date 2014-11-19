package functional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by bionic on 11/19/14.
 */
public class GridExample {

    @Test

    public void gridTest()
    {
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        WebDriver driver = null;
        try{
            driver = new RemoteWebDriver(new URL("http://localhost:9999/wd/hub"), capability);
        } catch(MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get("http://hotline.ua");
        driver.quit();
    }
}

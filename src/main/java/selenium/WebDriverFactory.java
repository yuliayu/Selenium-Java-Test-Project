package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import utils.PropertyLoader;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bionic on 11/10/14.
 */
public class WebDriverFactory {
    public static final String FIREFOX = "firefox";
    public static final String HTMLUNIT = "htmlunit";
    public static final String FIREFOX_REMOTE = "firefox_remote";

    public static WebDriverWrapper initDriver (String driverName)
    {
        WebDriverWrapper driverWrapper = null;
        WebDriver driver = null;
        if (driverName.equalsIgnoreCase(FIREFOX))
            driverWrapper = new WebDriverWrapper(new FirefoxDriver());
        else if (driverName.equalsIgnoreCase(HTMLUNIT))
            driverWrapper = new WebDriverWrapper(new HtmlUnitDriver());
        else if (driverName.equalsIgnoreCase(FIREFOX_REMOTE))
        {
            DesiredCapabilities capability = DesiredCapabilities.firefox();
            try{
                driverWrapper = new WebDriverWrapper(new RemoteWebDriver(new URL(PropertyLoader.loadProperty("selenium.hub")), capability));
            } catch(MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else
            Assert.fail("Invalid driver configuration");

        driverWrapper.manage().deleteAllCookies();
        driverWrapper.manage().window().maximize();

        return  driverWrapper;
    }

}

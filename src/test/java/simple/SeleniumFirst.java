package simple;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by bionic on 10/27/14.
 */
public class SeleniumFirst {

    public static WebDriver driver;

    @DataProvider
    public Object [][] filters() {
        return new Object[][] {
                new Object[] {"http://testng.org/doc/index.html", "TestNG"},
                new Object[] {"https://www.google.com/doodles", ""},
                new Object[] {"http://testng.org", "trololo"},
        };
    }

    @BeforeSuite
    public void initEnv()
    {
        driver = new HtmlUnitDriver();
    }
    @Test (dataProvider = "filters")
    public void numberOfFilteredElementsTest(String siteUrl, String text)
    {
        driver.get(siteUrl);
        Assert.assertTrue(driver.findElements(By.linkText(text)).size()>0);
        //Assert.assertEquals( type , "int");
    }

    @AfterSuite
    public void shutEnv()
    {
        if (driver != null)
            driver.quit();
    }
}

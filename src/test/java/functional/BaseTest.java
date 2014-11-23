package functional;

import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;
import selenium.WebDriverFactory;
import selenium.WebDriverWrapper;
import utils.Log4Test;
import utils.PropertyLoader;
import utils.ScreenshotMaker;

import java.io.File;
import java.io.IOException;

/**
 * Created by test on 11/23/14.
 */
public abstract class BaseTest {
    protected WebDriverWrapper driver;
    @BeforeSuite
    public void cleanScreenshots()
    {
        File scrSubDir = new File(PropertyLoader.loadProperty("screenshot.folder"));

        if (scrSubDir.exists()) {
            try {
                FileUtils.cleanDirectory(scrSubDir);
            } catch (IOException e) {
                Log4Test.error("Failed to clean the screenshot directory" + e.getMessage());

            }
        }
        ScreenshotMaker.clearScreenShotSubDirectory("");
    }
    @BeforeClass
    public void setEnv()
    {
        driver = WebDriverFactory.initDriver(PropertyLoader.loadProperty("browser.name"));
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String pageName = driver.getTitle();
            Log4Test.info("Taking screenshot for: " + pageName);
            ScreenshotMaker screenshotMaker = new ScreenshotMaker(driver);
            screenshotMaker.takeScreenShot(pageName);

            //    /target/screenShot/PageName

        }
    }
    @AfterClass
    public void closeEnv()
    {
        if (driver!=null)
        {
            driver.quit();
        }
    }
}

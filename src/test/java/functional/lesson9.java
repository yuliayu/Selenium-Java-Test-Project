package functional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import selenium.WebDriverFactory;
import selenium.WebDriverWrapper;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by test on 11/17/14.
 */
public class lesson9 extends FunctionalTest{
    public static WebDriverWrapper driver;
    public final  By CLOSESELECTOR = By.id("shade");

    public void closeSelector() {
        driver.findElement(CLOSESELECTOR).click();
    }

//    public void closeWin() {
//        driver.findElement(OKSELECTOR2).click();
//    }

    @Test
    public void buyFrige()
    {
        driver = WebDriverFactory.initDriver("firefox");
        driver.get("http://hotline.ua/");
      //  closeWin();
        closeSelector();

        Actions actions = new Actions(driver.getOriginalDriver());
        actions.moveToElement(driver.findElement(By.xpath("//a[@href='/bt/']")));
        actions.perform();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@href='/bt/holodilniki/']")).click();


        driver.findElement(By.className("op_all")).click();
        moveMouseDown();

        ((JavascriptExecutor)driver.getOriginalDriver()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                driver.findElement(By.className("jspPane")), "style", "padding: 0px 20px 0px 0px; width: 185px; top: -688px;");



        driver.findElement(By.xpath("//*[@id='filters']//a[contains(text(),'West')]")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void moveMouseDown()
    {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.setAutoDelay(900);
        robot.mouseWheel(-10);
    }
}

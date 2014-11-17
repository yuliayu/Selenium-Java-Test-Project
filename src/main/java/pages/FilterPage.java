package pages;

import actors.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.WebDriverWrapper;
import utils.Log4Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by test on 11/12/14.
 */
public class FilterPage extends HomePage
{
    public FilterPage(WebDriverWrapper driver)
    {
        super(driver);
    }

    public void addFilter(String filterName)
    {

        Log4Test.info("Selecting of " +filterName+ " filter");
        driver.findElement(By.xpath("//*[@id='filters']//a[contains(text(), '"+filterName+"')]")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        driver.findElement(By.className("op_all")).click();
    }
    public void sortByPrice()
    {
        Log4Test.info("Opening sorting menu...");
        driver.findElement(By.xpath("//*[contains(@class,'sorter-new')]/*[@class='box'][1]//*[@class='ddopener']")).click();
        driver.findElement(By.xpath("//a[contains(@href,'sort=0')]")).click();
    }
    public boolean isFridgeFilterPageOpened()
    {
        return driver.getCurrentUrl().contains("/holodilniki/");
    }
    public Product getProductByIndex(int index)
    {
        Product product = new Product();
        product.name = driver.findElement(By.xpath("//ul[contains(@class,'catalog')]/li["+index+"]//div[@class = 'title-box']//a")).getText();
        product.price = driver.findElement(By.xpath("//ul[contains(@class,'catalog')]/li["+index+"]//div[@class = 'price']//span[@class= 'orng']")).getText();
        Log4Test.info("Found product index : " + index + " name: " + product.name + " price: " +product.price );
        return product;

    }

}

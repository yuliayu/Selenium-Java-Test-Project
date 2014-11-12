package pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by test on 11/12/14.
 */
public class FilterPage extends HomePage
{
    public FilterPage(WebDriver driver)
    {
        super(driver);
    }
    public boolean isFilterPageOpened()
    {
        return driver.getCurrentUrl().contains("/holodilniki/627/");
    }

}

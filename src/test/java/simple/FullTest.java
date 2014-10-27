package simple;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by bionic on 10/27/14.
 */
public class FullTest {
    double c;
    @BeforeSuite
    public void preConfiguration ()
    {
        //setup environment
         c = 4;
    }
    @Test
    public void test2and2()
    {
        int a = 2;
        int b = 2;
        a = a + b;
        Assert.assertEquals((double)a,c);
    }
    @AfterSuite
    public void doDelete()
    {
        c=5;
    }
}

package simple;

import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * Created by bionic on 22.10.14.
 */
public class FirstTest {
    @Test
    public void firstTest()
    {
        System.out.print("new print");
        Assert.assertTrue(true);
    }
}

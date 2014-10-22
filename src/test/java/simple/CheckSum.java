package simple;

import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * Created by bionic on 22.10.14.
 */
public class CheckSum {
    @Test
    public void firstTest()
    {
        Sum sum = new Sum();

        Assert.assertEquals(sum.sum(5, 3),8);
    }
}

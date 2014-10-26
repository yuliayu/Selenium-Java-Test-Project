package simple;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by test on 10/27/14.
 */
public class CheckEmail {
    @Parameters ({ "email" })
    @Test
    public void checkEmail(String p)
    {
        Assert.assertTrue(ValidateEmail.checkEmail(p), "Incorrect email format of " + p);
    }

    @Parameters ({"wrong_email"})
    @Test
    public void failedEmail (String p)
    {
        Assert.assertFalse(ValidateEmail.checkEmail(p));
    }
}

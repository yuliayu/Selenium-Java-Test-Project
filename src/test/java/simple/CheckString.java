package simple;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by test on 10/29/14.
 */
public class CheckString {
    FileWriter writer;
    @DataProvider
    public Object [][] filters() {
        return new Object[][] {
                new Object[] {"My name is Yulia", "is Yulia"},
                new Object[] {"This is homework","ho"},
                new Object[] {"Hello", "Z"}
        };
    }

    @BeforeSuite
    public void initEnv() throws IOException
    {
        writer = new FileWriter("test_strings.txt");


    }
    @Test(dataProvider = "filters")
    public void numberOfFilteredElementsTest(String string1, String string2) throws IOException
    {
        if (string1.contains(string2)) {
            writer.append(string1 + " contains: \n" + string2 + "\n");
        }
        else
        {
            writer.append(string1 + " doesn't contain: \n" + string2 + "\n");

        }
        Assert.assertTrue(string1.contains(string2));

    }

    @AfterSuite
    public void closeFile() throws IOException
    {
        if (writer!=null)
        {
            writer.close();
        }
    }
}

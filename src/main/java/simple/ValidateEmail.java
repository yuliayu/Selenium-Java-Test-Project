package simple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by test on 10/27/14.
 */
public class ValidateEmail {

    public static boolean checkEmail (String emailString)
    {
        Pattern p = Pattern.compile("^([\\-a-z0-9.]){5,30}[^\\.\\-]@([a-z0-9-]{1,63})(\\.)([a-z]{2,4})(\\.?)([a-z]{0,4})+$");
        Matcher m = p.matcher(emailString.toLowerCase());
        return m.matches();
    }
}

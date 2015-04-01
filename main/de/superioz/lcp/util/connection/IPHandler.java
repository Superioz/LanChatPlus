package main.de.superioz.lcp.util.connection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class created on April in 2015
 */
public class IPHandler {

    /**
     * Checks if the given string is a valid ip address
     *
     * @param s The string
     * @return A boolean object with statement above
     */
    public static boolean isIP(String s){
        Pattern ptn = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
        Matcher mtch = ptn.matcher(s);

        return mtch.find();
    }

}

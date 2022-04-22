package vn.plusplus.lms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtil {
    private static final String TOKEN_PATTERN = "Bearer (.*)";
    public static String getAccessToken(String text) {
        Pattern pattern = Pattern.compile(TOKEN_PATTERN);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find( )) {
            return matcher.group(1);
        }
        return null;
    }
}

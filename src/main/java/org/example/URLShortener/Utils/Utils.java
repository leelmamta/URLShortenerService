package org.example.URLShortener.Utils;

import static org.example.URLShortener.constants.URLConstants.BASE62_CHARS;

public class Utils {
    public static String toBase62(long value) {
        StringBuilder base62 = new StringBuilder();
        while (value > 0) {
            int remainder = (int) (value % 62);
            base62.append(BASE62_CHARS.charAt(remainder));
            value /= 62;
        }
        return base62.reverse().toString();
    }
}

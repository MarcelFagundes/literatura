package com.challengeliteratura.challengeliteratura.util;


public class StringsUtil {
    public static String limitLength(String strings, int maximumLength) {
        if (strings == null) {
            return "";
        }
        if (maximumLength < 0) {
            throw new IllegalArgumentException("A palavra deve ter mais letras");
        }
        if (strings.length() <= maximumLength) {
            return strings;
        } else {
            return strings.substring(0, maximumLength);
        }
    }
}
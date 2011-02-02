package com.thoughtworks.videorental.util;

public class ParamsUtil {
    public static boolean nullOrEmpty(String... params) {
        for (String param : params) {
            if (param == null || param.isEmpty()) { return true; }
        }
        return false;
    }
}

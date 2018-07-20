package com.example.nguyenduy.projectbase.utils.method;

import android.text.TextUtils;

import java.util.List;

public class MethodUtils {

    public static boolean isEmpty(List<?> objects) {
        if (null == objects || objects.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Object[] objects) {
        if (null == objects || objects.length == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String input) {
        return TextUtils.isEmpty(input);
    }

    public static String[] convertArray(List<String> list) {
        if (list == null) {
            return null;
        }
        return list.toArray(new String[list.size()]);
    }
}

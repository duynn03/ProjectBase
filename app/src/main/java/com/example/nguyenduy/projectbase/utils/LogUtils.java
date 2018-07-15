package com.example.nguyenduy.projectbase.utils;

import android.util.Log;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;

public class LogUtils {
    private static final String tag = ResourceUtils.getString(R.string.app_name) + "." + LogUtils.class.getSimpleName();

    public final static void e(String message) {
        Log.e(tag, message);
    }

    public final static void d(String message) {
        Log.d(tag, message);
    }

    public final static void w(String message) {
        Log.w(tag, message);
    }

    public final static void v(String message) {
        Log.v(tag, message);
    }

    public final static void i(String tag, String message) {
        Log.i(tag, message);
    }
}

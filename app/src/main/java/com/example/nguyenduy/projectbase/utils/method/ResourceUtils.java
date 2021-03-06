package com.example.nguyenduy.projectbase.utils.method;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.nguyenduy.projectbase.application.MyApplication;

public class ResourceUtils {

    public static Resources getResource() {
        return MyApplication.getAppContext().getResources();
    }

    public static int getDimension(int id) {
        return getResource().getDimensionPixelSize(id);
    }

    public static String getString(int id) {
        return getResource().getString(id);
    }

    public static int getColor(int id) {
        return getResource().getColor(id);
    }

    public static Drawable getDrawable(int id) {
        return getResource().getDrawable(id);
    }

}

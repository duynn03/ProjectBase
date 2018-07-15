package com.example.nguyenduy.projectbase.utils.method;

import android.content.res.Resources;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;

public class WindowUtils {

    private static int getStatusBarHeight() {
        int result = 0;
        int resourceId = ResourceUtils.getResource().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = ResourceUtils.getDimension(resourceId);
        }
        return result;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /*include status bar*/
    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private static int getWidthOfScreenDesign() {
        return ResourceUtils.getDimension(R.dimen.width_of_screen_design);
    }

    private static int getHeightOfScreenDesign() {
        return ResourceUtils.getDimension(R.dimen.height_of_screen_design);
    }

    public static int convertHeightExcludeWidthStatusBar(float length) {
        return convertHeight(length) - getStatusBarHeight();
    }

    public static int convertHeight(float length) {
        return (int) ((length * getScreenHeight()) / getHeightOfScreenDesign());
    }

    public static int convertWidth(float length) {
        return (int) ((length * getScreenWidth()) / getWidthOfScreenDesign());
    }

}

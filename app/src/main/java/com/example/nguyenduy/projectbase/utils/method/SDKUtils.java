package com.example.nguyenduy.projectbase.utils.method;

import android.os.Build;

import com.example.nguyenduy.projectbase.BuildConfig;

public class SDKUtils {

    public static int getVersionSdkCurrent() {
        return Build.VERSION.SDK_INT;
    }

    // >= 6.0
    public static boolean isVersionSdkCurrentGreaterOrEqualVersionM() {
        return getVersionSdkCurrent() >= Build.VERSION_CODES.M;
    }

    // >= 7.0
    public static boolean isVersionSdkCurrentGreaterOrEqualVersionN() {
        return getVersionSdkCurrent() >= Build.VERSION_CODES.N;
    }

    // >= api 16
    public static boolean isVersionSdkCurrentGreaterOrEqualVersionJellyBean() {
        return getVersionSdkCurrent() >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    public static String getProductFlavors() {
        return BuildConfig.FLAVOR;
    }

    public static String getBuildTypes() {
        return BuildConfig.BUILD_TYPE;
    }

    public static String getApplicationID() {
        return BuildConfig.APPLICATION_ID;
    }

}

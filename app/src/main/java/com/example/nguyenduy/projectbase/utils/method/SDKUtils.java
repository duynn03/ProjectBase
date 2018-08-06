package com.example.nguyenduy.projectbase.utils.method;

import android.os.Build;

import com.example.nguyenduy.projectbase.BuildConfig;

public class SDKUtils {

    public static int getVersionSdkCurrent() {
        return Build.VERSION.SDK_INT;
    }

    // >= 8.0 (api 26)
    public static boolean isVersionSdkCurrentGreaterOrEqualVersion0() {
        return getVersionSdkCurrent() >= Build.VERSION_CODES.O;
    }

    // >= 7.0 (api 24)
    public static boolean isVersionSdkCurrentGreaterOrEqualVersionN() {
        return getVersionSdkCurrent() >= Build.VERSION_CODES.N;
    }

    // >= 6.0 (api 23)
    public static boolean isVersionSdkCurrentGreaterOrEqualVersionM() {
        return getVersionSdkCurrent() >= Build.VERSION_CODES.M;
    }

    // >= 4.1 (api 16)
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

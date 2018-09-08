package com.example.nguyenduy.projectbase.utils.method;

import android.os.Build;

import com.example.nguyenduy.projectbase.BuildConfig;

public class SDKUtils {

    public static int getVersionSdkCurrent() {
        return Build.VERSION.SDK_INT;
    }

    // >= 9.0 (api 28)
    public static boolean isVersionSdkCurrentGreaterOrEqualVersionP() {
        return getVersionSdkCurrent() >= Build.VERSION_CODES.P;
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

    // >= 5.0 (api 21)
    public static boolean isVersionSdkCurrentGreaterOrEqualVersionLollipop() {
        return getVersionSdkCurrent() >= Build.VERSION_CODES.LOLLIPOP;
    }

    // >= 4.2 (api 17)
    public static boolean isVersionSdkCurrentGreaterOrEqualVersionJellyBeanMR1() {
        return getVersionSdkCurrent() >= Build.VERSION_CODES.JELLY_BEAN_MR1;
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

package com.example.nguyenduy.projectbase.utils.method;

import com.example.nguyenduy.projectbase.BuildConfig;

public class SDKUtils {

    public static int getVersionSDK() {
        return android.os.Build.VERSION.SDK_INT;
    }

    public static boolean isVersionSDKGreaterVersionM() {
        return getVersionSDK() > android.os.Build.VERSION_CODES.M;
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

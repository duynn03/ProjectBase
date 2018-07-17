package com.example.nguyenduy.projectbase.utils.method;

public class SDKUtils {

    public static int getVersionSDK() {
        return android.os.Build.VERSION.SDK_INT;
    }

    public static boolean isVersionSDKGreaterVersionM() {
        return android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M;
    }
}
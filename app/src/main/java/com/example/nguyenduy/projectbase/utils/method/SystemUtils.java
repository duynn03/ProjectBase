package com.example.nguyenduy.projectbase.utils.method;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.example.nguyenduy.projectbase.application.MyApplication;

public class SystemUtils {

    public static ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) MyApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static boolean isMobileConnected() {
        return getConnectivityManager().getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
    }

    public static boolean isWifiConnected() {
        return getConnectivityManager().getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
    }

    public static boolean isNetworkOnline() {
        NetworkInfo networkInfo = getConnectivityManager().getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @SuppressLint("NewApi")
    public static boolean isAirplaneModeOn() {
        if (SDKUtils.isVersionSdkCurrentGreaterOrEqualVersionJellyBeanMR1())
            return Settings.Global.getInt(MyApplication.getAppContext().getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        return Settings.System.getInt(MyApplication.getAppContext().getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) != 0;
    }
}

package com.example.nguyenduy.projectbase.utils.method;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
}

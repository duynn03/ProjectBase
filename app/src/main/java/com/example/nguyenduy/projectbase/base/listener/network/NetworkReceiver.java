package com.example.nguyenduy.projectbase.base.listener.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;

import com.example.nguyenduy.projectbase.utils.method.SystemUtils;

public class NetworkReceiver extends BroadcastReceiver {

    private INetworkChangeListener mListener;

    public NetworkReceiver(INetworkChangeListener listener) {
        mListener = listener;
    }

    public interface INetworkChangeListener {
        void changeNetwork(boolean isWifiConnected, boolean isMobileConnected);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkInfo networkInfo = SystemUtils.getConnectivityManager().getActiveNetworkInfo();
        mListener.changeNetwork(SystemUtils.isWifiConnected(), SystemUtils.isMobileConnected());
        /*// Checks the user prefs and the network connection. Based on the result, decides whether
        // to refresh the display or keep the current display.
        // If the userpref is Wi-Fi only, checks to see if the device has a Wi-Fi connection.
        if (WIFI.equals(sPref) && networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            // If device has its Wi-Fi connection, sets refreshDisplay
            // to true. This causes the display to be refreshed when the user
            // returns to the app.
            refreshDisplay = true;
            Toast.makeText(context, R.string.wifi_connected, Toast.LENGTH_SHORT).show();

            // If the setting is ANY network and there is a network connection
            // (which by process of elimination would be mobile), sets refreshDisplay to true.
        } else if (ANY.equals(sPref) && networkInfo != null) {
            refreshDisplay = true;

            // Otherwise, the app can't download content--either because there is no network
            // connection (mobile or Wi-Fi), or because the pref setting is WIFI, and there
            // is no Wi-Fi connection.
            // Sets refreshDisplay to false.
        } else {
            refreshDisplay = false;
            Toast.makeText(context, R.string.lost_connection, Toast.LENGTH_SHORT).show();
        }*/
    }
}
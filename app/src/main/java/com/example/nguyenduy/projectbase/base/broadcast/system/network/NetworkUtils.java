package com.example.nguyenduy.projectbase.base.broadcast.system.network;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.utils.method.SystemUtils;

import static com.example.nguyenduy.projectbase.utils.Constants.IntentCommon.REQUEST_CHECK_SETTINGS_MOBILE;
import static com.example.nguyenduy.projectbase.utils.Constants.IntentCommon.REQUEST_CHECK_SETTINGS_WIFI;
import static com.example.nguyenduy.projectbase.utils.Constants.IntentCommon.REQUEST_CHECK_SETTINGS_WIRELESS;

public class NetworkUtils {

    private NetworkReceiver mReceiver;
    private Context mContext;

    public NetworkUtils(Context context) {
        mContext = context;
        mReceiver = new NetworkReceiver();
    }

    // Registers BroadcastReceiver to track network connection changes.
    public void register(
            NetworkReceiver.IChangeNetworkListener networkListener,
            NetworkReceiver.IChangeWifiListener wifiListener,
            NetworkReceiver.IChangeMobileDataListener mobileDataListener) {

        mReceiver.setListenerNetworkChange(networkListener);
        mReceiver.setListenerWifiChange(wifiListener);
        mReceiver.setListenerMobileDataChange(mobileDataListener);

        mContext.registerReceiver(mReceiver, createIntentReceiver());
    }

    private IntentFilter createIntentReceiver() {
        return new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    public void unregister() {
        if (null != mReceiver) {
            mReceiver.onDestroy();
            mContext.unregisterReceiver(mReceiver);
        }
    }

}

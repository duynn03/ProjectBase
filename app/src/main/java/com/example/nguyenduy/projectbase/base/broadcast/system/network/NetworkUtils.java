package com.example.nguyenduy.projectbase.base.broadcast.system.network;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

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

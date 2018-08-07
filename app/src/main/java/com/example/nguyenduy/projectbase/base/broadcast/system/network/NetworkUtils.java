package com.example.nguyenduy.projectbase.base.broadcast.system.network;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class NetworkUtils {

    private NetworkReceiver receiver;
    private Context mContext;

    public NetworkUtils(Context context) {
        mContext = context;
        receiver = new NetworkReceiver();
    }

    // Registers BroadcastReceiver to track network connection changes.
    public void register(
            NetworkReceiver.IChangeNetworkListener networkListener,
            NetworkReceiver.IChangeWifiListener wifiListener,
            NetworkReceiver.IChangeMobileDataListener mobileDataListener) {

        receiver.setListenerNetworkChange(networkListener);
        receiver.setListenerWifiChange(wifiListener);
        receiver.setListenerMobileDataChange(mobileDataListener);

        mContext.registerReceiver(receiver, createIntent());
    }

    private IntentFilter createIntent() {
        return new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    public void unregister() {
        if (null != receiver) {
            receiver.onDestroy();
            mContext.unregisterReceiver(receiver);
        }
    }

}

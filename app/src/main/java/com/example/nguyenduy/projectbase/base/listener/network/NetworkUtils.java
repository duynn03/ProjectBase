package com.example.nguyenduy.projectbase.base.listener.network;

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

    public void setListenerNetworkChange(NetworkReceiver.INetworkChangeListener listener) {
        receiver.setListenerNetworkChange(listener);
    }

    public void setListenerWifiChange(NetworkReceiver.IChangeWifiListener listener) {
        receiver.setListenerWifiChange(listener);
    }

    public void setListenerMobileDataChange(NetworkReceiver.IChangeMobileDataListener listener) {
        receiver.setListenerMobileDataChange(listener);
    }

    // Registers BroadcastReceiver to track network connection changes.
    public void registerChangeNetwork() {
        mContext.registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void unregisterChangeNetwork() {
        if (null != receiver) {
            receiver.onDestroy();
            mContext.unregisterReceiver(receiver);
        }
    }

}

package com.example.nguyenduy.projectbase.base.listener.network;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class NetworkUtils {

    private NetworkReceiver receiver;
    private Context mContext;

    public NetworkUtils(Context context, NetworkReceiver.INetworkChangeListener listener) {
        mContext = context;
        receiver = new NetworkReceiver(listener);
    }

    // Registers BroadcastReceiver to track network connection changes.
    public void registerChangeNetwork() {
        mContext.registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void unregisterChangeNetwork() {
        if (null != receiver)
            mContext.unregisterReceiver(receiver);
    }

}

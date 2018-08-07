package com.example.nguyenduy.projectbase.base.broadcast.system.airplane;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class AirplaneUtils {

    private Context mContext;
    private AirplaneReceiver mBroadcast;

    public AirplaneUtils(Context context) {
        mContext = context;
        mBroadcast = new AirplaneReceiver();
    }

    /*nhận broadcast khi context vẫn còn sống*/
    public void register(AirplaneReceiver.IChangeAirplaneListener listener) {
        mBroadcast.setListenerAirplaneChange(listener);
        mContext.registerReceiver(mBroadcast, createIntent());
    }

    private IntentFilter createIntent() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        return filter;
    }

    public void unregister() {
        mContext.unregisterReceiver(mBroadcast);
    }

}

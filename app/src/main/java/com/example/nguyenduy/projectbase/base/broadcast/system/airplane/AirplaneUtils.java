package com.example.nguyenduy.projectbase.base.broadcast.system.airplane;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class AirplaneUtils {

    private Context mContext;
    private AirplaneReceiver mReceiver;

    public AirplaneUtils(Context context) {
        mContext = context;
        mReceiver = new AirplaneReceiver();
    }

    public void register(AirplaneReceiver.IChangeAirplaneListener listener) {
        mReceiver.setListenerAirplaneChange(listener);
        mContext.registerReceiver(mReceiver, createIntentReceiver());
    }

    private IntentFilter createIntentReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        return filter;
    }

    public void unregister() {
        mContext.unregisterReceiver(mReceiver);
    }

}

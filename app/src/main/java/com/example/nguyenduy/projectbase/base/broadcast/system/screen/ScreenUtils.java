package com.example.nguyenduy.projectbase.base.broadcast.system.screen;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ScreenUtils {

    private Context mContext;
    private ScreenReceiver mBroadcast;

    public ScreenUtils(Context context) {
        mContext = context;
        mBroadcast = new ScreenReceiver();
    }

    /*nhận broadcast khi context vẫn còn sống*/
    public void register(ScreenReceiver.IChangeScreenListener listener) {
        mBroadcast.setListenerScreenChange(listener);
        mContext.registerReceiver(mBroadcast, createIntent());
    }

    private IntentFilter createIntent() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        return filter;
    }

    public void unregister() {
        mContext.unregisterReceiver(mBroadcast);
    }

}

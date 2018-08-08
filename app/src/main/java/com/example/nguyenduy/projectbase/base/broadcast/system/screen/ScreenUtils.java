package com.example.nguyenduy.projectbase.base.broadcast.system.screen;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ScreenUtils {

    private Context mContext;
    private ScreenReceiver mReceiver;

    public ScreenUtils(Context context) {
        mContext = context;
        mReceiver = new ScreenReceiver();
    }

    /*nhận broadcast khi context vẫn còn sống*/
    public void register(ScreenReceiver.IChangeScreenListener listener) {
        mReceiver.setListenerScreenChange(listener);
        mContext.registerReceiver(mReceiver, createIntentReceiver());
    }

    private IntentFilter createIntentReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        return filter;
    }

    public void unregister() {
        mContext.unregisterReceiver(mReceiver);
    }

}

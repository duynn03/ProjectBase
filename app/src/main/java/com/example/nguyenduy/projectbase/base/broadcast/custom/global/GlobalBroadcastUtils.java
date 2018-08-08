package com.example.nguyenduy.projectbase.base.broadcast.custom.global;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class GlobalBroadcastUtils {

    private GlobalReceiver mReceiver;
    private Context mContext;

    public GlobalBroadcastUtils(Context context) {
        mContext = context;
    }

    public void sendBroadcast(String nameBroadcast, String data) {
        mContext.sendBroadcast(createIntentSender(nameBroadcast, data));
    }

    private Intent createIntentSender(String nameBroadcast, String data) {
        Intent intentSender = new Intent(nameBroadcast);
        intentSender.putExtra("data", data);
        return intentSender;
    }

    public void register(String nameBroadcast, GlobalReceiver.IChangeGlobalListener listener) {
        mReceiver = new GlobalReceiver();
        mReceiver.setListenerGlobalChange(listener);
        mContext.registerReceiver(mReceiver, createIntentReceiver(nameBroadcast));
    }

    private IntentFilter createIntentReceiver(String nameBroadcast) {
        return new IntentFilter(nameBroadcast);
    }

    public void unregister() {
        mContext.unregisterReceiver(mReceiver);
    }

}

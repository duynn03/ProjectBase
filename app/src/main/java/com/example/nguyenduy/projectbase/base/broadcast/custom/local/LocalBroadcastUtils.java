package com.example.nguyenduy.projectbase.base.broadcast.custom.local;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

public class LocalBroadcastUtils {

    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver mReceiver;

    public LocalBroadcastUtils(Context context) {
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    public void sendBroadcast(String nameBroadcast, String data) {
        // Có thể sendBroadcastSync
        // https://developer.android.com/reference/android/support/v4/content/LocalBroadcastManager#registerReceiver(android.content.BroadcastReceiver,%20android.content.IntentFilter)
        localBroadcastManager.sendBroadcast(createIntentSender(nameBroadcast, data));
    }

    private Intent createIntentSender(String nameBroadcast, String data) {
        Intent intentSender = new Intent(nameBroadcast);
        intentSender.putExtra("data", data);
        return intentSender;
    }

    public void register(String nameBroadcast, LocalReceiver.IChangeLocalListener listener) {
        mReceiver = new LocalReceiver();
        mReceiver.setListenerLocalChange(listener);
        localBroadcastManager.registerReceiver(mReceiver, createIntentReceiver(nameBroadcast));
    }

    private IntentFilter createIntentReceiver(String nameBroadcast) {
        return new IntentFilter(nameBroadcast);
    }

    public void unregister() {
        localBroadcastManager.unregisterReceiver(mReceiver);
    }

}

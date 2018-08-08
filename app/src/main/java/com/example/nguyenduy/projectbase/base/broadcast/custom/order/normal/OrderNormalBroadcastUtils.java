package com.example.nguyenduy.projectbase.base.broadcast.custom.order.normal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class OrderNormalBroadcastUtils {

    private Order1Receiver mReceiver1;
    private Order2Receiver mReceiver2;
    private Context mContext;

    public OrderNormalBroadcastUtils(Context context) {
        mContext = context;
    }

    public void sendBroadcast(String nameBroadcast, String data) {

        // https://developer.android.com/reference/android/content/Context.html#sendOrderedBroadcast(android.content.Intent,%20java.lang.String)
        mContext.sendOrderedBroadcast(
                createIntentSender(nameBroadcast, data),
                null);
    }

    private Intent createIntentSender(String nameBroadcast, String data) {
        Intent intentSender = new Intent(nameBroadcast);
        intentSender.putExtra("data", data);
        return intentSender;
    }

    public void register(String nameBroadcast, Order1Receiver.IChangeOrder1Listener listener1, Order2Receiver.IChangeOrder2Listener listener2) {
        mReceiver1 = new Order1Receiver();
        mReceiver1.setListenerOrderChange(listener1);

        mReceiver2 = new Order2Receiver();
        mReceiver2.setListenerOrderChange(listener2);

        mContext.registerReceiver(mReceiver1, createIntentReceiver1(nameBroadcast));
        mContext.registerReceiver(mReceiver2, createIntentReceiver2(nameBroadcast));
    }

    private IntentFilter createIntentReceiver1(String nameBroadcast) {
        IntentFilter filter = new IntentFilter(nameBroadcast);
        filter.setPriority(1);
        return filter;
    }

    private IntentFilter createIntentReceiver2(String nameBroadcast) {
        IntentFilter filter = new IntentFilter(nameBroadcast);
        filter.setPriority(2);
        return filter;
    }

    public void unregister() {
        mContext.unregisterReceiver(mReceiver1);
        mContext.unregisterReceiver(mReceiver2);
    }
}

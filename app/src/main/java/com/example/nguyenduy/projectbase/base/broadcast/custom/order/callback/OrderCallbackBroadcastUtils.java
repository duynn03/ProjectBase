package com.example.nguyenduy.projectbase.base.broadcast.custom.order.callback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

// https://www.truiton.com/2013/04/android-sendorderedbroadcast-example-with-priority/
public class OrderCallbackBroadcastUtils {

    private Order3Receiver mReceiver3;
    private Order4Receiver mReceiver4;
    private FinalOrderReceiver mReceiverFinal;
    private Context mContext;

    public OrderCallbackBroadcastUtils(Context context) {
        mContext = context;
    }

    public void sendBroadcast(String nameBroadcast, String data) {

        // https://developer.android.com/reference/android/content/Context.html#sendOrderedBroadcast(android.content.Intent,%20java.lang.String,%20android.content.BroadcastReceiver,%20android.os.Handler,%20int,%20java.lang.String,%20android.os.Bundle)
        mContext.sendOrderedBroadcast(
                createIntentSender(nameBroadcast, data),
                null,   // permission
                mReceiverFinal,  // resultReceiver: broadcacst cuối cùng để nhận result về
                null,   // scheduler: A custom Handler with which to schedule the resultReceiver callback; if null it will be scheduled in the Context's main thread
                Activity.RESULT_OK, // An initial value for the result code
                null,   // An initial value for the result data
                null);  //An initial value for the result extras
    }

    private Intent createIntentSender(String nameBroadcast, String data) {
        Intent intentSender = new Intent(nameBroadcast);
        intentSender.putExtra("data", data);
        return intentSender;
    }

    public void register(String nameBroadcast,
                         Order3Receiver.IChangeOrder3Listener listener3,
                         Order4Receiver.IChangeOrder4Listener listener4,
                         FinalOrderReceiver.IChangeFinalOrderListener listenerFinal) {
        mReceiver3 = new Order3Receiver();
        mReceiver3.setListenerOrderChange(listener3);

        mReceiver4 = new Order4Receiver();
        mReceiver4.setListenerOrderChange(listener4);

        mReceiverFinal = new FinalOrderReceiver();
        mReceiverFinal.setListenerOrderChange(listenerFinal);

        mContext.registerReceiver(mReceiver3, createIntentReceiver3(nameBroadcast));
        mContext.registerReceiver(mReceiver4, createIntentReceiver4(nameBroadcast));
    }

    private IntentFilter createIntentReceiver3(String nameBroadcast) {
        IntentFilter filter = new IntentFilter(nameBroadcast);
        filter.setPriority(1);
        return filter;
    }

    private IntentFilter createIntentReceiver4(String nameBroadcast) {
        IntentFilter filter = new IntentFilter(nameBroadcast);
        filter.setPriority(2);
        return filter;
    }

    public void unregister() {
        mContext.unregisterReceiver(mReceiver3);
        mContext.unregisterReceiver(mReceiver4);
    }
}

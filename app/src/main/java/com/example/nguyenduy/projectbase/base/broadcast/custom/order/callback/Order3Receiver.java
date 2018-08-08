package com.example.nguyenduy.projectbase.base.broadcast.custom.order.callback;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class Order3Receiver extends BroadcastReceiver {

    private static final String TAG = MethodUtils.getTagClass(Order3Receiver.class);

    private IChangeOrder3Listener mListener;

    Order3Receiver() {
    }

    public interface IChangeOrder3Listener {
        void changeStatusOrder3(String nameBroadcast, String data);
    }

    public void setListenerOrderChange(IChangeOrder3Listener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (getResultCode() == Activity.RESULT_CANCELED) {
            LogUtils.i(TAG + "Order3Receiver(): " + "Activity.RESULT_CANCEL");
            // không send broadcast tới các receiver khác nữa (send thẳng tới FinalReceiver)
            return;
        }
        LogUtils.i(TAG + "Order3Receiver(): " + "Activity.RESULT_OK");
        // callback
        updateOrder(intent.getAction(), intent.getStringExtra("data"));
        // send data to Order3Receiver
        Bundle results = getResultExtras(true);
        String extras = results.getString("Extras");
        results.putString("Extras", extras + "-->Order3Receiver");
    }

    private void updateOrder(String nameBroadcast, String data) {
        if (null != mListener)
            mListener.changeStatusOrder3(nameBroadcast, data);
    }
}

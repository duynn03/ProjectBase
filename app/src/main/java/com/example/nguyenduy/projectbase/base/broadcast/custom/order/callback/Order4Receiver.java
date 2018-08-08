package com.example.nguyenduy.projectbase.base.broadcast.custom.order.callback;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class Order4Receiver extends BroadcastReceiver {

    private static final String TAG = MethodUtils.getTagClass(Order4Receiver.class);

    private IChangeOrder4Listener mListener;

    Order4Receiver() {
    }

    public interface IChangeOrder4Listener {
        void changeStatusOrder4(String nameBroadcast, String data);
    }

    public void setListenerOrderChange(IChangeOrder4Listener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (getResultCode() == Activity.RESULT_CANCELED) {
            LogUtils.i(TAG + "Order4Receiver(): " + "Activity.RESULT_CANCEL");
            return;
        }

        LogUtils.i(TAG + "Order4Receiver(): " + "Activity.RESULT_OK");
        // callback UI
        updateOrder(intent.getAction(), intent.getStringExtra("data"));
        // send data to Order3Receiver
        Bundle results = getResultExtras(true);
        results.putString("Extras", "Order4Receiver");

        // không send broadcast tới các receiver khác nữa (send thẳng tới FinalReceiver)
        abortBroadcast();
    }

    private void updateOrder(String nameBroadcast, String data) {
        if (null != mListener)
            mListener.changeStatusOrder4(nameBroadcast, data);
    }
}

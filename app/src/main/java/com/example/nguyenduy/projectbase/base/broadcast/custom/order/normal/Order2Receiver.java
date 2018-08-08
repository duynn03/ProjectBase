package com.example.nguyenduy.projectbase.base.broadcast.custom.order.normal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Order2Receiver extends BroadcastReceiver {

    private IChangeOrder2Listener mListener;

    Order2Receiver() {
    }

    public interface IChangeOrder2Listener {
        void changeStatusOrder2(String nameBroadcast, String data);
    }

    public void setListenerOrderChange(IChangeOrder2Listener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        updateOrder(intent.getAction(), intent.getStringExtra("data"));
    }

    private void updateOrder(String nameBroadcast, String data) {
        if (null != mListener)
            mListener.changeStatusOrder2(nameBroadcast, data);
    }
}

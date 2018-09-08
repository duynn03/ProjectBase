package com.example.nguyenduy.projectbase.base.broadcast.custom.order.normal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Order1Receiver extends BroadcastReceiver {

    private IChangeOrder1Listener mListener;

    Order1Receiver() {
    }

    public interface IChangeOrder1Listener {
        void changeStatusOrder1(String nameBroadcast, String data);
    }

    public void setListenerOrderChange(IChangeOrder1Listener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        updateOrder(intent.getAction(), intent.getStringExtra("data"));
    }

    private void updateOrder(String nameBroadcast, String data) {
        if (null != mListener)
            mListener.changeStatusOrder1(nameBroadcast, data);
    }
}

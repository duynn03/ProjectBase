package com.example.nguyenduy.projectbase.base.broadcast.custom.global;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class GlobalReceiver extends BroadcastReceiver {

    private IChangeGlobalListener mListener;

    GlobalReceiver() {
    }

    public interface IChangeGlobalListener {
        void changeStatusGlobal(String nameBroadcast, String data);
    }

    public void setListenerGlobalChange(IChangeGlobalListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        updateGlobal(intent.getAction(), intent.getStringExtra("data"));
    }

    private void updateGlobal(String nameBroadcast, String data) {
        if (null != mListener)
            mListener.changeStatusGlobal(nameBroadcast, data);
    }
}

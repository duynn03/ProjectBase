package com.example.nguyenduy.projectbase.base.broadcast.custom.local;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LocalReceiver extends BroadcastReceiver {

    private IChangeLocalListener mListener;

    LocalReceiver() {
    }

    public interface IChangeLocalListener {
        void changeStatusLocal(String nameBroadcast, String data);
    }

    public void setListenerLocalChange(IChangeLocalListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        updateLocal(intent.getAction(), intent.getStringExtra("data"));
    }

    private void updateLocal(String nameBroadcast, String data) {
        if (null != mListener)
            mListener.changeStatusLocal(nameBroadcast, data);
    }
}

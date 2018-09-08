package com.example.nguyenduy.projectbase.base.broadcast.custom.permission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PermissionReceiver extends BroadcastReceiver {

    private IChangePermissionListener mListener;

    PermissionReceiver() {
    }

    public interface IChangePermissionListener {
        void changeStatusPermission(String nameBroadcast, String data);
    }

    public void setListenerPermissionChange(IChangePermissionListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        updatePermission(intent.getAction(), intent.getStringExtra("data"));
    }

    private void updatePermission(String nameBroadcast, String data) {
        if (null != mListener)
            mListener.changeStatusPermission(nameBroadcast, data);
    }
}

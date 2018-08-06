package com.example.nguyenduy.projectbase.base.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class NotificationBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = MethodUtils.getTagClass(NotificationBroadcastReceiver.class);

    public NotificationBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.i(TAG + "onReceive(): " + "Action: " + intent.getAction());
    }

}

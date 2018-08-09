package com.example.nguyenduy.projectbase.base.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class NotificationReceiver extends BroadcastReceiver {

    private static final String TAG = MethodUtils.getTagClass(NotificationReceiver.class);

    public NotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.i(TAG + "onReceive(): " + "Action: " + intent.getAction());
        Toast.makeText(context, "onReceive(): " + "Action: " + intent.getAction(), Toast.LENGTH_SHORT).show();
    }

}

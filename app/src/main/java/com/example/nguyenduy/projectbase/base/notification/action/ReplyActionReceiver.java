package com.example.nguyenduy.projectbase.base.notification.action;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class ReplyActionReceiver extends BroadcastReceiver {

    private static final String TAG = MethodUtils.getTagClass(ReplyActionReceiver.class);

    public ReplyActionReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.i(TAG + "onReceive(): " + "Reply Action Message: " + ReplyActionUtils.getReplyMessage(intent));
        Toast.makeText(context, "onReceive(): " + "Reply Action Message: " + ReplyActionUtils.getReplyMessage(intent), Toast.LENGTH_SHORT).show();
    }

}
